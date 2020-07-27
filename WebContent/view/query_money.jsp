<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>转账系统</title>
<style type="text/css">
</style> 
</head>
<body>
<div>
<h2  align="center" >账户余额</h2>
<hr>
</div>
<br>
<form name="f3" method="post" action="query_money.action">
<table align="center">
<tr>
<td style="font-size:25px"> <h4><%= request.getAttribute("money") %></h4></td><td>元</td>
</tr>
</table>
</form>
</body>
</html>