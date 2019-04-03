/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.mobile;

import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.entity.meet.TbMeetingAgenda;
import com.lw.modules.meeting.entity.model.*;
import com.lw.modules.meeting.entity.news.TbNews;
import com.lw.modules.meeting.service.file.TbAnnexFileService;
import com.lw.modules.meeting.service.meal.TbMealService;
import com.lw.modules.meeting.service.meet.*;
import com.lw.modules.meeting.service.message.TbMessageRecordService;
import com.lw.modules.meeting.service.message.TbValidateCodeService;
import com.lw.modules.meeting.service.news.TbNewsService;
import com.lw.modules.meeting.service.stay.TbStayService;
import com.lw.modules.meeting.service.trip.TbTripService;
import com.lw.modules.meeting.service.user.TbUserService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 参会用户Controller
 * @author meijx
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/mobile/tbMobile")
public class TbMobileController extends BaseController {

	@Autowired
	private TbMeetingAgendaService tbMeetingAgendaService;
	@Autowired
	private TbUserService tbUserService;
	@Autowired
	private TbAnnexFileService tbAnnexFileService;
	@Autowired
	private TbNewsService tbNewsService;
	@Autowired
	private TbMealService tbMealService;
	@Autowired
	private TbMessageRecordService tbMessageRecordService;
	@Autowired
	private TbInviteCodeService tbInviteCodeService;
	@Autowired
	private TbMeetingService tbMeetingService;
	@Autowired
	private TbTripService  tbTripService;
	@Autowired
	private TbMeetingPlaceService tbMeetingPlaceService;
	@Autowired
	private TbJoinService tbJoinService;
	@Autowired
	TbStayService tbStayService;
	@Autowired
	private TbValidateCodeService tbValidateCodeService;


	@RequestMapping(value = "lookNews")
	public String lookNews(@RequestParam(required=true) String id, Model model) {
		if (StringUtils.isNotBlank(id)){
			TbNews tbNews = tbNewsService.get(id);
			model.addAttribute("context", StringEscapeUtils.unescapeHtml4(tbNews.getContent()));
		}

		return "modules/meeting/news/tbNewsFormView";
	}

	@ResponseBody
	@RequestMapping(value = "findNewsDesc")
	public String findNewsDesc(String id, Model model,@RequestParam(required=true) String callback) {
		String result = null;
		if (StringUtils.isNotBlank(id)){
			TbNewsContent tbNewsContent = 	tbNewsService.findById(id);
			String content = StringEscapeUtils.unescapeHtml4(tbNewsContent.getContent());
			String locatHost = "http://127.0.0.1:8080";
			String context = content.replace("/meeting/attached",locatHost);
			tbNewsContent.setContent(context);
			JSONObject json = JSONObject.fromObject(AppResult.ok(tbNewsContent));
			 result = tbNewsContent + "(" + json.toString() + ")";
		}
			return result;
	}


	@ResponseBody
	@RequestMapping(value = "getDemo")
	public TbMeetingAgenda getDemo(@RequestParam(required=true) String id) {
		TbMeetingAgenda entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMeetingAgendaService.get(id);
		}
		if (entity == null){
			entity = new TbMeetingAgenda();
		}
		return entity;
	}

	/**
	 * 发送验证码
	 */
	@ResponseBody
	@RequestMapping(value = "findCode")
	public String findCode(@RequestParam(required=true) String phone, @RequestParam(required=true) String callback){
		AppResult appResult = tbValidateCodeService.sendCode(phone);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/**
	 * 用户登录验证，同时回传登录用户的手机号、id、参加的会议名称
	 */
	@ResponseBody
	@RequestMapping(value = "checkUser")
	public String checkUser(@RequestParam(required=true) String phone, @RequestParam(required=true) String callback){
		AppResult appResult = tbUserService.findByPhone(phone);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/*资料列表*/
	@ResponseBody
	@RequestMapping("findFileList")
	public String findFileList(String meetId, @RequestParam(required=true) String callback){
		List<FileContent> tbAnnexFileList =  tbAnnexFileService.findAAA(meetId);
		JSONObject json = JSONObject.fromObject(AppResult.ok(tbAnnexFileList));
		String result = callback + "(" + json.toString() + ")";
		return result;
	}


	/*资料下载*/
	@RequestMapping("fileDownLoad")
	public void dowmLoad(@RequestParam(required=true) String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		tbAnnexFileService.appDownFile(id, request, response);
	}

	/*新闻列表查询*/
	@ResponseBody
	@RequestMapping("/findNewsList")
	public String findNews(String newsId,@RequestParam(required=true) String callback){

		List<TbNewsContent> tbNewsContentList = tbNewsService.findAll(newsId);

		JSONObject json = JSONObject.fromObject(AppResult.ok(tbNewsContentList));

		String result = callback + "(" + json.toString() + ")";

		return result;
	}

	/*新闻详细信息*/
	@ResponseBody
	@RequestMapping("/findNewsDetails")
	public String findNewsDetails(String id,@RequestParam(required=true) String callback){

		TbNewsContent tbNewsContent = 	tbNewsService.findById(id);
		String content = StringEscapeUtils.unescapeHtml4(tbNewsContent.getContent());
		String context = content.replace("/meeting/attached", AppConstant.LOCAL_HOST).replace("<img","<img style=\"max-width: 98%;max-height: 100%\" ");
		tbNewsContent.setContent(context);
		JSONObject json = JSONObject.fromObject(AppResult.ok(tbNewsContent));
		String result = callback + "(" + json.toString() + ")";
		return result;
	}


	@ResponseBody
	@RequestMapping(value="getAgendaList")
	public String getAgendaByMeetingId(@RequestParam(required=true)String meetingId, @RequestParam(required=true)String callback){
		System.out.println(">>>meetingId:"+meetingId);
		AppResult appResult = tbMeetingAgendaService.getAgendaList(meetingId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println(">>>result:"+result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value="getAgendaInfo")
	public String getAgendaInfoByAgendaid(@RequestParam(required=true)String agendaId, @RequestParam(required=true)String callback){
		System.out.println(">>>agendaId:"+agendaId);
		AppResult appResult = tbMeetingAgendaService.getAgendaInfo(agendaId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println(">>>result:"+result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value="getMealList")
	public String getMealList(@RequestParam(required=true)String meetingId, @RequestParam(required=true)String userId, @RequestParam(required=true)String callback){
		System.out.println(">>>meetingId:"+meetingId);
		AppResult appResult =  tbMealService.getMealList(meetingId,userId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println(">>>result:"+result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value="getMealTable")
	public String getMealTable(@RequestParam(required=true)String mealId,@RequestParam(required=true)String userId,@RequestParam(required=true)String callback){
		System.out.println("mealId:"+mealId);
		AppResult appResult =  tbMealService.getMealTable(mealId, userId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println(">>>result:"+result);
		return result;
	}

	/**
	 * 消息列表接口
	 */
	@ResponseBody
	@RequestMapping(value = "messageList")
	public String messageList(@ModelAttribute("paramsModel")ParamsModel paramsModel, @RequestParam(required=true)
			String callback){
		paramsModel.setCurrentPage(paramsModel.getCurrentPage());
		AppResult appResult = tbMessageRecordService.findUserMessage(paramsModel);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/**
	 * 消息是否有未读
	 */
	@ResponseBody
	@RequestMapping(value = "messageNotRead")
	public String messageNotRead(@ModelAttribute("paramsModel")ParamsModel paramsModel, @RequestParam(required=true)
			String callback){
		AppResult appResult = tbMessageRecordService.findNotRead(paramsModel);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/**
	 * 根据messageId将消息设为已读
	 */
	@ResponseBody
	@RequestMapping(value = "messageIsRead")
	public String messageIsRead(@ModelAttribute("paramsModel")ParamsModel paramsModel, @RequestParam(required =true)
			String callback){
		AppResult appResult = tbMessageRecordService.isRead(paramsModel);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/**
	 * 根据会议码校验并绑定会议
	 */
	@ResponseBody
	@RequestMapping(value = "checkMeeting")
	public String checkMeeting(@ModelAttribute("paramsModel")ParamsModel paramsModel, @RequestParam(required =true)
			String callback){
		AppResult appResult = tbInviteCodeService.checkMeeting(paramsModel);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/**
	 * 用户绑定会议码
	 */
	@ResponseBody
	@RequestMapping(value = "bindingCode")
	public String bindingCode(@ModelAttribute("paramsModel")ParamsModel paramsModel, @RequestParam(required =true)
			String callback){
		AppResult appResult = tbInviteCodeService.bindingCode(paramsModel);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/**
	 * 大会地图接口
	 */
	@ResponseBody
	@RequestMapping(value = "meetingMap")
	public String meetingMap(@RequestParam(required =true) String meetingId, @RequestParam(required =true)
			String callback){
		AppResult appResult = tbMeetingService.showMeetingMap(meetingId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		return result;
	}

	/*接送信息接口*/
	@ResponseBody
	@RequestMapping("/tripList")
	public String findTripList(String tripId,@RequestParam(required=true) String callback){

		List<TbTripContent> tripContentList =  tbTripService.findAll(tripId);

		JSONObject json = JSONObject.fromObject(AppResult.ok(tripContentList));

		String result = callback + "(" + json.toString() + ")";

		return result;
	}

	/*会场座次列表*/
	@ResponseBody
	@RequestMapping("/seatList")
	public String  findseatList(String seatId,@RequestParam(required=true) String callback){

		List<AgendaInfo> tbSeatContentList = tbMeetingPlaceService.findAll(seatId);

		JSONObject json = JSONObject.fromObject(AppResult.ok(tbSeatContentList));

		String result = callback + "(" + json.toString() + ")";

		return result;
	}


	/*会场座次详细信息*/
	@ResponseBody
	@RequestMapping("/seatDetails")
	public String findSearDetails(@RequestParam(required =true) String id, @RequestParam(required =true) String userId, @RequestParam(required=true) String callback){

		TbSeatContent tbSeatContent =	tbMeetingPlaceService.findById(id,userId);

		JSONObject json = JSONObject.fromObject(AppResult.ok(tbSeatContent));

		String result = callback + "(" + json.toString() + ")";

		return result;
	}

	/*查询座位*/
	@ResponseBody
	@RequestMapping("/getSeatNum")
	public  String getSeatNum(String userId,@RequestParam(required=true) String callback){

	TbSeatContent tbSeatContent =	tbUserService.findById(userId);

	JSONObject json = JSONObject.fromObject(AppResult.ok(tbSeatContent));

	String result = callback + "(" + json.toString() + ")";

	return  result;
	}



	/**
	 * 报名--获取单位下拉接口
	 */
	@ResponseBody
	@RequestMapping(value = "getDepartmentList")
	public String getJoinDepartmentList(@RequestParam(required =true) String meetingId, @RequestParam(required =true) String callback){
		System.out.println("获取报名单位接口");
		//AppResult appResult = tbJoinService.getDepartmentList();
		AppResult appResult = tbJoinService.getMeetingCompanyList(meetingId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println("result:"+result);
		return result;
	}
	/**
	 * 报名--提交接口
	 */
	@ResponseBody
	@RequestMapping(value = "joinMeeting")
	public String JoinMeeting(@ModelAttribute("joinInfo") JoinInfo joinInfo, @RequestParam(required =true) String callback){
		System.out.println("报名--提交接口");
		System.out.println("joinInfo:"+joinInfo);
		System.out.println("comeTime1:"+joinInfo.getComeTime1());
        System.out.println("goTime1:"+joinInfo.getGoTime1());
		AppResult appResult = tbJoinService.saveTbJoin(joinInfo);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println("result:"+result);
		return result;
	}

	/**
	 * 报名--根据userId获取User信息接口
	 */
	@ResponseBody
	@RequestMapping(value = "getUserByUserId")
	public String getUserByUserId(@RequestParam(required =true) String userId, @RequestParam(required =true) String meetingId, @RequestParam(required =true) String callback){
		System.out.println("报名--根据userId获取User信息接口1:"+userId);
		AppResult appResult = tbUserService.getUserById(userId,meetingId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println("报名--根据userId获取User信息接口2:"+result);
		return result;
	}

	/**
	 * 住宿接口--根据meetingId和userId查询住宿信息
	 */
	@ResponseBody
	@RequestMapping(value = "getStayInfo")
	public String getStayInfo(@RequestParam(required =true) String meetingId, @RequestParam(required =true) String userId, @RequestParam(required =true) String callback){
		System.out.println("住宿接口--根据meetingId和userId查询住宿信息p1:"+meetingId);
		System.out.println("住宿接口--根据meetingId和userId查询住宿信息p2:"+userId);
		AppResult appResult = tbStayService.getStayInfo(meetingId,userId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println("住宿接口--根据meetingId和userId查询住宿信息result:"+result);
		return result;
	}

	/**
	 * 报名接口--根据meetingId和userId查询报名信息
	 */
	@ResponseBody
	@RequestMapping(value = "getJoinInfo")
	public String getJoinInfo(@RequestParam(required =true) String meetingId, @RequestParam(required =true) String userId, @RequestParam(required =true) String callback){
		System.out.println("报名接口--根据meetingId和userId查询报名信息p1:"+meetingId);
		System.out.println("报名接口--根据meetingId和userId查询报名信息p2:"+userId);
		AppResult appResult = tbJoinService.getJoinInfo(meetingId,userId);
		JSONObject json = JSONObject.fromObject(appResult);
		String result = callback + "(" + json.toString() + ")";
		System.out.println("报名接口--根据meetingId和userId查询报名信息result:"+result);
		return result;
	}

	@ResponseBody
    @RequestMapping(value = "findMyData")
    public String findMyData(@RequestParam(required =true) String userId,@RequestParam(required =true) String meetingId ,@RequestParam(required=true) String callback){

		TbSelfContent tbSelfContent = tbUserService.findMyData(userId,meetingId);

		JSONObject json = JSONObject.fromObject(AppResult.ok(tbSelfContent));

		String result = callback + "(" + json.toString() + ")";

             return result;
    }


}