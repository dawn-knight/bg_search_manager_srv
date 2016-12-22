<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/MBgoSearchManagerSrv/css/login.css" rel="stylesheet">
<title>邦购搜索引擎管理后台</title>
<script type="text/javascript">
if (window != top){
	top.location.href = location.href; 
}
function check(){
	var logincode = document.getElementById("j_username").value;
	var password = document.getElementById("j_password").value;
	var error = document.getElementById("error");
	error.innerHTML="";
	if(logincode==""){
		error.innerHTML="用户名不能为空！";
		return false;
	}
	if(password==""){
		error.innerHTML="密码不能为空！";
		return false;
	}
	return true;
}


</script>

</head>

<body>
<div>
<table width="100%" height="100%" class="main" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
      <div class="login">
        <div class="top">
          <div class="logo"></div>
          <div class="lable"></div>
        </div>
        <table width="466" cellpadding="0" cellspacing="0">
          <tr>
            <td style="padding-top:30px;">
		    <form action="j_spring_security_check" method="post">
              <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="right" height="27">用户名:</td>
                  <td align="right" width="161">
                    <input type="text" id="j_username" name="j_username"  style="width:150px;height:20px" />
                  </td>
                  <td align="center" height="29">
                    <input name="submit" type="submit" value=""  class="submit" />
                  </td>
                </tr>
                <tr>
                  <td align="right" height="27">密&nbsp;&nbsp;码:</td>
                  <td align="right" width="161">
                    <input type="password" id="j_password" name="j_password" style="width:150px;height:20px" />
                  </td>
                  <td align="center" height="29">
                    <input name="reset" type="reset" value="" class="reset" />
                  </td>
                </tr>
              </table>
	        </form>
            </td>
          </tr>
        </table>
        <table width="100%" cellpadding="0" cellspacing="0" style="margin-top:8px;">
          <tr>
          	<td align="center" style="height:25"><font id="error" color="red" style="visibility: ${param.error == true ? 'visible' : 'hidden'};">
											账号或密码错误
									</font></td>
          </tr>
          <tr>
            <td align="center">MB 2014</td>
          </tr>
        </table>
      </div>
      <!--login -->
    </td>
  </tr>
</table>
</div>
</body>
</html>