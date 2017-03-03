package com.mapath.bpf.model;

/**
 * Created by yangyujun on 2017/3/2.
 */
public class KeyWordModel {

    private String keyword;
    //数据的起始
    private int start;
    //传过来的页码的编号
    private int page;

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getStart(){
        return start;
    }
    public void setStart(int start){
        this.start=start;
    }

    public int getPage(){
        return page;
    }
    public void setPage(int page){
        this.page=page;
    }


}
