/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;
import com.lw.modules.sys.entity.Dict;

/**
 * 参数配置Entity
 * @author handf
 * @version 2015-08-11
 */
public class TBaParameter extends DataEntity<TBaParameter> {
	
	private static final long serialVersionUID = 1L;
	private int sort = 0;		// 顺序
	private String useState;    // 使用状态
	private String paraValue;   // 参数值
	private String paraKey;		// 参数键
	private Dict tempId;		// 模板名称
	private String paraName;	// 参数名称
	private Dict paraType;	    // 参数类型
	
	public TBaParameter() {
		super();
	}

	public TBaParameter(String id){
		super(id);
	}
	
	@Length(min=0, max=4, message="使用状态长度必须介于 0 和 4 之间")
	public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState;
    }

    @Length(min=0, max=100, message="参数值长度必须介于 0 和 100 之间")
	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
	@Length(min=0, max=100, message="参数键长度必须介于 0 和 100 之间")
	public String getParaKey() {
		return paraKey;
	}

	public void setParaKey(String paraKey) {
		this.paraKey = paraKey;
	}
	
	@Length(min=0, max=32, message="参数名称长度必须介于 0 和 32 之间")
	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Dict getTempId() {
        return tempId;
    }

    public void setTempId(Dict tempId) {
        this.tempId = tempId;
    }

    public Dict getParaType() {
        return paraType;
    }

    public void setParaType(Dict paraType) {
        this.paraType = paraType;
    }
	
}