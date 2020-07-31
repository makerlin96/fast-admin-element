package com.starry.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.dto.UserDTO;
import com.starry.dao.MenuMapper;
import com.starry.entity.UserEntity;
import com.starry.dao.UserMapper;
import com.starry.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.utils.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserEntity getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public Set<String> getUserPermissions(Long userId) {
        List<String> permsList = menuMapper.selectMenuByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perms :
                permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public PageUtils getPageList(UserDTO userDTO) {
        long page = userDTO.getPage();
        long limit = userDTO.getLimit();
        Page<UserEntity> pages = new Page<>(page, limit);
        List<UserEntity> list = userMapper.selectPageList(pages, userDTO);
        pages.setRecords(list);
        return new PageUtils(pages);
    }
}
