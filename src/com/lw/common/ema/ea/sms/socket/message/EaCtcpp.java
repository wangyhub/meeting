package com.lw.common.ema.ea.sms.socket.message;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.CommonSMS;
import com.chinatricom.slidewindow.IDeliverPackage;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISMSPackage;
import com.chinatricom.slidewindow.ISubmitRespPackage;
import com.chinatricom.slidewindow.SMSCallback;
import com.chinatricom.slidewindow.WND_DATA;
import com.lw.common.ema.ea.sms.socket.EaCtcppChannel;

public class EaCtcpp extends CommonSMS {

    private static final Logger log = Logger.getLogger(EaCtcpp.class);

    private int ea = 0;
    private EaCtcppChannel channel = null;
    public EaCtcpp(String host, int port, String user, String passwd, int ea, int connId, int wndSize, int timeout,
            int trys, int speed, SMSCallback msgCallback, int connMode) {
        super(host, port, user, passwd, "", "", "", connMode, connId, wndSize, timeout, trys, 0, speed, msgCallback);
        this.ea = ea;
    }

    @Override
    protected ISMSPackage createActiveTestPackage() {
        EaActiveTest pack = new EaActiveTest();
        return pack;
    }

    @Override
    protected IRespPackage createActiveTestRespPackage() {
        EaActiveTestResp resp = new EaActiveTestResp();
        return resp;
    }

    @Override
    protected IDeliverPackage createDeliverPackage() {
        EaDeliver pack = new EaDeliver();
        return pack;
    }

    @Override
    protected IRespPackage createDeliverRespPackage(IDeliverPackage smsPackage) {
        EaDeliverResp resp = new EaDeliverResp();
        return resp;
    }

    @Override
    protected ISMSPackage createLoginPackage() {
        EaLogin pack = new EaLogin();
        Document resDocument = DocumentHelper.createDocument();
        Element resRoot = resDocument.addElement("body");
        resRoot.addElement("account").setText(this.user);
        resRoot.addElement("password").setText(this.passwd);
        resRoot.addElement("type").setText("" + this.loginMode);
        pack.mStub.setLoginMessage(resRoot.asXML());
        return pack;
    }

    @Override
    protected IRespPackage createLoginRespPackage() {
        EaLoginResp pack = new EaLoginResp(this.channel);
        return pack;
    }

    @Override
    protected IRespPackage createLogoutRespPackage() {
        EaLogoutResp pack = new EaLogoutResp();
        return pack;
    }

    @Override
    protected IDeliverPackage createReportPackage() {
        EaReport pack = new EaReport();
        return pack;
    }

    @Override
    protected ISMSHead createSMSHead() {
        EaHead head = new EaHead();
        head.mHead.setEa(this.ea);
        return head;
    }

    @Override
    protected ISMSPackage createSubmitPackage(ISubmitMsg submit) {
        // TODO Auto-generated method stub
        EaSubmit pack = new EaSubmit((SubmitMsg) submit);
        return pack;
    }

    @Override
    protected ISubmitRespPackage createSubmitRespPackage() {
        // TODO Auto-generated method stub
        EaSubmitResp pack = new EaSubmitResp();
        return pack;
    }

    @Override
    protected String getDesc() {
        // TODO Auto-generated method stub
        return "CTCEAPP " + super.getDesc() + ": ";
    }

    @Override
    protected IRespPackage createReportRespPackage(IDeliverPackage smsPackage) {
        // TODO Auto-generated method stub
        EaReportResp pack = new EaReportResp();
        return pack;
    }

    @Override
    protected void logDataPacket(String description, byte[] pack) {
        log.info(getDesc() + description + new String(pack));
    }
    @Override
    protected boolean onSubmitResp(int seqid, ISubmitRespPackage respPackage) {
        boolean _bRet = false;

        if (!isResendResultCode(respPackage.getResult())) {
            WND_DATA _wnddata = releaseWndData(seqid);

            if (_wnddata != null) {
                SubmitMsg _submit = (SubmitMsg) _wnddata.submit;

                if (_submit != null) {
                    _submit.setChanId(this.connId);
                    EaSubmitResp _resp = (EaSubmitResp) respPackage;
                    _submit.setSubmitSMSResp(_resp.mStub);
                } else {
                    log.info(getDesc() + "onSubmitResp在wndList没有找到seqId的短信");
                }

                if (msgCallback != null) {
                    _bRet = msgCallback.onSubmitedSMS(_submit);
                }
            }
        } else {
            log.info(getDesc() + "发送速度过快,消息重发");
            resetWndData(seqid);
        }

        return _bRet;
    }

    @Override
    protected IRespPackage createLoginRespPackage(ISMSPackage arg0) {
        EaLoginResp pack = new EaLoginResp(this.channel);
        return pack;
    }

    /**
     * @return channel : return the property channel.
     */
    public EaCtcppChannel getChannel() {
        return channel;
    }

    /**
     * @param channel : set the property channel.
     */
    public void setChannel(EaCtcppChannel channel) {
        this.channel = channel;
    }

    /**
     * @return ea : return the property ea.
     */
    public int getEa() {
        return ea;
    }

    /**
     * @param ea : set the property ea.
     */
    public void setEa(int ea) {
        this.ea = ea;
    }
}
