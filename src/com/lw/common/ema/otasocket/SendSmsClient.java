package com.lw.common.ema.otasocket;

import java.util.Random;

import com.lw.common.ema.otasocket.message.OTACallback;
import com.lw.common.ema.otasocket.message.OTAppChannel;
import com.lw.common.ema.otasocket.message.SubmitMsg;

/**
 * 调用CTC-SMS API客户端接口
 * 
 * @author 8521
 * @version 1.0
 */
public class SendSmsClient {
    private static SendSmsClient instance = null;

    private static OTAppChannel _chan = null;

    public static SendSmsClient getInstance(String host, int port, String user, String passwd) {
        if (instance == null)
            instance = new SendSmsClient(host, port, user, passwd);
        return instance;
    }

    /**
     * 方法描述：初始化通道
     * 
     * @param host
     *            服务端ip
     * @param port
     *            服务端端口
     * @param user
     *            账户
     * @param passwd
     *            密码
     * @param ea
     *            加密类型 0不加密，1des3加密
     * @param keyFilePaht
     *            密钥文件路径（）
     * @author: 8521
     * @date: 2012-4-13 下午03:57:47
     */
    private SendSmsClient(String host, int port, String user, String passwd) {
        if (_chan != null && _chan.isConnected()) {
            return;
        }
        // 相关参数定义
        OTACallback callback = new OTACallback();
        int wndSize = 16;
        int timeout = 60 * 1000; // 连接超时时间
        int trys = 3;
        int speed = 0;
        int maxSinglemsg = 70;
        int longSMSmaxSinglemsg = 67;
        // 开启通道
        _chan = new OTAppChannel(host, port, user, passwd, wndSize, timeout, trys, speed, maxSinglemsg,
                longSMSmaxSinglemsg, callback);
        _chan.start();
    }

    public boolean isActive() {
        int i = 0;
        while (!_chan.isActive() || !_chan.isConnected()) {
            try {
                i++;
                System.out.println("等待通道启动......");
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (i > 20) {
                    System.out.println("通道启动失败,退出");
                    System.exit(0);
                }
            }
        }
        return true;
    }

    /**
     * 下发短信,非线程安全
     * 
     * @param phoneNumber
     *            手机号
     * @param smsContext
     *            下发内容
     * @param smsType
     *            短信类型
     * @param subCode
     *            子码
     * @param priority
     *            优先级
     */
    public void sendSMS(String phoneNumber, String smsContext, String serviceId, String srcTermID, int priority) {
        try {
            SubmitMsg _msg = new SubmitMsg();
            _msg.setDestTermID(phoneNumber);
            _msg.setDestTermIDCount(phoneNumber.split(",").length);
            _msg.setServiceId(serviceId);
            _msg.setPriority(priority);
            _msg.setMsgContent(smsContext);
            _msg.setSrcTermID(srcTermID);
            _msg.setNeedReport(1);
            _msg.setMsgFormat(246);
            _msg.setIdxOfLongSms(1);
            _msg.setCountOfLongSms(1);
            _chan.Submit(_msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryreport(String inmsg) {
        _chan.queryReport(inmsg);
    }

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
        int port = 28850;
        String user = "ota";
        String pass = "ota.com";
        SendSmsClient client = SendSmsClient.getInstance(host, port, user, pass);
        if (client.isActive()) {
            // 发送
            for (int i = 0; i < 1; i++) {
                client.sendSMS("15000792799", "赵", "02", "1234", 5);
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
//            try {
//                Thread.sleep(200);
//                client.queryreport("查询统计");
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
        }

    }
}
