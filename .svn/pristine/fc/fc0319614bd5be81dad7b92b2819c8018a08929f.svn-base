package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrLog;


/**
 * 日志业务表
 * @author qining
 *
 */
public interface LogService {
	 /**
	  * 分页查询日志表数据
	 * @param map 查询条件存放在map里面
	 * @return
	 */
	public List<MgrLog> selectAndPage(Map<String,Object> map);
	 /**
	  * 查询数据条数
	 * @param map 查询条件存放在map里面
	 * @return {@link Integer}
	 */
	public int selectPageTotal(Map<String,Object> map);
	 /**
	  * 向日志表里面插入数据
	 * @param log 日志实体类 {@link MgrLog}
	 * @return
	 */
	public int insertSelective(MgrLog log);
}
