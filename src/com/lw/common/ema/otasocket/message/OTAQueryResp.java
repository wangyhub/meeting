package com.lw.common.ema.otasocket.message;

import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;

public class OTAQueryResp implements IRespPackage {

    public com.chinatricom.message.otaserver.OTAQueryResp mStub = new com.chinatricom.message.otaserver.OTAQueryResp();

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

    public int getResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub
        return null;
    }

}
