package com.lw.common.ema.ea.sms.socket;

import com.chinatricom.message.ICallback;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.message.ctcserverea.CTCEaConstants;
import com.chinatricom.message.ema.EmaChannel;
import com.chinatricom.slidewindow.SMSCallback;
import com.lw.common.ema.ea.encry.des3.Encry_DES3CLt;
import com.lw.common.ema.ea.sms.socket.message.EaCtcpp;
import com.lw.common.ema.ea.sms.socket.message.SubmitMsg;

/**
 * 大汉三通ctcpp短信通道
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-2-16 下午06:06:14
 */
public class EaCtcppChannel extends EmaChannel {

    private EaCtcpp ctcpp_mo = null;

    private EaCtcpp ctcpp_mt = null;

    private boolean bActive = false;

    private byte[] key = null;
    
    private EAMSCallback msgCallback = null;

    private int msgfmt = CTCEaConstants.CMT_MFT_GBK;

    /**
     * 类的构造方法 初始化通道参数 CtcppChannel.
     * 
     * @param
     * @param host
     * @param port
     * @param user
     * @param passwd
     * @param ea
     * @param wndSize
     * @param timeout
     * @param trys
     * @param speed
     * @param msgCallback
     */
    public EaCtcppChannel(String host, int port, String user, String passwd, int ea, int wndSize, int timeout,
            int trys, int speed, EAMSCallback msgCallback) {
        setMsgtype(1);
        setId(1);
        setCallback(msgCallback);
        setMsgfmt(msgfmt);
        ctcpp_mt = new EaCtcpp(host, port, user, passwd, ea, 1, wndSize, timeout, trys, speed,
                (SMSCallback) msgCallback, CTCEaConstants.CONNTYPE_MT);
        ctcpp_mt.subConnectionId = com.chinatricom.message.Constants.CONNTYPE_MT;
        ctcpp_mt.addResendResultCode(com.chinatricom.common.ERRORS.CTCSERVER_SEND_SPEED_TOO_FAST);
        ctcpp_mt.addResendResultCode(com.chinatricom.common.ERRORS.SLIDEWINDOW_SUBMIT_TIMEOUT);
        ctcpp_mt.addResendResultCode(CTCEaConstants.CMT_SERVERBUSY);
        ctcpp_mo = new EaCtcpp(host, port, user, passwd, ea, 1, wndSize, timeout, trys, speed,
                (SMSCallback) msgCallback, com.chinatricom.message.Constants.CONNTYPE_MO);
        ctcpp_mo.subConnectionId = com.chinatricom.message.Constants.CONNTYPE_MO;
    }

    @Override
    public boolean Submit(ISubmitMsg arg0) {
        SubmitMsg submitMsg = (SubmitMsg) arg0;
        System.out.println("发送短信："+submitMsg.getSmsMtMessage());
        if (this.key != null) {
            submitMsg.setSmsMtMessage(Encry_DES3CLt.getInstance().getEncString(submitMsg.getSmsMtMessage(), key));
        }
        return ctcpp_mt.onSubmitSMS(submitMsg);
    }

    @Override
    public boolean isBusy() {
        return ctcpp_mt.isConnected() && ctcpp_mt.isBusy();
    }

    @Override
    public boolean isActive() {
        return bActive;
    }

    @Override
    public int getFreespace() {
        return ctcpp_mt.getFreespace();
    }

    @Override
    public void setCallback(ICallback cb) {
        msgCallback =(EAMSCallback)cb;
    }

    @Override
    public int start() {
        int _nRet = 0;
        boolean _bStartMT = false;
        boolean _bStartMO = false;
        if (ctcpp_mt.getEa() != 0) {
            ctcpp_mt.setChannel(this);
        }
        _bStartMT = (ctcpp_mt.startSMS() == 0);
        if (_bStartMT) {
            _bStartMO = (ctcpp_mo.startSMS() == 0);
            if (_bStartMO) {
                bActive = true;
                _nRet = 1;
            }
        }

        if (!bActive) {
            if (_bStartMT)
                ctcpp_mt.stopSMS();
            if (_bStartMO)
                ctcpp_mo.stopSMS();
        }
        return _nRet;
    }

    @Override
    public int stop() {
        bActive = false;
        ctcpp_mt.stopSMS();
        ctcpp_mo.stopSMS();
        return 1;
    }

    public void setHostIp(String inIp) {
        ctcpp_mt.setHostIp(inIp);
        ctcpp_mo.setHostIp(inIp);
    }

    public void setHostPort(int inPort) {
        ctcpp_mt.setHostPort(inPort);
        ctcpp_mo.setHostPort(inPort);
    }

    @Override
    public boolean SubmitWhiteList(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isConnected() {
        return ctcpp_mt.isConnected();
    }

    /**
     * @return the msgfmt
     */
    public int getMsgfmt() {
        return msgfmt;
    }

    /**
     * @param msgfmt
     *            the msgfmt to set
     */
    public void setMsgfmt(int msgfmt) {
        this.msgfmt = msgfmt;
    }

    /**
     * @return ctcpp_mo : return the property ctcpp_mo.
     */
    public EaCtcpp getCtcpp_mo() {
        return ctcpp_mo;
    }

    /**
     * @return ctcpp_mt : return the property ctcpp_mt.
     */
    public EaCtcpp getCtcpp_mt() {
        return ctcpp_mt;
    }

    /**
     * @return key : return the property key.
     */
    public byte[] getKey() {
        return key;
    }

    /**
     * @param key
     *            : set the property key.
     */
    public void setKey(byte[] key) {
        msgCallback.setKey(key);
        this.key = key;
    }

}
