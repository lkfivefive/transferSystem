<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<% 
   java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(  
     "yyyy-MM-dd HH:mm:ss");  
   java.util.Date currentTime = new java.util.Date();  
   String time = simpleDateFormat.format(currentTime).toString();  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>转账系统</title>
<style type="text/css">
a{
	text-decoration:none;
	text-shadow:#8cb1ff 3px 4px 5px; 
	font-family : 宋体,sans-serif;
	font-size : 12pt; 
	text-align:right;
	color:#09057e;
}
</style>
<link rel="stylesheet" type="text/css" href="css/lk.css">
<script type="text/javascript">
	function reP(){
		window.open('show_systemimage.html', '系统运行流程图',"menubar=no,height=420,width=630,left=200,top=100");
	}
</script>
</head>
<body>
<div style="border:5px solid #ffffff">
<table>
<tr>
<td width=20%></td><td width=40% style="text-align:center;font-weight:bold;font-size:15pt;color:#09057e">您好，<%= request.getSession().getAttribute("accountname") %>！欢迎使用转账系统！</td><td width=40% style="text-align:right;font-weight:normal;font-size:12pt;color:#09057e"> 本次登录时间：<%=time %></td>
</tr>
</table>
</div>
<div style="border:20px solid #ffffff">
<h2 align="center">系统概述</h2>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">随着我国的信息技术迅猛发展，国内互联网的发展和普及，网络的使用无处不在，与人们的生活息息相关，支付方式日新月异，转账等网上交易业务方便、快捷、安全、可靠、实时和易操作等技术成为热点。</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">本系统研究转账服务的类型、过程及安全方法以及转账安全算法的研究和实现，主要针对于信息存储保密的安全这一方面，研究MD5和AES的算法原理及工作流程，通过MD5加密算法和AES加密解密算法的结合，保证信息加密存储，使得即使数据库受到攻击导致信息外泄，加密过后的信息也难以被破解，保障用户的信息安全。</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">本系统通过Eclipse进行设计和编程，基于MVC模式，连接MySQL数据库进行数据的存储，并对各web页面进行设计与链接，实现前端与后端分离，通过中间的servlet组件起到控制器的作用，将前端输入信息数据作为参数传递到后端进行处理，将处理完的信息返回至前端进行显示，主要功能有查询余额，查看和修改账户信息，办理转账，查看历史账单，修改密码等。</p>
</div>
<div align="right" style="border:20px solid #ffffff">
	<a href="javaScript:reP()">点击查看具体流程>></a>
</div>
</body>
</html>