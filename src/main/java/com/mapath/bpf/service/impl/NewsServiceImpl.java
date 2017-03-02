package com.mapath.bpf.service.impl;

import com.mapath.bpf.mapper.NewsMapper;
import com.mapath.bpf.model.KeyWordModel;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.utils.DateUtil;
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
    public List<NewsModel> newslist(KeyWordModel keyword) {
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
        if(newsModel.getId() != null && !"".equals(newsModel.getId())){
            newsmapper.update(newsModel);
        }else{
            newsModel.setId(UUID.uuid32());
            newsModel.setCreateDt(DateUtil.getSystemDateTime());
            newsModel.setClickNum(new Integer(0));
            newsmapper.save(newsModel);
        }
    }
    @Override
    /**
     * 将用户删除的信息也更新显示出来.
     */
    public void newsDelete(NewsModel newsModel) {
        newsModel.setIsdelete("1");
        newsmapper.update(newsModel);
    }
}
