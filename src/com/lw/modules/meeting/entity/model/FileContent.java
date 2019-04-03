package com.lw.modules.meeting.entity.model;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class FileContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String filename;		// 文件名

    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
