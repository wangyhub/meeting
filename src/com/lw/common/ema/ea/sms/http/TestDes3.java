package com.lw.common.ema.ea.sms.http;

import java.io.File;
import java.io.FileOutputStream;
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
public class TestDes3 {
    private static final String url = "http://127.0.0.1:8080/ctc-emassh60/servlet/smsEa";
    public static void main(String[] args) {
        try {
            TestDes3 test = new TestDes3();
            String keyFile = "D:\\key4.dat";
            test.getKey(keyFile);
            test.sendsms(keyFile);
            test.getSms(keyFile);
            test.getSmsReport(keyFile);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * ������������ȡ��Կ���������Կ�ļ���ָ���ļ�
     * 
     * @param keyFile
     * @throws Throwable
     * @author: 8521
     * @date: 2012-9-13 ����07:44:04
     */
    private void getKey(String keyFile) throws Throwable {

        System.out.println("------------------------��ʼ��ȡkey------------------------");
        Document reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("getKey");
        resRoot.addElement("account").setText("zh");
        resRoot.addElement("password").setText(MD5.MD5Encode("zh.com"));
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
                        FileOutputStream fos = new FileOutputStream(new File(keyFile));
                        BASE64Decoder decoder = new BASE64Decoder();
                        fos.write(decoder.decodeBuffer(keyStr));
                        fos.close();
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

    }

    private void sendsms(String keyFile) throws Throwable {
        System.out.println("------------------------��ʼ�������ж���------------------------");
        Document reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsMt");
        resRoot.addElement("password").setText(MD5.MD5Encode("zh.com"));
        resRoot.addElement("phoneNumber").setText("13111111111,13222222222,13333333333");
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
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "sendSms");
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("account", "zh");
            System.out.println("�������" + reqocument.asXML());
            postMethod.addParameter("smsMtMessage", Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(),
                    keyFile));
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
                                new String(postMethod.getResponseBodyAsString().trim()), keyFile));

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

    private void getSms(String keyFile) throws Throwable {
        System.out.println("------------------------��ʼ��ȡ���ж���------------------------");
        Document reqocument = DocumentHelper.createDocument();
        reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsMo");
        resRoot.addElement("password").setText(MD5.MD5Encode("zh.com"));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getSms");
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("account", "zh");
            postMethod.addParameter("smsMoMessage", Encry_DES3CLt.getInstance().getEncString(reqocument.asXML(),
                    keyFile));
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
                                new String(postMethod.getResponseBodyAsString().trim()), keyFile));
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

    private void getSmsReport(String keyFile) throws Throwable {
        System.out.println("------------------------��ʼ��ȡ״̬����------------------------");
        Document reqocument = DocumentHelper.createDocument();
        reqocument = DocumentHelper.createDocument();
        Element resRoot = reqocument.addElement("smsReport");
        resRoot.addElement("password").setText(MD5.MD5Encode("zh.com"));
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("postType", "getReport");
            postMethod.addParameter("ea", "1");
            postMethod.addParameter("account", "zh");
            postMethod.addParameter("smsReportMessage", Encry_DES3CLt.getInstance().getEncString(
                    reqocument.asXML(), keyFile));
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
                                new String(postMethod.getResponseBodyAsString().trim()), keyFile));
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