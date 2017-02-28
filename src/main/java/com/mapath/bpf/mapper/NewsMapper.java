package com.mapath.bpf.mapper;

import com.mapath.bpf.model.NewsModel;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */
public interface NewsMapper {
    /**
     * 将标题或者内容的信息查询
     * @return List<NewsModel>
     */
    List<NewsModel> findbyKeyword (String keyword);

    /**
     * 根据id查询
     * @param id
     * @return NewsModel
    */
    NewsModel findById(String id);

    /**
     *
     * @param newsModel
     */
    void save(NewsModel newsModel);

    /**
     *
     * @param newsModel
     */
    void update(NewsModel newsModel);
}
