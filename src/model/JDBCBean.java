package model;
//ģ�Ͳ㣬�������ݿ�
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
			Class.forName(driverStr);//�������������ؾ����������
			conn = DriverManager.getConnection(connStr,dbusername,dbpassword); //�����ݿ⽨������
			stmt = conn.createStatement();//����sql��ִ�У���ɾ�ģ��飩
		}catch(Exception ex){
			System.out.println("�޷�ͬ���ݿ⽨�����ӣ�");
			ex.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql){
		int result=0;
		try{
			result=stmt.executeUpdate(sql);//����ֵ��ʾ ��ɾ�ļ�������
		}catch(Exception ex){ 
			System.out.println("ִ�и��´���");
			ex.printStackTrace();
		}
		return result; 
	}
	
	public ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try{
			rs=stmt.executeQuery(sql);
		}catch(Exception ex){
			System.out.println("ִ�в�ѯ����");
			ex.printStackTrace();
		}
		return rs;
	}

	public void close(){
		try{ 
			stmt.close();
		    conn.close();
		}catch(Exception e){
			System.out.println("���ӹرմ���");
			e.printStackTrace();
		} 
	}
}
