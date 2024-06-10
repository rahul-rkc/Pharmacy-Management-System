package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCustomerProduct
 */
@WebServlet("/addCustomerProduct.do")
public class AddCustomerProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String cid = (String) hs.getAttribute("logname");
		String corder_id=request.getParameter("corder_id");
		PrintWriter out=response.getWriter();
		String message="Invalid order_id";
		Connection conn = null;
        PreparedStatement pstm = null;
        ServletContext context = getServletContext();
        ResultSet rs = null;
        int retid=0;
        String ordid1 = null;
        int prodid1=0;;
		int flag=0;
        try{
             conn = DataConnection.getConnection();
             String sql="select uprod_id,uprod_amt from user_product where usr_id='"+cid+"'";
             pstm=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
             rs = pstm.executeQuery();
             System.out.println("Query1 executed");
             PreparedStatement pstm1 = conn.prepareStatement("select u.uprod_id,prod_nm,prod_ctgry,prod_desc,prod_price,u.uord_amt from admin_product_sale a,user_order uo,user_order_prod u where a.prod_id=u.uprod_id and uo.uord_id = u.uord_id and uo.uord_id ='"+corder_id+"' and usr_id='"+cid+"'");
             ResultSet rs1 = pstm1.executeQuery();
             System.out.println("Query2 executed");
          
            
             while(rs1.next()){
            	 if(!rs.next()){
            		 int proddid = rs1.getInt(1);
            		 String prodnm = rs1.getString(2);
            		 String prodctgry = rs1.getString(3);
            		 String proddesc = rs1.getString(4);
            		 int prodprice = rs1.getInt(5);
            		 int order_amnt = rs1.getInt(6);
            		 PreparedStatement pstm3 = conn.prepareStatement("insert into user_product values(?,?,?,?,?,?,?)");
            		 pstm3.setString(1, cid);
            		 pstm3.setInt(2, proddid);
            		 pstm3.setString(3,prodnm);
            		 pstm3.setString(4, prodctgry);
            		 pstm3.setString(5,proddesc);
            		 pstm3.setInt(6, prodprice);
            		 pstm3.setInt(7, order_amnt);
            		 pstm3.executeUpdate();
            		 message = "New Product Added";
            		 request.setAttribute("addprod", message);
            		 pstm3.close();
            		// rs1.next();
            		 flag=2;
            	 }
            	 
            	 rs.beforeFirst();
            	 while(rs.next()){
            		 retid = rs.getInt(1);
            		 prodid1 = rs1.getInt(1);
            		 System.out.println(retid);
            		 System.out.println(prodid1);
               if(retid==prodid1){
            		flag=1;
			break;
            	 }
            	 flag=0;
            	 }            	 
            	if(flag==1){
                         int crrnt_amt = rs.getInt(2);
            		 int order_amnt = rs1.getInt(6);
            		 crrnt_amt = crrnt_amt + order_amnt;
            		 PreparedStatement pstm2 = conn.prepareStatement("update user_product set uprod_amt=? where uprod_id ='"+retid+"' and usr_id='"+cid+"'");
            		 pstm2.setInt(1, crrnt_amt);
            		 pstm2.executeUpdate();
            		 message = "Existing Product Updated";
            		 request.setAttribute("addprod", message);
				}else if(flag==0){
				 System.out.println("New product");
            		 int proddid = rs1.getInt(1);
            		 String prodnm = rs1.getString(2);
            		 String prodctgry = rs1.getString(3);
            		 String proddesc = rs1.getString(4);
            		 int prodprice = rs1.getInt(5);
            		 int order_amnt = rs1.getInt(6);
            		 PreparedStatement pstm3 = conn.prepareStatement("insert into user_product values(?,?,?,?,?,?,?)");
            		 pstm3.setString(1, cid);
            		 pstm3.setInt(2, proddid);
            		 pstm3.setString(3,prodnm);
            		 pstm3.setString(4, prodctgry);
            		 pstm3.setString(5,proddesc);
            		 pstm3.setInt(6, prodprice);
            		 pstm3.setInt(7, order_amnt);
            		 pstm3.executeUpdate();
            		 message = "New Product Added";
            		 request.setAttribute("addprod", message);
     				
     				
				} 
            	rs.beforeFirst();
            	 }
             pstm.close();
             pstm1.close();
          
             conn.close();
             RequestDispatcher rd = request.getRequestDispatcher("customerHome.jsp");
				rd.forward(request, response);
             
                       
        }catch(Exception e){
        	System.out.println(e);
        	
        }
	}

}
