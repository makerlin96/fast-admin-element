package com.starry;

import com.starry.controller.LoginController;
import com.starry.dao.MenuMapper;
import com.starry.dao.RoleMenuMapper;
import com.starry.dto.UserDTO;
import com.starry.service.MenuService;
import com.starry.service.RoleMenuService;
import com.starry.service.RoleService;
import com.starry.service.UserService;
import com.starry.utils.PageUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class AdminApplicationTests {

    @Resource
    UserService userService;
    @Resource
    LoginController loginController;

    @Resource
    MenuService menuService;

    @Resource
    RoleService roleService;

    @Resource
    MenuMapper menuMapper;
    @Resource
    RoleMenuMapper roleMenuMapper;

    @Resource
    RoleMenuService roleMenuService;


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

    @Test
    void getUserRoles() {
        long userId = 1L;
        System.out.println(roleService.getUserRoles(userId));
    }

    @Test
    void getUserButtons() {
        long userId = 1;
        System.out.println(menuService.getUserButtonList(userId));
    }

    @Test
    void getUserMenus() {
        long userId = 1;
        System.out.println(menuMapper.selectMenuTreeByUserId(userId));
    }

    @Test
    void buildMenus() {
        System.out.println(menuService.buildMenus(menuMapper.selectMenuTreeByUserId(1L)));
    }

    @Test
    void getRoleMenu() {
        System.out.println(roleMenuService.getRolePermissionByRoleId(1L));
    }

    @Test
    void getUserPageList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPage(1L);
        userDTO.setLimit(5L);
        userDTO.setUsername("a");
        PageUtils pageList = userService.getPageList(userDTO);
        System.out.println(pageList.toString());
    }
}
