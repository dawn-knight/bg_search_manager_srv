package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrGoodsTags;

/**
 * 商品标签库对应信息service接口
 * @author qining
 *
 */
public interface GoodsTagsService {

	/**
	 * 查询标签库所有信息
	 * @return List<MgrGoodsTags>
	 */
	public List<MgrGoodsTags> queryGoodsTags();
	
	/**分组查询去重复
	 * @param tagword 
	 * @return
	 */
	public List<MgrGoodsTags> queryTagsGroupBy(String tagword);

	/**
	 * 增加标签库记录
	 * @param MgrGoodsTags 标签库实体类
	 * @return Integer
	 */
	public int addGoodsTags(MgrGoodsTags mgrGoodsTags);

	
	/**
	 * 删除标签库数据
	 * @param ids 主键ID
	 * @return Integer
	 */
	public int deleteGoodsTags(Long id);
	
	/**删除标签库数据
	 * @param mgrGoodsTags 标签库实体类 根据实体类词进行删除
	 * @return Integer
	 */
	public int deleteGoodsTagsByword(MgrGoodsTags mgrGoodsTags);

	/**
	 * 修改标签库信息
	 * @param keyword 自定义分词model 根据model.id修改数据
	 * @return Integer
	 */
	public int updateGoodsTags(MgrGoodsTags mgrGoodsTags);

	/**漂亮删除标签库信息
	 * @param ids 主键Id 
	 * @return Integer
	 */
	public int deleteBatchByList(List<Long> ids);
	
    /**
     * 分页查询
     * @param map 存放查询条件
     * @return List<MgrGoodsTags>
     */
    public List<MgrGoodsTags> selectAndPage(Map<String,Object> map);
    /**
     * 查询标签库数据 
     * @param para 根据tab_word 查询
     * @return List<MgrGoodsTags>
     */
     public List<MgrGoodsTags> queryTagsToSelect(String tagword);
     /**
      * 查询标签库数据 
      * @param para 根据tab_word 查询
      * @return List<String>
      */
     public List<String> queryTags(String para);
     
     /**
      * 查询数据
     * @param mgrGoodsTags 根据goods_sn 和tag_word 条件查询
     * @return List<MgrGoodsTags>
     */
     public List<MgrGoodsTags> selectGoodsTagsByword(MgrGoodsTags mgrGoodsTags);
	
    
      /**
       * 查询数据总条件
     * @param map 根据分页的查询条件
     * @return Integer
     */
    public int selectPageTotal(Map<String,Object> map);
    
    /**
     * 修改标签标识
     * @param mgrGoodsTags
     * @return
     */
    public int updateToFlag(MgrGoodsTags mgrGoodsTags);
    
    
    public  List<Map<String, Object>> selectGoodssn(Map<String,Object> map);
    
    public  List<Map<String, Object>> selectGoodssnCount();
    
    public void TranAndInsert(List<Map<String, Object>> list);
    List<MgrGoodsTags> selectTagUnique(MgrGoodsTags mgrGoodsTags);
    public List<MgrGoodsTags> getBatchTag(Map<String, Object> map);
    public int updateLimit(MgrGoodsTags mgrGoodsTags);
    public int updateLimitOther(MgrGoodsTags mgrGoodsTags);
	public int updateAllSort();
}
