/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lw.common.web.BaseController;

/**
 * 功能：文件的上传功能
 * 说明： 1、实现FTP文件标签的增删改查
 *      2、实现本地文件的增删改查
 *      3、实现FTP图片文件的增删改查
 * 创建时间：2016-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/UploadFileDemo")
public class UploadFileDemoController extends BaseController {

    /**
     * 功能：跳转到演示页面
     * 具体的实现功能，请参照如下文件：
     * 文件一、说明：后台控制文件。        路径：/lwbase/src/com/lw/modules/base/web/TBaFileController.java 
     * 文件二、说明：FTP文件上传标签。 路径：/lwbase/WebRoot/WEB-INF/tags/sys/uploadfile.tag
     * 文件三、说明：本地文件上传标签。 路径：/lwbase/WebRoot/WEB-INF/tags/sys/uploadLocalfile.tag
     * 文件四、说明：FTP图片上传标签。 路径：/lwbase/WebRoot/WEB-INF/tags/sys/uploadImage.tag
     * @return
     */
	@RequestMapping(value = "form")
	public String form() {
		return "modules/demo/UploadFileDemoForm";
	}	
	
}