package com.lw.common.ema.ea.sms.socket.message;

import com.chinatricom.message.Constants;
import com.chinatricom.message.ctcserverea.CTCEaHead;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * ��������CTCPP��ͷ�ṹ
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 ����04:44:25
 */
public class EaHead implements ISMSHead {

    public CTCEaHead mHead = new CTCEaHead();

    public boolean check() {
        // TODO Auto-generated method stub
        return true;
    }

    public int getBodySize() {
        // TODO Auto-generated method stub
        return mHead.getLen();
    }

    public byte[] getHeadBytes() {
        return mHead.pack().getBytes();
    }

    public int getHeadSize() {
        // TODO Auto-generated method stub
        return Constants.CMT_HeadSize;
    }

    public int getStat() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean parseHead(byte[] headPack) {
        return mHead.unPack(new String(headPack));
    }

    public void setBodySize(int bodySize) {
        mHead.setLen(bodySize);
    }

    public PackageType getPackageType() {

        PackageType pt = PackageType.PT_Unknown;
        switch (mHead.getMsgType()) {
        case Constants.CMT_Login:
            pt = PackageType.PT_Login;
            break;
        case Constants.CMT_LoginResp:
            pt = PackageType.PT_LoginResp;
            break;
        case Constants.CMT_Logout:
            pt = PackageType.PT_Logout;
            break;
        case Constants.CMT_LogoutResp:
            pt = PackageType.PT_LogoutResp;
            break;
        case Constants.CMT_SubmitSMS:
            pt = PackageType.PT_Submit;
            break;
        case Constants.CMT_SubmitSMSResp:
            pt = PackageType.PT_SubmitResp;
            break;
        case Constants.CMT_DeliverSMS:
            pt = PackageType.PT_Deliver;
            break;
        case Constants.CMT_DeliverSMSResp:
            pt = PackageType.PT_DeliverResp;
            break;
        case Constants.CMT_Report:
            pt = PackageType.PT_Report;
            break;
        case Constants.CMT_ReportResp:
            pt = PackageType.PT_ReportResp;
            break;
        case Constants.CMT_ActiveTest:
            pt = PackageType.PT_ActiveTest;
            break;
        case Constants.CMT_ActiveTestResp:
            pt = PackageType.PT_ActiveTestResp;
            break;
        case Constants.CMT_QueryReport:
            pt = PackageType.PT_Query;
            break;
        case Constants.CMT_QueryReportResp:
            pt = PackageType.PT_QueryResp;
            break;
        }

        return pt;
    }

    @SuppressWarnings("incomplete-switch")
	public void setPackageType(PackageType pt) {
        switch (pt) {
        case PT_Login:
            mHead.setMsgType(Constants.CMT_Login);
            break;
        case PT_LoginResp:
            mHead.setMsgType(Constants.CMT_LoginResp);
            break;
        case PT_Logout:
            mHead.setMsgType(Constants.CMT_Logout);
            break;
        case PT_LogoutResp:
            mHead.setMsgType(Constants.CMT_LogoutResp);
            break;
        case PT_Submit:
            mHead.setMsgType(Constants.CMT_SubmitSMS);
            break;
        case PT_SubmitResp:
            mHead.setMsgType(Constants.CMT_SubmitSMSResp);
            break;
        case PT_Deliver:
            mHead.setMsgType(Constants.CMT_DeliverSMS);
            break;
        case PT_DeliverResp:
            mHead.setMsgType(Constants.CMT_DeliverSMSResp);
            break;
        case PT_Report:
            mHead.setMsgType(Constants.CMT_Report);
            break;
        case PT_ReportResp:
            mHead.setMsgType(Constants.CMT_ReportResp);
            break;
        case PT_ActiveTest:
            mHead.setMsgType(Constants.CMT_ActiveTest);
            break;
        case PT_ActiveTestResp:
            mHead.setMsgType(Constants.CMT_ActiveTestResp);
            break;
        case PT_Query:
            mHead.setMsgType(Constants.CMT_QueryReport);
            break;
        case PT_QueryResp:
            mHead.setMsgType(Constants.CMT_QueryReportResp);
            break;
        }
    }

    public int getSeqId() {
        return (int) mHead.getSeqId();
    }

    public void setSeqId(int seqId) {
        mHead.setSeqId(seqId);
    }

}
