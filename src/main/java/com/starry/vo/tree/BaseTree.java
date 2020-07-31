package com.starry.vo.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BaseTree<T> implements Serializable {
    private static final long serialVersionUID = 2346869793011125884L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 子节点列表
     */
    private List<T> children = new ArrayList<>();
}
