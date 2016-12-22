package com.mbgo.searchmgr.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/roleMenu")
public class RoleMenuController{

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	public final static String PACKAGE = "pages/view/system";
	  @RequestMapping(value = "/toRoleMenu")
	    public String toRoleMenu() {
	        return PACKAGE + "/roleMenu";
	    }
	
	  	@RequestMapping(value = "/queryRole")
	  	@ResponseBody
	  	public List<MgrRole> queryRole(HttpServletRequest request,HttpServletResponse response) {
	  		List<MgrRole> listPage = roleService.queryRole();
	  		return listPage;
	  	}
	  	@RequestMapping(value = "/queryMenu")
	  	@ResponseBody
	  	public List<MgrMenu> queryMenu(HttpServletRequest request,HttpServletResponse response) {
	  		List<MgrMenu> listPage = menuService.queryMenu();
	  		return listPage;
	  	}
	  
		@RequestMapping(value = "/queryMenuByRid")
	  	@ResponseBody
	  	public List<MgrMenu> queryMenuByRid(HttpServletRequest request) {
	  		String roleId = request.getParameter("role");
	  		List<MgrRoleMenu> listPage = roleMenuService.queryRoleByRid(Long.parseLong(roleId));
	  		List<MgrMenu> listRole = new ArrayList<MgrMenu>();
	  		MgrMenu menu = null;
	  		for (MgrRoleMenu mgrUserRole : listPage) {
				menu = menuService.selectByPrimaryKey(mgrUserRole.getMenuId());
				listRole.add(menu);
			}
	  		
	  		return listRole;
	  	}
		
		@RequestMapping(value = "/updateRoleMenu")
	  	@ResponseBody
	  	 public List<ResultUtil> updateRoleMenu(HttpServletRequest request){
	  		List<MgrRoleMenu> listUR = new ArrayList<MgrRoleMenu>();
	  		List<Long> list = new ArrayList<Long>();
	  		   MgrRoleMenu rm = null;
	  		   List<ResultUtil> result = new ArrayList<ResultUtil>();
			  ResultUtil ru = new ResultUtil();
			  try{
			  String roleId = request.getParameter("role");
			  String [] menuId = request.getParameterValues("menu");
			  list.add(Long.parseLong(roleId));
			  if(null == menuId || menuId.equals("")){
				  roleMenuService.deleteBatchByList(list);
			  }else{
				 roleMenuService.deleteBatchByList(list);
			  for (String id : menuId) {
				listUR.clear();
                rm = new MgrRoleMenu();
                rm.setRoleId(Long.parseLong(roleId));
                rm.setMenuId(Long.parseLong(id));
                listUR.add(rm);
                roleMenuService.addBatch(listUR);
			}
			 
			  }
			  ru.setStatus("提示信息");
			  ru.setMessage("修改角色权限成功");
			  result.add(ru);
			  }catch (Exception e) {
				e.printStackTrace();
				ru.setStatus("提示信息");
				ru.setMessage("修改角色权限失败");
				result.add(ru);
			}
				  
			  return result;
		  }
		
}
