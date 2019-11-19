package iyun.anche.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import iyun.anche.auth.redis.RedisStringService;
import iyun.anche.auth.service.ITokenService;
import iyun.anche.auth.common.enumeration.AuthExceptionEnum;
import iyun.anche.auth.common.enumeration.ResultCodeEnum;
import iyun.anche.auth.common.utils.ResultUtil;
import iyun.anche.auth.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * token管理相关方法
 * @since 2019/10/14 下午3:34
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    RedisStringService redisStringService;

    /**
     * 密码登录
     *
     * @param username     登录账号
     * @param password     登录密码
     * @param clientId     终端ID
     * @param clientSecret 终端密码
     * @return {
     * "code": 1,
     * "msg": "成功",
     * "data": {
     * "access_token": "812c81fc-e0a1-41a3-896a-5972797aa9dc",
     * "refresh_token": "860aeed9-cfd2-4ac1-9f15-b7f5050eabf2",
     * "scope": "app",
     * "token_type": "bearer",
     * "expires_in": 3599
     * }
     * }
     */
    @Override
    public Result loginByPassword(String username, String password, String clientId, String clientSecret) {
        Result result = new Result();
        HttpHeaders headers = new HttpHeaders();
        // 此处使用SecurityConfig处的加密方式
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        return this.restTemplatePost(requestEntity);
    }

    /**
     * 退出账号，删除token和refresh_token
     *
     * @param access_token
     */
    @Override
    public boolean logout(String access_token) {
        return consumerTokenServices.revokeToken(access_token);
    }

    /**
     * 刷新token
     *
     * @param refresh_token refresh_token
     * @param clientId      终端ID
     * @param clientSecret  终端密码
     */
    @Override
    public Result refreshToken(String refresh_token, String clientId, String clientSecret) {
        // 寻找该refresh_token对应的token
        String token = this.getTokenByRefreshToken(refresh_token);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("refresh_token", refresh_token);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        Result result = this.restTemplatePost(requestEntity);

        // 如果refresh_token失效，删除对应的token，主要目的是删除auth_to_access，否则登录的时候token无法重新生成
//        if (result.getCode() == ResultCodeEnum.FAILED.getCode()) {
//            consumerTokenServices.revokeToken(token);
//        }
        return result;
    }

    /** ******************************私有方法******************* */

    /**
     * restTemplate post url: @tokenUrl/oauth/token
     *
     * @param requestEntity 参数
     */
    private Result restTemplatePost(HttpEntity requestEntity) {
        Result result = new Result();

        try {
            ResponseEntity<JSONObject> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", requestEntity, JSONObject.class);

            result.setCode(ResultCodeEnum.SUCCESS.getCode());
            result.setMsg(ResultCodeEnum.SUCCESS.getMsg());
            JSONObject jo = response.getBody();

            result.setData(jo);
        } catch (HttpClientErrorException e) {
            JSONObject jsonObject = JSONObject.parseObject(e.getResponseBodyAsString());

            result.setCode(e.getStatusCode().value());
            result.setMsg(e.getStatusCode().getReasonPhrase());
            result.setData(jsonObject);

            // 身份信息校验失败
            if (result.getMsg().equals(AuthExceptionEnum.BAD_REQUEST.getRaw())) {
                return ResultUtil.fail(AuthExceptionEnum.BAD_REQUEST.getMsg());
            }

            // 终端信息校验失败
            if (result.getMsg().equals(AuthExceptionEnum.CLIENT_UNAUTHORIZED.getRaw())) {
                return ResultUtil.fail(AuthExceptionEnum.CLIENT_UNAUTHORIZED.getMsg());
            }

        } catch (Exception e) {
            return ResultUtil.fail(e.getMessage());
        }

        return result;
    }

    // 依据refresh_token获取token
    private String getTokenByRefreshToken(String refreshToken) {
        return redisStringService.getRedis("refresh_to_access:" + refreshToken);
    }

}
