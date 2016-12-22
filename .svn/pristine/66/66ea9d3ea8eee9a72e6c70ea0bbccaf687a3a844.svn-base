package com.mbgo.searchmgr.core.service;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MgrRoleMenu;


/**
 * 角色菜单业务接口类
 * @author qining
 *
 */
public interface RoleMenuService {

	
	/**
	 * 根据角色id查询角色菜单中间表
	 * @param roleId
	 * @return List<MgrRoleMenu>
	 */
	public List<MgrRoleMenu> queryByRoleId(Long roleId);
	
	/**
	 * 根据菜单id查询角色菜单中间表
	 * @param menuId
	 * @return List<MgrRoleMenu>
	 */
	public List<MgrRoleMenu> queryByMenuId(Long menuId);
	/**
	 * 根据角色id查询角色菜单中间表
	 * @param roleId
	 * @return List<MgrRoleMenu>
	 */
	public List<MgrRoleMenu> queryRoleByRid(Long roleId);
	/**
	 * 批量删除中间表数据
	 * @param ids
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	/**
	 * 批量增加中间表数据
	 * @param list
	 * @return Integer
	 */
	public int addBatch(List<MgrRoleMenu> list);
	
}
