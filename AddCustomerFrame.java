import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;


    /**
     * This class creates a new customer GUI form.
     */
public class AddCustomerFrame extends javax.swing.JFrame {
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
    private final String url = "jdbc:derby:inventory_management";
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
        
        customerSubmitButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
			  customerSubmitButtonActionPerformed(event);	
		    }  
		  }
        );                      
    }
    private void customerSubmitButtonActionPerformed(ActionEvent event){
	  int result = 0;
	   try{
	     connection = DriverManager.getConnection(url);
	     insertNewCustomer = connection.prepareStatement("INSERT INTO Customer (customerNo, customerName, customerAddress, customerCity, customerState, customerZip, customerEmail, customerPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	     
	 
	     insertNewCustomer.setInt(1, Integer.parseInt(customerNoTextField.getText()));
	     insertNewCustomer.setString(2, customerNameTextField.getText());
	     insertNewCustomer.setString(3, customerAddressTextField.getText());
	     insertNewCustomer.setString(4, customerCityTextField.getText());
	     insertNewCustomer.setString(5, customerStateTextField.getText());
	     insertNewCustomer.setString(6, customerZipTextField.getText());
	     insertNewCustomer.setString(7, customerEmailTextField.getText());
	     insertNewCustomer.setString(8, customerPhoneTextField.getText());
	     
	     result = insertNewCustomer.executeUpdate();
	     
	      if (result > 0){
		   JOptionPane.showMessageDialog(null, "New Customer Added", "Success", JOptionPane.PLAIN_MESSAGE);
		   this.dispose();	
		   //AppView appView = new AppView();
		   //appView.setVisible(true);
		   //appView.setResizable(false);
		   //appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	     catch (SQLException sqlException){
	       sqlException.printStackTrace();   
	     }
	     
     }            	             
}

