package com.mbgo.searchmgr.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.searchmgr.core.bean.MgrRole;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/role")
public class RoleController{

	@Autowired
	public RoleService roleService;
	@Autowired
	public UserRoleService userRoleService;
	@Autowired
	public RoleMenuService roleMenuService;
	
	public final static String PACKAGE = "pages/view/system";
	  @RequestMapping(value = "/toRole")
	    public String toUser() {
	        return PACKAGE + "/role";
	    }
	
	  @RequestMapping(value = "/queryRole")
	  public void queryRole(HttpServletRequest request,HttpServletResponse response) {
		  Map<String,Object> map = null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String rolename = request.getParameter("rolename1") == null?"":request.getParameter("rolename1");
		  String beginTime = request.getParameter("beginTime") == null?"":request.getParameter("beginTime");
		  String endTime = request.getParameter("endTime") == null?"":request.getParameter("endTime");
		  Long at = ConvertUtil.dateConvert(beginTime);
		  Long et = ConvertUtil.dateConvert(endTime);
		  if(currentPage == 0){
			  currentPage = (currentPage) * pageSize;
		  }else{
		   currentPage = (currentPage-1) * pageSize;
		  }
		  map= new HashMap<String, Object>();
		  map.put("rolename",rolename);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrRole> listPage = roleService.selectAndPage(map);
		  int total = roleService.selectPageTotal(map);
		  JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessorUtil());
		  String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(listPage,jsonConfig).toString()+"}";
		  response.setContentType("text/html;charset=utf-8");
//		  mapTP.put("rows", listPage);
		  try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
 
	  @RequestMapping(value = "/addRole")
	  @ResponseBody
	  public List<ResultUtil> addRole(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrRole role = new MgrRole();
		try{
		ru = new ResultUtil();
		String rolename = request.getParameter("rolename");
		Long at = new Date().getTime();
		Date ut = new Date();
		role.setRolename(rolename);
		role.setAddTime(at);
		role.setLastUpdate(ut);
		roleService.insertSelective(role);
		ru.setStatus("提示信息");
		ru.setMessage("添加成功");
		list.add(ru);
		}catch (Exception e) {
			e.printStackTrace();
			ru = new ResultUtil();
			ru.setStatus("提示信息");
			ru.setMessage("添加失败");
		}
		return list;
	  }
	  
	  @RequestMapping(value = "/updateRole")
	  @ResponseBody
	  public List<ResultUtil> updateRole(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrRole role = new MgrRole();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String rolename = request.getParameter("rolename");
			  Date lu = new Date();
			  role.setId(Long.parseLong(id));
			  role.setRolename(rolename);
			  role.setLastUpdate(lu);
			  roleService.updateRole(role);
			  ru.setStatus("提示信息");
			  ru.setMessage("修改成功");
			  list.add(ru);
		  }catch (Exception e) {
			  e.printStackTrace();
			  ru = new ResultUtil();
			  ru.setStatus("提示信息");
			  ru.setMessage("修改失败");
		  }
		  return list;
	  }
	  
	  @RequestMapping(value = "/deleteRole")
	  @ResponseBody
	  public List<ResultUtil> deleteRole(HttpServletRequest request) {
		  StringBuilder tempUr = new StringBuilder("");
		  StringBuilder temRm = new StringBuilder("");
		  String status = "提示信息";
		  String message ="";
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = new ResultUtil();
		  String  ids = request.getParameter("ids");
		  Long [] lic = ConvertUtil.convertLongArray(ids);
		  for (Long id : lic) {
				List<MgrUserRole> listUr = userRoleService.queryByRid(id);
				List<MgrRoleMenu> listRm = roleMenuService.queryByRoleId(id);
				if(listRm.size()>0){
					MgrRole  role = roleService.queryRoleId(id);
					temRm.append(role.getRolename()).append("、");
				}
				else if(listUr.size()>0){
					MgrRole  role = roleService.queryRoleId(id);
					tempUr.append(role.getRolename()).append("、");
				}else{
					roleService.deleteByPrimaryKey(id);
				}
			  }
		  if(tempUr.toString().equals("") && temRm.toString().equals("")){
			   message = "删除成功";
			 }
		  if(!tempUr.toString().equals("")){
				 String str = tempUr.substring(0, tempUr.length()-1);
				 tempUr.delete(0, tempUr.length());
				 tempUr.append(str);
				 tempUr.append("用户与角色表关联，不能删除");
				 message = tempUr.toString();
			} 
		  if(!temRm.toString().equals("")){
				 String str = temRm.substring(0, temRm.length()-1);
				 temRm.delete(0, temRm.length());
				 temRm.append(str);
				 temRm.append("用户与菜单表关联，不能删除");
				 if (!message.equals("")) {
					 message = message+"、" + temRm.toString();
				}
				
			 } 
		  ru.setStatus(status);
		  ru.setMessage(message);
		  list.add(ru);
		  return list;
	  }

	  
	  
	  
}
