package com.lw.modules.meeting.common;

import cn.jpush.api.JPushClient;
import com.lw.common.config.Global;
import java.util.List;

public class JiguangHandle {

    public static int sendToAppList(List<String> aliasList, String title, String msg) {
        String masterSecret = Global.getConfig("jpush.master.secret");
        String appKey = Global.getConfig("jpush.app.key");
        //创建JPushClient
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        return JPushUtil.sendToAliasList(jpushClient,aliasList,title,msg);
    }

}
