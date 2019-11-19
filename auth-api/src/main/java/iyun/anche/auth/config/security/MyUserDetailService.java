package iyun.anche.auth.config.security;

import com.alibaba.fastjson.JSON;
import iyun.anche.auth.service.IUserService;
import iyun.anche.auth.service.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 正常用户登录
        Users users = userService.selectByUsername(username);
        if (users == null) {
            log.warn("用户{}不存在", username);
            throw new UsernameNotFoundException(username);
        }
//
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//        // 获取所有权限
//        if (!CollectionUtils.isEmpty(acMember.getAcPermissionSet())) {
//            for (AcPermission acPermission : acMember.getAcPermissionSet()) {
//                if (acPermission.getAuthUri() != null) {
//                    authorityList.add(new SimpleGrantedAuthority(acPermission.getAuthUri()));
//                }
//            }
//        }
//        User myUser = new User(acMember.getAcMember().getMemberName(), acMember.getAcMember().getPassword(), authorityList);
        User myUser = new User(users.getUsername(), users.getPwd(), authorityList);
        log.info("登录成功！用户: {}", JSON.toJSONString(myUser));
        return myUser;
    }

}

