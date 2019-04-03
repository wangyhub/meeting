package com.lw.common.ema.jwsserver.smsnotify;

import javax.jws.WebService;

import org.apache.log4j.Logger;

/**
 * 类描述： WEBSERVICE短信接口<br>
 * 接收企信通6.0推送过来的上行短信，状态报告
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 下午04:34:15
 */
@WebService(endpointInterface = "com.ctc.ema.jwsserver.smsnotify.ISmsOperatorNotify")
public class SmsOperatorNotifyImp implements ISmsOperatorNotify {
    private static Logger log = Logger.getLogger(SmsOperatorNotifyImp.class);

    public boolean getReport(String account, ReportMessageResDetail[] reportMessageArray) {

        if (reportMessageArray != null && reportMessageArray.length > 0) {
            log.info("------------------------------------------------------------------------------");
            log.info("接收到网关推送过来的状态报告:共 " + reportMessageArray.length + " 条");
            for (ReportMessageResDetail reportMessageResDetail : reportMessageArray) {
                log.info("短信标识(smsid)=" + reportMessageResDetail.getSmsId());
                log.info("手机号码(phoneNumber)=" + reportMessageResDetail.getPhoneNumber());
                log.info("发送状态(stat)=" + reportMessageResDetail.getStat());
                log.info("发送状态秒速(statDes)=" + reportMessageResDetail.getStatDes());
            }
            log.info("------------------------------------------------------------------------------");
        }
        return true;
    }

    public boolean getSms(String account, MoMessageResDetail[] moMessageArray) {
        if (moMessageArray != null && moMessageArray.length > 0) {
            log.info("------------------------------------------------------------------------------");
            log.info("接收到网关推送过来的上行短信:共 " + moMessageArray.length + " 条");
            for (MoMessageResDetail moMessageResDetail : moMessageArray) {
                log.info("发送内容(content)=" + moMessageResDetail.getContent());
                log.info("手机号码(phoneNumber)=" + moMessageResDetail.getPhoneNumber());
                log.info("子码(subCode)=" + moMessageResDetail.getSubCode());
            }
            log.info("------------------------------------------------------------------------------");
        }
        return true;
    }

}