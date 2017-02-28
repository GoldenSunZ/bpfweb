package com.mapath.bpf.service.impl;

import com.mapath.bpf.mapper.NewsMapper;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsmapper;

    @Override
    public List<NewsModel> newslist(String keyword) {
        List <NewsModel> newslist=newsmapper.findbyKeyword(keyword);
        return newslist;
    }

    @Override
    public NewsModel newsfindById(String id) {
        NewsModel newsmodel=newsmapper.findById(id);
        return newsmodel;
    }

    @Override
    public void newsSave(NewsModel newsModel) {
        newsmapper.save(newsModel);
    }

    @Override
    public void newsUpdate(NewsModel newsModel) {
        NewsModel news = new NewsModel();
        newsmapper.update(newsModel);
    }

    @Override
    /**
     * 将用户删除的信息也更新显示出来,
     */
    public void newsDelete(NewsModel newsModel) {
        newsmapper.update(newsModel);
    }
}
