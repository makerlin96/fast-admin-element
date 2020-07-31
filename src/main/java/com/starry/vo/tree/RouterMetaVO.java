package com.starry.vo.tree;

import lombok.Data;

@Data
public class RouterMetaVO {
    private String title;
    private String icon;

    public RouterMetaVO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public RouterMetaVO(String title) {
        this.title = title;
    }
}
