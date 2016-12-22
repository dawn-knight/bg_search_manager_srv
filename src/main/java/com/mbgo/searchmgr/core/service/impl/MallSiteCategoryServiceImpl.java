package com.mbgo.searchmgr.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MallSiteCategory;
import com.mbgo.searchmgr.core.mapper.MallSiteCategoryMapper;
import com.mbgo.searchmgr.core.service.MallSiteCategoryService;

@Service("mallSiteCategoryService")
public class MallSiteCategoryServiceImpl implements MallSiteCategoryService{

	@Autowired
	private MallSiteCategoryMapper categoryMapper;
	
	public List<MallSiteCategory> getListByParentId(int id){
		return categoryMapper.getListByParentId(id);
	}
	public int addMallSiteCaategory(MallSiteCategory msc){
		return categoryMapper.addMallSiteCaategory(msc);
	}
	public MallSiteCategory selectByPrimaryKey(int id){
		return categoryMapper.selectByPrimaryKey(id);
	}
	public int deleteAll(){
		return categoryMapper.deleteAll();
	}
}
