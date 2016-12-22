package com.mbgo.searchmgr.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrUser;
import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.service.UserService;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/userRole")
public class UserRoleController{

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;
	
	public final static String PACKAGE = "pages/view/system";
	  @RequestMapping(value = "/toUserRole")
	    public String toUserRole() {
	        return PACKAGE + "/userRole";
	    }
	
	  	@RequestMapping(value = "/queryUser")
	  	@ResponseBody
	    public List<MgrUser> queryUser(HttpServletRequest request,HttpServletResponse response) {
		  List<MgrUser> listPage = userService.queryUserAll();
		  return listPage;
		  }
	  	@RequestMapping(value = "/queryRole")
	  	@ResponseBody
	  	public List<MgrRole> queryRole(HttpServletRequest request,HttpServletResponse response) {
	  		List<MgrRole> listPage = roleService.queryRole();
	  		return listPage;
	  	}
	  	@RequestMapping(value = "/queryRoleByuid")
	  	@ResponseBody
	  	public List<MgrRole> queryRoleByuid(HttpServletRequest request) {
	  		String userId = request.getParameter("user");
	  		List<MgrUserRole> listPage = userRoleService.queryRoleByUid(Long.parseLong(userId));
	  		List<MgrRole> listRole = new ArrayList<MgrRole>();
	  		MgrRole role = null;
	  		for (MgrUserRole mgrUserRole : listPage) {
				role = roleService.queryRoleId(mgrUserRole.getRoleid());
				listRole.add(role);
			}
	  		
	  		return listRole;
	  	}
	  	@RequestMapping(value = "/updateUserRole")
	  	@ResponseBody
	  	 public List<ResultUtil> updateUserRole(HttpServletRequest request){
	  		List<ResultUtil> result = null;
			ResultUtil ru = null;
	  		 try{
			List<MgrUserRole> listUR = new ArrayList<MgrUserRole>();
			List<Long> list = new ArrayList<Long>();
			MgrUserRole ur = null;
		    result = new ArrayList<ResultUtil>();
		    ru = new ResultUtil();
			String userId = request.getParameter("user");
			String[] roleId = request.getParameterValues("role");
			list.add(Long.parseLong(userId));
			if (null == roleId || roleId.equals("")) {
				userRoleService.deleteBatchByList(list);
			} else {
				userRoleService.deleteBatchByList(list);
				for (String id : roleId) {
					listUR.clear();
					ur = new MgrUserRole();
					ur.setUserid(Long.parseLong(userId));
					ur.setRoleid(Long.parseLong(id));
					listUR.add(ur);
					userRoleService.addBatch(listUR);
				}
			}
			  
			  ru.setStatus("提示信息");
			  ru.setMessage("修改用户角色成功");
              result.add(ru);
			  }catch (Exception e) {
				ru.setStatus("提示信息");
				ru.setMessage("修改用户角色失败");
				result.add(ru);
			}
			  return result;
		  }

	  
	  
}
