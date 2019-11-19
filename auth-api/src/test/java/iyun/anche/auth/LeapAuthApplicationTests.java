package iyun.anche.auth;

import iyun.anche.auth.service.IUserService;
import iyun.anche.auth.service.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LeapAuthApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void selectByUsername() {
        Users us = userService.selectByUsername("admin");

        System.out.println(us);

    }
}
