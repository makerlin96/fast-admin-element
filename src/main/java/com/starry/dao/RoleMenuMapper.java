package com.starry.dao;

import com.starry.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {
    int insertOrUpdate(@Param("roleId") Long roleId, @Param("menuId") String menuId);
}
