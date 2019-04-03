package com.lw.common.ema.http.sms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.lw.common.ema.common.MD5;

/**
 * 
 * 获取上行短信
 * @author 8521
 * @version 1.0
 */
public class GetSmsClient {
    /** log4J*/
    private static final Logger log = Logger.getLogger(GetSmsClient.class);

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/ctc-emassh60/httpSms/getSms";
        String account = "zh";
        String password = "zh.com";
        try {
            getSms(url, account, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSms(String url, String account, String password) {
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.addParameter("account", account);
            postMethod.addParameter("password", MD5.MD5Encode(password));
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(60000);
            managerParams.setSoTimeout(120000);
            httpClient.executeMethod(postMethod);
            int code = postMethod.getStatusCode();
            String _sedrespContent = null;
            if (code == HttpStatus.SC_OK) {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"utf-8"));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                _sedrespContent = resBuffer.toString();
            }
            if (_sedrespContent != null) {
                log.info("\n\n get Res:" + _sedrespContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("e :" + e);
        } finally {

            if (postMethod != null)
                postMethod.releaseConnection();

        }

    }
}
