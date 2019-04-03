package com.lw.common.ema.otasocket.message;

import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISMSPackage;

/**
 * 类描述：CTCPP心跳检测包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:40:52
 */
public class OTAActiveTest implements ISMSPackage {

    public com.chinatricom.message.otaserver.OTAActiveTest mStub = new com.chinatricom.message.otaserver.OTAActiveTest();

    public byte[] getBytes() {

        return mStub.pack();
    }

    public boolean parsePackage(byte[] bodyPack) {
        if (bodyPack != null)
            return (mStub.unPack(bodyPack) == 0);

        return true;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }
}
