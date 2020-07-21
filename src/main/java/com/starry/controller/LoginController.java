package com.starry.controller;

import cn.hutool.core.util.IdUtil;
import com.starry.utils.R;
import com.wf.captcha.SpecCaptcha;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
public class LoginController extends AbstractController {

    @PostMapping(value = "/login")
    public R login() {
        return R.ok();
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
        HashMap<String, String> map = new HashMap<>(3);
        map.put("captcha", specCaptcha.toBase64());
        map.put("uuid", uuid);
        map.put("code", code);
        return R.ok().put("data", map);
    }

}
