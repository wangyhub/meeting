package com.lw.common.ema.otasocket.message;

import java.util.Date;

import org.apache.log4j.Logger;

import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.CommonSMS;
import com.chinatricom.slidewindow.IDeliverPackage;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISMSPackage;
import com.chinatricom.slidewindow.ISubmitRespPackage;
import com.chinatricom.slidewindow.SMSCallback;
import com.chinatricom.slidewindow.WND_DATA;
import com.chinatricom.util.DateUtil;

public class OTApp extends CommonSMS {

    private static final Logger log = Logger.getLogger(OTApp.class);

    public OTApp(String host, int port, String user, String passwd, int connId, int wndSize, int timeout,
            int trys, int speed, SMSCallback msgCallback, int connMode) {
        super(host, port, user, passwd, "", "", "", connMode, connId, wndSize, timeout, trys, 0, speed, msgCallback);
    }

    @Override
    protected ISMSPackage createActiveTestPackage() {
        OTAActiveTest pack = new OTAActiveTest();
        return pack;
    }

    @Override
    protected IRespPackage createActiveTestRespPackage() {
        OTAActiveTestResp resp = new OTAActiveTestResp();
        return resp;
    }

    @Override
    protected IDeliverPackage createDeliverPackage() {
        OTADeliver pack = new OTADeliver();
        return pack;
    }

    @Override
    protected IRespPackage createDeliverRespPackage(IDeliverPackage smsPackage) {
        OTADeliverResp resp = new OTADeliverResp();
        return resp;
    }

    @Override
    protected ISMSPackage createLoginPackage() {
        OTALogin pack = new OTALogin();
        pack.mStub.setClientID(this.user);
        pack.mStub.setAuthenticatorClient(this.passwd.getBytes());
        pack.mStub.setLoginMode((byte)this.loginMode);
        return pack;
    }

    @Override
    protected IRespPackage createLoginRespPackage() {
        OTALoginResp pack = new OTALoginResp();
        return pack;
    }

    @Override
    protected IRespPackage createLogoutRespPackage() {
        OTALogoutResp pack = new OTALogoutResp();
        return pack;
    }

    @Override
    protected IDeliverPackage createReportPackage() {
        return null;
    }

    @Override
    protected ISMSHead createSMSHead() {
        OTAHead head = new OTAHead();
        return head;
    }

    @Override
    protected ISMSPackage createSubmitPackage(ISubmitMsg submit) {
        // TODO Auto-generated method stub
        OTASubmit pack = new OTASubmit((SubmitMsg) submit);
        return pack;
    }

    @Override
    protected ISubmitRespPackage createSubmitRespPackage() {
        // TODO Auto-generated method stub
        OTASubmitResp pack = new OTASubmitResp();
        return pack;
    }

    @Override
    protected String getDesc() {
        // TODO Auto-generated method stub
        return "OTAPP " + super.getDesc() + ": ";
    }

    @Override
    protected IRespPackage createReportRespPackage(IDeliverPackage smsPackage) {
        // TODO Auto-generated method stub
        return null;
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
                    OTASubmitResp _resp = (OTASubmitResp) respPackage;
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
        OTALoginResp pack = new OTALoginResp();
        return pack;
    }
    @Override
    protected ISMSPackage createQueryReportPackage(String inMsgs) {
        OTAQuery pack =new OTAQuery();
        pack.mStub.setQueryTime(DateUtil.transFormString(new Date(), "yyyyMMdd"));        
        return pack;
    }
}
