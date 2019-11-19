package iyun.anche.auth.service;

public interface IUserService {

    /**
     * 根据用户名查询用户信息，用户名唯一
     */
    Users selectByUsername(String username);
}
