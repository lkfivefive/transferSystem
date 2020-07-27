<%@ page language="java" contentType="text/html; charset=GB18030"
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
	function reH(){
		window.open('show_aesencrypt.html', 'AES加密流程图',"menubar=no,height=450,width=430,left=400,top=100");
	}
</script>
</head>
<body>
<div>
<h2  align="center">转账业务</h2>
<hr>
</div>
<br>
<form name="f7" method="post" action="transfer.action">
<table class="box-table-b" align="center">
<tr>
<th>收款账号：</th><td><input type="text" name="binno"></td>
</tr>
<tr>
<th>收款人姓名：</th><td><input type="text" name="bname"></td>
</tr>
<tr>
<th>转账金额：</th><td><input type="text" name="bmoney"></td>
</tr>
<tr>
<th>备注：</th><td><input type="text" name="bnote" placeholder="选填"></td>
</tr>
</table>
<p align="center"><input type="submit" value="提交">
<input type="reset" value="重置">
<a href="welcome.jsp"><input type="button" value="返回"></input></a>
</form>
<hr>
<div style="border:20px solid #ffffff">
<h2 align="center" style="color:#616161;">AES加密详情</h2>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">填写账单信息并提交后，系统根据所填写信息判断是否合理，主要有以下：</p>
<p style="text-indent: 4em;font-family:宋体;font-weight:bold;">1.转账金额是否为负数以及是否为非数字</p>
<p style="text-indent: 4em;font-family:宋体;font-weight:bold;">2.付款账户余额是否满足转账的金额</p>
<p style="text-indent: 4em;font-family:宋体;font-weight:bold;">3.收款账号是否与收款人姓名相匹配</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">账单信息验证无误后，系统分别将其String类型的信息每16字节截断转换成4*4的字节矩阵（不足16字节进行填补）的预处理作为加密处理的输入，读取账户经过MD5加密处理的128bit账户密码作为决定AES加密密钥的种子，将这128bit经过SecureRandom生成安全随机数序列作为密钥，AES加密处理后输出4*4字节矩阵转换为String类型密文储存到MySQL数据库中，从而实现了账单信息的加密储存。</p>
<p style="font-family:宋体;font-weight:bold;font-size:18px;">AES加密原理流程如下：</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">AES是一个迭代的，对称密钥分组的密码，它以字节为基础，算法由轮密钥开始，并用Nr表示对一个数据分组的轮数。AES算法的主循环State矩阵执行Nr-1轮迭代运算，每轮都包括所有4个阶段的代换，分别被称为SubBytes（字节代换），ShiftRows（行移位变换），MixColumns（列混合变换）和AddRoundKey（轮密钥加）。最后执行只包括3个阶段的最后一轮变换。</p>
</div>
<div align="right" style="border:20px solid #ffffff">
<a href="javaScript:reH()">点击查看AES加密流程>></a><br>
<a href="https://blog.csdn.net/gulang03/article/details/81175854">AES加密算法原理详解>></a>
</div>
</body>
</html>