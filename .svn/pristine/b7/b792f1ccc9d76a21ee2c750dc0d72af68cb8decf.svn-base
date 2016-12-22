<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/css.jsp"%>
<%@ include file="/common/js.jsp"%>
<%@ include file="/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<script type="text/javascript">
			$(function(){
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'日志' ,
			fit:true ,
			url:'queryLog' ,
			fitColumns:true ,  
			striped: true ,					//隔行变色特性 
			//nowrap: false ,				//折行显示 为true 显示在一会 
			loadMsg: '数据正在加载,请耐心的等待...' ,
			rownumbers:true ,
			
	       frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
// 	                           {
// 									field : 'ck',
// 									width : 50,
// 									checkbox : true
// 								} 
							] ],
							columns : [ [
									{
										field : 'username',
										title : '登录用户',
										width : 50,
										align : 'center',
										
									},
									{
										field : 'userrole',
										title : '角色名',
										width : 100,
										align : 'center',
										
									},
									{
										field : 'logContent',
										title : '日志内容',
										width : 200,
										align : 'center',
										
									},
									  {
										field : 'addtime',
										title : '更新时间',
										width : 100,
										align : 'center',
									},

							] ],
							onClickRow: function (rowIndex, rowData) {
	                             $(this).datagrid('unselectRow', rowIndex);
	                         }, 
							pagination : true,
							pageSize : 10,
							pageList : [ 5, 10, 15, 20, 50 ],
						});

		$('#list').datagrid('getPager').pagination({  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    }); 
		
		/**
		 * 关闭窗口方法
		 */
		$('#close').click(function() {
			$('#add').dialog('close');
		});

		$('#searchbtn').click(function(){
			$('#list').datagrid('load' ,serializeForm($('#search')));
		});
		
		$('#clearbtn').click(function(){
			$('#search').form('clear');
			$('#list').datagrid('load' ,{});
		});
		
		if('<shiro:hasRole name="admin" > </shiro:hasRole>'){
		    $('#authAdd').show();
			$('#authUpdate').show();
			$('#authDelete').show();
		}else{
			$('#authAdd').hide();
			$('#authUpdate').hide();
			$('#authDelete').hide();
		}
	});
			
</script>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true">
				<div region="north" title="商品标签库查询" collapsed=false style="height:30%; padding: 5px;" >
					<form id="search" method="post">
					<table style="padding: 5px;">
					<tr>
					<td>&nbsp;&nbsp;用户名: &nbsp;&nbsp;</td>
					<td>
					<input class="easyui-textbox" id="username" name="username" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>&nbsp;&nbsp;角色名: &nbsp;&nbsp;</td>
					<td>
					<input class="easyui-textbox" id="userrole" name="userrole" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>&nbsp;&nbsp;添加时间:&nbsp;&nbsp;</td>
					<td><input id="beginTime" name="beginTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"   value="" />&nbsp;&nbsp;</td>
					</tr>
					<tr><td></td><td></td></tr>
	    			<tr><td></td><td></td></tr>
					<tr>
					<td>&nbsp;&nbsp;到:&nbsp;&nbsp;</td>
					<td><input id="endTime"  name="endTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"  value="" />
					</td>
					<td><a  id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 25px;">查询</a></td>
					
				   <td>
					<a  id="clearbtn" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" style="width:80px;height: 25px;">清空</a>
				   
				   </td>
					
					</tr>
					</table>

					</form>
				
				</div>
				<div region="center" >
					<table id="list"></table>
				</div>
			</div>
  </body>
</html>