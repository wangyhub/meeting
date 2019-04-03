/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import org.hibernate.validator.constraints.Length;

/**
 * 日历设置Entity
 * @author zhangxudong
 * @version 2015-12-03
 */
public class TBaCalendar{
	
	private String year;		// 年份
	private String hdnwwdata1;		// 1月的工作日
	private String hdnwwdata2;		// 2月的工作日
	private String hdnwwdata3;		// 3月的工作日
	private String hdnwwdata4;		// 4月的工作日
	private String hdnwwdata5;		// 5月的工作日
	private String hdnwwdata6;		// 6月的工作日
	private String hdnwwdata7;		// 7月的工作日
	private String hdnwwdata8;		// 8月的工作日
	private String hdnwwdata9;		// 9月的工作日
	private String hdnwwdata10;		// 10月的工作日
	private String hdnwwdata11;		// 11月的工作日
	private String hdnwwdata12;		// 12月的工作日
	

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=64, message="1月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata1() {
		return hdnwwdata1;
	}

	public void setHdnwwdata1(String hdnwwdata1) {
		this.hdnwwdata1 = hdnwwdata1;
	}
	
	@Length(min=0, max=64, message="2月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata2() {
		return hdnwwdata2;
	}

	public void setHdnwwdata2(String hdnwwdata2) {
		this.hdnwwdata2 = hdnwwdata2;
	}
	
	@Length(min=0, max=64, message="3月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata3() {
		return hdnwwdata3;
	}

	public void setHdnwwdata3(String hdnwwdata3) {
		this.hdnwwdata3 = hdnwwdata3;
	}
	
	@Length(min=0, max=64, message="4月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata4() {
		return hdnwwdata4;
	}

	public void setHdnwwdata4(String hdnwwdata4) {
		this.hdnwwdata4 = hdnwwdata4;
	}
	
	@Length(min=0, max=64, message="5月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata5() {
		return hdnwwdata5;
	}

	public void setHdnwwdata5(String hdnwwdata5) {
		this.hdnwwdata5 = hdnwwdata5;
	}
	
	@Length(min=0, max=64, message="6月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata6() {
		return hdnwwdata6;
	}

	public void setHdnwwdata6(String hdnwwdata6) {
		this.hdnwwdata6 = hdnwwdata6;
	}
	
	@Length(min=0, max=64, message="7月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata7() {
		return hdnwwdata7;
	}

	public void setHdnwwdata7(String hdnwwdata7) {
		this.hdnwwdata7 = hdnwwdata7;
	}
	
	@Length(min=0, max=64, message="8月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata8() {
		return hdnwwdata8;
	}

	public void setHdnwwdata8(String hdnwwdata8) {
		this.hdnwwdata8 = hdnwwdata8;
	}
	
	@Length(min=0, max=64, message="9月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata9() {
		return hdnwwdata9;
	}

	public void setHdnwwdata9(String hdnwwdata9) {
		this.hdnwwdata9 = hdnwwdata9;
	}
	
	@Length(min=0, max=64, message="10月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata10() {
		return hdnwwdata10;
	}

	public void setHdnwwdata10(String hdnwwdata10) {
		this.hdnwwdata10 = hdnwwdata10;
	}
	
	@Length(min=0, max=64, message="11月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata11() {
		return hdnwwdata11;
	}

	public void setHdnwwdata11(String hdnwwdata11) {
		this.hdnwwdata11 = hdnwwdata11;
	}
	
	@Length(min=0, max=64, message="12月的工作日长度必须介于 0 和 64 之间")
	public String getHdnwwdata12() {
		return hdnwwdata12;
	}

	public void setHdnwwdata12(String hdnwwdata12) {
		this.hdnwwdata12 = hdnwwdata12;
	}
	
}