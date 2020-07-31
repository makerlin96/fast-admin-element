package com.starry.service.impl;

import com.starry.entity.MenuEntity;
import com.starry.dao.MenuMapper;
import com.starry.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.utils.MenuTreeUtils;
import com.starry.vo.tree.MenuTreeVO;
import com.starry.vo.tree.RouterMetaVO;
import com.starry.vo.tree.RouterVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author Lewis
 * @since 2020-07-20
 */
@Service(value = "menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Set<String> getUserButtonList(Long userId) {
        return menuMapper.selectUserButtonList(userId);
    }

    @Override
    public List<MenuEntity> getMenuTreeByUserId(Long userId) {
        List<MenuEntity> menus = menuMapper.selectMenuTreeByUserId(userId);
        return getChildPerms(menus, 0);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<MenuEntity> getChildPerms(List<MenuEntity> list, int parentId) {
        List<MenuEntity> returnList = new ArrayList<MenuEntity>();
        for (MenuEntity t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<MenuEntity> list, MenuEntity t) {
        // 得到子节点列表
        List<MenuEntity> childList = getChildList(list, t);
        t.setChildren(childList);
        for (MenuEntity tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                for (MenuEntity n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MenuEntity> list, MenuEntity t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private List<MenuEntity> getChildList(List<MenuEntity> list, MenuEntity t) {
        List<MenuEntity> subList = new ArrayList<MenuEntity>();
        for (MenuEntity n : list) {
            if (n.getParentId().longValue() == t.getId().longValue()) {
                subList.add(n);
            }
        }
        return subList;
    }

    @Override
    public List<MenuTreeVO> getListTree() {
        List<MenuTreeVO> list = menuMapper.selectList();
        return MenuTreeUtils.build(list, 0L);
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVO> buildMenus(List<MenuEntity> menus) {
        List<RouterVO> routers = new LinkedList<>();
        for (MenuEntity menu : menus) {
            RouterVO router = new RouterVO();
            router.setName(StringUtils.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new RouterMetaVO(menu.getName(), menu.getIcon()));
            List<MenuEntity> cMenus = menu.getChildren();
            if (cMenus != null && !cMenus.isEmpty() && menu.getMenuType() == 1) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(MenuEntity menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if (0 == menu.getParentId() && 0 == menu.getIsFrame()) {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }

}
