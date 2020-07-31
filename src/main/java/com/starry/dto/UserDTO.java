package com.starry.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2868919913032027410L;
    private String username;
    private String realName;
    private Long startDate;
    private Long endDate;
    private Long page;
    private Long limit;
}
