package com.mapath.bpf.model;

import java.util.List;


/**
 * 用于封装提交页码的总数 ，以及数据
 */
public class PageNumber {

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
