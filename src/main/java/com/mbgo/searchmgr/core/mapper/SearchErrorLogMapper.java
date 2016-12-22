package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.SearchErrorLog;

public interface SearchErrorLogMapper {
	public List<SearchErrorLog> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	
	public int deleteBatchByList(List<Long> ids);
}