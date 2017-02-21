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

    @RequestMapping(value = {"applications.html"})
    public String applicationsPage(){

        return "applications";
    }

    @RequestMapping(value = {"vision.html"})
    public String visionPage(){

        return "vision";
    }

    @RequestMapping(value = {"expertise.html"})
    public String expertisePage(){

        return "expertise";
    }

    @RequestMapping(value = {"transparency.html"})
    public String transparencyPage(){

        return "transparency";
    }

    @RequestMapping(value = {"news.html"})
    public String newsPage(){

        return "news";
    }

    @RequestMapping(value = {"contact.html"})
    public String contactPage(){

        return "contact";
    }
}
