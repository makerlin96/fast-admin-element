package com.starry.service;

import com.starry.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {
    List<Long> getRolePermissionByRoleId(Long roleId);
    int save(Long roleId, List<Long> menuId);
}
