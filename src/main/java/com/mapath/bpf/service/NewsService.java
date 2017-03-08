package com.mapath.bpf.service;

import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.NewsPage;
import com.mapath.bpf.model.PageNumber;
import com.mapath.util.pagination.model.DataGrid;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */
public interface NewsService {

    DataGrid<NewsModel> newslist(NewsPage newsPage);

    NewsModel newsfindById(String id);

    void newsSave(NewsModel newsModel);

    void newsDelete(NewsModel newsModel);

}
