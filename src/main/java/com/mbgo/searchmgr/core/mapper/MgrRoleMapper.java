package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrSameKeyword;

public interface MgrRoleMapper {

	int deleteByPrimaryKey(Long id);

	int insert(MgrSameKeyword record);

	int insertSelective(MgrSameKeyword record);
	
	List<MgrRole> queryRoleId(Long id);
	
	MgrRole queryRoleIdM(Long id);

	MgrRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MgrSameKeyword record);

	int updateByPrimaryKey(MgrSameKeyword record);
	public List<MgrRole> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	public int insertSelective(MgrRole record);
	public int updateByPrimaryKeySelective(MgrRole role);

	public int deleteBatchByList(List<Long> ids);
	public List<MgrRole> queryRoleAll();
}
