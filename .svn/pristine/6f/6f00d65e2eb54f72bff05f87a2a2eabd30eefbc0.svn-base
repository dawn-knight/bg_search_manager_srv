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
<title>商品分类属性管理</title>
<style type="text/css">
div.pageTitle {
    padding-top:5px;
	padding : 10px;
}
div.pageTitle .mainTitle {
	font-size : 20px;
	font-weight : bold;
}
div.pageTitle .titleInfo {
	font-size : 13px;
	color : gray;
}
div.pageTitle hr {
	border : none;
	border-bottom : 1px solid gray;
	width : 80%;
	margin-left : 0px;
}
#table{
		margin : auto 30px;
		border-collapse:collapse;
		
		border-radius : 4px;
	}
	#table td {
		border : solid 2px #7194E7;
	}
	#leftDiv,#rightDiv{
		width: 400px;
		height: 600px;
		overflow:auto;
		font-size:13px;
		text-align:left;
	}
	li{
		list-style: none;
		line-height:18px;
	}
	.cateName {
		cursor:pointer;
		text-decoration:underline;
		color:blue;
	}
	.cateName:hover {
		color: red;
	}
	.couldClick{
		text-decoration:underline;
		cursor:pointer;
	}
	.showAttrsSpan{
		color:black;
		text-decoration:none;
/* 		display : none; */
	}
	#rightDiv{
		padding-left:10px;
	}
	.titlep{
		font-weight:bold;
	}
	span[name="upSpan"]{
		color:green;
		font-weight:bold;
		margin-left:20px;
	}
	span[name="downSpan"]{
		font-weight:bold;
		color:blue;
		margin-left:10px;
	}
	span[name="deleteSpan"]{
		font-weight:bold;
		color:red;
		margin-left:10px;
	}
	span[name="addSpan"]{
		font-weight:bold;
		color: green;
		margin-left:20px;
	}
  .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%;  
             width: 55%;  
             height: 60%;  
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
<script type="text/javascript">
//全局变量
var cateDivID = 'cateDiv_';
var $attrInfoSelect;
$(function(){
	showSubCate(document.getElementById('allCate'));
	loadAttrInfoSelect();
	
});

function showSubCate(obj_this, cid) {
	var obj = $(obj_this).parent('li');
	cid = cid || 0;
	var subDivID = cateDivID + cid;
	var subDiv = $('#' + subDivID);
	if(subDiv.length > 0) {
		showHidden(subDiv);
	}else{
		$.ajax({
			type:"post",
			url : 'parentCates',
			data:"cid="+cid,
			datatype:'json',
			success : function(rs) {
				var h = '';
				if(rs) {
					var list = rs;
					h += '<div id="'+subDivID+'" style="display:none;">';
					h += '<ul>';
					for(var i = 0; i < list.length; i ++) {
						var cate = list[i];
						var cid = cate.id;
						var name = cate.siteCateName;
						h += '<li class="cateInfoLi">';
						$.ajax({
							type:"post",
							url : 'searchAttr',
							data:"cid="+cid,
							 async: false,
							datatype:'json',
							success : function(rs) {
								if(rs == "1"){
									h += '<span id="cateInfoLi'+cid+'" class="cateName" onmouseover="showAttrSpan(this);" onmouseout="hiddenAttrSpan(this);"   onclick="showSubCate(this, '+cid+')">'+name+'('+cid+')</span>&nbsp;&nbsp;&nbsp;';
									h += '<span name="showAttrs" class="showAttrsSpan" style="cursor: pointer;" onclick="showAttrs('+cid+ ',\''+name+'\')">'+'查看属性'+'</span>';	
								}else{
									h += '<span id="cateInfoLi'+cid+'" class="noSubName" >'+name+'('+cid+')</span>&nbsp;&nbsp;&nbsp;';
									h += '<span name="showAttrs" class="showAttrsSpan" style="cursor: pointer;" onclick="showAttrs('+cid+ ',\''+name+'\')">'+'查看属性'+'</span>';
								}
								h += '</li>';
							},
							error:function(){
								result = 0;
							}
						});
							
						
					}
					h += '</ul>';
					h += '</div>';
					obj.after(h);
					showHidden($('#' + subDivID));
				}
			}
		});
	}
}


function showHidden(obj) {
	if(obj.data('v') == null || obj.data('v') == 0) {
		obj.slideDown(300);
		obj.data('v', 1);
	}else{
		obj.slideUp(300, function(){
			hiddenSubs(obj);
		});
		obj.data('v', 0);
	}
}

function hiddenSubs(obj) {
	if($('#isSubIn').attr('checked')){
		var o = obj.find('div[id*="cateDiv_"]');
		o.css('display', 'none');
		o.data('v', 0);
	}
}

//显示分类下属性
function showAttrs(cid, name){
	url = 'cateAttrs';
	data = {'cid':cid};
	var $rightDiv = $('#rightDiv');
	$rightDiv.html('<div style="padding-top:5px;">您正在查看 <font style="color:red;">'+name+'</font> 的属性</div>');
	$rightDiv.attr('cid',cid);
	$.ajax({
		type:'post',
		data:data,
		url:url,
		datatype:'json',
		success : function(rs) {
			//被显示的属性部分
			var $divShown = $('<div></div>').attr('name','divShown');
			$rightDiv.append($divShown);
			$('<p class="titlep">显示属性：</p>').appendTo($divShown);
			var $ul = $('<ul></ul>').appendTo($divShown);
			 var list = rs;
			for(var i=0; i < list.length; i++){
				var attr = list[i];
				if(attr["isLimit"] == 1){
				var $li = buildShowLi(attr.propertyCode,attr.propertyName).appendTo($ul);
				}
			}
			$('<br />').appendTo($rightDiv);
			//不被显示属性部分
			var $divNotShown = $('<div></div>').attr('name','divNotShown');
			$rightDiv.append($divNotShown);
			$('<p class="titlep">不显示属性:</p>').appendTo($divNotShown);
			var $ul = $('<ul></ul>').appendTo($divNotShown);
			 var list = rs;
			for(var i=0; i < list.length; i++){
				var attr = list[i];
				if(attr["isLimit"] == 2){
				var $li = buildNotShowLi(attr.propertyCode,attr.propertyName).appendTo($ul);
				}
			}
			$('<br />').appendTo($rightDiv);
			//从数据库添加新属性区域
			var $divAddAttrInfo = $('<div></div>').attr('name','divAddAttrInfo');
			$rightDiv.append($divAddAttrInfo);
			$('<spap class="titlep">新增属性:</span>').appendTo($divAddAttrInfo);
			$('<input type="text" size="8" onkeyup="getSimi(this);" />').appendTo($divAddAttrInfo);
			var $select = $attrInfoSelect.clone().appendTo($divAddAttrInfo);
			$('<input type="button" clsss="btnStyle1" value="新增"/>').appendTo($divAddAttrInfo)
												.click(function(){
													var option = $select.find('option:selected');
													addAttrInfo(cid,option.attr('attrCode'),option.attr('attrName'));
												});
			$('<br />').appendTo($rightDiv);
											
		}
	
	});
}
//创建一个代表被显示属性的li元素
function buildShowLi(attrCode,attrName){
	var $li = $('<li></li>');
	var $attrSpan = $('<span id="'+attrCode+'"></span>')
				.attr('attrCode',attrCode)
				.attr('attrName',attrName)
				.text(attrName +'('+attrCode+')')
				.appendTo($li);
	 var $upSpan = $('<span onmouseover="addClass(this);" onmouseout="removeClass(this);" onclick="liftSort(this,1)"></span>').attr('name','upSpan').attr('attrCode',attrCode).text('↑').appendTo($li);
	 var $downSpan = $('<span onmouseover="addClass(this);" onmouseout="removeClass(this);" onclick="liftSort(this,-1)"></span>').attr('name','downSpan').attr('attrCode',attrCode).text('↓').appendTo($li);
	 var $deleteSpan = $('<span onmouseover="addClass(this);" onmouseout="removeClass(this);" onclick="deleteSort(this);"></span>').attr('name','deleteSpan').attr('attrCode',attrCode).text('×').appendTo($li);
	 return $li;
}
//创建一个代表不被显示属性的li元素
function buildNotShowLi(attrCode,attrName){
	var $li = $('<li></li>');
	var $attrSpan = $('<span></span>')
					.attr('attrCode',attrCode)
					.attr('attrName',attrName)
					.text(attrName+'('+attrCode+')')
					.appendTo($li);
	var $addSpan = $('<span onmouseover="addClass(this);" onmouseout="removeClass(this);"  onclick="updateLimitShow(this);"></span>').attr('name','addSpan').attr('attrCode',attrCode).text('＋').appendTo($li);
	var $deleteSpan = $('<span onmouseover="addClass(this);" onmouseout="removeClass(this);" onclick="deleteAttr(this);"></span>').attr('name','removeSpan').attr('attrCode',attrCode).text('×').appendTo($li);
	return $li;
}



//加载新增属性下拉框数据
function loadAttrInfoSelect(){
	jQuery.get('getAllAttr',function(rs){
		$attrInfoSelect = $('<select id="selectedObj"><select>');
		var list = eval(rs);
		$('<option attrCode="">请选择</option>').appendTo($attrInfoSelect);
		for(var i = 0; i < list.length; i++){
			var optionKey = list[i].propertyCode;
			var optionValue = list[i].propertyName;
			$('<option></option>').text(optionValue+'('+optionKey+')').attr('attrCode',optionKey).attr('attrName',optionValue).appendTo($attrInfoSelect);
		}
	});
}

function getSimi(oThis) {
	var obj = $(oThis);
	var v = obj.val().replace(/\s/gi, '');
	if(v && v.length > 0) {
		var slct = $('#selectedObj');
		slct.find('option').each(function(){
			if($(this).val().indexOf(v) > -1) {
				$(this).attr('selected', 'selected');
				return false;
			}
		});
		
	}
	
}



//新增属性
function addAttrInfo(cid,attrCode,attrName){
	var url = 'addAttrInfo';
	var data={'cid':cid,'attrCode':attrCode,'attrName':attrName};
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		data : data,
		dataType : 'json',
		success : function(result) {
			if(result[0] == "success"){
			var $ul = $('div[name=divShown]').children('ul');
			var $li = buildShowLi(attrCode,attrName).appendTo($ul);
			}
		},
		error : function(result) {
			alert("执行出错，请从新执行！");
		}
	});
}

function addClass(para){
	$(para).addClass('couldClick');
}
function removeClass(para){
	$(para).removeClass('couldClick');
}
	//修改属性(使其不可显示)
function deleteSort(span){
	  var  cid = $('#rightDiv').attr('cid');
	  var $span = $(span);
		var $oldLi = $span.parent('li');
		var attrName = $oldLi.children(':first').attr('attrName');
		var attrCode = $span.attr('attrCode');
		 var url="updateLimit";
		data={'cid':cid,'attrCode':attrCode,'attrName':attrName};
		$.ajax({
			type : 'post',
			url : url,
			cache : false,
			data : data,
			dataType : 'json',
			success : function(result) {
				var $ul = $('div[name=divNotShown]').children('ul');
				var $li = buildNotShowLi(attrCode,attrName).prependTo($ul);
				$oldLi.remove();
			},
			error : function(result) {
				alert("执行出错，请从新执行！");
			}
		});
}
function updateLimitShow(span){
	  var  cid = $('#rightDiv').attr('cid');
	  var $span = $(span);
		var $oldLi = $span.parent('li');
		var attrName = $oldLi.children(':first').attr('attrName');
		var attrCode = $span.attr('attrCode');
		url="updateLimitShow";
		data={'cid':cid,'attrCode':attrCode,'attrName':attrName};
		$.ajax({
			type : 'post',
			url : url,
			cache : false,
			data : data,
			dataType : 'json',
			success : function(result) {
				var $ul = $('div[name=divShown]').children('ul');
				var $li = buildShowLi(attrCode,attrName).appendTo($ul);
				$oldLi.remove();
			},
			error : function(result) {
				alert("执行出错，请从新执行！");
			}
		});
}
	
	
//添加属性(彻底删除)
function deleteAttr(span){
	var  cid = $('#rightDiv').attr('cid');
	var $span = $(span);
	var $oldLi = $span.parent('li');
	var attrName = $oldLi.children(':first').attr('attrName');
	var attrCode = $span.attr('attrCode');
	url="deleteAttr";
	data={'cid':cid,'attrCode':attrCode,'attrName':attrName};
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		data : data,
		dataType : 'json',
		success : function(result) {
			$oldLi.remove();
		},
		error : function(result) {
			alert("执行出错，请从新执行！");
		}
	});
}

//offset目前只支持1和-1
function liftSort(span,offset){
	var  cid = $('#rightDiv').attr('cid');
	var $span = $(span);
	var $li = $span.parent('li');
	var url = 'liftSort';
	var attrCode =  $span.attr('attrCode');
	var prev = $li.prev().children();
	if(prev.length > 0){
		prevCode = prev[0].id;
	}else{
		prevCode = attrCode;
	}
	var next = $li.next().children();
	if(next.length > 0){
		nextCode = next[0].id;
	}else{
		nextCode = attrCode;
	}
	
	var data={'cid':cid,'attrCode':attrCode,'offset':offset,'prevCode':prevCode,'nextCode':nextCode};
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		data : data,
		dataType : 'json',
		success : function(result) {
			//上移 待移动li要和前面li换位
			if(1 == offset){
				//[0].attr("attrcode")
				var $prevLi = $li.prev();
				
				if($prevLi.size() > 0){
					//交换prevLi和$li位置
					$li.insertBefore($prevLi);
				}
			}else if(-1 == offset){
				var $nextLi = $li.next();
				if($nextLi.size() > 0){
					$li.insertAfter($nextLi);
				}
			}
		},
		error : function(result) {
			alert("执行出错，请从新执行！");
		}
	});
}
function showAttrSpan(span){
// 	$(span).next().fadeIn(0);
}
function hiddenAttrSpan(span){
// 	$(span).next().fadeOut(0);
}
function reloadCateinfoFromMysql() {
	var url = 'reloadInterfaceCategory';
	var fade ="fadewait";
	var content="waitDiv";
	commonPopShow(fade,content);
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		dataType : 'json',
		success : function(result) {
			commonPopClose(fade,content);
			alert(result);
		},
		error : function(result) {
			commonPopClose(fade,content);
			alert(result);
		}
	});
}
function commonPopShow(fade,content){
	document.getElementById(fade).style.display='block';
	document.getElementById(content).style.display='block';
}
function commonPopClose(fade,content){
	document.getElementById(fade).style.display='none';
	document.getElementById(content).style.display='none';
}
function jump(){
	var cid = $("#jump").val();
	var sId = "#cateInfoLi"+cid;
// // 	showAttrs(id);
	var obj = $(sId).parent('li');
	var subDivID = cateDivID + cid;
	var subDiv = $('#' + subDivID);
	queryParent(cid,obj,subDivID,subDiv);
	
}

var num = new Array();
function queryParent(cid,obj,subDivID,subDiv){
	$.ajax({
		type:"post",
		cache: false,
		url : 'queryByPk',
		data:"id="+cid,
		datatype:'json',
		success : function(rs) {
			var list = rs;
			for(var i = list.length-1;i>=0;i--){
				var cid1 = list[i]["parentId"];
				var sId1 = "#cateInfoLi"+cid1;
				var obj1 = $(sId1).parent('li');
				var subDivID1 = cateDivID + cid1;
				var subDiv1 = $('#' + subDivID1);
				num[i] = subDivID;
				expand(cid1,obj1,subDivID1,subDiv1);
			}
			var sId2 = "#cateInfoLi"+cid;
			var obj2 = $(sId2).parent('li');
			var subDivID2 = cateDivID + cid;
			var subDiv2 = $('#' + subDivID2);
			num[list.length] = subDivID;
			expand(cid,obj2,subDivID2,subDiv2);
		},
		error:function(){
			alert("加载出错");
		}
	});
}
function expand(cid,obj,subDivID,subDiv){
// 	for(var j=0;j<num.length;j++){
// 		if(typeof(num[j]) != "undefined"){
// 		$('#'+num[j]).attr({style:"display:none"});
// 		}
// 	}
	if(subDiv.length > 0) {
		showHiddenD(subDiv);
	}else{
		$.ajax({
			type:"post",
			url : 'parentCates',
			data:"cid="+cid,
			async: false,
			datatype:'json',
			success : function(rs) {
				var h = '';
				if(rs) {
					var list = rs;
					h += '<div id="'+subDivID+'" style="">';
					h += '<ul>';
					for(var i = 0; i < list.length; i ++) {
						var cate = list[i];
						var cid = cate.id;
						var name = cate.siteCateName;
						h += '<li class="cateInfoLi">';
						$.ajax({
							type:"post",
							url : 'searchAttr',
							data:"cid="+cid,
							 async: false,
							datatype:'json',
							success : function(rs) {
								if(rs == "1"){
									h += '<span id="cateInfoLi'+cid+'" class="cateName" onmouseover="showAttrSpan(this);" onmouseout="hiddenAttrSpan(this);"   onclick="showSubCate(this, '+cid+')">'+name+'('+cid+')</span>&nbsp;&nbsp;&nbsp;';
									h += '<span name="showAttrs" class="showAttrsSpan" style="cursor: pointer;" onclick="showAttrs('+cid+ ',\''+name+'\')">'+'查看属性'+'</span>';	
								}else{
									h += '<span id="cateInfoLi'+cid+'" class="noSubName" >'+name+'('+cid+')</span>&nbsp;&nbsp;&nbsp;';
									h += '<span name="showAttrs" class="showAttrsSpan" style="cursor: pointer;" onclick="showAttrs('+cid+ ',\''+name+'\')">'+'查看属性'+'</span>';
								}
								h += '</li>';
							},
							error:function(){
								result = 0;
							}
						});
							
						
					}
					h += '</ul>';
					h += '</div>';
					obj.after(h);
					showHidden($('#' + subDivID));
				}
			}
		});
	}
}

function showHiddenD(obj) {
	if(obj.data('v') == null || obj.data('v') == 0) {
		obj.slideDown(300);
		obj.data('v', 1);
	}
}
</script>
</head>

<body>

<div class="pageTitle">
		<span class="mainTitle">排序权重管理</span>
		<span class="titleInfo"></span>	
		<input onclick="reloadCateinfoFromMysql();" style=" " class="btnStyle1" type="button" value="加载分类信息" />
		序号：<input type="text" id="jump" name="jump" onblur="jump();"/>
		<hr />
	</div>
  	<table id="table">
  		<tr>
  			<td>
  				<div id="leftDiv">
					<ul>
						<li><span id="allCate" class="cateName" onmouseover="showAttrSpan(this);" onmouseout="hiddenAttrSpan(this);" style="font-weight:bold;">全部分类</span>
						<span name="showAttrs" class="showAttrsSpan" style="cursor: pointer;" onclick="showAttrs('0','全部分类')">查看属性</span>
<!-- 							<span style="margin-left:45px;" class="btnStyle1"> -->
<!-- 								<input id="isOnlyOne" type="checkbox" checked="checked" /><label for="isOnlyOne">单个展开</label> -->
<!-- 							</span> -->
<!-- 							<span style="margin-left:10px;" class="btnStyle1"> -->
<!-- 								<input id="isSubIn" type="checkbox" /><label for="isSubIn">同时收起子分类</label> -->
<!-- 							</span> -->
							<div style="height:6px;">&nbsp;</div>
						</li>
					</ul>
  				</div>
  			</td>
  			
  			<td>
  				<div id="rightDiv">
  				
  				</div>
  				
  				<div id="addAttrInfo">
  				</div>
  			</td>
  		</tr>
  	</table>
  	<!--         加载等待提示框 -->
        <div id="waitDiv" class="white_content">
        <div style="text-align: center; margin: 5px; padding: 5px;font-size: 20px;">正在从数据库更新基础分类信息，请稍后...</div>
	    <div style="text-align:center; margin-left: auto; margin-right: auto;"><img alt="请稍候" src="${rc.contextPath}/images/wait.gif"> </div>
        </div>
        <div id="fadewait" class="black_overlay"></div> 
  </body>
</html>



