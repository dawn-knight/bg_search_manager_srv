package com.mbgo.searchmgr.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.searchmgr.core.bean.MgrBaseKeyword;
import com.mbgo.searchmgr.core.bean.MgrDicKeyword;
import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.bean.MgrHotKeyword;
import com.mbgo.searchmgr.core.bean.MgrHotSearchKeyword;
import com.mbgo.searchmgr.core.bean.MgrSameKeyword;
import com.mbgo.searchmgr.core.bean.MgrTagsInfo;
import com.mbgo.searchmgr.core.service.BaseKeywordService;
import com.mbgo.searchmgr.core.service.GoodsTagsService;
import com.mbgo.searchmgr.core.service.HotKeywordService;
import com.mbgo.searchmgr.core.service.HotSearchKeywordService;
import com.mbgo.searchmgr.core.service.KeywordService;
import com.mbgo.searchmgr.core.service.SameKeywordService;
import com.mbgo.searchmgr.core.service.TagsInfoService;
import com.mbgo.searchmgr.core.util.ResultUtil;
import com.mbgo.searchmgr.core.util.TranUtil;
import com.mongodb.BasicDBList;

@Controller
@RequestMapping("/dataTransform")
public class DataTransform {
	
	public final static String PACKAGE = "pages/view/word";
	
	@Autowired
	private HotKeywordService hotKeywordService;
	@Autowired
	private TagsInfoService tagsInfoService; 
	@Autowired
	private GoodsTagsService goodsTagsService; 
	@Autowired
	private SameKeywordService sameKeywordService;
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private BaseKeywordService baseKeywordService;
	@Autowired
	private HotSearchKeywordService hotSearchKeywordService;
    
	public HotKeywordService getHotKeywordService() {
		return hotKeywordService;
	}
	public void setHotKeywordService(HotKeywordService hotKeywordService) {
		this.hotKeywordService = hotKeywordService;
	}
	
	
	  @RequestMapping(value = "/toDataTransform")
	    public String toDataTransform() {
	        return PACKAGE + "/dataTransform";
	    }
	
	  //总词库转换
	     @RequestMapping(value = "/addBaseKeyword")
		 @ResponseBody
		 public List<ResultUtil> addBaseKeyword(HttpServletRequest request) {
			 TranUtil tu = new TranUtil();
			 String tableName = "indexmgr_base_word";
			  ResultUtil ru = new ResultUtil();
			  List<ResultUtil> listR = new ArrayList<ResultUtil>();
			  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName);
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 MgrBaseKeyword mbk = null;
						 for (Map<String,Object> map: list) {
								 mbk = new MgrBaseKeyword();
								 String keyWord = (String) map.get("keyWord");
								 String wordCode = (String) map.get("wordCode");
								 Long totalCount = (Long) map.get("totalCount");
								 Long rsCount = (Long) map.get("rsCount");
								 Long addTime = (Long) map.get("addTime");
								 Long updateTime = (Long)map.get("updateTime");
								 mbk.setKeyword(keyWord);
								 mbk.setWordCode(wordCode);
								 mbk.setSearchCount(totalCount);
								 mbk.setRscount(rsCount);
								 mbk.setAddTime(addTime);
								 mbk.setLastUpdate(new Date(updateTime));
								 System.out.println(mbk.toString());
								 baseKeywordService.addBaseKeyword(mbk);
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrBaseKeyword mbk = null;
									 for (Map<String,Object> map : list) {
											 mbk = new MgrBaseKeyword();
											 String keyWord = (String) map.get("keyWord");
											 String wordCode = (String) map.get("wordCode");
											 Long totalCount = (Long) map.get("totalCount");
											 Long rsCount = (Long) map.get("rsCount");
											 Long addTime = (Long) map.get("addTime");
											 Long updateTime = (Long)map.get("updateTime");
											 mbk.setKeyword(keyWord);
											 mbk.setWordCode(wordCode);
											 mbk.setSearchCount(totalCount);
											 mbk.setRscount(rsCount);
											 mbk.setAddTime(addTime);
											 mbk.setLastUpdate(new Date(updateTime));
											 System.out.println(mbk.toString());
											 baseKeywordService.addBaseKeyword(mbk);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
		 }
	  
	     //热门关键字转换
	 @RequestMapping(value = "/addHotKeyword")
	 @ResponseBody
	  public List<ResultUtil> addHotKeyword(HttpServletRequest request) {
		 TranUtil tu = new TranUtil();
		 String tableName = "indexmgr_hot_word";
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName);
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 MgrHotKeyword mhk = null;
						 for (Map<String,Object> map: list) {
							    mhk = new MgrHotKeyword();
								String wordCode = (String) map.get("wordCode");
								Long searchCount = (Long) map.get("searchCount");
								String keyWord = (String) map.get("keyWord");
								Long addTime = (Long) map.get("addTime");
								Long updateTime = (Long)map.get("updateTime");
								mhk.setWordCode(wordCode);
								mhk.setWord(keyWord);
								mhk.setSearchCount(searchCount);
								mhk.setAddTime(addTime);
								mhk.setLastUpdate(new Date(updateTime));
								hotKeywordService.add(mhk);
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrHotKeyword mhk = null;
									 for (Map<String,Object> map : list) {
											    mhk = new MgrHotKeyword();
												String wordCode = (String) map.get("wordCode");
												Long searchCount = (Long) map.get("searchCount");
												String keyWord = (String) map.get("keyWord");
												Long addTime = (Long) map.get("addTime");
												Long updateTime = (Long)map.get("updateTime");
												mhk.setWordCode(wordCode);
												mhk.setWord(keyWord);
												mhk.setSearchCount(searchCount);
												mhk.setAddTime(addTime);
												mhk.setLastUpdate(new Date(updateTime));
												System.out.println(keyWord);
												hotKeywordService.add(mhk);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
			 
	  }
	 //同义词转换
	 @RequestMapping(value = "/addSameWord")
	 @ResponseBody
	 public List<ResultUtil> addSameWord(HttpServletRequest request) {
		 TranUtil tu = new TranUtil();
		 String tableName = "indexmgr_same_word";
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName);
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 MgrSameKeyword msk = null;
						 for (Map<String,Object> map: list) {
							 msk = new MgrSameKeyword();
							 BasicDBList keyWord = (BasicDBList) map.get("keyWord");
							 String str = "";
							 for(int k = 0;k<keyWord.size();k++){
								 str+=keyWord.get(k) +",";
							 }
							 String keyword = str.substring(0,str.length()-1);
							 System.out.println(keyword);
							 Long addTime = (Long) map.get("addTime");
							 Long updateTime = (Long)map.get("updateTime");
							 msk.setWords(keyword);
							 msk.setAddTime(addTime);
							 msk.setLastUpdate(new Date(updateTime));
							 sameKeywordService.add(msk);
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrSameKeyword msk = null;
									 for (Map<String,Object> map : list) {
										 msk = new MgrSameKeyword();
										 BasicDBList keyWord = (BasicDBList) map.get("keyWord");
										 String str = "";
										 for(int k = 0;k<keyWord.size();k++){
											 str+=keyWord.get(k) +",";
										 }
										 String keyword = str.substring(0,str.length()-1);
										 System.out.println(keyword);
										 Long addTime = (Long) map.get("addTime");
										 Long updateTime = (Long)map.get("updateTime");
										 msk.setWords(keyword);
										 msk.setAddTime(addTime);
										 msk.setLastUpdate(new Date(updateTime));
										 sameKeywordService.add(msk);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
		 
	 }
	 //商品标签库管理
	 @RequestMapping(value = "/addgoodsTags")
	 @ResponseBody
	 public List<ResultUtil> addgoodsTags(HttpServletRequest request) {
		 TranUtil tu = new TranUtil();
		 String tableName = "goods";
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName); 
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 for (Map<String,Object> map: list) {
							 if(map == null){
								 continue;
							 }
							 String goodsn = (String) map.get("_id");
							 BasicDBList keyWord = (BasicDBList) map.get("tags");
							 if(null == keyWord){
								 continue;
							 
							 }else{
								 Object addTimeString = map.get("updateTime");
								 Long addTime = System.currentTimeMillis() ;
									if(addTimeString != null) {
										addTime = Long.parseLong(addTimeString.toString());
									}
								 for(int k = 0;k<keyWord.size();k++){
									 MgrGoodsTags msk = new MgrGoodsTags();
									 String keyword = keyWord.get(k)+"";
									 msk.setTagWord(keyword);
									 msk.setIsLimited(1);
									 msk.setGoodsSn(String.valueOf(goodsn));
									 msk.setAddTime(new Date(addTime));
									 msk.setFlag(1);
									 goodsTagsService.addGoodsTags(msk);
								 }
							 }
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrGoodsTags msk = null;
									 for (Map<String,Object> map : list) {
										 msk = new MgrGoodsTags();
										 String goodsn = (String) map.get("_id");
										 BasicDBList keyWord = (BasicDBList) map.get("tags");
										 if(null == keyWord){
											 msk.setTagWord("");
										 
										 }else{
											 String str = "";
											 for(int k = 0;k<keyWord.size();k++){
												 str+=keyWord.get(k) +" ";
											 }
											 String keyword = str;
											 msk.setTagWord(keyword);
										 }
										 Long addTime = (Long) map.get("updateTime");
										
										 msk.setIsLimited(1);
										 msk.setGoodsSn(String.valueOf(goodsn));
										 msk.setAddTime(new Date(addTime));
										 msk.setFlag(1);
										 goodsTagsService.addGoodsTags(msk);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
		 
	 }
	 
	 @RequestMapping(value = "/addTagsInfo")
	 @ResponseBody
	 public List<ResultUtil> addTagsInfo(HttpServletRequest request) {
		 
		 TranUtil tu = new TranUtil();
		 String tableName = "indexmgr_new_tag";
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName);
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 MgrTagsInfo mti = null;
						 for (Map<String,Object> map: list) {
							 if(map == null){
								 continue;
							 }
							 mti = new MgrTagsInfo();
							 String keyWord = (String) map.get("name");
							 Long addTime = (Long) map.get("addTime");
							 int type =Integer.parseInt(map.get("type").toString());
							 Long updateTime = System.currentTimeMillis();
							 mti.setTagType(type);
							 mti.setTagSort(1);
							 mti.setTagWord(keyWord);
							 mti.setAddTime(addTime);
							 mti.setUpdateTime(new Date(updateTime));
							 tagsInfoService.add(mti);
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrTagsInfo mti = null;
									 for (Map<String,Object> map : list) {
										 mti = new MgrTagsInfo();
										 String keyWord = (String) map.get("keyWord");
										 Long addTime = (Long) map.get("addTime");
										 Long updateTime = (Long)map.get("updateTime");
										 mti.setTagType(1);
										 mti.setTagSort(1);
										 mti.setTagWord(keyWord);
										 mti.setAddTime(addTime);
										 mti.setUpdateTime(new Date(updateTime));
										 tagsInfoService.add(mti);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
	 }
	 
	 //热门搜索词
	 @RequestMapping(value = "/addHotSearch")
	 @ResponseBody
	 public List<ResultUtil> addHotSearch(HttpServletRequest request) {
		 
		 TranUtil tu = new TranUtil();
		 String tableName = "indexmgr_hot_keyword";
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName);
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 MgrHotSearchKeyword msk = null;
						 for (Map<String,Object> map: list) {
							 msk = new MgrHotSearchKeyword();
							 String word = (String) map.get("word");
							 Integer sort = (Integer) map.get("sort");
							 msk.setWord(word);
							 msk.setSortIndex(sort);
							 msk.setCategoryId("1");
							 msk.setChannelId("1");
			                 msk.setAddTime(new Date());
							 msk.setIndexLevel(1);
							 hotSearchKeywordService.add(msk);
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrHotSearchKeyword msk = null;
									 for (Map<String,Object> map : list) {
										 msk = new MgrHotSearchKeyword();
										 String word = (String) map.get("word");
										 Integer sort = (Integer) map.get("sort");
										 msk.setWord(word);
										 msk.setSortIndex(sort);
										 msk.setCategoryId("1");
										 msk.setChannelId("1");
						                 msk.setAddTime(new Date());
										 msk.setIndexLevel(1);
										 hotSearchKeywordService.add(msk);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
		 
	 }
	 
	 @RequestMapping(value = "/addBaseWord")
	 @ResponseBody
	 public List<ResultUtil> addBaseWord(HttpServletRequest request) {
		
		 TranUtil tu = new TranUtil();
		 String tableName = "indexmgr_industry_word";
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String,Object>> list = null;
			try{
			 int count = tu.getAllCount(tableName);
			 int pagesize = 0;
				if(count%5000 !=0){
					int num = count/5000+1;
					for (int i = 0;i<=num;i++) {
						 list = tu.getAllData(pagesize,count,tableName);
						 if(list == null) {
							 continue;
						 }
						 pagesize = pagesize + 5000;
						 MgrDicKeyword msk = null;
						 for (Map<String,Object> map: list) {
							 msk = new MgrDicKeyword();
							 String keyWord = (String) map.get("keyWord");
							 Integer weighing = (Integer) map.get("weighing");
							 Long addTime = (Long) map.get("addTime");
							 Long updateTime = (Long)map.get("updateTime");
							 msk.setWord(keyWord);
							 msk.setAddTime(addTime);
							 msk.setLastUpdate(new Date(updateTime));
							msk.setWeight(weighing);
							keywordService.addKeyWord(msk);
							 }
					}
					 ru.setStatus("提示信息");
					 ru.setMessage("添加成功");
					 listR.add(ru);
						}else{
							int num = count/10000;
							for (int i = 0;i<=num;i++) {
								  list = tu.getAllData(pagesize,count,tableName);
								  if(list == null) {
										 continue;
									 }
								  pagesize = pagesize+10000;
								  MgrDicKeyword msk = null;
									 for (Map<String,Object> map : list) {
										 msk = new MgrDicKeyword();
										 String keyWord = (String) map.get("keyWord");
										 Integer weighing = (Integer) map.get("weighing");
										 Long addTime = (Long) map.get("addTime");
										 Long updateTime = (Long)map.get("updateTime");
										 msk.setWord(keyWord);
										 msk.setAddTime(addTime);
										 msk.setLastUpdate(new Date(updateTime));
										 msk.setWeight(weighing);
										 keywordService.addKeyWord(msk);
										 }
									 ru.setStatus("提示信息");
									 ru.setMessage("添加成功");
									 listR.add(ru);
							}
						}
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
	 }

	 @RequestMapping(value = "/tranGoodsSn")
	 @ResponseBody
	 public List<ResultUtil> tranGoodsSn(HttpServletRequest request) {
		
		  ResultUtil ru = new ResultUtil();
		  List<ResultUtil> listR = new ArrayList<ResultUtil>();
		  List<Map<String, Object>> raw = null;
		  int rowCount = 0;
			try{
			   raw = goodsTagsService.selectGoodssnCount();
			   rowCount = raw.size();
			   int currentPage = 0;
			   int pageSize = 5000;
			   Map<String,Object> map = new HashMap<String,Object>();
			   if(rowCount%5000 !=0){
					 int num = rowCount/5000+1;
					  for(int i = 0;i<num;i++){
						  map.put("currentPage", currentPage);
						  map.put("pageSize",pageSize);
						  List<Map<String, Object>> list = goodsTagsService.selectGoodssn(map);
						  if(list == null) {
								 continue;
							 }
						  goodsTagsService.TranAndInsert(list);
						  currentPage = currentPage + 1;
			  }
			}else{
				int num = rowCount/5000;
				for (int i = 0;i<=num;i++) {
					 map.put("currentPage", currentPage);
					 map.put("pageSize",pageSize);
					 List<Map<String, Object>> list = goodsTagsService.selectGoodssn(map);
					if(list == null) {
						 continue;
					 }
					goodsTagsService.TranAndInsert(list);
					 currentPage = currentPage + 1;
				}
			}
			   ru.setStatus("提示信息");
			   ru.setMessage("添加成功");
			   listR.add(ru);
			}catch (Exception e) {
				e.printStackTrace();
				ru = new ResultUtil();
				 ru.setStatus("提示信息");
				 ru.setMessage("添加失败");
				 listR.add(ru);
			}	
			 return listR;
	 }	
   
   
}
