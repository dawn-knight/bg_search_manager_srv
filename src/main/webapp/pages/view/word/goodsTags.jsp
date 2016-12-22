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
<title>商品标签库</title>
<script type="text/javascript">
$(function(){
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'商品标签列表' ,
			//width:1300 ,
			fit:true ,
			height:650 ,
			url:'queryGoodsTags' ,
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
										field : 'goodsSn',
										title : '商品码',
										width : 100,
										align : 'center',
									},
									{
										field : 'tagWord',
										title : '标签内容',
										width : 100,
										align : 'center',
									},
                                    {
										field : 'addTime',
										title : '添加时间',
										width : 100,
										align : 'center',
									},
// 									  {
// 										field : 'tagSort',
// 										title : '标签顺序值',
// 										width : 100,
// 										align : 'center',
// 									},
                                       {
										field : 'tagSort',
										title : '显示顺序',
										width : 100,
										align : 'center',
// 										formatter:function(value , record , index){
// 											if(value == 0){
// 												return '<span style=color:red; >不显示</span>' ;
// 											} else if( value == 1){
// 												return '<span style=color:green; >显示</span>'; 
// 											}
// 									}
									},
									{
										field : 'flag',
										title : '是否已删除',
										width : 50,
										align : 'center',
										formatter:function(value , record , index){
											if(value == 0){
												return '<span style=color:red; >已删除</span>' ;
											} else if( value == 1){
												return '<span style=color:green; >未删除</span>'; 
											}
									}
									},

							] ],
							onClickRow: function (rowIndex, rowData) {
	                             $(this).datagrid('unselectRow', rowIndex);
	                         }, 
							pagination : true,
							pageSize : 10,
							pageList : [ 5, 10, 15, 20, 50 ],
							toolbar : [ 
								{
									id:'authDelete',
									text:'删除商品标签' ,
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
																$.post('deleteGoodsTags',
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

							},
// 							{
// 								id:'updateLimit',
// 								text:'修改要显示的标签' ,
// 								iconCls:'icon-remove' , 
// 								handler:function(){
// 									flag = 'edit';
// 									var arr =$('#list').datagrid('getSelections');
// 									if(arr.length != 1){
// 										$.messager.show({
// 											title:'提示信息!',
// 											msg:'只能选择一行记录进行修改!'
// 										});
// 									} else {
// 										$('#add').dialog({
// 											title:'修改是要显示的标签'
// 										});
// 										$('#add').dialog('open'); //打开窗口
// //											$('#addform').get(0).reset();   //清空表单数据 
// 										$('#addform').form('clear');
// 										$('#addform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
// 											id:arr[0].id,
// 											goodsSn:arr[0].goodsSn,
// 											tagWord:arr[0].tagWord,
// 											isLimited:arr[0].isLimited,
// 										});
// 									}
								
// 								}

// 						}
							]
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
					url : 'updateLimit',
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
	<div id="lay" class="easyui-layout" fit="true" style="width:90%;height:78%;">
				<div region="north" title="商品标签库查询" collapsible=false style="height:26%; padding: 3px;" >
					<form id="search" method="post">
					<table style="padding: 5px;">
					<tr>
					<td>&nbsp;&nbsp;商品码: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>
					<input class="easyui-textbox" id="goodsSn1" name="goodsSn1" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>&nbsp;&nbsp;标签内容: &nbsp;&nbsp;</td>
					<td>
					<input class="easyui-textbox" id="tagWord1" name="tagWord1" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>&nbsp;&nbsp;添加时间:&nbsp;&nbsp;</td>
					<td><input id="beginTime" name="beginTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"   value="" />&nbsp;&nbsp;</td>
					</tr>
					<tr><td></td><td></td></tr>
					<tr>
					<td>&nbsp;&nbsp;到:&nbsp;&nbsp;</td>
					<td><input id="endTime"  name="endTime"  class="easyui-datetimebox" editable="false" style="width:180px;height: 25px;"  value="" />
					</td>
<%-- 					<td><td><%=request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern") %></td> --%>
					<td><a  id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 25px;">查询</a></td>
				   <td>
					<a  id="clearbtn" class="easyui-linkbutton" data-options="iconCls:'icon-cut'" style="width:80px;height: 25px;">清空</a>
				   
				   </td>
					
					</tr>
					</table>

					</form>
				
				</div>
				<div region="center" >
					<table id="list" width="650px;"></table>
				</div>
			</div>
  			
 			<div id=add title="新增商品标签" modal=true  draggable=false class="easyui-dialog" closed=true style="width:350px;height: 230px; padding-top: 10px; padding-left: 6px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<table>
	    					<tr>
	    						<td>&nbsp;&nbsp;商品码:&nbsp;&nbsp;</td>
	    						<td>
	    						<input class="easyui-textbox" id="goodsSn" name="goodsSn" required=true  readonly="readonly" missingMessage="商品码必填!" style="width:180px;height:25px">&nbsp;&nbsp;
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;标签内容:&nbsp;&nbsp;</td>
	    						<td>
	    						<input class="easyui-textbox" id="tagWord" name="tagWord" required=true  readonly="readonly"  missingMessage="标签内容必填!"  style="width:180px;height:25px"></td>
	    					</tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;是否要显示标签:&nbsp;&nbsp;</td>
	    						<td>
	    							否<input type="radio" readonly="readonly" checked="checked" id="isLimited" name="isLimited" value="0"/>
	    							 &nbsp;&nbsp;
	    							是<input type="radio" readonly="readonly" id="isLimited" name="isLimited" value="1" />
	    						</td>
	    					</tr>  
							<tr><td></td><td></td></tr>
							<tr><td></td><td></td></tr>
	    					<tr align="center">
	    						<td colspan="2" align="center">
	    						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	    						 <a  id="confirm" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;height: 25px;">提交</a>
	    						 <a  id="close" class="easyui-linkbutton"  data-options="iconCls:'icon-cut'" style="width:80px;height: 25px;">关闭</a>
	    					</tr>  
	    						
	    				</table>
	    		</form> 
 			</div>
  </body>
</html>



