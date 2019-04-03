package com.lw.common.ema.ea.sms.socket;

public class SendSmsTest {
    /**
     * 方法描述：main方法入口
     * 
     * @param args
     * @author: 8521
     * @date: 2012-6-11 下午01:56:02
     */
    public static void main(String[] args) {
        // 初始化一次就可以,单例模式
        String host = "127.0.0.1";
        int port= 28080;
        String user  ="zh";
        String pass = "zh.com";
        int ea = 1;
        SendSmsClient.getInstance().init(host, port, user, pass,ea);
        // 发送
        for (int i = 0; i < 1; i++) {
            SendSmsClient.getInstance().sendSMS("15000792799,15000792798,13282892222", "短信测试", "02", 1234, 9);
        }

    }
}
