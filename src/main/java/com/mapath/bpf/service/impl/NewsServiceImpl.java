package com.mapath.bpf.service.impl;

import com.mapath.bpf.mapper.NewsMapper;
import com.mapath.bpf.model.KeyWordModel;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.PageNumber;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.utils.DateUtil;
import com.mapath.bpf.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsmapper;


    public PageNumber newslist(KeyWordModel keyword) {

        //页码的总数
        int pagetotal;
        //从前端传过来的页码数，将数据取出来
        int page=keyword.getPage();
        //页面的起始位置开始获取，并且将数据赋值给sql语句。
        keyword.setStart((page-1)*10);
        //记录的总录
        int total=newsmapper.count();

        if(total%10 == 0) {
            //总的页数
            pagetotal = total/10;
        } else{
            //总的页数+1
            pagetotal=(total/10)+1;
        }
        //每次只能拿到10 条数据
        List <NewsModel> newslist=newsmapper.findbyKeyword(keyword);
        PageNumber pagenumber=new PageNumber();
        pagenumber.setPagetotal(pagetotal);
        pagenumber.setList(newslist);
        return pagenumber;
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

