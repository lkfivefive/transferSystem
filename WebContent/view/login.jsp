<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>登录</title>
<style type="text/css">
body{      
        background-image: url(images/login.jpg);      
        background-size:cover;    
     }
</style>
</head>
<body>
<div align="center" style="padding-top: 150px">
        <h2>欢迎使用转账系统</h2>
    </div>
    <br>
	<form name="f1" method="post" action="login.action">
	 <table width="300" border="0" align="center">
	 <tr height="30px">
           <td>账　号:</td>
                <td>
                   <input id="ano" type="text" name="ano">    
                 </td>
     </tr>
     <tr height="30px">
            <td>密　码:</td>
              <td>
                  <input id="apwd" type="password" name="apwd">                            
              </td>
      </tr>
	<tr height="40px">
           <td></td>
            <td><input type="submit" value="登录">
				<a href="register.jsp"><input type="button" value="注册"></input></a>
            </td>
     </tr>	
	 </table>
	</form>
</body>
</html>