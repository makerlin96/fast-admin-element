package com.starry.vo.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTreeVO extends BaseTree<MenuTreeVO> implements Serializable {
    private static final long serialVersionUID = 7476131120564126573L;

    private Long id;

    private Long parentId;

    private String name;

    private String path;

    private Integer menuType;

    private String perms;

    private Integer status;

    private String icon;

    private Integer sort;

    private Integer isFrame;

    private String component;

    private String hidden;
}
