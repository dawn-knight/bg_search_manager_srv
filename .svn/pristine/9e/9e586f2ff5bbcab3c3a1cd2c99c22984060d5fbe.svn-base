package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrConvertKeyword;

/**
 * 分词转换接口
 * @author qining
 *
 */
public interface ConvertKeywordService{

	/**
	 * 查询所有的关键词转换表里面的数据
	 * @return
	 */
	public List<MgrConvertKeyword> queryConvertKeyword();
	
	/**
	 * 向关键词转换表里面添加数据
	 * @param MgrConvertKeyword 尸体列
	 */
	public int addConvertKeyWord (MgrConvertKeyword keyword);
	
	 /**
	  * 删除关键词转换表里的数据
	 * @param ids  主键id
 	 */
	public int deleteConvertKeyword(Long id);
	
	
	/**
	 * 修改关键词转换表里的数据
	 * @param keyword MgrConvertKeyword 实体类 内部通过id修改
	 */
	public int updateConvertKeyword(MgrConvertKeyword keyword);
	
	/**
	 * 批量删除关键字转换数据
	 * @param ids
	 * @return
	 */
	public int deleteBatchByList(List<Long> ids);
	
	/**查询数据并分页
	 * @param map 查询的数据范围
	 * @return
	 */
	List<MgrConvertKeyword> selectAndPage(Map<String,Object> map);
	
	/**根据查询条件得到数据的总条件
	 * @param map查询的数据范围
	 * @return
	 */
	public int selectPageTotal(Map<String,Object> map);
}
