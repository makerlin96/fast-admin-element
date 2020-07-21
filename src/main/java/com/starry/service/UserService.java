package com.starry.service;

import com.starry.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
public interface UserService extends IService<UserEntity> {
    UserEntity getUserByUsername(String username);
}
