package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.bean.MgrTagsInfo;


/**
 * 标签信息表业务接口
 * @author qining
 *
 */
public interface TagsInfoService{

	/**
	 * 查询所有的标信息
	 * @return {@link List<MgrTagsInfo>} 
	 * @deprecated
	 * {@link TagsInfoService  selectPageTotal}
	 */
	public List<MgrTagsInfo> query();
	
	/**
	 * 增加标签信息数据
	 * @param tagsInfo 标签信息实体类 {@link MgrTagsInfo}
	 * @return Integer
	 */
	public int add (MgrTagsInfo tagsInfo);
	
	 /**
	  * 删除标签信息数据 
	 * @param id 主键id
	 * @return Integer
	 */
	public int delete(Long id);
	 
	/**
	 * 修改标签信息数据
	 * @param tagsInfo 标签信息实体类 {@link MgrTagsInfo}
	 * @return Integer
	 */
	public int updateByPrimaryKeySelective(MgrTagsInfo tagsInfo);
	
	/**
	 * 批量删除标签信息表数据
	 * @param ids 主键id存放的List里面
	 * @return
	 */
	public int deleteBatchByList(List<Long> ids);
	
	/**
	 * 查询标签信息数据
	 * @param map 查询条件存放的map里面
	 * @return List<MgrTagsInfo>
	 */
	public List<MgrTagsInfo> selectAndPage(Map<String,Object> map);
	/**
	 * 查询标签信息总条数
	 * @param map 查询条件存放到map里面
	 * @return
	 */
	public int selectPageTotal(Map<String,Object> map);
	
	public List<String> queryTags(String para);
	
	public List<MgrTagsInfo> queryTagsGroupBy(String tagword);
	public int updateAll();
    
    List<MgrTagsInfo> queryTagsUnique(String word);
}
