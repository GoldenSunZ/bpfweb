package com.mapath.bpf.controller;

import com.mapath.bpf.model.KeyWordModel;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.AdminService;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.service.impl.NewsServiceImpl;
import com.mapath.bpf.utils.DateUtil;
import com.mapath.bpf.utils.UUID;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Controller
public class NewsController {

     private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;



    @RequestMapping(value = "newsList")
    public String newsList(){
        logger.info("enter news page");
        return "news";
    }

    @RequestMapping(value = "queryNews", method = RequestMethod.POST)
    public String queryNews(String keyword, Model model){
        logger.info("execute query");

        //测试keyWord模糊查询
        KeyWordModel key=new KeyWordModel();
        key.setKeyword(keyword);
        List<NewsModel> records = newsService.newslist(key);
        logger.info((records == null)?"0条":records.size() + "条");

        model.addAttribute("news", records);
        return "news";
    }
}

