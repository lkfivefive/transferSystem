package model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class BillInfo {
	private int bno;
	private String boutno;
	private String binno;
	private String bname;
	private String bmoney;
	private Date bdate;
	private String bnote;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBoutno() {
		return boutno;
	}
	public void setBoutno(String boutno) {
		this.boutno = boutno;
	}
	public String getBinno() {
		return binno;
	}
	public void setBinno(String binno) {
		this.binno = binno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBmoney() {
		return bmoney;
	}
	public void setBmoney(String bmoney) {
		this.bmoney = bmoney;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate2) {
		this.bdate = bdate2;
	}
	public String getBnote() {
		return bnote;
	}
	public void setBnote(String bnote) {
		this.bnote = bnote;
	}
	
	
	//查询历史账单
	public ArrayList<BillInfo> getBillInfo(String code,String pwd) throws UnsupportedEncodingException { //code是加密后付款账号，binno是未加密的收款账号
		ArrayList<BillInfo> list=new ArrayList<BillInfo>();
		String sql="select * from bill where boutno='"+code+"' order by bdate desc";
		JDBCBean jdbc=new JDBCBean();
		ResultSet rs=jdbc.executeQuery(sql);
		try { 
			while(rs.next()){
				BillInfo bi=new BillInfo(); 
				bi.setBno(rs.getInt("bno"));
				bi.setBdate(rs.getDate("bdate"));
				bi.setBoutno(bi.decrypt(rs.getString("boutno"),pwd));
				bi.setBinno(bi.decrypt(rs.getString("binno"),pwd));
				bi.setBname(bi.decrypt(rs.getString("bname"),pwd));
				bi.setBmoney(bi.decrypt(rs.getString("bmoney"),pwd));
				bi.setBnote(bi.decrypt(rs.getString("bnote"),pwd));
				System.out.println("解密后的付款账号："+bi.getBoutno());
				System.out.println("解密后的收款账号："+bi.getBinno());
				System.out.println("解密后的姓名："+bi.getBname());
				System.out.println("解密后的金额："+bi.getBmoney());
				System.out.println("解密后的备注："+bi.getBnote());
				list.add(bi);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc.close();
		return list;
	}
	
	//增加账单
	public int transfer(){
		String sql="insert into bill (boutno,binno,bname,bmoney,bdate,bnote) values('"+boutno+"','"+binno+"','"+bname+"','"+bmoney+"',curdate(),'"+bnote+"')";
		JDBCBean jdbc=new JDBCBean();
		int result=jdbc.executeUpdate(sql);
		jdbc.close();
		return result;
		}
	 
	
	//AES加密
	//输入：content是需要加密的字符串,password是根据用户密码产生的密钥；输出：加密后的十六进制字符串
	public String encrypt(String content, String password) { //content是需要加密的字符串,password是根据用户密码产生的密钥
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
	        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
	        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	        byte[] byteContent = content.getBytes("utf-8");
	        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
	        byte[] result = cipher.doFinal(byteContent);// 加密
	        
	        //将字节矩阵转换成string类型(将二进制转换成十六进制)
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            String hex = Integer.toHexString(result[i] & 0xFF);
	            if (hex.length() == 1) {
	                hex = '0' + hex;
	            }
	            sb.append(hex.toUpperCase());
	        }
	        return sb.toString();
	        
	        } catch (NoSuchPaddingException e) {
	             e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	             e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	             e.printStackTrace();
	        } catch (InvalidKeyException e) {
	             e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	             e.printStackTrace();
	        } catch (BadPaddingException e) {
	             e.printStackTrace();
	        }
	         return null;
	}
	
	//AES解密
	//输入：code是需要解密的十六进制字符串,password是根据用户密码产生的密钥；输出：明文
	public String decrypt(String code, String password) throws UnsupportedEncodingException {
        try {
        	//将string类型转换成字节矩阵(将十六进制转换成二进制)
        	if (code.length() < 1)
	            return null;
	        byte[] content = new byte[code.length() / 2];
	        for (int i = 0; i < code.length() / 2; i++) {
	            int high = Integer.parseInt(code.substring(i * 2, i * 2 + 1), 16);
	            int low = Integer.parseInt(code.substring(i * 2 + 1, i * 2 + 2), 16);
	            content[i] = (byte) (high * 16 + low);
	        }
	        
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            String de_code = new String(result,"UTF-8");
            return  de_code;// 明文   
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	
	
	
}
