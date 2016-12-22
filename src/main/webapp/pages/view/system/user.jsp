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
<title>用户管理</title>
<script type="text/javascript">
			$(function(){
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'用户列表' ,
			fit:true ,
			url:'queryUser' ,
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
							} ] ],
							columns : [ [
									{
										field : 'username',
										title : '用户名',
										width : 100,
										align : 'center',
										
									},
									{
										field : 'sex',
										title : '性别',
										width : 100,
										align : 'center',
										formatter : function(value, record, index){
											if (value == undefined) {
												return "";
											}
											if(value == 1){
												return '<span style=color:red; >男</span>' ;
											} else if( value == 0){
												return '<span style=color:green; >女</span>' ; 
											}
										}
									},
									
                                    {
										field : 'addTime',
										title : '添加时间',
										width : 100,
										align : 'center',
										formatter : function(value, rec, index) {
											if (value == undefined) {
												return "";
											}
											var tempDate = new Date(value);
											return tempDate
													.format("yyyy-MM-dd hh:mm:ss");

										}
									},
									  {
										field : 'lastUpdate',
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
							toolbar : [ {
								id:'authAdd',
								text : '新增用户',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'add';
									$('#add').dialog({
										title : '新增用户'
									});
// 									$('#addform').get(0).reset();
									$('#addform').form('clear');
									$('#add').dialog('open');

								}
							},{
								    id:'authUpdate',
									text:'修改用户' ,
									iconCls:'icon-edit' , 
									handler:function(){
										flag = 'edit';
										var arr =$('#list').datagrid('getSelections');
										if(arr.length != 1){
											$.messager.show({
												title:'提示信息!',
												msg:'只能选择一行记录进行修改!'
											});
										} else {
											$('#add').dialog({
												title:'修改用户'
											});
											$('#add').dialog('open'); //打开窗口
// 											$('#addform').get(0).reset();   //清空表单数据 
											$('#addform').form('clear');
											$('#addform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
												id:arr[0].id,
												username:arr[0].username,
												password:arr[0].password,
												sex:arr[0].sex,
												addTime:new Date(arr[0].addTime).format("yyyy-MM-dd hh:mm:ss"),
												lastUpdate:new Date(arr[0].lastUpdate).format("yyyy-MM-dd hh:mm:ss")
											});
										}
									
									}
								},{
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
																$.post('deleteUser',
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
		 *  提交表单方法
		 */
		$('#confirm').click(function() {
			if ($('#addform').form('validate')) {
				$.ajax({
					type : 'post',
					url : flag=='add'?'addUser':'updateUser',
					cache : false,
					data : $('#addform').serialize(),
					dataType : 'json',
					success : function(result) {
						//1 关闭窗口
						$('#add').dialog('close');
						//2刷新datagrid 
						$('#list').datagrid('reload');
						//3 提示信息
						$.messager.show({
							title : result[0]["status"],
							msg : result[0]["message"],
						});
					},
					error : function(result) {
						$.meesager.show({
							title : result[0]["status"],
							msg : result[0]["message"],
						});
					}
				});
			} else {
				$.messager.show({
					title : '提示信息!',
					msg : '数据验证不通过,不能保存!'
				});
			}
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
		
		$('#authAdd').hide();
		$('#authUpdate').hide();
		$('#authDelete').hide();
		var au = $("#auth").val();
		var listadd = $("#listadd").val().split(",");
		var listdelete = $("#listdelete").val().split(",");
		var listupdate = $("#listupdate").val().split(",");
		var temps = au.substring(1,au.length);
		var tempe = temps.substring(0,temps.length-1);
		var end = tempe.split(",");
		for(var i = 0; i< end.length;i++){
			for(var j = 0;j<listadd.length;j++){
				if(end[i].trim() == listadd[j]){
					  $('#authAdd').show();
				}
			}
			
			for(var j = 0;j<listdelete.length;j++){
			if(end[i].trim() == listdelete[j]){
				$('#authDelete').show();
			}
			}
			for(var j = 0;j<listupdate.length;j++){
			if(end[i].trim() == listupdate[j]){
				$('#authUpdate').show();
			}
			}
		}
		
	});
			
</script>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true">
				<div region="north" title="用户查询" collapsible=false style="height:20%; padding-top :10px; padding-left: 5px;" >
					<form id="search" method="post">
					<table>
					<tr>
					<td>&nbsp;&nbsp;用户名: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>
					<input class="easyui-textbox" id="username1" name="username1" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>添加时间:&nbsp;&nbsp;</td>
					<td><input id="beginTime" name="beginTime"  class="easyui-datetimebox" editable="false" style="width:160px; height: 25px;"  value="" />&nbsp;&nbsp;</td>
					<td>到:&nbsp;&nbsp;</td>
					<td><input id="endTime" name="endTime"  class="easyui-datetimebox" editable="false" style="width:160px; height: 25px;"  value="" />&nbsp;&nbsp;
					</td>
					<td>
					 <a  id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 25px;">查询</a>
					</td>
					<td></td>
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
  			
 			<div id="add" title="新增用户" modal=true  draggable=false class="easyui-dialog" closed=true style="width:350px;height: 240px; padding-top: 10px; padding-left: 6px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<table>
	    					<tr>
	    						<td>&nbsp;&nbsp;用户名:&nbsp;&nbsp;</td>
	    						<td>
	    							<input class="easyui-textbox" id="username" name="username" required=true missingMessage="用户名必须填写"  style="width:180px;height:25px">&nbsp;&nbsp;
	    							</td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;密码:&nbsp;&nbsp;</td>
	    						<td>
	    						<input class="easyui-textbox" type="password" id="password" name="password" required=true missingMessage="密码必须填写"  style="width:180px;height:25px"></td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;性别&nbsp;&nbsp;</td>
	    						<td>
	    						<select class="easyui-combobox" id="sex" style="width:180px;height:25px;" name="sex" required=true missingMessage="请选择性别">
									<option value="1">男</option>
									<option value="0">女</option>
									</select>
	    						</td>
	    						</tr>
	    						<tr><td></td><td></td></tr>
	    					<tr align="center">
	    						<td colspan="2">
	    							<a id="confirm" class="easyui-linkbutton">确定</a>
	    							<a id="close" class="easyui-linkbutton">关闭</a>
	    						</td>
	    					</tr>   					 					    					    					    					    					    					    					    					
	    				</table>
	    		</form> 
	    		<div style="color:#444444; font-size:13px;">
  		<pre>
  规则：
  	增加：用户名必输、密码必输，两次密码必须相同。
  		</pre>
  	</div>			
 			</div>
  </body>
</html>