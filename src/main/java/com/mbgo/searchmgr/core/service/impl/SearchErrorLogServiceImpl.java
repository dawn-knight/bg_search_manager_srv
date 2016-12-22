package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.SearchErrorLog;
import com.mbgo.searchmgr.core.mapper.SearchErrorLogMapper;
import com.mbgo.searchmgr.core.service.SearchErrorLogService;

/**
 * SearchErrorLogService接口实现类
 * @author qining
 * 
 */
@Service("searchErrorLogService")
public class SearchErrorLogServiceImpl implements SearchErrorLogService{

	@Autowired
	private SearchErrorLogMapper searchErrorLog;

	@Override
	public List<SearchErrorLog> selectAndPage(Map<String, Object> map) {
		return searchErrorLog.selectAndPage(map);
	}

	@Override
	public int selectPageTotal(Map<String, Object> map) {
		return searchErrorLog.selectPageTotal(map);
	}
	public int deleteBatchByList(List<Long> ids){
		return searchErrorLog.deleteBatchByList(ids);
	}
	

}
