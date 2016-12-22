<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/css.jsp"%>
<%@ include file="/common/js.jsp"%>
<%@ include file="/common/auth.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据转换</title>
 
 <style type="text/css">
 
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
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
 </style>
<script type="text/javascript">
$(function(){
			$('#baseKeyword').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
					document.getElementById('light').style.display='block';
					document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addBaseKeyword',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
				
			});
			$('#keyword').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
					document.getElementById('light').style.display='block';
				    document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addBaseWord',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
				
			});
			$('#sameWord').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
				   document.getElementById('light').style.display='block';
				   document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addSameWord',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
			});
			$('#goodsTags').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
					document.getElementById('light').style.display='block';
					document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addgoodsTags',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
			});
			$('#goodssnTran').click(function() {
				$.messager.confirm('提示信息' , '确定转换?' , function(r){
					if(r){
					document.getElementById('light').style.display='block';
					document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'tranGoodsSn',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
			});
			
			$('#hotKeyword').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
					document.getElementById('light').style.display='block';
					document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addHotKeyword',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}					
				});
				
			});
			$('#tagInfo').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
				    document.getElementById('light').style.display='block';
				    document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addTagsInfo',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
			});
			$('#hotSearch').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
				   document.getElementById('light').style.display='block';
				   document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : 'addHotSearch',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
			});
			$('#reload').click(function() {
				$.messager.confirm('提示信息' , '点击确定?' , function(r){
					if(r){
				   document.getElementById('light').style.display='block';
				   document.getElementById('fade').style.display='block';
					$.ajax({
						type : 'post',
						url : '${rc.contextPath}/cateAttr/reloadCategory',
						cache : false,
						dataType : 'json',
						success : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行成功",
							});
						},
						error : function(result) {
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							$.messager.show({
								title :"提示信息",
								msg : "执行失败！请从新操作",
							});
						}
					});
					}
				});
				
			});
});
</script>
</head>

<body>
<div style="margin:20px 0 10px 0;"></div>
	<div class="easyui-tabs" style="width:900px;height:250px">
		<div title="总词库转换" style="padding:10px">
			<ul>
			   <a href="#" id="baseKeyword" name="baseKeyword" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			</ul>
		</div>
		<div title="自定义分词转换" style="padding:10px">
			<ul>
			   <a href="#" id="keyword" name="keyword" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			</ul>
		</div>
		<div title="商品标签库转换" style="padding:10px">
			 <a href="#" id="goodsTags" name="goodsTags" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			 	<br/>
			 		<br/>
			 			<br/>
			<a href="#" id="goodssnTran" name="goodssnTran" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">九位码转六位码</a>
		</div>
		<div title="热门关键字转换" style="padding:10px">
			<ul class="easyui-tree">
			<a href="#" id="hotKeyword" name="hotKeyword" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			</ul>
		</div>
		<div title="热门搜索词转换" style="padding:10px">
			<ul class="easyui-tree" >
			<a href="#" id="hotSearch" name="hotSearch" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			</ul>
		</div>
		<div title="同义词库转换" style="padding:10px">
			<ul class="easyui-tree">
			<a href="#"  id="sameWord" name="sameWord" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			</ul>
		</div>
		<div title="标签信息转换" style="padding:10px">
			<ul class="easyui-tree">
			<a href="#" id="tagInfo" name="tagInfo" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
		
			</ul>
		</div>
		<div title="商品分类属性管理" style="padding:10px">
			<ul class="easyui-tree">
			<a href="#" id="reload" name="reload" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:20%">点击</a>
			
			</ul>
		</div>
	</div>
	
	
	
	<!--         加载等待提示框 -->
        <div id="light" class="white_content">
        <div style="text-align: center; margin: 5px; padding: 5px;font-size: 20px;">程序正在执行，请耐心等候。。。。。。。。</div>
	    <div style="text-align:center; margin-left: auto; margin-right: auto;"><img alt="请稍候" src="${rc.contextPath}/images/wait.gif"> </div>
        </div>
	  
        <div id="fade" class="black_overlay"></div> 
  </body>
</html>



