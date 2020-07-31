package com.starry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.dao.MenuMapper;
import com.starry.dao.RoleMenuMapper;
import com.starry.dao.UserMapper;
import com.starry.entity.RoleEntity;
import com.starry.dao.RoleMapper;
import com.starry.entity.RoleMenuEntity;
import com.starry.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.utils.MenuTreeUtils;
import com.starry.utils.PageUtils;
import com.starry.vo.tree.PermissionTreeVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Set<String> getUserRoles(Long userId) {
        List<String> roleList = roleMapper.selectUserRoles(userId);
        Set<String> roleSet = new HashSet<>();
        for (String role :
                roleList) {
            if (StringUtils.isBlank(role)) {
                continue;
            }
            roleSet.addAll(Arrays.asList(role.trim().split(",")));
        }
        return roleSet;
    }

    @Override
    public PageUtils getPage() {
        long page = 1L;
        long limit = 1000;
        Page<RoleEntity> pages = new Page<>(page, limit);
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        IPage<RoleEntity> pageList = roleMapper.selectPage(pages, wrapper);
        return new PageUtils(pageList);
    }



    @Override
    public List<PermissionTreeVO> getPermissionTreeVO() {
        List<PermissionTreeVO> list = menuMapper.selectPermissionTree();
        return MenuTreeUtils.build(list, 0L);
    }
}
