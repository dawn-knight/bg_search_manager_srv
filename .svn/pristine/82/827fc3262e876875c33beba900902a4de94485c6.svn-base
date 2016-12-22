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

import com.mbgo.searchmgr.core.bean.MgrUser;
import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.service.UserService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/user")
public class UserController{

	@Autowired
	public UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	public final static String PACKAGE = "pages/view/system";
	  @RequestMapping(value = "/toUser")
	    public String toUser() {
	        return PACKAGE + "/user";
	    }
	
	  @RequestMapping(value = "/queryUser")
	  public void queryUser(HttpServletRequest request,HttpServletResponse response) {
		  Map<String,Object> map = null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String username = request.getParameter("username1") == null?"":request.getParameter("username1");
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
		  map.put("username",username);
		  map.put("beginTime",at);
		  map.put("endTime",et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrUser> listPage = userService.selectAndPage(map);
		  int total = userService.selectPageTotal(map);
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
 
	  @RequestMapping(value = "/addUser")
	  @ResponseBody
	  public List<ResultUtil> addUser(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrUser user = new MgrUser();
		try{
		ru = new ResultUtil();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		Long at = new Date().getTime();
		Date ut = new Date();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(Integer.parseInt(sex));
		user.setAddTime(at);
		user.setLastUpdate(ut);
		userService.insertSelective(user);
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
	  
	  @RequestMapping(value = "/updateUser")
	  @ResponseBody
	  public List<ResultUtil> updateUser(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrUser user = new MgrUser();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String username = request.getParameter("username");
			  String password = request.getParameter("password");
			  String sex = request.getParameter("sex");
			  Date lu = new Date();
			  user.setId(Long.parseLong(id));
			  user.setUsername(username);
			  user.setPassword(password);
			  user.setSex(Integer.parseInt(sex));
			  user.setLastUpdate(lu);
			  userService.updateUser(user);
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
	  
	  @RequestMapping(value = "/deleteUser")
	  @ResponseBody
	  public List<ResultUtil> deleteUser(HttpServletRequest request) {
		  StringBuilder temp = new StringBuilder("");
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = new ResultUtil();
		  String  ids = request.getParameter("ids");
		  Long [] lic = ConvertUtil.convertLongArray(ids);
		  for (Long id : lic) {
			List<MgrUserRole> listUr = userRoleService.queryRoleByUid(id);
			if(listUr.size()>0){
				MgrUser  user = userService.selectByPrimaryKey(id);
				temp.append(user.getUsername()).append("、");
			}else{
				userService.deleteByPrimaryKey(id);
			}
		  }
		 if(temp.toString().equals("")){
			 ru.setStatus("提示信息");
			 ru.setMessage("删除成功");
		 }else{
			 String str = temp.substring(0, temp.length()-1);
			 temp.delete(0, temp.length());
			 temp.append(str);
			 temp.append("用户与角色表管理，不能删除");
			 ru.setStatus("提示信息");
			 ru.setMessage(temp.toString());
		 } 
		  list.add(ru);
		  return list;
	  }
	  
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	  
	  
}
