package com;

import java.sql.*;

public class Payment  
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/Payment", "root", "n2G0u2B0i@N#u"); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 
public String readPayments() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Account Holder Name</th> <th>Account Number</th><th>CVV</th>"+ "<th>Expire Date</th><th>Phone</th><th>Email</th> <th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from payments"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String paymentID = Integer.toString(rs.getInt("paymentID")); 
 String name = rs.getString("name"); 
 String accNumber = rs.getString("accNumber"); 
 String cvv = rs.getString("cvv"); 
 String expDate = rs.getString("expDate"); 
 String phone = rs.getString("phone"); 
 String email = rs.getString("email"); 
 // Add into the html table
output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate' type='hidden' value='" + paymentID
 + "'>" + name + "</td>"; 
 output += "<td>" + accNumber + "</td>"; 
 output += "<td>" + cvv + "</td>"; 
 output += "<td>" + expDate + "</td>"; 
 output += "<td>" + phone + "</td>"; 
 output += "<td>" + email + "</td>"; 
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='submit' value='Remove' class='btnRemove btn btn-danger' data-paymentid='"
 + paymentID + "'>" + "</td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the payment."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String insertPayment(String name, String accNumber, 
 String cvv, String expDate,String phone, String email ) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for inserting."; 
 } 
 // create a prepared statement
 String query = " insert into payments (`paymentID`,`name`,`accNumber`,`cvv`,`expDate`,`phone`,`email`)"
 + " values (?, ?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, accNumber); 
 preparedStmt.setString(4, cvv); 
 preparedStmt.setString(5, expDate); 
 preparedStmt.setString(6, phone); 
 preparedStmt.setString(7, email); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newPayments = readPayments(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
 newPayments + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updatePayment(String ID, String name, String accNumber, String cvv, String expDate, String phone, String email) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for updating."; 
 } 
 // create a prepared statement
 String query = "UPDATE payments SET name=?,accNumber=?,cvv=?,expDate=?,phone=?,email=? WHERE paymentID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, name); 
 preparedStmt.setString(2, accNumber); 
 preparedStmt.setString(3, cvv); 
 preparedStmt.setString(4, expDate); 
 preparedStmt.setString(5, phone); 
 preparedStmt.setString(6, email); 
 preparedStmt.setInt(7, Integer.parseInt(ID)); 
 
 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newPayments = readPayments(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newPayments + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while updating the payments.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String deletePayment(String paymentID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for deleting."; 
 } 
 // create a prepared statement
 String query = "delete from payments where paymentID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newPayments = readPayments(); 
 output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
}