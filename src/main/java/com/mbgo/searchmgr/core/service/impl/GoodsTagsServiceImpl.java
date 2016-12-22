package com.mbgo.searchmgr.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;
import com.mbgo.searchmgr.core.mapper.MgrGoodsTagsMapper;
import com.mbgo.searchmgr.core.service.GoodsTagsService;

/**
 *  GoodsTagsService接口实现类
 * @author qining
 *
 */
@Service("goodsTagsService")
public class GoodsTagsServiceImpl implements GoodsTagsService{
	
	@Autowired
	private MgrGoodsTagsMapper goodsTagsMapper;

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#queryGoodsTags()
	 */
	@Override
	public List<MgrGoodsTags> queryGoodsTags() {
		List<MgrGoodsTags> list = goodsTagsMapper.selectByPrimaryKey(1l);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#queryTagsGroupBy(java.lang.String)
	 */
	public List<MgrGoodsTags> queryTagsGroupBy(String tagword){
		return goodsTagsMapper.queryTagsGroupBy(tagword);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#addGoodsTags(com.mbgo.searchmgr.core.mongodb.bean.GoodsTags)
	 */
	@Override
	public int addGoodsTags(MgrGoodsTags mgrGoodsTags) {
		return goodsTagsMapper.insertSelective(mgrGoodsTags);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#deleteGoodsTags(java.lang.String[])
	 */
	@Override
	public int deleteGoodsTags(Long id){
		return goodsTagsMapper.deleteByPrimaryKey(id);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#deleteGoodsTagsByword(com.mbgo.searchmgr.core.bean.MgrGoodsTags)
	 */
	public int deleteGoodsTagsByword(MgrGoodsTags mgrGoodsTags){
		return goodsTagsMapper.deleteGoodsTagsByword(mgrGoodsTags);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#updateKeyword(com.mbgo.searchmgr.core.mongodb.bean.GoodsTags)
	 */
	@Override
	public int updateGoodsTags(MgrGoodsTags mgrGoodsTags){
		return goodsTagsMapper.updateByPrimaryKey(mgrGoodsTags);
	}
	public int updateToFlag(MgrGoodsTags mgrGoodsTags){
		return goodsTagsMapper.updateToFlag(mgrGoodsTags);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#deleteBatchByList(java.util.List)
	 */
	@Override
	public int deleteBatchByList(List<Long> ids) {
		return goodsTagsMapper.deleteBatchByList(ids);
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#selectAndPage(java.util.Map)
	 */
	public List<MgrGoodsTags> selectAndPage(Map<String,Object> map){
		return goodsTagsMapper.selectAndPage(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return goodsTagsMapper.selectPageTotal(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#queryTagsToSelect(java.lang.String)
	 */
	public List<MgrGoodsTags> queryTagsToSelect(String para){
		return goodsTagsMapper.queryTagsToSelect(para);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#queryTags(java.lang.String)
	 */
	public List<String> queryTags(String para){
		return goodsTagsMapper.queryTags(para);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.GoodsTagsService#selectGoodsTagsByword(com.mbgo.searchmgr.core.bean.MgrGoodsTags)
	 */
	public List<MgrGoodsTags> selectGoodsTagsByword(MgrGoodsTags mgrGoodsTags){
	 return goodsTagsMapper.selectGoodsTagsByword(mgrGoodsTags);
	}
	
	public  List<Map<String, Object>> selectGoodssn(Map<String,Object> map){
		return goodsTagsMapper.selectGoodssn(map);
	}
	public  List<Map<String, Object>> selectGoodssnCount(){
		return goodsTagsMapper.selectGoodssnCount();
	}
	
	
	public void TranAndInsert(List<Map<String, Object>> list){
		for (Map<String, Object> map : list) {
			if(map != null){
				goodsTagsMapper.updateGoodssn(map);
			}
		}
	}
	
	public List<MgrGoodsTags> selectTagUnique(MgrGoodsTags mgrGoodsTags){
		return goodsTagsMapper.selectTagUnique(mgrGoodsTags);
	}
	
	public List<MgrGoodsTags> getBatchTag(Map<String, Object> map){
		return goodsTagsMapper.getBatchTag(map);
	}
	 public int updateLimit(MgrGoodsTags mgrGoodsTags){
		 return goodsTagsMapper.updateLimit(mgrGoodsTags);
	 }
     public int updateLimitOther(MgrGoodsTags mgrGoodsTags){
    	 return goodsTagsMapper.updateLimitOther(mgrGoodsTags);
	 }
     
     public int updateAllSort(){
    	 List<Long> ids = goodsTagsMapper.selectAllId();
    	 return goodsTagsMapper.updateAllSort(ids);
	 }
}
