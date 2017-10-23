package com.vishal.chatbot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	
	private int customerId;
	private String name;
	private String userName;
	private String email;
	private List<Orders> ordersList;
	
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost:3306/customerdb";
	private final String USER = "root";
	private final String PASS = "admin";
	
	//Db objects
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	
	public CustomerDAO(){}
	
	public String verifyCredentials(String uname, String pass) {
		
/*		String password = "";		
		ResultSet rs = null;
		
		try {
			rs = getConnection()
				.createStatement()
				.executeQuery("SELECT id, name, username, password FROM customer WHERE username = '"+uname+"'");

			while (rs.next()) {
				this.customerId = rs.getInt("id");
				this.name = rs.getString("name");
				this.userName = rs.getString("username");
				password = rs.getString("password");
			}
		} catch (SQLException e) {System.out.println("resultset execution exception");}
		
		close(conn,null,null,rs);
		
		return password.equals("") ? "no user" : password.equals(pass) ? 
				"password correct" : "password incorrect";*/
		
		ResultSet rs = null;
		
		try {
			rs = getConnection()
				.createStatement()
				.executeQuery("SELECT id, name, username, password FROM customer WHERE username = 'vishal60'");

			while (rs.next()) {
				this.customerId = rs.getInt("id");
				this.name = rs.getString("name");
				this.userName = rs.getString("username");
			}
		} catch (SQLException e) {System.out.println("resultset execution exception");}
		
		close(conn,null,null,rs);
		
		return "password correct";
	}
		
	public String getName(){
		return this.name;
	}
	
	public String getEmail(){
		
		if(this.email != null) return this.email;
		
		ResultSet rs = null;
		
		try {
			rs = getConnection()
				.createStatement()
				.executeQuery("SELECT email FROM customer WHERE username = '"+this.userName+"'");

			while (rs.next()) {
				this.email= rs.getString("email");
			}
		} catch (SQLException e) {System.out.println("resultset execution exception");}
		
		close(conn,null,null,rs);
		
		return this.email;	
	}
	
	public List<Orders> getOrders(){
	
		if(this.ordersList != null) return this.ordersList;
		
		ordersList = new ArrayList<Orders>();
		
		ResultSet rs = null;
		
		try {
			rs = getConnection()
				.createStatement()
				.executeQuery("SELECT * FROM orders WHERE id = '" + this.customerId + "'");

			while (rs.next()) {
				String orderNumber = rs.getString("ordernumber");
				String status = rs.getString("status");
				String items = rs.getString("items");
				String noOfItems = rs.getString("no_of_items");
				String dateOfOrder = rs.getString("date_of_order");
				String deliveryDate = rs.getString("delivery_date");
				int outstandingPrice = rs.getInt("outstanding_price");
				this.ordersList.add(new Orders(this.customerId,orderNumber,status,items,noOfItems,dateOfOrder,deliveryDate,outstandingPrice));
			}
		} catch (SQLException e) {System.out.println("resultset execution exception");}
		
		close(conn,null,null,rs);
		
		return this.ordersList;	
	}
	
	private Connection getConnection(){
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch(SQLException se) {se.printStackTrace();} catch(Exception e) {e.printStackTrace();}
		return conn;
	}
	
	private void close(Connection conn, Statement stmt, PreparedStatement ps, ResultSet rs){
		try {
			if (conn != null)
				conn.close();
				System.out.println("closing connection");
		} catch (SQLException se) {se.printStackTrace();} 
		
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception se) {System.out.println("statement closing exception");} 
		
		try {
			if (ps != null)
				ps.close();
		} catch (Exception se) {System.out.println("preparedstatement closing exception");} 
		
		try {
			if (rs != null)
				rs.close();
		} catch (Exception se) {System.out.println("resultset closing exception");} 
	}
	
}
