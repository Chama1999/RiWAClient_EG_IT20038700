package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	//created method to connect to the DB
		public Connection connect()
		{
			Connection con = null;
			//implement try-catch block to find errors
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid",//provide the correct details: DBServer/DBName, username, password
						"root", "");
				//For testing
				System.out.print("Successfully connected to db");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		//Implement insert method
		public String addPayment(String CardType,String CardNumber,String CardHolderName,String CVC,String CardExpireDate,String PaymentDate,int BillID )
		{
			String output = "";
			try
			{
				//for get DB connection
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }//connection error message 
				
				//create a prepared statement
				String insertQuery = "insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmnt = con.prepareStatement(insertQuery);
				double TaxAmount = this.calculateTaxAmount(BillID);
				double TotalAmount = this.calculateSubAmount(BillID);
				String Status = "pending";
				//binding values
				pstmnt.setString(1, CardType);
				pstmnt.setString(2, CardNumber);
				pstmnt.setString(3, CardHolderName);
				pstmnt.setString(4, CVC);
				pstmnt.setString(5, CardExpireDate);
				pstmnt.setString(6, Status);
				pstmnt.setDouble(7, TaxAmount);
				pstmnt.setDouble(8, TotalAmount);
				pstmnt.setString(9, PaymentDate);
				pstmnt.setInt(10, BillID);
				
				// execute the statement
							pstmnt.execute();
							con.close();
							String newItems = getAllPayment();
							output = "{\"status\":\"success\", \"data\": \"" +
									newItems + "\"}";
				
				
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//create method to read all payment details
		public String getAllPayment()
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
				output = "<table border='1' style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; radius: 10px\">" +
	                    "<tr style=\"border: 1px solid #ddd; padding: 8px;\">" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">PaymentID</th>" +
	                    "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">CardType</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">CardNumber</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">CardHolderName</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">CVC</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">CardExpireDate</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">Status</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">TaxAmount</th>" +
	                    "<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">TotalAmount</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">PaymentDate</th>" +
						"<th style=\"padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #04AA6D; color: white;\">BillID</th><th>Update</th><th>Remove</th></tr>";
				String query = "select * from payment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					int PaymentID = rs.getInt("PaymentID");
					String CardType = rs.getString("CardType");
					String CardNumber = rs.getString("CardNumber");
					String CardHolderName = rs.getString("CardHolderName");
					String CVC = rs.getString("CVC");
					String CardExpireDate = rs.getString("CardExpireDate");
					String Status = rs.getString("Status");
					float TaxAmount = rs.getFloat("TaxAmount");
					float TotalAmount = rs.getFloat("TotalAmount");
					String PaymentDate = rs.getString("PaymentDate");
					int BillID = rs.getInt("BillID");
					// Add into the html table
					output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + PaymentID
							+ "'>" + "</td></tr>";
					output += "<tr style=\"border: 1px solid #ddd; padding: 8px;\"><td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + PaymentID + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardType + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardNumber + "</td>";							
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardHolderName + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CVC + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + CardExpireDate + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + Status + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #191919; font-weight: 600\">" + TaxAmount + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #191919; font-weight: 600\">" + TotalAmount + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + PaymentDate + "</td>";
					output += "<td style=\"padding-top: 6px; padding-bottom: 6px; text-align: center; color: #3B3B3B;\">" + BillID + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ PaymentID + "'>" + "</td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ PaymentID + "'>" + "</td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the payments.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		//create method to read payment by user id
	/*	public String getPaymentByUser(int UserID) {
			try(Connection con = connect()) {//get connection
				//create a prepared statement
				String getQuery = "select py.PaymentID, o.BillID, c.name, py.PaymentDate,o.NoOfUnits,py.Status, py.TotalAmount from billing o \n"
						+ "join user c on o.UserID = c.UserID \n"
						+ "join payment py on o.BillID = py.BillID \n" 
						+ "where c.UserID = ?;";
				PreparedStatement pstmnt = con.prepareStatement(getQuery);
				pstmnt.setInt(1, UserID);
				//create html table
				String output = "<table border='1'>" + 
						"<tr>" 
						+ "<th>PaymentID</th>" 
						+ "<th>Bill ID</th>"
						+ "<th>Full Name</th>"
						+ "<th>Payment Date</th>" 
						+ "<th>No Of Units per month</th>"
						+ "<th>Status</th>"
						+ "<th>Total Amount(Include TAX)</th>";
		       ResultSet rs = pstmnt.executeQuery();
		       //iterate through the rows in the result set
		       while (rs.next()) {
					int PaymentID = rs.getInt("PaymentID");
					int BillID = rs.getInt("BillID");
					String name = rs.getString("name");
					String PaymentDate = rs.getString("PaymentDate");
					int NoOfUnits = rs.getInt("NoOfUnits");
					String Status = rs.getString("Status");
					double TotalAmount = rs.getDouble("TotalAmount");
					
	                //add into the html table
					output += "<tr><td>" + PaymentID + "</td>";
					output += "<td>" + BillID + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + PaymentDate + "</td>";
					output += "<td>" + NoOfUnits + "</td>";
					output += "<td>" + Status + "</td>";
					output += "<td>" + TotalAmount + "</td>";
					

				}
		       output += "</table>";//complete the html table
				return output;
				
			}
			catch(Exception e) {
				return "Error occur during retrieving \n" + e.getMessage();//get error message
			}
			
		}*/
		
		
		
		/*public float calculateTaxAmount(int BillID, int NoOfUnit) {
			float TaxAmount = 0;
			try(Connection con = connect()) {
				String getQuery = "select o.NoOfUnits\n" 
						+ "from billing o\n"
						+ "where o.BillID = ?;";
			     
				PreparedStatement pstmt = con.prepareStatement(getQuery);
				pstmt.setInt(1, NoOfUnit);
				ResultSet rs = pstmt.executeQuery();
				
				
				if (NoOfUnit <= 100)
				{
					TaxAmount = NoOfUnit * 3;
				}	
				else if (NoOfUnit <= 200)
				{
					TaxAmount = 100 * 3 + (NoOfUnit-100)*4;
				}
				else
				{
					TaxAmount = 100 * 3 + 100 * 4 + (NoOfUnit-200) * 5;
				}
				
	            while (rs.next()) {
					
	            	TaxAmount = rs.getFloat("TaxAmount");
				}
	            con.close();
	             
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return TaxAmount;
			
		}*/
		
		//create method to calculate tax amount
		public double calculateTaxAmount(int BillID) {
			double TaxAmount = 0;
			try(Connection con = connect()) {
				//create a prepared statement
				String getQuery = "select o.Amount, o.NoOfUnits\n" 
						+ "from billing o\n"
						+ "where o.BillID = ?;";
			     
				PreparedStatement pstmt = con.prepareStatement(getQuery);
				pstmt.setInt(1, BillID);
				ResultSet rs = pstmt.executeQuery();
				
				int NoOfUnits = 0;
				
				//iterate through the rows in the result set
	            while (rs.next()) {
					
	            	NoOfUnits = rs.getInt("NoOfUnits");//get from bill table
		
					//calculate tax using if condition
					if(NoOfUnits < 100)
					{
						TaxAmount = NoOfUnits*3;
					}
					else if(NoOfUnits < 200)
					{
						TaxAmount = 100*3+(NoOfUnits-100)*4;
					}
					else
					{
						TaxAmount = 100*3 + 100*4 + (NoOfUnits-200)*5;
					}
				}
	            con.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return TaxAmount;//return valid tax amount
			
		}
		
		
		
		
		
		//create method to calculate total amount
		public double calculateSubAmount(int BillID) {
			double TotalAmount = 0;
			
			try(Connection con = connect()) {
				//create a prepared statement
				String getQuery = "select o.Amount, o.NoOfUnits\n" 
						+ "from billing o\n"
						+ "where o.BillID = ?;";
			     
				PreparedStatement pstmt = con.prepareStatement(getQuery);
				pstmt.setInt(1, BillID);
				ResultSet rs = pstmt.executeQuery();//execute a statement
				
				
				float Amount = 0;
				double TaxAmount = calculateTaxAmount(BillID);//get from calculateTaxAmount() method
				
	            while (rs.next()) {
					
	            	
					Amount = rs.getFloat("Amount");
					
					
				}
	            con.close();
	            TotalAmount = Amount + TaxAmount;//Calculate total amount
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return TotalAmount;
			
		}
		
		//create update method
		public String updatePayment(int PaymentID,String CardType,String CardNumber,String CardHolderName,String CVC,String CardExpireDate,String PaymentDate,int BillID) 
		{
			String output = "";
			try(Connection con = connect()) {
				
				//implement prepared statement
				String updateQuery = "update payment set CardType=?,CardNumber=?,CardHolderName=?,CVC=?,CardExpireDate=?,Status=?,TaxAmount=? ,TotalAmount=? ,PaymentDate=?,BillID=? where PaymentID =?" ;
				
				PreparedStatement pstmt = con.prepareStatement(updateQuery);
				double TaxAmount = this.calculateTaxAmount(BillID);
				double TotalAmount = this.calculateSubAmount(BillID);
				String Status = "pending";
				pstmt.setString(1,CardType);
				pstmt.setString(2,CardNumber);
				pstmt.setString(3,CardHolderName);
				pstmt.setString(4,CVC);
				pstmt.setString(5,CardExpireDate);
				pstmt.setString(6,Status);
				pstmt.setDouble(7, TaxAmount);
				pstmt.setDouble(8,TotalAmount);
				pstmt.setString(9,PaymentDate);
				pstmt.setInt(10,BillID);
				pstmt.setInt(11,PaymentID);
				pstmt.execute();
				con.close();
				System.out.println(PaymentID);
		
				String newItems = getAllPayment();
				output = "{\"status\":\"success\", \"data\": \"" +
						newItems + "\"}";
				
				
			}
			catch(Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";
				System.err.println(e.getMessage());
			}
		return output;	
			
		}
		//create delete method
		public String DeletePayment(int PaymentID) 
		{ 
			String output = ""; 
			try
			{  
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for deleting."; } 
				// create a prepared statement
				String query = "delete from payment where PaymentID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); //prepared statement
				// binding values
				preparedStmt.setInt(1,PaymentID); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newItems = getAllPayment();
				output = "{\"status\":\"success\", \"data\": \"" +
						newItems + "\"}";
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the Payment.\"}";
				System.err.println(e.getMessage());
			} 
			return output; 
		} 
		

}
