package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;

public interface MgrGoodsTagsMapper {

	int deleteByPrimaryKey(Long id);

	int deleteBatchByList(List<Long> ids);
	
	int insert(MgrGoodsTags record);

	int insertSelective(MgrGoodsTags record);

	List<MgrGoodsTags> selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MgrGoodsTags record);

	int updateByPrimaryKey(MgrGoodsTags record);
	int updateToFlag(MgrGoodsTags record);
	List<MgrGoodsTags> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	public List<MgrGoodsTags> queryTagsToSelect(String para);
	public List<String> queryTags(String para);
	
	public int deleteGoodsTagsByword(MgrGoodsTags mgrGoodsTags);
	List<MgrGoodsTags> selectGoodsTagsByword(MgrGoodsTags mgrGoodsTags);
	List<MgrGoodsTags> selectTagUnique(MgrGoodsTags mgrGoodsTags);
	
	public List<MgrGoodsTags> queryTagsGroupBy(String tagword);
	public  List<Map<String, Object>> selectGoodssn(Map<String,Object> map);
	
	public  List<Map<String, Object>> selectGoodssnCount();
	
	public void updateGoodssn(Map<String, Object> map);
	public List<MgrGoodsTags> getBatchTag(Map<String, Object> map);
	
	 public int updateLimit(MgrGoodsTags mgrGoodsTags);
     public int updateLimitOther(MgrGoodsTags mgrGoodsTags);
     public int updateAllSort(List<Long> ids);
     public List<Long> selectAllId();
}
