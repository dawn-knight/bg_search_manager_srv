<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/css.jsp"%>
<%@ include file="/common/js.jsp"%>
<%@ include file="/common/tag.jsp"%>
<%@ include file="/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>商品标签管理</title>
<script type="text/javascript">
//公共的显示弹出框
function commonPopShow(fade,content){
	document.getElementById(fade).style.display='block';
	document.getElementById(content).style.display='block';
}
function commonPopClose(fade,content){
	document.getElementById(fade).style.display='none';
	document.getElementById(content).style.display='none';
}

function divBatch(tagContent,fade,light){
    $("#batchTag").val('');	
    $("#allTagBatch").html("");	
	var temp ="#"+tagContent;
	var isContent = $(temp).html();
	if(isContent == ""){
		alert("没有商品信息！！！");
	}else{
		if(tagContent=="tabInfoContentTag"){
			var btn ='<input type="button" value="确定" onclick="addBatchProductTag();">';
			$("#btn").html("");
			$("#btn").html(btn);
			commonPopShow(fade,light);
		}else{
		    var btn ='<input type="button"  value="确定" onclick="addBatchProduct();">';
			$("#btn").html("");
			$("#btn").html(btn);
			commonPopShow(fade,light);
		}
	}
}


function hiddenDiv() {  
var objDiv = document.getElementById("float-div");     
objDiv.style.display = "none";
}

function batchTagQuery(){
	var para = $("#batchTag").val();
	$.ajax({
		type : 'post',
		url : 'queryTagsToSelect',
		cache : false,
		data : "para="+para,
		dataType : 'json',
		success : function(result) {
			$("#batchDiv").html("");
			var list = result;
			var tempAppend='';
			if(list.length>0){
				for(var i = 0;i<4;i++){
					var word = list[i]["tagWord"];
				tempAppend = tempAppend + '<div>'+
				'<nobr><a href="javascript:getBatchTag(\''+word+'\')">'+word+'</a></nobr>'+
			    '</div>';
				}
			$(tempAppend).appendTo($("#batchDiv"));
			}
		},
		error : function(result) {
			alert("出错");
		}
	
	});
	var div = document.getElementById('batchDiv');
	div.style.visibility = "visible";
}


$(function(){
	flagRefresh[0]='con';
	$("#addTag").click(function(){
		var params = $("#paramTag").textbox("getValue");
		$.ajax({
			type:'post',
			url:'addProductById',
		    cache:true,
		    data : 'params=' + params,
	 	 	dataType : 'json',
	 	 	success : function(result){
	 	 		$.messager.show({
	 	 			title : result[0]["status"],
					msg : result[0]["message"],
				});	 	 		
	 	 	},
	 	     error : function(result){
	 	    	$.messager.show({
	 	    		title : result[0]["status"],
					msg : result[0]["message"],
				});	
	 	     }
		});
	});

	$('#tagSearch').find('span[class*="tagSpan"]').each(function(){
		var id = $(this).find('#realValue').val();
		var w = $(this).find('.tagWord').val();
	});
	
});

// function setTrack(tt,event){
// 	var e = event?event:window.event;
// 	if(e.keyCode == 13){
// 		document.getElementById("updateTag").style.display = "block"; 
// 		updateProduceTag();
// 	}
// 	}


function updateProduceTag(){
	document.getElementById("updateTag").style.display = "block"; 
	var paramTag = $("#paramTag").val();
	var goodSn = $("#hiddenGoodSn").val(); 
	$.ajax({
		type:'post',
		url:'getGoodTags',
	    cache:true,
	    data : 'paramTag=' + paramTag,
 	 	dataType : 'json',
 	 	success : function(result){
 	 		$("#updateTag").html("") ;
			var list = result;
			var tempAppend='';
			if(list.length>0){
				for(var i = 0;i<list.length;i++){
				tempAppend = tempAppend + '<div>'+
// 				'<a href="javascript:getValue(\'searchPara\',\''+list[i].tagWord+'\')">'+list[i].tagWord+'</a>'+
// 			    '</div>';
				'<nobr><a href="javascript:getTagValue(\''+goodSn+'\',\''+list[i]+'\')">'+list[i]+'</a></nobr>'+
			    '</div>';
				}
				
			$(tempAppend).appendTo($("#updateTag"));
			}
 	 	},
 	     error : function(result){
 	    	 alert("添加失败");
 	     }
	});
}

function getTagValue(sourceObj,tagName){
	document.getElementById("updateTag").style.display = "none"; 
	hiddenDiv();
	$.ajax({
		type:'post',
		url:'addProductById',
	    cache:true,
	    data : 'param=' + sourceObj+"&tag="+tagName,
 	 	dataType : 'json',
 	 	success : function(result){
 	 		alert(result[0]["message"]);
 	 		if(flagRefresh[0]=='con'){
 	 			refreshPage();
 	 		}else{
 	 			refreshPageTag();
 	 		}
 	 	},
 	     error : function(result){
 	    	 alert(result[0]["message"]);
 	     }
});
}
   function conRefresh(){
    var pageSize = $("#rounded").val();
	var pageNo = '1';
	var params = appendParams(pageSize,pageNo);
	commonQuery(params);
   }
   function tagRefresh(){
    var pageSize = $("#rounded").val();
	var pageNo = '1';
	var params = appendParams(pageSize,pageNo);
	commonQuery(params);
   }
   
function deleteTag(sourceObj,tagName){
	$.ajax({
		type:'post',
		url:'deleteProductById',
	    cache:true,
	    data : 'param=' + sourceObj+"&tag="+tagName,
 	 	dataType : 'json',
 	 	success : function(result){
 	 		alert("删除成功");
 	 		if(flagRefresh[0]=='con'){
 	 			refreshPage();
 	 		}else{
 	 			refreshPageTag();
 	 		}
 	 	},
 	     error : function(result){
 	    	 alert("删除失败");
 	     }
});
}

function getBatchTag(sourceObj){
//	document.getElementById(targetObj).value = sourceObj;
     var div = document.getElementById('batchDiv');
	div.style.visibility = "hidden";
    var temp = '';
    temp = $("#allTagBatch").html();
    temp = temp + '<span id="batchTagValue" class="tagSpan" onclick="deleteValue();">'+sourceObj+''+
	'<input type="hidden" id="bvalue" name="bvalue" value="'+sourceObj+'"/></span>&nbsp;&nbsp;';
    $("#allTagBatch").html(temp);
    
}
function deleteValue(){
	var obj = $(arguments.callee.caller.arguments[0].target);
	obj.remove();
}


//批量导入代码
function addBatchProduct(){
	var fade ="fadewait";
	var content="waitDiv";
	var fadebatch='fadebatch';
	var lightBatch='lightBatch';
	var bvalue = $(':input').filter('[id="bvalue"]');
	var bProductId = $(':input').filter('[id="batchProductId"]');
	var yescon = document.getElementsByName("yescon");
	var yestag = document.getElementsByName("yestag");
	var page='';
	var isrevoke = '';
    for(var i=0;i<yescon.length;i++)
    {
        if(yescon[i].checked==true)
        {
            page = "yescon="+yescon[i].value;
        }
    }
    for(var i=0;i<yestag.length;i++)
    {
        if(yestag[i].checked==true)
        {
        	isrevoke = "yestag="+yestag[i].value;
        }
    }
    if(bvalue.length <= 0){
    	alert("至少选择一个标签");
    }else if(page==""){
    	alert("请选择数据范围");
    }else if(isrevoke==""){
    	alert("请选择执行方式");
    }
    else{
    var tempValue='';
    for(var i = 0;i<bvalue.length;i++){
    	tempValue += "&bvalue=" + bvalue[i].value;
    }
    var tempProductId = '';
    for(var i = 0;i<bProductId.length;i++){
    	tempProductId += "&bProductId=" + bProductId[i].value;
    }
    var paramValue = tempValue.substring(1, tempValue.length);
    var paraPid = tempProductId.substring(1, tempProductId.length);
    var params = paramValue +"&" + paraPid+"&"+page+"&"+isrevoke;
    
    commonPopClose(fadebatch,lightBatch);
    commonPopShow(fade,content);
	$.ajax({
		type:'post',
		url:'addBatchProductById',
	    cache:true,
	    data : params,
 	 	dataType : 'json',
 	 	success : function(result){
 	 	  commonPopClose(fade,content);
 	 		alert("执行成功");
 	 		refreshPage();
 	 		
 	 	},
 	     error : function(result){
 	 	 	 commonPopClose(fade,content);
 	    	 alert("执行失败");
 	     }
});
    }
}
//批量导入代码
function addBatchProductTag(){
	var fade ="fadewait";
	var content="waitDiv";
	var fadebatch='fadebatch';
	var lightBatch='lightBatch';
	var bvalue = $(':input').filter('[id="bvalue"]');
	var bProductId = $(':input').filter('[id="batchProductIdTag"]');
	var yescon = document.getElementsByName("yescon");
	var yestag = document.getElementsByName("yestag");
	var page='';
	var isrevoke = '';
    for(var i=0;i<yescon.length;i++)
    {
        if(yescon[i].checked==true)
        {
            page = "yescon="+yescon[i].value;
        }
    }
    for(var i=0;i<yestag.length;i++)
    {
        if(yestag[i].checked==true)
        {
        	isrevoke = "yestag="+yestag[i].value;
        }
    }
    if(bvalue.length <= 0){
    	alert("至少选择一个标签");
    }else if(page==""){
    	alert("请选择数据范围");
    }else if(isrevoke==""){
    	alert("请选择执行方式");
    }
    else{
    var tempValue='';
    for(var i = 0;i<bvalue.length;i++){
    	tempValue += "&bvalue=" + bvalue[i].value;
    }
    var tempProductId = '';
    for(var i = 0;i<bProductId.length;i++){
    	tempProductId += "&bProductId=" + bProductId[i].value;
    }
    var paramValue = tempValue.substring(1, tempValue.length);
    var paraPid = tempProductId.substring(1, tempProductId.length);
    var params = paramValue +"&" + paraPid+"&"+page+"&"+isrevoke;
    
    commonPopClose(fadebatch,lightBatch);
    commonPopShow(fade,content);
	$.ajax({
		type:'post',
		url:'addBatchProductById',
	    cache:true,
	    data : params,
 	 	dataType : 'json',
 	 	success : function(result){
 	 	  commonPopClose(fade,content);
 	 		alert("执行成功");
 	 		refreshPageTag();
 	 	},
 	     error : function(result){
 	 	 	 commonPopClose(fade,content);
 	    	 alert("执行失败");
 	     }
});
    }
}

$(document).bind('click',function(e){
		var elem = e.target;
		while (elem) {
		if (elem && elem.id !='float-div' && elem.id !='addBtn'
			&& elem.id != 'paramTag' && elem.id != 'interDiv' && elem.id !='updateTag') {
			document.getElementById("updateTag").style.display = "none"; 
			document.getElementById('float-div').style.display = 'none';
		}else{
			 return;
		}
 		elem = elem.parentNode;
		}
	 });

function hovercell(goodSn,thisObj) {  
	$("#paramTag").val('');
	$("#updateTag").html("");
	$("#hiddenGoodSn").val(goodSn);
	var thisLeft = $(thisObj).position().left - 0;
	var thisTop = $(thisObj).position().top - 0;
    var objDiv = document.getElementById("float-div");  
	objDiv.style.left = thisLeft+'px';
	objDiv.style.top = thisTop+'px';
	objDiv.style.display = "block"; 
// 	 var e = arguments[0]||event || window.event; 
// 	 var evt=getEvent();
// 		if(null!=evt){
// 			var el=evt.target||evt.srcElement;
// 			var top=0;
// 			var left=0;
// 			while(el){//递归求元素所在页面的位置
// 				top+=el.offsetTop;//el.offsetTop的值是el元素的上起始位置相对于父元素的上起始位置的差值，所以需要递归相加

// 				left+=el.offsetLeft;
// 				el=el.offsetParent;
// 			}
			
// 			var x = clickX=left;
// 			var y = clickY=top;
// 		    var client_x = x+"px";
// 		    var client_y = y+"px";
// 		    var objDiv = document.getElementById("float-div");  
// 			objDiv.style.left = client_x;
// 			objDiv.style.top = client_y;
// 			objDiv.style.display = "block"; 
// 		}
}


//获取事件
function getEvent() {
	if (document.all) {
		return window.event;// 如果是ie
	}
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
					|| (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}


$(document).ready(function(){
	 $('#tabs').tabs({   
	       border:false,   
	       onSelect:function(title){   
	    	   if(title == "按条件搜索商品"){
	    			document.getElementById('con').style.display='block';
	    			document.getElementById('tag').style.display='none';
	    			flagRefresh[0] ='con';
	    		}else if(title == "按标签搜索商品"){
	    			document.getElementById('tag').style.display='block';
	    			document.getElementById('con').style.display='none';
	    			flagRefresh[0]='tag';
	    		}
	       }
	 });
	//按标签搜索框的click事件
		$('#tagInputDiv').click(function(e){
			var id = e.target.id;
			if(id == "tagInputDiv"){
				$("#searchTag").val('');
				$("#tagSearch").html('');
				commonPopShow('fade1','light1');
			}else{
				if(id != 'tagInput'){
				pushTags(id,null);
				var clickObj = $(e.target);
				clickObj.fadeOut(0, function(){
					$(this).remove();
				});
				}
			}
		});
	

// 		 var options = {  
// 			        beforeSubmit:  showRequest,  //提交前处理 
// 			        success:       showResponse,  //处理完成 
// 			        resetForm: false,  
// 			        dataType:  'json', 
// 			        url:'importGoodTags',
// 			        type :'post',
// 			        success:function(result){
// 			        	alert(result);
// 			        },
// 			        error:function(result){
// 			        	alert(result);
// 			        }
// 			    };  
// 		 $('#importForm').submit(function() {  
// 			 alert("aaa");
// 		        $(this).ajaxSubmit(options);  
// 		    });  
		
// 		 function showRequest(formData, jqForm, options) {  
// 			    return true;  
// 			}  
// 		 function showResponse(responseText, statusText)  {  
// 			 return false;
// 			}  
						  
				
});
function pageNumTag(){
    var pageSize = $("#roundedTag").val();
	var currentPage = $("#hcurrentPageTag").val();
	var params = appendTag(pageSize,currentPage);
	commonQueryTag(params);
}

var flagRefresh={};
var selectTags = {};
function selectPara(thisPara,id,word){
	pushTags(id, word);
	if(selectTags[id]){
		$(thisPara).addClass('tagBg');
	}else{
		$(thisPara).removeClass('tagBg');
	}
}
//重绘标签信息元素，以保存的id信息，不同颜色显示
function drawTagSpan(){
	$('span[class*="tagSpan"]').each(function(){
		var id = $(this).find('.tagId').val();
		var w = $(this).find('.tagWord').val();
		if(selectTags[id]){
			$(this).addClass('tagBg');
		}else{
			$(this).removeClass('tagBg');
		}
	});
}
//保持或删除id信息
function pushTags(id, word){
	if(selectTags[id]){
		deleteTags(id);
	}else{
		selectTags[id] = word;
	}
}
function deleteTags(id){
	selectTags[id] = null;
	delete selectTags[id];
}
//确认选择标签
function confirmTags(){
	var fade = 'fade1';
	var light = 'light1';
	var h = '';
	for(var id in selectTags){
		var word = selectTags[id];
		if(word){//onclick="deleteSelectTags(this,\''+id+'\')"
			h += '<span class="tag_Span" id="'+id+'" >'+word+'<input id="tagName" type="hidden" value="'+word+'" class="tagName" /></span>';
		}
	}
	
	if(h == null || h.length < 1) {
		alert('请至少选择一个标签！！');
		return;
	}else{
	$('#tagInput').html(h);
	commonPopClose(fade,light);
	}
}
function showDiv(){
	var  para = $("#searchPara").val();
	$.ajax({
		type : 'post',
		url : 'queryTagsToSelect',
		cache : false,
		data : "para="+para,
		dataType : 'json',
		success : function(result) {
			$("#sel").html("") ;
			var list = result;
			var tempAppend='';
			if(list.length>0){
				for(var i = 0;i<list.length;i++){
				tempAppend = tempAppend + '<div>'+
				'<nobr><a href="javascript:getValue(\''+list[i]["tagWord"]+'\')">'+list[i]["tagWord"]+'</a></nobr>'+
			    '</div>';
				}
			$(tempAppend).appendTo($("#sel"));
			}
		},
		error : function(result) {
			$.meesager.show({
				title : "提示信息",
				msg : "出错",
			});
		}
	});
	var div = document.getElementById('sel');
	div.style.visibility = "visible";
}

function getValue(sourceObj){
    var temp = '';
    temp = $("#appendTag").html();
    temp = temp + '<span id="tagValue" onclick="deleteValue();">'+sourceObj+'</span>'+
	'<input type="hidden" id="ivalue" name="ivalue" value="'+sourceObj+'"/>';
    $("#appendTag").html(temp);
	hideDiv();
}
function hideDiv(){
	var div = document.getElementById('sel');
	div.style.visibility = "hidden";
}
function deleteValue(){
var obj = $(arguments.callee.caller.arguments[0].target);
obj.remove();
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


</script>
<style type="text/css">
#tabs {
	width: 92%;
	height:90%;
  	margin-left: 2px;
 	margin-right: auto;
}
#panel{
	width:95%;
	height:120px;
	padding:10px;
/* 	margin-left: auto; */
/* 	margin-right: auto; */
}
#condition {
	overflow-y: scroll;
 	height: 88%;
}
#tagCon {
 	overflow-y: scroll; 
 	height: 88%; 
 	}
#tabInfo{
width: 98%;
margin-left:auto;
margin-right:auto;
margin-top: 10px;
float: left;
}
#tabInfoL{
width: 49%;
border:1px solid #8A2BE2;
float: left;
padding: 2px;
margin: 2px;
}
#tabInfoR{
width: 49%;
border:1px solid #8A2BE2;
float: right;
padding: 2px;
margin: 2px;
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
        .myText{
				width:200px;
				height:30px;
			}
			.myDiv{
				border:1px solid #CE7E00;
				background:#FFF0D9;
				scrollbar-face-color:#FFF0D9;
				position:absolute;
				z-index:10;
				font-size:12px;
			}
			.msgDiv{
				border:1px solid #CE7E00;
				background:#FFF0D9;
				overflow-x: hidden;
				z-index:10;
			}
			
			.myDiv a{
				text-decoration:none;
				display:block;
				height:18px;
				line-height:18px;
				text-indent:4px;
				text-align:left;
			}
			.myDiv a:link, .myDiv a:visited{
				color: #2A1B00;
				background-color:#FFF0D9;
			}
			.myDiv a:hover, .myDiv a:active{
				background-color:#C2C660;
			}
     #appendTag>span{
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #999;
	border-radius: 5px;
	display: inline-block;
	padding: 3px 8px;
	margin-left:3px;
	text-decoration: none;
	cursor: pointer;
  }
  
    #appendTag>span>a:hover{
    color: #EE00EE ;
	text-decoration:none;
	cursor: pointer;
   }
     #tagSearch>span{
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #999;
	border-radius: 5px;
	display: inline-block;
	padding: 3px 8px;
	margin-left:3px;
	text-decoration: none;
	cursor: pointer;
  }
    #tagSearch>span>a:hover{
    color: #EE00EE ;
	text-decoration:none;
	cursor: pointer;
   }
     td>span{
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #90A8E0;
	border-radius: 5px;
	display: inline-block;
	padding: 3px 8px;
	margin-left:3px;
	text-decoration: none;
	cursor: pointer;
	background-color: #90A8E0;
  }
    td>span>a:hover{
    color: #EE00EE ;
	text-decoration:none;
	cursor: pointer;
   }
   
   .pointer {cursor:pointer;}
   .right {float:right;
    margin-top: -20px;
    margin-right: -20px;
   }
     img {border:none}
     
  #scroll_div {
  text-align:left;
  margin:0px;
  width:8%; 
  height:400px; 
  background:#E4DEDE;
/*   background:#ACC9F8; */
/*   float: right; */
  padding-top:20px;
  margin-top: 10%;
  position: absolute;
  right:0px;
   }
   #con{
   text-align:center;
    margin-top: 20px;
   }
   #tag{
   text-align:center;
    margin-top: 20px;
   }
   .pageStyle{
   padding-top: 6px;
   padding-bottom:6px;
   margin-top: 6px;
   margin-bottom: 6px;
   background-color: #7194E7; 
   }
   .tagSpan{
		float:left;
		padding-top:3px;
		padding-bottom:3px;
		padding-left:6px;
		padding-right:5px;
		background-color:#7099DD;
		color:white;
		margin:2px;
		cursor:pointer;
		font-size:13px;
		border-radius: 3px;
	}
     .tagSpanSelect{
		float:left;
		padding-top:3px;
		padding-bottom:3px;
		padding-left:6px;
		padding-right:5px;
		background-color:green;
		color:white;
		margin:2px;
		cursor:pointer;
		font-size:13px;
		border-radius: 3px;
	}
	.tag_Span{
		float:left;
		padding-top:3px;
		padding-bottom:3px;
		padding-left:6px;
		padding-right:5px;
		background-color:#7099DD;
		color:white;
		margin:4px;
		cursor:pointer;
		font-size:13px;
		border-radius: 3px;
	}
	#tagInputDiv{
	    height :35px;
		border:1px solid #aaaaaa;
		padding-left:1px;
		width:700px;
		text-align:center;
		display:inline-block;
	}
#btn_close,#btn_gotop{ cursor:pointer;}
	.tagBg{
		background-color:green;
	}
	
	ul {
 list-style: none;
}
	#faq li {
 margin:0;
 padding:0;
width: 600px;
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
 border-bottom:1px #ccc dotted;
}
#faq dd {
 display:none;
 margin:0;
 background:#E5ECF9;
}
</style>

</head>

<body>
<div id="scroll_div">

<div id="con">
<input type="button" id="pageStyle"  class="pageStyle" onclick="first();" value="查看第一页 " style="width:90%"  onclick="prevTwenty();" />
<input type="button" id="pageStyle" class="pageStyle"  onclick="prev();" value="查看前一页" style="width:90%"  onclick="prevTwenty();" />
<input type="button" id="pageStyle" class="pageStyle"  onclick="nextPage();"value="查看后一页" style="width:90%" onclick="prevTwenty();" />
<input type="button" id="pageStyle" class="pageStyle"  onclick="last();" value="查看最后一页" style="width:90%" onclick="prevTwenty();" />
<select id="rounded" name="rounded" onchange="pageNum()" style="width: 90%; margin-top: 6px; margin-bottom: 6px;">
			   		<option value="10">10条/页</option>
			   		<option value="20">20条/页</option>
			   		<option value="30">30条/页</option>		   		
			   	</select>
		共<span id="totalNum">0</span>条/<span id="totalPage">0</span>页<br/>
		当前第<span id="currentPage">1</span><input type="hidden" id="hcurrentPage" name="hcurrentPage" value="1"/>页 
		<input type="button"  class="pageStyle" id="batchImportCon" value="批量导入" style="width:90%"/>	   	
</div>
<!-- <dl id="con" style="display: block;"> -->
<!-- 			   <dt class="start" >首页</dt> -->
<!-- 			   <dt class="first" >上一页</dt> -->
<!-- 		       <dt class="next" >下一页</dt> -->
<!-- 			   <dt class="end"  >尾页</dt> -->
<!-- 			   <dt  class="rounded"> -->
<!-- 			   	<select id="rounded" name="rounded" onchange="pageNum()"> -->
<!-- 			   		<option value="10">10条/页</option> -->
<!-- 			   		<option value="20">20条/页</option> -->
<!-- 			   		<option value="30">30条/页</option>		   		 -->
<!-- 			   	</select> -->
<!-- 			   </dt> -->
<!-- 			  <dt class="pageInfo">共<span id="totalNum">0</span>条/<span id="totalPage">0</span>页/当前第<span id="currentPage">1</span><input type="hidden" id="hcurrentPage" name="hcurrentPage" value="1"/>页 -->
<!-- 			  </dt> -->
<!-- 			</dl> -->
		<div id="tag" style="display: none;">
<input type="button" id="pageStyle" class="pageStyle"  onclick="firstTag();" value="查看第一页 " style="width:90%"  onclick="prevTwenty();" />
<input type="button" id="pageStyle" class="pageStyle" onclick="prevTag();" value="查看前一页" style="width:90%"  onclick="prevTwenty();" />
<input type="button" id="pageStyle" class="pageStyle" onclick="nextPageTag();"value="查看后一页" style="width:90%" onclick="prevTwenty();" />
<input type="button" id="pageStyle" class="pageStyle" onclick="lastTag();" value="查看最后一页" style="width:90%" onclick="prevTwenty();" />
	<select id="roundedTag" name="roundedTag" onchange="pageNumTag()" style="width: 90%; margin-top: 6px; margin-bottom: 6px;">
			   		<option value="10">10条/页</option>
			   		<option value="20">20条/页</option>
			   		<option value="30">30条/页</option>		   		
			   	</select>
		共<span id="totalNumTag">0</span>条/<span id="totalPageTag">0</span>页<br/>
		当前第<span id="currentPageTag">1</span><input type="hidden" id="hcurrentPageTag" name="hcurrentPageTag" value="1"/>页 
		<input type="button"  class="pageStyle" id="batchImportTag" value="批量导入" style="width:90%"/>
</div>
<!-- 		<dl id="tag" style="display: none;"> -->
<!-- 			   <dt class="start" onclick="firstTag();">首页</dt> -->
<!-- 			   <dt class="first" onclick="prevTag();">上一页</dt> -->
<!-- 		        <dt class="next" onclick="nextPageTag();">下一页</dt> -->
<!-- 			   <dt class="end" onclick="lastTag();">尾页</dt> -->
<!-- 			   <dt  class="rounded"> -->
<!-- 			   	<select id="roundedTag" name="roundedTag" onchange="pageNumTag()"> -->
<!-- 			   		<option value="10">10条/页</option> -->
<!-- 			   		<option value="20">20条/页</option> -->
<!-- 			   		<option value="30">30条/页</option>		   		 -->
<!-- 			   	</select> -->
<!-- 			   </dt> -->
<!-- 			  <dt class="pageInfo">共<span id="totalNumTag">0</span>条/<span id="totalPageTag">0</span>页/当前第<span id="currentPageTag">1</span><input type="hidden" id="hcurrentPageTag" name="hcurrentPageTag" value="1"/>页 -->
<!-- 			  </dt> -->
<!-- 			</dl>	 -->
</div>
	<div style="margin-left: auto;margin-right: auto;text-align: center; height: 5%">
  		<div style="text-align: center; margin: 2px; padding: 2px;font-size: 17px;">商品标签管理</div> 
  		<div style="float: right; margin-right: 9%; padding-bottom: 2px;">
  		<span style="color: bule"><a href="#" onclick="tagged();">根据六位码打标签</a></span>
  		</div>
  	</div>
  	<div class="easyui-tabs" data-options="tabWidth:200" id="tabs">
	  <div id="condition" title="按条件搜索商品">
      <div id="panel" class="easyui-panel" title="查询条件" data-options="collapsible:true">
      <form id="query" name="query">
       <table style="margin-left: auto;margin-right: auto;">
      <tr>
      <td style="width: 50px;">关键字:</td>
      <td>
      <input class="easyui-textbox" id="word" name="word" style="width:150px;height:32px">&nbsp;&nbsp;
      </td>
        <td width="70px;">平台商品id:</td>
      <td>
      <input class="easyui-textbox" id="productId" name="productId" style="width:100px;height:32px">&nbsp;&nbsp;
      &nbsp;&nbsp;
      </td>
     <td>色系:</td>
      <td>
      <input class="easyui-textbox" id="color" name="color"  style="width:120px;height:32px">&nbsp;&nbsp;
      </td>
       <td>品牌：</td>
      <td>
      <input class="easyui-textbox" id="brand" name="brand" style="width:120px;height:32px">
      </td>
       <td>店铺ID：</td>
      <td>
      <input class="easyui-textbox" id="storeId" name="storeId" style="width:120px;height:32px">
      </td>
      </tr>
      <tr>
       <td width="70px;">分类ID:</td>
       <td>
     <input class="easyui-textbox" id="cid" name="cid" style="width:150px;height:32px"> &nbsp;&nbsp;
      </td>
      
      <td width="70px;">自定义价格:</td>
      <td style="400px;"><input class="easyui-textbox" id="prMin" name="prMin"  style="width:50px;height:32px">
     -- <input class="easyui-textbox" id="prMax" name="prMax" style="width:50px;;height:32px">
     &nbsp;&nbsp;
       </td>
      
        <td width="70px;">折扣:</td>
      <td style="400px;"><input class="easyui-textbox" id="disMin" name="disMin" style="width:50px;height:32px">
     -- <input class="easyui-textbox" id="disMax" name="disMax" style="width:50px;;height:32px">
       </td>
       <td width="70px;">最低库存值:</td>
       <td>
     <input class="easyui-textbox" id="stock" name="stock" style="width:120px;height:32px"> &nbsp;&nbsp;
      </td>
       <td width="70px;">尺寸code:</td>
       <td>
     <input class="easyui-textbox" id="sizeCode" name="sizeCode" style="width:120px;height:32px"> &nbsp;&nbsp;
      </td>
      </tr>
      <tr>
       <td width="70px;">搜索类型:</td>
       <td>
       <select class="easyui-combobox" id="searchType" name="searchType" style="height: 32px; width: 150px" >
               <option value="">请选择</option>
			   <option value="1">搜索款式商品</option>
			   <option value="2">搜索颜色商品</option>
      </select>
      </td>
       <td width="70px;">条件优先级:</td>
       <td>
       <select class="easyui-combobox" id="andFirst" name="andFirst" style="height: 32px; width: 120px" >
               <option value="">请选择</option>
			   <option value="0">或优先</option>
			   <option value="1">且优先</option>
      </select>
      </td>
       <td width="70px;">排序类型:</td>
       <td>
       <select class="easyui-combobox" id="sortType" name="sortType" style="height: 32px; width: 120px" >
			   <option value="">请选择</option>
			   <option value="1">升序</option>
			   <option value="-1">降序</option>
      </select>
      </td>
       <td width="70px;">排序字段:</td>
       <td>
        <select class="easyui-combobox" id="sortField" name="sortField" style="height: 32px; width: 120px" >
               <option value="">请选择</option>
			   <option value="1">价格</option>
			   <option value="2">总销量</option>
			   <option value="3">上市日期</option>
			   <option value="4">周销量</option>
			   <option value="5">满意度</option>
      </select>
      </td>
      
      <td width="70px;">
     <a href="#" id="confirm" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 32px;">查询</a>
     </td>
     <td>
     <a href="#" id="reset" class="easyui-linkbutton"  style="width:80px;height: 32px;">重置</a>
<!--      <a href="#" id="batchImportCon" class="easyui-linkbutton"  style="width:80px;height: 32px;">批量导入</a> -->
      </td>
      </tr>
      </table>
      </form>
	</div>
	
		<div id="tabInfo">
       <div id="tabInfoContent"></div>		
		
	

		
		</div>
		</div>
		<div id="tagCon" title="按标签搜索商品" style="padding:10px">
		<input type="hidden" id="flag" name="flag" value="1"/>
      <div id="panel" class="easyui-panel" title="查询条件" data-options="collapsible:true">
<!--       <input type="text" id="tagQuery1" name="tagQuery1" style="width:30%;height:32px" onfocus="commonPopShow('fade1','1')"> -->
<!--      <div  class="msgDiv" id="tagQuery1" onfocus="commonPopShow('fade1','light1')" style="border:1 #999999 solid; word-break:break-all; width:80%; height:55px; cursor:"text"; contentEditable="true"></div> -->
     <div id="tagInputDiv">
										<div style="display:inline-block;float:left;" id="tagInput">
										</div>
										<div style="height:35px;display:inline-block;float:left;width:20px;" id="anyID"></div>
									</div>
									<br/>
      <a href="#" id="tagQuery"  class="easyui-linkbutton"  style="height:32px;  margin: 5px;">点击查询</a>
<!--       <a href="#" id="batchImportTag"  class="easyui-linkbutton"  style="height:32px;">批量导入</a> -->
<!--       <a href="#" class="easyui-linkbutton" id="clearbtn" style="height:32px;">清空</a> -->
<!--       <div id="tagSearch" style="padding: 5px; margin: 5px"> -->
<!-- 	   <span id="tagValue">aaaa<a><sup>x</sup></a></span><input type="hidden" id="realValue" name="realValue" value="aaa"/> -->
      
<!-- 	 </div> -->
      
      
	</div>
	
	
	
	
	
		<div id="tabInfo">
		
          <div id="tabInfoContentTag"></div>			
		</div>
		
		
		
		</div>
	</div>
	
	  <div id="light" class="white_content">
<!-- 	<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">点这里关闭本窗口</a> -->
	
	 <img src="${rc.contextPath}/images/off.png" class="right pointer" onclick="closeDiv();" title="点击关闭"/>
	 
	 
	  <form id="importForm" name="importForm" action="importGoodTags"  method="post" enctype="multipart/form-data">
	  <div>
	 <div style="text-align: center; margin: 5px; padding: 5px;font-size: 20px;">通过Excel文件导入</div> 
	 
	 <div style="width: 48%; float: left;border:1px solid #8A2BE2; height: 120px;">
	 <input type='text'  id='searchPara' name="searchPara" class="myTxt" onblur="showDiv();" style="width: 300px; height:25px; margin-top: 10px; margin-left: 5px;" />
		<div id='sel' class='myDiv' style="width:300px; height:80px; visibility:hidden; margin-left: 5px; overflow: hidden;">
<!-- 					<div> -->
<!-- 						<a href="javascript:getValue('txt1','选择一')">选择一</a> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<a href="javascript:getValue('txt1','选择二')">选择二</a> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<a href="javascript:getValue('txt1','选择三')">选择三</a> -->
<!-- 					</div> -->
		</div>
	 </div>
	    <div style="width: 48%;float: right;  border:1px solid #8A2BE2;height: 120px;">
	      <div style="margin-top: 10px; margin-left: 5px;">
          <input type="file" id="filename" name="filename"/> 
          <input id="importData" name="importData" type="submit" value="提交" style="width:80px;height: 25px;"/>
			<br/>
			<br/>
			<br/>
			</div>
	      </div>



	  </div>
	 <div style="width: 100%; height:150px; float: left; height: 150px;  margin-top: 10px;">
	 <div id="selectTag"   class="easyui-panel" title="选中的标签" style="width:700px;height:100px;">
	 <div id="appendTag">
<!-- 	 <span id="tagValue">aaaa<sup>x</sup></span><input type="hidden" id="realValue" name="realValue" value="aaa"/> -->

	 </div>
	</div>
	 	<div>
				  <ul id="faq">
				  <li>
				    <dl>
				      <dt>规则说明</dt>
				      <dd>
• 在标签输入框中，输入标签内容的开头字符串，系统自动给出前面几个符合要求的标签，点击选中标签，选择的标签会出现 在“已选择标签”中。如果要取消标签，则在“已选择标签”中点击相应标签即可 <br>
• excel文件中，商品编号（六位码）位于第一个sheet的第一列；选择excel、选择需要打上的标签，然后确定 <br>
• excel表格中的六位码对应的商品分上架和下架两种（对于搜索来说，即可搜索和不可搜索）。如果是已上架商品，指定 标签将直接打到对应商品上，5分钟定时更新索引时生效；如果是未上架商品，则将标签信息存放到未上架标签库中，等该商品 在下次上架时，自动打上对应标签<br> 
• 对未上架的商品导入标签，只保留最后一次导入的标签；对于已上架的商品，对其下架，其对应标签会被保存，如果此时导入该商品 的标签，则保留最后一次导入的标签<br> 

					</dd>
				    </dl>
				  </li>
				 </ul>

				</div>
<!-- 	 </div> -->

	  </div>
	  </form>
	  
	  </div> 
	  
	  
        <div id="fade" class="black_overlay"></div> 
       
        <div id="light1" class="white_content">
	
	 <img src="${rc.contextPath}/images/off.png"  class="right pointer" onclick="commonPopClose('fade1','light1');" title="点击关闭"/>
	   
	 <div style="text-align: center; margin: 5px; padding: 5px;font-size: 15px;">商品标签编辑</div> 
	 
	 <div style="width: 100%; float: left;border:1px solid #8A2BE2; height: 80px;">
	 <input type='text'  id='searchTag' name="searchTag" style="width: 300px; height:30px; margin-top: 10px; margin-left: 5px;" />
	 <input type="button" id="search" name="search" value="点击查询" onclick="tagquery();" style="height: 30px;">
	 <input type="button" id="resultT" name="resultT" value="确定" onclick="confirmTags();" style="height: 30px;">
    </div>
<!--  <div style="width: 100%; height:150px; float: left;border:1px solid #8A2BE2; height: 150px;  margin-top: 10px;"> -->
	 <div id="selectTag"   class="easyui-panel" title="所有的标签" style="width:100%;height:180px;">
	<div id="tagSearch" style="padding: 5px; margin: 5px">
      
	 </div>
	</div>
	 </div>

<!-- 	  </div> -->

	  
	  
	  
        <div id="fade1" class="black_overlay"></div> 
        
        
<!--         <div style='position:absolute; background-color:#FFFFE0; top:40;left:10; display:none; width:450px;height:180px;border:1px double #AAA;' id='float-div' class='float-div'>    -->
        <div style='position:absolute; top:40;left:10; display:none; width:450px;height:180px;' id='float-div' class='float-div'>   
          <div id="interDiv" style="padding-top: 5px;">
          <input type="hidden" id="hiddenGoodSn" name="hiddenGoodSn">
          <input type="text" id="paramTag" name="paramTag" style="width:380px;height:32px; margin-left: 10px;" onkeyup="updateProduceTag();"></div>
            
           <div id='updateTag'  class='myDiv' style="width:380px; height:120px; margin-left: 10px; display: none; overflow:hidden;">
<!-- 					<div> -->
<!-- 						<a href="javascript:getValue('txt1','选择一')">选择一</a> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<a href="javascript:getValue('txt1','选择二')">选择二</a> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<a href="javascript:getValue('txt1','选择三')">选择三</a> -->
<!-- 					</div> -->
		</div>
        
        
        </div>
       
         <div id="lightBatch" class="white_content">
	 <div id="fitTag">
	 <img src="${rc.contextPath}/images/off.png" class="right pointer" onclick="commonPopClose('fadebatch','lightBatch');" title="点击关闭"/>
	 <div style="text-align: center; margin: 5px; padding: 5px;font-size: 15px;">商品标签编辑</div> 
	 
	 <div style="width: 48%; float: left;border:1px solid #8A2BE2; height: 150px;">
	 <input type='text'  id='batchTag' name="batchTag" class="myTxt" onkeyup="batchTagQuery();" style="width: 300px; height:25px;margin-left: 5px; margin-top: 10px" />
		<div id='batchDiv' class='myDiv' style="width:300px; height:100px; visibility:hidden; margin-left: 5px;overflow:hidden;">
		
		</div>
	 </div>
	    <div style="width: 48%;float: right;  border:1px solid #8A2BE2;height: 150px;">
	      <div style="margin-top: 10px; margin-left: 5px;">
	    <div id="allTagBatch" style="padding: 5px; margin: 5px;">
<!-- 	   <span id="tagValue">aaaa<a><sup>x</sup></a></span><input type="hidden" id="realValue" name="realValue" value="aaa"/> -->
	  </div>
	</div>
			</div>
      <table>
      <tr>
      <td>数据范围：</td>
      <td><input type="radio" id="yescon" name="yescon" value="1"/>符合条件的当前页</td>
      <td><input type="radio" id="yescon" name="yescon" value="2"/>符合条件的所有商品 </td>
      </tr>
      <tr>
       <td>执行方式：</td>
      <td><input type="radio" id="yestag" name="yestag" value="1"/>打上指定标签(若没有)</td>
      <td><input type="radio" id="yestag" name="yestag" value="2"/>撤销指定标签(如果有))</td>
      </tr>
<!--       <tr> -->
<!--       <td></td> -->
<!--       <td><input type="button" value="确定" onclick="addBatchProduct();"></td> -->
<!--       <td><input type="button" value="取消"></td> -->
<!--       </tr> -->
      </table>
      </div>
       <div id="btn"></div>
    <div>
				  <ul>
				  <li>
				    <dl>
				      <dt>规则说明</dt>
				      <dd>
      • 在标签输入框中，输入标签内容的开头字符串，系统自动给出前面几个符合要求的标签，点击选中标签，
	                 选择的标签会出现 在“已选择标签”中。如果要取消标签，则在“已选择标签”中点击相应标签即可 <br>
	  • 标签有效天数，默认是0，表示不失效 <br>
	  • 选择好打标签时的条件，确定 <br>
					</dd>
				    </dl>
				  </li>
				 </ul>

				</div>
     
	  </div>
        <div id="fadebatch" class="black_overlay"></div> 
        
        
<!--         加载等待提示框 -->
        <div id="waitDiv" class="white_content">
        <div style="text-align: center; margin: 5px; padding: 5px;font-size: 20px;">程序正在执行，请耐心等候。。。。。。。。</div>
	    <div style="text-align:center; margin-left: auto; margin-right: auto;"><img alt="请稍候" src="${rc.contextPath}/images/wait.gif"> </div>
        </div>
	  
        <div id="fadewait" class="black_overlay"></div> 
        
        
  </body>
</html>


