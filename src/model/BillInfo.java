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
	
	
	//��ѯ��ʷ�˵�
	public ArrayList<BillInfo> getBillInfo(String code,String pwd) throws UnsupportedEncodingException { //code�Ǽ��ܺ󸶿��˺ţ�binno��δ���ܵ��տ��˺�
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
				System.out.println("���ܺ�ĸ����˺ţ�"+bi.getBoutno());
				System.out.println("���ܺ���տ��˺ţ�"+bi.getBinno());
				System.out.println("���ܺ��������"+bi.getBname());
				System.out.println("���ܺ�Ľ�"+bi.getBmoney());
				System.out.println("���ܺ�ı�ע��"+bi.getBnote());
				list.add(bi);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc.close();
		return list;
	}
	
	//�����˵�
	public int transfer(){
		String sql="insert into bill (boutno,binno,bname,bmoney,bdate,bnote) values('"+boutno+"','"+binno+"','"+bname+"','"+bmoney+"',curdate(),'"+bnote+"')";
		JDBCBean jdbc=new JDBCBean();
		int result=jdbc.executeUpdate(sql);
		jdbc.close();
		return result;
		}
	 
	
	//AES����
	//���룺content����Ҫ���ܵ��ַ���,password�Ǹ����û������������Կ����������ܺ��ʮ�������ַ���
	public String encrypt(String content, String password) { //content����Ҫ���ܵ��ַ���,password�Ǹ����û������������Կ
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// ����AES��Key������
			kgen.init(128, new SecureRandom(password.getBytes()));// �����û�������Ϊ�������ʼ����128λ��key������
            //����û��ϵ��SecureRandom�����ɰ�ȫ��������У�password.getBytes()�����ӣ�ֻҪ������ͬ�����о�һ�������Խ���ֻҪ��password����
			SecretKey secretKey = kgen.generateKey();// �����û����룬����һ����Կ
			byte[] enCodeFormat = secretKey.getEncoded();// ���ػ��������ʽ����Կ���������Կ��֧�ֱ��룬�򷵻�null
	        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// ת��ΪAESר����Կ
	        Cipher cipher = Cipher.getInstance("AES");// ����������
	        byte[] byteContent = content.getBytes("utf-8");
	        cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������
	        byte[] result = cipher.doFinal(byteContent);// ����
	        
	        //���ֽھ���ת����string����(��������ת����ʮ������)
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
	
	//AES����
	//���룺code����Ҫ���ܵ�ʮ�������ַ���,password�Ǹ����û������������Կ�����������
	public String decrypt(String code, String password) throws UnsupportedEncodingException {
        try {
        	//��string����ת�����ֽھ���(��ʮ������ת���ɶ�����)
        	if (code.length() < 1)
	            return null;
	        byte[] content = new byte[code.length() / 2];
	        for (int i = 0; i < code.length() / 2; i++) {
	            int high = Integer.parseInt(code.substring(i * 2, i * 2 + 1), 16);
	            int low = Integer.parseInt(code.substring(i * 2 + 1, i * 2 + 2), 16);
	            content[i] = (byte) (high * 16 + low);
	        }
	        
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// ����AES��Key������
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// �����û����룬����һ����Կ
            byte[] enCodeFormat = secretKey.getEncoded();// ���ػ��������ʽ����Կ
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// ת��ΪAESר����Կ
            Cipher cipher = Cipher.getInstance("AES");// ����������
            cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������
            byte[] result = cipher.doFinal(content);
            String de_code = new String(result,"UTF-8");
            return  de_code;// ����   
            
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
