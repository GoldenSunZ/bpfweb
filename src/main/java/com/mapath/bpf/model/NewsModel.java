package com.mapath.bpf.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */
public class NewsModel implements Serializable {

    private String id;
    private String title;
    private String picture;
    private String comments;
    private String author;
    private Date   createDt;
    private Integer clickNum;
    private String isdelete;

    public NewsModel(){

    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }

    public String getComments() {
        return comments;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", comments='" + comments + '\'' +
                ", author='" + author + '\'' +
                ", createDt=" + createDt +
                ", clickNum=" + clickNum +
                ", isdelete='" + isdelete + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsModel newsModel = (NewsModel) o;

        if (!id.equals(newsModel.id)) return false;
        if (title != null ? !title.equals(newsModel.title) : newsModel.title != null) return false;
        if (picture != null ? !picture.equals(newsModel.picture) : newsModel.picture != null) return false;
        if (comments != null ? !comments.equals(newsModel.comments) : newsModel.comments != null) return false;
        if (author != null ? !author.equals(newsModel.author) : newsModel.author != null) return false;
        if (createDt != null ? !createDt.equals(newsModel.createDt) : newsModel.createDt != null) return false;
        if (clickNum != null ? !clickNum.equals(newsModel.clickNum) : newsModel.clickNum != null) return false;
        return !(isdelete != null ? !isdelete.equals(newsModel.isdelete) : newsModel.isdelete != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (createDt != null ? createDt.hashCode() : 0);
        result = 31 * result + (clickNum != null ? clickNum.hashCode() : 0);
        result = 31 * result + (isdelete != null ? isdelete.hashCode() : 0);
        return result;
    }


}
