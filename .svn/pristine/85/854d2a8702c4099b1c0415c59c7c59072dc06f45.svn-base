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

import com.mbgo.searchmgr.core.bean.MgrBaseKeyword;
import com.mbgo.searchmgr.core.service.BaseKeywordService;
import com.mbgo.searchmgr.core.util.AutokeyConvernt;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/baseKetword")
public class BaseKetword {
	
	public final static String PACKAGE = "pages/view/word";
	
	@Autowired
	private BaseKeywordService baseKeywordService;
	
	 
	  @RequestMapping(value = "/toBaseKetword")
	    public String toBaseKetword() {
	        return PACKAGE + "/baseKeyword";
	    }
	

	  @RequestMapping(value = "/queryBaseKetword")
//	  @ResponseBody
	  public void queryBaseKetword(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String keyWord = request.getParameter("keyword1") == null?"":request.getParameter("keyword1");
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
		  map.put("keyWord",keyWord);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrBaseKeyword> listPage = null;
		  try{
			  listPage = baseKeywordService.selectAndPage(map);
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  int total = baseKeywordService.selectPageTotal(map);
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
	  
	  @RequestMapping(value = "/addBaseKetword")
	  @ResponseBody
	  public List<ResultUtil> addBaseKetword(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrBaseKeyword baseKeyword = new MgrBaseKeyword();
		try{
		ru = new ResultUtil();
		String keyword = request.getParameter("keyword");
		String searchCount = request.getParameter("searchCount");
		String rscount = request.getParameter("rscount");
		Long at = new Date().getTime();
		Date ut = new Date();
		 String wordCode = AutokeyConvernt.code(keyword);
		 List<MgrBaseKeyword> listMbk = baseKeywordService.selectByCode(wordCode);
		 if(listMbk.size() > 0 ){
			ru.setStatus("1");
			ru.setMessage("关键字已经存在，请勿重复输入");
			list.add(ru);
			return list; 
		}
		baseKeyword.setKeyword(keyword);
		baseKeyword.setWordCode(wordCode);
		baseKeyword.setSearchCount(Long.parseLong(searchCount));
		baseKeyword.setRscount(Long.parseLong(rscount));
		baseKeyword.setAddTime(at);
		baseKeyword.setLastUpdate(ut);
		baseKeywordService.addBaseKeyword(baseKeyword);
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
   
	  @RequestMapping(value = "/updateBaseKetword")
	  @ResponseBody
	  public List<ResultUtil> updateBaseKetword(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrBaseKeyword baseKeyword = new MgrBaseKeyword();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String keyword = request.getParameter("keyword");
//				String wordCode = request.getParameter("wordCode");
				String searchCount = request.getParameter("searchCount");
				String rscount = request.getParameter("rscount");
				Date ut = new Date();
				String wordCode = AutokeyConvernt.code(keyword);
				List<MgrBaseKeyword> listMbk = baseKeywordService.selectByCode(wordCode);
				if(listMbk.size() > 0 ){
					for (MgrBaseKeyword mgrBaseKeyword : listMbk) {
						if(mgrBaseKeyword.getId() != Long.parseLong(id)){
							ru.setStatus("1");
							ru.setMessage("关键字已经存在，请勿重复输入");
							list.add(ru);
							return list; 
						}
					}
					
				}
				baseKeyword.setId(Long.parseLong(id));
				baseKeyword.setKeyword(keyword);
				baseKeyword.setWordCode(wordCode);
				baseKeyword.setSearchCount(Long.parseLong(searchCount));
				baseKeyword.setRscount(Long.parseLong(rscount));
				baseKeyword.setLastUpdate(ut);
			   baseKeywordService.updateBaseKeyword(baseKeyword);
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
	  
	  @RequestMapping(value = "/deleteBaseKetword")
	  @ResponseBody
	  public List<ResultUtil> deleteBaseKetword(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		  List<Long> lids = ConvertUtil.convertLongList(ids);
		  baseKeywordService.deleteBatchByList(lids);
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  list.add(ru);
		  return list;
	  }
	  
	   @RequestMapping(value="/updateSearch")
	   public void updateSearch(HttpServletRequest request){
		   Map<String,Long> mapCache = new HashMap<String,Long>();
		   Map<String,Long> mapResult = new HashMap<String, Long>();
		   String wordCountTemp = request.getParameter("keywordCount");
		   wordCountTemp = wordCountTemp.substring(0,wordCountTemp.length()-2);
		   String [] arrWordCount = wordCountTemp.split("#;#");
		   try{
		   for (String wordCount : arrWordCount) {
			String[] vs = wordCount.split("#:#");
			if(vs.length == 2 ) {
					String keyword = vs[0];
					String result = vs[1];
					if(!mapCache.containsKey(keyword)){
						mapCache.put(keyword, 1l);
						mapResult.put(keyword, Long.parseLong(result));
					}else{
						Long count = mapCache.get(keyword)+1;
						mapCache.put(keyword, count);
					}
			}
		   }
			for (Map.Entry<String, Long> entry: mapCache.entrySet()) {
				String keyword = entry.getKey();
				Long result = mapResult.get(keyword); 
				Long searchCount = entry.getValue();
				String wordCode = AutokeyConvernt.code(entry.getKey());
				MgrBaseKeyword baseKeyword = baseKeywordService.selectDataByWordcode(wordCode);
				MgrBaseKeyword	mbk = null;
				if(null == baseKeyword){
					mbk = new MgrBaseKeyword();
					mbk.setKeyword(keyword);
					mbk.setWordCode(wordCode);
					mbk.setRscount(result);
					mbk.setSearchCount(searchCount);
					mbk.setAddTime(new Date().getTime());
					mbk.setLastUpdate(new Date());
					baseKeywordService.addBaseKeyword(mbk);
				}else{
				 mbk = new MgrBaseKeyword();
				 searchCount = baseKeyword.getSearchCount() + searchCount;
				 mbk.setRscount(result);
				 mbk.setSearchCount(searchCount);
				 mbk.setId(baseKeyword.getId());
				 baseKeywordService.updateBaseKeyword(mbk);
				}
			}
		}catch (Exception e) {
			System.out.println("传入的数据格式有误"); 
		}
	  
	   }
	   @RequestMapping("downLoadBaseKetword")
	   public void downLoadBaseKetword(HttpServletRequest request,HttpServletResponse response){
		      Map<String, Object> map =null;
		      try{
			  String keyWord = request.getParameter("keyword") == null?"":request.getParameter("keyword");
			  keyWord = new String( keyWord.getBytes("ISO-8859-1") , "UTF-8"); 
			  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
			  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
			  Long at = ConvertUtil.dateConvert(beginTime);
			  Long et = ConvertUtil.dateConvert(endTime);
			  map= new HashMap<String, Object>();
			  map.put("keyWord",keyWord);
			  map.put("beginTime",at);
			  map.put("endTime",et);
			  List<MgrBaseKeyword> listPage = null;
			  listPage = baseKeywordService.exportSelect(map);
		      baseKeywordService.downloadBaseWord(response, listPage);
			  }catch (Exception e) {
				  e.printStackTrace();
			}
	   }
	   
}
