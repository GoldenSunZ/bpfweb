package com.mapath.bpf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ulongx on 2017/2/21.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"index.html","/"})
    public String indexPage(){

        return "index";
    }
}
