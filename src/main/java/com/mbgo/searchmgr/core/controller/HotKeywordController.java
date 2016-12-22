package com.mbgo.searchmgr.core.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
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

import com.csvreader.CsvWriter;
import com.mbgo.searchmgr.core.bean.MgrHotKeyword;
import com.mbgo.searchmgr.core.service.HotKeywordService;
import com.mbgo.searchmgr.core.util.AutokeyConvernt;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/hotKeyword")
public class HotKeywordController{

	@Autowired
	private HotKeywordService hotKeywordService;
	
	public final static String PACKAGE = "pages/view/word";
	
	  @RequestMapping(value = "/toHotKeyword")
	    public String toHotKeyword() {
	        return PACKAGE + "/hotKeyword";
	    }
	  
	  @RequestMapping(value = "/queryHotKeyword")
	  public void queryHotKeyword(HttpServletRequest request,HttpServletResponse response) {
		  Map<String, Object> map =null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String word = request.getParameter("word1") == null?"":request.getParameter("word1");
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
		  List<MgrHotKeyword> listPage = hotKeywordService.selectAndPage(map);
		  int total = hotKeywordService.selectPageTotal(map);
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
	  @RequestMapping(value = "/addHotKeyword")
	  @ResponseBody
	  public List<ResultUtil> addHotKeyword(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrHotKeyword hotKeyword = new MgrHotKeyword();
		try{
		ru = new ResultUtil();
		String word = request.getParameter("word");
		String searchCount = request.getParameter("searchCount");
		Long at = new Date().getTime();
		Date ut = new Date();
		 String code = AutokeyConvernt.code(word);
		 List<MgrHotKeyword> listMhk = hotKeywordService.selectByCode(code);
		 if(listMhk.size() > 0 ){
			ru.setStatus("1");
			ru.setMessage("关键字已经存在，请勿重复输入");
			list.add(ru);
			return list; 
		}
		hotKeyword.setWord(word);
		hotKeyword.setWordCode(code);
		hotKeyword.setSearchCount(Long.parseLong(searchCount));
		hotKeyword.setAddTime(at);
		hotKeyword.setLastUpdate(ut);
		hotKeywordService.add(hotKeyword);
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
	  @RequestMapping(value = "/updateHotKeyword")
	  @ResponseBody
	  public List<ResultUtil> updateHotKeyword(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrHotKeyword hotKeyword = new MgrHotKeyword();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String word = request.getParameter("word");
			  String searchCount = request.getParameter("searchCount");
			  Date ut = new Date();
			  
			  String code = AutokeyConvernt.code(word);
			  List<MgrHotKeyword> listMhk = hotKeywordService.selectByCode(code);
				if(listMhk.size() > 0 ){
					for (MgrHotKeyword mgrHotKeyword : listMhk) {
						if(mgrHotKeyword.getId() != Long.parseLong(id)){
							ru.setStatus("1");
							ru.setMessage("关键字已经存在，请勿重复输入");
							list.add(ru);
							return list; 
						}
					}
					
				}
			  
			  hotKeyword.setId(Long.parseLong(id));
			  hotKeyword.setWord(word);
			  hotKeyword.setWordCode(code);
			  hotKeyword.setSearchCount(Long.parseLong(searchCount));
			  hotKeyword.setLastUpdate(ut);
			  hotKeywordService.updateByPrimaryKeySelective(hotKeyword);
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

	  @RequestMapping(value = "/checkCode")
	  @ResponseBody
	  public List<ResultUtil> checkCode(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		 String words = request.getParameter("words");
		 String code = AutokeyConvernt.code(words);
		 List<MgrHotKeyword> listMhk = hotKeywordService.selectByCode(code);
		 if(listMhk.size() > 0){
			 ru.setStatus("1");
			 ru.setMessage(words +"已经存在，无需输入");
		 }else{
			 ru.setStatus("0");
			 ru.setMessage(code);
		 }
		 list.add(ru);
		  return list;
	  }
	  
	  @RequestMapping(value = "/deleteHotKeyword")
	  @ResponseBody
	  public List<ResultUtil> deleteHotKeyword(HttpServletRequest request) {
		  String  ids = request.getParameter("ids");
		  List<Long>  cids = ConvertUtil.convertLongList(ids);
		  hotKeywordService.deleteBatchByList(cids);
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  ru = new ResultUtil();
		  list.add(ru);
		  return list;
	  }
	  
	  @RequestMapping(value = "/exportCvs")
	  public void exportCvs(HttpServletRequest request,HttpServletResponse response){
		  Map<String, Object> map =null;
		  CsvWriter wr = null;
		  String csvFilePath = null;
		  try {  
		   csvFilePath = request.getParameter("file") == null?"D:/test.csv":request.getParameter("file");
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String word = request.getParameter("word1") == null?"":request.getParameter("word1");
		  String wordCode = request.getParameter("wordCode1") == null?"":request.getParameter("wordCode1");
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
		  map.put("wordCode",wordCode);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrHotKeyword> listPage = hotKeywordService.selectAndPage(map);
		  wr = new CsvWriter(csvFilePath, ',',Charset.forName("GBK"));
		  String[] title = { "热门内容", "内容编码", "搜索次数", "添加时间", "更新时间" };
		  wr.writeRecord(title);
		  String[] content = new String[5];
		  for (MgrHotKeyword mhk : listPage) {
                content[0] = mhk.getWord();
                content[1] = mhk.getWordCode();
                content[2] = mhk.getSearchCount().toString();
                content[3] = ConvertUtil.dataToString(new Date(mhk.getAddTime()));
                content[4] = ConvertUtil.dataToString(mhk.getLastUpdate());
                wr.writeRecord(content);
			}
			  wr.close();
			  
			   File file = new File(csvFilePath);// path是根据日志路径和文件名拼接出来的
			    String filename = file.getName();// 获取日志文件名称
			    InputStream fis = new BufferedInputStream(new FileInputStream(csvFilePath));
			    byte[] buffer = new byte[fis.available()];
			    fis.read(buffer);
			    fis.close();
			    response.reset();
			    // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			    response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
			    response.addHeader("Content-Length", "" + file.length());
			    OutputStream os = new BufferedOutputStream(response.getOutputStream());
			    response.setContentType("application/octet-stream");
			    os.write(buffer);// 输出文件
			    os.flush();
			    os.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally{
			if(wr != null){
				wr.close();
			}
		}
	  }
	public HotKeywordService getHotKeywordService() {
		return hotKeywordService;
	}

	public void setHotKeywordService(HotKeywordService hotKeywordService) {
		this.hotKeywordService = hotKeywordService;
	}
	
	  
	  
}
