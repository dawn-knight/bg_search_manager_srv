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

	$('input:checkbox').filter('[name="roleTotal"]').click(function(){
		
		if(this.checked){
			$('input:checkbox').filter('[name="role"]').each(function(){
				this.checked = true;
			});
		}else{
			$('input:checkbox').filter('[name="role"]').each(function(){
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
	    $.ajax({
		    type:"post",
			url : '${rc.contextPath}/userRole/queryUser', 
			dataType : "json",
			success : function(list) {
			$("#userList").html("") ;
			var str = '';
			for(var i = 0; i <list.length;i++){
// 		                 str += '<li><span><input type="checkbox" onclick="vali();" id="user" name="user" value='+list[i]["id"]+'>'+list[i]["username"]+'</span></li>';
		                 str +='<li><span><input type="checkbox" onclick="vali(this);" id="user" name="user" value='+list[i]["id"]+'>'+list[i]["username"]+'</span></li>';
				}
			$(str).appendTo($("#userList"));
			},
			error:function(a,b,c){
				alert("出错了");
			}
	    });
	    $.ajax({
		    type:"post",
			url : '${rc.contextPath}/userRole/queryRole', 
			dataType : "json",
			success : function(list) {
			$("#roleList").html("") ;
			var str = '';
			for(var i = 0; i <list.length;i++){
                        //str += '<li><span><input type="checkbox" id="role" name="role" value='+list[i]["id"]+'>'+list[i]["rolename"]+'</span></li>';
                         str +='<li><span><input type="checkbox" id="role" name="role" value='+list[i]["id"]+'>'+list[i]["rolename"]+'</span></li>';
				}
			$(str).appendTo($("#roleList"));
			},
			error:function(a,b,c){
				alert("出错了");
			}
	    });
	    
	    
});


function valiur(){
	var $checkObj = $('input:checkbox:checked').filter('[id="user"]');
// 	var $checkObj2 = $('input:checkbox:checked').filter('[id="role"]');
	if($checkObj.length <= 0){
		alert("请选择一个用户");
		return false;
	}
// 	if($checkObj2.length <= 0){
// 		alert("您至少选择一个角色");
// 		return false;
// 	}
	return true;
}


function confirt(){
	if(valiur()){
		var $user = $('input:checkbox:checked').filter('[id="user"]');
		var $role = $('input:checkbox:checked').filter('[id="role"]');
		var data =$user.serialize()+"&" +$role.serialize();
		 $.ajax({
			    type:"post",
				url : '${rc.contextPath}/userRole/updateUserRole', 
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

function vali(element){
	var $checkObj = $('input:checkbox:checked').filter('[id="user"]');
	var len = $checkObj.length;
	if(len > 0){
	if(len > 1){
		$checkObj.attr("checked", false);
		 element.checked = true;
		 $checkObj = $('input:checkbox:checked').filter('[id="user"]');
		 updateUR($checkObj);
    }else{
    	updateUR($checkObj);
    }
    }else{
    	 $('input:checkbox').filter('[id="role"]').attr("checked",false); 	
    }
}

function updateUR($checkObj){
	  $('input:checkbox:checked').filter('[id="role"]').attr("checked",false); 	
	   var data = $checkObj.serialize();
	    $.ajax({
			    type:"post",
				url : '${rc.contextPath}/userRole/queryRoleByuid', 
				data :data,
				dataType : "json",
				success : function(list) {
				var role = $("input[id='role']");
				for(var i = 0; i <list.length;i++){
					if(list[i] != null){
						 for(var j = 0; j < role.length;j++){
							 if(list[i]["id"] == role[j].value){
								 $('input:checkbox').filter('[id="role"]').eq(j).prop("checked", true);
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
	.tree{width:91%;  padding: 20px 10px; height: 88%;}
	.menu > li > img + input[type=checkbox]{margin-left:10px;}
	.tree .menu > li > ul{margin-left:30px;}
	input[type=checkbox]{margin-right:5px;}
  span {
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #999;
	border-radius: 5px;
	display: inline-block;
	padding: 3px 8px;
	margin-left:5px;
	padding-bottom 0px;
	text-decoration: none
}

#tabInfo {
	width: 70%;
	margin-top: 10px;
}

#tabInfoL {
	width: 40%;
	height: 400px;
	border: 1px solid #8A2BE2;
	float: left;
	padding: 2px;
	margin: 2px;
}

#tabInfoR {
	width: 40%;
	height: 400px;
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
<div class="easyui-panel" title="用户名称" style="width:100%;height:100%;">
        
        

<div class="tree">
	<ul class="menu">
    	<li>
        	 <img src="${rc.contextPath}/images/fc_zk.jpg" class="fczk"/><span><input id="user" name ="user" disabled="disabled" type="checkbox"/>所有用户</span>
            <ul id="userList">
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
		<div class="easyui-panel" title="角色名" style="width:100%;height:100%;">



<div class="tree">
	<ul class="menu">
    	<li>
        	 <img src="${rc.contextPath}/images/fc_zk.jpg" class="fczk"/><span><input type="checkbox" id="roleTotal" name="roleTotal"/>所有角色</span>
            <ul id="roleList">
<!--             	<li><span><input id="role" name="role" type="checkbox"/>Chlid Element1</span></li> -->
<!--                 <li><span><input id="role" name="role" type="checkbox"/>Chlid Element2</span></li> -->
<!--                 <li><span><input id="role" name="role" type="checkbox"/>Chlid Element3</span></li> -->
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

