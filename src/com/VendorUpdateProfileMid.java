package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VendorUpdateProfileMid
 */
@WebServlet("/vendorUpdateProfileMid.do")
public class VendorUpdateProfileMid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorUpdateProfileMid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession hs = request.getSession();
		String vndr_id = (String) hs.getAttribute("logname");
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select * from current_vendor where vnd_id='"+vndr_id+"'");
			while(rs1.next()){
				String vndid=rs1.getString("vnd_id");
				String vnm=rs1.getString("vnd_nm");
				String vpwd=rs1.getString("vnd_pwd");
				String vadd=rs1.getString("vnd_add");
				int vphn=rs1.getInt("vnd_phn");
				String vmail=rs1.getString("vnd_mail");
				
				
				VendorDetails vd=new VendorDetails();
				vd.setVid(vndid);
				vd.setVpwd(vpwd);
				vd.setVnm(vnm);
				vd.setVadd(vadd);
				vd.setVphn(vphn);
				vd.setVmail(vmail);
				//vd.setDesc(vdesc);
				request.setAttribute("vendor", vd);
				RequestDispatcher view=request.getRequestDispatcher("vendorUpdateProfile.jsp");
				view.forward(request, response);
			}
			st.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("sql error!!");
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(VendorUpdateProfileMid.class.getName()).log(Level.SEVERE, null, ex);
            }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
