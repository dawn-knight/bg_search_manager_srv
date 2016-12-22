<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/css.jsp"%>
<%@ include file="/common/js.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户角色管理</title>
<script type="text/javascript">
$(document).ready(function () {

	$('input:checkbox').filter('[name="totalMenu"]').click(function(){
		if(this.checked){
			$('input:checkbox').filter('[id^="menu"]').each(function(){
				this.checked = true;
			});
		}else{
			$('input:checkbox').filter('[id^="menu"]').each(function(){
				this.checked = false;
			});
		}
		
	});
	
	    $(".fczk").bind("click",function(){
			var __self = $(this);
		  var __src = $(__self).attr("src");
		  if(__src.indexOf("fc_hb") >= 0){
			  __self.attr("src", __src.replace("fc_hb","fc_zk"));
			  __self.siblings("ul").show();
		  }else{
			  __self.attr("src", __src.replace("fc_zk","fc_hb"));
			  __self.siblings("ul").hide();
		  }
	});
	    
	    function temp(){
	    	var __self = $(this);
	    	  var __src = $(__self).attr("src");
	    	  if(__src.indexOf("fc_hb") >= 0){
	    		  __self.attr("src", __src.replace("fc_hb","fc_zk"));
	    		  __self.siblings("ul").show();
	    	  }else{
	    		  __self.attr("src", __src.replace("fc_zk","fc_hb"));
	    		  __self.siblings("ul").hide();
	    	  }
	    }
	    
	    $.ajax({
		    type:"post",
			url : '${rc.contextPath}/roleMenu/queryRole', 
			dataType : "json",
			success : function(list) {
			$("#roleList").html("") ;
			var str = '';
			for(var i = 0; i <list.length;i++){
// 		                 str += '<li><span><input type="checkbox" onclick="vali();" id="role" name="role" value='+list[i]["id"]+'>'+list[i]["rolename"]+'</span></li>';
		                 str +='<li><span><input type="checkbox" onclick="vali(this);" id="role" name="role" value='+list[i]["id"]+'>'+list[i]["rolename"]+'</span></li>';
				}
			$(str).appendTo($("#roleList"));
			},
			error:function(a,b,c){
				alert("加载角色出错！");
			}
	    });
	    
	  
	    $.ajax({
		    type:"post",
			url : '${rc.contextPath}/roleMenu/queryMenu', 
			dataType : "json",
			success : function(list) {
			$("#menuList").html("") ;
			var str = '';
			var login ='';
				for(var k = 0;k<list.length;k++){
					 if(list[k]["url"] == "/login/successLogin"){
						 login = '<li><img src="${rc.contextPath}/images/fc_zk.jpg"  class="child_fczk"/><input id="menu" name="menu"  type="checkbox"  value='+list[k]["id"]+'><span>'+list[k]["name"]+'</span>';
					 }
				}
			for(var i = 0; i <list.length;i++){
				if(list[i]["parentId"] == 0){
					 str +='<li><img src="${rc.contextPath}/images/fc_zk.jpg" onclick="child_fczk(this);" class="child_fczk"/><input id="menu'+list[i]["id"]+'1" name="menu'+list[i]["id"]+'3" onclick="ctwo(this);" type="checkbox" value='+list[i]["id"]+'><span>'+list[i]["name"]+'</span><ul>';
					 for(var j= 0; j<list.length;j++){
						 if(list[j]["parentId"] == list[i]["id"]){
					     str += '<li><img src="${rc.contextPath}/images/fc_zk.jpg" onclick="child_fczk(this);" class="child_fczk"/><input id="menu'+list[i]["id"]+'2" name="menu'+list[j]["id"]+'" onclick="ctwo(this);" type="checkbox" value='+list[j]["id"]+'><span>'+list[j]["name"]+'</span><ul>';
					     for(var k= 0; k<list.length;k++){
 						 if(list[k]["parentId"] == list[j]["id"]){
 						 str += '<li><span><input type="checkbox"  id="menu'+list[i]["id"]+'3" name="menu'+list[j]["id"]+'" value='+list[k]["id"]+'>'+list[k]["name"]+'</span></li>';
 						 }
 						 }
					     str+= '</ul>';
						 }
						 }
					 str +='</ul></li>';
					 }
				 }
				    str+= login;
					
			$(str).appendTo($("#menuList"));
			},
			error:function(a,b,c){
				alert("出错了");
			}
	    });
  
});


function valiur(){
	var $checkObj = $('input:checkbox:checked').filter('[id="role"]');
// 	var $checkObj2 = $('input:checkbox:checked').filter('[id="menu"]');
	if($checkObj.length <= 0){
		alert("请选择一个角色");
		return false;
	}
	return true;
}

function vali(element){
	var $checkObj = $('input:checkbox:checked').filter('[id="role"]');
	var len = $checkObj.length;
	if(len > 0){
	if(len > 1){
		$checkObj.attr("checked",false);
	    element.checked = true;
	    $checkObj = $('input:checkbox:checked').filter('[id="role"]');
		updateRM($checkObj);
    }else{
    	updateRM($checkObj);
    }
    }
	 $('input:checkbox').filter('[id^="menu"]').attr("checked",false); 	
}

function updateRM($checkObj){
	$('input:checkbox:checked').filter('[id^="menu"]').attr("checked",false);
	   var data = $checkObj.serialize();
	    $.ajax({
			    type:"post",
				url : '${rc.contextPath}/roleMenu/queryMenuByRid', 
				data :data,
				dataType : "json",
				success : function(list) {
				var role = $("input[id^='menu']");
				for(var i = 0; i <list.length;i++){
					if(list[i] != null){
						 for(var j = 0; j < role.length;j++){
							 if(list[i]["id"] == role[j].value){
								 $('input:checkbox').filter('[id^="menu"]').eq(j).prop("checked", true);
							 }
						 }
						 }
						
				     }
		},
				error:function(a,b,c){
					alert("响应错误");
				}
				});
}

function confirt(){
	if(valiur()){
		var $role = $('input:checkbox:checked').filter('[id="role"]');
		var $menu = $('input:checkbox:checked').filter('[id^="menu"]');
		var temp = $menu.serialize();
		var tempsub='';
		var menu = '';
		if(temp.length > 0){
		var appendMenu = temp.split("&");
		for(var i = 0;i<appendMenu.length;i++){
			tempsub = appendMenu[i].split("=");
			menu += "menu=" + tempsub[1]+"&";
		}
		menu = menu.substring(0,menu.length-1);
		}else{
			menu = '';
		}
		var data =$role.serialize()+"&" +menu;
		 $.ajax({
			    type:"post",
				url : '${rc.contextPath}/roleMenu/updateRoleMenu', 
				data :data,
				dataType : "json",
				success : function(result) {
					$.messager.show({
						title : result[0]["status"],
						msg : result[0]["message"],
					});
				},
				error:function(){
					$.messager.show({
						title : result[0]["status"],
						msg : result[0]["message"],
					});
				}
				});
	}
	
}

function ctwo(element){
	var menu = element.id;
	var two = menu.substring(0,menu.length-1)+"2";
	var three = menu.substring(0,menu.length-1)+"3";
	var end = menu.substring(menu.length-1,menu.length);
	if(element.checked){
         if(end == 1){
        	 $('input:checkbox').filter('[id="'+two+'"]').each(function(){
     			this.checked = true;
     		});		
        	 $('input:checkbox').filter('[id="'+three+'"]').each(function(){
     			this.checked = true;
     		});		
        	 
         }
         if(end = 2){
        	 $('input:checkbox').filter('[name="'+element.name+'"]').each(function(){
      			this.checked = true;
      		});	
         }
	}else{
		 if(end == 1){
        	 $('input:checkbox').filter('[id="'+two+'"]').each(function(){
     			this.checked = false;
     		});		
        	 $('input:checkbox').filter('[id="'+three+'"]').each(function(){
     			this.checked = false;
     		});		
        	 
         }
         if(end = 2){
        	 $('input:checkbox').filter('[name="'+element.name+'"]').each(function(){
      			this.checked = false;
      		});	
         }
	}
	
}
function child_fczk(element){
	var __self = $(element);
	  var __src = $(__self).attr("src");
	  if(__src.indexOf("fc_hb") >= 0){
		  __self.attr("src", __src.replace("fc_hb","fc_zk"));
		  __self.siblings("ul").show();
	  }else{
		  __self.attr("src", __src.replace("fc_zk","fc_hb"));
		  __self.siblings("ul").hide();
	  }
}
</script>
<style type="text/css">
.menu{
  margin-left: 20px;
  width: 260px;
  height: 340px;
  overflow: scroll;
  }
	ul,li{list-style:none;}
	li{margin-top:3px;}
	.tree{width:100%;  padding: 20px 10px; height: 88%;}
	.menu > li > img + input[type=checkbox]{margin-left:10px;}
	.tree .menu > li > ul{margin-left:30px;}
	.tree .menu > li > ul > li > ul{margin-left:10px;}
	.tree .menu > li > ul > li > ul>li>span{margin-left:5px;}
	.tree .menu > li > ul > li > img{margin-left:-18px; position:absolute; margin-top: 5px;}
	.tree .menu > li > ul > li > img + input[type=checkbox]{margin-left:0px; margin-top: 5px;}
	.tree .menu > li > ul>li >ul{margin-left:3px;}
	.tree .menu > li > ul > li > ul>li{margin-left:10px;}
	.tree .menu > li > ul > li > ul>li>ul>li>span{margin-left:20px;}
	.tree .menu > li > ul > li > ul>li> img{margin-left:-18px; position:absolute; margin-top: 5px;}
	.tree .menu > li > ul > li > ul>li>img + input[type=checkbox]{margin-left:0px; margin-top: 5px;}
	input[type=checkbox]{margin-right:5px; margin-left: 5px; margin-top: 2px;}
  span {
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #999;
	border-radius: 5px;
	display: inline-block;
	padding: 3px 8px;
	margin-left:3px;
	text-decoration: none
}

#tabInfo {
	width: 100%;
	height：100%;
	margin-top: 10px;
}

#tabInfoL {
	width: 43%;
	height: 100%;
	border: 1px solid #8A2BE2;
	float: left;
	padding: 2px;
	margin: 2px;
}

#tabInfoR {
	width: 43%;
	height: 100%;
	border: 1px solid #8A2BE2;
	float: right;
	padding: 2px;
	margin: 2px;
}

</style>
</head>

<body>
<body>

<div id="tabInfo">

		<div id="tabInfoL">
<div class="easyui-panel" title="角色名称" style="width:100%;height:100%;">
        
        
<div class="tree">
	<ul class="menu">
    	<li>
        	 <img src="${rc.contextPath}/images/fc_zk.jpg" class="fczk"/><span><input id="role" name="role" type="checkbox"  disabled="disabled" />角色名称</span>
            <ul id="roleList">
<!--             	<li><span><input type="checkbox"/>Chlid Element1</span></li> --> 
                      
<!--                 <li><span><input type="checkbox"/>Chlid Element2</span></li> -->
<!--                 <li><span><input type="checkbox"/>Chlid Element3</span></li> -->
            </ul>
        </li>              
       
    </ul>
</div>

        
	</div>
</div>
		<div id="tabInfoR">
		<div class="easyui-panel" title="菜单名" style="width:100%;height:100%;">


<div class="tree">
	<ul class="menu">
    	<li>
        	 <img src="${rc.contextPath}/images/fc_zk.jpg" class="fczk"/><span><input id="totalMenu" name="totalMenu" type="checkbox"/>菜单名称</span>
            <ul id="menuList">
<%--             	<li><img src="${rc.contextPath}/images/fc_zk.jpg" class="child_fczk"/><input type="checkbox"/><span>Chlid Element1</span> --%>
<!--                 	<ul> -->
<!--                     <li><span><input type="checkbox"/>Chlid Element2</span></li> -->
<!--                      <li><span><input type="checkbox"/>Chlid Element3</span></li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!--                 <li><span><input type="checkbox"/>Chlid Element2</span></li> -->
<!--                 <li><span><input type="checkbox"/>Chlid Element3</span></li> -->
            </ul>
        </li>
    </ul>
</div>





	</div>
		</div>
		
		
		
		</div>
		<div style="margin-left: auto; margin-right: auto; margin-top: 100px;" >
		<a href="javascript:;" onclick="confirt();"><img title="点击提交" src="${rc.contextPath}/images/arrows.png" width="130" height="130" /></a>
		</div>
  </body>
  
</html>

