package com.lw.common.sms.cm.utils;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.lw.common.sms.cm.httpclient.HttpProtocolHandler;


/**
 * c123发短信接口
 * @author kviuff
 * 
 */
public class C123SendSmsUtil {
	/**
	 * C123短信接口
	 * @param ac:用户账号,authkey:认证密钥,cgid:通道组编号,csid:签名编号,c:短信内容,m:发送号码,如多个以英文逗号分隔
	 * @return 1:操作成功,0:账户格式不正确,-1:服务器拒绝,-2:密钥不正确,-3密钥已锁定,-4:参数不正确
	 * @return -5：无此账户，-6：账户已锁定或已过期，-7：账户未开启接口发送，-8：不可使用该通道组
	 * @return -9账户余额不足，-10：内部错误，-11：扣费失败
	 */
	public String sendSms(String mobiles, String msg) {
		
		Map<String ,String> postData = new HashMap<String ,String>();
		postData.put("ac", CommonSmsUtil.AC);
		postData.put("authkey", CommonSmsUtil.AUTHKEY);
		postData.put("cgid", CommonSmsUtil.CGID);
		postData.put("csid", CommonSmsUtil.CSID);
		postData.put("m", mobiles);
		postData.put("c", msg);
		postData.put("encode", "utf-8");
		String str = "";
		try {
			HttpProtocolHandler httpProto = HttpProtocolHandler.getInstance();
			String content =  httpProto.postHttpClient(CommonSmsUtil.APIURL, "0", postData);
			
			Document document = null;
			document = DocumentHelper.parseText(content);
			Element root = document.getRootElement();
			
			str = root.attributeValue("result");
			System.out.println("短息发送返回信息:" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		C123SendSmsUtil c = new C123SendSmsUtil();
		c.sendSms("18251845003", "我是测试1");
	}
}