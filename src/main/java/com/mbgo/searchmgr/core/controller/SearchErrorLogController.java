package com.mbgo.searchmgr.core.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.bean.SearchErrorLog;
import com.mbgo.searchmgr.core.service.SearchErrorLogService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;


@Controller
@RequestMapping("/searchErrorLog")
public class SearchErrorLogController {

	public final static String PACKAGE = "pages/view/system";
	
	@Autowired
	private SearchErrorLogService errorLogService;
	
	@RequestMapping("/toSearchLog")
	public String toSearchLog(){
		return PACKAGE + "/searchLog";
	}
	
	
	@RequestMapping("/queryErrorLog")
	public void queryErrorLog(HttpServletRequest request,HttpServletResponse response){
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String productId = request.getParameter("productId") == null?"":request.getParameter("productId");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("productId",productId);
		  map.put("beginTime",beginTime);
		  map.put("endTime",endTime);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<SearchErrorLog> listPage = null;
		  try{
			  listPage = errorLogService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  int total = errorLogService.selectPageTotal(map);
		  JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessorUtil());
		  String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(listPage,jsonConfig).toString()+"}";
		  response.setContentType("text/html;charset=utf-8");
//		  mapTP.put("rows", listPage);
		  try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/deleteSearchLog")
	@ResponseBody
	public  List<ResultUtil> deleteSearchLog(HttpServletRequest request){
		  ResultUtil ru = null;
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  try{
		  String  ids = request.getParameter("ids");
		  List<Long> lids = ConvertUtil.convertLongList(ids);
		  errorLogService.deleteBatchByList(lids);
		  ru = new ResultUtil();
		  ru.setStatus("提示信息");
	      ru.setMessage("删除成功");
		  }catch (Exception e) {
		  ru.setStatus("提示信息");
		  ru.setMessage("删除失败");
		}
		  list.add(ru);
		  return list;
		  
		 
	  }

	
	
}
