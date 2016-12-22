package com.mbgo.searchmgr.core.mapper;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MgrRoleMenu;

public interface MgrRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MgrRoleMenu record);

    int insertSelective(MgrRoleMenu record);

    MgrRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MgrRoleMenu record);

    int updateByPrimaryKey(MgrRoleMenu record);
    public List<MgrRoleMenu> queryByRoleId(Long roleId);
    public List<MgrRoleMenu> queryByMenuId(Long menuId);
    
    public int deleteBatchByList(List<Long> list);
    public int addBatch(List<MgrRoleMenu> list);
}