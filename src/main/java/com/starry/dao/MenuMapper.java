package com.starry.dao;

import com.starry.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<MenuEntity> {

}
