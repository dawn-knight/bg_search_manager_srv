package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.mapper.MgrRoleMapper;
import com.mbgo.searchmgr.core.service.RoleService;

/**
 * RoleService接口实现类
 * @author qining
 * 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private MgrRoleMapper mgrRoleMapper;
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#queryRoleId(java.lang.Long)
	 */
	@Override
	public MgrRole queryRoleId(Long id) {
		MgrRole role = mgrRoleMapper.queryRoleIdM(id);
		return role;
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#selectAndPage(java.util.Map)
	 */
	public List<MgrRole> selectAndPage(Map<String,Object> map){
		return mgrRoleMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return mgrRoleMapper.selectPageTotal(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#insertSelective(com.mbgo.searchmgr.core.bean.MgrRole)
	 */
	public int insertSelective(MgrRole record){
		return mgrRoleMapper.insertSelective(record);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#updateRole(com.mbgo.searchmgr.core.bean.MgrRole)
	 */
	public int updateRole(MgrRole role){
		return mgrRoleMapper.updateByPrimaryKeySelective(role);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#deleteBatchByList(java.util.List)
	 */
	public int deleteBatchByList(List<Long> ids){
		return mgrRoleMapper.deleteBatchByList(ids);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#queryRole()
	 */
	public List<MgrRole> queryRole(){
		return mgrRoleMapper.queryRoleAll();
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleService#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mgrRoleMapper.deleteByPrimaryKey(id);
	}
	
}
