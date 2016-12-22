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

<title>同义词库管理</title>

<script type="text/javascript">
			$(function(){
	    /**
		 *	初始化数据表格  
		 */
		$('#list').datagrid({
			idField:'id' ,		//只要创建数据表格 就必须要加 ifField
			title:'商品标签列表' ,
			//width:1300 ,
			fit:true ,
			url:'querySameKeyword' ,
			fitColumns:true ,  
			striped: true ,					//隔行变色特性 
			//nowrap: false ,				//折行显示 为true 显示在一会 
			loadMsg: '数据正在加载,请耐心的等待...' ,
			rownumbers:true ,
// 			singleSelect : true,
			checkOnSelect: false, 
	       frozenColumns : [ [ //冻结列特性 ,不要与fitColumns 特性一起使用 
							{
								field : 'ck',
								width : 50,
								checkbox : true
								
							} ] ],
							columns : [ [
									{
										field : 'words',
										title : '同义词内容',
										width : 200,
										align : 'center',
										formatter:function(value , record , index){
											var arr = value.split(",");
											var temp ="";
											for(var i = 0;i < arr.length;i++){
											if(i != arr.length-1){	
												temp = temp +'<span class="bloc"><span class="fon">'+arr[i]+'</span><a title="点击删除" class="subx" onclick="deleteOne('+record.id+',\''+record.words+'\',\''+arr[i]+'\');"><sup>x</sup></a></span> &nbsp;&nbsp;';
											}
											else{
											temp = temp +'<span class="bloc"><span class="fon">'+arr[i]+'</span><a title="点击删除" class="subx" onclick="deleteOne('+record.id+',\''+record.words+'\',\''+arr[i]+'\');"><sup>x</sup></a></span>';
											}
											}
											return temp.substring(0, temp.length-2);
										}
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
									  

							] ],
							onClickRow: function (rowIndex, rowData) {
	                             $(this).datagrid('unselectRow', rowIndex);
	                         }, 
							pagination : true,
							pageSize : 10,
							pageList : [ 5, 10, 15, 20, 50 ],
							toolbar : [ {
								id:'authAdd',
								text : '新增同义词',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'add';
									$('#add').dialog({
										title : '新增同义词'
									});
// 									$('#addform').get(0).reset();
									$('#addform').form('clear');
									$('#add').dialog('open');

								}
							},{
								    id:'authUpdate',
									text:'修改同义词' ,
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
												title:'修改同义词'
											});
											$('#add').dialog('open'); //打开窗口
// 											$('#addform').get(0).reset();   //清空表单数据 
											$('#addform').form('clear');
											$('#addform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
												id:arr[0].id ,
												words:arr[0].words,
											});
										}
									
									}
								},{
									id:'authDelete',
									text:'删除同义词' ,
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
																$.post('deleteSameKeyword',
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
							{
								id:'authimp',
								text:'导入同义词' ,
								iconCls:'icon-add' , 
								handler:function(){
										document.getElementById('light').style.display='block';
										document.getElementById('fade').style.display='block';
									
							}

						}]
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
					url : flag=='add'?'addSameKeyword':'updateSameKeyword',
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
		$('#authimp').hide();
		var au = $("#auth").val();
		var listadd = $("#listadd").val().split(",");
		var listdelete = $("#listdelete").val().split(",");
		var listupdate = $("#listupdate").val().split(",");
		var listimp = $("#listimp").val().split(",");
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
			for(var j = 0;j<listimp.length;j++){
			if(end[i].trim() == listimp[j]){
				$('#authimp').show();
			}
			}
		}
		
		
	});
			
			function deleteOne(id,words,word){
				if(confirm("您确定要删除吗？")){
				var arr = words.split(",");
				var temp = "";
				var data = "";
				for(var i =0;i<arr.length;i++){
					if(arr[i] != word && arr[i] !=""){
					if(i != arr.length-1){
						temp = temp + arr[i]+",";
				  }else{
					temp = temp + arr[i];
					}
					}
				}
                var last = temp.charAt(temp.length-1);
                if(last ==","){
                	temp = temp.substring(0, temp.length-1);
                }
                data = "id=" + id +"&words=" + temp;
				commonAjaxId('updateSameKeywordId', data);
			}
			}	

			function commonAjaxId(url,para){
				 $.ajax({
					    type:"post",
						url : url, 
						data : para, 
						dataType : "json",
						success : function(list) {
							//1 刷新数据表格 
							$('#list').datagrid('reload');
							//2 清空idField   
							$('#list').datagrid('unselectAll');
							$.messager.show({
								title:'提示信息!' , 
								msg:'删除成功!'
							});
							},
						error:function(a,b,c){
							$.messager.show({
								title:'提示信息!' , 
								msg:'删除失败!'
							});
						}
						});
				
			}
			function closeDiv() {
				document.getElementById('light').style.display='none';
				document.getElementById('fade').style.display='none';
			}
	
			function importSame(){
				var v = document.getElementById("filename").value;
				if(v && v.length > 1){
					if(v.indexOf('.') > 0 && (v.indexOf('.txt') == v.length - 4)){
						document.forms["fileForm"].submit();
					}else{
						alert('请选择txt文本格式的数据');
					}
				}else{
				alert('请选择txt文本格式的数据');
				}
			}
			
			function t123(o) {
				var iframe = $(o).get(0);
				iframe.onload = iframe.onreadystatechange = function() {
					if(document.readyState && document.readyState != 'complete') {
					} else {
						var win = iframe.contentWindow;
						var frmobj = win.document.getElementsByTagName('html')[0]; 
						var rs = $(frmobj).find('pre').text();
						if(rs == "0"){
							alert("导入失败");
						}else{
							commonPopClose("fade","light");
							alert("导入成功");
						}
					}
				}
			}
			
			function commonPopClose(fade,content){
				document.getElementById(fade).style.display='none';
				document.getElementById(content).style.display='none';
			}
</script>
<style type="text/css">
.subx{
margin-top:-5px;
margin-right:-4px;
/* position:absolute; */
position relative;
margin-top:-10px;
z-index:110;
margin-left:1px;
}

.subx:hover{color: #EE00EE ;
	text-decoration:none;
   }
.fon{
   color:#9B30FF !important;
}
.bloc{
  background-color:#F0E68C;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  border: 1px solid #999;
	padding-bottom:2px;
	border-radius: 4px;
	display: inline-block;
	padding: 2px 9px;
	text-decoration: none
}

  .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%;  
             width: 40%;  
             height: 35%;  
            padding: 20px; 
            border: 5px solid #94B2F4; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
          .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
</style>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true" style="width:90%;height:78%;">
				<div region="north" title="同义词库查询" collapsible=false style="height:21%; padding-top :20px; padding-left: 5px;">
					<form id="search" method="post">
					<table>
					<tr>
					<td>&nbsp;&nbsp;关键词: &nbsp;&nbsp;</td>
					<td>
                    <input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>					
					<input type="hidden" id="listimp" name="listimp" value='${listimp}'>					
					<input class="easyui-textbox" id="words1" name="words1" style="width:180px;height:25px">&nbsp;&nbsp;
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
					<table id="list" width="53%"></table>
				</div>
			</div>
  			
 			<div id="add" title="新增商品标签" modal=true  draggable=false class="easyui-dialog" closed=true style="width:400px;height: 300px; padding-top: 10px; padding-left: 10px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<table>
	    					<tr>
	    						<td>&nbsp;&nbsp;关键词:&nbsp;&nbsp;</td>
	    						<td>
<!-- 	    					<input class="easyui-textbox" id="words" name="words" required=true validType="midLength[2,5]" missingMessage="用同义词内容必填!" invalidMessage="用户名必须在2到5个字符之间!" style="width:180px;height:25px"> -->
	    						<input class="easyui-textbox" id="words" name="words" required=true  data-options="multiline:true"  missingMessage="用同义词内容必填!"  style="width:260px;height:100px">
	    						
	    						</td>
	    					</tr>
							<tr><td></td><td></td></tr>
							<tr><td></td><td></td></tr>
	    					<tr align="center">
	    						<td colspan="2" align="center">
	    						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	    						 <a  id="confirm" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;height: 25px;">添加</a>
	    						 <a  id="close" class="easyui-linkbutton"  data-options="iconCls:'icon-cut'" style="width:80px;height: 25px;">关闭</a>
	    						</td>
	    					</tr>  
	    				</table>
	    		</form> 				
	    			   	<div style="color:#444444; font-size:13px;">
  		<pre>
  规则：
  	增加：同义词必输项，同义词之间用因为逗号（,）隔开
  		
  		</pre>
  	</div>	
 			</div>
 	
 	 <div id="light" class="white_content">
	 <img style="float: right; margin-top: -10px; margin-right: -10px;" src="${rc.contextPath}/images/off.png" class="right pointer" onclick="closeDiv();" title="点击关闭"/>
	 
	  <form id="fileForm" target="hiddenIframe" name="fileForm" action="importSameWord" method="post" enctype="multipart/form-data">
	  <div>
	 <div style="text-align: center; margin: 5px; padding: 5px;font-size: 20px;">同义词导入</div> 
	    <div style="width: 90%;border:1px solid #8A2BE2;height: 120px;">
	      <div style="margin-top: 10px; margin-left: 5px;">
          <input type="file" id="filename" name="filename"/> 
           <input id="importData" name="importData" type="button" value="提交" onclick="importSame();" style="width:80px;height: 25px;"/>
			<br/>
			<br/>
			<br/>
			</div>
	      </div>

	  </div>
	  </form>
	  	<iframe name="hiddenIframe" style="display:none;" id="hiddenIframe" onload="t123(this)">
			  		
			  		</iframe>
	  </div> 
	  
	  
        <div id="fade" class="black_overlay"></div> 		
 			
 			
  </body>
</html>