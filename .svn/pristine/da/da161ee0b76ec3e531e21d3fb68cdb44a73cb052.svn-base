package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrHotSearchKeyword;
import com.mbgo.searchmgr.core.mapper.MgrHotSearchKeywordMapper;
import com.mbgo.searchmgr.core.service.HotSearchKeywordService;

/**
 *  HotSearchKeywordService接口实现类
 * @author qining
 */
@Service("hotSearchKeywordService")
public class HotSearchKeywordServiceImpl implements HotSearchKeywordService{
	
	@Autowired
	private MgrHotSearchKeywordMapper searchKeywordMapper;

	@Override
	public int add(MgrHotSearchKeyword hotSearchKeyword) {
		return searchKeywordMapper.insertSelective(hotSearchKeyword);
	}
	
	@Override
	public int delete(Integer id){
		return searchKeywordMapper.deleteByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.HotKeywordService#updateByPrimaryKeySelective(com.mbgo.searchmgr.core.mongodb.bean.HotKeyword)
	 */
	public int updateByPrimaryKeySelective(MgrHotSearchKeyword hotSearchKeyword){
		return searchKeywordMapper.updateByPrimaryKeySelective(hotSearchKeyword);
	}
	
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return searchKeywordMapper.deleteBatchByList(ids);
	}
	
	public List<MgrHotSearchKeyword> selectAndPage(Map<String,Object> map){
		return searchKeywordMapper.selectAndPage(map);
	}
	public int selectPageTotal(Map<String,Object> map){
		return searchKeywordMapper.selectPageTotal(map);
	}
}
