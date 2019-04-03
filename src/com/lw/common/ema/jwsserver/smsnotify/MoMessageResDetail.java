package com.lw.common.ema.jwsserver.smsnotify;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 类描述：保存接口短信参数
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-16 上午10:47:44
 */
@XmlRootElement(name = "com.ctc.ema.jwsserver.notify.sms.MoMessageResDetail")
public class MoMessageResDetail {

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 短信内容
     */
    private String content;

    /**
     *扩展号
     */
    private String subCode;

    /**
     *接收时间
     */
    private Date revTime;

    /**
     * 保留
     */
    private String demo;

    /**
     * @return phoneNumber : return the property phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber : set the property phoneNumber.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return content : return the property content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content : set the property content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return subCode : return the property subCode.
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * @param subCode : set the property subCode.
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    /**
     * @return revTime : return the property revTime.
     */
    public Date getRevTime() {
        return revTime;
    }

    /**
     * @param revTime : set the property revTime.
     */
    public void setRevTime(Date revTime) {
        this.revTime = revTime;
    }

    /**
     * @return demo : return the property demo.
     */
    public String getDemo() {
        return demo;
    }

    /**
     * @param demo : set the property demo.
     */
    public void setDemo(String demo) {
        this.demo = demo;
    }

   
}
