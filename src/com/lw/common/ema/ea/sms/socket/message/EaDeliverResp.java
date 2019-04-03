package com.lw.common.ema.ea.sms.socket.message;

import org.apache.log4j.Logger;

import com.chinatricom.message.ctcserverea.CTCEaDeliverSMSResp;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP通道短信上行响应包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:43:37
 */
public class EaDeliverResp implements IRespPackage {

    private static final Logger log = Logger.getLogger(EaDeliverResp.class);

    public CTCEaDeliverSMSResp mStub = new CTCEaDeliverSMSResp();

    public int getResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub
        return null;
    }

    public byte[] getBytes() {
        return mStub.pack().getBytes();
    }

    public boolean parsePackage(byte[] bodyPack) {
        if (bodyPack != null) {
            try {
                String strMsg = new String(bodyPack, "gbk");
                mStub.unPack(strMsg);
                log.info(strMsg);
            } catch (Exception ex) {
                log.error("", ex);
            }

        }

        return true;
    }

    public void setHead(ISMSHead head) {
        EaHead _head = (EaHead) head;
        mStub.setHead(_head.mHead);
    }

}
