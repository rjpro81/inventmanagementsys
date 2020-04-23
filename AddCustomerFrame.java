package com.ralph.inventmanagementsys;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class creates a window to add new customers to database.
 * @author Ralph Julsaint
 */
public class AddCustomerFrame extends JFrame{
    private final ApplicationContext context = new AnnotationConfigApplicationContext(InventoryConfiguration.class);
    private JButton customerSubmitButton;
    private JLabel customerNoLabel;
    private JLabel customerNameLabel;
    private JLabel customerAddressLabel;
    private JLabel customerCityLabel;
    private JLabel customerStateLabel;
    private JLabel customerZipLabel;
    private JLabel customerEmailLabel;
    private JLabel customerPhoneLabel;
    private JTextField customerNoTextField;
    private JTextField customerNameTextField;
    private JTextField customerAddressTextField;
    private JTextField customerCityTextField;
    private JTextField customerStateTextField;
    private JTextField customerZipTextField;
    private JTextField customerEmailTextField;
    private JTextField customerPhoneTextField;
    private JPanel customerPanel;
    private JPanel customerButtonPanel;
    private PreparedStatement insertNewCustomer;
    //private final String url = "jdbc:derby://localhost:1527/inventory_management";
    private Connection connection;

    
    public AddCustomerFrame() {
	super("New Customer");
        initComponents();
    }

                              
    private void initComponents() {
        customerNoTextField = new JTextField(10);
        customerNameTextField = new JTextField(10);
        customerAddressTextField = new JTextField(10);
        customerCityTextField = new JTextField(10);
        customerStateTextField = new JTextField(10);
        customerZipTextField = new JTextField(10);
        customerEmailTextField = new JTextField(10);
        customerPhoneTextField = new JTextField(10);
        customerNoLabel = new JLabel("Customer #:");
        customerNameLabel = new JLabel("Customer Name:");
        customerAddressLabel = new JLabel("Customer Address:");
        customerCityLabel = new JLabel("Customer City:");
        customerStateLabel = new JLabel("Customer State:");
        customerZipLabel = new JLabel("Customer Zip:");
        customerEmailLabel = new JLabel("Customer Email:");
        customerPhoneLabel = new JLabel("Customer Phone:");
        customerSubmitButton = new JButton("Submit");
        setLayout(new BorderLayout());
        customerButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        customerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerPanel.add(customerNoLabel);
        customerPanel.add(customerNoTextField);
        customerPanel.add(customerNameLabel);
        customerPanel.add(customerNameTextField);
        customerPanel.add(customerAddressLabel);
        customerPanel.add(customerAddressTextField);
        customerPanel.add(customerCityLabel);
        customerPanel.add(customerCityTextField);
        customerPanel.add(customerStateLabel);
        customerPanel.add(customerStateTextField);
        customerPanel.add(customerZipLabel);
        customerPanel.add(customerZipTextField);
        customerPanel.add(customerEmailLabel);
        customerPanel.add(customerEmailTextField);
        customerPanel.add(customerPhoneLabel);
        customerPanel.add(customerPhoneTextField);
        customerButtonPanel.add(customerSubmitButton);
        
        add(customerPanel, BorderLayout.CENTER);
        add(customerButtonPanel, BorderLayout.SOUTH);
        
        customerSubmitButton.addActionListener((ActionEvent event) -> {
            customerSubmitButtonActionPerformed(event);
        });                      
    }
    private void customerSubmitButtonActionPerformed(ActionEvent event){
	int result;
        DatabaseManager obj = context.getBean(DatabaseManager.class);
	try{
	    
	    PreparedStatement ps = obj.createStatement("INSERT INTO Customer (customerNo, customerName, customerAddress, customerCity, customerState, customerZip, customerEmail, customerPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	    ps.setInt(1, Integer.parseInt(customerNoTextField.getText()));
	    ps.setString(2, customerNameTextField.getText());
	    ps.setString(3, customerAddressTextField.getText());
	    ps.setString(4, customerCityTextField.getText());
	    ps.setString(5, customerStateTextField.getText());
	    ps.setString(6, customerZipTextField.getText());
	    ps.setString(7, customerEmailTextField.getText());
	    ps.setString(8, customerPhoneTextField.getText());
	     
	    result = ps.executeUpdate();
	     
	    if (result > 0){
		JOptionPane.showMessageDialog(null, "New Customer Added", "Success", JOptionPane.PLAIN_MESSAGE);
		this.dispose();	
	    } 
	    else{
                JOptionPane.showMessageDialog(null, "Invalid", "Invalid", JOptionPane.PLAIN_MESSAGE);	
                customerNoTextField.setText("");
                customerNameTextField.setText("");
                customerAddressTextField.setText("");
                customerCityTextField.setText("");
                customerStateTextField.setText("");
                customerZipTextField.setText("");
                customerEmailTextField.setText("");
                customerPhoneTextField.setText("");
            }      
	}   
	catch (SQLException e){
            System.err.println(e);
	}
	     
    }  
}
