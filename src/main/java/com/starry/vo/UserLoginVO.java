package com.starry.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {
    private static final long serialVersionUID = -1401564984804883514L;

    private String username;

    private String password;

    private String uuid;

    private String code;
}
