package com.ralph.inventmanagementsys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.time.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

@SuppressWarnings("serial")
class AddItemFrame extends JFrame {
	private final String url = "jdbc:derby:inventory_management";
	  private JLabel itemNoLabel;
	  private JTextField itemNoTextField;
	  private JLabel itemNameLabel;
	  private JTextField itemNameTextField;
	  private JLabel itemQOHLabel;
	  private JTextField itemQOHTextField;
	  private JLabel itemPriceLabel;
	  private JTextField itemPriceTextField; 
	  private JLabel itemExpDateLabel;
	  private JTextField itemExpDateTextField;
	  private JLabel customerNoLabel;
	  private JComboBox<Customer> customerList;
	  private DefaultComboBoxModel<Customer> comboBoxModel;
	  private JButton cancelButton;
	  private JButton submitButton;
	  private JPanel addItemPanel;
	  private JPanel addItemComponentsPanel;
	  private JPanel addItemInputPanel;
	  private JPanel addItemButtonPanel;
	  
	  AddItemFrame(){
	    super("Add Item");
	    
	    itemNoLabel = new JLabel("Item#:");
	    itemNoTextField = new JTextField(10);
	    Random generator = new Random();
	    int num = 999999 + generator.nextInt(9000000);
	    String numAsString = ""+num;
	    itemNoTextField.setText(numAsString);
	    itemNoTextField.setEditable(false);
	    itemNameLabel = new JLabel("Name:");
	    itemNameTextField = new JTextField(10);
	    itemQOHLabel = new JLabel("Qty:");
	    itemQOHTextField = new JTextField(10);
	    itemPriceLabel = new JLabel("Price:");
	    itemPriceTextField = new JTextField(10);
	    itemExpDateLabel = new JLabel("Expiration:");
	    itemExpDateTextField = new JTextField(10);
	    itemExpDateTextField.setText("mm/dd/yyyy");
	    customerNoLabel = new JLabel("Customer:");
	    customerList = new JComboBox<>(getCustomerModel());
	    
	    addItemInputPanel = new JPanel();
	    addItemInputPanel.setLayout(new GridLayout(6, 2, 4, 4));
	    
	    cancelButton = new JButton("cancel");
	    submitButton = new JButton("submit");
	    
	    addItemInputPanel.add(itemNoLabel);
	    addItemInputPanel.add(itemNoTextField);
	    addItemInputPanel.add(itemNameLabel);
	    addItemInputPanel.add(itemNameTextField);
	    addItemInputPanel.add(itemQOHLabel);
	    addItemInputPanel.add(itemQOHTextField);
	    addItemInputPanel.add(itemPriceLabel);
	    addItemInputPanel.add(itemPriceTextField);
	    addItemInputPanel.add(itemExpDateLabel);
	    addItemInputPanel.add(itemExpDateTextField);
	    addItemInputPanel.add(customerNoLabel);
	    addItemInputPanel.add(customerList);
	    
	    addItemButtonPanel = new JPanel();
	    addItemButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    
	    addItemButtonPanel.add(cancelButton);
	    addItemButtonPanel.add(submitButton);
	    
	    addItemComponentsPanel = new JPanel();
	    addItemComponentsPanel.setLayout(new BoxLayout(addItemComponentsPanel, BoxLayout.Y_AXIS));
	    
	    addItemComponentsPanel.add(addItemInputPanel);
	    addItemComponentsPanel.add(addItemButtonPanel);
	    
	    addItemPanel = new JPanel();
	    
	    addItemPanel.add(addItemComponentsPanel);
	    
	    add(addItemPanel);
	    
	    cancelButton.addActionListener(
	      new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  dispose();	
		    }  
		  }
	    );
	    
	    submitButton.addActionListener(
	      new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent evt){
			  int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
	          if (option == 0){
			    String itemNo = itemNoTextField.getText();
			    String itemName = itemNameTextField.getText();
			    String itemQOH = itemQOHTextField.getText();
			    String itemPrice = itemPriceTextField.getText();
			    String itemExpDate = itemExpDateTextField.getText();
			    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			    java.util.Date date = null;
			    try{
				  date = (java.util.Date) formatter.parse(itemExpDate);	
				}
				catch(ParseException e){
				  System.err.println(e);	
				}
				long dateAsLong = date.getTime();
				java.sql.Date d = new java.sql.Date(dateAsLong);
			    int customerNo = getCustomerNoFromName(customerList.getSelectedItem());
			    try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
			      String addItem = "INSERT INTO Item (itemNo, itemName, itemQOH, itemPrice, itemExpDate, suppNo) VALUES ("+itemNo+",\'"+itemName+"\',"+itemQOH+","+itemPrice+",\'"+d+"\',"+customerNo+")";
			      int result = stmt.executeUpdate(addItem);
			      
			      itemNoTextField.setText("");
			      itemNameTextField.setText("");
				  itemQOHTextField.setText("");
				  itemPriceTextField.setText("");
				  itemExpDateTextField.setText("");
			      
			      if (result > 0){
					JOptionPane.showMessageDialog(null, "New record added");
					String activity = "Item#: " + itemNo + " added";
					ApplicationFrame.inventoryDetailTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Item"));  
					addItemActivity(activity, LocalDate.now());
					dispose();
				  } else {
					JOptionPane.showMessageDialog(null, "No record added"); 
					dispose(); 
				  }
			    }
			    catch(SQLException e){
			      System.err.println(e);	
			    }	
		      }  
		    }   
		  }
	    );
	  }
	  private void addItemActivity(String activity, LocalDate date){
	    Path file = Paths.get("inventory_activity.txt");
	    class AddItemActivity extends Activity{
		  AddItemActivity(String a, LocalDate d){
			super(a, d);  
		  }	
		}  
		try(BufferedWriter out = Files.newBufferedWriter(file, Charset.forName("UTF-8"), StandardOpenOption.APPEND)){
		  AddItemActivity aia = new AddItemActivity(activity, date);
		  Object[] rowData = {aia};
		  out.write(aia.toString(), 0, aia.toString().length());
		  ApplicationFrame.inventoryActivityTableModel.addRow(rowData);	
		}
		catch(IOException e){
		  System.err.println(e);	
		}
	  }
	  
	  private DefaultComboBoxModel<Customer> getCustomerModel(){
		comboBoxModel = new DefaultComboBoxModel<>();
	    try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
		  String query = "SELECT * FROM Supplier";
		  ResultSet rs = stmt.executeQuery(query);
		  
		  while (rs.next()){
			comboBoxModel.addElement(new Customer(rs.getInt("suppNo"), rs.getString("suppName"), rs.getString("suppAddress"), rs.getString("suppCity"), rs.getString("suppState"), rs.getInt("suppZip"), rs.getString("suppEmail"), rs.getInt("suppPhone")));  
		  }	
		}
		catch(SQLException e){
		  System.err.println(e);	
		}  
		return comboBoxModel;
	  }
	  
	  private int getCustomerNoFromName(Object customerName){
		  int customerNo = 0;
		  try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
		    String query = "SELECT suppNo FROM Supplier WHERE suppName='"+customerName.toString()+"'";
		    ResultSet rs = stmt.executeQuery(query);
		    System.out.println(customerName.toString());
		    if (rs.next()){
			  customerNo = rs.getInt("suppNo");	
			}
		  }
		  catch(SQLException e){
			System.err.println(e);  
		  }	
		  return customerNo;
	  }
}
