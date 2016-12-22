package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrSameKeyword;

public interface MgrSameKeywordMapper {


	int deleteByPrimaryKey(Long id);

	int deleteBatchByList(List<Long> ids);
	
	int insert(MgrSameKeyword record);

	int insertSelective(MgrSameKeyword record);

	List<MgrSameKeyword>  selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MgrSameKeyword record);

	int updateByPrimaryKey(MgrSameKeyword record);
	
	List<MgrSameKeyword> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
}
