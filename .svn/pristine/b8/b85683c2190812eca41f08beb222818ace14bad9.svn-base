package com.mbgo.searchmgr.core.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbgo.searchmgr.core.bean.MgrLog;
import com.mbgo.searchmgr.core.service.LogService;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;

@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogService logService;
	
	public final static String PACKAGE = "pages/view/system";
	
	  @RequestMapping(value = "/toLog")
	    public String toLog() {
	        return PACKAGE + "/log";
	    }
	  @RequestMapping(value = "/queryLog")
	  public void queryGoodsTags(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String username = request.getParameter("username") == null?"":request.getParameter("username");
		  String userrole = request.getParameter("userrole") == null?"":request.getParameter("userrole");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("username",username);
		  map.put("userrole",userrole);
		  map.put("beginTime",beginTime);
		  map.put("endTime",endTime);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrLog> listPage = null;
		  try{
			  listPage = logService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  int total = logService.selectPageTotal(map);
		  JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessorUtil());
		  String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(listPage,jsonConfig).toString()+"}";
		  response.setContentType("text/html;charset=utf-8");
		  try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
