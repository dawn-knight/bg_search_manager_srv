package com.mbgo.searchmgr.core.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrUser;
import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.security.ICustomUserDetailsService;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.service.UserService;

public class UserDetailsServiceimpl implements ICustomUserDetailsService {


	@Autowired
	 private UserRoleService userRoleService;
	@Autowired
	 private UserService userService;
	@Autowired
	private RoleService roleService;
	 
	@Override
	public UserDetails loadUserByUsername(String username){
		 MgrUser userdetail = null;
		try{
			MgrUser mu = new MgrUser();
			mu.setUsername(username);
			MgrUser user = userService.login(mu);
		 List<String> roleName = obtionGrantedAuthorities(user);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String name : roleName) {
			authorities.add(new SimpleGrantedAuthority(name));
		}
	        boolean enables = true;  
	        boolean accountNonExpired = true;  
	       boolean credentialsNonExpired = true;  
	        boolean accountNonLocked = true;  
	        userdetail = new MgrUser(user.getId(),user.getUsername(), user.getPassword(), 
	    		  authorities, accountNonExpired, accountNonLocked, credentialsNonExpired,enables);
		}catch(Exception e){
			e.printStackTrace();
		}
		return userdetail;
		}
	
	   //取得用户的权限  
    private List<String> obtionGrantedAuthorities(MgrUser user) {  
    	List<String> roleList = new ArrayList<String>();  
    	Long userId = user.getId();
    	List<MgrUserRole> roleId = this.userRoleService.queryRoleByUid(userId);
    	for (MgrUserRole userRoleDO : roleId) {
    		MgrRole role = roleService.queryRoleId(userRoleDO.getRoleid());
    		roleList.add(role.getRolename());
		}
        return roleList;  
    } 

	
	
	}

