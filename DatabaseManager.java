
/**
 * This class provides a database connectivity management for the application.
 * @author Ralph Julsaint
 */

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DatabaseManager {
  private static final String URL = "jdbc:derby:inventory_management;create=false;";
  private static final String username = "ralph";
  private static final String password = "04147676";
  
  private Connection connection;
  private PreparedStatement insertNewItem;;
  private PreparedStatement querySuppliers;
  private PreparedStatement insertNewSupplier;
  private PreparedStatement queryInvoices;
  private Statement statement;
  
  public DatabaseManager(){
    try{
      connection = DriverManager.getConnection(URL, username, password);
      insertNewItem = connection.prepareStatement(
        "INSERT INTO Item (itemNo, itemName, itemPrice, suppNo) VALUES (?, ?, ?, ?)");
      querySuppliers = connection.prepareStatement(
        "SELECT * FROM Supplier");
      insertNewSupplier = connection.prepareStatement(
      "INSERT INTO Supplier (suppNo, suppName, suppAddress, suppCity, suppState,suppZip, suppEmail, suppPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
      queryInvoices = connection.prepareStatement(
      "SELECT invoiceNo FROM Invoice");
 
      
    }
    catch (SQLException sqlException){
      sqlException.printStackTrace();
      System.exit(1);
    }
  }
  
  
  public int addItem(int itemNo, String itemName, String itemDesc, double itemPrice, int suppNo){
    int result = 0;
     try{
       insertNewItem.setInt(1, itemNo);
       insertNewItem.setString(2, itemName);
       insertNewItem.setString(3, itemDesc);
       insertNewItem.setDouble(4, itemPrice);
       insertNewItem.setInt(5, suppNo);
       
       result = insertNewItem.executeUpdate();
     }
     catch (SQLException sqlException){
       sqlException.printStackTrace();
       close();
     }
     
    return result;
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
  
 
  
  public int getItemCount(){
	int count = 0;
	ResultSet resultSet = null;
	
	try{
	  statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	  resultSet = statement.executeQuery("SELECT * FROM Item");
	  resultSet.last();
	  count = resultSet.getRow();
	  resultSet.beforeFirst();
    }
    catch (SQLException sqlException){
	  sqlException.printStackTrace();	
    }
    finally{
	  try{
		resultSet.close();  
      }	
      catch (SQLException sqlException){
		sqlException.printStackTrace();  
	  }
    }
    return count;
  }
  
  public double getAnnualSpending(){
	double annualSpending = 0.0;
	ResultSet resultSet = null;
	ResultSetMetaData metaData = null;
	
	try{
	  statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	  resultSet = statement.executeQuery("SELECT itemPrice SUM(*) AS annualSpending FROM Item");	
	  annualSpending = resultSet.getDouble("annualSpending");
	}  
	catch (SQLException sqlException){
	  sqlException.printStackTrace();	
    }
    finally{
	  try{
		resultSet.close();  
	  }	
	  catch (SQLException sqlException){
		sqlException.printStackTrace();  
	  }
	}
	return annualSpending;
  }
  
  public int getExpiredItemCount(){
	int count = 0;
	ResultSet resultSet = null;
	
	try{
	  statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	  resultSet = statement.executeQuery("SELECT * FROM Item WHERE itemExpDate > CURRENT_DATE");	
	  resultSet.last();
	  count = resultSet.getRow();
	  resultSet.beforeFirst();
    }  
    catch (SQLException sqlException){
	  sqlException.printStackTrace();	
	}
	finally{
	  try{
		resultSet.close();  
	  }	
	  catch(SQLException sqlException){
		sqlException.printStackTrace();  
	  }
	}
	return count;
  }
  
  public List<String> getAllFlaggedItems(){
	List<String> results = null;
	ResultSet resultSet = null;
	  
	try{
	  statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	  resultSet = statement.executeQuery("SELECT * FROM Item WHERE itemExpDate > CURRENT_DATE");	
	  
	  while (resultSet.next()){
		results.add(
		  resultSet.getString("itemNo")
		);  
	  }
    }  
    catch (SQLException sqlException){
	  sqlException.printStackTrace();	
	}
	finally{
	  try{
		resultSet.close();  
	  }	
	  catch(SQLException sqlException){
		sqlException.printStackTrace();  
	  }
	}
	return results;
  }
  
  public List<Customer> getAllSuppliers(){
    List<Customer> results = null;
    ResultSet resultSet = null;
    
    try{
      resultSet = querySuppliers.executeQuery();
      results = new ArrayList<>();
      
      while (resultSet.next()){
        results.add(new Customer(
        resultSet.getInt("customerNo"),
        resultSet.getString("customerName"),
        resultSet.getString("customerAddress"),
        resultSet.getString("customerCity"),
        resultSet.getString("customerState"),
        resultSet.getInt("customerZip"),
        resultSet.getString("customerEmail"),
        resultSet.getInt("customerPhone")));
      }
    }
    catch (SQLException sqlException){
      sqlException.printStackTrace();
    }
    finally{
      try{
        resultSet.close();
      }
      catch(SQLException sqlException){
        sqlException.printStackTrace();
        close();
      }
    }
    return results;
  }
  
  public List <Invoice> getAllInvoices(){
	List<Invoice> results = null;
	ResultSet resultSet = null;
	
	try{
	  results = new ArrayList<>();
	  while (resultSet.next()){
		results.add(
		  new Invoice(
		  resultSet.getString("invoiceDate"),
		  resultSet.getString("invoiceNo"),
		  resultSet.getString("suppNo"),
		  resultSet.getString("orderNo"),
		  resultSet.getString("orderDate"),
		  resultSet.getString("itemNo")
		  )
		);  
	  }	
	}
	catch (SQLException sqlException){
	  sqlException.printStackTrace();	
    }
    finally{
	  try{
	    resultSet.close();	
	  }
	  catch (SQLException sqlException){
		sqlException.printStackTrace();  
	  }
    }
    return results;
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
