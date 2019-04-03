package com.lw.common.ema.ea.sms.webservice.notify;

import javax.jws.WebService;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * �������� WEBSERVICE���Žӿ�<br>
 * ��������ͨ6.0���͹��������ж��ţ�״̬����
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 ����04:34:15
 */
@WebService(endpointInterface = "com.ctc.ema.ea.sms.webservice.notify.ISmsEaOperatorNotify")
public class SmsEaOperatorNotifyImp implements ISmsEaOperatorNotify {

    public String getSms(int ea, String account, String smsMoMessage) {
        // TODO Auto-generated method stub
        System.out.println("�յ���������͹��������У�ea=" + ea + ",account=" + account + ",smsMoMessage=" + smsMoMessage);
        Document resDocument = DocumentHelper.createDocument();
        Element resRoot = resDocument.addElement("smsMoRes");
        try {
            resRoot.addElement("stat").setText("r:000");
            return resDocument.asXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}