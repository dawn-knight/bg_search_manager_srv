package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.mbgo.searchmgr.core.bean.MgrSearchwordLog;
import com.mbgo.searchmgr.core.bean.SearchwordLog;

/**
 * 关键字搜索量信息service接口
 * @author qining
 *
 */
public interface SearchWordService {


	
    /**
     * 分页查询方法
     * @param map 里面存放的key=value要查询的范围
     * @return
     */
    public List<MgrSearchwordLog> selectAndPage(Map<String,Object> map);
    public List<MgrSearchwordLog> exportWord(Map<String,Object> map);
	
    
	/** 根据分页查询条件查询总条数
	 * @param map 里面存放的key=value要查询的范围
	 * @return
	 */
	public int selectPageTotal(Map<String,Object> map);
	
	
	public void downLoadSearchword(HttpServletResponse response, List<SearchwordLog> list);
	
}
