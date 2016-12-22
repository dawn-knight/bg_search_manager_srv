package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrHotSearchKeyword;

/**
 * 热门搜索关键字
 * @author qining
 *
 */
public interface HotSearchKeywordService{

	
	
	/** 添加热门搜索关键字
	 * @param hotSearchKeyword 实体类
	 * @return Integer
	 */
	public int add(MgrHotSearchKeyword hotSearchKeyword);
	
	 /**
	 * 删除热门搜索关键字
	 * @param id 主键Id
	 * @return Integer
	 */
	public int delete(Integer id);
	 
	/**
	 * 修改热门关键字
	 * @param hotKeyword 热门关键字实体类
	 * @return Integer
	 */
	public int updateByPrimaryKeySelective(MgrHotSearchKeyword hotSearchKeyword);
	
	/**
	 * 批量删除热门搜索词表数据
	 * @param ids
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	
	/**
	 * 分页查询
	 * @param map 查询条件
 	 * @return List<MgrHotSearchKeyword>
	 */
	public List<MgrHotSearchKeyword> selectAndPage(Map<String,Object> map);
	/** 查询分页的总条数
	 * @param map 查询条件
	 * @return Integer
	 */
	public int selectPageTotal(Map<String,Object> map);
}
