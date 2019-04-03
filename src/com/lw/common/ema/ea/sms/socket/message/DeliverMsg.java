package com.lw.common.ema.ea.sms.socket.message;

import com.chinatricom.message.Constants;
import com.chinatricom.message.IDeliverMsg;

public class DeliverMsg implements IDeliverMsg {
    private String smsMoMessage = "";

    private String to = "";

    private String from = "";

    private String serviceId = "";

    private int type = Constants.CMT_TextSMS;

    private int msgfmt = Constants.CMT_MFT_GBK;

    private String msg = "";

    public DeliverMsg() {
    }

    public String getLogMsg() {
        return null;
    }

    public void setCorpId(String arg0) {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMsgfmt() {
        return msgfmt;
    }

    public void setMsgfmt(int msgfmt) {
        this.msgfmt = msgfmt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCountOfSms() {
        return 1;
    }

    public int getIdxOfSms() {
        return 1;
    }

    public void merge(IDeliverMsg inMsg) {

    }

    public void setChanId(int arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * @return smsMoMessage : return the property smsMoMessage.
     */
    public String getSmsMoMessage() {
        return smsMoMessage;
    }

    /**
     * @param smsMoMessage
     *            : set the property smsMoMessage.
     */
    public void setSmsMoMessage(String smsMoMessage) {
        this.smsMoMessage = smsMoMessage;
    }

}
