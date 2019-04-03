package com.lw.modules.meeting.common;

import com.lw.common.config.Global;
import com.lw.common.ema.common.MD5;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmsHandle {
    private static String[] BEFORESHUFFLE=new String[]{"1","2", "3", "4", "5", "6", "7",  "8", "9","0"};

    public static String getSmsMessage(String message, String phoneNumber){
        StringBuffer messageBuffer = new StringBuffer()
                .append("<com.ctc.ema.server.jwsserver.sms.MtMessage><content>")
                .append(message)
                .append("</content>")
                .append("<phoneNumber>")
                .append(phoneNumber)
                .append("</phoneNumber><smsId></smsId><subCode></subCode>")
                .append("</com.ctc.ema.server.jwsserver.sms.MtMessage>");
        return messageBuffer.toString();
    }

    public static String sendSms( String message) {
        String status = "1";
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(Global.getConfig("smsUrl"));
            postMethod.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("account", Global.getConfig("smsAccount"));
            postMethod.addParameter("password", MD5.MD5Encode(Global.getConfig("smsPassword")));
            postMethod.addParameter("smsType", Global.getConfig("smsType"));
            postMethod.addParameter("message", message);
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(60000);
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            String _sedrespContent = null;
            if (code == HttpStatus.SC_OK) {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        resStream, "utf-8"));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                _sedrespContent = resBuffer.toString();
            }
            if (_sedrespContent != null) {
                //log.info("\n\n Send Res:" + _sedrespContent);
            }
        } catch (Throwable e) {
            status = "0";
            e.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }

        return status;
    }

    public static String getSmsCode(){
        List list = Arrays.asList(BEFORESHUFFLE);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }
}
