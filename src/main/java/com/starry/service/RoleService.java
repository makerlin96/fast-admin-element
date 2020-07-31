package com.starry.service;

import com.starry.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starry.entity.RoleMenuEntity;
import com.starry.utils.PageUtils;
import com.starry.vo.tree.PermissionTreeVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
public interface RoleService extends IService<RoleEntity> {
    Set<String> getUserRoles(Long userId);
    PageUtils getPage();
    List<PermissionTreeVO> getPermissionTreeVO();
}
