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
<title>关键字转换</title>
<style type="text/css">
body {
 margin:auto;
}
ul {
 list-style: none;
}
#faq {
 font-size: 12px;
 width: 400px;
}
#faq li {
 margin:0;
 padding:0;
}
#faq dl {
 margin: 0;
 padding:0;
 display:inline;
}
#faq dt {
 font-weight:bold;
 cursor:pointer;
 line-height: 20px;
 padding: 0 0 5px 22px;
 border-bottom:1px #ccc dotted;
}
#faq dd {
 display:none;
 margin:0;
 padding: 5px 0 5px 20px;
 background:#E5ECF9;
}


</style>

<script type="text/javascript">
			$(function(){
				//日期时间组件
				$('#addTime,#lastUpdate').datetimebox({
					required:true , 
					missingMessage:'时间必填!' ,
					editable:false
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
			url:'queryConvertKeyword' ,
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
										field : 'oldword',
										title : '旧关键字',
										width : 100,
										align : 'center',
									},
									{
										field : 'combt',
										title : '待转换',
										width : 100,
										align : 'center',
									},
									{
										field : 'newword',
										title : '转换后',
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
								  id : 'authAdd',
								text : '新增转换关键字',
								iconCls : 'icon-add',
								handler : function() {
									flag = 'add';
									$('#add').dialog({
										title : '新增分词',
										
									});
// 									$('#addform').get(0).reset();
									$('#addform').form('clear');
									$('#add').dialog('open');
								}
							},{
								    id : 'authUpdate',
									text:'修改转换关键字' ,
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
												title:'修改转换关键字'
											});
											$('#add').dialog('open'); //打开窗口
// 											$('#addform').get(0).reset();   //清空表单数据 
											$('#addform').form('clear');
											var temp = arr[0].newword.split(";");
											var temp1 = document.getElementsByName("convert");
											for(var i = 0;i<temp.length;i++){
												for(var j = 0; j< temp1.length;j++){
													var tt = temp1[j].value+":"+ arr[0].combt;
													if(temp[i] == tt){
														temp1[j].checked = true;
													}
												}
											}
											$('#add').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
												id:arr[0].id,
												oldWord1:arr[0].oldword,
												newWord1:arr[0].newword,
												combt:arr[0].combt,
												addTime:new Date(arr[0].addTime).format("yyyy-MM-dd hh:mm:ss"),
												lastUpdate:new Date(arr[0].lastUpdate).format("yyyy-MM-dd hh:mm:ss"),
												
											      
											});
										}
									
									}
								},{
									 id : 'authDelete',
									text:'删除关键字转换' ,
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
																$.post('deleteConvertKeyword',
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
					url : flag=='add'?'addConvertKeyword':'updateConvertKeyword',
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
			
	
			var lastFaqClick=null;
			window.onload=function(){
			  var faq=document.getElementById("faq");
			  var dls=faq.getElementsByTagName("dl");
			  for (var i=0,dl;dl=dls[i];i++){
			    var dt=dl.getElementsByTagName("dt")[0];//取得标题
			     dt.id = "faq_dt_"+(Math.random()*100);
			     dt.onclick=function(){
			       var p=this.parentNode;//取得父节点
			        if (lastFaqClick!=null&&lastFaqClick.id!=this.id){
			          var dds=lastFaqClick.parentNode.getElementsByTagName("dd");
			          for (var i=0,dd;dd=dds[i];i++){
			            dd.style.display='none';
			          }
			        }
			        lastFaqClick=this;
			        var dds=p.getElementsByTagName("dd");//取得对应子节点，也就是说明部分
			        var tmpDisplay='none';
			        if (gs(dds[0],'display')=='none'){
			          tmpDisplay='block';
			        }
			        for (var i=0;i<dds.length;i++){
			          dds[i].style.display=tmpDisplay;
			        }
			      }
			  }
			}

			function gs(d,a){
			  if (d.currentStyle){
			    var curVal=d.currentStyle[a]
			  }else{
			    var curVal=document.defaultView.getComputedStyle(d, null)[a]
			  }
			  return curVal;
			}
			
			function conon(convert){
				var combt = $("#combt").val();
				var newWord1 = $("#newWord1").val();
				var temp="";
				var total = document.getElementsByName("convert");
				for(var i = 0;i<total.length;i++){
					if(total[i].checked == true){
						if(total[i].value == '4'){
					     temp = temp+";" + combt;
						}
						else{
						temp = temp+";" + total[i].value+ ":" + combt;
						}
					}
				}
				var rs = temp.substring(1,temp.length);
				$("#newWord1").textbox("setValue",rs);
				
			}
</script>
</head>

<body>
<body>
	<div id="lay" class="easyui-layout" fit="true" style="width:90%;height:70%;">
				<div region="north" title="关键字转换查询" collapsible=false style="height:21%; padding-top :20px; padding-left: 5px;" >
					<form id="search" method="post">
					<table>
					<tr>
					<td>&nbsp;&nbsp;旧关键字: &nbsp;&nbsp;</td>
					<td>
					<input type="hidden" id="auth" name="auth" value='<sec:authentication property="principal.authorities" />'>
					<input type="hidden" id="listadd" name="listadd" value='${listadd}'>
					<input type="hidden" id="listdelete" name="listdelete" value='${listdelete}'>
					<input type="hidden" id="listupdate" name="listupdate" value='${listupdate}'>
					
					<input class="easyui-textbox" id="oldWord" name="oldWord" style="width:180px;height:25px">&nbsp;&nbsp;
					</td>
					<td>转换后关键字:&nbsp;&nbsp;</td>
					<td>
					<input class="easyui-textbox" id="newWord" name="newWord" style="width:180px;height:25px">&nbsp;&nbsp;
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
  			
  				<div>
				  <ul id="faq">
				  <li>
				    <dl>
				      <dt>总词库说明</dt>
				      <dd>
		    1，用户输入的最原始的关键字信息在《关键字统计信息》的<关键字基本统计>中查看，总词库中存放的 是经过处理（汉字简单去重复、特殊字符过滤）的。<br/>
			2，每天凌晨统计前一天的关键字信息，将关键字从《关键字统计信息》的<关键字基本统计>中copy到总词库， 更新对应关键字搜索次数和结果数。<br/>
			3，由于是统计前一天，因此总词库的“添加时间”表示关键字进入总词库的时间，并不是用户搜索词关键字的 时间，真正的搜索发生的时间是“添加时间”的前一天。
				      
					</dd>
				    </dl>
				  </li>
				 </ul>
				</div>
  			
 			<div id="add" title="新增转换关键字" modal=true  draggable=false class="easyui-dialog" closed=true style="width:450px;height: 350px; padding-top: 10px; padding-left: 10px;">
	    		<form id="addform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<table style=" padding: 0 0 5px 45px;">
	    					<tr>
	    						<td>&nbsp;&nbsp;旧关键字:&nbsp;&nbsp;</td>
	    						<td><input class="easyui-textbox" id="oldWord1" name="oldWord1" required=true  missingMessage="旧关键字必填!" style="width:180px;height:25px"></td>
	    					</tr>
	    					
	    					<tr><td></td><td></td></tr>
	    					<tr><td></td><td></td></tr>
	    					<tr>
	    						<td>&nbsp;&nbsp;待转换:&nbsp;&nbsp;</td>
	    						<td><input class="easyui-textbox" id="combt" name="combt" required=true  missingMessage="待转换字必填!" style="width:180px;height:25px"></td>
	    					</tr>
	    					
	    					<tr><td></td><td></td></tr>
	    					<tr><td></td><td></td></tr>
	    						<tr>
	    						<td>&nbsp;&nbsp;转换后:&nbsp;&nbsp;</td>
	    						<td><input class="easyui-textbox" id="newWord1" name="newWord1" readonly="readonly" style="width:180px;height:25px"></td>
	    					</tr>
	    					<tr><td></td><td></td></tr>
	    					<tr><td></td><td></td></tr>
	    						<tr>
	    						<td>&nbsp;&nbsp;选择:&nbsp;&nbsp;</td>
	    						<td style="margin:2px 0">
	    						<input type="checkbox" id="convert" name="convert" value="$BRAND" onclick="conon(this)"><span>品牌</span>
						    	<input type="checkbox" id="convert" name="convert" value="$COLOR" onclick="conon(this)"><span>色系</span>
								<input type="checkbox" id="convert" name="convert" value="$SHOP" onclick="conon(this)"><span>店铺</span>
								<input type="checkbox" id="convert" name="convert" value="$CID" onclick="conon(this)"><span>分类</span>
								<input type="checkbox" id="convert" name="convert" value="4" onclick="conon(this)"><span>默认</span>
	    						</td>
	    					</tr>
							<tr><td></td><td></td></tr>
							<tr><td></td><td></td></tr>
	    					<tr align="center">
	    						<td colspan="2" align="center">
	    						 <a  id="confirm" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px;height: 25px;">添加</a>
	    						 <a  id="close" class="easyui-linkbutton"  data-options="iconCls:'icon-cut'" style="width:80px;height: 25px;">关闭</a>
	    						</td>
	    					</tr>  
	    					
	    					
	    					
	    				</table>
	    		</form> 
	    		<div style="color:#444444; font-size:13px;">
  		<pre>
  规则：
  	增加：旧关健字、待转换字必须输，转换后文本框禁止输入。
  	         转换后的结果会根据多选框的选择自动生成  
  		
  		</pre>
  	</div>
	    			
    		
 			</div>
  </body>
</html>