package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrHotKeyword;
import com.mbgo.searchmgr.core.mapper.MgrHotKeywordMapper;
import com.mbgo.searchmgr.core.service.HotKeywordService;

/**
 *  HotKeywordService接口实现类
 * @author qining
 *
 */
@Service("hotKeywordService")
public class HotKeywordServiceImpl implements HotKeywordService{
	
	@Autowired
	private MgrHotKeywordMapper mgrHotKeywordMapper;

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#query()
	 */
	@Override
	public List<MgrHotKeyword> query() {
		List<MgrHotKeyword> list = mgrHotKeywordMapper.selectByPrimaryKey(1l);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#add(com.mbgo.searchmgr.core.bean.MgrHotKeyword)
	 */
	@Override
	public int add(MgrHotKeyword mgrHotKeyword) {
		return mgrHotKeywordMapper.insertSelective(mgrHotKeyword);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id){
		return mgrHotKeywordMapper.deleteByPrimaryKey(id);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#updateByPrimaryKeySelective(com.mbgo.searchmgr.core.mongodb.bean.HotKeyword)
	 */
	public int updateByPrimaryKeySelective(MgrHotKeyword mgrHotKeyword){
		return mgrHotKeywordMapper.updateByPrimaryKeySelective(mgrHotKeyword);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return mgrHotKeywordMapper.deleteBatchByList(ids);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#selectAndPage(java.util.Map)
	 */
	public List<MgrHotKeyword> selectAndPage(Map<String,Object> map){
		return mgrHotKeywordMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return mgrHotKeywordMapper.selectPageTotal(map);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#selectByCode(java.lang.String)
	 */
	public List<MgrHotKeyword> selectByCode(String code){
		return mgrHotKeywordMapper.selectByCode(code);
	}
}
