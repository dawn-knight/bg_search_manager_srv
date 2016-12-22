package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrHotKeyword;

public interface MgrHotKeywordMapper {

	int deleteByPrimaryKey(Long id);
	

	int deleteBatchByList(List<Long> ids);
	
	int insert(MgrHotKeyword record);

	int insertSelective(MgrHotKeyword record);

	List<MgrHotKeyword> selectByPrimaryKey(Long id);
	
	MgrHotKeyword selectByOther(MgrHotKeyword record);

	int updateByPrimaryKeySelective(MgrHotKeyword record);

	int updateByPrimaryKey(MgrHotKeyword record);
	
	List<MgrHotKeyword> selectAndPage(Map<String,Object> map);
	List<MgrHotKeyword> selectByCode(String code);
	public int selectPageTotal(Map<String,Object> map);
}
