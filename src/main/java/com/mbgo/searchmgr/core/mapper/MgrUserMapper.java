package com.mbgo.searchmgr.core.mapper;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrUser;

public interface MgrUserMapper {

	int deleteByPrimaryKey(Long id);
	int deleteBatchByList(List<Long> ids);

	int insert(MgrUser record);

	int insertSelective(MgrUser record);

	MgrUser selectByPrimaryKey(Long id);
	
	MgrUser login(String username);
	
	MgrUser login(MgrUser user);
	
	List<MgrUser> queryUser(String username);

	int updateByPrimaryKeySelective(MgrUser record);

	int updateByPrimaryKey(MgrUser record);
	List<MgrUser> selectAndPage(Map<String,Object> map);
	public int selectPageTotal(Map<String,Object> map);
	
	public List<MgrUser> queryUserAll();
	
	public MgrUser queryUserByName(String username);
	
}
