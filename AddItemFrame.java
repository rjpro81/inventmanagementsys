package com.ralph.inventmanagementsys;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class creates a window to add new Items to database.
 * @author Ralph Julsaint
 */
public class AddItemFrame extends JFrame{
    private final String url = "jdbc:derby://localhost:1527/inventory_management";
    private final JLabel itemNoLabel;
    private JTextField itemNoTextField;
    private final JLabel itemNameLabel;
    private JTextField itemNameTextField;
    private final JLabel itemQOHLabel;
    private JTextField itemQOHTextField;
    private final JLabel itemPriceLabel;
    private JTextField itemPriceTextField; 
    private final JLabel itemExpDateLabel;
    private JTextField itemExpDateTextField;
    private final JLabel customerNoLabel;
    private JComboBox<Customer> customerList;
    private DefaultComboBoxModel<Customer> comboBoxModel;
    private final JButton cancelButton;
    private final JButton submitButton;
    private final JPanel addItemPanel;
    private final JPanel addItemComponentsPanel;
    private final JPanel addItemInputPanel;
    private final JPanel addItemButtonPanel;
    private final ApplicationContext context = new AnnotationConfigApplicationContext(InventoryConfiguration.class);
	  
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
	    
        cancelButton.addActionListener((ActionEvent evt) -> {
            dispose();
        });
	    
	submitButton.addActionListener((ActionEvent evt) -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0){
                String itemNo = itemNoTextField.getText();
                String itemName = itemNameTextField.getText();
                String itemQOH = itemQOHTextField.getText();
                String itemPrice = itemPriceTextField.getText();
                String itemExpDate = itemExpDateTextField.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                java.util.Date date;
                long dateAsLong = 0;
                try{
                    date = (java.util.Date) formatter.parse(itemExpDate);
                    dateAsLong = date.getTime();
                }
                catch(ParseException e){
                    System.err.println(e);
                }
                java.sql.Date d = new java.sql.Date(dateAsLong);
                int customerNo = getCustomerNoFromName(customerList.getSelectedItem());
                DatabaseManager obj = context.getBean(DatabaseManager.class);
                String addItem = "INSERT INTO Item (itemNo, itemName, itemQOH, itemPrice, itemExpDate, customerNo) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = obj.createStatement(addItem);
                try{
                    ps.setString(1, itemNo);
                    ps.setString(2, itemName);
                    ps.setString(3, itemQOH);
                    ps.setString(4, itemPrice);
                    ps.setString(5, itemExpDate);
                    ps.setInt(6, customerNo);
                    int result = ps.executeUpdate();
                    
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
        });
    }
    private void addItemActivity(String a, LocalDate d){
	class AddItemActivity extends Activity{
            LocalDate d;
	    AddItemActivity(String a, LocalDate d){
		super(a);
                this.d = d;
	    }	
            LocalDate getDate(){
                return d;
            }
            
            @Override
            public String toString(){
                return String.format(getActivity()+" on "+getDate());
            }
	}  
	AddItemActivity aia = new AddItemActivity(a, d);
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        String addActivityStatement = "INSERT INTO Activity activity VALUES (?)";
        PreparedStatement stmt = obj.createStatement(addActivityStatement);
        
        try{
            stmt.setString(1, aia.toString());
            int result = stmt.executeUpdate();
            ApplicationFrame.inventoryActivityTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Activity"));
        }
        catch(SQLException e){
            System.err.println(e);   
        }    
    }
	  
    private DefaultComboBoxModel<Customer> getCustomerModel(){
	comboBoxModel = new DefaultComboBoxModel<>();
	try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
	    String query = "SELECT * FROM Customer";
	    ResultSet rs = stmt.executeQuery(query);	  
	    while (rs.next()){
		comboBoxModel.addElement(new Customer(rs.getInt("CUSTOMERNO"), rs.getString("CUSTOMERNAME"), rs.getString("CUSTOMERADDRESS"), rs.getString("CUSTOMERCITY"), rs.getString("CUSTOMERSTATE"), rs.getString("CUSTOMERZIP"), rs.getString("CUSTOMEREMAIL"), rs.getString("CUSTOMERPHONE")));  
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
	    String query = "SELECT customerNo FROM Customer WHERE customerName='"+customerName.toString()+"'";
	    ResultSet rs = stmt.executeQuery(query);
	    System.out.println(customerName.toString());
	    if (rs.next()){
		customerNo = rs.getInt("customerNo");	
	    }
	}
	catch(SQLException e){
	    System.err.println(e);  
	}	
	return customerNo;
    }    
}
