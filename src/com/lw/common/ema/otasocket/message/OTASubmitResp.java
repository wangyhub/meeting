package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.otaserver.OTAConstants;
import com.chinatricom.message.otaserver.OTASubmitSMSResp;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISubmitRespPackage;

/**
 * 类描述：CTCPP短信下发响应包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:49:43
 */
public class OTASubmitResp implements ISubmitRespPackage {

    public OTASubmitSMSResp mStub = new OTASubmitSMSResp();


    public byte[] getBytes() {
        return mStub.pack();
    }

    public boolean parsePackage(byte[] bodyPack) {
        if (bodyPack != null)
            return mStub.unPack(bodyPack) ==0;

        return true;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }

    public String getMsgId() {
        // TODO Auto-generated method stub
        return mStub.getMsgId();
    }

    public String getSuccPhones() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getResult() {
        // TODO Auto-generated method stub
        return mStub.getResult();
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub
        return OTAConstants.getSubmitDesc(mStub.getResult());
    }


}
