package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.bean.MgrTagsInfo;

public interface MgrTagsInfoMapper {

	int deleteByPrimaryKey(Long id);
	
	int deleteBatchByList(List<Long> ids);
	int updateBatch(List<Long> ids);
	int insert(MgrTagsInfo record);

	int insertSelective(MgrTagsInfo record);

	List<MgrTagsInfo> selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MgrTagsInfo record);

	int updateByPrimaryKey(MgrTagsInfo record);
	List<MgrTagsInfo> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	
	public List<String> queryTags(String para);
	public List<MgrTagsInfo> queryTagsGroupBy(String tagword);
	List<Long> selectAllId();
	List<MgrTagsInfo> queryTagsUnique(String word);
}
