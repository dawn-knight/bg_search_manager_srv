package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrHotKeyword;

/**
 * 热门关键字Service接口
 * @author qining
 *
 */
public interface HotKeywordService{

	/**查询所有的热门关键字数据
	 * @return List<MgrHotKeyword>
	 */
	public List<MgrHotKeyword> query();
	
	/** 增加热门关键字
	 * @param hotKeyword 实体类
	 * @return Integer 
	 */
	public int add (MgrHotKeyword hotKeyword);
	
	/**
	 * 删除热门关键字
	 * @param id 主键id
	 * @return Integer
	 */
	public int delete(Long id);
	 
	 /**
	  * 修改热门关键字
	 * @param hotKeyword 热门关键字实体类
	 */
	public int updateByPrimaryKeySelective(MgrHotKeyword hotKeyword);
	 
	/**
	 * 批量删除热门关键字
	 * @param ids 主键id
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	
    /**
     * 分页查询热门关键字数据
     * @param map 查询条件
     * @return List<MgrHotKeyword>
     */
    public List<MgrHotKeyword> selectAndPage(Map<String,Object> map);
	
	/**
	 * 根据条件查询热门关键字数据总条数
	 * @param map 查询条件存放在map里面
	 * @return Integer
	 */
	public int selectPageTotal(Map<String,Object> map);
	 
	/**
	 *  查询热门关键字
	 * @param code 关键字code
	 * @return
	 */
	public List<MgrHotKeyword> selectByCode(String code);
}
