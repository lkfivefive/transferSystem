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
	function reP(){
		window.open('show_systemimage.html', 'ϵͳ��������ͼ',"menubar=no,height=420,width=630,left=200,top=100");
	}
</script>
</head>
<body>
<div style="border:5px solid #ffffff">
<table>
<tr>
<td width=20%></td><td width=40% style="text-align:center;font-weight:bold;font-size:15pt;color:#09057e">���ã�<%= request.getSession().getAttribute("accountname") %>����ӭʹ��ת��ϵͳ��</td><td width=40% style="text-align:right;font-weight:normal;font-size:12pt;color:#09057e"> ���ε�¼ʱ�䣺<%=time %></td>
</tr>
</table>
</div>
<div style="border:20px solid #ffffff">
<h2 align="center">ϵͳ����</h2>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">�����ҹ�����Ϣ����Ѹ�ͷ�չ�����ڻ������ķ�չ���ռ��������ʹ���޴����ڣ������ǵ�����ϢϢ��أ�֧����ʽ�������죬ת�˵����Ͻ���ҵ�񷽱㡢��ݡ���ȫ���ɿ���ʵʱ���ײ����ȼ�����Ϊ�ȵ㡣</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">��ϵͳ�о�ת�˷�������͡����̼���ȫ�����Լ�ת�˰�ȫ�㷨���о���ʵ�֣���Ҫ�������Ϣ�洢���ܵİ�ȫ��һ���棬�о�MD5��AES���㷨ԭ���������̣�ͨ��MD5�����㷨��AES���ܽ����㷨�Ľ�ϣ���֤��Ϣ���ܴ洢��ʹ�ü�ʹ���ݿ��ܵ�����������Ϣ��й�����ܹ������ϢҲ���Ա��ƽ⣬�����û�����Ϣ��ȫ��</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">��ϵͳͨ��Eclipse������ƺͱ�̣�����MVCģʽ������MySQL���ݿ�������ݵĴ洢�����Ը�webҳ�������������ӣ�ʵ��ǰ�����˷��룬ͨ���м��servlet����𵽿����������ã���ǰ��������Ϣ������Ϊ�������ݵ���˽��д��������������Ϣ������ǰ�˽�����ʾ����Ҫ�����в�ѯ���鿴���޸��˻���Ϣ������ת�ˣ��鿴��ʷ�˵����޸�����ȡ�</p>
</div>
<div align="right" style="border:20px solid #ffffff">
	<a href="javaScript:reP()">����鿴��������>></a>
</div>
</body>
</html>