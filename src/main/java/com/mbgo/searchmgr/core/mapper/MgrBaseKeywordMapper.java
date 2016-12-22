package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrBaseKeyword;

public interface MgrBaseKeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MgrBaseKeyword record);

    int insertSelective(MgrBaseKeyword record);

    MgrBaseKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MgrBaseKeyword record);

    int updateByPrimaryKey(MgrBaseKeyword record);
    
    List<MgrBaseKeyword> selectAndPage(Map<String,Object> map);
    List<MgrBaseKeyword> exportSelect(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	
	public int deleteBatchByList(List<Long> ids);
	public List<MgrBaseKeyword> selectByCode(String code);
	public MgrBaseKeyword selectDataByWordcode(String code);
}