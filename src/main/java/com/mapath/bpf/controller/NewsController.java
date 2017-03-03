package com.mapath.bpf.controller;

import com.mapath.bpf.model.KeyWordModel;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.PageNumber;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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


    /*测试跳转到新闻主体页面(只作为跳转使用)*/

    @RequestMapping(value = "newsContent")
    public String newsContent(Model model,KeyWordModel keyword){
        //List<NewsModel> newsModels=newsService.newslist(keyword);
       // model.addAttribute("newsModels",newsModels);
        model.addAttribute("key",keyword.getKeyword());
        return "newscontent"; //新闻主体页面
    }

    /*ajax跳转删除数据*/
    @RequestMapping(value = "deletedata")
    @ResponseBody
    public NewsModel newsDeleteData(Model model,NewsModel newsModel){
        String id=newsModel.getId();
        NewsModel deletenews=newsService.newsfindById(id);  //通过id找到对应的newsModel对象
        newsService.newsDelete(deletenews);   //删除操作，逻辑删除
        return newsModel;
    }

    /*跳转到新闻编辑页面*/
    @RequestMapping(value = "newsEditor/{id}")
    public String newsEditor(@PathVariable String id, Model model){
        NewsModel newsModel=newsService.newsfindById(id); //通过id找到对应的newsModel对象
        model.addAttribute("title",newsModel.getTitle());
        model.addAttribute("comments",newsModel.getComments());
        return "newseditor";
    }


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
        key.setPage(1);
        PageNumber records = newsService.newslist(key);
        //将信息的条数以及分页的总页数输出
        logger.info((records == null)?"0条":records+ "条");
        //logger.info((records == null)?"0条":list.size() + "条");
        model.addAttribute("news", records);
        return "news";
    }
}


