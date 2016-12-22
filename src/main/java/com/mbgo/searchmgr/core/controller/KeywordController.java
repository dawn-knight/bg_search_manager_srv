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

import com.mbgo.searchmgr.core.bean.MgrDicKeyword;
import com.mbgo.searchmgr.core.service.KeywordService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/keyword")
public class KeywordController{
	

	@Autowired
	private KeywordService KeywordService;
	
	public final static String PACKAGE = "pages/view/word";
	
	  @RequestMapping(value = "/toCusword")
	    public String toCusword() {
	        return PACKAGE + "/keyword";
	    }
	  
	  @RequestMapping(value = "/queryDicKeyword")
//	  @ResponseBody
	  public void queryDicKeyword(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  String order = request.getParameter("order")==null?"desc":request.getParameter("order");
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String word = request.getParameter("word") == null?"":request.getParameter("word");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
		  Long at = ConvertUtil.dateConvert(beginTime);
		  Long et = ConvertUtil.dateConvert(endTime);
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("word",word);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  map.put("order", order);
		  List<MgrDicKeyword> listPage = null;
		  try{
		   listPage = KeywordService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  List<MgrDicKeyword> list = KeywordService.selectPageTotal(map);
		  int total = list.size();
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
//		  list.add(mapTP);
//		  return PACKAGE + "/keyword";
	  }
	  @RequestMapping(value = "/addword")
	  @ResponseBody
	  public List<ResultUtil> addword(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrDicKeyword keyword = new MgrDicKeyword();
		try{
		ru = new ResultUtil();
		String word = request.getParameter("word");
//		String addTime =  request.getParameter("addTime");
//		String updateTime =  request.getParameter("lastUpdate");
		String weight =  request.getParameter("weight");
		Long at = new Date().getTime();
		Date ut = new Date();
		keyword.setWord(word);
		keyword.setAddTime(at);
		keyword.setLastUpdate(ut);
		keyword.setWeight(Integer.parseInt(weight));
		KeywordService.addKeyWord(keyword);
		ru.setStatus("提示信息");
		ru.setMessage("添加成功");
		list.add(ru);
		}catch (Exception e) {
			e.printStackTrace();
			ru = new ResultUtil();
			ru.setStatus("提示信息");
			ru.setMessage("添加失败");
		}
		return list;
	  }
	  @RequestMapping(value = "/updateword")
	  @ResponseBody
	  public List<ResultUtil> updateword(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrDicKeyword keyword = new MgrDicKeyword();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String word = request.getParameter("word");
//			  String addTime =  request.getParameter("addTime");
//			  String updateTime =  request.getParameter("lastUpdate");
			  String weight =  request.getParameter("weight");
			  Date ut = new Date();
			  keyword.setId(Long.parseLong(id));
			  keyword.setWord(word);
			  keyword.setLastUpdate(ut);
			  keyword.setWeight(Integer.parseInt(weight));
			  KeywordService.updateKeyword(keyword);
			  ru.setStatus("提示信息");
			  ru.setMessage("修改成功");
			  list.add(ru);
		  }catch (Exception e) {
			  e.printStackTrace();
			  ru = new ResultUtil();
			  ru.setStatus("提示信息");
			  ru.setMessage("修改失败");
		  }
		  return list;
	  }

	  @RequestMapping(value = "/deleteKeyword")
	  @ResponseBody
	  public List<ResultUtil> deleteKeyword(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		  System.out.println(ids);
		  List<Long> lids = ConvertUtil.convertLongList(ids);
		  KeywordService.deleteBatchByList(lids);
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  list.add(ru);
		  return list;
	  }
	public KeywordService getKeywordService() {
		return KeywordService;
	}

	public void setKeywordService(KeywordService keywordService) {
		KeywordService = keywordService;
	}
	  
	
	  
	  
}
