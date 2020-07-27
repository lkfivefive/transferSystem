package control;
//控制层，接收view层的请求，调用model层封装的功能函数
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
		
		//处理登录操作
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
		    	System.out.println("账号:"+accountno);
		    	System.out.println("登录成功！");
		    	request.getSession().setAttribute("ano",accountno);
		    	request.getSession().setAttribute("apwd",accountpwd);
		    	String accountname = ai.Query_name();
		    	request.getSession().setAttribute("accountname", accountname);
		    	request.getRequestDispatcher("/view/main.jsp").forward(request, response);
		    }
		    else{
		    	System.out.println("登录失败！");
		    	request.getRequestDispatcher("/view/login.jsp") .forward(request, response);
		    }
		}
		
		//处理注册操作
		else if(actionUrl.equals("/view/register.action")) {
			String accountno=request.getParameter("ano");
			String accountpwd=request.getParameter("apwd");
			String accountname=request.getParameter("aname");
			String accountId=request.getParameter("aId");
			String accountsex=request.getParameter("asex");
			String accounttel=request.getParameter("atel");
			String accountmail=request.getParameter("amail");
			String accountaddress=request.getParameter("address");
			System.out.println("账号:"+accountno);
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			try {
				ai.setApwd(AccountInfo.MD5_encrypt(accountpwd));
				System.out.println("加密前："+"账户密码："+accountpwd);
				System.out.println("加密后："+"账户密码："+AccountInfo.MD5_encrypt(accountpwd));
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
				System.out.println("注册成功！");
				request.getRequestDispatcher("/view/register_success.html").forward(request, response); 
			}
			else {
				System.out.println("注册失败！");
				request.getRequestDispatcher("/view/register_fail.html").forward(request, response);
			}
		       
		}
		
		//处理查询余额的操作
		else if(actionUrl.equals("/view/query_money.action")){
			String accountno=(String)request.getSession().getAttribute("ano");
			System.out.println(accountno+"正在处理查询余额业务");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			float money=ai.Query_money();
			System.out.println("余额为："+money);
			if(money!=-1) {
				request.setAttribute("money", money);
				request.getRequestDispatcher("/view/query_money.jsp").forward(request, response);
			}
			else {
				System.out.println("查询余额失败！");
			}
		}
		
		//处理查询账户信息操作
		else if(actionUrl.equals("/view/query_accountinfo.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			System.out.println(accountno+"正在处理查询账户信息业务");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			ArrayList<AccountInfo> accountlist=ai.getAccountInfo();
			request.setAttribute("accountlist", accountlist);
			request.getRequestDispatcher("/view/query_accountinfo.jsp").forward(request, response);
		}
		
		//修改密码
		else if(actionUrl.equals("/view/edit_pwd.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			String oldpwd=request.getParameter("oldpwd");
			String newpwd=request.getParameter("apwd");
			System.out.println(accountno+"正在修改密码");
			AccountInfo ai=new AccountInfo();
			ai.setAno(accountno);
			String accountpwd=ai.Query_pwd();
			try {
				if(!accountpwd.equals(AccountInfo.MD5_encrypt(oldpwd))) {
					request.setAttribute("error", "账户原密码错误！");
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
						System.out.println("修改成功！");
						request.getRequestDispatcher("/view/editpwd_success.html").forward(request, response); 
					}
					else {
						System.out.println("修改失败！");
						request.setAttribute("error", "修改失败！");
						request.getRequestDispatcher("/view/editpwd_fail.jsp").forward(request, response);
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//处理修改账户信息操作
		else if(actionUrl.equals("/view/edit_accountinfo.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			String accountname=request.getParameter("aname");
			String accountId=request.getParameter("aId");
			String accountsex=request.getParameter("asex");
			String accounttel=request.getParameter("atel");
			String accountmail=request.getParameter("amail");
			String accountaddress=request.getParameter("address");
			System.out.println(accountno+"正在修改账户信息");
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
				System.out.println("修改成功！");
				request.getRequestDispatcher("/view/edit_success.html").forward(request, response); 
			}
			else {
				System.out.println("修改失败！");
				request.getRequestDispatcher("/view/edit_fail.html").forward(request, response);
			}
		       
		}
		
		//处理查询历史账单操作
		else if(actionUrl.equals("/view/query_billinfo.action")) {
			String accountno=(String)request.getSession().getAttribute("ano");
			AccountInfo ai = new AccountInfo();
			ai.setAno(accountno);
			String pwd =ai.Query_pwd();
			System.out.println(accountno+"正在处理查询历史账单业务");
			BillInfo bi=new BillInfo();
			String en_boutno=bi.encrypt(accountno, pwd);
			System.out.println("加密后的付款账号："+en_boutno);
			ArrayList<BillInfo> billlist=bi.getBillInfo(en_boutno,pwd);
			request.setAttribute("billlist", billlist);
			request.getRequestDispatcher("/view/query_billinfo.jsp").forward(request, response);
		}
		
		//处理转账操作
		else if(actionUrl.equals("/view/transfer.action")) {
			String boutno=(String)request.getSession().getAttribute("ano");
			String binno=request.getParameter("binno");
			String bname=request.getParameter("bname");
			float bmoney=Float.parseFloat(request.getParameter("bmoney"));
			if(bmoney<0) {
				request.setAttribute("error", "转账失败！转账金额不能为负数！");
				request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
			}
			else {
				String bnote=request.getParameter("bnote");
				System.out.println(boutno+"正在办理转账业务");
				AccountInfo aout=new AccountInfo();
				aout.setAno(boutno);
				float rest_money=aout.Query_money();//转出账户的余额
				if(rest_money<bmoney) {//判断转出账户余额是否够
					request.setAttribute("error", "转账失败！您的账户余额不足！");
					request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
				}
				else {
					AccountInfo ain=new AccountInfo();
					ain.setAno(binno);
					String ain_name=ain.Query_name();//转入账户姓名
					if(!bname.equals(ain_name)) {//判断转入账户账号和姓名是否对应
						request.setAttribute("error", "转账失败！收款账号与收款人姓名不符！");
						request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
					}
					else {//条件满足，开始转账
						int result_out=aout.transfer_out(bmoney);
						int result_in=ain.transfer_in(bmoney);
						//AES加密
						String pwd = aout.Query_pwd();//根据转出账户的密码产生密钥
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
				        System.out.println("加密前："+"付款账号："+boutno+" "+"收款账号："+binno+" "+"收款人姓名："+bname+" "+"转账金额："+bmoney+" "+"备注："+bnote);
				        System.out.println("加密后："+"付款账号："+en_boutno+" "+"收款账号："+en_binno+"收款人姓名："+en_bname+" "+"转账金额："+en_bmoney+" "+"备注："+en_bnote);
				        int result_tran=bi.transfer();
				        if(result_out==1 && result_in==1 && result_tran==1) {
				        	System.out.println("转账成功");
				        	request.getRequestDispatcher("/view/transfer_success.html").forward(request, response);
				        }
				        else {
				        	System.out.println("转账失败");
				        	request.setAttribute("error", "转账失败！");
				        	request.getRequestDispatcher("/view/transfer_fail.jsp").forward(request, response);
				        }
					}
				}
			}
		}
		
		
		//处理退出系统操作
		else if(actionUrl.equals("/view/exit.action")) {
			request.getSession().removeAttribute("ano");
			request.getSession().removeAttribute("apwd");
			request.getSession().invalidate();
		    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		}
		
/*		
        //处理注册账号是否存在的操作
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
