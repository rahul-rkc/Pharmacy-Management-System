package com;

import java.sql.*;

public class DataConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/pms";
   String name = "root";
   String password = "rahul";
   Connection con = DriverManager.getConnection(url, name, password);
return con;	}
}
