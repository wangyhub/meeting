package com.lw.common.ema.ea.sms.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.UUID;
import java.lang.Exception; 
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.lw.common.ema.common.MD5;
import com.lw.common.ema.ea.encry.des3.Encry_DES3CLt;

/**
 * ������������ͨ6.0�û��ӿڶ��ſͻ���
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-9-4 ����09:51:42
 */
public class TestDes3 {
    private static final String url = "http://127.0.0.1:8080/ctc-ema-bestpay/webService/smsOperEa?wsdl";

    private static final String ACCOUNT = "zh";

    private static final String PASSWORD = "zh.com";

    public static void main(String[] args) {
        String keyFile = "D:\\key.dat";
        TestDes3 test = new TestDes3();
        test.getKey(keyFile);
        test.sendSms(keyFile);
        test.getReport(keyFile);
        test.getSms(keyFile);

    }

    /**
     * ������������ȡ��Կ
     * 
     * @param keyFile
     * @author: 8521
     * @date: 2012-9-19 ����03:48:49
     */
    public void getKey(String keyFile) {
        try {
            SmsOperatorEaImpServiceLocator locator = new SmsOperatorEaImpServiceLocator();
            ISmsOperatorEa ioperator = locator.getSmsOperatorEaImpPort(new URL(
                    url));
            Document reqocument = DocumentHelper.createDocument();
            System.out.println("------------------------��ʼ��ȡ��Կ--------------------");
            reqocument = DocumentHelper.createDocument();
            Element resRoot = reqocument.addElement("getKeyRes");
            resRoot.addElement("account").setText(ACCOUNT);
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            System.out.println("�������" + reqocument.asXML());
            String res = ioperator.getKey(1, reqocument.asXML());
            System.out.println("���ؽ��" + res);
            Document doc = null;

            /** ����xml */
            doc = DocumentHelper.parseText(res);
            Element rootElement = doc.getRootElement();
            String keyStr = rootElement.elementText("key");
            if (keyStr != null) {
                keyStr = keyStr.replace(' ', '+');
                FileOutputStream fos = new FileOutputStream(new File(keyFile));
                BASE64Decoder decoder = new BASE64Decoder();
                fos.write(decoder.decodeBuffer(keyStr));
                fos.close();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------�����ȡ��Կ--------------------");
    }

    /**
     * �������������Ͷ���
     * 
     * @param keyFile
     * @author: 8521
     * @date: 2012-9-19 ����03:48:58
     */
    public void sendSms(String keyFile) {
        try {

            SmsOperatorEaImpServiceLocator locator = new SmsOperatorEaImpServiceLocator();
            ISmsOperatorEa ioperator = locator.getSmsOperatorEaImpPort(new URL(
                    url));
            Document reqocument = DocumentHelper.createDocument();

            System.out.println("------------------------��ʼ�������ж���------------------------");
            reqocument = DocumentHelper.createDocument();
            Element resRoot = reqocument.addElement("smsMt");
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            resRoot.addElement("phoneNumber").setText("13111111111,13222222222,13333333333");
            resRoot.addElement("smsType").setText("20");
            resRoot.addElement("priority").setText("9");
            resRoot.addElement("smsId").setText(UUID.randomUUID().toString());
            BASE64Encoder baseEncoder = new BASE64Encoder();
            String content = baseEncoder.encodeBuffer("���Զ���".getBytes("utf-8"));
            resRoot.addElement("content").setText(content);
            resRoot.addElement("subCode").setText("12345");
            resRoot.addElement("sendTime").setText("20120905153540");
            String res = ioperator.sendSms(1, ACCOUNT,Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyFile));
            System.out.println("�������" + reqocument.asXML());
            System.out.println("���ؽ��" + Encry_DES3CLt.getInstance().getDesString(res, keyFile));
            System.out.println("------------------------���������ж���------------------------");

        } catch (java.lang.Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * ������������ȡ����
     * 
     * @param keyFile
     * @author: 8521
     * @date: 2012-9-19 ����03:49:09
     */
    public void getSms(String keyFile) {
        try {
            SmsOperatorEaImpServiceLocator locator = new SmsOperatorEaImpServiceLocator();
            ISmsOperatorEa ioperator = locator.getSmsOperatorEaImpPort(new URL(
                    url));
            Document reqocument = DocumentHelper.createDocument();

            System.out.println("------------------------��ʼ��ȡ���ж���------------------------");
            reqocument = DocumentHelper.createDocument();
            Element resRoot = reqocument.addElement("smsMo");
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            String res = ioperator.getSms(1, ACCOUNT,Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyFile));
            System.out.println("�������" + reqocument.asXML());
            System.out.println("���ؽ��" + Encry_DES3CLt.getInstance().getDesString(res, keyFile));
            System.out.println("------------------------�����ȡ���ж���------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ������������ȡ״̬����
     * 
     * @param keyFile
     * @author: 8521
     * @date: 2012-9-19 ����03:49:29
     */
    public void getReport(String keyFile) {
        try {
            SmsOperatorEaImpServiceLocator locator = new SmsOperatorEaImpServiceLocator();
            ISmsOperatorEa ioperator = locator.getSmsOperatorEaImpPort(new URL(
                    url));
            Document reqocument = DocumentHelper.createDocument();

            System.out.println("------------------------��ʼ��ȡ����״̬����--------------------");
            reqocument = DocumentHelper.createDocument();
            Element resRoot = reqocument.addElement("smsReport");
            resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
            String res = ioperator.getReport(1, ACCOUNT,Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyFile));
            System.out.println("�������" + reqocument.asXML());
            System.out.println("���ؽ��" + Encry_DES3CLt.getInstance().getDesString(res, keyFile));
            System.out.println("------------------------�����ȡ����״̬����--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}