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

import com.mbgo.searchmgr.core.bean.MgrConvertKeyword;
import com.mbgo.searchmgr.core.service.ConvertKeywordService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/convertKeyword")
public class ConvertKeywordController{
	
	@Autowired
    private ConvertKeywordService convertKeywordService;
	
	public final static String PACKAGE = "pages/view/word";
	
	  @RequestMapping(value = "/toConvertKeyword")
	    public String toCusword() {
	        return PACKAGE + "/convertKeyword";
	    }
	  
	  @RequestMapping(value="/queryConvertKeyword")
	  public void queryConvertKeyword(HttpServletRequest request,HttpServletResponse response){
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String oldWord = request.getParameter("oldWord") == null?"":request.getParameter("oldWord");
		  String newWord = request.getParameter("newWord") == null?"":request.getParameter("newWord");
//		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
//		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
//		  Long at = ConvertUtil.dateConvert(beginTime);
//		  Long et = ConvertUtil.dateConvert(endTime);
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("oldWord",oldWord);
		  map.put("newWord",newWord);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrConvertKeyword> listPage = null;
		  try{
		   listPage = convertKeywordService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  int total = convertKeywordService.selectPageTotal(map);
		  JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessorUtil());
		  String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(listPage,jsonConfig).toString()+"}";
		  response.setContentType("text/html;charset=utf-8");
		  try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
	  }
	  
//	  @RequestMapping(value = "/queryConvert")
//	  @ResponseBody
//	  public List queryConvert(HttpServletRequest request,HttpServletResponse response){
//		  List<ConvertDto> list = new ArrayList<ConvertDto>();
//		  ConvertDto dtp = new ConvertDto();
//		  dtp.setId("1");
//		  dtp.setText("$CID:53,112,177,215");
//		  list.add(dtp);
//		  ConvertDto dtp1 = new ConvertDto();
//		  dtp1.setId("2");
//		  dtp1.setText("针织");
//		  list.add(dtp1);
//		  ConvertDto dtp2 = new ConvertDto();
//		  dtp2.setId("3");
//		  dtp2.setText("$COLOR:5");
//		  list.add(dtp2);
//		  return list;
//	  }
	  @RequestMapping(value = "/deleteConvertKeyword")
	  @ResponseBody
	  public List<ResultUtil> deleteConvertKeyword(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		  System.out.println(ids);
		  List<Long> lids = ConvertUtil.convertLongList(ids);
		  int num = convertKeywordService.deleteBatchByList(lids);
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  if(num>0){
			  ru.setStatus("提示信息");
			  ru.setMessage("删除成功");
		  }else{
			  ru.setStatus("提示信息");
			  ru.setMessage("删除失败");
		  }
		  list.add(ru);
		  return list;
	  }
	  
	
	  @RequestMapping(value = "/addConvertKeyword")
	  @ResponseBody
	  public List<ResultUtil> addConvertKeyword(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrConvertKeyword keyword = new MgrConvertKeyword();
		try{
		ru = new ResultUtil();
		String oldWord = request.getParameter("oldWord1");
		String newWord = request.getParameter("newWord1");
		String combt = request.getParameter("combt");
//		String addTime =  request.getParameter("addTime");
//		String updateTime =  request.getParameter("lastUpdate");
		Long addTime = new Date().getTime();
		Date updateTime = new Date();
//		Long at = ConvertUtil.dateConvert(addTime);
//		Date ut = ConvertUtil.dateTodate(updateTime);
		keyword.setOldword(oldWord);
		keyword.setNewword(newWord);
		keyword.setCombt(combt);
		keyword.setAddTime(addTime);
		keyword.setLastUpdate(updateTime);
		convertKeywordService.addConvertKeyWord(keyword);
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
	  
	  @RequestMapping(value = "/updateConvertKeyword")
	  @ResponseBody
	  public List<ResultUtil> updateConvertKeyword(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrConvertKeyword keyword = new MgrConvertKeyword();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String oldWord = request.getParameter("oldWord1");
				String newWord = request.getParameter("newWord1");
				String combt = request.getParameter("combt");
//				String addTime =  request.getParameter("addTime");
//				String updateTime =  request.getParameter("lastUpdate");
//				Long at = ConvertUtil.dateConvert(addTime);
//				Date ut = ConvertUtil.dateTodate(updateTime);
				Long addTime = new Date().getTime();
				Date updateTime = new Date();
				keyword.setId(Long.parseLong(id));
				keyword.setOldword(oldWord);
				keyword.setNewword(newWord);
				keyword.setCombt(combt);
				keyword.setAddTime(addTime);
				keyword.setLastUpdate(updateTime);
				convertKeywordService.updateConvertKeyword(keyword);
			  ru.setStatus("提示信息");
			  ru.setMessage("修改成功");
			  list.add(ru);
		  }catch (Exception e) {
			  e.printStackTrace();
			  ru = new ResultUtil();
			  ru.setStatus("1");
			  ru.setMessage("修改失败");
		  }
		  return list;
	  }
}
