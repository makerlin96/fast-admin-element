package com.starry.controller;

import com.starry.entity.MenuEntity;
import com.starry.service.MenuService;
import com.starry.utils.R;
import com.starry.vo.tree.MenuTreeVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sys/menu")
public class MenuController extends AbstractController{
    @Autowired
    MenuService menuService;

    @GetMapping
    @RequiresPermissions("sys:menu:list")
    public R list() {
        List<MenuTreeVO> list = menuService.getListTree();
        return R.ok().put("data", list);
    }
    @PostMapping
    @RequiresPermissions("sys:menu:add")
    public R add(@RequestBody MenuEntity entity) {
        menuService.save(entity);
        return R.ok("添加成功");
    }

    @DeleteMapping
    @RequiresPermissions("sys:menu:del")
    public R del(@RequestBody String[] ids) {
        for (String id :
                ids) {
            menuService.removeById(Long.parseLong(id));
        }
        return R.ok().put("data", ids);
    }

    @PutMapping
    @RequiresPermissions("sys:menu:edit")
    public R edit(@RequestBody MenuEntity entity) {
        menuService.updateById(entity);
        return R.ok("编辑成功");
    }

    @GetMapping(value = "/info/{id}")
    public R info(@PathVariable Long id) {
        try {
            MenuEntity entity = menuService.getById(id);
            if (entity == null) {
                return R.error("参数有误！");
            } else {
                return R.ok().put("data", entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
