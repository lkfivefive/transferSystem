package control;
//���Ʋ㣬����view������󣬵���model���װ�Ĺ��ܺ���
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountInfo;
import model.BillInfo;



public class AccountInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getServletPath()); 
		request.setCharacterEncoding("GBK");
		String actionUrl=request.getServletPath();
		
		//�����¼����
		if(actionUrl.equals("/view/login.action")){
			String accountno=request.getParameter("ano");
			String accountpwd=request.getParameter("apwd");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			try {
				ai.setApwd(AccountInfo.MD5_encrypt(accountpwd));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean login_success=false;
			login_success=ai.Login();
		    if(login_success) {
		    	System.out.println("�˺�:"+accountno);
		    	System.out.println("��¼�ɹ���");
		    	request.getSession().setAttribute("ano",accountno);
		    	request.getSession().setAttribute("apwd",accountpwd);
		    	String accountname = ai.Query_name();
		    	request.getSession().setAttribute("accountname", accountname);
		    	request.getRequestDispatcher("/view/main.jsp").forward(request, response);
		    }
		    else{
		    	System.out.println("��¼ʧ�ܣ�");
		    	request.getRequestDispatcher("/view/login.jsp") .forward(request, response);
		    }
		}
		
		//����ע�����
		else if(actionUrl.equals("/view/register.action")) {
			String accountno=request.getParameter("ano");
			String accountpwd=request.getParameter("apwd");
			String accountname=request.getParameter("aname");
			String accountId=request.getParameter("aId");
			String accountsex=request.getParameter("asex");
			String accounttel=request.getParameter("atel");
			String accountmail=request.getParameter("amail");
			String accountaddress=request.getParameter("address");
			System.out.println("�˺�:"+accountno);
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			try {
				ai.setApwd(AccountInfo.MD5_encrypt(accountpwd));
				System.out.println("����ǰ��"+"�˻����룺"+accountpwd);
				System.out.println("���ܺ�"+"�˻����룺"+AccountInfo.MD5_encrypt(accountpwd));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ai.setAname(accountname);
			ai.setaId(accountId);
			ai.setAsex(accountsex);
			ai.setAtel(accounttel);
			ai.setAmail(accountmail);
			ai.setAddress(accountaddress);
			int result=ai.register();
			if(result==1){
				System.out.println("ע��ɹ���");
				request.getRequestDispatcher("/view/register_success.html").forward(request, response); 
			}
			else {
				System.out.println("ע��ʧ�ܣ�");
				request.getRequestDispatcher("/view/register_fail.html").forward(request, response);
			}
		       
		}
		
		//�����ѯ���Ĳ���
		else if(actionUrl.equals("/view/query_money.action")){
			String accountno=(String)request.getSession().getAttribute("ano");
			System.out.println(accountno+"���ڴ����ѯ���ҵ��");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			float money=ai.Query_money();
			System.out.println("���Ϊ��"+money);
			if(money!=-1) {
				request.setAttribute("money", money);
				request.getRequestDispatcher("/view/query_money.jsp").forward(request, response);
			}
			else {
				System.out.println("��ѯ���ʧ�ܣ�");
			}
		}
		
		//�����ѯ�˻���Ϣ����
		else if(actionUrl.equals("/view/query_accountinfo.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			System.out.println(accountno+"���ڴ����ѯ�˻���Ϣҵ��");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			ArrayList<AccountInfo> accountlist=ai.getAccountInfo();
			request.setAttribute("accountlist", accountlist);
			request.getRequestDispatcher("/view/query_accountinfo.jsp").forward(request, response);
		}
		
		//�޸�����
		else if(actionUrl.equals("/view/edit_pwd.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			String oldpwd=request.getParameter("oldpwd");
			String newpwd=request.getParameter("apwd");
			System.out.println(accountno+"�����޸�����");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			String accountpwd=ai.Query_pwd();
			try {
				if(!accountpwd.equals(AccountInfo.MD5_encrypt(oldpwd))) {
					request.setAttribute("error", "�˻�ԭ�������");
					request.getRequestDispatcher("/view/editpwd_fail.jsp").forward(request, response);
				}
				else {
					try {
						ai.setApwd(AccountInfo.MD5_encrypt(newpwd));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int result=ai.editpwd();
					if(result==1){
						System.out.println("�޸ĳɹ���");
						request.getRequestDispatcher("/view/editpwd_success.html").forward(request, response); 
					}
					else {
						System.out.println("�޸�ʧ�ܣ�");
						request.setAttribute("error", "�޸�ʧ�ܣ�");
						request.getRequestDispatcher("/view/editpwd_fail.jsp").forward(request, response);
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//�����޸��˻���Ϣ����
		else if(actionUrl.equals("/view/edit_accountinfo.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			String accountname=request.getParameter("aname");
			String accountId=request.getParameter("aId");
			String accountsex=request.getParameter("asex");
			String accounttel=request.getParameter("atel");
			String accountmail=request.getParameter("amail");
			String accountaddress=request.getParameter("address");
			System.out.println(accountno+"�����޸��˻���Ϣ");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			ai.setAname(accountname);
			ai.setaId(accountId);
			ai.setAsex(accountsex);
			ai.setAtel(accounttel);
			ai.setAmail(accountmail);
			ai.setAddress(accountaddress);
			int result=ai.editAccountInfo();
			if(result==1){
				System.out.println("�޸ĳɹ���");
				request.getRequestDispatcher("/view/edit_success.html").forward(request, response); 
			}
			else {
				System.out.println("�޸�ʧ�ܣ�");
				request.getRequestDispatcher("/view/edit_fail.html").forward(request, response);
			}
		       
		}
		
		//�����ѯ��ʷ�˵�����
		else if(actionUrl.equals("/view/query_billinfo.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			AccountInfo ai = new AccountInfo();
			ai.setAno(accountno);
			String pwd =ai.Query_pwd();
			System.out.println(accountno+"���ڴ����ѯ��ʷ�˵�ҵ��");
			BillInfo bi=new BillInfo();
			String en_boutno=bi.encrypt(accountno, pwd);
			System.out.println("���ܺ�ĸ����˺ţ�"+en_boutno);
			ArrayList<BillInfo> billlist=bi.getBillInfo(en_boutno,pwd);
			request.setAttribute("billlist", billlist);
			request.getRequestDispatcher("/view/query_billinfo.jsp").forward(request, response);
		}
		
		//����ת�˲���
		else if(actionUrl.equals("/view/transfer.action")) {
			String boutno=(String)request.getSession().getAttribute("ano");
			String binno=request.getParameter("binno");
			String bname=request.getParameter("bname");
			float bmoney=Float.parseFloat(request.getParameter("bmoney"));
			if(bmoney<0) {
				request.setAttribute("error", "ת��ʧ�ܣ�ת�˽���Ϊ������");
				request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
			}
			else {
				String bnote=request.getParameter("bnote");
				System.out.println(boutno+"���ڰ���ת��ҵ��");
				AccountInfo aout=new AccountInfo();
				aout.setAno(boutno);
				float rest_money=aout.Query_money();//ת���˻������
				if(rest_money<bmoney) {//�ж�ת���˻�����Ƿ�
					request.setAttribute("error", "ת��ʧ�ܣ������˻����㣡");
					request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
				}
				else {
					AccountInfo ain=new AccountInfo();
					ain.setAno(binno);
					String ain_name=ain.Query_name();//ת���˻�����
					if(!bname.equals(ain_name)) {//�ж�ת���˻��˺ź������Ƿ��Ӧ
						request.setAttribute("error", "ת��ʧ�ܣ��տ��˺����տ�������������");
						request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
					}
					else {//�������㣬��ʼת��
						int result_out=aout.transfer_out(bmoney);
						int result_in=ain.transfer_in(bmoney);
						//AES����
						String pwd = aout.Query_pwd();//����ת���˻������������Կ
						BillInfo bi=new BillInfo();
						String en_boutno=bi.encrypt(boutno,pwd);
				        bi.setBoutno(en_boutno);
				        String en_binno=bi.encrypt(binno,pwd);
				        bi.setBinno(en_binno);
				        String en_bname=bi.encrypt(bname,pwd);
				        bi.setBname(en_bname);
				        String en_bmoney=bi.encrypt(Float.toString(bmoney),pwd);
				        bi.setBmoney(en_bmoney);
				        String en_bnote=bi.encrypt(bnote,pwd);
				        bi.setBnote(en_bnote);
				        System.out.println("����ǰ��"+"�����˺ţ�"+boutno+" "+"�տ��˺ţ�"+binno+" "+"�տ���������"+bname+" "+"ת�˽�"+bmoney+" "+"��ע��"+bnote);
				        System.out.println("���ܺ�"+"�����˺ţ�"+en_boutno+" "+"�տ��˺ţ�"+en_binno+"�տ���������"+en_bname+" "+"ת�˽�"+en_bmoney+" "+"��ע��"+en_bnote);
				        int result_tran=bi.transfer();
				        if(result_out==1 && result_in==1 && result_tran==1) {
				        	System.out.println("ת�˳ɹ�");
				        	request.getRequestDispatcher("/view/transfer_success.html").forward(request, response);
				        }
				        else {
				        	System.out.println("ת��ʧ��");
				        	request.setAttribute("error", "ת��ʧ�ܣ�");
				        	request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
				        }
					}
				}
			}
		}
		
		
		//�����˳�ϵͳ����
		else if(actionUrl.equals("/view/exit.action")) {
			request.getSession().removeAttribute("ano");
			request.getSession().removeAttribute("apwd");
			request.getSession().invalidate();
		    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		}
		
/*		
        //����ע���˺��Ƿ���ڵĲ���
		else if(actionUrl.equals("/view/check_IdExist.action")){
			   String accountno=request.getParameter("ano"); 
			   AccountInfo ai=new AccountInfo(); 
			   ai.setaId(accountno);
			   System.out.println(accountno);
			   boolean Id_exist=ai.check_IdExist();
			   System.out.println(Id_exist); 
			   if(Id_exist) {
				   
			   }
			   else {
				   
			   }
			}
*/		
		
		
		
		
		
		
		
		
	}
}
