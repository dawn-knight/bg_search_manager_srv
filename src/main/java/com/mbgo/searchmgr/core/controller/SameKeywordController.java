package com.mbgo.searchmgr.core.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mbgo.searchmgr.core.bean.MgrSameKeyword;
import com.mbgo.searchmgr.core.service.SameKeywordService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ReadTxtLine;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/sameKeyword")
public class SameKeywordController{

	@Autowired
	private SameKeywordService sameKeywordService;
	
	public final static String PACKAGE = "pages/view/word";
	
	  @RequestMapping(value = "/toSameKeyword")
	    public String toSameKeyword() {
	        return PACKAGE + "/sameKeyword";
	    }
	  
	  @RequestMapping(value = "/querySameKeyword")
	  public void querySameKeyword(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  String order = request.getParameter("order")==null?"desc":request.getParameter("order");
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String words = request.getParameter("words1") == null?"":request.getParameter("words1");
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
		  map.put("words",words);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  map.put("order", order);
		  List<MgrSameKeyword> listPage = sameKeywordService.selectAndPage(map);
		  int total = sameKeywordService.selectPageTotal(map);
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
	  @RequestMapping(value = "/addSameKeyword")
	  @ResponseBody
	  public List<ResultUtil> addSameKeyword(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrSameKeyword sameKeyword = new MgrSameKeyword();
		try{
		ru = new ResultUtil();
		String words = request.getParameter("words");
		Long at = new Date().getTime();
		Date ut = new Date();
		sameKeyword.setWords(words);
		sameKeyword.setAddTime(at);
		sameKeyword.setLastUpdate(ut);
		sameKeywordService.add(sameKeyword);
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
	  @RequestMapping(value = "/updateSameKeyword")
	  @ResponseBody
	  public List<ResultUtil> updateSameKeyword(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrSameKeyword sameKeyword = new MgrSameKeyword();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String words = request.getParameter("words");
			  Date ut = new Date();
			  sameKeyword.setId(Long.parseLong(id));
			  sameKeyword.setWords(words);
			  sameKeyword.setLastUpdate(ut);
			  sameKeywordService.updateByPrimaryKeySelective(sameKeyword);
			  ru.setStatus("提示信息");
			  ru.setMessage("修改成功");
			  list.add(ru);
		  }catch (Exception e) {
			  e.printStackTrace();
			  ru = new ResultUtil();
			  ru.setStatus("提示信息");
			  ru.setMessage("修改失败");
			  list.add(ru);
		  }
		  return list;
	  }
	  @RequestMapping(value = "/updateSameKeywordId")
	  @ResponseBody
	  public List<String> updateSameKeywordId(HttpServletRequest request) {
		  List<String> list = new ArrayList<String>();
		  MgrSameKeyword sameKeyword = new MgrSameKeyword();
		  try{
			  String id = request.getParameter("id");
			  String words = request.getParameter("words");
			  sameKeyword.setId(Long.parseLong(id));
			  sameKeyword.setWords(words);
			  sameKeywordService.updateByPrimaryKeySelective(sameKeyword);
			  list.add("添加成功");
		  }catch (Exception e) {
			  e.printStackTrace();
			  list.add("添加失败");
		  }
		  return list;
	  }

	  @RequestMapping(value = "/deleteSameKeyword")
	  @ResponseBody
	  public List<ResultUtil> deleteSameKeyword(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		  List<Long>  cids = ConvertUtil.convertLongList(ids);
		  sameKeywordService.deleteBatchByList(cids);
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  list.add(ru);
		  return list;
	  }
	  @RequestMapping(value = "/importSameWord")
	  public String importSameWord(HttpServletRequest request,HttpServletResponse response) {
		   MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		   String result = "";
	        /** 构建文件保存的目录* */
	        String pathDir = "/upload/sameword";
	        /** 得到文件保存目录的真实路径* */
	        String realDir = request.getSession().getServletContext()
	                .getRealPath(pathDir);
		   /** 页面控件的文件流* */
	        MultipartFile multipartFile = multipartRequest.getFile("filename");
	        /** 获取文件的后缀* */
	        String filename = multipartFile.getOriginalFilename();
	        String createName = new Date().getTime()+filename; 
	        String endPath = realDir + File.separator + createName;
	        File file = new File(endPath);
	        if(!file.exists()){
	        	file.mkdirs();
	        }
	        try{
	        multipartFile.transferTo(file);
	        List<String> word = ReadTxtLine.readTxtFile(endPath);
	        MgrSameKeyword sameKeyword = new MgrSameKeyword();
	        for (String para : word) {
	        	sameKeyword.setWords(para);
	        	sameKeyword.setAddTime(new Date().getTime());
	        	sameKeyword.setLastUpdate(new Date());
	        	sameKeywordService.add(sameKeyword);
			}
	        result = "1";
	        }catch (Exception e) {
	        result = "0";	
	        e.printStackTrace();
			}
		   return result;
	  }

	public SameKeywordService getSameKeywordService() {
		return sameKeywordService;
	}

	public void setSameKeywordService(SameKeywordService sameKeywordService) {
		this.sameKeywordService = sameKeywordService;
	}

	  
	  
}
