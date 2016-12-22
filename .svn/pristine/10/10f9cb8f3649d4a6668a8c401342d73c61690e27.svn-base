package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrHotSearchKeyword;

public interface MgrHotSearchKeywordMapper {

	int deleteByPrimaryKey(Integer id);

	int deleteBatchByList(List<Long> ids);
	
	int insert(MgrHotSearchKeyword record);

	int insertSelective(MgrHotSearchKeyword record);

	List<MgrHotSearchKeyword> selectByPrimaryKey(Integer id);
	
	MgrHotSearchKeyword selectByPrimaryKey(MgrHotSearchKeyword record);
	
	MgrHotSearchKeyword selectByOther(MgrHotSearchKeyword record);

	int updateByPrimaryKeySelective(MgrHotSearchKeyword record);

	int updateByPrimaryKey(MgrHotSearchKeyword record);
	List<MgrHotSearchKeyword> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	
}
