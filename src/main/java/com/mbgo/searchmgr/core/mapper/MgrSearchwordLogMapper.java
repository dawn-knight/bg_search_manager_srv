package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrSearchwordLog;

public interface MgrSearchwordLogMapper {

    int insert(MgrSearchwordLog record);

    int insertSelective(MgrSearchwordLog record);

    MgrSearchwordLog selectByPrimaryKey(Long id);
    public List<MgrSearchwordLog> selectAndPage(Map<String,Object> map);
    public List<MgrSearchwordLog> exportWord(Map<String,Object> map);
	
	public int selectPageTotal(Map<String,Object> map);
}