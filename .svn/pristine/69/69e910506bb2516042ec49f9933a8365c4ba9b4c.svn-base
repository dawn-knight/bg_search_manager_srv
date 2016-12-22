package com.mbgo.searchmgr.core.service;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MgrUserRole;


/**
 * 用户角色业务接口类
 * @author qining
 *
 */
public interface UserRoleService {

	
	/**根据用户Id查询角色ID
	 * @param userId
	 * @return List<UserRole>
	 */
	public List<MgrUserRole> queryRoleByUid(Long userId);
	
	/**
	 * 根据角色id查询用户角色中间表数据
	 * @param roleId
	 * @return {@link List<MgrUserRole>}
	 */
	public List<MgrUserRole> queryByRid(Long roleId);
	
	/**
	 * 批量删除用户角色中间表数据
	 * @param userId 根据用户id
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> userId);
	/**
	 * 批量增加用户角色中间表数据
	 * @param mgrUserRoles 数据存放到list集合 
	 * @return {@link Integer}
	 */
	public int addBatch(List<MgrUserRole> mgrUserRoles);
	/**
	 * 修改用户角色表数据
	 * @param ur 用户角色实体类 {@link MgrUserRole}
	 * @return Integer
	 */
	public int updateBatchByList(MgrUserRole ur);
	
	
}
