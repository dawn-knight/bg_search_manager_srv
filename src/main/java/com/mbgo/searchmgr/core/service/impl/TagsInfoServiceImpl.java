package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.bean.MgrTagsInfo;
import com.mbgo.searchmgr.core.mapper.MgrTagsInfoMapper;
import com.mbgo.searchmgr.core.service.TagsInfoService;

/**
 * TagsInfoService接口实现类
 * @author qining
 * 
 */

@Service("tagsInfoService")
public class TagsInfoServiceImpl implements TagsInfoService{
	
	@Autowired
	private MgrTagsInfoMapper tagsInfoMapper;

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#query()
	 */
	@Override
	public List<MgrTagsInfo> query() {
		List<MgrTagsInfo> list = tagsInfoMapper.selectByPrimaryKey(1l);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#add(com.mbgo.searchmgr.core.bean.MgrTagsInfo)
	 */
	@Override
	public int add(MgrTagsInfo tagsInfo) {
		return tagsInfoMapper.insertSelective(tagsInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id){
		return tagsInfoMapper.deleteByPrimaryKey(id);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#updateByPrimaryKeySelective(com.mbgo.searchmgr.core.bean.MgrTagsInfo)
	 */
	public int updateByPrimaryKeySelective(MgrTagsInfo tagsInfo){
		return tagsInfoMapper.updateByPrimaryKeySelective(tagsInfo);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return tagsInfoMapper.deleteBatchByList(ids);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#selectAndPage(java.util.Map)
	 */
	public List<MgrTagsInfo> selectAndPage(Map<String,Object> map){
		return tagsInfoMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.TagsInfoService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return tagsInfoMapper.selectPageTotal(map);
	}
	public List<String> queryTags(String para){
		return tagsInfoMapper.queryTags(para);
	}
	
	public List<MgrTagsInfo> queryTagsGroupBy(String tagword){
		return tagsInfoMapper.queryTagsGroupBy(tagword);
	}
	
	public int updateAll(){
		List<Long> ids = tagsInfoMapper.selectAllId();
		int num = tagsInfoMapper.updateBatch(ids);
		return num;
	}
	public List<MgrTagsInfo> queryTagsUnique(String word){
		return tagsInfoMapper.queryTagsUnique(word);
	}
}
