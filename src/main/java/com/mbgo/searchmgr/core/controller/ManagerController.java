package com.mbgo.searchmgr.core.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.mbgo.search.core.bean.index.Product;
import com.mbgo.search.core.bean.query.ProductQueryResult;
import com.mbgo.search.core.bean.update.UpdateOption;
import com.mbgo.search.util.HttpClientUtil;
import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.bean.MgrTagsInfo;
import com.mbgo.searchmgr.core.service.GoodsTagsService;
import com.mbgo.searchmgr.core.service.TagsInfoService;
import com.mbgo.searchmgr.core.util.CommonUtil;
import com.mbgo.searchmgr.core.util.ImportExcel;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/manager")
public class ManagerController{

	
	public final static String PACKAGE = "pages/view/word";
	@Autowired
	private GoodsTagsService goodsTagsService;
	
	@Autowired
	private TagsInfoService tagsInfoService;
	
	  @RequestMapping(value = "/goodsTagsManager")
	    public String goodsTagsManager() {
	        return PACKAGE + "/goodsTagsManager";
	    }
	   @RequestMapping(value = "/importGoodTags")
	   public String importGoodTags(HttpServletRequest request, @RequestParam("filename") MultipartFile file){
		   String [] tempPara = request.getParameterValues("ivalue");
		   String path = request.getSession().getServletContext().getRealPath("upload")+File.separator;  
		   String fileName = file.getOriginalFilename();  
		   List<ResultUtil> list = new ArrayList<ResultUtil>();
		   ResultUtil ru = new ResultUtil();
		   String createName = new Date().getTime() + fileName; 
		   String endPath = path+createName;
		   File targetFile = new File(path, createName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	      //保存  
	        try {  
	            file.transferTo(targetFile);  
	            List<MgrGoodsTags> listTags = ImportExcel.parseExcel(endPath,tempPara);
	            List<UpdateOption> listOption = new ArrayList<UpdateOption>();
	            UpdateOption option = null;
	            for(MgrGoodsTags mgrGoodsTags : listTags) {
	            	 List<MgrTagsInfo> listTag = tagsInfoService.queryTagsUnique(mgrGoodsTags.getTagWord());
	 				int sort =0;
	 				if(null != listTag && listTag.size()>0){
	 					sort = listTag.get(0).getTagSort();
	 				}
	 				mgrGoodsTags.setTagSort(sort);
	            	goodsTagsService.addGoodsTags(mgrGoodsTags);
	            	option = new UpdateOption();
	          	    option.setProductId(mgrGoodsTags.getGoodsSn());
					option.setType("update");
					listOption.add(option);
	          	    ru.setStatus("提示信息");
	          	  	ru.setMessage("导入成功");
	          	}
	            list.add(ru);
	               String urlupdate = new CommonUtil().loadProperties().getProperty("addProducturl");
	               for (UpdateOption updateOption : listOption) {
	            	   List<NameValuePair> params = new ArrayList<NameValuePair>();
	    			   params.add(new BasicNameValuePair("list",updateOption.getProductId()));
	    			   params.add(new BasicNameValuePair("type","update"));
	    			   HttpClientUtil.post(urlupdate, params);
				       }
//				   List<NameValuePair> updateparams = new ArrayList<NameValuePair>();
//				   updateparams.add(new BasicNameValuePair("list",JSON.toJSONString(listOption)));
//				   HttpClientUtil.post(urlupdate, updateparams);
	        
	        } catch (Exception e) {  
	        	 ru.setStatus("提示信息");
        		 ru.setMessage("导入失败");
        	     e.printStackTrace();
	        }
		   return PACKAGE + "/goodsTagsManager";
		   
	   }
	   
	   @RequestMapping(value = "/queryTagsToSelect")
	   @ResponseBody
	   public List<MgrTagsInfo> queryTagsToSelect(HttpServletRequest request){
//		   List<MgrGoodsTags> list = new ArrayList<MgrGoodsTags>();
		   List<MgrTagsInfo> list = new ArrayList<MgrTagsInfo>();
		   String para = request.getParameter("para");
		   try{
			  list = tagsInfoService.queryTagsGroupBy(para);
		   }catch(Exception e) {
			   e.printStackTrace();
			   
		}
		   return list;
	   }
	   
	   @RequestMapping("searchProduct")
	   public void searchProduct(HttpServletRequest request,HttpServletResponse response){
		   try{
		   String pageSize = request.getParameter("pageSize");
		   String currentPage = request.getParameter("currentPage");
		   //关键字
		   String word = request.getParameter("word"); 
		   //平台商品id
		   String productId = request.getParameter("productId"); 
		   //色系
		   String color = request.getParameter("color"); 
		   //品牌
		   String brand = request.getParameter("brand"); 
		   //店铺ID
		   String storeId = request.getParameter("storeId"); 
		   //分类id
		   String cid = request.getParameter("cid"); 
		   //自定义价格段
		   String prMin = request.getParameter("prMin"); 
		   String prMax = request.getParameter("prMax"); 
		   //自定义折扣
		   String disMin = request.getParameter("disMin"); 
		   String disMax = request.getParameter("disMax"); 
		   //最低库存值
		   String stock = request.getParameter("stock"); 
		   //尺寸Code
//		   String sizeCode = request.getParameter("sizeCode"); 
		   //搜索类型
		   String searchType = request.getParameter("searchType");
		   //条件优先级
		   String andFirst = request.getParameter("andFirst");
		   //排序类型
		   String sortType = request.getParameter("sortType");
		    //排序顺序
		   String sortField = request.getParameter("sortField");
		   String priceRange = "["+prMin+"-"+prMax+"]";
		   //排序
//		   String sortCol = request.getParameter("sortCol"); 
		   //价格区间
//		   String pin = request.getParameter("pin"); 
		   //折扣
		   String discountRange = "["+disMin+"-"+disMax+"]";
		   String url = new CommonUtil().loadProperties().getProperty("searchurl");
		   List<NameValuePair> params = new ArrayList<NameValuePair>();
		   params.add(new BasicNameValuePair("pageNo",currentPage));
		   params.add(new BasicNameValuePair("pageSize",pageSize));
		   params.add(new BasicNameValuePair("word",word));
		   params.add(new BasicNameValuePair("productId",productId));
		   params.add(new BasicNameValuePair("color",color));
		   params.add(new BasicNameValuePair("brand",brand));
		   params.add(new BasicNameValuePair("storeId",storeId));
		   params.add(new BasicNameValuePair("cid",cid));
		   params.add(new BasicNameValuePair("priceRange",priceRange));
		   params.add(new BasicNameValuePair("discountRange",discountRange));
		   params.add(new BasicNameValuePair("sortType",sortType));
		   params.add(new BasicNameValuePair("stock",stock));
		   params.add(new BasicNameValuePair("searchType",searchType));
		   params.add(new BasicNameValuePair("andFirst",andFirst));
		   params.add(new BasicNameValuePair("sortType",sortType));
		   params.add(new BasicNameValuePair("sortField",sortField));
		   String json =  HttpClientUtil.get(url, params);
		   ProductQueryResult result = new ProductQueryResult();
		   result = JSON.parseObject(json, ProductQueryResult.class);
		   Long total = result.getTotal();
		   List<Product> list = result.getList();
		   String resultJson = "{\"total\":"+total+" , \"rounded\":"+pageSize+", \"currentPage\":"+currentPage+", \"rows\":"+JSONArray.fromObject(list)+"}";
		   response.setContentType("text/html;charset=utf-8");
		   response.getWriter().print(resultJson);
		   }catch (Exception e) {
			   e.printStackTrace();
		 }
		   
	   }
	   @RequestMapping("searchProductBytag")
	   public void searchProductBytag(HttpServletRequest request,HttpServletResponse response){
		   try{
			   String pageSize = request.getParameter("pageSize");
			   String pageNo = request.getParameter("currentPage");
			   String [] tags = request.getParameterValues("tagName");
			   StringBuilder append = new StringBuilder();
			   String tagPara = "";
			   if(null != tags){
			   for (String tag : tags) {
				   append.append(tag).append(",");
			  }
			   tagPara = append.substring(0,append.length()-1);
			   }
//			   String url = "http://10.100.20.246/MBgoSearchSrv/search/qg.do";
			   String url = new CommonUtil().loadProperties().getProperty("searchurl");
			   List<NameValuePair> params = new ArrayList<NameValuePair>();
			   params.add(new BasicNameValuePair("pageNo",pageNo));
			   params.add(new BasicNameValuePair("pageSize",pageSize));
			   params.add(new BasicNameValuePair("tags",tagPara));
			   String json =  HttpClientUtil.get(url, params);
			   ProductQueryResult result = new ProductQueryResult();
			   result = JSON.parseObject(json, ProductQueryResult.class);
			   Long total = result.getTotal();
			   List<Product> list = result.getList();
			   String resultJson = "{\"total\":"+total+" , \"rounded\":"+pageSize+", \"currentPage\":"+pageNo+", \"rows\":"+JSONArray.fromObject(list)+"}";
			   response.setContentType("text/html;charset=utf-8");
			   response.getWriter().print(resultJson);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   
	   }
	   
	   @RequestMapping("addProductById")
	   @ResponseBody
	   public List<ResultUtil> addProductById(HttpServletRequest request,HttpServletResponse response){
		   List<MgrGoodsTags> isExist = new ArrayList<MgrGoodsTags>();
		   List<ResultUtil> result = new ArrayList<ResultUtil>();
		   ResultUtil ru = new ResultUtil();
		   try{
			   String tagName = request.getParameter("tag");  
			   String tags = request.getParameter("param"); 
			   List<MgrTagsInfo> listTags = tagsInfoService.queryTagsUnique(tagName);
				int sort =0;
				if(null != listTags && listTags.size()>0){
					sort = listTags.get(0).getTagSort();
				}
			   MgrGoodsTags mgrGoodsTags = new MgrGoodsTags();
			   mgrGoodsTags.setGoodsSn(tags);
			   mgrGoodsTags.setTagWord(tagName);
			   mgrGoodsTags.setAddTime(new Date());
			   mgrGoodsTags.setFlag(1);
			   mgrGoodsTags.setTagSort(sort);
			   mgrGoodsTags.setIsLimited(1);
			   isExist = goodsTagsService.selectGoodsTagsByword(mgrGoodsTags);
				if(isExist.size()<=0){
					goodsTagsService.addGoodsTags(mgrGoodsTags);
					ru.setStatus("提示信息");
					ru.setMessage("添加成功");
				}else{
					ru.setStatus("提示信息");
					ru.setMessage("要添加的标签已存在");
				}
//				String url ="http://10.100.20.246/MBgoSearchSrv/search/updateProductTagsByIds.do";
				String url = new CommonUtil().loadProperties().getProperty("addProducturl");
			   List<NameValuePair> params = new ArrayList<NameValuePair>();
			   params.add(new BasicNameValuePair("list",tags));
			   params.add(new BasicNameValuePair("type","update"));
			   HttpClientUtil.post(url, params);
		   }catch (Exception e) {
			   e.printStackTrace();
			   ru.setStatus("提示信息");
			   ru.setMessage("添加失败");
		   }
		   result.add(ru);
		   return result;
	   }
	   
	   @RequestMapping("getGoodTags")
	   @ResponseBody
	   public List getGoodTags(HttpServletRequest request,HttpServletResponse response){
		   List<String> list = null;
		   try{
			   list = new ArrayList<String>();
			   String tags = request.getParameter("paramTag"); 
			   list =  tagsInfoService.queryTags(tags);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   return list;
		   
	   }
	   @RequestMapping("deleteProductById")
	   @ResponseBody
	   public List<String> deleteProductById(HttpServletRequest request,HttpServletResponse response){
		    List<String> list = null;
		   try{
			   list = new ArrayList<String>();
			   String tags = request.getParameter("param"); 
			   String tagName = request.getParameter("tag");  
			   MgrGoodsTags mgrGoodsTags = new MgrGoodsTags();
			   mgrGoodsTags.setGoodsSn(tags);
			   mgrGoodsTags.setTagWord(tagName);
			   mgrGoodsTags.setFlag(0);
			   goodsTagsService.updateToFlag(mgrGoodsTags);
//			   goodsTagsService.deleteGoodsTagsByword(mgrGoodsTags);
			   
//			   String url = "http://10.100.20.246/MBgoSearchSrv/search/upsertProductById.do";
//			   String url ="http://10.100.20.246/MBgoSearchSrv/search/updateProductTagsByIds.do";
			   String url = new CommonUtil().loadProperties().getProperty("addProducturl");
			   List<NameValuePair> params = new ArrayList<NameValuePair>();
			   params.add(new BasicNameValuePair("list",tags));
			   params.add(new BasicNameValuePair("type","update"));
			   HttpClientUtil.post(url, params);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   return list;
	   }
	  
	   @RequestMapping("addBatchProductById")
	   @ResponseBody
	   public List<String> addBatchProductById(HttpServletRequest request,HttpServletResponse response){
		    List<String> list = null;
		    List<UpdateOption> listOption = null;
//		    StringBuilder paraAppend = new StringBuilder("");
		   try{
			   List<MgrGoodsTags> isExist = new ArrayList<MgrGoodsTags>();
			   MgrGoodsTags goodsTags = null;
			   list = new ArrayList<String>();
			   String yescon = request.getParameter("yescon");  
			   String yestag = request.getParameter("yestag"); 
			   String[] valueStr = request.getParameterValues("bvalue");
			   String[] pIdStr = request.getParameterValues("bProductId");
			   if(yescon.equals("1") && yestag.equals("1")){
			for (String pid : pIdStr) {
					for (String tag : valueStr) {
						int sort =0;
						List<MgrTagsInfo> listTags = tagsInfoService.queryTagsUnique(tag);
						if(null != listTags && listTags.size()>0){
							sort = listTags.get(0).getTagSort();
						}
						goodsTags = new MgrGoodsTags();
						goodsTags.setGoodsSn(pid);
						goodsTags.setTagWord(tag);
						goodsTags.setFlag(1);
						goodsTags.setIsLimited(1);
						goodsTags.setTagSort(sort);
						goodsTags.setAddTime(new Date());
						isExist = goodsTagsService.selectGoodsTagsByword(goodsTags);
						if(isExist.size()<=0){
							goodsTagsService.addGoodsTags(goodsTags);
						}
					}
				}
			   listOption = new ArrayList<UpdateOption>();
			   for (String string : pIdStr) {
				   UpdateOption option = new UpdateOption();
				   option.setProductId(string);
				   option.setType("update");
				   listOption.add(option);
//				   paraAppend.append(string).append(","); 
				  
			}
			  String url = new CommonUtil().loadProperties().getProperty("addProducturl");
			   for (UpdateOption updateOption : listOption) {
				   List<NameValuePair> params = new ArrayList<NameValuePair>();
    			   params.add(new BasicNameValuePair("list",updateOption.getProductId()));
    			   params.add(new BasicNameValuePair("type","update"));
    			   HttpClientUtil.post(url, params);
			}
			}
			   if(yescon.equals("1") && yestag.equals("2")){
				   for (String pid : pIdStr) {
					for (String tag : valueStr) {
						int sort =0;
						List<MgrTagsInfo> listTags = tagsInfoService.queryTagsUnique(tag);
						if(null != listTags && listTags.size()>0){
							sort = listTags.get(0).getTagSort();
						}
						goodsTags = new MgrGoodsTags();
						goodsTags.setGoodsSn(pid);
						goodsTags.setTagWord(tag);
						goodsTags.setFlag(1);
						goodsTags.setIsLimited(1);
						goodsTags.setTagSort(sort);
						goodsTags.setAddTime(new Date());
						isExist = goodsTagsService.selectGoodsTagsByword(goodsTags);
						if(isExist.size()>0){
							goodsTagsService.deleteGoodsTagsByword(goodsTags);
						}
					}
				}
			    listOption = new ArrayList<UpdateOption>();
			   for (String string : pIdStr) {
				   UpdateOption option = new UpdateOption();
				   option.setProductId(string);
				   option.setType("update");
				   listOption.add(option);
//				   String url = "http://10.100.20.246/MBgoSearchSrv/search/updateProductTagsByIds.do";
				   String url = new CommonUtil().loadProperties().getProperty("addProducturl");
//				   List<NameValuePair> params = new ArrayList<NameValuePair>();
//				   params.add(new BasicNameValuePair("list",JSON.toJSONString(listOption)));
//				   String result = HttpClientUtil.post(url, params);
				   for (UpdateOption updateOption : listOption) {
					   List<NameValuePair> params = new ArrayList<NameValuePair>();
	    			   params.add(new BasicNameValuePair("list",updateOption.getProductId()));
	    			   params.add(new BasicNameValuePair("type","update"));
	    			   HttpClientUtil.post(url, params);
				}
			}
			}
			   
			   
			   if(yescon.equals("2") && yestag.equals("1")){
//				   String url = "http://10.100.20.246/MBgoSearchSrv/search/qg.do";
				   String url = new CommonUtil().loadProperties().getProperty("searchurl");
				   List<NameValuePair> params = new ArrayList<NameValuePair>();
				   String json =  HttpClientUtil.get(url, params);
				   ProductQueryResult result = new ProductQueryResult();
				   result = JSON.parseObject(json, ProductQueryResult.class);
				   Long total = result.getTotal();
				   String batchJson = "";
				   ProductQueryResult batchresult = null;
				   int pageNo = 1;
				   int pagesize = 200;
				   long num = (total/200)+1;
				   
				   
//				   String commonUrl = "http://10.100.20.246/MBgoSearchSrv/search/qg.do";
				   String commonUrl = new CommonUtil().loadProperties().getProperty("searchurl");
				   List<NameValuePair> commonParams = null;
				   for (int i = 0;i <= num;i++) {
					   System.out.println(String.valueOf(pageNo));
					   commonParams = new ArrayList<NameValuePair>();
					   commonParams.add(new BasicNameValuePair("pageNo",String.valueOf(pageNo)));
					   commonParams.add(new BasicNameValuePair("pageSize",String.valueOf(pagesize)));
					   batchJson =  HttpClientUtil.get(commonUrl, commonParams);
					   batchresult = JSON.parseObject(batchJson, ProductQueryResult.class);
					   List<Product> listPro = batchresult.getList();
						pageNo +=1;
						listOption = new ArrayList<UpdateOption>();
						for (Product product : listPro) {
							if(product != null){
							for (String tag : valueStr) {
								int sort =0;
								List<MgrTagsInfo> listTags = tagsInfoService.queryTagsUnique(tag);
								if(null != listTags && listTags.size()>0){
									sort = listTags.get(0).getTagSort();
								}
								goodsTags = new MgrGoodsTags();
								goodsTags.setGoodsSn(product.getProductId());
								goodsTags.setTagWord(tag);
								goodsTags.setFlag(1);
								goodsTags.setIsLimited(1);
								goodsTags.setTagSort(sort);
								goodsTags.setAddTime(new Date());
								isExist = goodsTagsService.selectGoodsTagsByword(goodsTags);
								if(isExist.size()<=0){
									goodsTagsService.addGoodsTags(goodsTags);
								}
						     }
							UpdateOption option = new UpdateOption();
							option.setProductId(product.getProductId());
							option.setType("update");
							listOption.add(option);
					}
					}
//						   String urlupdate = "http://10.100.20.246/MBgoSearchSrv/search/updateProductTagsByIds.do";
						   String urlupdate = new CommonUtil().loadProperties().getProperty("addProducturl");
						   for (UpdateOption updateOption : listOption) {
							   List<NameValuePair> updateparams = new ArrayList<NameValuePair>();
			    			   params.add(new BasicNameValuePair("list",updateOption.getProductId()));
			    			   params.add(new BasicNameValuePair("type","update"));
			    			   HttpClientUtil.post(urlupdate, params);
						}
						
				}
	    }
			   
			   if(yescon.equals("2") && yestag.equals("2")){
//				   String url = "http://10.100.20.246/MBgoSearchSrv/search/qg.do";
				   String url = new CommonUtil().loadProperties().getProperty("searchurl");
				   List<NameValuePair> params = new ArrayList<NameValuePair>();
				   String json =  HttpClientUtil.get(url, params);
				   ProductQueryResult result = new ProductQueryResult();
				   result = JSON.parseObject(json, ProductQueryResult.class);
				   Long total = result.getTotal();
				   String batchJson = "";
				   ProductQueryResult batchresult = null;
				   int pageNo = 1;
				   int pagesize = 200;
				   long num = (total/20)+1;
				   
				   
//				   String commonUrl = "http://10.100.20.246/MBgoSearchSrv/search/qg.do";
				   String commonUrl = new CommonUtil().loadProperties().getProperty("searchurl");
				   List<NameValuePair> commonParams = null;
				   for (int i = 0;i <= num;i++) {
					   commonParams = new ArrayList<NameValuePair>();
					   commonParams.add(new BasicNameValuePair("pageNo",String.valueOf(pageNo)));
					   commonParams.add(new BasicNameValuePair("pageSize",String.valueOf(pagesize)));
					   batchJson =  HttpClientUtil.get(commonUrl, commonParams);
					   batchresult = JSON.parseObject(batchJson, ProductQueryResult.class);
					   
					   List<Product> listPro = batchresult.getList();
						pageNo +=1;
						listOption = new ArrayList<UpdateOption>();
						for (Product product : listPro) {
							if(product != null){
							for (String tag : valueStr) {
								int sort =0;
								List<MgrTagsInfo> listTags = tagsInfoService.queryTagsUnique(tag);
								if(null != listTags && listTags.size()>0){
									sort = listTags.get(0).getTagSort();
								}
								goodsTags = new MgrGoodsTags();
								goodsTags.setGoodsSn(product.getProductId());
								goodsTags.setTagWord(tag);
								goodsTags.setFlag(1);
								goodsTags.setIsLimited(1);
								goodsTags.setTagSort(sort);
								goodsTags.setAddTime(new Date());
								isExist = goodsTagsService.selectGoodsTagsByword(goodsTags);
								if(isExist.size()>0){
									goodsTagsService.deleteGoodsTagsByword(goodsTags);
								}
								
						     }
							UpdateOption option = new UpdateOption();
							option.setProductId(product.getProductId());
							option.setType("update");
							listOption.add(option);
					}
					}
//						   String urlupdate = "http://10.100.20.246/MBgoSearchSrv/search/updateProductTagsByIds.do";
						   String urlupdate = new CommonUtil().loadProperties().getProperty("addProducturl");
						   for (UpdateOption updateOption : listOption) {
							   List<NameValuePair> updateparams = new ArrayList<NameValuePair>();
			    			   params.add(new BasicNameValuePair("list",updateOption.getProductId()));
			    			   params.add(new BasicNameValuePair("type","update"));
			    			   HttpClientUtil.post(urlupdate, params);
						}
						
				}
	    }	   
    }   catch (Exception e) {
			   e.printStackTrace();
		   }
		   return list;
}
	   
	   
}