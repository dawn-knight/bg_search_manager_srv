package com.mbgo.searchmgr.core.mapper;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MallSiteCategory;

public interface MallSiteCategoryMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(MallSiteCategory record);

    int insertSelective(MallSiteCategory record);

    MallSiteCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallSiteCategory record);

    int updateByPrimaryKey(MallSiteCategory record);
    List<MallSiteCategory> getListByParentId(Integer id);
    public int addMallSiteCaategory(MallSiteCategory msc);
    public int deleteAll();
}