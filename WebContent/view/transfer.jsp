<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>ת��ϵͳ</title>
<style type="text/css">
a{
	text-decoration:none;
	text-shadow:#8cb1ff 3px 4px 5px; 
	font-family : ����,sans-serif;
	font-size : 12pt; 
	text-align:right;
	color:#09057e;
}
</style>
<link rel="stylesheet" type="text/css" href="css/lk.css">
<script type="text/javascript">
	function reH(){
		window.open('show_aesencrypt.html', 'AES��������ͼ',"menubar=no,height=450,width=430,left=400,top=100");
	}
</script>
</head>
<body>
<div>
<h2  align="center">ת��ҵ��</h2>
<hr>
</div>
<br>
<form name="f7" method="post" action="transfer.action">
<table class="box-table-b" align="center">
<tr>
<th>�տ��˺ţ�</th><td><input type="text" name="binno"></td>
</tr>
<tr>
<th>�տ���������</th><td><input type="text" name="bname"></td>
</tr>
<tr>
<th>ת�˽�</th><td><input type="text" name="bmoney"></td>
</tr>
<tr>
<th>��ע��</th><td><input type="text" name="bnote" placeholder="ѡ��"></td>
</tr>
</table>
<p align="center"><input type="submit" value="�ύ">
<input type="reset" value="����">
<a href="welcome.jsp"><input type="button" value="����"></input></a>
</form>
<hr>
<div style="border:20px solid #ffffff">
<h2 align="center" style="color:#616161;">AES��������</h2>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">��д�˵���Ϣ���ύ��ϵͳ��������д��Ϣ�ж��Ƿ������Ҫ�����£�</p>
<p style="text-indent: 4em;font-family:����;font-weight:bold;">1.ת�˽���Ƿ�Ϊ�����Լ��Ƿ�Ϊ������</p>
<p style="text-indent: 4em;font-family:����;font-weight:bold;">2.�����˻�����Ƿ�����ת�˵Ľ��</p>
<p style="text-indent: 4em;font-family:����;font-weight:bold;">3.�տ��˺��Ƿ����տ���������ƥ��</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">�˵���Ϣ��֤�����ϵͳ�ֱ���String���͵���Ϣÿ16�ֽڽض�ת����4*4���ֽھ��󣨲���16�ֽڽ��������Ԥ������Ϊ���ܴ�������룬��ȡ�˻�����MD5���ܴ����128bit�˻�������Ϊ����AES������Կ�����ӣ�����128bit����SecureRandom���ɰ�ȫ�����������Ϊ��Կ��AES���ܴ�������4*4�ֽھ���ת��ΪString�������Ĵ��浽MySQL���ݿ��У��Ӷ�ʵ�����˵���Ϣ�ļ��ܴ��档</p>
<p style="font-family:����;font-weight:bold;font-size:18px;">AES����ԭ���������£�</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">AES��һ�������ģ��Գ���Կ��������룬�����ֽ�Ϊ�������㷨������Կ��ʼ������Nr��ʾ��һ�����ݷ����������AES�㷨����ѭ��State����ִ��Nr-1�ֵ������㣬ÿ�ֶ���������4���׶εĴ������ֱ𱻳�ΪSubBytes���ֽڴ�������ShiftRows������λ�任����MixColumns���л�ϱ任����AddRoundKey������Կ�ӣ������ִ��ֻ����3���׶ε����һ�ֱ任��</p>
</div>
<div align="right" style="border:20px solid #ffffff">
<a href="javaScript:reH()">����鿴AES��������>></a><br>
<a href="https://blog.csdn.net/gulang03/article/details/81175854">AES�����㷨ԭ�����>></a>
</div>
</body>
</html>