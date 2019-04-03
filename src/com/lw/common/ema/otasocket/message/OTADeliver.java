package com.lw.common.ema.otasocket.message;

import org.apache.log4j.Logger;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.otaserver.OTADeliverSMS;
import com.chinatricom.slidewindow.IDeliverPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * 类描述：CTCPP通道短信上行包
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 下午04:42:47
 */
public class OTADeliver implements IDeliverPackage {

    private static final Logger log = Logger.getLogger(OTADeliver.class);

    public OTADeliverSMS mStub = new OTADeliverSMS();

    public IDeliverMsg getDeliverMsg() {
        DeliverMsg msg = null;
        try {
            msg = new DeliverMsg();
            msg.setDeliverSMS(mStub);
        } catch (Exception ex) {
            log.error("", ex);
        }

        return msg;
    }

    public IReportMsg getReportMsg() {
        ReportMsg report = new ReportMsg();
        report.setReport(mStub.getReport());
        report.setDestTermID(mStub.getDestTermID());
        report.setSrcTermID(mStub.getSrcTermID());
        return report;
    }

    public boolean isReport() {
        // TODO Auto-generated method stub
        return mStub.getIsReport() == 1;
    }

    public byte[] getBytes() {
        try {
            return mStub.pack();
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    public boolean parsePackage(byte[] bodyPack) {
        try {
            mStub.unPack(bodyPack);
        } catch (Exception ex) {
            log.error("", ex);
        }

        return true;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }

}
