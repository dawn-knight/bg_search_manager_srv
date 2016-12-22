package com.mbgo.searchmgr.core.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mbgo.searchmgr.core.bean.MgrLog;
import com.mbgo.searchmgr.core.service.LogService;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.service.UserService;
import com.mbgo.searchmgr.core.util.CommonUtil;

/**
 * @author qining
 *
 */
public class CommonInterceptor implements HandlerInterceptor{
	
	@Autowired
	private LogService logService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 MgrLog log = null;
		String content ="";
		Date date = null;
		String uri = request.getRequestURI();    
		String path = request.getContextPath();
		String method = CommonUtil.getRemainingPath(uri,path,false);
		if(!method.equals("/baseKetword/updateSearch")){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		if(null == userDetails){
			date = new Date();
			content = "外部调用了方法"+method;
			log = new MgrLog();
		    log.setUsername("接口");
		    log.setUserrole("接口");
		    log.setLogContent(content);
		    log.setAddtime(date);
		    logService.insertSelective(log);
		}
		else if(!method.startsWith("/log")){
		String allRole = userDetails.getAuthorities().toString();
		String username = userDetails.getUsername();
	    date = new Date();
		content = "用戶"+username+"操作了方法:"+method;
        log = new MgrLog();
        log.setUsername(username);
        log.setUserrole(allRole);
        log.setLogContent(content);
        log.setAddtime(date);
        logService.insertSelective(log);
		}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
