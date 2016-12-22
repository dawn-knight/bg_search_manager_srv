package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrDicKeyword;
import com.mbgo.searchmgr.core.mapper.MgrDicKeywordMapper;
import com.mbgo.searchmgr.core.service.KeywordService;


/**
 * KeywordService接口实现类
 * @author qining
 * 
 */
@Service("keywordService")
public class KeywordServiceImpl implements KeywordService{
	
	@Autowired
	private MgrDicKeywordMapper dicKeywordMapper;

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#queryDicKeyword()
	 */
	@Override
	public List<MgrDicKeyword> queryDicKeyword() {
		List<MgrDicKeyword> list = dicKeywordMapper.selectByPrimaryKey(1l);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#addKeyWord(com.mbgo.searchmgr.core.mongodb.bean.Keyword)
	 */
	@Override
	public int addKeyWord(MgrDicKeyword keyword) {
		return dicKeywordMapper.insertSelective(keyword);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#deleteKeyword(java.lang.String[])
	 */
	@Override
	public int deleteKeyword(Long id){
		return dicKeywordMapper.deleteByPrimaryKey(id);
	}
	
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#updateKeyword(com.mbgo.searchmgr.core.mongodb.bean.Keyword)
	 */
	public int updateKeyword(MgrDicKeyword keyword){
		return dicKeywordMapper.updateByPrimaryKeySelective(keyword);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return dicKeywordMapper.deleteBatchByList(ids);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#selectAndPage(java.util.Map)
	 */
	public List<MgrDicKeyword> selectAndPage(Map<String,Object> map){
		return dicKeywordMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.KeywordService#selectPageTotal(java.util.Map)
	 */
	public List<MgrDicKeyword> selectPageTotal(Map<String,Object> map){
		return dicKeywordMapper.selectPageTotal(map);
	}
	
}
