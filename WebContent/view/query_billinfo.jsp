<%@ page language="java" contentType="text/html; charset=GB18030"
	import="java.util.*,model.BillInfo"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>ת��ϵͳ</title>
<style type="text/css">
.class1{
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
	function reD(){
		window.open('show_aesdecrypt.html', 'AES��������ͼ',"menubar=no,height=450,width=430,left=400,top=100");
	}
</script>
</head>
<body>
<%!    
    public static final int PAGESIZE = 5;  
    int pageCount;  
    int curPage = 1;  
%>  
<div>
<h2  align="center">��ʷ�˵�</h2>
<hr>
</div>
<div style="height:222px">
<form  name="f5" method="post" action="query_billinfo.action">
<table class="box-table-a" align="center">
<thead>
       <tr>
           <th scope="col">�˵����</th>
           <th scope="col">�����˺�</th>
           <th scope="col">�տ��˺�</th>
           <th scope="col">�տ�������</th>
           <th scope="col">ת�˽��</th>
           <th scope="col">ת������</th>
           <th scope="col">��ע</th>
       </tr>
</thead>
<%
ArrayList<BillInfo> billlist = (ArrayList<BillInfo>)request.getAttribute("billlist");
int size=billlist.size();
pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1); 
String tmp = request.getParameter("curPage");  
if(tmp==null || "0".equals(tmp)){  
    tmp="1";  
}  
curPage = Integer.parseInt(tmp);  
if(curPage>=pageCount) curPage = pageCount;  

for(int i=(curPage-1)*PAGESIZE;i<curPage*PAGESIZE && i<size;i++){
	BillInfo bi=billlist.get(i);
%>
<tbody>
	   <tr>
           <td><%=bi.getBno() %></td>
           <td><%=bi.getBoutno() %></td>
           <td><%=bi.getBinno() %></td>
           <td><%=bi.getBname() %></td>
           <td><%=bi.getBmoney() %></td>
           <td><%=bi.getBdate() %></td>
           <td><div style="width:400px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;" title=<%=bi.getBnote() %>><%=bi.getBnote() %></div></td>
       </tr>
</tbody>
<%}%>
</table>
</div>
<div align="center">
<a href = "query_billinfo.action?curPage=1" >��ҳ</a>&nbsp;&nbsp;&nbsp;  
<a href = "query_billinfo.action?curPage=<%=curPage-1%>" >��һҳ</a>&nbsp;&nbsp;&nbsp;  
<a href = "query_billinfo.action?curPage=<%=curPage+1%>" >��һҳ</a>&nbsp;&nbsp;&nbsp;  
<a href = "query_billinfo.action?curPage=<%=pageCount%>" >βҳ</a>&nbsp;&nbsp;&nbsp;  
��&nbsp;<%=curPage%>&nbsp;ҳ&nbsp;/&nbsp;��&nbsp;<%=pageCount%>&nbsp;ҳ  
</div>
</form>
<hr>
<div style="border:20px solid #ffffff">
<h2 align="center" style="color:#616161;">AES��������</h2>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">ϵͳ��ȡ�˻�����MD5���ܴ����128bit�˻�������Ϊ����AES������Կ�����ӣ�����128bit����SecureRandom���ɰ�ȫ�����������Ϊ��Կ,��ϵͳ�˺ž���AES���ܺ��������Ϊ��ƥ��ĸ����˺��ַ�����MySQL���ݿ��н��в�ѯ��</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">����ѯ��Ľ�����ֱ����AES���ܣ�ͬ������String���͵���Ϣÿ16�ֽڽض�ת����4*4���ֽھ����Ԥ������Ϊ���ܴ�������룬����AES�ǶԳ���Կ��������룬������Կ��Ϊ������Կ�����ܴ�������4*4�ֽھ���ת��ΪString�������Ĵ�����ArrayList������ǰ�ˣ�ͨ����д����������ʾ���û����Ӷ�ʵ���������Ļ��Լ��������˵���Ϣ�İ�ȫ�洢��</p>
<p style="font-family:����;font-weight:bold;font-size:18px;">AES����ԭ���������£�</p>
<p style="text-indent: 2em;font-family:����;font-weight:bold;">����AESΪ�ԳƼ����㷨��AES�ļ�����Կ�ͽ�����Կһ�£�AES���ܹ��̼�Ϊ���ܹ��̵�����̡�AES�����㷨�����һ����Կ��ʼ����ѭ��State����ִ��Nr-1�ֵ������㣬ÿ�ֶ���������4���׶εĴ������ֱ𱻳�Ϊinv_SubBytes�����ֽڴ�������inv_ShiftRows��������λ�任����inv_MixColumns�����л�ϱ任����AddRoundKey������Կ�ӣ������ִ��ֻ����3���׶ε����һ�ֱ任��</p>
</div>
<div align="right" style="border:20px solid #ffffff">
<a class="class1" href="javaScript:reD()">����鿴AES��������>></a><br>
<a class="class1" href="https://blog.csdn.net/gulang03/article/details/81175854">AES�����㷨ԭ�����>></a>
</div>
</body>
</html>