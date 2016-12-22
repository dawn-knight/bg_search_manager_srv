package com.mbgo.searchmgr.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.mapper.MgrRoleMenuMapper;
import com.mbgo.searchmgr.core.service.RoleMenuService;

/**
 * RoleMenuService接口实现类
 * @author qining
 * 
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService{

	@Autowired
	private MgrRoleMenuMapper mgrRoleMenuMapper;
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleMenuService#queryByRoleId(java.lang.Long)
	 */
	@Override
	public List<MgrRoleMenu> queryByRoleId(Long roleId) {
		return mgrRoleMenuMapper.queryByRoleId(roleId);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleMenuService#queryByMenuId(java.lang.Long)
	 */
	@Override
	public List<MgrRoleMenu> queryByMenuId(Long menuId) {
		return mgrRoleMenuMapper.queryByMenuId(menuId);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleMenuService#queryRoleByRid(java.lang.Long)
	 */
	@Override
	public List<MgrRoleMenu> queryRoleByRid(Long roleId) {
		return mgrRoleMenuMapper.queryByRoleId(roleId);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleMenuService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> list) {
		return mgrRoleMenuMapper.deleteBatchByList(list);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.RoleMenuService#addBatch(java.util.List)
	 */
	@Override
	public int addBatch(List<MgrRoleMenu> list) {
		return mgrRoleMenuMapper.addBatch(list);
	}

}
