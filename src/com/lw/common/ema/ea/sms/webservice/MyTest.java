package com.lw.common.ema.ea.sms.webservice;

import java.net.URL;

import com.lw.common.ema.jwsserver.sms.ISmsOperator;
import com.lw.common.ema.jwsserver.sms.MtMessage;
import com.lw.common.ema.jwsserver.sms.MtMessageRes;
import com.lw.common.ema.jwsserver.sms.SmsOperatorImpServiceLocator;

public class MyTest {
	public static void main(String[] args) {
		SmsOperatorImpServiceLocator locator=new SmsOperatorImpServiceLocator();
		try{
			ISmsOperator ioperator=locator.getSmsOperatorImpPort(new URL(
					"http://192.168.5.34:8080/ema/webService/smsOper?wsdl"
					));
			System.out.println("===========begin sending message===========");
			MtMessage msg =new MtMessage();
			msg.setContent("���Զ���");
			msg.setSubCode("145");
			msg.setPhoneNumber(new String[]{"15251857925"});
			MtMessageRes mtMessageRes=ioperator.sendSms("cefpr", "3tYN(iz8", "03", msg);
			if("r:000".equals(mtMessageRes.getSubStat())){
				System.out.println("����ɹ�!"+mtMessageRes.getSubStat()+";������"+mtMessageRes.getSubStatDes());
				System.out.println("�����ύ������ˮ�ţ�"+mtMessageRes.getSmsId());
			}else{
				System.out.println("fail , wrong number is:"+mtMessageRes.getSubStat()+";������"+mtMessageRes.getSubStatDes());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
