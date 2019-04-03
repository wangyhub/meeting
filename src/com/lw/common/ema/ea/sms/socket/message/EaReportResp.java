package com.lw.common.ema.ea.sms.socket.message;

import com.chinatricom.message.ctcserverea.CTCEaReportResp;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP状态报告响应包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:48:23
 */
public class EaReportResp implements IRespPackage {

    public CTCEaReportResp mStub = new CTCEaReportResp();

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
        if (bodyPack != null)
            return mStub.unPack(new String(bodyPack));

        return true;
    }

    public void setHead(ISMSHead head) {
        EaHead _head = (EaHead) head;
        mStub.setHead(_head.mHead);
    }

}
