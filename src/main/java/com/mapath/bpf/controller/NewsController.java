package com.mapath.bpf.controller;

import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.NewsPage;
import com.mapath.bpf.service.NewsService;
import com.mapath.util.pagination.model.DataGrid;
import com.mapath.util.pagination.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Controller
public class NewsController {

     private Logger logger = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    private NewsService newsService;

    /*新闻主体页面*/
    @RequestMapping("newsContent")
    public String getPageData(NewsPage newsPage, Model model, HttpSession session){
        String user=(String) session.getAttribute("user");
        if (StringUtils.isEmpty(user)){
            return "login";
        }
        if(newsPage.getPageInfo() == null){
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(1);
            newsPage.setPageInfo(pageInfo);
        }
        DataGrid<NewsModel> dataGrid = newsService.newslist(newsPage);
        model.addAttribute("result",dataGrid);
        model.addAttribute("keyWord",newsPage.getKeyWord());
        return "newscontent";  //新闻主体页面
    }

    /*保存编辑页面*/
    @RequestMapping(value = "saveNewseditor", method = RequestMethod.POST)
    public String saveNews(NewsPage newsPage,Model model){
        if(newsPage.getPageInfo() == null){
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(1);
            newsPage.setPageInfo(pageInfo);
        }
        NewsModel newsModel=newsService.newsfindById(newsPage.getId()); //通过id查询出newModel对象
        newsModel.setTitle(newsPage.getTitle());
        newsModel.setComments(newsPage.getComments());
        newsService.newsSave(newsModel);
        DataGrid<NewsModel> dataGrid = newsService.newslist(newsPage);
        model.addAttribute("result",dataGrid);
        model.addAttribute("keyWord",newsPage.getKeyWord());
        return "newscontent";
    }

    /*保存新增的新闻*/
    @RequestMapping(value = "saveNewsMessages")
    public String saveNewsMessages(NewsPage newsPage,Model model){
        if(newsPage.getPageInfo() == null){
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(1);
            newsPage.setPageInfo(pageInfo);
        }
        NewsModel newsModel=new NewsModel();
        newsModel.setTitle(newsPage.getTitle());
        newsModel.setComments(newsPage.getComments());
        newsService.newsSave(newsModel);
        DataGrid<NewsModel> dataGrid = newsService.newslist(newsPage);
        model.addAttribute("result",dataGrid);
        model.addAttribute("keyWord",newsPage.getKeyWord());
        return "newscontent";
    }

    /*新增新闻，跳转controller*/
    @RequestMapping(value = "newsMessages")
    public String newMessages(){

        return "newnews";
    }

    /*ajax跳转删除数据*/
    @RequestMapping(value = "deletedata")
    @ResponseBody
    public NewsModel newsDeleteData(Model model,NewsModel newsModel){
        String id=newsModel.getId();
        NewsModel deletenews=newsService.newsfindById(id);  //通过id找到对应的newsModel对象
        newsService.newsDelete(deletenews);   //删除操作，逻辑删除
        return deletenews;
    }

    /*跳转到新闻编辑页面*/
    @RequestMapping(value = "newsEditor/{id}")
    public String newsEditor(@PathVariable String id, Model model){
        NewsModel newsModel=newsService.newsfindById(id); //通过id找到对应的newsModel对象
        model.addAttribute("title",newsModel.getTitle());
        model.addAttribute("comments",newsModel.getComments());
        model.addAttribute("id",id);
        return "newseditor";
    }


    @RequestMapping(value = "newsList")
    public String newsList(){
        logger.info("enter news page");
        return "news";
    }

}


