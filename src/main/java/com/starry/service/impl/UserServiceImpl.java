package com.starry.service.impl;

import com.starry.entity.UserEntity;
import com.starry.dao.UserMapper;
import com.starry.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
