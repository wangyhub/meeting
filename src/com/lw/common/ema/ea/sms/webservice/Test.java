package com.lw.common.ema.ea.sms.webservice;

import java.net.URL;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Encoder;

import com.lw.common.ema.common.MD5;

/**
 * ������������ͨ6.0�û��ӿڶ��ſͻ���
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-9-4 ����09:51:42
 */

public class Test {
    private static final String URL="http://172.18.2.235:8080/ctc-ema-bestpay/webService/smsOperEa?wsdl";
    private static final String ACCOUNT="cefpr";
    private static final String PASSWORD="3tYN(iz8";
    public static void main(String[] args) {
        SmsOperatorEaImpServiceLocator locator = new SmsOperatorEaImpServiceLocator();
        try {
            ISmsOperatorEa ioperator = locator.getSmsOperatorEaImpPort(new URL(URL));
            System.out.println("------------------------��ʼ�������ж���------------------------");
            Document reqocument = DocumentHelper.createDocument();
            Element resRoot = reqocument.addElement("smsMt");
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            resRoot.addElement("phoneNumber").setText("15251857925");
            resRoot.addElement("smsType").setText("20");
            resRoot.addElement("priority").setText("9");
            resRoot.addElement("smsId").setText(UUID.randomUUID().toString());
            BASE64Encoder baseEncoder = new BASE64Encoder();
            String content = baseEncoder.encodeBuffer("���Զ���".getBytes("utf-8"));
            resRoot.addElement("content").setText(content);
            resRoot.addElement("subCode").setText("12345");
            resRoot.addElement("sendTime").setText("20120905153540");
            String res = ioperator.sendSms(0, ACCOUNT,reqocument.asXML());
            System.out.println("�������" + reqocument.asXML());
            System.out.println("���أ�" + res);
            System.out.println("------------------------���������ж���------------------------");
            
            System.out.println("------------------------��ʼ��ȡ���ж���------------------------");
            reqocument = DocumentHelper.createDocument();
            resRoot = reqocument.addElement("smsMo");
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            res = ioperator.getSms(0, ACCOUNT,reqocument.asXML());
            System.out.println("�������" + reqocument.asXML());
            System.out.println("���أ�" + res);
            System.out.println("------------------------�����ȡ���ж���------------------------");
            
            System.out.println("------------------------��ʼ��ȡ����״̬����--------------------");
            reqocument = DocumentHelper.createDocument();
            resRoot = reqocument.addElement("smsReport");
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            res = ioperator.getReport(0, ACCOUNT,reqocument.asXML());
            System.out.println("�������" + reqocument.asXML());
            System.out.println("���أ�" + res);
            System.out.println("------------------------�����ȡ����״̬����--------------------");
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

    }
}