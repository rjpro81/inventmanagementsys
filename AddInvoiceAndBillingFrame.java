package com.ralph.inventmanagementsys;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.text.*;
import java.util.Random;
import java.io.*;

@SuppressWarnings("serial")
class AddInvoiceAndBillingFrame extends JFrame {
	private final String url = "jdbc:derby:inventory_management";
	  private JLabel invoiceNoLabel;
	  private JTextField invoiceNoTextField;
	  private JLabel customerNoLabel;
	  private JLabel invoiceDateLabel;
	  private JTextField invoiceDateTextField;
	  private JButton cancelButton;
	  private JButton submitButton;
	  private JPanel invoiceAndBillingPanel;
	  private JPanel invoiceAndBillingComponentsPanel;
	  private JPanel invoiceAndBillingInputPanel;
	  private JPanel invoiceAndBillingButtonPanel;
	  private JComboBox<Customer> customerList;
	  private DefaultComboBoxModel<Customer> comboboxModel;
	  
	  
	  AddInvoiceAndBillingFrame(){
		super("Add Invoice");
		
		Random generator = new Random();
		int randomNum = 99999999 + generator.nextInt(900000000);
		invoiceNoLabel = new JLabel("Invoice#:");
		invoiceNoTextField = new JTextField(10);
		invoiceNoTextField.setText(""+randomNum);
		invoiceNoTextField.setEditable(false);
		customerNoLabel = new JLabel("Customer#:");
		invoiceDateLabel = new JLabel("Date:");
		invoiceDateTextField = new JTextField("mm/dd/yyyy", 10); 
		cancelButton = new JButton("cancel");
		submitButton = new JButton("submit");
		
		
		comboboxModel = new DefaultComboBoxModel<>();
	    try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
		  String query = "SELECT * FROM Supplier";
		  ResultSet rs = stmt.executeQuery(query);
		  
		  while (rs.next()){
			comboboxModel.addElement(
		    new Customer(rs.getInt("suppNo"), rs.getString("suppName"), rs.getString("suppAddress"), rs.getString("suppCity"), 
		    rs.getString("suppState"), rs.getInt("suppZip"), rs.getString("suppEmail"), rs.getInt("suppPhone")));  
		  }
		}
		catch(SQLException e){
		  System.err.println(e);	
		}
		customerList = new JComboBox<>(comboboxModel);
		
		
		invoiceAndBillingPanel = new JPanel();
		invoiceAndBillingComponentsPanel = new JPanel();
		invoiceAndBillingComponentsPanel.setLayout(new BoxLayout(invoiceAndBillingComponentsPanel, BoxLayout.Y_AXIS));
		invoiceAndBillingInputPanel = new JPanel();
		invoiceAndBillingInputPanel.setLayout(new GridLayout(3, 2, 4, 4));
		invoiceAndBillingButtonPanel = new JPanel();
		invoiceAndBillingButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		invoiceAndBillingInputPanel.add(invoiceNoLabel);
		invoiceAndBillingInputPanel.add(invoiceNoTextField);
		invoiceAndBillingInputPanel.add(customerNoLabel);
		invoiceAndBillingInputPanel.add(customerList);
		invoiceAndBillingInputPanel.add(invoiceDateLabel);
		invoiceAndBillingInputPanel.add(invoiceDateTextField);
		
		invoiceAndBillingButtonPanel.add(cancelButton);
		invoiceAndBillingButtonPanel.add(submitButton);
		
		invoiceAndBillingComponentsPanel.add(invoiceAndBillingInputPanel);
		invoiceAndBillingComponentsPanel.add(invoiceAndBillingButtonPanel);
		
		invoiceAndBillingPanel.add(invoiceAndBillingComponentsPanel);
		
		add(invoiceAndBillingPanel);
		
		cancelButton.addActionListener(
		  new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  AddInvoiceAndBillingFrame.this.dispose();	
			}  
		  }
		);
		
		submitButton.addActionListener(
		  new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
			  if (option == 0){
			    String invoiceNo = invoiceNoTextField.getText();
			    
			    String invoiceDate = invoiceDateTextField.getText();	
			  
			    if (!invoiceNo.equals("") || !invoiceDate.equals("")){
				  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				  java.util.Date date = null;
				  try{
				    date = (java.util.Date) formatter.parse(invoiceDate);
			      }
			      catch(ParseException e){
					System.err.println(e);  
				  }
				  long dateAsLong = date.getTime();
				  Date d = new Date(dateAsLong);
				  Object obj = customerList.getSelectedItem();
			      int customerNo = getCustomerNoFromName(obj);
			    
			      try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
				    String addInvoiceQuery = "INSERT INTO Invoice (invoiceNo, suppNo, invoiceDate) VALUES ("+invoiceNo+","+customerNo+","+"\'"+d+"\')"; 	
				    int result = stmt.executeUpdate(addInvoiceQuery);
				    invoiceNoTextField.setText("");
				    invoiceDateTextField.setText("");
				    LocalDate currentDate = LocalDate.now();
				    if (result > 0){
					  JOptionPane.showMessageDialog(null, "New invoice added"); 
					  ApplicationFrame.invoiceTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Invoice"));
					  addActivity("Invoice#: "+invoiceNo+" added", currentDate);
					  AddInvoiceAndBillingFrame.this.dispose();
				    } else {
					  JOptionPane.showMessageDialog(null, "No new record added");  
				    }
				  }
				  catch(SQLException e){
				    System.err.println(e);	
				  }
			    } else {
				  JOptionPane.showMessageDialog(null, "You must enter a valid input for all fields");
			    }
			  } 
			} 
		  }
		);
	  }
	  
	  private int getCustomerNoFromName(Object customerName){
		String name = customerName.toString();
		int customerNo = 0;
		try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
	      String query = "SELECT suppNo FROM Supplier WHERE suppName='"+name+"'";
	      ResultSet rs = stmt.executeQuery(query);
	      
	      if (rs.next()){
		    customerNo = rs.getInt("suppNo");
		  }
		}
		catch(SQLException e){
		  System.err.println(e);	
		}  
		return customerNo;
	  }
	  
	  private void addActivity(String activity, LocalDate date){
		class AddInvoiceActivity extends Activity{
		  AddInvoiceActivity(String a, LocalDate b){
			super(a, b);  
		  }
		}  
		File file = new File("inventory_activity.txt");
		
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file, true))){
		  AddInvoiceActivity addInvoice = new AddInvoiceActivity(activity, date);
		  out.write(addInvoice.toString(), 0, addInvoice.toString().length());	
		  Object[] rowData = {addInvoice};
		  ApplicationFrame.inventoryActivityTableModel.addRow(rowData);
		}
		catch(IOException e){
		  System.err.println(e);	
		}
	  }
}
