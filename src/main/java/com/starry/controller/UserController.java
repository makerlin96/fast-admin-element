package com.starry.controller;

import com.starry.dto.UserDTO;
import com.starry.entity.MenuEntity;
import com.starry.entity.UserEntity;
import com.starry.service.MenuService;
import com.starry.service.RoleService;
import com.starry.service.UserService;
import com.starry.utils.PageUtils;
import com.starry.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping(value = "/sys/user")
public class UserController extends AbstractController {
    @Resource
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/permissions")
    public R getPermissions() {
        Long userId = getUserId();
        UserEntity userEntity = userService.getById(userId);
        // 获取用户角色
        Set<String> userRoles = roleService.getUserRoles(userId);
        // 获取用户可以操作的按钮
        Set<String> buttonSet = menuService.getUserButtonList(userId);
        // 获取用户菜单
        List<MenuEntity> menus = menuService.getMenuTreeByUserId(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("data", userEntity);
        map.put("routes", menuService.buildMenus(menus));
        map.put("roles", userRoles);
        map.put("buttons", buttonSet);
        return R.ok(map);
    }

    @GetMapping(value = "/info")
    public R getInfo(@RequestParam("token") String token) {
        return R.ok();
    }

    @GetMapping
    public R list(@RequestParam Map<String, Object> params) {
        UserDTO userDTO = new UserDTO();
        userDTO.setPage(Long.parseLong(String.valueOf(params.get("page"))));
        userDTO.setUsername(String.valueOf(params.get("username")));
        userDTO.setLimit(Long.parseLong(String.valueOf(params.get("limit"))));
        userDTO.setRealName(String.valueOf(params.get("realName")));
        if (StringUtils.isNotBlank(String.valueOf(params.get("startDate")))) {
            userDTO.setStartDate(Long.parseLong(String.valueOf(params.get("startDate"))));
        } else {
            userDTO.setStartDate(0L);
        }
        if (StringUtils.isNotBlank(String.valueOf(params.get("endDate")))) {
            userDTO.setEndDate(Long.parseLong(String.valueOf(params.get("endDate"))));
        } else {
            userDTO.setEndDate(new Date().getTime());
        }
        PageUtils list = userService.getPageList(userDTO);
        return R.ok().put("data", list);
    }

    @PostMapping
    public R add(@RequestBody UserEntity userEntity) {
        userEntity.setCreateTime(new Date().getTime());
        boolean save = userService.save(userEntity);
        return save ? R.ok() : R.error();
    }

    @PutMapping
    public R edit(@RequestBody UserEntity userEntity) {
        boolean b = userService.updateById(userEntity);
        return b ? R.ok() : R.error();
    }

    @DeleteMapping
    public R del(@RequestBody List<Long> ids) {
        if (ids != null) {
            for (Long id :
                    ids) {
                userService.removeById(id);
            }
        }
        return R.ok();
    }
}
