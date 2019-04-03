package com.lw.common.ema.ctcserver.sms;

import com.chinatricom.smsclient.ctcpp.CtcppChannel;
import com.chinatricom.smsclient.message.SubmitMsg;

/**
 * 调用CTC-SMS API客户端接口
 * 
 * @author 8521
 * @version 1.0
 */
public class SendSmsClient {
    private static SendSmsClient instance = null;

    public static CtcppChannel _chan = null;

    private SendSmsClient() {
    }

    public static SendSmsClient getInstance() {
        if (instance == null)
            instance = new SendSmsClient();
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
     * @author: 8521
     * @date: 2012-4-13 下午03:57:47
     */
    public int init(String host, int port, String user, String passwd) {
        if (_chan != null && _chan.isConnected()) {
            return 0;
        }
        // 相关参数定义
        CqBSMSCallback callback = new CqBSMSCallback();
        int wndSize = 16;
        int timeout = 60 * 1000; // 连接超时时间
        int trys = 3;
        int speed = 0;
        // 开启通道
        _chan = new CtcppChannel(host, port, user, passwd, 1, wndSize, timeout, trys, speed, callback, 0, 0, 1, 1);
        return _chan.start();
    }

    /**
     * 方法描述：重启通道通道
     * 
     * @param host
     *            服务端ip
     * @param port
     *            服务端端口
     * @param user
     *            账户
     * @param passwd
     *            密码
     * @author: 8521
     * @date: 2012-4-13 下午03:57:47
     */
    public int reset(String host, int port, String user, String passwd) {
        if (_chan != null && _chan.isConnected()) {
            _chan.stop();
        }
        return init(host, port, user, passwd);
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
     */
    public void sendSMS(String phoneNumber, String smsContext, String smsType) {
        try {
            SubmitMsg _msg = new SubmitMsg();
            _msg.setTo(phoneNumber);
            _msg.setMsg(smsContext);
            _msg.setFrom("12345");
            _msg.setServiceId("");
            int i = 0;
            while (!_chan.isConnected()) {
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
            _chan.submit(_msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
