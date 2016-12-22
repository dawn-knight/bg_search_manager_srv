package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrMenu;


/**
 * MenuService 菜单业务接口
 * @author qining
 *
 */
/**
 * @author Administrator
 *
 */
public interface MenuService {
	
	
	/**
	 * 根据条件进行分页 
	 * @param map 存放key=value 的查询条件
	 * @return list集合
	 */
	public List<MgrMenu> selectAndPage(Map<String,Object> map);
	
	/**
	 * 查询所有的菜单
	 * @return List<MgrMenu>
	 */
	public List<MgrMenu> queryMenu();
	/**
	 * 根据url查询菜单
	 * @param url url连接
	 * @return List<MgrMenu>
	 */
	public List<MgrMenu> queryMenuUrl(String url);
	
	/**
	 * 根据条件查询菜单信息
	 * @param parentId 父类id字段
	 * @return List<MgrMenu>
	 */
	public List<MgrMenu> queryMenuName(Long parentId);
	
	/**
	 * 根据主键id查询菜单信息
	 * @param menuId 主键id
	 * @return MgrMenu
	 */
	public MgrMenu selectByMenuId(Long menuId);
	/**
	 * 根据条件查询总条数 
	 * @param map 存放key=value 的查询条件
	 * @return list集合
	 */
	public int selectPageTotal(Map<String,Object> map);
	/**
	 * 根据条件进行菜单插入
	 * @param record
	 * @return int
	 */
	public int insertSelective(MgrMenu record);
	
	/**
	 * 根据条件进行修改
	 * @param menu
	 * @return int
	 */
	public int updateMenu(MgrMenu menu);
	/**
	 * 根据主键进行查询MgrMenu 信息
	 * @param id 主键id
	 * @return  MgrMenu
	 */
	public MgrMenu selectByPrimaryKey(Long id);
	/**
	 * 根据主键id进行单个删除
	 * @param id 根据主键id
	 * @return int
	 */
	public int deleteByPrimaryKey(Long id);
	
	
	/**
	 * 根据主键id进行批量
	 * @param id 根据主键id
	 * @return int
	 */
	public int deleteBatchByList(List<Long> ids);
	
	public List<MgrMenu> queryMenuUrlFuzzy(String url);
}
