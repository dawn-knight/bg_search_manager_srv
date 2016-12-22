<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/css.jsp"%>
<%@ include file="/common/js.jsp"%>
<%@ include file="/common/auth.jsp"%>
<%@ include file="/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>热门关键字管理</title>
<script type="text/javascript">
			$(function(){
				$('#searchCount').numberbox({
					required:true , 
					missingMessage:'搜索次数必填!' ,
				});
				
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'商品标签列表' ,
			//width:1300 ,
			fit:true ,
			height:650 ,
			url:'queryHotKeyword' ,
			fitColumns:true ,  
			striped: true ,					//隔行变色特性 
			//nowrap: false ,				//折行显示 为true 显示在一会 
			loadMsg: '数据正在加载,请耐心的等待...' ,
			rownumbers:true ,
			checkOnSelect:false,
	       frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'ck',
								width : 50,
								checkbox : true
							} ] ],
							columns : [ [
									{
										field : 'word',
										title : '热门内容',
										width : 100,
										align : 'center',
									},
// 									{
// 										field : 'wordCode',
// 										title : '内容编码',
// 										width : 100,
// 										align : 'center',
// 									},
									{
										field : 'searchCount',
										title : '搜索次数',
										width : 50,
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
								text : '新增热门关键字',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'add';
									$('#add').dialog({
										title : '新增热门关键字'
									});
									$('#addform').get(0).reset();
									$("#searchCount").numberbox('setValue',0);
									$('#add').dialog('open');

								}
							},{
								    id:'authUpdate',
									text:'修改热门关键字' ,
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
												title:'修改热门关键字'
											});
											$('#add').dialog('open'); //打开窗口
											$('#addform').get(0).reset();   //清空表单数据 
											$('#addform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
												id:arr[0].id ,
												word:arr[0].word,
												searchCount:arr[0].searchCount,
											});
										}
									
									}
								},{
									id:'authDelete',
									text:'删除热门关键字' ,
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
																$.post('deleteHotKeyword',
																		{ids:ids} ,
																function(result){
																	//1 刷新数据表格 
																	$('#list').datagrid('reload');
																	//2 清空idField   
																	$('#list').datagrid('unselectAll');
																	//3 给提示信息 
																	$.messager.show({
																		title:'提示信息!' , 
																		msg:'操作成功!'
																	});
																});
														} else {
															return ;
														}
												});
											}
								}

							},{
							id:'exportCvs',
							text : '导出CSV文件',
							iconCls : 'icon-add',
							handler : function() {
								var file = prompt("请输入您的名字","D:/test.csv");//将输入的内容赋给变量 name ，
							    var grid = $('#list');  
							    var options = grid.datagrid('getPager').data("pagination").options;  
							    var page = options.pageNumber;  
							    var rows = options.total;  
							    var word1 = $("#word1").val();
							    var wordCode1 = $("#wordCode1").val();
							    var beginTime = $("#beginTime").val();
							    var endTime = $("#endTime").val();
							    var data ="word1=" +word1+"&wordCode1="+wordCode1+"&beginTime=" + beginTime+"&endTime="+endTime+"&page="+page+"&rows="+rows+"&file=" + file;
										$.ajax({
											type : 'post',
											url : 'exportCvs',
											cache : false,
											data : data,
											dataType : 'json',
											success : function(result) {
												$.messager.show({
													title : "提示信息",
													msg : "导出成功文件在" +file,
												});
											},
											error : function(result) {
												$.messager.show({
													title : "提示信息",
													msg : "导出失败。",
												});
											}
										});

							}
						},
							]
						});
		$('#list').datagrid('getPager').pagination({  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    }); 
		
		function valid(){
		 var word = $("#word").val();
		 var wordCode = $("#wordCode").val();
		 if(word == '' || wordCode == ''){
		 return false;
		 } else{
			 return true;
		 }
		}
		
		/**
		 *  提交表单方法
		 */
		$('#confirm').click(function() {
			if ($('#addform').form('validate')) {
				$.ajax({
					type : 'post',
					url : flag=='add'?'addHotKeyword':'updateHotKeyword',
					cache : false,
					data : $('#addform').serialize(),
					dataType : 'json',
					success : function(result) {
						if(result[0]["status"] == "1"){
		                      $.messager.show({
		                    		title:'提示信息',
		                    		msg:result[0]["message"],
		                    	});

							}else{
								//1 关闭窗口
								$('#add').dialog('close');
								//2刷新datagrid 
								$('#list').datagrid('reload');
								 $.messager.show({
			                    		title:'提示信息',
			                    		msg:result[0]["message"],
			                    	});
							}
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
		$('#exportCvs').hide();
		$('#authDelete').hide();
		var au = $("#auth").val();
		var listadd = $("#listadd").val().split(",");
		var listdelete = $("#listdelete").val().split(",");
		var listupdate = $("#listupdate").val().split(",");
		var listexp = $("#listexp").val().split(",");
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
			for(var j = 0;j<listexp.length;j++){
			if(end[i].trim() == listexp[j]){
				$('#listexp').show();
			}
			}
		}
		
	});
		
			
</script>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true" style="width:90%;height:80%;">
				<div region="north" title="热门关键字查询" collapsible=false style="height:21%; padding-top :20px; padding-left: 5px;" >
					<form id="search" method="post">
					<table>
					<tr>
					<td>&nbsp;&nbsp;热门内容: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>
					<input type="hidden" id="listexp" name="listexp" value='${listexp}'>
					<input class="easyui-textbox" id="word1" name="word1" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					
					<td>&nbsp;&nbsp;添加时间:&nbsp;&nbsp;</td>
					<td><input id="beginTime" name="beginTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"   value="" />&nbsp;&nbsp;</td>
					
					<td>&nbsp;&nbsp;到:&nbsp;&nbsp;</td>
					<td><input id="endTime" name="endTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"  value="" />
					</td>
					<td></td>
					<td><a  id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 25px;">查询</a></td>
					  <td>
					<a  id="clearbtn" class="easyui-linkbutton" style="width:80px;height: 25px;">清空</a>
				   </td>
					</tr>
					</table>

					</form>
				
				</div>
				<div region="center" >
					<table id="list" width="650px;"></table>
				</div>
			</div>
  			
 			<div id="add" title="新增商品标签" modal=true  draggable=false class="easyui-dialog" closed=true style="width:400px;height: 300px;  padding-top: 10px; padding-left: 6px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" id = "id" name="id" value="" />
	    				<table>
	    					<tr>
	    						<td>热门内容:</td>
	    							<td><input class="easyui-textbox" id="word" name="word" required=true   missingMessage="热门内容必填!" style="width:180px;height:25px"></td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>搜索次数:</td>
	    						<td>
	    							<input class="easyui-textbox" id="searchCount" name="searchCount" required=true   style="width:180px;height:25px">
	    						</td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
							<tr><td></td><td></td></tr>
	    					<tr align="center">
	    						<td colspan="2" align="center">
	    						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	    						 <a  id="confirm" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;height: 25px;">确定</a>
	    						 <a  id="close" class="easyui-linkbutton"  data-options="iconCls:'icon-cut'" style="width:80px;height: 25px;">关闭</a>
	    						</td>
	    					</tr>  					 					    					    					    					    					    					    					    					
	    				</table>
	    		</form> 
	    		   	<div style="color:#444444; font-size:13px;">
  		<pre>
  规则：
  	增加：热门内容必须输，美邦、邦美属于同一个不
  	允许重复。  搜索次数默认为0。
	修改：不允许修改为已经存在的热门内容，或者
	    Unicode code相同，搜索次数只能为数字
  		
  		</pre>
  	</div>				
 			</div>
  </body>
</html>