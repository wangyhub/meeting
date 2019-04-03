/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.news;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.lw.common.persistence.DataEntity;

/**
 * 新闻动态Entity
 * @author meijx
 * @version 2019-03-19
 */
public class TbNews extends DataEntity<TbNews> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String title;		// 标题
	private Date newsTime;		// 新闻时间
	private String issuer;		// 发布人员
	private String content;		// 内容
	private String isTop;		// 是否置顶
	private String status;		// 状态
	private String type;		// 类型
	
	public TbNews() {
		super();
	}

	public TbNews(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=500, message="标题长度必须介于 0 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}
	
	@Length(min=0, max=100, message="发布人员长度必须介于 0 和 100 之间")
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	@Length(min=0, max=4000, message="内容长度必须介于 0 和 4000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2, message="是否置顶长度必须介于 0 和 2 之间")
	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}