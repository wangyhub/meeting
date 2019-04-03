package com.lw.common.ema;

import java.net.URL;

import com.lw.common.ema.common.MD5;
import com.lw.common.ema.jwsserver.sms.ISmsOperator;
import com.lw.common.ema.jwsserver.sms.MtMessage;
import com.lw.common.ema.jwsserver.sms.MtMessageRes;
import com.lw.common.ema.jwsserver.sms.SmsOperatorImpServiceLocator;


public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		   SmsOperatorImpServiceLocator locator = new SmsOperatorImpServiceLocator();
			try {
				//Web Service内网地址，调用服务器的DNS需配置为192.168.2.2，否则需将sms.cnca替换为192.168.5.34
				ISmsOperator ioperator = locator.getSmsOperatorImpPort(new URL("http://192.168.5.34:8080/ema/webService/smsOper?wsdl"));  						
				// 发送短信
				MtMessage msg = new MtMessage();
				msg.setContent("短信测试内容！"); //发送短信内容
				String[] phoneNumber = new String[1];
				phoneNumber[0] = "15105194868";
				msg.setPhoneNumber(phoneNumber);
				MtMessageRes mtMessageRes = ioperator.sendSms("ccc_sp", MD5.MD5Encode("ccc_sp"), "01", msg);  //访问Web Service用户名、密码，01为业务类型号码
				if ("r:000".equals(mtMessageRes.getSubStat())) {
					System.out.println("成功");
				}else{
					System.out.println("失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
