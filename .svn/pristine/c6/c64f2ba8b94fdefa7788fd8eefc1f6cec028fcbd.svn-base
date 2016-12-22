package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrMenu;

public interface MgrMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MgrMenu record);

    int insertSelective(MgrMenu record);

    MgrMenu selectByPrimaryKey(Long id);
    MgrMenu selectByMenuId(Long id);
    List<MgrMenu> queryMenuAll();

    int updateByPrimaryKeySelective(MgrMenu record);

    int updateByPrimaryKey(MgrMenu record);
    
    public List<MgrMenu> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	public int updateRole(MgrMenu menu);

	public int deleteBatchByList(List<Long> ids);
	
	public List<MgrMenu> selectMenuAll();
	public List<MgrMenu> queryMenuUrl(String url);
	public List<MgrMenu> queryMenuUrlFuzzy(String url);
	public List<MgrMenu> queryMenuName(Long parentId);
}