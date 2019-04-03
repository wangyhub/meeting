package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.otaserver.OTAConstants;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP连接响应包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:45:50
 */
public class OTALoginResp implements IRespPackage {

    public com.chinatricom.message.otaserver.OTALoginResp mStub = new com.chinatricom.message.otaserver.OTALoginResp();


    public OTALoginResp() {

    }


    public byte[] getBytes() {
        return mStub.pack();
    }

    public boolean parsePackage(byte[] bodyPack) {
        boolean res = true;
        if (bodyPack != null) {
            res = (mStub.unPack(bodyPack) == 0);
        }
        return res;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }

    public int getResult() {
        return mStub.getStatus();
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub

        return OTAConstants.getLoginDesc(mStub.getStatus());
    }

}
