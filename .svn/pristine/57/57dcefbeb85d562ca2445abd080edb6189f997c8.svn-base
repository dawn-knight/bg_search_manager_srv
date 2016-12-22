package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrSameKeyword;
import com.mbgo.searchmgr.core.mapper.MgrSameKeywordMapper;
import com.mbgo.searchmgr.core.service.SameKeywordService;

/**
 * SameKeywordService接口实现类
 * @author qining
 * 
 */

@Service("sameKeywordService")
public class SameKeywordServiceImpl implements SameKeywordService{
	
	@Autowired
	private MgrSameKeywordMapper sameKeywordMapper;

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.SameKeywordService#query()
	 */
	@Override
	public List<MgrSameKeyword> query() {
		List<MgrSameKeyword> list = sameKeywordMapper.selectByPrimaryKey(1l);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.SameKeywordService#add(com.mbgo.searchmgr.core.bean.MgrSameKeyword)
	 */
	@Override
	public int add(MgrSameKeyword sameKeyword) {
		return sameKeywordMapper.insertSelective(sameKeyword);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.SameKeywordService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id){
		return sameKeywordMapper.deleteByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#updateByPrimaryKeySelective(com.mbgo.searchmgr.core.mongodb.bean.HotKeyword)
	 */
	public int updateByPrimaryKeySelective(MgrSameKeyword sameKeyword){
		return sameKeywordMapper.updateByPrimaryKeySelective(sameKeyword);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.SameKeywordService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return sameKeywordMapper.deleteBatchByList(ids);
	}
	

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.SameKeywordService#selectAndPage(java.util.Map)
	 */
	public List<MgrSameKeyword> selectAndPage(Map<String,Object> map){
		return sameKeywordMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.SameKeywordService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return sameKeywordMapper.selectPageTotal(map);
	}
}
