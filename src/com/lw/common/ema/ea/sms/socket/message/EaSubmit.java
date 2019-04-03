package com.lw.common.ema.ea.sms.socket.message;

import com.chinatricom.message.ctcserverea.CTCEaSubmitSMS;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISubmitPackage;

/**
 * 类描述：CTCPP短信下发包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:49:04
 */
public class EaSubmit implements ISubmitPackage {

    public CTCEaSubmitSMS mStub = new CTCEaSubmitSMS();

    public EaSubmit(SubmitMsg msg) {
        mStub.setSmsMtMessage(msg.getSmsMtMessage());
    }

    public SubmitMsg getSubmitMsg() {
        // TODO Auto-generated method stub
        return null;
    }

    public byte[] getBytes() {
        try {
            return mStub.pack().getBytes("utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean parsePackage(byte[] bodyPack) {
        try {
            if (bodyPack != null)
                return mStub.unPack(new String(bodyPack, "utf-8"));
        } catch (Exception ex) {
            return false;
        }
        return false;

    }

    public void setHead(ISMSHead head) {
        EaHead _head = (EaHead) head;
        mStub.setHead(_head.mHead);
    }

}
