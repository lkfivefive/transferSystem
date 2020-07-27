package model;
import java.security.MessageDigest;
//模型层，封装数据和功能函数逻辑
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountInfo {
	private String ano;
	private String apwd;
	private float amoney;
	private String aname;
	private String aId;
	private String asex;
	private String atel;
	private String amail;
	private String address;
	
	
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public float getAmoney() {
		return amoney;
	}
	public void setAmoney(float amoney) {
		this.amoney = amoney;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getAsex() {
		return asex;
	}
	public void setAsex(String asex) {
		this.asex = asex;
	}
	public String getAtel() {
		return atel;
	}
	public void setAtel(String atel) {
		this.atel = atel;
	}
	public String getAmail() {
		return amail;
	}
	public void setAmail(String amail) {
		this.amail = amail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
/*
  	//查询账户Id是否存在
	public boolean check_IdExist() {
		boolean Id_exist=false;
		String sql="select * from account where ano='"+ano+"'";
		JDBCBean jdbc=new JDBCBean();
		ResultSet rs=jdbc.executeQuery(sql);
		try {
			if(rs!=null && rs.next())
				Id_exist=true;
			} catch (SQLException e) {
			           e.printStackTrace();
			        }
			        jdbc.close();
			return Id_exist;
	}
*/	
	//实现账户登录的方法
	public boolean Login() {
		boolean log_suc=false;
		String sql="select count(1) from account where ano='" +ano+"'and apwd='"+apwd+"'";
		JDBCBean jdbc=new JDBCBean();
		ResultSet rs=jdbc.executeQuery(sql);
		try {
			if(rs.next()&&rs.getInt(1)==1)
				log_suc=true;
		}catch(SQLException e) {
	        e.printStackTrace();
	    }
		jdbc.close();
		return log_suc;
	}
	
	//实现注册账户的方法
	public int register(){
		String sql="insert into account values('"+ano+"','"+apwd+"',0,'"+aname+"','"+aId+"','"+asex+"','"+atel+"','"+amail+"','"+address+"')";
		JDBCBean jdbc=new JDBCBean();
		int result=jdbc.executeUpdate(sql);
		jdbc.close();
		return result;
		} 
	
	//查询余额
	public float Query_money() {
		float money=-1;
		String sql="select amoney from account where ano='"+ano+"'";
		JDBCBean jdbc=new JDBCBean();
		ResultSet rs=jdbc.executeQuery(sql);
		try {
			if(rs.next())
				money=rs.getFloat("amoney");
		}catch(SQLException e){
	        e.printStackTrace();
	    }
		jdbc.close();
		return money;
	}
	
	//查询账户姓名
		public String Query_name() {
			String name="";
			String sql="select aname from account where ano='"+ano+"'";
			JDBCBean jdbc=new JDBCBean();
			ResultSet rs=jdbc.executeQuery(sql);
			try {
				if(rs.next())
					name=rs.getString("aname");
			}catch(SQLException e){
		        e.printStackTrace();
		    }
			jdbc.close();
			return name;
		}

	//查询账户密码
		public String Query_pwd() {
			String pwd="";
			String sql="select apwd from account where ano='"+ano+"'";
			JDBCBean jdbc=new JDBCBean();
			ResultSet rs=jdbc.executeQuery(sql);
			try {
				if(rs.next())
					pwd=rs.getString("apwd");
			}catch(SQLException e){
				e.printStackTrace();
			}
			jdbc.close();
			return pwd;
		}
		
	
	//转出
	public int transfer_out(float money){
		String sql="update account set amoney=amoney-"+money+" where ano='"+ano+"' ";
		JDBCBean jdbc=new JDBCBean();
		int result=jdbc.executeUpdate(sql);
		jdbc.close();
		return result;
		} 
	
	//转入
		public int transfer_in(float money){
			String sql="update account set amoney=amoney+"+money+" where ano='"+ano+"' ";
			JDBCBean jdbc=new JDBCBean();
			int result=jdbc.executeUpdate(sql);
			jdbc.close();
			return result;
			} 
	
	//查询账户信息
	public ArrayList<AccountInfo> getAccountInfo(){
		ArrayList<AccountInfo> list=new ArrayList<AccountInfo>(); 
		String sql="select * from account where ano='"+ano+"'";
		JDBCBean jdbc=new JDBCBean();
		ResultSet rs=jdbc.executeQuery(sql);
		try { 
			while(rs.next()){
				AccountInfo ai=new AccountInfo(); 
				ai.setAno(rs.getString("ano"));
				ai.setApwd(rs.getString("apwd"));
				ai.setAmoney(rs.getFloat("amoney"));
				ai.setAname(rs.getString("aname"));
				ai.setaId(rs.getString("aId"));
				ai.setAsex(rs.getString("asex"));
				ai.setAtel(rs.getString("atel"));
				ai.setAmail(rs.getString("amail"));
				ai.setAddress(rs.getString("address"));
				list.add(ai);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc.close();
		return list;
	}
	
	//修改密码
		public int editpwd(){
			String sql="update account set apwd='"+apwd+"' where ano='"+ano+"' ";
			JDBCBean jdbc=new JDBCBean();
			int result=jdbc.executeUpdate(sql);
			jdbc.close();
			return result;
			}
	
	//修改账户信息
	public int editAccountInfo(){
		String sql="update account set aname='"+aname+"',aId='"+aId+"',asex='"+asex+"',atel='"+atel+"',amail='"+amail+"',address='"+address+"' where ano='"+ano+"' ";
		JDBCBean jdbc=new JDBCBean();
		int result=jdbc.executeUpdate(sql);
		jdbc.close();
		return result;
		} 
	
	//用于账户密码MD5加密
	public static String MD5_encrypt(String content) throws Exception {
    	MessageDigest md5=MessageDigest.getInstance("MD5");
    	byte[] srcBytes=content.getBytes("utf-8"); 
    	byte[] resultBytes = md5.digest(srcBytes);
    	
        //字节数组转String时密文字母小写
        StringBuilder hex = new StringBuilder(resultBytes.length * 2);  
        for (byte b : resultBytes) {  
            if ((b & 0xFF) < 0x10) hex.append("0");  
            hex.append(Integer.toHexString(b & 0xFF));  
        }  
        return hex.toString();  
 }
	
	
	
	
	
	
	
	
	
}
