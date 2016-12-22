<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Session过期</title>
<script language="javascript">
if (window != top){
	top.location.href = location.href; 
}
var times=5; //时间,秒
function diplaytime(){ //时间递减
document.getElementById("time").innerHTML =times;
times=times-1;
}
function redirect(){ //跳转页
window.location.href="/MBgoSearchManagerSrv/login.jsp";//指定要跳转到的目标页面
}
timer=setInterval('diplaytime()', 1000);//显示时间
timer=setTimeout('redirect()',times * 1000); //跳转

</script>
<STYLE type=text/css>
TD {
	FONT-SIZE: 9pt; FONT-FAMILY: "Arial", "Helvetica", "sans-serif"
}
BODY {
	FONT-SIZE: 9pt; FONT-FAMILY: "Arial", "Helvetica", "sans-serif"
}
.tbl1 {
	BORDER-RIGHT: #3f5294 1px solid; BORDER-TOP: #3f5294 1px solid; FONT-SIZE: 9pt; BORDER-LEFT: #3f5294 1px solid; BORDER-BOTTOM: #3f5294 1px solid
}
.td1 {
	BORDER-RIGHT: #ffffff 0px solid; BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #ffffff 0px solid
}
#sty {
height: 382px;
cellspacing : 0;
 cellpadding : 0;
 width : 470px; 
 margin-left: auto;
 margin-right: auto;
}
</STYLE>

<STYLE type=text/css>A {
	COLOR: #000000; TEXT-DECORATION: none
}
A:hover {
	COLOR: #ff0000; TEXT-DECORATION: none
}
</STYLE>

<STYLE type=text/css>.style6 {
	FONT-FAMILY: "Courier New", Courier, mono
}
</STYLE>


</head>

<body>
<table id="sty">
  <tbody>
  <tr>
    <td valign=top background="/MBgoSearchManagerSrv/images/bg.gif"><br>
      <table style="width: 100%;border: 0;">
        <tbody>
        <tr>
          <td width="14%">&nbsp;</td>
          <td width="86%">
            <table style="filter: glow(color=#ffffff, strength=5)" width="100%" align="center">
              <tbody>
              <tr>
                <td align="center" height="14px;"><span class=style6><font 
                  color=#ff0000 
          size=6>Empired!</font></span></td></tr></tbody></table></td></tr>
        <tr>
          <td>&nbsp;</td>
          
          <td>
									<table style="width: 100%;border: 0;cellspacing:2; width:100%"  align="center">
										<tbody>
											<tr>
												<td align="center" style="font-size: 18px">你的页面由于长时间未操作已过期</td>
											</tr>
											<tr>
												<td></td>
											</tr>
											<tr>
												<td></td>
											</tr>
											<tr>
												<td></td>
											</tr>
											<tr>
												<td align="center" style="font-size: 18px;"><span
													id="time" style="color: red;">5</span>秒之后跳转到登录页面，</td>
											</tr>
										</tbody>
									</table>
									<table style="width: 98%">
											<tbody>
												<tr>
													<td width="38%">&nbsp;</td>
													<td width="17%">
														<div align=right>
															<a href="#"><img height=38
																src="/MBgoSearchManagerSrv/images/jt.gif" width=56 border=0></a>
														</div>
													</td>
													<td width="45%">
														<div align=center>
															<a href="/MBgoSearchManagerSrv/login.jsp"><font
																color=#ff0000>[点击返回登录页面]</font></a>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
            </tbody></table>
            
            </td>
            </tr>
            </tbody>
            </table>

</body>
</html>
