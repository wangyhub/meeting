/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.modules.sys.entity.Dict;
import com.lw.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.lw.common.persistence.DataEntity;

/**
 * 附近配置Entity
 * @author handf
 * @version 2015-08-09
 */
public class TBaAtt extends DataEntity<TBaAtt> {
	
	private static final long serialVersionUID = 1L;
	private String useState;	        // 使用状态：0启用 1停用
	private Office office;		        // 部门类型
	private Dict type;                  // 附件类型
	private String fileId;              // 附件
	private int sort = 0;		        // 顺序
	private Double isrequired;          // 是否必填0否1必填
	private String name;		        // 名称
	
	public TBaAtt() {
		super();
	}

	public TBaAtt(String id){
		super(id);
	}

	@Length(min=0, max=4, message="使用状态：0启用 1停用长度必须介于 0 和 4 之间")
	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}
	
	@NotNull(message="部门类型不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Dict getType() {
        return type;
    }

    public void setType(Dict type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Double getIsrequired() {
		return isrequired;
	}

	public void setIsrequired(Double isrequired) {
		this.isrequired = isrequired;
	}
	
	@Length(min=0, max=600, message="附件标题长度必须介于 0 和 600 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}