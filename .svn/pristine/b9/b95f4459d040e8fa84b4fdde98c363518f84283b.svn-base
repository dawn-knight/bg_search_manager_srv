package com.mbgo.searchmgr.core.mapper;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MgrUserRole;

public interface MgrUserRoleMapper {


	int deleteByPrimaryKey(Long id);

	int insert(MgrUserRole record);

	int insertSelective(MgrUserRole record);

	MgrUserRole selectByPrimaryKey(Long id);
	
	List<MgrUserRole> queryRoleByUid(Long userId);
	List<MgrUserRole> queryByRid(Long roleId);
	
	MgrUserRole queryRolePK(Long roleId);

	int updateByPrimaryKeySelective(MgrUserRole record);

	int updateByPrimaryKey(MgrUserRole record);
	public int deleteBatchByList(List<Long> userId);
	public int updateByUserId(MgrUserRole ur);
	public int addBatch(List<MgrUserRole> list);
}
