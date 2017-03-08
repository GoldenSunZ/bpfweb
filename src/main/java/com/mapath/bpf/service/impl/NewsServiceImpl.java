package com.mapath.bpf.service.impl;

import com.mapath.bpf.mapper.NewsMapper;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.NewsPage;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.utils.DateUtil;
import com.mapath.bpf.utils.UUID;
import com.mapath.util.pagination.model.DataGrid;
import com.mapath.util.pagination.model.PageResult;
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

    @Override
    public DataGrid<NewsModel> newslist(NewsPage newsPage){
        List<NewsModel> newsModels = newsmapper.findbyKeyword(newsPage);
        DataGrid<NewsModel> dataGrid = new DataGrid(newsModels,newsPage);
        return dataGrid;
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
            newsModel.setIsdelete("0");
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

