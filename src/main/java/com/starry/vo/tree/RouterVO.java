package com.starry.vo.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVO {
    private String name;
    private String path;
    private String hidden;
    private String redirect;
    private String component;
    private Boolean alwaysShow;
    private RouterMetaVO meta;
    private List<RouterVO> children;
}
