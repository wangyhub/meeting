package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.otaserver.OTAConstants;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP包头结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:44:25
 */
public class OTAHead implements ISMSHead {

    public com.chinatricom.message.otaserver.OTAHead mHead = new com.chinatricom.message.otaserver.OTAHead();

    public boolean check() {
        // TODO Auto-generated method stub
        return true;
    }

    public int getBodySize() {
        // TODO Auto-generated method stub
        return mHead.getPacketLength() - OTAConstants.HEAD_SIZE;
    }

    public byte[] getHeadBytes() {
        return mHead.pack();
    }

    public int getHeadSize() {
        // TODO Auto-generated method stub
        return OTAConstants.HEAD_SIZE;
    }

    public int getStat() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean parseHead(byte[] headPack) {

        return (mHead.unPack(headPack) == 0);
    }

    public void setBodySize(int bodySize) {
        mHead.setPacketLength(bodySize + OTAConstants.HEAD_SIZE);
    }

    public PackageType getPackageType() {

        PackageType pt = PackageType.PT_Unknown;
        switch (mHead.getRequestID()) {
        case OTAConstants.Login:
            pt = PackageType.PT_Login;
            break;
        case OTAConstants.Login_Resp:
            pt = PackageType.PT_LoginResp;
            break;
        case OTAConstants.Exit:
            pt = PackageType.PT_Logout;
            break;
        case OTAConstants.Exit_Resp:
            pt = PackageType.PT_LogoutResp;
            break;
        case OTAConstants.Submit:
            pt = PackageType.PT_Submit;
            break;
        case OTAConstants.Submit_Resp:
            pt = PackageType.PT_SubmitResp;
            break;
        case OTAConstants.Deliver:
            pt = PackageType.PT_Deliver;
            break;
        case OTAConstants.Deliver_Resp:
            pt = PackageType.PT_DeliverResp;
            break;
        case OTAConstants.Active_Test:
            pt = PackageType.PT_ActiveTest;
            break;
        case OTAConstants.Active_Test_Resp:
            pt = PackageType.PT_ActiveTestResp;
            break;
        case OTAConstants.Query:
            pt = PackageType.PT_Query;
            break;
        case OTAConstants.Query_Resp:
            pt = PackageType.PT_QueryResp;
            break;
        }

        return pt;
    }

    public void setPackageType(PackageType pt) {
        switch (pt) {
        case PT_Login:
            mHead.setRequestID(OTAConstants.Login);
            break;
        case PT_LoginResp:
            mHead.setRequestID(OTAConstants.Login_Resp);
            break;
        case PT_Logout:
            mHead.setRequestID(OTAConstants.Exit);
            break;
        case PT_LogoutResp:
            mHead.setRequestID(OTAConstants.Exit_Resp);
            break;
        case PT_Submit:
            mHead.setRequestID(OTAConstants.Submit);
            break;
        case PT_SubmitResp:
            mHead.setRequestID(OTAConstants.Submit_Resp);
            break;
        case PT_Deliver:
            mHead.setRequestID(OTAConstants.Deliver);
            break;
        case PT_DeliverResp:
            mHead.setRequestID(OTAConstants.Deliver_Resp);
            break;
        case PT_ActiveTest:
            mHead.setRequestID(OTAConstants.Active_Test);
            break;
        case PT_ActiveTestResp:
            mHead.setRequestID(OTAConstants.Active_Test_Resp);
            break;
        case PT_Query:
            mHead.setRequestID(OTAConstants.Query);
            break;
        case PT_QueryResp:
            mHead.setRequestID(OTAConstants.Query_Resp);
            break;
        }
    }

    public int getSeqId() {
        return (int) mHead.getSequenceID();
    }

    public void setSeqId(int sequenceID) {
        mHead.setSequenceID(sequenceID);
    }

}
