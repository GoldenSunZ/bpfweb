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

        //从前端传过来的页码数，将数据取出来
        int page=keyword.getPage();
        //页面的起始位置开始获取，并且将数据赋值给sql语句。
        keyword.setStart((page-1)*10);
        //记录的总录
        int total=newsmapper.count();

        //总的页数
        int pagetotal=(total/10)+1;

        //每次只能拿到10 条数据
        List <NewsModel> newslist=newsmapper.findbyKeyword(keyword);
        pageNumber pagenumber=new pageNumber();
        pagenumber.setPagetotal(pagetotal);
        pagenumber.setList(newslist);
        return (List<NewsModel>) pagenumber;
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

/**
 * 用于封装提交页码的总数 ，以及数据
 */
class pageNumber{

    private List<NewsModel> list;
    private int pagetotal;

    public List<NewsModel> getList(){
        return list;
    }
    public void setList(List<NewsModel>  list){
        this.list=list;
    }
    public int getPagetotal(){
        return pagetotal;
    }
    public void setPagetotal(int pagetotal){
        this.pagetotal=pagetotal;
    }

}