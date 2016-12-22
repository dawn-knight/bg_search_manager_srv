package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.mbgo.searchmgr.core.bean.MgrBaseKeyword;

/**
 * 商品标签库对应信息service接口
 * @author qining
 *
 */
public interface BaseKeywordService {


	/**
	 * 增加标签库
	 * @param MgrBaseKeyword 标签库model类
	 */
	public int addBaseKeyword(MgrBaseKeyword baseKeyword);

	
	/**
	 * 删除标签库数据
	 * @param ids 主键id
	 */
	public int deleteBaseKeyword(Long id);

	/**
	 * 修改标签库信息
	 * @param keyword
	 *  自定义分词model 根据model.id修改数据
	 */
	public int updateBaseKeyword(MgrBaseKeyword baseKeyword);

	
	/**批量删除基本词库
	 * @param ids 主键id放到List集合里面
	 * @return
	 */
	public int deleteBatchByList(List<Long> ids);
	
    /**
     * 分页查询方法
     * @param map 里面存放的key=value要查询的范围
     * @return
     */
    public List<MgrBaseKeyword> selectAndPage(Map<String,Object> map);
    public List<MgrBaseKeyword> exportSelect(Map<String,Object> map);
	
    
	/** 根据分页查询条件查询总条数
	 * @param map 里面存放的key=value要查询的范围
	 * @return
	 */
	public int selectPageTotal(Map<String,Object> map);
	
	/**
	 * 根据词语的编码查询是否已经存在
	 * @param code 词的编码后的字符串
	 * @return
	 */
	public List<MgrBaseKeyword> selectByCode(String code);
	/**
	 * 根据词语的编码查询是否已经存在
	 * @param code 词的编码后的字符串
	 * @return
	 */
	public MgrBaseKeyword selectDataByWordcode(String code);
	
	
	public void downloadBaseWord(HttpServletResponse response, List<MgrBaseKeyword> lits);
}
