package com.lw.common.ema.otasocket.message;

import java.util.ArrayList;
import java.util.List;

import com.chinatricom.message.ICallback;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.message.ema.EmaChannel;
import com.chinatricom.message.otaserver.OTAConstants;
import com.chinatricom.slidewindow.SMSCallback;
import com.chinatricom.util.IDGenerator;
import com.chinatricom.util.SynMemIDGenerator;

/**
 * 大汉三通ctcpp短信通道
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-2-16 下午06:06:14
 */
public class OTAppChannel extends EmaChannel {

    private OTApp _mo = null;

    private OTApp _mt = null;

    private boolean bActive = false;

    private int msgfmt = OTAConstants.CMT_MFT_GBK;

    private int maxSinglemsg = 70;

    private int longSMSmaxSinglemsg = 67;

    protected IDGenerator idGen = new SynMemIDGenerator();

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
    public OTAppChannel(String host, int port, String user, String passwd, int wndSize, int timeout, int trys,
            int speed, int maxSinglemsg, int longSMSmaxSinglemsg, OTACallback msgCallback) {
        setMsgtype(1);
        setId(1);
        setCallback(msgCallback);
        setMsgfmt(msgfmt);
        setMaxSinglemsg(maxSinglemsg);
        setLongSMSmaxSinglemsg(longSMSmaxSinglemsg);
        _mt = new OTApp(host, port, user, passwd, 1, wndSize, timeout, trys, speed, (SMSCallback) msgCallback,
                OTAConstants.CONNTYPE_MT);
        _mt.subConnectionId = OTAConstants.CONNTYPE_MT;
        _mo = new OTApp(host, port, user, passwd, 1, wndSize, timeout, trys, speed, (SMSCallback) msgCallback,
                OTAConstants.CONNTYPE_MO);
        _mo.subConnectionId = OTAConstants.CONNTYPE_MO;
    }

    @Override
    public boolean Submit(ISubmitMsg arg0) {
        System.out.println("发送短信：" + arg0);
        List<SubmitMsg> submitMsgArray = splitLongMsg((SubmitMsg) arg0);
        for (SubmitMsg _submsg : submitMsgArray) {
            _mt.onSubmitSMS(_submsg);
        }
        return true;
    }

    /**
     * 不支持长短信的场合，分割长短信，如果支持，则返回同样的内容
     * 
     * @param inMsg
     * @return
     */
    private ArrayList<SubmitMsg> splitLongMsg(SubmitMsg inMsg) {
        ArrayList<SubmitMsg> _list = new ArrayList<SubmitMsg>();
        inMsg.setMsgSeqid(idGen.nextId());
        if (inMsg.getMsgContent().length() <= this.getMaxSinglemsg()) {
            inMsg.setCountOfLongSms(1);
            inMsg.setIdxOfLongSms(1);
            _list.add(inMsg);
        } else {
            int actualMaxMessageLen = this.getLongSMSmaxSinglemsg();
            int ncount = inMsg.getMsgContent().length() / (actualMaxMessageLen);
            if (inMsg.getMsgContent().length() % (actualMaxMessageLen) > 0)
                ncount++;
            System.out.println("将原始短信切割成" + ncount + "条：" + inMsg.getMsgContent());
            int _beginPos = 0;
            for (int i = 1; i <= ncount; i++) {
                SubmitMsg _submit = new SubmitMsg();
                _submit = (SubmitMsg) inMsg.clone();
                if (i == ncount)
                    _submit.setMsgContent(inMsg.getMsgContent().substring(_beginPos));
                else
                    _submit.setMsgContent(inMsg.getMsgContent().substring(_beginPos, _beginPos + actualMaxMessageLen));

                _beginPos += actualMaxMessageLen;
                _submit.setCountOfLongSms(ncount);
                _submit.setIdxOfLongSms(i);
                _list.add(_submit);
            }
        }
        return _list;
    }

    public boolean queryReport(String arg0) {
        return _mt.queryReport(arg0);
    }

    @Override
    public boolean isBusy() {
        return _mt.isConnected() && _mt.isBusy();
    }

    @Override
    public boolean isActive() {
        return bActive;
    }

    @Override
    public int getFreespace() {
        return _mt.getFreespace();
    }

    @Override
    public void setCallback(ICallback cb) {
    }

    @Override
    public int start() {
        int _nRet = 0;
        boolean _bStartMT = false;
        boolean _bStartMO = false;
        _bStartMT = (_mt.startSMS() == 0);
        if (_bStartMT) {
            _bStartMO = (_mo.startSMS() == 0);
            if (_bStartMO) {
                bActive = true;
                _nRet = 1;
            }
        }

        if (!bActive) {
            if (_bStartMT)
                _mt.stopSMS();
            if (_bStartMO)
                _mo.stopSMS();
        }
        return _nRet;
    }

    @Override
    public int stop() {
        bActive = false;
        _mt.stopSMS();
        _mo.stopSMS();
        return 1;
    }

    public void setHostIp(String inIp) {
        _mt.setHostIp(inIp);
        _mo.setHostIp(inIp);
    }

    public void setHostPort(int inPort) {
        _mt.setHostPort(inPort);
        _mo.setHostPort(inPort);
    }

    @Override
    public boolean SubmitWhiteList(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isConnected() {
        return _mt.isConnected();
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
     * @return maxSinglemsg : return the property maxSinglemsg.
     */
    public int getMaxSinglemsg() {
        return maxSinglemsg;
    }

    /**
     * @param maxSinglemsg
     *            : set the property maxSinglemsg.
     */
    public void setMaxSinglemsg(int maxSinglemsg) {
        this.maxSinglemsg = maxSinglemsg;
    }

    /**
     * @return longSMSmaxSinglemsg : return the property longSMSmaxSinglemsg.
     */
    public int getLongSMSmaxSinglemsg() {
        return longSMSmaxSinglemsg;
    }

    /**
     * @param longSMSmaxSinglemsg
     *            : set the property longSMSmaxSinglemsg.
     */
    public void setLongSMSmaxSinglemsg(int longSMSmaxSinglemsg) {
        this.longSMSmaxSinglemsg = longSMSmaxSinglemsg;
    }

}
