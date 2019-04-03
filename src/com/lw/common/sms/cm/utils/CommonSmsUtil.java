package com.lw.common.sms.cm.utils;

import com.lw.common.utils.PropertiesLoader;

/**
 * 发短信常量类
 * @author kviuff
 * @version 2015-10-20 15:20:00
 */
public class CommonSmsUtil {
	
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
	/**
	 * 发送短信的请求地址
	 */
	public static final String APIURL = propertiesLoader.getProperty("apiurl");
	/**
	 * 用户账号
	 */
	public static final String AC = propertiesLoader.getProperty("ac");
	/**
	 * 认证密钥
	 */
	public static final String AUTHKEY = propertiesLoader.getProperty("authkey");
	/**
	 * 通道组编号
	 */
	public static final String CGID = propertiesLoader.getProperty("cgid");
	/**
	 * 签名编号
	 */
	public static final String CSID = propertiesLoader.getProperty("csid");

}
