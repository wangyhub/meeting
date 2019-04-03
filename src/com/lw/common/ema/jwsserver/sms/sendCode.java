package com.lw.common.ema.jwsserver.sms;
import java.net.URL;

import com.lw.common.ema.common.MD5;

public class sendCode {
   public int sendSms(String code, String moveTel){
	   int i =1;
	   SmsOperatorImpServiceLocator locator = new SmsOperatorImpServiceLocator();
		try {
			ISmsOperator ioperator = locator
					.getSmsOperatorImpPort(new URL(
							"http://192.168.5.34:8080/ema/webService/smsOper?wsdl"));  
							//Web Service内网地址，调用服务器的DNS需配置为192.168.2.2，否则需将sms.cnca替换为192.168.5.34
			// 发送短信
			MtMessage msg = new MtMessage();
			msg.setContent("强制性产品认证指定机构/实验室审批系统用户注册验证码："+code+"，有效时间为30分钟。"); //发送短信内容
			//msg.setSubCode("145");   //子号码：0-3位
			String[] phoneNumber = new String[1];
			phoneNumber[0] = moveTel;
			msg.setPhoneNumber(phoneNumber);
			MtMessageRes mtMessageRes = ioperator.sendSms("ccc_sp", MD5.MD5Encode("ccc_sp"), "01", msg);  //访问Web Service用户名、密码，01为业务类型号码
			if ("r:000".equals(mtMessageRes.getSubStat())) {
				i=1;
			}else{
				i=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return i;
   }
}
