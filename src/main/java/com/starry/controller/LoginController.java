package com.starry.controller;

import cn.hutool.core.util.IdUtil;
import com.starry.entity.UserEntity;
import com.starry.service.UserService;
import com.starry.shiro.ShiroUtils;
import com.starry.utils.JwtUtils;
import com.starry.utils.R;
import com.starry.utils.RedisUtils;
import com.starry.vo.UserLoginVO;
import com.wf.captcha.SpecCaptcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@RestController
public class LoginController extends AbstractController {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @PostMapping(value = "/login")
   public R login(@RequestBody UserLoginVO userLoginVO) {
        String username = userLoginVO.getUsername();
        UserEntity user = userService.getUserByUsername(username);
        String captcha = (String) redisUtils.get("admin:captcha:" + userLoginVO.getUuid());
        // 判断验证码是否过期
        if (StringUtils.isEmpty(captcha))
            return R.error("验证码已经过期");
        // 判断验证码是否正确
        if (!userLoginVO.getCode().trim().equals(captcha))
            return R.error("验证码错误");
        // 判断用户是否存在
        if (user == null)
            return R.error("用户名不存在");
        // 判断账号是否禁用
        if (user.getStatus() == 1)
            return R.error("账号已被锁定,请联系管理员!");
        user.setLastLoginTime(new Date().getTime());
        userService.updateById(user);
        String token = jwtUtils.createToken(String.valueOf(user.getUserId()));
        redisUtils.set("admin:loginUser:" + user.getUserId(), token, jwtUtils.getExpire());
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        return R.ok().put("data", map);
    }

    @GetMapping(value = "/captcha/{timestamp}")
    public R captcha(@PathVariable("timestamp") String timestamp) {
        Date date = new Date();
        long time = Long.parseLong(timestamp);
        System.out.println(date.getTime() - time);
        if ((date.getTime() - time) > 6000) {
            return R.error("请求已过期");
        }
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        String code = specCaptcha.text().toLowerCase();
        String uuid = IdUtil.simpleUUID();
        redisUtils.set("admin:captcha:" + uuid, code, 120);
        HashMap<String, String> map = new HashMap<>(3);
        map.put("captcha", specCaptcha.toBase64());
        map.put("uuid", uuid);
        map.put("code", code);
        return R.ok().put("data", map);
    }

    @GetMapping(value = "/logout")
    public R logout() {
        ShiroUtils.logout();
        return R.ok();
    }

}
