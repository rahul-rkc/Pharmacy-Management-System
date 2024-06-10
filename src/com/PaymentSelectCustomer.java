package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PaymentSelectCustomer
 */
@WebServlet("/paymentSelectCustomer.do")
public class PaymentSelectCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentSelectCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String cust_id = (String) hs.getAttribute("logname");
		String payment_mode = request.getParameter("paymode");
		String cordid = null;
		int money = 0;
		System.out.println(cust_id);
		Connection con;
		if(payment_mode.equals("Cash/Cheque")){
			String crd_no = "Cash";
			String sql = "select * from user_curr_tran_det where usr_id = '"+cust_id+"'";
//			String sql1 = "delete from user_curr_tran_det";
			String sql2 = "update user_tran_det set crd_no ='"+crd_no+"' where uord_id = ? ";
			try{
				con = DataConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				
				cordid = rs.getString("uord_id");
				money = rs.getInt("money");
                                }
				ps2.setString(1, cordid);
				ps2.executeUpdate();
//				PreparedStatement ps1 = con.prepareStatement(sql1);
//				ps1.executeUpdate();
				ps.close();
//				ps1.close();
				TransactionS t = new TransactionS();
				t.setId(cust_id);
				t.setOrd_id(cordid);
				t.setOrd_amt(money);
				ps2.executeUpdate();
				request.setAttribute("bill", t);
				RequestDispatcher view=request.getRequestDispatcher("customerBill.jsp");
				view.forward(request, response);
				
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
			//response.sendRedirect("viewAdminProdbyCust.do");
		}
		if(payment_mode.equals("Card")){
			response.sendRedirect("customerPayCard.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
