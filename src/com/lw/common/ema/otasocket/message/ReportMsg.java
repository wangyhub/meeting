package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.otaserver.OTAStatReport;

public class ReportMsg implements IReportMsg {

    /**
     * 消息发送号码(21byte)
     */
    private String srcTermID;

    /**
     * 短消息接收号码(21byte)
     */
    private String destTermID;

    private OTAStatReport report = new OTAStatReport();

    public ReportMsg() {
    }

    /**
     * @return report : return the property report.
     */
    public OTAStatReport getReport() {
        return report;
    }

    /**
     * @param report
     *            : set the property report.
     */
    public void setReport(OTAStatReport report) {
        this.report = report;
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
     * @return srcTermID : return the property srcTermID.
     */
    public String getSrcTermID() {
        return srcTermID;
    }

    /**
     * @param srcTermID
     *            : set the property srcTermID.
     */
    public void setSrcTermID(String srcTermID) {
        this.srcTermID = srcTermID;
    }

    /**
     * @return destTermID : return the property destTermID.
     */
    public String getDestTermID() {
        return destTermID;
    }

    /**
     * @param destTermID
     *            : set the property destTermID.
     */
    public void setDestTermID(String destTermID) {
        this.destTermID = destTermID;
    }

}
