package com.lw.common.ema.ea.sms.socket;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Encoder;

import com.lw.common.ema.ea.sms.socket.message.SubmitMsg;

/**
 * 调用CTC-SMS API客户端接口
 * 
 * @author 8521
 * @version 1.0
 */
public class SendSmsClient {
    private static SendSmsClient instance = null;

    public static EaCtcppChannel _chan = null;

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
     * @param ea
     *            加密类型 0不加密，1des3加密
     * @param keyFilePaht
     *            密钥文件路径（）
     * @author: 8521
     * @date: 2012-4-13 下午03:57:47
     */
    public int init(String host, int port, String user, String passwd, int ea) {
        if (_chan != null && _chan.isConnected()) {
            return 0;
        }
        // 相关参数定义
        EAMSCallback callback = new EAMSCallback();
        int wndSize = 16;
        int timeout = 60 * 1000; // 连接超时时间
        int trys = 3;
        int speed = 0;
        // 开启通道
        _chan = new EaCtcppChannel(host, port, user, passwd, ea, wndSize, timeout, trys, speed, callback);
        return _chan.start();
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
    public void sendSMS(String phoneNumber, String smsContext, String smsType, int subCode, int priority) {
        try {
            SubmitMsg _msg = new SubmitMsg();
            BASE64Encoder baseEncoder = new BASE64Encoder();
            Document resDocument = DocumentHelper.createDocument();
            Element rootElement = resDocument.addElement("body");
            rootElement.addElement("phoneNumber").setText(phoneNumber);
            rootElement.addElement("smsType").setText(smsType);
            rootElement.addElement("priority").setText("" + priority);
            rootElement.addElement("smsId").setText("");
            rootElement.addElement("content").setText(baseEncoder.encode(smsContext.getBytes("utf-8")));
            rootElement.addElement("subCode").setText("" + subCode);
            rootElement.addElement("sendTime").setText("");
            _msg.setSmsMtMessage(rootElement.asXML());
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
            _chan.Submit(_msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
