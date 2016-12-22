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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.search.util.HttpClientUtil;
import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.service.GoodsTagsService;
import com.mbgo.searchmgr.core.util.CommonUtil;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/goodsTags")
public class GoodsTagsController{

	@Autowired
	private GoodsTagsService goodsTagsService;
	
	public final static String PACKAGE = "pages/view/word";
	
	  @RequestMapping(value = "/toGoodsTags")
	    public String toGoodsTags() {
	        return PACKAGE + "/goodsTags";
	    }
	  
	  @RequestMapping(value = "/queryGoodsTags")
//	  @ResponseBody
	  public void queryGoodsTags(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String goodsSn = request.getParameter("goodsSn1") == null?"":request.getParameter("goodsSn1");
		  String tagWord = request.getParameter("tagWord1") == null?"":request.getParameter("tagWord1");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
//		  Long at = ConvertUtil.dateConvert(beginTime);
//		  Long et = ConvertUtil.dateConvert(endTime);
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("goodsSn1",goodsSn);
		  map.put("tagWord1",tagWord);
		  map.put("beginTime",beginTime);
		  map.put("endTime",endTime);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrGoodsTags> listPage = null;
		  try{
			  listPage = goodsTagsService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  int total = goodsTagsService.selectPageTotal(map);
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
	  @RequestMapping(value = "/addGoodsTags")
	  @ResponseBody
	  public List<ResultUtil> addGoodsTags(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrGoodsTags goodsTags = new MgrGoodsTags();
		try{
		ru = new ResultUtil();
		String goodsSn = request.getParameter("goodsSn");
		String tagWord = request.getParameter("tagWord");
		String flag = request.getParameter("flag");
		Date ut = new Date();
		goodsTags.setGoodsSn(goodsSn);
		goodsTags.setTagWord(tagWord);
		goodsTags.setAddTime(ut);
		goodsTags.setFlag(Integer.parseInt(flag));
		goodsTagsService.addGoodsTags(goodsTags);
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
	  @RequestMapping(value = "/updateGoodsTags")
	  @ResponseBody
	  public List<ResultUtil> updateGoodsTags(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrGoodsTags goodsTags = new MgrGoodsTags();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String goodsSn = request.getParameter("goodsSn");
			  String tagWord = request.getParameter("tagWord");
			  String flag = request.getParameter("flag");
			  goodsTags.setId(Long.parseLong(id));
			  goodsTags.setGoodsSn(goodsSn);
			  goodsTags.setTagWord(tagWord);
			  goodsTags.setFlag(Integer.parseInt(flag));
			  goodsTagsService.updateGoodsTags(goodsTags);
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
	  
	  @RequestMapping(value = "/updateLimit")
	  @ResponseBody
	  public List<ResultUtil> updateLimit(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrGoodsTags goodsTags = new MgrGoodsTags();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String goodsSn = request.getParameter("goodsSn");
			  String isLimited = request.getParameter("isLimited");
			  int flag = Integer.parseInt(isLimited);
			  goodsTags.setId(Long.parseLong(id));
			  goodsTags.setGoodsSn(goodsSn);
			  if(flag==0){
				  goodsTags.setIsLimited(flag);
				  goodsTagsService.updateLimit(goodsTags);
			  }else{
				  goodsTags.setIsLimited(0);
				  goodsTagsService.updateLimitOther(goodsTags);
				  goodsTags.setIsLimited(flag);
				  goodsTagsService.updateLimit(goodsTags);
			  }
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

	  @RequestMapping(value = "/deleteGoodsTags")
	  @ResponseBody
	  public List<ResultUtil> deleteGoodsTags(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		 List<Long>  cids = ConvertUtil.convertLongList(ids);
		  goodsTagsService.deleteBatchByList(cids);
		  String url = new CommonUtil().loadProperties().getProperty("addProducturl");
		  for(Long id:cids){
		   List<NameValuePair> params = new ArrayList<NameValuePair>();
		   params.add(new BasicNameValuePair("list",String.valueOf(id)));
		   params.add(new BasicNameValuePair("type","update"));
//		   HttpClientUtil.post(url, params);
		  }
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  list.add(ru);
		  return list;
	  }

	public GoodsTagsService getGoodsTagsService() {
		return goodsTagsService;
	}

	public void setGoodsTagsService(GoodsTagsService goodsTagsService) {
		this.goodsTagsService = goodsTagsService;
	}

	  
	  
	  
}
