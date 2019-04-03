package com.lw.common.ema.ea.sms.socket.message;

public class EaConstants {

    //
    public static final int CMT_ALREADYLOGIN = 1;

    public static final int CMT_AuthErr = 2; // 用户名无效

    /**
     * 获取登录结果说明
     */
    public static String getLoginDesc(int status) {
        String str;
        switch (status) {
        case EaConstants.CMT_ALREADYLOGIN:
            str = "重复登录【" + status + "】";
            break;
        case EaConstants.CMT_AuthErr:
            str = "认证失败【" + status + "】";
            break;
        default:
            str = "未知错误【" + status + "】";
            break;
        }
        return str;
    }

    /************** submitResp****************************8534 start *************/
    public static String getSubmitDesc(int status) {
        String str;
        switch (status) {
        case 0:
            str = "提交成功";
            break;
        case -1:
            str = "机构用户无效";
            break;
        case -2:
            str = "提交失败";
            break;
        case 98:
            str = "系统正忙";
            break;
        default:
            str = "未知错误";
            break;
        }
        return str;
    }

    /************** submitResp****************************8534 end *************/

    /************** reportResp****************************8534 start *************/
    public static String getReportDesc(int status) {
        String str;
        switch (status) {
        case 0:
            str = "发送成功";
            break;
        case 98:
            str = "系统正忙";
            break;
        default:
            str = "发送失败";
            break;
        }
        return str;
    }
    /************** reportResp****************************8534 end *************/

}
