package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.otaserver.OTADeliverSMS;

public class DeliverMsg implements IDeliverMsg {

    private OTADeliverSMS deliverSMS = new OTADeliverSMS();


    public DeliverMsg() {
    }


    public int getCountOfSms() {
        // TODO Auto-generated method stub
        return 0;
    }


    public String getFrom() {
        // TODO Auto-generated method stub
        return null;
    }


    public int getIdxOfSms() {
        // TODO Auto-generated method stub
        return 0;
    }


    public void merge(IDeliverMsg inMsg) {
        // TODO Auto-generated method stub
        
    }


    public String getLogMsg() {
        // TODO Auto-generated method stub
        return null;
    }


    public void setChanId(int inId) {
        // TODO Auto-generated method stub
        
    }


    public void setCorpId(String inSrc) {
        // TODO Auto-generated method stub
        
    }


    /**
     * @return deliverSMS : return the property deliverSMS.
     */
    public OTADeliverSMS getDeliverSMS() {
        return deliverSMS;
    }


    /**
     * @param deliverSMS : set the property deliverSMS.
     */
    public void setDeliverSMS(OTADeliverSMS deliverSMS) {
        this.deliverSMS = deliverSMS;
    }

}
