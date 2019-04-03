package com.lw.common.ema.ea.sms.http;

import java.util.List;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
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
    
    private static final String URL = "http://127.0.0.1:8080/ctc-ema-bestpay/servlet/smsEa";
    private static final String PASSWORD = "zh.com";
    public static void main(String[] args) {
        try {
            Test test = new Test();
             test.sendsms();
             test.getSms();
             test.getSmsReport();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    private void sendsms() throws Throwable {
        System.out.println("------------------------��ʼ�������ж���------------------------");
        Document reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsMt");
        resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
        resRoot.addElement("phoneNumber").setText("13111111111");
        resRoot.addElement("smsType").setText("20");
        resRoot.addElement("priority").setText("9");
        resRoot.addElement("smsId").setText(UUID.randomUUID().toString());
        BASE64Encoder baseEncoder = new BASE64Encoder();
        String content = baseEncoder.encodeBuffer("���Զ�����룬ͬʱָ��ʱ�䷢�ͣ�����ʱ��:012/09/29 11:27".getBytes("utf-8"));
        resRoot.addElement("content").setText(content);
        resRoot.addElement("subCode").setText("12345");
        resRoot.addElement("sendTime").setText("20120905153540");
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(URL);
//            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
            postMethod.addParameter("postType", "sendSms");
            postMethod.addParameter("ea", "0");
            postMethod.addParameter("account", "zh");
            System.out.println("�������" + reqocument.asXML());
            postMethod.addParameter("smsMtMessage", reqocument.asXML());
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            // �������ӳ�ʱʱ��(��λ����)
            managerParams.setConnectionTimeout(60000);
            // ���ö���ݳ�ʱʱ��(��λ����)
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            if (code == HttpStatus.SC_OK) {
                System.out.println("���ؽ��" + new String(postMethod.getResponseBodyAsString().trim()));

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

    private void getSms() throws Throwable {
        System.out.println("------------------------��ʼ��ȡ���ж���------------------------");
        Document reqocument = DocumentHelper.createDocument();
        reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsMo");
        resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(URL);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getSms");
            postMethod.addParameter("ea", "0");
            postMethod.addParameter("account", "zh");
            postMethod.addParameter("smsMoMessage", reqocument.asXML());
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
                String res = new String(postMethod.getResponseBodyAsString().trim());
                System.out.println("���ؽ��" + res);
                
                try {
                    Document doc = DocumentHelper.parseText(res);
                    Element rootElement = doc.getRootElement();
                    @SuppressWarnings("unchecked")
					List<Element> smsElement= rootElement.elements("sms");
                   BASE64Decoder decoder = new BASE64Decoder();
                    for (Element element : smsElement) {
                        String content = element.elementText("content");
                        if (StringUtils.isNotBlank(content)) {
                            content = content.replace(' ', '+');
                            content = new String(decoder.decodeBuffer(content), "utf-8");
                        }
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
        System.out.println("------------------------�����ȡ���ж���------------------------");
    }

    private void getSmsReport() throws Throwable {
        System.out.println("------------------------��ʼ��ȡ״̬����------------------------");
        Document reqocument = DocumentHelper.createDocument();
        reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsReport");
        resRoot.addElement("password").setText(MD5.MD5Encode(PASSWORD));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(URL);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getReport");
            postMethod.addParameter("ea", "0");
            postMethod.addParameter("account", "zh");
            postMethod.addParameter("smsReportMessage", reqocument.asXML());
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
                System.out.println("���ؽ��" + new String(postMethod.getResponseBodyAsString().trim()));
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