package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrConvertKeyword;
import com.mbgo.searchmgr.core.mapper.MgrConvertKeywordMapper;
import com.mbgo.searchmgr.core.service.ConvertKeywordService;



/**
 *  ConvertKeywordService接口实现类
 * @author qining
 *
 */
@Service("convertKeywordService")
public class ConvertKeywordServiceImpl implements ConvertKeywordService{
	
	@Autowired
	private MgrConvertKeywordMapper convertKeywordMapper;


	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#addConvertKeyWord(com.mbgo.searchmgr.core.bean.MgrConvertKeyword)
	 */
	@Override
	public int addConvertKeyWord(MgrConvertKeyword keyword) {
		return convertKeywordMapper.insertSelective(keyword);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#deleteConvertKeyword(java.lang.Long)
	 */
	@Override
	public int deleteConvertKeyword(Long id) {
		return convertKeywordMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#updateConvertKeyword(com.mbgo.searchmgr.core.bean.MgrConvertKeyword)
	 */
	@Override
	public int updateConvertKeyword(MgrConvertKeyword keyword) {
		return convertKeywordMapper.updateByPrimaryKeySelective(keyword);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return convertKeywordMapper.deleteBatchByList(ids);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#selectAndPage(java.util.Map)
	 */
	@Override
	public List<MgrConvertKeyword> selectAndPage(Map<String, Object> map) {
		return convertKeywordMapper.selectAndPage(map);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#selectPageTotal(java.util.Map)
	 */
	@Override
	public int selectPageTotal(Map<String, Object> map) {
		return convertKeywordMapper.selectPageTotal(map);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.ConvertKeywordService#queryConvertKeyword()
	 */
	@Override
	public List<MgrConvertKeyword> queryConvertKeyword() {
		// TODO Auto-generated method stub
		return null;
	}

}
