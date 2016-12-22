package com.mbgo.searchmgr.core.security.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.bean.MgrUser;
import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.service.UserService;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	 private RoleMenuService roleMenuService;
	@Autowired
	 private MenuService menuService;
	@Autowired
	 private UserRoleService userRoleService;
	@Autowired
	 private UserService userService;
	 
	@Override  
    public void onAuthenticationSuccess(HttpServletRequest request,  
            HttpServletResponse response, Authentication authentication){  
        try{  
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();  
        //输出登录提示信息  
        System.out.println("管理员 " + userDetails.getUsername() + " 登录");
        System.out.println("------------记录日志---------------");
        String name = userDetails.getUsername();
        MgrUser tempUser = new MgrUser();
		tempUser.setUsername(name);
		MgrUser mu = userService.login(tempUser);
		Long id = mu.getId();
		List<MgrUserRole> userRole = userRoleService.queryRoleByUid(id);
	 	List<MgrRoleMenu> roleMenu = new ArrayList<MgrRoleMenu>();
		List<MgrMenu> listMenu = new ArrayList<MgrMenu>();
		MgrMenu  menuDO= null;
		Long mId = 0l;
			for (MgrUserRole ur : userRole) {
                roleMenu = roleMenuService.queryByRoleId(ur.getRoleid());
                for (MgrRoleMenu roleMenuDO : roleMenu) {
                	boolean flag = true;
                	mId = roleMenuDO.getMenuId();
                	for (MgrMenu menu : listMenu) {
                		Long menuId = menu.getId();
                		if(mId == menuId){
                			flag = false;
                		}
                }
                	if(flag){
                		menuDO =  menuService.selectByPrimaryKey(mId);
                		if(menuDO != null){
                        listMenu.add(menuDO);
                		}
                	}
                	
                }
			}
			response.sendRedirect("/tplc/login/index");
	        super.onAuthenticationSuccess(request, response, authentication); 
        }catch(Exception  e){
        	e.printStackTrace();
        }
        
    }

	public RoleMenuService getRoleMenuService() {
		return roleMenuService;
	}

	public void setRoleMenuService(RoleMenuService roleMenuService) {
		this.roleMenuService = roleMenuService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
