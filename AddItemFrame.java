import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;

class AddItemFrame extends JFrame{
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
  private JTextField customerNoTextField;
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
    customerNoLabel = new JLabel("Customer#:");
    customerNoTextField = new JTextField(10);
    
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
    addItemInputPanel.add(customerNoTextField);
    
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
		    int month = Integer.parseInt(itemExpDate.substring(0, 2));
		    int day = Integer.parseInt(itemExpDate.substring(3, 5));
		    int year = Integer.parseInt(itemExpDate.substring(6, 10));
		    Date d = new Date(year, month, day);
		    String customerNo = customerNoTextField.getText();
		    try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
		      String addItem = "INSERT INTO Item (itemNo, itemName, itemQOH, itemPrice, itemExpDate, suppNo) VALUES ("+itemNo+",\'"+itemName+"\',"+itemQOH+","+itemPrice+",\'"+d+"\',"+customerNo+")";
		      int result = stmt.executeUpdate(addItem);
		      
		      itemNoTextField.setText("");
		      itemNameTextField.setText("");
			  itemQOHTextField.setText("");
			  itemPriceTextField.setText("");
			  itemExpDateTextField.setText("");
			  customerNoTextField.setText("");
		      
		      if (result > 0){
				JOptionPane.showMessageDialog(null, "New record added");
				dispose();
				ApplicationFrame.inventoryDetailTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Item"));  
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
}
