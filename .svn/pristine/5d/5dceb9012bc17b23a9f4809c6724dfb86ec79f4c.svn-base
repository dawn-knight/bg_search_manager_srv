package com.mbgo.searchmgr.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbgo.searchmgr.core.bean.MgrUser;
import com.mbgo.searchmgr.core.mapper.MgrUserMapper;
import com.mbgo.searchmgr.core.service.UserService;

/**
 * UserService接口实现类
 * @author qining
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private MgrUserMapper mgrUserMapper;
	


	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#login(com.mbgo.searchmgr.core.bean.MgrUser)
	 */
	@Override
	public MgrUser login(MgrUser mgrUser) {
		MgrUser user= mgrUserMapper.login(mgrUser);
		return user;
	}


	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#queryUser(java.lang.String)
	 */
	@Override
	public List<MgrUser> queryUser(String username) {
		List<MgrUser> list = mgrUserMapper.queryUser(username);
		return list;
	}
	
	public MgrUser queryUserByName(String username){
		MgrUser user = mgrUserMapper.queryUserByName(username);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#queryUserAll()
	 */
	@Override
	public List<MgrUser> queryUserAll() {
		List<MgrUser> list = mgrUserMapper.queryUserAll();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#selectAndPage(java.util.Map)
	 */
	public	List<MgrUser> selectAndPage(Map<String,Object> map){
		List<MgrUser> list = mgrUserMapper.selectAndPage(map);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#selectPageTotal(java.util.Map)
	 */
	public int selectPageTotal(Map<String,Object> map){
		return mgrUserMapper.selectPageTotal(map);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#insertSelective(com.mbgo.searchmgr.core.bean.MgrUser)
	 */
	public int insertSelective(MgrUser record){
		return mgrUserMapper.insertSelective(record);
		
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#updateUser(com.mbgo.searchmgr.core.bean.MgrUser)
	 */
	public int updateUser(MgrUser user){
		return mgrUserMapper.updateByPrimaryKeySelective(user);
	}

	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#deleteBatchByList(java.util.List)
	 */
	public int deleteBatchByList(List<Long> ids){
		return mgrUserMapper.deleteBatchByList(ids);
	}
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#deleteByPrimaryKey(java.lang.Long)
	 */
	public int deleteByPrimaryKey(Long id){
		return mgrUserMapper.deleteByPrimaryKey(id);
	}
     
	/* (non-Javadoc)
	 * @see com.mbgo.searchmgr.core.service.UserService#selectByPrimaryKey(java.lang.Long)
	 */
	public MgrUser selectByPrimaryKey(Long id){
		return mgrUserMapper.selectByPrimaryKey(id);
	}

	
}
