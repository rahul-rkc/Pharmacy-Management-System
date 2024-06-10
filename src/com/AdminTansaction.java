package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminTansaction
 */
@WebServlet("/adminTansaction.do")
public class AdminTansaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTansaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
                    PrintWriter out = response.getWriter();
                    out.write("hi");
		String admin_id = request.getParameter("id");
		String card_no = request.getParameter("crd_no");
		String bank_pin = request.getParameter("bnk_pin");
		String aord_id = request.getParameter("aord_id");
		String ord_amt = request.getParameter("tot_amt");
		int getblnc = Integer.valueOf(ord_amt);
		int retblnc;
                out.write("hiii");
		String sql = "select blnc from bank_abc where id = '"+admin_id+"' and card_no = '"+card_no+"' and pin = '"+bank_pin+"'";
                out.write(admin_id+" "+card_no+" "+bank_pin);
		String sql1 = "update admin_order set aord_stat=2 where aord_id='"+aord_id+"'";
                out.write(aord_id);
		String sql2 = "update admin_tran_det set crd_no = '"+card_no+"' where admin_id = '"+admin_id+"' and aord_id = '"+aord_id+"'";
                out.write("sql2");
		try{
			Connection con = DataConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);
                                        out.write("sql1");
			ResultSet rs=ps.executeQuery(); 
			while (rs.next()) {
				retblnc = rs.getInt(1);
				if(retblnc>getblnc){
					System.out.println("Success");
					TransactionS t = new TransactionS();
					t.setId(admin_id);
					t.setOrd_id(aord_id);
					t.setOrd_amt(getblnc);
					ps2.executeUpdate();
					request.setAttribute("bill", t);
					RequestDispatcher view=request.getRequestDispatcher("adminBill.jsp");
					view.forward(request, response);
				}
				else{
					System.out.println("Not Enough Balance");
					PreparedStatement ps1=con.prepareStatement(sql1);
					ps1.executeUpdate();
					response.sendRedirect("adminHome.jsp");
				}
			}
			con.close();
		}
		catch(Exception e){
			
		}
//        
//        out.write("payment Succesfull");
	}

}
