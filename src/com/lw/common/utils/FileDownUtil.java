package com.lw.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * 不同浏览器对应的下载处理
 * @author --
 * @version 2015-08-10
 */
public class FileDownUtil {
	public static String encodeFilename(HttpServletRequest request,String fileName) 
	        throws UnsupportedEncodingException  {
        String agent =request.getHeader("USER-AGENT");
        try {
            //IE
            if(null != agent && -1 != agent.indexOf("MSIE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }else if(null != agent && -1 != agent.indexOf("Firefox")) {
            	//Firefox[Mozilla]
                fileName =  new String( fileName.getBytes("UTF-8"), "ISO-8859-1" );
                //fileName = URLEncoder.encode(fileName, "UTF-8");
            }else if(null != agent && -1 != agent.indexOf("Gecko")) {
            	fileName = URLEncoder.encode(fileName, "UTF-8");
            }
        }catch (UnsupportedEncodingException e) {
            try {
        	    fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        	    // fileName = URLEncoder.encode(fileName, "UTF-8");
            }catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return fileName;
	}
}
