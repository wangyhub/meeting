package com.lw.common.ema.jwsserver.smsnotify;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 类描述：保存接口短信参数
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-16 上午10:47:44
 */
@XmlRootElement(name = "com.ctc.ema.jwsserver.notify.sms.ReportMessageResDetail")
public class ReportMessageResDetail {
	/**
	 * 短信标识
	 */
	private String smsId;

	/**
	 * 手机号码
	 */
	private String phoneNumber;

	/**
	 * 提交状态
	 */
	private String stat;

	/**
	 * 状态描述
	 */
	private String statDes;

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	/**
	 * @return phoneNumber : return the property phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            : set the property phoneNumber.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return stat : return the property stat.
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            : set the property stat.
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return statDes : return the property statDes.
	 */
	public String getStatDes() {
		return statDes;
	}

	/**
	 * @param statDes
	 *            : set the property statDes.
	 */
	public void setStatDes(String statDes) {
		this.statDes = statDes;
	}

}
