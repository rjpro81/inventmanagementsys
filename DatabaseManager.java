package com.ralph.inventmanagementsys;

/**
 * This class provides a database connectivity management for the application.
 * @author Ralph Julsaint
 */

import java.sql.*;

class DatabaseManager {
	private static final String URL = "jdbc:derby:inventory_management";
	  
	  private Connection connection;
	  private PreparedStatement insertNewSupplier;
	  public DatabaseManager(){
	    try{
	      connection = DriverManager.getConnection(URL);
	      connection.prepareStatement(
	        "INSERT INTO Item (itemNo, itemName, itemPrice, suppNo) VALUES (?, ?, ?, ?)");
	      connection.prepareStatement(
	        "SELECT * FROM Supplier");
	      insertNewSupplier = connection.prepareStatement(
	      "INSERT INTO Supplier (suppNo, suppName, suppAddress, suppCity, suppState,suppZip, suppEmail, suppPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	      connection.prepareStatement(
	      "SELECT invoiceNo FROM Invoice");
	 
	      
	    }
	    catch (SQLException sqlException){
	      sqlException.printStackTrace();
	      System.exit(1);
	    }
	  }
	  
	  public int addSupplier(int suppNo, String suppName, String suppAddress,
	  String suppCity, String suppState, int suppZip, String suppEmail,
	  String suppPhone){
		 int result = 0;
		 try{
		   insertNewSupplier.setInt(1, suppNo);
		   insertNewSupplier.setString(2, suppName);
		   insertNewSupplier.setString(3, suppAddress);
		   insertNewSupplier.setString(4, suppCity);
		   insertNewSupplier.setString(5, suppState);
		   insertNewSupplier.setInt(6, suppZip);
		   insertNewSupplier.setString(7, suppEmail);
		   insertNewSupplier.setString(8, suppPhone);	
		   
		   result = insertNewSupplier.executeUpdate(); 
	     } 
	     catch (SQLException sqlException){
		   sqlException.printStackTrace();
		   close();	 
	     }
	     
	     return result;
	     
	  }
	 
	  public void close(){
	    try{
	      connection.close();
	    }
	    catch (SQLException sqlException){
	      sqlException.printStackTrace();
	    }
	  }
}
