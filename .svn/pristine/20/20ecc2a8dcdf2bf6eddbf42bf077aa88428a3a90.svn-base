<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/css.jsp"%>
<%@ include file="/common/js.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>邦购搜索引擎管理后台</title>
<script type="text/javascript">
var menusList ='';
var _menus = '';

$(document).ready(function () {
	 $.ajax({
		    type:"post",
			url : '${rc.contextPath}/menu/loadLeft', 
			success : function(list) {
				var flag = false;
				menusList = '{"menus":[';
				for(var i = 0 ;i<list.length;i++){
					if(list[i]["parentId"] == 0){
				menusList += '{"menuid":"'+list[i]["id"]+'","icon":"icon-sys","menuname":"'+list[i]["name"]+'",';
			    menusList += '"menus":[';
				     for(var j = 0 ;j<list.length;j++){
				    	 if(list[j]["parentId"] == list[i]["id"]){
				            flase = true;
				             menusList += '{"menuid":"'+list[j]["id"]+'","menuname":"'+list[j]["name"]+'","icon":"icon-log","url":"${rc.contextPath}'+list[j]["url"]+'"},'; 
				    	 }
				      }
				     if(flag){
			       menusList = menusList.substring(0,menusList.length-1);     
			       flag = false;
				     }
				menusList += '],';
				menusList = menusList.substring(0,menusList.length-1);
				menusList += '},';
					}
				}
				menusList = menusList.substring(0,menusList.length-1);
				menusList += ']}';
				_menus = eval('(' + menusList + ')');
	            InitLeftMenu();
				tabClose();
				tabCloseEven();
				},
			error:function(a,b,c){ 
				alert("加载菜单出错！");
			}
	    });
});

function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			});
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			});
		}
	});
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	});
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	});
}

$(function() {
	
	 
	tabCloseEven();

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});

	  $('#loginOut').click(function() {
	        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

	            if (r) {
	                location.href = '${rc.contextPath}/j_spring_security_logout';
	            }
	        });
	    });
});


//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});

    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
        $.each(n.menus, function(j, o) {
        	if(o.url == '/MBgoSearchManagerSrv/manager/goodsTagsManager'){
        		menulist += '<li><div><a class="cs-navi-tab"  target="_blank" ref="'+o.menuid+'" href="' + o.url + '" rel="" class="cs-navi-tab"><span class="icon icon-add" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';	
        	}else{
        	menulist += '<li><div><a class="cs-navi-tab" ref="'+o.menuid+'" href="#" rel="' + o.url + '" class="cs-navi-tab"><span class="icon icon-add" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        	}
        });
		menulist += '</ul>';

		$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid,icon);

		addTab(tabTitle,url,icon);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}
</script>
<style type="text/css">

.easyui-accordion ul{list-style-type:none;margin:0px; padding:10px;}
.easyui-accordion ul li{ padding:0px;}
.easyui-accordion ul li a{line-height:24px;}
.easyui-accordion ul li div{margin:2px 0px;padding-left:10px;padding-top:2px;}
.easyui-accordion ul li div.hover{border:1px dashed #99BBE8; background:#E0ECFF;cursor:pointer;}
.easyui-accordion ul li div.hover a{color:#416AA3;}
.easyui-accordion ul li div.selected{border:1px solid #99BBE8; background:#E0ECFF;cursor:default;}
.easyui-accordion ul li div.selected a{color:#416AA3; font-weight:bold;}
.easyui-accordion>div li a span.icon{width:18px; height:18px; float:left;} 
.easyui-accordion>div li:hover a{text-decoration:none;}
.easyui-accordion>div li a span + span{margin-left:3px;}
.easyui-accordion>div .nav { margin-bottom: auto;margin-top: auto;}
span{margin-top: 4px;}
.icon{ background:url(${rc.contextPath}/images/tabicons.png) no-repeat;width:18px; line-height:18px; display:inline-block;}
.icon-sys{ background-position:0px -200px;}
.icon-set{ background-position:-380px -200px;}
.icon-add{background-position: -20px 0px;}
.icon-add1{background:url('icon/edit_add.png') no-repeat;}
.icon-nav{background-position: -100px -20px; }
.icon-users{background-position: -100px -480px;}
.icon-role{background-position: -360px -200px;}
.icon-set{background-position: -380px -200px;}
.icon-log{background-position: -380px -80px;}
.icon-delete16{background:url('icon/delete.gif') no-repeat;width:18px; line-height:18px; display:inline-block;}
.icon-delete{ background-position:-140px -120px;}
.icon-edit{ background-position:-380px -320px;}
.icon-magic{ background-position:0px -500px;}
.icon-database{ background-position:-20px -140px;}
.icon-expand{ background:url('${rc.contextPath}/images/coll2.gif') no-repeat;}
.icon-collapse{ background:url('${rc.contextPath}/images/coll3.gif') no-repeat;}
</style>

</head>
<body class="easyui-layout">
	<div region="north" border="true" class="cs-north" style="height:11%">
		<div class="cs-north-bg">
		<div class="cs-north-logo">邦购搜索引擎管理后台</div>
		<span style="float:right; padding-top:10px; padding-right:20px; font-size: 15px;">欢迎&nbsp;<span id="uname" style="color: blue; font-size:20px;"><sec:authentication property="name"/>&nbsp;</span>用户 &nbsp;&nbsp;  <a href="#" id="loginOut">安全退出</a></span>
	</div>
	</div>
		
	<div region="west" border="true" split="true" title="导航菜单" class="cs-west">
     <div id="nav"  class="easyui-accordion" fit="true" border="false">
<!-- 				<div title="搜索词库管理"> -->
<!-- 				<ul> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/baseKetword/toBaseKetword" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">总词库管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/keyword/toCusword" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">自定义分词管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/hotKeyword/toHotKeyword" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">热门关键字管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/hotSearchKeyword/toHotSearchKeyword" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">热门搜索词管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/sameKeyword/toSameKeyword" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">同义词库管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 </ul> -->
<!-- 				</div> -->
			
<!-- 			<div title="商品信息管理"> -->
<!-- 				<ul> -->
<!-- 			 <li> -->
<%-- 				<div><a href="${rc.contextPath}/manager/goodsTagsManager"  target="_blank" src=""  class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">商品&标签管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			  <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/goodsTags/toGoodsTags" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">商品标签库</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/tagsInfo/toTagsInfo" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">标签库信息管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 </ul> -->
<!-- 				</div>	 -->
<!-- 			<div title="关键字管理"> -->
<!-- 				<ul> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/convertKeyword/toConvertKeyword"  class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">关键字转换设置</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 </ul> -->
<!-- 				</div>	 -->
<!-- 			<div title="数据转换"> -->
<!-- 				<ul> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/dataTransform/toDataTransform" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">表数据转换</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 </ul> -->
<!-- 				</div>	 -->
<!-- 				<div title="系统管理"> -->
<!-- 					<ul> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/user/toUser" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">用户管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/role/toRole" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">角色管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/menu/toMenu" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">菜单管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			  <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/userRole/toUserRole" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">用户角色管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			  <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/roleMenu/toRoleMenu" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">角色菜单管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			  <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/log/toLog" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">日志管理</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			  <li> -->
<%-- 				<div><a href="javascript:void(0);" src="${rc.contextPath}/searchErrorLog/toSearchLog" class="cs-navi-tab"><span class="icon icon-add"></span><span class="nav">搜索错误日志</span></a></div> --%>
<!-- 			 </li> -->
<!-- 			 </ul> -->
<!-- 				</div> -->
		</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" style="height: 100%">
                <div title="邦购后台">
				<div class="cs-home-remark">
					<h2>欢迎来到邦购搜索引擎管理后台</h2> <br>
				</div>
				</div>
        </div>
	</div>

	<div region="south" border="false" id="south" style="overflow: hidden; height: 40px; background: #D2E0F2; ">
	<div class="footer">版权所有：<a href="http://www.banggo.com">美特斯.邦威</a></div>
	</div>
	
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>
