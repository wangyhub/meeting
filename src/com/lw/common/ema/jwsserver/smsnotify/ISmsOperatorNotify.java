package com.lw.common.ema.jwsserver.smsnotify;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * 类描述： WEBSERVICE短信接口<br>
 * 接收企信通6.0推送过来的上行短信，状态报告
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 下午04:34:15
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ISmsOperatorNotify {


    /**
     * 接收企信通6.0推送过来的上行短信
     * 
     * @param account
     *            账户
     * @param moMessageRes
     *            上行信息
     */
    public boolean getSms(String account, MoMessageResDetail[] moMessageArray);

    /**
     * 接收企信通6.0推送过来的状态报告
     * 
     * @param account
     *            账户
     * @param reportMessageRes
     *            状态报告
     * @author: 8522
     * @date: 2012-2-17 下午04:38:05
     */
    public boolean  getReport(String account, ReportMessageResDetail[] reportMessageArray);


}