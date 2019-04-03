package com.lw.common.ema.otasocket.message;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;

/**
 * 类描述：短信提交状态，状态报告，上行回写类，<br>
 * 所有提交状态，状态报告，上行都会回调对应的方法，客户端只需要在方法中处理获取的信息即可
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-4-13 下午04:03:43
 */
public class OTACallback implements SMSCallback {

    /**
     * 这个函数接收上行短信，短信的各个字段都放在DeliverMsg
     */
    public boolean onDeliverSMS(IDeliverMsg arg0) {
        DeliverMsg msg = (DeliverMsg) arg0;
        if (msg != null) {
            System.out.println("收到deliverMsg:" + msg);
            System.out.println("srcTermID:" + msg.getDeliverSMS().getSrcTermID());
            System.out.println("destTermID:" + msg.getDeliverSMS().getDestTermID());
            System.out.println("msgContent:" + msg.getDeliverSMS().getMsgContent());
            System.out.println("recvTime:" + msg.getDeliverSMS().getRecvTime());
        }
        return true;
    }

    /**
     * 这个函数接收下发短信的状态报告，它通过msgid关联到下行短信 result : 0———成功 1——等待发送 2——失败
     */
    public boolean onMTReportSMS(IReportMsg arg0) {
        ReportMsg msg = (ReportMsg) arg0;
        if (msg != null) {
            System.out.println("收到report: " + msg);
            System.out.println("msgid: " + msg.getReport().getId());
            System.out.println("stat: " + msg.getReport().getStat());
            System.out.println("destTermID: " + msg.getDestTermID());
            System.out.println("srcTermID: " + msg.getSrcTermID());
        }
        return true;
    }

    /**
     * 这个函数接收下行短信的response，SubmitMsg里面有了result和msgId,这里的msg就是_chan.submit(_msg)
     * 里面的_msg。 result = 0 表示成功， 其他均为失败。
     */
    public boolean onSubmitedSMS(ISubmitMsg arg0) {
        SubmitMsg msg = (SubmitMsg) arg0;
        if (msg != null) {
            System.out.println("收到submit resp:" + msg.getSubmitSMSResp());
            System.out.println("DestTermID:" + msg.getDestTermID());
            System.out.println("MsgId:" + msg.getSubmitSMSResp().getMsgId());
            System.out.println("result:" + msg.getSubmitSMSResp().getResult());
            System.out.println("ResultDesc:" + msg.getSubmitSMSResp().getResultDesc());
        }
        return true;
    }
}
