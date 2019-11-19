package iyun.anche.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import iyun.anche.auth.repository.IUserRepository;
import iyun.anche.auth.service.IUserService;
import iyun.anche.auth.service.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    /**
     * 根据用户名查询用户信息，用户名唯一
     *
     * @param username
     */
    @Override
    public Users selectByUsername(String username) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "username", "pwd");
        queryWrapper.eq("username", username);
        List<Users> usersList = userRepository.selectList(queryWrapper);

        log.info("", usersList);

        if (usersList.size() > 0) {
            return usersList.get(0);
        } else {
            return null;
        }
    }
}
