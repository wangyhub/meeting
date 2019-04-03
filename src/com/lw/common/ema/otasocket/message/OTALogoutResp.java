package com.lw.common.ema.otasocket.message;

import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP退出连接响应包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:47:00
 */
public class OTALogoutResp implements IRespPackage {

    public com.chinatricom.message.otaserver.OTALogoutResp mStub = new com.chinatricom.message.otaserver.OTALogoutResp();

    public int getResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub
        return null;
    }

    public byte[] getBytes() {
        return mStub.pack();
    }

    public boolean parsePackage(byte[] bodyPack) {
        if (bodyPack != null)
            return mStub.unPack(bodyPack) == 0;

        return true;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }

}
