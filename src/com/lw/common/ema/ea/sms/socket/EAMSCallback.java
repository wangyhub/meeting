package com.lw.common.ema.ea.sms.socket;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;
import com.lw.common.ema.ea.encry.des3.Encry_DES3CLt;
import com.lw.common.ema.ea.sms.socket.message.DeliverMsg;
import com.lw.common.ema.ea.sms.socket.message.ReportMsg;
import com.lw.common.ema.ea.sms.socket.message.SubmitMsg;

/**
 * 类描述：短信提交状态，状态报告，上行回写类，<br>
 * 所有提交状态，状态报告，上行都会回调对应的方法，客户端只需要在方法中处理获取的信息即可
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-4-13 下午04:03:43
 */
public class EAMSCallback implements SMSCallback {
    private byte[] key = null;

    /**
     * 这个函数接收上行短信，短信的各个字段都放在DeliverMsg
     */
    public boolean onDeliverSMS(IDeliverMsg arg0) {
        DeliverMsg msg = (DeliverMsg) arg0;
        if (msg != null) {
            if (key != null) {
                System.out.println("收到deliverMsg:"
                        + Encry_DES3CLt.getInstance().getDesString(msg.getSmsMoMessage(), key));
            } else
                System.out.println("收到deliverMsg:" + msg.getSmsMoMessage());
            // 可以将上行短信入库，放入队列等，建议先放入队列，之后异步处理这些上行短信，因为入库会消耗时间，影响收发性能。
        }
        return true;
    }

    /**
     * 这个函数接收下发短信的状态报告，它通过msgid关联到下行短信 result : 0———成功 1——等待发送 2——失败
     */
    public boolean onMTReportSMS(IReportMsg arg0) {
        ReportMsg msg = (ReportMsg) arg0;
        if (msg != null) {
            if (key != null) {
                System.out.println("收到report: "
                        + Encry_DES3CLt.getInstance().getDesString(msg.getSmsReportMessage(), key));
            } else
                System.out.println("收到report: " + msg.getSmsReportMessage());
            // 可以将状态报告入库，放入队列等，但是建议先放入队列，之后异步处理这些状态报告，因为入库会消耗时间，影响收发性能。
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
            if (key != null) {
                System.out.println("收到submit resp:"
                        + Encry_DES3CLt.getInstance().getDesString(msg.getSubmitSMSResp().getSmsMtMessageRes(),
                                key));
            } else
                System.out.println("收到submit resp:" + msg.getSubmitSMSResp().getSmsMtMessageRes());
            // 可以将提交状态入库，放入队列等，但是建议先放入队列，之后异步处理这些提交状态信息，因为入库会消耗时间，影响收发性能。
        }
        return true;
    }

    /**
     * @return key : return the property key.
     */
    public byte[] getKey() {
        return key;
    }

    /**
     * @param key
     *            : set the property key.
     */
    public void setKey(byte[] key) {
        this.key = key;
    }
}
