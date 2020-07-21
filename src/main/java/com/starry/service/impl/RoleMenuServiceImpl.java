package com.starry.service.impl;

import com.starry.entity.RoleMenuEntity;
import com.starry.dao.RoleMenuMapper;
import com.starry.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
