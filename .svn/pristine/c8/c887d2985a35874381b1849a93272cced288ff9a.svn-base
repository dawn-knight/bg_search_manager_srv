package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrDicKeyword;

public interface MgrDicKeywordMapper {
	int deleteByPrimaryKey(Long id);
	
	int deleteBatchByList(List<Long> ids);
	
	int insert(MgrDicKeyword record);

	int insertSelective(MgrDicKeyword record);

	List<MgrDicKeyword> selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MgrDicKeyword record);

	int updateByPrimaryKey(MgrDicKeyword record);
	List<MgrDicKeyword> selectAndPage(Map<String,Object> map);
	public List<MgrDicKeyword> selectPageTotal(Map<String,Object> map);
}
