package com.starry.controller;

import com.starry.entity.RoleEntity;
import com.starry.service.RoleMenuService;
import com.starry.service.RoleService;
import com.starry.utils.PageUtils;
import com.starry.utils.R;
import com.starry.vo.RolePermissionsVO;
import com.starry.vo.tree.PermissionTreeVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/sys/role")
public class RoleController extends AbstractController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping
    @RequiresPermissions("sys:role:list")
    public R list() {
        PageUtils page = roleService.getPage();
        return R.ok().put("data", page);
    }

    @PostMapping
    @RequiresPermissions("sys:role:add")
    public R add(@RequestBody RoleEntity entity) {
        entity.setCreateTime(new Date());
        roleService.save(entity);
        return R.ok();
    }

    @PutMapping
    @RequiresPermissions("sys:role:edit")
    public R edit(@RequestBody RoleEntity entity) {
        roleService.updateById(entity);
        int rows = roleMenuService.save(entity.getRoleId(), entity.getPermissions());
        return R.ok().put("data", rows);
    }

    @DeleteMapping
    @RequiresPermissions("sys:role:del")
    public R del(@RequestBody Long[] ids) {
        for (Long id :
                ids) {
            roleService.removeById(id);
            roleMenuService.removeById(id);
        }
        return R.ok();
    }

    @GetMapping(value = "/info/{id}")
    @RequiresPermissions("sys:role:list")
    public R info(@PathVariable Long id) {
        RoleEntity entity = roleService.getById(id);
        entity.setPermissions(roleMenuService.getRolePermissionByRoleId(entity.getRoleId()));
        return R.ok().put("data", entity);
    }

    @GetMapping(value = "/permission")
    public R permissionList() {
        List<PermissionTreeVO> list = roleService.getPermissionTreeVO();
        return R.ok().put("data", list);
    }
}
