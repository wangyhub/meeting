package com.lw.common.ema.ea.sms.socket.message;

import org.apache.log4j.Logger;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ctcserverea.CTCEaDeliverSMS;
import com.chinatricom.slidewindow.IDeliverPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP通道短信上行包
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:42:47
 */
public class EaDeliver implements IDeliverPackage {

    private static final Logger log = Logger.getLogger(EaDeliver.class);

    public CTCEaDeliverSMS mStub = new CTCEaDeliverSMS();

    public IDeliverMsg getDeliverMsg() {
        DeliverMsg msg = null;
        try {
            msg = new DeliverMsg();
            msg.setSmsMoMessage(mStub.getSmsMoMessage());
        } catch (Exception ex) {
            log.error("", ex);
        }

        return msg;
    }

    public IReportMsg getReportMsg() {
        return null;
    }

    public boolean isReport() {
        // TODO Auto-generated method stub
        return false;
    }

    public byte[] getBytes() {
        try {
            return mStub.pack().getBytes("gbk");
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    public boolean parsePackage(byte[] bodyPack) {
        try {
            String strMsg = new String(bodyPack, "gbk");
            mStub.unPack(strMsg);
        } catch (Exception ex) {
            log.error("", ex);
        }

        return true;
    }

    public void setHead(ISMSHead head) {
        EaHead _head = (EaHead) head;
        mStub.setHead(_head.mHead);
    }

    public static void main(String[] args) {

        EaDeliver _deliver = new EaDeliver();
        String strMsg = "<body><to>1062001515</to><from>tel:18988888888</from><serviceId>String</serviceId><type>1</type><msgfmt>15</msgfmt><msg>埃里克森登记费</msg><linkid>null</linkid>";
        _deliver.parsePackage(strMsg.getBytes());

    }
}
