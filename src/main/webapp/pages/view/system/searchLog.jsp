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
<title>搜索错误日志</title>
<script type="text/javascript">
			$(function(){
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'日志' ,
			fit:true ,
			url:'queryErrorLog',
			fitColumns:true ,  
			striped: true ,					//隔行变色特性 
			//nowrap: false ,				//折行显示 为true 显示在一会 
			loadMsg: '数据正在加载,请耐心的等待...' ,
			rownumbers:true ,
	       frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
	                           {
									field : 'ck',
									width : 50,
									checkbox : true
								}
							 ] ],
							columns : [ [
									{
										field : 'productId',
										title : '商品ID',
										width : 100,
										align : 'center',
										
									},
									{
										field : 'logType',
										title : '商品类型',
										width : 100,
										align : 'center',
										
									},
									{
										field : 'logMsg',
										title : '日志内容',
										width : 200,
										align : 'center',
										
									},
									  {
										field : 'addTime',
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
							toolbar : [{
									id:'authDelete',
									text:'删除用户' ,
									iconCls:'icon-remove' , 
									handler:function(){
											var arr =$('#list').datagrid('getSelections');
											if(arr.length <=0){
												$.messager.show({
													title:'提示信息!',
													msg:'至少选择一行记录进行删除!'
												});
											} else {
												
												$.messager.confirm('提示信息' , '确认删除?' , function(r){
														if(r){
																var ids = '';
																for(var i =0 ;i<arr.length;i++){
																	ids += arr[i].id + ',' ;
																}
																ids = ids.substring(0 , ids.length-1);
																$.post('deleteSearchLog',
																{ids:ids} ,
																function(result){
																	//1 刷新数据表格 
																	$('#list').datagrid('reload');
																	//2 清空idField   
																	$('#list').datagrid('unselectAll');
																	//3 给提示信息 
																	$.messager.show({
																		title : result[0]["status"],
																		msg : result[0]["message"],
																	});
																});
														} else {
															return ;
														}
												});
											}
								}

							} ]
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
		
		$('#authDelete').hide();
		var au = $("#auth").val();
		var listdelete = $("#listdelete").val().split(",");
		var temps = au.substring(1,au.length);
		var tempe = temps.substring(0,temps.length-1);
		var end = tempe.split(",");
		for(var i = 0; i< end.length;i++){
			for(var j = 0;j<listdelete.length;j++){
			if(end[i].trim() == listdelete[j]){
				$('#authDelete').show();
			}
			}
		}
	});
			
</script>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true">
				<div region="north" title="搜索错误信息" collapsed=false style="height:20%; padding: 5px;" >
					<form id="search" method="post">
					<table style="padding: 5px;">
					<tr>
					<td>&nbsp;&nbsp;商品ID: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input class="easyui-textbox" id="productId" name="productId" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>&nbsp;&nbsp;添加时间:&nbsp;&nbsp;</td>
					<td><input id="beginTime" name="beginTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"   value="" />&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;到:&nbsp;&nbsp;</td>
					<td><input id="endTime"  name="endTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"  value="" />
					</td>
					<td>&nbsp;&nbsp;<a  id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 25px;">查询</a></td>
					
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