package com.mapath.bpf.model;

import com.mapath.util.pagination.model.PageInfo;

/**
 * Created by jinyang on 2017/3/7.
 */
public class NewsPage extends NewsModel {

    private String keyWord;
    private PageInfo pageInfo;

    public NewsPage() {
    }

    public NewsPage(String keyWord, PageInfo pageInfo) {
        this.keyWord = keyWord;
        this.pageInfo = pageInfo;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
