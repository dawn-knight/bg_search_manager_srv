package com.mbgo.searchmgr.core.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import com.mbgo.searchmgr.core.bean.MgrSearchwordLog;
import com.mbgo.searchmgr.core.bean.SearchwordLog;
import com.mbgo.searchmgr.core.service.SearchWordService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;

@Controller
@RequestMapping("/searchWord")
public class SearchwordController {
	public final static String PACKAGE = "pages/view/word";
	
	private String common = "";
	@Autowired
	private SearchWordService wordService;
	 @RequestMapping(value = "/toSearchword")
	 public String toSearchword() {
	        return PACKAGE + "/searchword";
	    }
	  @ResponseBody
	  @RequestMapping("/queryWord")
	  public void queryWord(HttpServletRequest request,HttpServletResponse response){
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String keyWord = request.getParameter("word") == null?"":request.getParameter("word");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
		  common = keyWord;
		  Long at = null;
		  Long et = null;
		  if(!beginTime.equals("")){
			  String dis_begintime="00:00:00";
			  beginTime = beginTime.split(" ")[0] +" " +dis_begintime;
			  at = ConvertUtil.dateConvert(beginTime);
		  }
		  if(!endTime.equals("")){
			  String dis_endtime="23:59:59";
			  endTime = endTime.split(" ")[0]+" "+dis_endtime;
			  et = ConvertUtil.dateConvert(endTime);
		  }
		   
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("word",keyWord);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrSearchwordLog> listPage = null;
		  try{
			  listPage = wordService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  int total = wordService.selectPageTotal(map);
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
	  
	  @RequestMapping("downLoadSearchword")
	   public void downLoadSearchword(HttpServletRequest request,HttpServletResponse response){
		      Map<String, Object> map =null;
		      try{
		    	  String keyWord = request.getParameter("word") == null?"":request.getParameter("word");
		    	  keyWord = common;
//		    	  String urlStr =URLEncoder.encode(word,"ISO8859-1"); 
//		    	  String  keyWord=URLDecoder.decode(urlStr,"UTF-8"); 
//		    	  System.out.println(keyWord);
				  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
				  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
				  Long at = null;
				  Long et = null;
				  if(!beginTime.equals("")){
					  String dis_begintime="00:00:00";
					  beginTime = beginTime.split(" ")[0] +" " +dis_begintime;
					  at = ConvertUtil.dateConvert(beginTime);
				  }
				  if(!endTime.equals("")){
					  String dis_endtime="23:59:59";
					  endTime = endTime.split(" ")[0]+" "+dis_endtime;
					  et = ConvertUtil.dateConvert(endTime);
				  }
			  map= new HashMap<String, Object>();
			  map.put("word",keyWord);
			  map.put("beginTime",at);
			  map.put("endTime",et);
			  List<MgrSearchwordLog> listPage = null;
			  List<SearchwordLog> listPageDTO = null;
			  listPage = wordService.exportWord(map);
			  listPageDTO = ConvertUtil.longToDate(listPage);
			  wordService.downLoadSearchword(response, listPageDTO);
			  }catch (Exception e) {
				  e.printStackTrace();
			}
	  }
	  
}
