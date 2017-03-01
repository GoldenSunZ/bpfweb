package com.mapath.bpf.controller;

import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.service.impl.NewsServiceImpl;
import com.mapath.bpf.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Controller()
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value={"findByKeyWord"})
    public List<NewsModel> newFindByKeyWord(String keyword){
        List<NewsModel> list=newsService.newslist(keyword);
        news newsfind=new news();
        newsfind.setList(list);
        return (List<NewsModel>) newsfind;
    }
    @RequestMapping(value={"findById"})
    public NewsModel newFindById(String id){
        NewsModel newsmodel= newsService.newsfindById(id);
        return newsmodel;
    }
    @RequestMapping(value={"save"})
    public void newSave(NewsModel newsModel){
        newsService.newsSave(newsModel);
    }
    @RequestMapping(value={"update"})
    public void newUpdate(NewsModel newsModel){
        newsService.newsSave(newsModel);
    }
    @RequestMapping(value={"delete"})
    public void newDelete(NewsModel newsModel){
        newsService.newsDelete(newsModel);
    }

    class news {
        private List<NewsModel> list;

        public List<NewsModel> getList() {
            return list;
        }

        public void setList(List<NewsModel> list) {
            this.list = list;
        }
    }
}
