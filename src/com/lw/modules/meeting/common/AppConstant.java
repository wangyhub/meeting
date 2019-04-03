package com.lw.modules.meeting.common;

/**
 * @program: meeting
 * @Date: 2019/3/13 16:15
 * @Author: wangy
 * @Description:
 */
public class AppConstant {
    //接口返回错误状态码
    public static final String APP_ERROE_STATUS = "0";

    //本机服务器地址
    public static final String LOCAL_HOST = "http://192.168.4.115:9080/meeting/attached";

    //登录返回错误信息
    public static final String LOGIN_ERROR_MSG = "手机号错误";
    //验证码发送失败
    public static final String SMS_CODE_MISS = "验证码发送失败";
    //验证会议码返回错误信息
    public static final String CHECK_MEETINGCODE_MSG = "会议码错误或已被使用";

    public static final String AGENDA_ERROR_MSG = "获取议程错误";

    public static final String MEAL_ERROR_MSG = "获取用餐信息错误";

    public static final String JOIN_DEPARTMENT_ERROR_MSG = "获取单位信息错误";

    public static final String JOIN_PART_ERROR_MSG = "提交报名信息错误";

    public static final String GET_STAY_ERROR_MSG = "获取住宿信息错误";

    public static final String GET_JOIN_INFO_ERROR_MSG = "获取住宿信息错误";

    //app消息每页显示条数
    public static final Integer APP_MESSAGE_PAGENUM = 7;
    //app消息未读状态
    public static final String NOTREAD_MESSAGE_STATUS = "1";
    //app消息已读状态
    public static final String ISTREAD_MESSAGE_STATUS = "0";
    //app消息类型1 手机推送消息
    public static final Integer MESSAGE_TYPE_PUSH = 1;

}
