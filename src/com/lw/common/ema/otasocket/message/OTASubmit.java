package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.message.otaserver.OTASubmitSMS;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISubmitPackage;

/**
 * 类描述：CTCPP短信下发包结构
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:49:04
 */
public class OTASubmit implements ISubmitPackage {

    public OTASubmitSMS mStub = new OTASubmitSMS();

    public OTASubmit(SubmitMsg msg) {
        mStub.setNeedReport(msg.getNeedReport());
        mStub.setPriority(msg.getPriority());
        mStub.setServiceId(msg.getServiceId());
        mStub.setMsgFormat(msg.getMsgFormat());
        mStub.setValidTime(msg.getValidTime());
        mStub.setAtTime(msg.getAtTime());
        mStub.setSrcTermID(msg.getSrcTermID());
        mStub.setDestTermIDCount(msg.getDestTermIDCount());
        mStub.setDestTermID(msg.getDestTermID());
        mStub.setMsgContent(msg.getMsgContent());
        mStub.setSeqOfLongSms(msg.getMsgSeqid());
        mStub.setIdxOfLongSms(msg.getIdxOfLongSms());
        mStub.setCountOfLongSms(msg.getCountOfLongSms());
        mStub.setpKTotal(msg.getCountOfLongSms());
        mStub.setpKNumber(msg.getIdxOfLongSms());
    }


    public byte[] getBytes() {
        try {
            return mStub.pack();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean parsePackage(byte[] bodyPack) {
        try {
            if (bodyPack != null)
                return mStub.unPack(bodyPack) ==0;
        } catch (Exception ex) {
            return false;
        }
        return false;

    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }


    public ISubmitMsg getSubmitMsg() {
        // TODO Auto-generated method stub
        return null;
    }

}
