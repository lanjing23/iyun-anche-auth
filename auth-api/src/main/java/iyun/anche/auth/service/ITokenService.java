package iyun.anche.auth.service;

import iyun.anche.auth.common.vo.Result;

public interface ITokenService {

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
    Result loginByPassword(String username, String password, String clientId, String clientSecret);

    /**
     * 退出账号，删除token和refresh_token
     */
    boolean logout(String access_token);

    /**
     * 刷新token
     *
     * @param refresh_token refresh_token
     * @param clientId      终端ID
     * @param clientSecret  终端密码
     */
    Result refreshToken(String refresh_token, String clientId, String clientSecret);
}
