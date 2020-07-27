<%@ page language="java" contentType="text/html; charset=GB18030"
	import="java.util.*,model.BillInfo"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>转账系统</title>
<style type="text/css">
.class1{
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
	function reD(){
		window.open('show_aesdecrypt.html', 'AES解密流程图',"menubar=no,height=450,width=430,left=400,top=100");
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
<h2  align="center">历史账单</h2>
<hr>
</div>
<div style="height:222px">
<form  name="f5" method="post" action="query_billinfo.action">
<table class="box-table-a" align="center">
<thead>
       <tr>
           <th scope="col">账单编号</th>
           <th scope="col">付款账号</th>
           <th scope="col">收款账号</th>
           <th scope="col">收款人姓名</th>
           <th scope="col">转账金额</th>
           <th scope="col">转账日期</th>
           <th scope="col">备注</th>
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
<a href = "query_billinfo.action?curPage=1" >首页</a>&nbsp;&nbsp;&nbsp;  
<a href = "query_billinfo.action?curPage=<%=curPage-1%>" >上一页</a>&nbsp;&nbsp;&nbsp;  
<a href = "query_billinfo.action?curPage=<%=curPage+1%>" >下一页</a>&nbsp;&nbsp;&nbsp;  
<a href = "query_billinfo.action?curPage=<%=pageCount%>" >尾页</a>&nbsp;&nbsp;&nbsp;  
第&nbsp;<%=curPage%>&nbsp;页&nbsp;/&nbsp;共&nbsp;<%=pageCount%>&nbsp;页  
</div>
</form>
<hr>
<div style="border:20px solid #ffffff">
<h2 align="center" style="color:#616161;">AES解密详情</h2>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">系统读取账户经过MD5加密处理的128bit账户密码作为决定AES加密密钥的种子，将这128bit经过SecureRandom生成安全随机数序列作为密钥,将系统账号经过AES加密后的密文作为待匹配的付款账号字符串在MySQL数据库中进行查询。</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">将查询后的结果集分别进行AES解密，同样将其String类型的信息每16字节截断转换成4*4的字节矩阵的预处理作为解密处理的输入，由于AES是对称密钥分组的密码，解密密钥即为加密密钥，解密处理后输出4*4字节矩阵转换为String类型明文储存至ArrayList返回至前端，通过编写函数逐行显示给用户，从而实现密文明文化以及保障了账单信息的安全存储。</p>
<p style="font-family:宋体;font-weight:bold;font-size:18px;">AES解密原理流程如下：</p>
<p style="text-indent: 2em;font-family:宋体;font-weight:bold;">由于AES为对称加密算法，AES的加密密钥和解密密钥一致，AES解密过程即为加密过程的逆过程。AES解密算法由最后一轮密钥开始，主循环State矩阵执行Nr-1轮迭代运算，每轮都包括所有4个阶段的代换，分别被称为inv_SubBytes（逆字节代换），inv_ShiftRows（逆行移位变换），inv_MixColumns（逆列混合变换）和AddRoundKey（轮密钥加）。最后执行只包括3个阶段的最后一轮变换。</p>
</div>
<div align="right" style="border:20px solid #ffffff">
<a class="class1" href="javaScript:reD()">点击查看AES解密流程>></a><br>
<a class="class1" href="https://blog.csdn.net/gulang03/article/details/81175854">AES解密算法原理详解>></a>
</div>
</body>
</html>