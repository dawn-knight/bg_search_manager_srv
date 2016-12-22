package com.mbgo.searchmgr.core.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.security.ISecurityMetadataSource;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.service.UserService;

public class SecurityMetadataSourceImpl implements ISecurityMetadataSource{

	
	@Autowired
	 private RoleMenuService roleMenuService;
	@Autowired
	 private MenuService menuService;
	@Autowired
	 private UserRoleService userRoleService;
	@Autowired
	 private UserService userService;
	@Autowired
	private RoleService roleService;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null; 
	  
	  
	    public Collection<ConfigAttribute> getAllConfigAttributes() {  
	        // TODO Auto-generated method stub  
	        return null;  
	    }  
	  
	    public boolean supports(Class<?> clazz) {  
	        // TODO Auto-generated method stub  
	        return true;  
	    }  
	    //返回所请求资源所需要的权限  
	    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {  
	     Collection<ConfigAttribute> configAttributes  = new ArrayList<ConfigAttribute>();  ;
	     ConfigAttribute configAttribute = null;
	        String requestUrl = ((FilterInvocation) object).getRequestUrl();  
	        List<MgrMenu> mendoList = menuService.queryMenuUrl(requestUrl);
	        for (MgrMenu menuDO : mendoList) {
	        	List<MgrRoleMenu> roleMenu =	roleMenuService.queryByMenuId(menuDO.getId());
	        	for (MgrRoleMenu roleMenuDO : roleMenu) {
					MgrRole role = roleService.queryRoleId(roleMenuDO.getRoleId());
                     //以权限名封装为Spring的security Object  
				      configAttribute = new SecurityConfig(role.getRolename());  
				      configAttributes.add(configAttribute);  
	        	}
			}
	        return configAttributes;
	    }
	    
}
