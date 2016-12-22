package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrSameKeyword;


/**
 * 同义词库业务接口
 * @author qining
 *
 */
public interface SameKeywordService{

	/**
	 * 查询所有的同义词库数据
	 * @return List<MgrSameKeyword>
	 * @deprecated
	 * {@link com.mbgo.searchmgr.core.service.SameKeywordService.selectPageTotal}
	 */
	public List<MgrSameKeyword> query();
	
	/**
	 * 增加同义词库表
	 * @param sameKeyword 同义词库表实体类 {@link MgrSameKeyword}
	 * @return Integer
	 */
	public int add(MgrSameKeyword sameKeyword);
	
	/**
	 * 删除同义词库表
	 * @param id 主键id
	 * @return Integer
	 */
	public int delete(Long id);
	 /**
	  * 批量删除同义词库数据
	 * @param hotKeyword 热门关键字实体类 {@link MgrSameKeyword}
	 */
	public int updateByPrimaryKeySelective(MgrSameKeyword sameKeyword);
	
	/**
	 * 批量删除同义词库数据
	 * @param ids 主键id存放到List集合
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	/**
	 * 查询同义词库表数据
	 * @param map 查询条件存放到map里面
	 * @return List<MgrSameKeyword>
	 */
	public List<MgrSameKeyword> selectAndPage(Map<String,Object> map);
	/**
	 * 根据条件查询数据同条数
	 * @param map 查询条件存放map
	 * @return {@link Integer}
	 */
	public int selectPageTotal(Map<String,Object> map);
}
