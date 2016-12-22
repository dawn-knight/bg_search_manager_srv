package com.mbgo.searchmgr.core.service;

import java.util.List;
import java.util.Map;

import com.mbgo.searchmgr.core.bean.MgrUser;


/**
 * 用户表业务接口
 * @author qining
 *
 */
public interface UserService {

	
	/**
	 * 登陆方法
	 * @param mgrUser 用户实体类
	 * @return {@link MgrUser}
	 */
	public MgrUser login(MgrUser mgrUser);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return List集合
	 */
	public List<MgrUser> queryUser(String username);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return {@link MgrUser}
	 */
	public MgrUser queryUserByName(String username);
	
	/**
	 *查询所有的用户
	 * @return List<MgrUser>
	 */
	public List<MgrUser> queryUserAll();
	/**
	 * 根据条件分页查询
	 * @param map 存放key=value 查询条件
	 * @return list集合
	 */
	public List<MgrUser> selectAndPage(Map<String,Object> map);
	/**
	 * 根据条件查询总条数
	 * @param map 存放key=value 查询条件
	 * @return int
	 */
	public int selectPageTotal(Map<String,Object> map);
	/**
	 * 根据条件进行插入
	 * @param record {@link MgrUser}
	 * @return int
	 */
	public int insertSelective(MgrUser record);
	/**
	 * 根据条件进行修改
	 * @param record {@link MgrUser 参数是实体类 共用性更强}
	 * @return int
	 */
	public int updateUser(MgrUser user);
	/**
	 * 根据主键id 进行删除 
	 * @param ids 主键 存放到list
	 * @return int
	 */
	public int deleteBatchByList(List<Long> ids);
	
	/**
	 *  根据主键id 单个删除
	 * @param id 主键值
	 * @return int
	 */
	public int deleteByPrimaryKey(Long id);
	/**
	 * 根据主键查询信息
	 * @param id 主键
	 * @return {@link MgrUser}
	 */
	public MgrUser selectByPrimaryKey(Long id);
	
}
