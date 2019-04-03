/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 附件管理Entity
 * @author handf
 * @version 2015-08-10
 */
public class TBaFile extends DataEntity<TBaFile> {
	
	private static final long serialVersionUID = 1L;
	private String fileName;    // 附件标题
	private String fileSize;    // 附件大小
	private String fileType;	// 附件类型
	private String folderPath;  // 文件夹路径
	private String isMarge;    // 01 合成成功 02 合成失败
	
	public TBaFile() {
		super();
	}

	public TBaFile(String id){
		super(id);
	}

	@Length(min=0, max=600, message="附件标题长度必须介于 0 和 600 之间")
	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getIsMarge() {
		return isMarge;
	}

	public void setIsMarge(String isMarge) {
		this.isMarge = isMarge;
	}

}