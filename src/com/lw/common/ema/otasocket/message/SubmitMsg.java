package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.message.otaserver.OTASubmitSMSResp;

public class SubmitMsg implements ISubmitMsg , Cloneable {

    private int result;

    private String msgId = "";

    private OTASubmitSMSResp submitSMSResp;

    /**
     * 是否要求返回状态报告
     */
    private int needReport;

    /**
     * 短消息发送优先级
     */
    private int priority;

    /**
     * 业务代码
     */
    private String serviceId;

    /**
     * 短消息格式
     */
    private int msgFormat;

    /**
     * 短消息有效时间
     */
    private String validTime;

    /**
     * 短消息定时发送时间
     */
    private String atTime;

    /**
     * 短信息发送方号码
     */
    private String srcTermID;

    /**
     * 短消息接收号码总数
     */
    private int destTermIDCount;

    /**
     * 短消息接收号码
     */
    private String destTermID;


    /**
     * 短消息内容
     */
    private String msgContent;
    
    private int countOfLongSms;

    private int idxOfLongSms;

    private int msgSeqid;

    public SubmitMsg() {
    }

    @Override
    public Object clone() {
        SubmitMsg o = null;
        try {
            o = (SubmitMsg) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
    public void setMsgId(String arg0) {
        this.msgId = arg0;
    }

    public void setResult(int arg0) {
        this.result = arg0;
    }

    public int getResult() {
        return result;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getLogMsg() {
        return null;
    }

    public void setCorpId(String arg0) {

    }

    public int getNumberOfPhone() {

        int _nRet = 0;
        return _nRet;
    }

    public void setSuccPhones(String inPhones) {
        // TODO Auto-generated method stub

    }

    public void setChanId(int inId) {
        // TODO Auto-generated method stub

    }

    /**
     * @return submitSMSResp : return the property submitSMSResp.
     */
    public OTASubmitSMSResp getSubmitSMSResp() {
        return submitSMSResp;
    }

    /**
     * @param submitSMSResp
     *            : set the property submitSMSResp.
     */
    public void setSubmitSMSResp(OTASubmitSMSResp submitSMSResp) {
        this.submitSMSResp = submitSMSResp;
    }

    /**
     * @return serviceId : return the property serviceId.
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId
     *            : set the property serviceId.
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return needReport : return the property needReport.
     */
    public int getNeedReport() {
        return needReport;
    }

    /**
     * @param needReport
     *            : set the property needReport.
     */
    public void setNeedReport(int needReport) {
        this.needReport = needReport;
    }

    /**
     * @return priority : return the property priority.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            : set the property priority.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return msgFormat : return the property msgFormat.
     */
    public int getMsgFormat() {
        return msgFormat;
    }

    /**
     * @param msgFormat
     *            : set the property msgFormat.
     */
    public void setMsgFormat(int msgFormat) {
        this.msgFormat = msgFormat;
    }

    /**
     * @return validTime : return the property validTime.
     */
    public String getValidTime() {
        return validTime;
    }

    /**
     * @param validTime
     *            : set the property validTime.
     */
    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    /**
     * @return atTime : return the property atTime.
     */
    public String getAtTime() {
        return atTime;
    }

    /**
     * @param atTime
     *            : set the property atTime.
     */
    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    /**
     * @return srcTermID : return the property srcTermID.
     */
    public String getSrcTermID() {
        return srcTermID;
    }

    /**
     * @param srcTermID
     *            : set the property srcTermID.
     */
    public void setSrcTermID(String srcTermID) {
        this.srcTermID = srcTermID;
    }

    /**
     * @return destTermIDCount : return the property destTermIDCount.
     */
    public int getDestTermIDCount() {
        return destTermIDCount;
    }

    /**
     * @param destTermIDCount
     *            : set the property destTermIDCount.
     */
    public void setDestTermIDCount(int destTermIDCount) {
        this.destTermIDCount = destTermIDCount;
    }

    /**
     * @return destTermID : return the property destTermID.
     */
    public String getDestTermID() {
        return destTermID;
    }

    /**
     * @param destTermID
     *            : set the property destTermID.
     */
    public void setDestTermID(String destTermID) {
        this.destTermID = destTermID;
    }

    /**
     * @return msgContent : return the property msgContent.
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent
     *            : set the property msgContent.
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return countOfLongSms : return the property countOfLongSms.
     */
    public int getCountOfLongSms() {
        return countOfLongSms;
    }

    /**
     * @param countOfLongSms : set the property countOfLongSms.
     */
    public void setCountOfLongSms(int countOfLongSms) {
        this.countOfLongSms = countOfLongSms;
    }

    /**
     * @return idxOfLongSms : return the property idxOfLongSms.
     */
    public int getIdxOfLongSms() {
        return idxOfLongSms;
    }

    /**
     * @param idxOfLongSms : set the property idxOfLongSms.
     */
    public void setIdxOfLongSms(int idxOfLongSms) {
        this.idxOfLongSms = idxOfLongSms;
    }

    /**
     * @return msgSeqid : return the property msgSeqid.
     */
    public int getMsgSeqid() {
        return msgSeqid;
    }

    /**
     * @param msgSeqid : set the property msgSeqid.
     */
    public void setMsgSeqid(int msgSeqid) {
        this.msgSeqid = msgSeqid;
    }

}
