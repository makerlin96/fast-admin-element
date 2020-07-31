package com.starry.service;

import com.starry.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starry.vo.tree.MenuTreeVO;
import com.starry.vo.tree.RouterVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
public interface MenuService extends IService<MenuEntity> {
    Set<String> getUserButtonList(Long userId);
    List<MenuEntity> getMenuTreeByUserId(Long userId);
    List<RouterVO> buildMenus(List<MenuEntity> menus);
    List<MenuTreeVO> getListTree();
}
