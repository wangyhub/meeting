package com.lw.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.util.ProcessURL;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

@Service
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
	@Override 
	protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception { 
		 WebUtils.issueRedirect(request, response, redirectUrl, null, true, true,new ProcessURL());
	}
}
