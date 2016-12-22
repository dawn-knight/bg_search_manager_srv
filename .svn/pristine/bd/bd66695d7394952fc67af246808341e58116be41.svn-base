package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrConvertKeyword;

public interface MgrConvertKeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MgrConvertKeyword record);

    int insertSelective(MgrConvertKeyword record);

    MgrConvertKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MgrConvertKeyword record);

    int updateByPrimaryKey(MgrConvertKeyword record);
    
    public List<MgrConvertKeyword> selectAndPage(Map<String, Object> map);

	public int selectPageTotal(Map<String, Object> map);
	
	public int deleteBatchByList(List<Long> ids);
    
}