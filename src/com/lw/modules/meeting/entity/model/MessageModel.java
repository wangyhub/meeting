package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

/**
 * @program: meeting
 * @Date: 2019/3/14 10:24
 * @Author: wangy
 * @Description: 手机端显示消息列表的对象
 */
public class MessageModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String messageId;   //消息ID
    private String messageTime;   //消息成接收时间
    private String status;      //状态
    private String kind;        //消息类型
    private String type;        //消息种类
    private String content;     //消息内容
    private String sendTime;    //发送时间

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
