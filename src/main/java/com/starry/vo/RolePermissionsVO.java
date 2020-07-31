package com.starry.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RolePermissionsVO implements Serializable {
    private static final long serialVersionUID = -3158264115885668061L;

    private Long roleId;

    private List<Long> permissions;
}
