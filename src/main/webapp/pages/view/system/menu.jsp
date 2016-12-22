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
<title>菜单管理</title>
<script type="text/javascript">
			$(function(){
				 $.ajax({
					    type:"post",
						url : '${rc.contextPath}/menu/getMenuCom', 
						dataType : "json",
						success : function(list) {
							var data = '[{"text":"一级菜单","id":"0"},{"text":"主页面","id":"-2"},';
							for(var i = 0;i<list.length;i++){
								data += '{"text":"'+list[i]["name"]+'","id":"'+list[i]["id"]+'"},';
							}
							data+= '{"text":"--增删改权限--","id":"-10"},',
							
							 $.ajax({
								    type:"post",
									url : '${rc.contextPath}/roleMenu/queryMenu', 
									dataType : "json",
									success : function(list) {
										for(var i = 0; i <list.length;i++){
											if(list[i]["parentId"] == 0){
												 for(var j= 0; j<list.length;j++){
													 if(list[j]["parentId"] == list[i]["id"]){
														 
													 data += '{"text":"'+list[j]["name"]+'","id":"'+list[j]["id"]+'"},';	 
													 }
												 }
											}
											}
										data = data.substring(0,data.length-1);
										data += ']';
										$("#parentId").combobox("loadData", eval('(' + data + ')'));	
									},
									error:function(a,b,c){
										alert("出错了");
									}
							    });
						},
						error:function(a,b,c){
							alert("出错了");
						}
				    });
// 				   data = data.substring(0,data.length-1);
				 	
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'角色' ,
			//width:1300 ,
			fit:true ,
			url:'queryMenu' ,
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
										field : 'name',
										title : '菜单名',
										width : 100,
										align : 'center',
										
									},
									{
										field : 'url',
										title : '连接',
										width : 100,
										align : 'center',
										
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
								text : '新增菜单',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'add';
									$('#add').dialog({
										title : '新增菜单'
									});
// 									$('#addform').get(0).reset();
									$('#addform').form('clear');
									$('#add').dialog('open');

								}
							},{
								    id:'authUpdate',
									text:'修改菜单' ,
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
												title:'修改菜单'
											});
											$('#add').dialog('open'); //打开窗口
// 											$('#addform').get(0).reset();   //清空表单数据 
											$('#addform').form('clear');
											$('#addform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
												id:arr[0].id,
												name:arr[0].name,
												url:arr[0].url,
												addTime:new Date(arr[0].addTime).format("yyyy-MM-dd hh:mm:ss"),
												lastUpdate:new Date(arr[0].lastUpdate).format("yyyy-MM-dd hh:mm:ss"),
												desc:arr[0].desc,
												parentId:arr[0].parentId,
											});
										}
									
									}
								},{
									id:'authDelete',
									text:'删除菜单' ,
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
																$.post('deleteMenu',
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
					url : flag=='add'?'addMenu':'updateMenu',
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
// 			var word = $('#word').val();
// 			var addTime = $('#addTime').val();
// 			var endTime = $('#endTime').val();
// 			var para = "word="+word+"&addTime="+addTime+"&endTime"+endTime;
			$('#list').datagrid('load' ,serializeForm($('#search')));
		});
		
		$('#clearbtn').click(function(){
			$('#search').form('clear');
			$('#list').datagrid('load' ,{});
		});
		
// 		$('#authAdd').hide();
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
				<div region="north" title="菜单查询" collapsible=false style="height:20%; padding-top :10px; padding-left: 5px;">
					<form id="search" method="post">
					<table>
					<tr>
					<td>&nbsp;&nbsp;菜单名称: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>
					<input class="easyui-textbox" id="name1" name="name1"  style="width:180px;height:25px">&nbsp;&nbsp;
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
  			
 			<div id="add" title="新增菜单" modal=true  draggable=false class="easyui-dialog" closed=true style="width:400px;height: 300px; padding-top: 10px; padding-left: 6px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<table>
	    					<tr>
	    						<td>&nbsp;&nbsp;菜单名:&nbsp;&nbsp;</td>
	    						<td>
	    						<input class="easyui-textbox" id="name" name="name" required=true missingMessage="菜单名必须填写" style="width:180px;height:25px">&nbsp;&nbsp;
	    						</td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;URL:&nbsp;&nbsp;</td>
	    						<td><input type="text" name="url" class="easyui-textbox" required=true missingMessage="菜单连接必须填写" style="width:180px;height:25px"  value="" /></td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;请选择上级菜单&nbsp;&nbsp;</td>
	    						<td>
<!-- 	    						<select class="easyui-combobox" id="parentId" style="width:180px;height:25px;" name="parentId" required=true missingMessage="请选择上级菜单"> -->
<!-- 									<option value="-1">请选择上级菜单</option> -->
<!-- 									<option value="0"  selected="selected">一级菜单</option> -->
<!-- 									<option value="20">搜索词库管理</option> -->
<!-- 									<option value="21">系统管理</option> -->
<!-- 									<option value="50">商品信息管理</option> -->
<!-- 									</select> -->
                                    <input class="easyui-combobox" id="parentId" required=true missingMessage="上级菜单必选"
                                     name="parentId" style="width:180px" 
                                    data-options="valueField:'id', textField:'text',groupField:'group'" >  
	    						</td>
	    						</tr>
	    						<tr><td></td><td></td></tr>
	    					<tr>
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
  	增加：菜单名、url、上级菜单必输。url只需要输入工程名
  	之后的比如MBgoSearchManagerSrv/login/successLogin
  	只需要输入login/successLogin
  	上级菜单必须选择，如果没有上级菜单就选择以及菜单
  		</pre>
  	</div>						
 			</div>
  </body>
</html>