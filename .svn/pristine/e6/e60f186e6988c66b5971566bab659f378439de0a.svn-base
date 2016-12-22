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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbgo.searchmgr.core.bean.MgrMenu;
import com.mbgo.searchmgr.core.bean.MgrRoleMenu;
import com.mbgo.searchmgr.core.bean.MgrUser;
import com.mbgo.searchmgr.core.bean.MgrUserRole;
import com.mbgo.searchmgr.core.service.MenuService;
import com.mbgo.searchmgr.core.service.RoleMenuService;
import com.mbgo.searchmgr.core.service.RoleService;
import com.mbgo.searchmgr.core.service.UserRoleService;
import com.mbgo.searchmgr.core.service.UserService;
import com.mbgo.searchmgr.core.util.ConvertUtil;
import com.mbgo.searchmgr.core.util.DtoSelect;
import com.mbgo.searchmgr.core.util.JsonDateValueProcessorUtil;
import com.mbgo.searchmgr.core.util.ResultUtil;

@Controller
@RequestMapping("/menu")
public class MenuController{

	@Autowired
	public UserService userService;
	@Autowired
	public UserRoleService userRoleService;
	@Autowired
	public MenuService menuService;
	@Autowired
	public RoleService roleService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	public final static String PACKAGE = "pages/view/system";
	  @RequestMapping(value = "/toMenu")
	    public String toMenu() {
	        return PACKAGE + "/menu";
	  }
	
	  @RequestMapping("/loadLeft")
		@ResponseBody
		public List<MgrMenu> loadLeft(HttpServletRequest request){
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String name = userDetails.getUsername();
			MgrUser user = userService.queryUserByName(name);
			List<MgrUserRole> userRole = userRoleService.queryRoleByUid(user.getId());
		 	List<MgrRoleMenu> roleMenu = new ArrayList<MgrRoleMenu>();
			List<MgrMenu> listMenu = new ArrayList<MgrMenu>();
			MgrMenu  menuDO= null;
			Long mId = 0l;
			try{
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
	                		menuDO =  menuService.selectByMenuId(mId);
	                		if(menuDO != null){
	                        listMenu.add(menuDO);
	                		}
	                	}
	                	
	                }
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return listMenu;
		}
	  @RequestMapping(value = "/queryMenu")
	  public void queryMenu(HttpServletRequest request,HttpServletResponse response) {
		  Map<String,Object> map = null;
		  int currentPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		  int pageSize    = Integer.parseInt(request.getParameter("rows")==null?"1":request.getParameter("rows"));		  
		  String name = request.getParameter("name1") == null?"":request.getParameter("name1");
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
		  map.put("name",name);
		  map.put("beginTime", at);
		  map.put("endTime", et);
		  map.put("currentPage", currentPage);
		  map.put("pageSize", pageSize);
		  List<MgrMenu> listPage = menuService.selectAndPage(map);
		  int total = menuService.selectPageTotal(map);
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
 
	  @RequestMapping(value = "/getMenuName")
	  @ResponseBody
	  public List<DtoSelect> getMenuName(HttpServletRequest request,HttpServletResponse response) {
		  List<DtoSelect> list = new ArrayList<DtoSelect>();
		  List<MgrMenu> listMenu = menuService.queryMenuName(0l);
		  for (MgrMenu mgrMenu : listMenu) {
			  list.add(new DtoSelect(mgrMenu.getParentId(),mgrMenu.getName()));
		}
		 
		  return list;
	  }
	  @RequestMapping(value = "/getMenuCom")
	  @ResponseBody
	  public List<MgrMenu> getMenuCom(HttpServletRequest request,HttpServletResponse response) {
		  List<DtoSelect> list = new ArrayList<DtoSelect>();
		  List<MgrMenu> listMenu = menuService.queryMenuName(0l);
		  return listMenu;
	  }
 
	  
	  @RequestMapping(value = "/addMenu")
	  @ResponseBody
	  public List<ResultUtil> addMenu(HttpServletRequest request) {
		List<ResultUtil> list = new ArrayList<ResultUtil>();
		ResultUtil ru = null;
		MgrMenu menu = new MgrMenu();
		try{
		ru = new ResultUtil();
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String parentId =  request.getParameter("parentId");
		Long at = new Date().getTime();
		Date ut = new Date();
		menu.setName(name);
		menu.setUrl(url);
		menu.setAddTime(at);
		menu.setLastUpdate(ut);
		menu.setParentId(Long.parseLong(parentId));
		menuService.insertSelective(menu);
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
	  
	  @RequestMapping(value = "/updateMenu")
	  @ResponseBody
	  public List<ResultUtil> updateMenu(HttpServletRequest request) {
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = null;
		  MgrMenu menu = new MgrMenu();
		  try{
			  ru = new ResultUtil();
			  String id = request.getParameter("id");
			  String name = request.getParameter("name");
			  String url = request.getParameter("url");
			  String parentId =  request.getParameter("parentId");
			  Date lu = new Date();
			  menu.setId(Long.parseLong(id));
			  menu.setName(name);
			  menu.setUrl(url);
			  menu.setLastUpdate(lu);
			  menu.setParentId(Long.parseLong(parentId));
			  menuService.updateMenu(menu);
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
	  
	  @RequestMapping(value = "/deleteMenu")
	  @ResponseBody
	  public List<ResultUtil> deleteMenu(HttpServletRequest request) {
		  StringBuilder temp = new StringBuilder("");
		  List<ResultUtil> list = new ArrayList<ResultUtil>();
		  ResultUtil ru = new ResultUtil();
		  String  ids = request.getParameter("ids");
		  Long [] lic = ConvertUtil.convertLongArray(ids);
		  for (Long id : lic) {
			List<MgrRoleMenu> listRm = roleMenuService.queryByMenuId(id);
			if(listRm.size()>0){
				MgrMenu  menu = menuService.selectByPrimaryKey(id);
				temp.append(menu.getName()).append("、");
			}else{
				menuService.deleteByPrimaryKey(id);
			}
		  }
		 if(temp.toString().equals("")){
			 ru.setStatus("提示信息");
			 ru.setMessage("删除成功");
		 }else{
			 String str = temp.substring(0, temp.length()-1);
			 temp.delete(0, temp.length());
			 temp.append(str);
			 temp.append("菜单与角色表管理，不能删除");
			 ru.setStatus("提示信息");
			 ru.setMessage(temp.toString());
		 } 
		  list.add(ru);
		  return list;
	  }

	  
	  
	  
}
