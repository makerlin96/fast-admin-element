package com.starry.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.dto.UserDTO;
import com.starry.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity selectUserByUsername(@Param("username") String username);
    List<UserEntity> selectPageList(Page pages, @Param("params") UserDTO params);
}
