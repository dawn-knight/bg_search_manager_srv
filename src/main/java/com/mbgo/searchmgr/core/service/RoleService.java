package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrRole;


/**
 * 角色业务接口类
 * @author qining
 *
 */
public interface RoleService {

	
	/**根据角色Id查询角色
	 * @param id 角色ID
	 * @return List<Role>
	 */
	public MgrRole queryRoleId(Long id);
	
	/**
	 * 查询角色数据并分页
	 * @param map key=value值存储查询条件
	 * @return List<MgrRole>
	 */
	public List<MgrRole> selectAndPage(Map<String,Object> map);
	/**
	 * 查询所有的角色数据
	 * @return List<MgrRole>
	 */
	public List<MgrRole> queryRole();
	/**
	 * 根据条件查询分页数据的总条数
	 * @param map
	 * @return Integer
	 */
	public int selectPageTotal(Map<String,Object> map);
	/**
	 * 向角色表里面插入数据
	 * @param record 角色表实体类 MgrRole
	 * @return Integer
	 */
	public int insertSelective(MgrRole record);
	/**
	 * 修改角色表里面的数据
	 * @param role 角色表实体类 MgrRole
	 * @return
	 */
	public int updateRole(MgrRole role);
	/**
	 * 批量删除角色表数据
	 * @param ids 主键id存放到List<Long>
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	/**
	 * 删除角色表数据
	 * @param id 主键id
	 * @return Integer
	 */
	public int deleteByPrimaryKey(Long id);
}
