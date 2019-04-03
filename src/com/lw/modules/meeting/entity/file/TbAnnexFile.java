/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.file;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 会议资料Entity
 * @author meijx
 * @version 2019-03-16
 */
public class TbAnnexFile extends DataEntity<TbAnnexFile> {
	
	private static final long serialVersionUID = 1L;
	private String modulecode;		// 业务模块
	private String modulename;		// 业务模块名称
	private String moduleid;		// 模块ID
	private String filename;		// 文件名
	private String filetype;		// 文件类型
	private String path;		// 路径
    private String redirectPath;       // 重定向路劲
	
	public TbAnnexFile() {
		super();
	}

	public TbAnnexFile(String id){
		super(id);
	}

	@Length(min=0, max=2, message="业务模块长度必须介于 0 和 2 之间")
	public String getModulecode() {
		return modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	@Length(min=0, max=200, message="业务模块名称长度必须介于 0 和 200 之间")
	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	
	@Length(min=0, max=32, message="模块ID长度必须介于 0 和 32 之间")
	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	
	@Length(min=0, max=200, message="文件名长度必须介于 0 和 200 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Length(min=0, max=20, message="文件类型长度必须介于 0 和 20 之间")
	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	@Length(min=0, max=400, message="路径长度必须介于 0 和 400 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    public String getRedirectPath() {
        return redirectPath;
    }

    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }
	
}