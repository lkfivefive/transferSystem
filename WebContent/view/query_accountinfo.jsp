<%@ page language="java" contentType="text/html; charset=GB18030"
	import="java.util.*,model.AccountInfo"
    pageEncoding="GB18030"%>
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
	function reM(){
		window.open('show_md5encrypt.html', 'MD5加密流程图',"menubar=no,height=550,width=450,left=400,top=50");
	}
</script>
</head>
<body>
<div>
<h2  align="center">账户信息</h2>
<hr>
</div>
<br>
<div align="center">
<form  name="f4" method="post" action="query_accountinfo.action">
<table align="center" class="gridtable">
<%
ArrayList<AccountInfo> accountlist = (ArrayList<AccountInfo>) request.getAttribute("accountlist");
int i=1;
for(AccountInfo ai:accountlist){
%>
<tr>
<th>账号</th><td><%=ai.getAno() %></td>
</tr>
<tr>
<th>密码(已加密)</th><td><%=ai.getApwd() %></td>
</tr>
<tr>
<th>姓名</th><td><%=ai.getAname() %></td>
</tr>
<tr>
<th>身份号码</th><td><%=ai.getaId() %></td>
</tr>
<tr>
<th>性别</th><td><%=ai.getAsex() %></td>
</tr>
<tr>
<th>电话</th><td><%=ai.getAtel() %></td>
</tr>
<tr>
<th>邮箱</th><td><%=ai.getAmail() %></td>
</tr>
<tr>
<th>地址</th><td><%=ai.getAddress() %></td>
</tr>
<%i++;}%>
</table>
<p align="center">
<a href="edit_accountinfo.jsp"><input type="button" value="修改账户信息"></input></a>
</p>
</form>
</div>
<hr>
<div style="border:20px solid #ffffff">
<h2 align="center" style="color:#616161;">MD5加密详情</h2>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">当用户创建一个新账号时，密码不直接保存到数据库中，而是经过一次MD5加密后再保存。这样，即时信息被泄露，也不能立即理解这些信息的真正含义。而用户登录时系统比较数据库中经过加密的数据与用户所输入的数据是否一致即可验证其身份。</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">MD5加密算法属哈希算法一类，具有不可逆性。MD5将将任意长度的字符串映射成128bit的大整数，并且经过MD5加密后的128bit密文反推明文目前技术上还不太可能实现，即便知晓了加密算法的细节和描述，也无法将一个MD5密文还原至明文。</p>
<p style="font-family:宋体;font-weight:bold;font-size:18px;">MD5加密原理流程如下：</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">在MD5加密算法中，首先对需要加密的信息进行填充，使信息的字节长度满足(N+1)*512。其中，有4个32bit被叫做链接变量的整数参数A，B，C，D，这四个链接变量初值赋值给a，b，c，d好了后，开始进行算法的四轮循环运算，循环的次数为N+1。主循环有四轮，每轮有16次操作，每次操作对a，b，c，d中的三个做非线性函数运算，再将结果加上第四个变量，512bit明文的某个32bit分组和一个常数。再将所得结果向右环移一个不定的数，再加上a，b，c，d中之一，将此结果赋值给a，b，c，d中之一。</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">在四轮循环结束后，将A，B，C，D分别加上a，b，c，d，再用下一组512bit明文继续运行该算法，直至最后输出的是A，B，C，D的级联，所以无论明文多长，所得密文都是128bit。</p>
</div>
<div align="right" style="border:20px solid #ffffff">
<a href="javaScript:reM()">点击查看MD5加密流程>></a><br>
<a href="http://blog.chinaunix.net/uid-26000296-id-5757681.html">MD5加密算法原理详解>></a>
</div>
</body>
</html>