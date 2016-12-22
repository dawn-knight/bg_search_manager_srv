package com.mbgo.searchmgr.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.util.CommonUtil;

public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		StringBuilder delete = new StringBuilder("");
		StringBuilder add = new StringBuilder("");
		StringBuilder update = new StringBuilder("");
		StringBuilder imp = new StringBuilder("");
		StringBuilder exp = new StringBuilder("");
		String listdelete = "";
		String listadd = "";
		String listupdate = "";
		String listimp = "";
		String listexp = "";
		String uriAdd = "";
		String uriDelete = "";
		String uriUpdate = "";
		String uriimp = "";
		String uriexp = "";
		String uri = request.getRequestURI();    
		String path = request.getContextPath();
		String method = CommonUtil.getRemainingPath(uri,path,false);
		String [] temp = method.split("/");
		if(temp.length == 3){
			String short_uri = temp[1];
			uriAdd = "/"+short_uri+"/add";
			uriDelete = "/"+short_uri+"/delete";
			uriUpdate = "/"+short_uri+"/update";
			uriimp = "/"+short_uri+"/import";
			uriexp = "/"+short_uri+"/export";
			
			List<MgrMenu> menulist = menuService.queryMenuUrlFuzzy(short_uri);
			for (MgrMenu mgrMenu : menulist) {
				String mgrMenuUrl = mgrMenu.getUrl();
				Long menuId = mgrMenu.getId();
				 if(mgrMenuUrl.endsWith(uriDelete)){
					 List<MgrRoleMenu> listRM =	roleMenuService.queryByMenuId(menuId);
					 for (MgrRoleMenu mgrRoleMenu : listRM) {
						 MgrRole role = roleService.queryRoleId(mgrRoleMenu.getRoleId());
						 delete.append(role.getRolename()).append(",");
					} 
					 if(!delete.toString().equals("")){
					 listdelete = delete.substring(0, delete.length()-1);
					 }
				 }else if(mgrMenuUrl.endsWith(uriUpdate)){
					 List<MgrRoleMenu> listRM =	roleMenuService.queryByMenuId(menuId);
					 for (MgrRoleMenu mgrRoleMenu : listRM) {
						 MgrRole role = roleService.queryRoleId(mgrRoleMenu.getRoleId());
						 update.append(role.getRolename()).append(",");
					}
					 if(!update.toString().equals("")){
					 listupdate = update.substring(0, update.length()-1);
					 }
				 }else if(mgrMenuUrl.endsWith(uriAdd)){
					 List<MgrRoleMenu> listRM =	roleMenuService.queryByMenuId(menuId);
					 for (MgrRoleMenu mgrRoleMenu : listRM) {
						 MgrRole role = roleService.queryRoleId(mgrRoleMenu.getRoleId());
						 add.append(role.getRolename()).append(",");
					}
					 if(!add.toString().equals("")){
					 listadd = add.substring(0, add.length()-1);
					 }
				 }else if(mgrMenuUrl.endsWith(uriimp)){
					 List<MgrRoleMenu> listRM =	roleMenuService.queryByMenuId(menuId);
					 for (MgrRoleMenu mgrRoleMenu : listRM) {
						 MgrRole role = roleService.queryRoleId(mgrRoleMenu.getRoleId());
						 imp.append(role.getRolename()).append(",");
					}
					 if(!imp.toString().equals("")){
					 listimp = imp.substring(0, imp.length()-1);
					 }
		     	}else if(mgrMenuUrl.endsWith(uriexp)){
					 List<MgrRoleMenu> listRM =	roleMenuService.queryByMenuId(menuId);
					 for (MgrRoleMenu mgrRoleMenu : listRM) {
						 MgrRole role = roleService.queryRoleId(mgrRoleMenu.getRoleId());
						 exp.append(role.getRolename()).append(",");
					}
					 if(!exp.toString().equals("")){
					 listexp = exp.substring(0, exp.length()-1);
					 }
					 
				 }
			}
		}
		 request.setAttribute("listdelete", listdelete);
		 request.setAttribute("listupdate", listupdate);
		 request.setAttribute("listadd", listadd);
		 request.setAttribute("listimp", listimp);
		 request.setAttribute("listexp", listexp);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	

}
