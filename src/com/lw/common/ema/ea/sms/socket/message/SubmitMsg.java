package com.lw.common.ema.ea.sms.socket.message;

import com.chinatricom.message.Constants;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.message.ctcserverea.CTCEaSubmitSMSResp;

public class SubmitMsg implements ISubmitMsg {

    private int result = Constants.CMT_OK;

    private String msgId = "";

    private CTCEaSubmitSMSResp submitSMSResp;

    private String smsMtMessage = "";

    public SubmitMsg() {
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

    /**
     * @return smsMtMessage : return the property smsMtMessage.
     */
    public String getSmsMtMessage() {
        return smsMtMessage;
    }

    /**
     * @param smsMtMessage
     *            : set the property smsMtMessage.
     */
    public void setSmsMtMessage(String smsMtMessage) {
        this.smsMtMessage = smsMtMessage;
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
    public CTCEaSubmitSMSResp getSubmitSMSResp() {
        return submitSMSResp;
    }

    /**
     * @param submitSMSResp : set the property submitSMSResp.
     */
    public void setSubmitSMSResp(CTCEaSubmitSMSResp submitSMSResp) {
        this.submitSMSResp = submitSMSResp;
    }

}
