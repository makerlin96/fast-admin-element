package com.starry.service.impl;

import com.starry.entity.MenuEntity;
import com.starry.dao.MenuMapper;
import com.starry.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
