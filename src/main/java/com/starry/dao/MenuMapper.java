package com.starry.dao;

import com.starry.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starry.vo.tree.MenuTreeVO;
import com.starry.vo.tree.PermissionTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
    Set<String> selectUserButtonList(Long userId);
    List<MenuEntity> selectMenuTreeByUserId(Long userId);
    List<String> selectMenuByUserId(Long userId);
    List<MenuTreeVO> selectList();
    List<PermissionTreeVO> selectPermissionTree();
}
