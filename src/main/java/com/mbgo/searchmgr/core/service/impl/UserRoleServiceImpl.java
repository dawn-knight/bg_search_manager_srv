package com.mbgo.searchmgr.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.mapper.MgrUserRoleMapper;
import com.mbgo.searchmgr.core.service.UserRoleService;

/**
 * UserRoleService接口实现类
 * @author qining
 * 
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private MgrUserRoleMapper mgrUserRoleMapper;
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserRoleService#queryRoleByUid(java.lang.Long)
	 */
	@Override
	public List<MgrUserRole> queryRoleByUid(Long userId) {
		List<MgrUserRole> list = mgrUserRoleMapper.queryRoleByUid(userId);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserRoleService#queryByRid(java.lang.Long)
	 */
	public List<MgrUserRole> queryByRid(Long roleId) {
		List<MgrUserRole> list = mgrUserRoleMapper.queryByRid(roleId);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserRoleService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> userId) {
		return mgrUserRoleMapper.deleteBatchByList(userId);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserRoleService#updateBatchByList(com.mbgo.searchmgr.core.bean.MgrUserRole)
	 */
	public int updateBatchByList(MgrUserRole ur){
		return mgrUserRoleMapper.updateByUserId(ur);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserRoleService#addBatch(java.util.List)
	 */
	public int addBatch(List<MgrUserRole> list){
		return mgrUserRoleMapper.addBatch(list);
	}
	
	
}
