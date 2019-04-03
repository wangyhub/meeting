package com.lw.common.ema.ea.sms.socket.message;

import com.chinatricom.message.Constants;
import com.chinatricom.message.IReportMsg;

public class ReportMsg implements IReportMsg {
    private String smsReportMessage;

    private int result = Constants.CMT_OK;

    private String msgid;

    private int err;

    private String errDesc;

    private String phone;

    private int chanId = 0;

    public ReportMsg() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogMsg() {
        return null;
    }

    public void setCorpId(String arg0) {
    }

    public void setChanId(int arg0) {
        this.chanId = arg0;
    }

    public int getChanId() {
        return chanId;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    /**
     * @return smsReportMessage : return the property smsReportMessage.
     */
    public String getSmsReportMessage() {
        return smsReportMessage;
    }

    /**
     * @param smsReportMessage
     *            : set the property smsReportMessage.
     */
    public void setSmsReportMessage(String smsReportMessage) {
        this.smsReportMessage = smsReportMessage;
    }

}
