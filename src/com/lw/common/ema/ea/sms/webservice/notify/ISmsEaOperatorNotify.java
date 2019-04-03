package com.lw.common.ema.ea.sms.webservice.notify;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * 类描述： WEBSERVICE短信接口<br>
 * 接收企信通6.0推送过来的上行短信
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 下午04:34:15
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ISmsEaOperatorNotify {


    /**
     * 接收企信通6.0推送过来的上行短信
     * 
     */
    public String getSms(int ea ,String account,String smsMoMessage);
}