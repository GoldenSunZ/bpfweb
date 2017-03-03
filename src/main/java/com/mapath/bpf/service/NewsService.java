package com.mapath.bpf.service;

import com.mapath.bpf.model.KeyWordModel;
import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.PageNumber;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */
public interface NewsService {

    PageNumber newslist(KeyWordModel keyword);

    NewsModel newsfindById(String id);

    void newsSave(NewsModel newsModel);

    void newsDelete(NewsModel newsModel);

}
