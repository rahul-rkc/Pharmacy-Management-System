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
 * Servlet implementation class PaymentSelectAdmin
 */
@WebServlet("/paymentSelectAdmin.do")
public class PaymentSelectAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentSelectAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String admin_id = (String) hs.getAttribute("logname");
		String payment_mode = request.getParameter("paymode");
		Connection con;
		String ordid = null;
		int money = 0;
		if(payment_mode.equals("Cash/Cheque")){
			String crd_no = "Cash";
			String sql = "select * from admin_curr_tran_det where admin_id = '"+admin_id+"'";
			String sql1 = "delete from admin_curr_tran_det";
			String sql2 = "update admin_tran_det set crd_no ='"+crd_no+"' where aord_id = ? ";
			//String sql3 = "select money from admin_tran_det where aord_id = ?";
			try{
				con = DataConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps2 = con.prepareStatement(sql2);
				//PreparedStatement ps3 = con.prepareStatement(sql3);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				ordid = rs.getString("aord_id");
				money = rs.getInt("money");
				}
				ps2.setString(1, ordid);
				ps2.executeUpdate();
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.executeUpdate();
				ps.close();
				ps1.close();
				TransactionS t = new TransactionS();
				t.setId(admin_id);
				t.setOrd_id(ordid);
				t.setOrd_amt(money);
				ps2.executeUpdate();
				request.setAttribute("bill", t);
				RequestDispatcher view=request.getRequestDispatcher("adminBill.jsp");
				view.forward(request, response);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			//response.sendRedirect("chooseVendor.do");
		}
		if(payment_mode.equals("Card")){
			response.sendRedirect("adminPayCard.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
