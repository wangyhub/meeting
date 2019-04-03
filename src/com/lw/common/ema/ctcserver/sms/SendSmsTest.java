package com.lw.common.ema.ctcserver.sms;

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
//        SendSmsClient.getInstance().init("127.0.0.1", 28030, "zh", "zh.com");
        SendSmsClient.getInstance().init("112.25.150.108", 28010, "oa", "123.com");

        // 发送 ，02表示短信业务类型标识，有管理员提供
        SendSmsClient.getInstance().sendSMS("18912661598,15000792799", "赵鸿短信测试002", "01");
    }
}
