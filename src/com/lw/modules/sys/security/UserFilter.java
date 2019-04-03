package com.lw.modules.sys.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.util.ProcessURL;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

@Service
public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter {
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String loginUrl = getLoginUrl();
	    WebUtils.issueRedirect(request, response, loginUrl, null, true, true,new ProcessURL());
	}
}
