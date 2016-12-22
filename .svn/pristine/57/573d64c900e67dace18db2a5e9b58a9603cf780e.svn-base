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
<title>自定义分词</title>
<script type="text/javascript">
			$(function(){
				$('#weight').numberbox({
					min:0 , 
					max:10 ,
					required:true , 
					missingMessage:'权重必填!' ,
					precision:0
				});
				
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'自定义关键词' ,
			//width:1300,
			fit:true ,
			height:650,
			url:'queryDicKeyword' ,
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
										title : '关键字',
										width : 100,
										align : 'center',
									},
                                    {
										field : 'addTime',
										title : '添加时间',
										width : 100,
										align : 'center',
										sortable:true,
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
									{
										field : 'weight',
										title : '权重',
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
								id : 'authAdd',
								text : '新增分词',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'add';
									$('#add').dialog({
										title : '新增分词',
										
									});
// 									$('#addform').get(0).reset();
                                    $('#addform').form('clear');
                                    $("#weight").numberbox('setValue',0);
									$('#add').dialog('open');
								}
							},{
								    id : 'authUpdate',
									text:'修改分词' ,
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
												title:'修改分词'
											});
											$('#add').dialog('open'); //打开窗口
// 											$('#addform').get(0).reset();   //清空表单数据 
                                            $('#addform').form('clear');
											$('#add').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
												id:arr[0].id,
												word:arr[0].word,
												weight:arr[0].weight,
											});
										}
									
									}
								},{
									 id : 'authDelete',
									text:'删除分词' ,
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
																$.post('deleteKeyword',
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
					url : 'addword',
					url : flag=='add'?'addword':'updateword',
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
			
			
			//js方法：序列化表单 			
			function serializeForm(form){
				var obj = {};
				$.each(form.serializeArray(),function(index){
					if(obj[this['name']]){
						obj[this['name']] = obj[this['name']] + ','+this['value'];
					} else {
						obj[this['name']] =this['value'];
					}
				});
				return obj;
			}
</script>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true" style="width:90%;height:80%;">
				<div region="north" title="自定义分词查询" collapsible=false style="height:21%; padding-top :20px; padding-left: 5px;" >
					<form id="search" method="post">
					<table>
					<tr>
					<td>&nbsp;&nbsp;关键词: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>
					<input class="easyui-textbox" id="word" name="word" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>添加时间:&nbsp;&nbsp;</td>
					<td><input id="beginTime" name="beginTime"  class="easyui-datetimebox" editable="false" style="width:160px; height: 25px;"  value="" />&nbsp;&nbsp;
					</td>
					<td>到:&nbsp;&nbsp;</td>
					<td><input id="endTime" name="endTime"  class="easyui-datetimebox" editable="false" style="width:160px; height: 25px;"  value="" />&nbsp;&nbsp;
					</td>
					<td>
					 <a  id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 25px;">查询</a>
					</td>
					<td></td>
					<td>
					<a  id="clearbtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px;height: 25px;">清空</a>
					</td>
					</tr>
					</table>

					</form>
				
				</div>
				<div region="center">
					<table id="list"></table>
				</div>
			</div>
  			
 			<div id="add" title="新增关键词" modal=true  draggable=false class="easyui-dialog" closed=true style="width:400px;height: 300px; padding-top: 10px; padding-left: 10px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<table>
	    					<tr>
	    						<td>&nbsp;&nbsp;关键词:&nbsp;&nbsp;</td>
	    						<td><input class="easyui-textbox" id="word" name="word" required=true validType="midLength[2,5]" missingMessage="关键字必填!" invalidMessage="用户名必须在2到5个字符之间!" style="width:180px;height:25px"></td>
	    					</tr>
	    					
	    					<tr><td></td><td></td></tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;权重:&nbsp;&nbsp;</td>
	    						<td>
	    						<input class="easyui-textbox" id="weight" name="weight" required=true   style="width:180px;height:25px">
	    						</td>
	    					</tr>  
							<tr><td></td><td></td></tr>
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
  	增加：关键字必须输，权重默认为0
	修改：权重只能为数字
  		
  		</pre>
  	</div>			
 			</div>
  </body>
</html>