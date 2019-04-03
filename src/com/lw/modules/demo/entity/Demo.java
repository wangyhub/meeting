/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lw.modules.demo.entity;


import com.lw.common.persistence.DataEntity;
import com.lw.modules.gen.entity.GenScheme;


/**
 * demo
 * @author nanking
 * @version 2015-7-31
 */
public class Demo extends DataEntity<GenScheme> {
	
	private static final long serialVersionUID = 1L;

	
	public Demo(String id){
		super(id);
	}
	
}