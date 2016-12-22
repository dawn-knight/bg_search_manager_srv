package com.mbgo.searchmgr.core.service;

import java.util.List;

import com.mbgo.searchmgr.core.bean.MallProperty;

public interface MallPropertyService {

	public List<MallProperty> getListByParentId(int id);
	public List<MallProperty> selectByCid(MallProperty pro);
	public List<MallProperty> getAllAttr();
	public int addAttrInfo(MallProperty property);
	public int addMallProperty(MallProperty property);
	
	public int updateLimit(MallProperty property);
	public int deleteAttr(MallProperty property);
	public int selectByCidCount(MallProperty property);
	public MallProperty selectBykey(int id);
	public int deleteAll();
	
}
