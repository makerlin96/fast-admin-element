package com.starry.service.impl;

import com.starry.entity.UserRoleEntity;
import com.starry.dao.UserRoleMapper;
import com.starry.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

}
