package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrDicKeyword;

/**
 * 自定义分词Service接口类
 * @author qining
 *
 */
public interface KeywordService{

	/**
	 * 自定义分词查询类
	 * @return List<MgrDicKeyword>
	 */
	public List<MgrDicKeyword> queryDicKeyword();
	
	/**
	 * 增加自定义分词
	 * @param keyword 自定义分词 model类
	 * @return Integer
	 */
	public int addKeyWord (MgrDicKeyword keyword);
	
	 /**
	  * 删除自定义分词
	 * @param ids  字符串数组 ID 主键
 	 */
	public int deleteKeyword(Long id);
	
	
	/**
	 * 修改自定义分词数据
	 * @param keyword 自定义分词model 根据model.id修改数据
	 * @return Integer
	 */
	public int updateKeyword(MgrDicKeyword keyword);
	
	/**批量删除
	 * @param ids 主键id
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	
	/**分页查询
	 * @param map 查询条件
	 * @return List<MgrDicKeyword>
	 */
	public List<MgrDicKeyword> selectAndPage(Map<String,Object> map);
	
	/**
	 *查询分页的总条数
	 * @param map 查询条件
	 * @return Integer
	 */
	public List<MgrDicKeyword> selectPageTotal(Map<String,Object> map);
}
