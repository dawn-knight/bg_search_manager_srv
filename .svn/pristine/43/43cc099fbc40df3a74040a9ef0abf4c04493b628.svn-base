package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrLog;
import com.mbgo.searchmgr.core.mapper.MgrLogMapper;
import com.mbgo.searchmgr.core.service.LogService;

/**
 * LogService接口实现类
 * @author qining
 * 
 */

@Service("logService")
public class LogServiceImpl implements LogService{

	@Autowired
	private MgrLogMapper mgrLogMapper;
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.LogService#selectAndPage(java.util.Map)
	 */
	@Override
	public List<MgrLog> selectAndPage(Map<String, Object> map) {
		return mgrLogMapper.selectAndPage(map);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.LogService#selectPageTotal(java.util.Map)
	 */
	@Override
	public int selectPageTotal(Map<String, Object> map) {
		return mgrLogMapper.selectPageTotal(map);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.LogService#insertSelective(com.mbgo.searchmgr.core.bean.MgrLog)
	 */
	@Override
	public int insertSelective(MgrLog log) {
		return mgrLogMapper.insertSelective(log);
	}

}
