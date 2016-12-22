package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.mapper.MgrMenuMapper;
import com.mbgo.searchmgr.core.service.MenuService;

/**
 * LogService接口实现类
 * @author qining
 * 
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MgrMenuMapper mgrMenuMapper;
	
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#selectAndPage(java.util.Map)
	 */
	public List<MgrMenu> selectAndPage(Map<String,Object> map){
		return mgrMenuMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#queryMenu()
	 */
	public List<MgrMenu> queryMenu(){
		return mgrMenuMapper.queryMenuAll();
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return mgrMenuMapper.selectPageTotal(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#insertSelective(com.mbgo.searchmgr.core.bean.MgrMenu)
	 */
	public int insertSelective(MgrMenu record){
		return mgrMenuMapper.insertSelective(record);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#updateMenu(com.mbgo.searchmgr.core.bean.MgrMenu)
	 */
	public int updateMenu(MgrMenu menu){
		return mgrMenuMapper.updateByPrimaryKeySelective(menu);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#deleteBatchByList(java.util.List)
	 */
	public int deleteBatchByList(List<Long> ids){
		return mgrMenuMapper.deleteBatchByList(ids);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long id){
		return mgrMenuMapper.deleteByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#selectByPrimaryKey(java.lang.Long)
	 */
	public MgrMenu selectByPrimaryKey(Long id){
		return mgrMenuMapper.selectByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#queryMenuUrl(java.lang.String)
	 */
	@Override
	public List<MgrMenu> queryMenuUrl(String url) {
		return mgrMenuMapper.queryMenuUrl(url);
	}
	@Override
	public List<MgrMenu> queryMenuUrlFuzzy(String url) {
		return mgrMenuMapper.queryMenuUrlFuzzy(url);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.MenuService#queryMenuName(java.lang.Long)
	 */
	@Override
	public List<MgrMenu> queryMenuName(Long parentId) {
		return mgrMenuMapper.queryMenuName(parentId);
	}
	public MgrMenu selectByMenuId(Long menuId){
		return mgrMenuMapper.selectByMenuId(menuId);
	}
	
}
