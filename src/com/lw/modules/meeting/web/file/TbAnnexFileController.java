/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.entity.file.TbAnnexFile;
import com.lw.modules.meeting.service.file.TbAnnexFileService;

import java.io.*;

/**
 * 会议资料Controller
 * @author meijx
 * @version 2019-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/file/tbAnnexFile")
public class TbAnnexFileController extends BaseController {

	@Autowired
	private TbAnnexFileService tbAnnexFileService;

    /**
     * 附件上传
     * @param request
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "fileUpload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile file, TbAnnexFile tbAnnexFile, HttpServletRequest request) throws Exception {
        tbAnnexFileService.save(file, tbAnnexFile.getModuleid(), tbAnnexFile.getModulecode(), tbAnnexFile.getModulename());
        return  "redirect:"+Global.getAdminPath() + tbAnnexFile.getRedirectPath();
    }

    /**
     * 附件删除
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "fileDelete")
    public String fileDelete(TbAnnexFile tbAnnexFile, HttpServletRequest request, Model model) throws Exception {
        String redirectPath = request.getParameter("redirectPath");
        tbAnnexFileService.delete(tbAnnexFile);
        return "redirect:"+Global.getAdminPath() + redirectPath;
    }

    @RequestMapping("/fileDownload")
    public String fileDownload(TbAnnexFile tbAnnexFile,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        String downLoadPath = tbAnnexFile.getPath();  //注意不同系统的分隔符
        System.out.println(downLoadPath);
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(tbAnnexFile.getFilename().getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

	@ModelAttribute
	public TbAnnexFile get(@RequestParam(required=false) String id) {
		TbAnnexFile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbAnnexFileService.get(id);
		}
		if (entity == null){
			entity = new TbAnnexFile();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbAnnexFile tbAnnexFile, HttpServletRequest request, HttpServletResponse response, Model model) {
        Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
        if(meetingId != null) {
            tbAnnexFile.setModuleid((String)meetingId);
            Page<TbAnnexFile> page = tbAnnexFileService.findPage(new Page<TbAnnexFile>(request, response), tbAnnexFile);
            model.addAttribute("page", page);
        }
		return "modules/meeting/file/tbAnnexFileList";
	}

	@RequestMapping(value = "form")
	public String form(TbAnnexFile tbAnnexFile, Model model) {
		model.addAttribute("tbAnnexFile", tbAnnexFile);
		return "modules/meeting/file/tbAnnexFileForm";
	}

	@RequestMapping(value = "save")
	public String save(TbAnnexFile tbAnnexFile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbAnnexFile)){
			return form(tbAnnexFile, model);
		}
		tbAnnexFileService.save(tbAnnexFile);
		addMessage(redirectAttributes, "保存会议资料成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/file/tbAnnexFile/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbAnnexFile tbAnnexFile, RedirectAttributes redirectAttributes) {
		tbAnnexFileService.delete(tbAnnexFile);
		addMessage(redirectAttributes, "删除会议资料成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/file/tbAnnexFile/?repage";
	}

}