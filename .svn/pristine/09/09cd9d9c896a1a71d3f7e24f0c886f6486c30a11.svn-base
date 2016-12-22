package com.mbgo.searchmgr.core.storeservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.mbstore.StoreMallSiteCategory;
import com.mbgo.searchmgr.core.storemapper.StoreMallSiteCategoryMapper;
import com.mbgo.searchmgr.core.storeservice.StoreMallSiteCategoryService;


@Service("storeMallSiteCategoryService")
public class StoreMallSiteCategoryServiceImpl implements StoreMallSiteCategoryService{

	@Autowired
	private StoreMallSiteCategoryMapper categoryMapper;
	
	public List<StoreMallSiteCategory> getAll(){
		return categoryMapper.getAll();
	}
}
