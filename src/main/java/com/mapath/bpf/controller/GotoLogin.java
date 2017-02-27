package com.mapath.bpf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/2/24.
 * 测试跳转登录页面
 */
@Controller
public class GotoLogin {

    @RequestMapping(value = "gotologin")
    public String gotoLogin(){

//        return "forward:login";
//        return "redirect:login";
        return "login";
    }
}
