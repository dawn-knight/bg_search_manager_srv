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

import com.mbgo.searchmgr.core.bean.MgrHotSearchKeyword;
import com.mbgo.searchmgr.core.service.HotSearchKeywordService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/hotSearchKeyword")
public class HotSearchKeywordController{

	@Autowired
	private HotSearchKeywordService hotSearchKeywordService;
	
	public final static String PACKAGE = "pages/view/word";
	
	  @RequestMapping(value = "/toHotSearchKeyword")
	    public String toHotSearchKeyword() {
	        return PACKAGE + "/hotSearchKeyword";
	    }
	  
	  @RequestMapping(value = "/queryHotSearchKeyword")
	  public void queryHotSearchKeyword(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String word = request.getParameter("word") == null?"":request.getParameter("word");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("word",word);
		  map.put("beginTime",beginTime);
		  map.put("endTime",endTime);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrHotSearchKeyword> listPage = hotSearchKeywordService.selectAndPage(map);
		  int total = hotSearchKeywordService.selectPageTotal(map);
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
//		  return list;
	  }
	  @RequestMapping(value = "/addHotSearchKeyword")
	  @ResponseBody
	  public List<ResultUtil> addHotSearchKeyword(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrHotSearchKeyword hotSearchKeyword = new MgrHotSearchKeyword();
		try{
		ru = new ResultUtil();
		String word = request.getParameter("word");
		String sortIndex = request.getParameter("sortIndex");
		String indexLevel = request.getParameter("indexLevel");
		String channelId = request.getParameter("channelId");
		String categoryId = request.getParameter("categoryId");
		Date ut = new Date();
		hotSearchKeyword.setWord(word);
		hotSearchKeyword.setSortIndex(Integer.parseInt(sortIndex));
		hotSearchKeyword.setIndexLevel(Integer.parseInt(indexLevel));
		hotSearchKeyword.setChannelId(channelId);
		hotSearchKeyword.setCategoryId(categoryId);
		hotSearchKeyword.setAddTime(ut);
		hotSearchKeywordService.add(hotSearchKeyword);
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
	  @RequestMapping(value = "/updateHotSearchKeyword")
	  @ResponseBody
	  public List<ResultUtil> updateHotSearchKeyword(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrHotSearchKeyword hotSearchKeyword = new MgrHotSearchKeyword();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String word = request.getParameter("word");
			  String sortIndex = request.getParameter("sortIndex");
			  String indexLevel = request.getParameter("indexLevel");
			  String channelId = request.getParameter("channelId");
			  String categoryId = request.getParameter("categoryId");
			  Date ut = new Date();
			  hotSearchKeyword.setId(Integer.parseInt(id));
			  hotSearchKeyword.setWord(word);
			  hotSearchKeyword.setSortIndex(Integer.parseInt(sortIndex));
			  hotSearchKeyword.setIndexLevel(Integer.parseInt(indexLevel));
			  hotSearchKeyword.setChannelId(channelId);
			  hotSearchKeyword.setCategoryId(categoryId);
			  hotSearchKeyword.setAddTime(ut);
			  hotSearchKeywordService.updateByPrimaryKeySelective(hotSearchKeyword);
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

	  @RequestMapping(value = "/deleteHotSearchKeyword")
	  @ResponseBody
	  public List<ResultUtil> deleteHotSearchKeyword(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		  List<Long>  cids = ConvertUtil.convertLongList(ids);
		  hotSearchKeywordService.deleteBatchByList(cids);
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  list.add(ru);
		  return list;
	  }

	public HotSearchKeywordService getHotSearchKeywordService() {
		return hotSearchKeywordService;
	}

	public void setHotSearchKeywordService(
			HotSearchKeywordService hotSearchKeywordService) {
		this.hotSearchKeywordService = hotSearchKeywordService;
	}

	
	  
	  
}
