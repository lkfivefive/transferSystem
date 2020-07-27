package model;
//模型层，连接数据库
import java.sql.*;
public class JDBCBean {
	private String driverStr="com.mysql.jdbc.Driver";
	private String connStr;
	private String dbusername="root";
	private String dbpassword="123456";
	private Connection conn=null; 
	private Statement stmt=null;
	public JDBCBean() {
		connStr="jdbc:mysql://localhost:3306/lk?useUnicode=true&characterEncoding=gb2312&useSSL=false&serverTimezone=UTC";
		try{
			Class.forName(driverStr);//导入驱动，加载具体的驱动类
			conn = DriverManager.getConnection(connStr,dbusername,dbpassword); //与数据库建立连接
			stmt = conn.createStatement();//发送sql，执行（增删改，查）
		}catch(Exception ex){
			System.out.println("无法同数据库建立连接！");
			ex.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql){
		int result=0;
		try{
			result=stmt.executeUpdate(sql);//返回值表示 增删改几条数据
		}catch(Exception ex){ 
			System.out.println("执行更新错误！");
			ex.printStackTrace();
		}
		return result; 
	}
	
	public ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
		}catch(Exception ex){
			System.out.println("执行查询错误！");
			ex.printStackTrace();
		}
		return rs;
	}

	public void close(){
		try{ 
			stmt.close();
		    conn.close();
		}catch(Exception e){
			System.out.println("连接关闭错误！");
			e.printStackTrace();
		} 
	}
}
