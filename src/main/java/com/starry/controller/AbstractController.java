package com.starry.controller;

import com.starry.entity.UserEntity;
import com.starry.shiro.ShiroUtils;

public class AbstractController {
    protected UserEntity getUser() {
        return ShiroUtils.getUser();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
