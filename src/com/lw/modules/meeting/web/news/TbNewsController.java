/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.entity.news.TbNews;
import com.lw.modules.meeting.service.news.TbNewsService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新闻动态Controller
 * @author meijx
 * @version 2019-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/news/tbNews")
public class TbNewsController extends BaseController {

	@Autowired
	private TbNewsService tbNewsService;
	
	@ModelAttribute
	public TbNews get(@RequestParam(required=false) String id) {
		TbNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbNewsService.get(id);
		}
		if (entity == null){
			entity = new TbNews();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbNews tbNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbNews.setMeetingId((String) meetingId);
			Page<TbNews> page = tbNewsService.findPage(new Page<TbNews>(request, response), tbNews);
			model.addAttribute("page", page);
		}
		return "modules/meeting/news/tbNewsList";
	}

	@RequestMapping(value = "form")
	public String form(TbNews tbNews, Model model) {
		model.addAttribute("tbNews", tbNews);
		return "modules/meeting/news/tbNewsForm";
	}

	@RequestMapping(value = "save")
	public String save(TbNews tbNews, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbNews.setMeetingId((String) meetingId);
			tbNewsService.save(tbNews);
			addMessage(redirectAttributes, "保存新闻动态成功");
		}
		else{
			addMessage(redirectAttributes, "保存新闻动态失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/news/tbNews/?repage";
	}

	@RequestMapping(value = "submit")
	public String submit(TbNews tbNews, RedirectAttributes redirectAttributes) {
		if ("1".equals(tbNews.getStatus())) {
			tbNewsService.updateStatus(tbNews);
		}

		addMessage(redirectAttributes, "发布新闻动态成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/news/tbNews/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(TbNews tbNews, RedirectAttributes redirectAttributes) {
		tbNewsService.delete(tbNews);
		addMessage(redirectAttributes, "删除新闻动态成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/news/tbNews/?repage";
	}

	@RequestMapping(value = "uploadJson")
	public @ResponseBody String uploadJson(@RequestParam("imgFile") MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response) {
        JSONObject obj = new JSONObject();
        try {
            //定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            //最大文件大小
            long maxSize = 1000000;
            if(imgFile.getSize() > maxSize){
                obj.put("error", 1);
                obj.put("message", "上传文件大小超过1MB限制。");
                return obj.toString();
            }

            String fileName = imgFile.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if(!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)){
                obj.put("error", 1);
                obj.put("message", "只允许上传"+ extMap.get("image") + "格式。");
                return obj.toString();
            }

            String path = "/attached/";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ymd = sdf.format(new Date());
            path += ymd+"/";

            // 获取绝对路径
            String realpath = request.getServletContext().getRealPath("/") + path;
            File dirFile = new File(realpath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
			System.out.println(realpath);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            // 上传文件
            imgFile.transferTo(new File(realpath, newFileName));

            obj.put("error", 0);
            obj.put("url", request.getServletContext().getContextPath() + path +newFileName);

        } catch (Exception e) {
            obj.put("error", 1);
            obj.put("message", "上传图片出错！");
        }

        return obj.toString();

	}

}