package com.lw.common.ema.otasocket.message;

import org.apache.log4j.Logger;

import com.chinatricom.message.otaserver.OTADeliverSMSResp;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP通道短信上行响应包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:43:37
 */
public class OTADeliverResp implements IRespPackage {

    private static final Logger log = Logger.getLogger(OTADeliverResp.class);

    public OTADeliverSMSResp mStub = new OTADeliverSMSResp();

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
        if (bodyPack != null) {
            try {
                mStub.unPack(bodyPack);
            } catch (Exception ex) {
                log.error("", ex);
            }

        }

        return true;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }

}
