package com.lw.common.ema.ea.sms.http;

import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
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
public class TestDes3Byte {
    private static final String url = "http://127.0.0.1:8080/ctc-ema-bestpay/servlet/smsEa";

    private static final String account = "zh";

    private static final String password = "zh.com";

    public static void main(String[] args) {
        try {

            TestDes3Byte test = new TestDes3Byte();
            // test.testkey();
            byte[] keyArray = test.getKey();
            // // ���������Կ���Ͷ���
            test.sendsms(keyArray);
            // // ���������Կ��ȡ���ж���
            test.getSms(keyArray);
            // // ���������Կ��ȡ״̬����
            test.getSmsReport(keyArray);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * ������������ȡ��Կ���������Կ�ļ���ָ���ļ�
     * 
     * @param keyArray
     * @throws Throwable
     * @author: 8521
     * @date: 2012-9-13 ����07:44:04
     */
    private byte[] getKey() throws Throwable {

        System.out.println("------------------------��ʼ��ȡkey------------------------");
        Document reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("getKey");
        resRoot.addElement("account").setText(account);
        resRoot.addElement("password").setText(MD5.MD5Encode(password));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getKey");
            System.out.println("�������" + reqocument.asXML());
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("getKeyMessage", reqocument.asXML());
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            // �������ӳ�ʱʱ��(��λ����)
            managerParams.setConnectionTimeout(60000);
            // ���ö���ݳ�ʱʱ��(��λ����)
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            if (code == HttpStatus.SC_OK) {
                String res = new String(postMethod.getResponseBodyAsString().trim());
                System.out.println("���ؽ��" + res);
                Document doc = null;
                try {
                    /** ����xml */
                    doc = DocumentHelper.parseText(res);
                    Element rootElement = doc.getRootElement();
                    String keyStr = rootElement.elementText("key");
                    if (keyStr != null) {
                        keyStr = keyStr.replace(' ', '+');
                        BASE64Decoder decoder = new BASE64Decoder();
                        return decoder.decodeBuffer(keyStr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (postMethod != null)
                postMethod.releaseConnection();

        } catch (Exception e) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (postMethod != null)
                postMethod.releaseConnection();
        }
        System.out.println("------------------------�����ȡkey------------------------");
        return null;
    }

    @SuppressWarnings("unused")
	private void testkey() {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] keyArray = decoder.decodeBuffer("ea07c86e8fdfc4e6d39ec89467efc2aeea07c86e8fdfc4e6");

            System.out.println("���ܷ��ؽ��" + Encry_DES3CLt.getInstance().getEncString("abcdefg", keyArray));
            System.out.println("���ܷ��ؽ��" + Encry_DES3CLt.getInstance().getDesString("bX68h0cQZSY=", keyArray));

        } catch (Exception e) {
        	
        }

    }

    private void sendsms(byte[] keyArray) throws Throwable {
        System.out.println("------------------------��ʼ�������ж���------------------------");
        Document reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsMt");
        resRoot.addElement("password").setText(MD5.MD5Encode(password));
        resRoot.addElement("phoneNumber").setText("15000792799,15000792798,15000792797");
        resRoot.addElement("smsType").setText("20");
        resRoot.addElement("priority").setText("9");
        resRoot.addElement("smsId").setText(UUID.randomUUID().toString());
        BASE64Encoder baseEncoder = new BASE64Encoder();
        String content = baseEncoder.encodeBuffer("���Զ�����룬ͬʱָ��ʱ�䷢�ͣ�����ʱ��:012/09/29 11:27".getBytes("UTF-8"));
        resRoot.addElement("content").setText(content);
        resRoot.addElement("subCode").setText("12345");
        resRoot.addElement("sendTime").setText("20120905153540");
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "sendSms");
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("account", account);
            System.out.println("�������" + reqocument.asXML());
            System.out.println("����������ģ�" + Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyArray));
            postMethod.addParameter("smsMtMessage",
                    Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyArray));
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            // �������ӳ�ʱʱ��(��λ����)
            managerParams.setConnectionTimeout(60000);
            // ���ö���ݳ�ʱʱ��(��λ����)
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            if (code == HttpStatus.SC_OK) {
                System.out.println("���ؽ��"
                        + Encry_DES3CLt.getInstance().getDesString(
                                new String(postMethod.getResponseBodyAsString().trim()), keyArray));

            }
            if (postMethod != null)
                postMethod.releaseConnection();

        } catch (Exception e) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (postMethod != null)
                postMethod.releaseConnection();
        }
        System.out.println("------------------------���������ж���------------------------");
    }

    private void getSms(byte[] keyArray) throws Throwable {
        System.out.println("------------------------��ʼ��ȡ���ж���------------------------");
        Document reqocument = DocumentHelper.createDocument();
        reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsMo");
        resRoot.addElement("password").setText(MD5.MD5Encode(password));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getSms");
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("account", account);
            postMethod.addParameter("smsMoMessage",
                    Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyArray));
            System.out.println("�������" + reqocument.asXML());
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            // �������ӳ�ʱʱ��(��λ����)
            managerParams.setConnectionTimeout(60000);
            // ���ö���ݳ�ʱʱ��(��λ����)
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            if (code == HttpStatus.SC_OK) {
                System.out.println("���ؽ��"
                        + Encry_DES3CLt.getInstance().getDesString(
                                new String(postMethod.getResponseBodyAsString().trim()), keyArray));
            }
            if (postMethod != null)
                postMethod.releaseConnection();

        } catch (Exception e) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (postMethod != null)
                postMethod.releaseConnection();
        }
        System.out.println("------------------------�����ȡ���ж���------------------------");
    }

    private void getSmsReport(byte[] keyArray) throws Throwable {
        System.out.println("------------------------��ʼ��ȡ״̬����------------------------");
        Document reqocument = DocumentHelper.createDocument();
        reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsReport");
        resRoot.addElement("password").setText(MD5.MD5Encode(password));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getReport");
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("account", account);
            postMethod.addParameter("smsReportMessage",
                    Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(), keyArray));
            System.out.println("�������" + reqocument.asXML());
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            // �������ӳ�ʱʱ��(��λ����)
            managerParams.setConnectionTimeout(60000);
            // ���ö���ݳ�ʱʱ��(��λ����)
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            if (code == HttpStatus.SC_OK) {
                System.out.println("���ؽ��"
                        + Encry_DES3CLt.getInstance().getDesString(
                                new String(postMethod.getResponseBodyAsString().trim()), keyArray));
            }
            if (postMethod != null)
                postMethod.releaseConnection();

        } catch (Exception e) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (postMethod != null)
                postMethod.releaseConnection();
        }
        System.out.println("------------------------�����ȡ״̬����------------------------");
    }
}