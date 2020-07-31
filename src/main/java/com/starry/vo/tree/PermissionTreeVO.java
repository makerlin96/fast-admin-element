package com.starry.vo.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionTreeVO extends BaseTree<PermissionTreeVO> {

    private static final long serialVersionUID = 1829654279155328220L;

    private Long id;

    private Long parentId;

    private String name;

}
