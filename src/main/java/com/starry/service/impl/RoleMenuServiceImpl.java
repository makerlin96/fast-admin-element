package com.starry.service.impl;

import com.starry.entity.RoleMenuEntity;
import com.starry.dao.RoleMenuMapper;
import com.starry.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.vo.RolePermissionsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Override
    public int save(Long roleId, List<Long> menuId) {
        StringBuilder menuIds = new StringBuilder();
        for (Long id :
                menuId) {
            if (menuId.indexOf(id) == menuId.size() - 1) {
                menuIds.append(id);
            } else {
                menuIds.append(id).append(",");
            }
        }
        return roleMenuMapper.insertOrUpdate(roleId, menuIds.toString());
    }

    @Override
    public List<Long> getRolePermissionByRoleId(Long roleId) {
        RoleMenuEntity roleMenuEntity = baseMapper.selectById(roleId);
        if (roleMenuEntity == null) {
            return null;
        }
        RolePermissionsVO rolePermissionsVO = new RolePermissionsVO();
        rolePermissionsVO.setRoleId(roleMenuEntity.getRoleId());
        String menuList = roleMenuEntity.getMenuId();
        List<Long> permissionList= new ArrayList<>();
        if (!"".equals(menuList)) {
            String[] strings = menuList.split(",");
            for (String str :
                    strings) {
                permissionList.add(Long.parseLong(str));
            }
        }
        return permissionList;
    }
}
