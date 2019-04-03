package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

public class TbNewsContent implements Serializable {

    private String newsId;  //新闻Id

    private Date newsTime;  //新闻时间

    private String content;  //新闻内容

    private String title;   //标题issuer

    private String issuer;   //发布人员

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
