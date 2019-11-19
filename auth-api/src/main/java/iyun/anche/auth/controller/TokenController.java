package iyun.anche.auth.controller;

import iyun.anche.auth.service.ITokenService;
import iyun.anche.auth.common.utils.ResultUtil;
import iyun.anche.auth.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * oauth2权限管理相关接口
 * @since 2019/10/12 下午2:04
 */
@RestController
@RequestMapping("/token")
@RefreshScope
@Slf4j
public class TokenController {

    @Autowired
    ITokenService tokenService;

    /**
     * 密码方式登录
     */
    @GetMapping("/login")
    public Result login(String username, String password, String clientId, String clientSecret) {
        if (clientId == null) {
            return ResultUtil.fail("clientId不允许为空");
        }
        if (clientSecret == null) {
            return ResultUtil.fail("clientSecret不允许为空");
        }
        if (username == null) {
            return ResultUtil.fail("username不允许为空");
        }
        if (password == null) {
            return ResultUtil.fail("password不允许为空");
        }

        return tokenService.loginByPassword(username, password, clientId, clientSecret);
    }

    /**
     * 退出账号，删除token和refresh_token
     * @Return
     * {
     *     "code": 1,
     *     "msg": "账号退出成功",
     *     "data": null
     * }
     */
    @GetMapping("/logout")
    public Result logout(String access_token) {

        if (access_token == null) {
            return ResultUtil.fail("access_token不允许为空");
        }

        boolean key = tokenService.logout(access_token);

        if (key) {
            return ResultUtil.success("账号退出成功");
        } else {
            return ResultUtil.fail("账号退出失败");
        }
    }

    /**
     * 刷新token
     * 推荐直接访问http://localhost:8081/oauth/token，可获得失败的具体的信息
     *
     * 该方法中，只会对请求结果进行返回，造成执行失败的原因表述不够清楚
     */
    @GetMapping("/refreshToken")
    public Result refreshToken(String refresh_token, String clientId, String clientSecret) {
        if (refresh_token == null) {
            return ResultUtil.fail("refresh_token不允许为空");
        }
        if (clientId == null) {
            return ResultUtil.fail("clientId不允许为空");
        }
        if (clientSecret == null) {
            return ResultUtil.fail("clientSecret不允许为空");
        }

        return tokenService.refreshToken(refresh_token, clientId, clientSecret);
    }

}
