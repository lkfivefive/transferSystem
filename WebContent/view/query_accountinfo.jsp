<%@ page language="java" contentType="text/html; charset=GB18030"
	import="java.util.*,model.AccountInfo"
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
	function reM(){
		window.open('show_md5encrypt.html', 'MD5��������ͼ',"menubar=no,height=550,width=450,left=400,top=50");
	}
</script>
</head>
<body>
<div>
<h2  align="center">�˻���Ϣ</h2>
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
<th>�˺�</th><td><%=ai.getAno() %></td>
</tr>
<tr>
<th>����(�Ѽ���)</th><td><%=ai.getApwd() %></td>
</tr>
<tr>
<th>����</th><td><%=ai.getAname() %></td>
</tr>
<tr>
<th>��ݺ���</th><td><%=ai.getaId() %></td>
</tr>
<tr>
<th>�Ա�</th><td><%=ai.getAsex() %></td>
</tr>
<tr>
<th>�绰</th><td><%=ai.getAtel() %></td>
</tr>
<tr>
<th>����</th><td><%=ai.getAmail() %></td>
</tr>
<tr>
<th>��ַ</th><td><%=ai.getAddress() %></td>
</tr>
<%i++;}%>
</table>
<p align="center">
<a href="edit_accountinfo.jsp"><input type="button" value="�޸��˻���Ϣ"></input></a>
</p>
</form>
</div>
<hr>
<div style="border:20px solid #ffffff">
<h2 align="center" style="color:#616161;">MD5��������</h2>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">���û�����һ�����˺�ʱ�����벻ֱ�ӱ��浽���ݿ��У����Ǿ���һ��MD5���ܺ��ٱ��档��������ʱ��Ϣ��й¶��Ҳ�������������Щ��Ϣ���������塣���û���¼ʱϵͳ�Ƚ����ݿ��о������ܵ��������û�������������Ƿ�һ�¼�����֤����ݡ�</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">MD5�����㷨����ϣ�㷨һ�࣬���в������ԡ�MD5�������ⳤ�ȵ��ַ���ӳ���128bit�Ĵ����������Ҿ���MD5���ܺ��128bit���ķ�������Ŀǰ�����ϻ���̫����ʵ�֣�����֪���˼����㷨��ϸ�ں�������Ҳ�޷���һ��MD5���Ļ�ԭ�����ġ�</p>
<p style="font-family:����;font-weight:bold;font-size:18px;">MD5����ԭ���������£�</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">��MD5�����㷨�У����ȶ���Ҫ���ܵ���Ϣ������䣬ʹ��Ϣ���ֽڳ�������(N+1)*512�����У���4��32bit���������ӱ�������������A��B��C��D�����ĸ����ӱ�����ֵ��ֵ��a��b��c��d���˺󣬿�ʼ�����㷨������ѭ�����㣬ѭ���Ĵ���ΪN+1����ѭ�������֣�ÿ����16�β�����ÿ�β�����a��b��c��d�е������������Ժ������㣬�ٽ�������ϵ��ĸ�������512bit���ĵ�ĳ��32bit�����һ���������ٽ����ý�����һ���һ�������������ټ���a��b��c��d��֮һ�����˽����ֵ��a��b��c��d��֮һ��</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">������ѭ�������󣬽�A��B��C��D�ֱ����a��b��c��d��������һ��512bit���ļ������и��㷨��ֱ������������A��B��C��D�ļ����������������Ķ೤���������Ķ���128bit��</p>
</div>
<div align="right" style="border:20px solid #ffffff">
<a href="javaScript:reM()">����鿴MD5��������>></a><br>
<a href="http://blog.chinaunix.net/uid-26000296-id-5757681.html">MD5�����㷨ԭ�����>></a>
</div>
</body>
</html>