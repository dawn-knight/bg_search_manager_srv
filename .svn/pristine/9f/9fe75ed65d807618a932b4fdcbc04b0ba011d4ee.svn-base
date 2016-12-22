package com.mbgo.searchmgr.core.mbshopservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.mbshop.ProductLibAttrKey;
import com.mbgo.searchmgr.core.mbshopservice.ProductLibAttrKeyService;
import com.mbgo.searchmgr.core.shopmapper.ProductLibAttrKeyMapper;

@Service("productLibAttrKeyService")
public class ProductLibAttrKeyServiceImpl implements ProductLibAttrKeyService{

	@Autowired
	private ProductLibAttrKeyMapper attrKeyMapper;
	
	public List<ProductLibAttrKey> getAll(){
		return attrKeyMapper.getAll();
	}
}
