package com.starry;

import com.starry.controller.LoginController;
import com.starry.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class AdminApplicationTests {

    @Resource
    UserService userService;
    @Resource
    LoginController loginController;

    @Test
    void contextLoads() {
    }

    @Test
    void getUserByUsername() {
        String username = "admin";
        System.out.println(userService.getUserByUsername(username).toString());
    }

    @Test
    void getCaptcha() {
        long time = new Date().getTime();
        System.out.println(loginController.captcha(String.valueOf(time)));
    }

}
