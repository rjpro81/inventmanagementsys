import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.time.*;
import java.io.*;

    /**
     * This class creates new form AddUserAccountView
     */

public class AddUserFrame extends JFrame {
	private JButton submitButton;
	private JButton cancelButton;
    private JLabel userNoLabel;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel verifyPasswordLabel;
    private JTextField userNoTextField;
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JPasswordField verifyPasswordField;
    private Connection connection;
    private final String url = "jdbc:derby:inventory_management;create=false";
    private PreparedStatement insertNewUser;
    private JPanel userPanel;
    private JPanel userComponentsPanel;
    private JPanel userInputPanel;
    private JPanel buttonPanel;

    public AddUserFrame() {
		super("New User");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
                             
    private void initComponents() {
        userNoLabel = new JLabel("User#:");
        firstnameLabel = new JLabel("First Name:");
        lastnameLabel = new JLabel("Last Name:");
        emailLabel = new JLabel("Email:");
        phoneLabel = new JLabel("Phone:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        verifyPasswordLabel = new JLabel("Verify Password:");
        
        Random generator = new Random();
        int randomInt = 9999 + generator.nextInt(90000);
        userNoTextField = new JTextField(12);
        userNoTextField.setText(""+randomInt);
        userNoTextField.setEditable(false);
        firstnameTextField = new JTextField(12);
        lastnameTextField = new JTextField(12);
        emailTextField = new JTextField(12);
        phoneTextField = new JTextField(12);
        usernameTextField = new JTextField(12);
        passwordField = new JPasswordField(12);
        verifyPasswordField = new JPasswordField(12);
        cancelButton = new JButton("cancel");
        submitButton = new JButton("submit");
        userPanel = new JPanel(); 
        userInputPanel = new JPanel(new GridLayout(8, 2, 4, 4));
        userComponentsPanel = new JPanel();
        userComponentsPanel.setLayout(new BoxLayout(userComponentsPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        userInputPanel.add(userNoLabel);
        userInputPanel.add(userNoTextField);
        userInputPanel.add(firstnameLabel);
        userInputPanel.add(firstnameTextField);
        userInputPanel.add(lastnameLabel);
        userInputPanel.add(lastnameTextField);
        userInputPanel.add(emailLabel);
        userInputPanel.add(emailTextField);
        userInputPanel.add(phoneLabel);
        userInputPanel.add(phoneTextField);
        userInputPanel.add(usernameLabel);
        userInputPanel.add(usernameTextField);
        userInputPanel.add(passwordLabel);
        userInputPanel.add(passwordField);
        userInputPanel.add(verifyPasswordLabel);
        userInputPanel.add(verifyPasswordField);
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        
        userComponentsPanel.add(userInputPanel);
        userComponentsPanel.add(buttonPanel);
        
        userPanel.add(userComponentsPanel);
        add(userPanel);
       
        submitButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  submitButtonActionPerformed(evt);	
		    }  
		  }
        );
        
        cancelButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  AddUserFrame.this.dispose();	
			}  
		  }
        );
    }
            
    private void submitButtonActionPerformed(ActionEvent evt){
		int result = 0;
	   try{
	     connection = DriverManager.getConnection(url);
	     insertNewUser = connection.prepareStatement("INSERT INTO App_User (userNo, userFirstName, userLastName, userEmail, userPhone, userName, userPassword) VALUES (?, ?, ?, ?, ?, ?, ?)");
	     
	     if (!verifyPasswordField.getText().equals(passwordField.getText())){
		   JOptionPane.showMessageDialog(null, "Password confirmation did not match", "Invalid", JOptionPane.PLAIN_MESSAGE);
		   userNoTextField.setText("");
		   firstnameTextField.setText("");
           lastnameTextField.setText("");
           emailTextField.setText("");
           phoneTextField.setText("");
           usernameTextField.setText("");
           passwordField.setText("");
           verifyPasswordField.setText("");
		 } else {
	     insertNewUser.setInt(1, Integer.parseInt(userNoTextField.getText()));
	     insertNewUser.setString(2, firstnameTextField.getText());
	     insertNewUser.setString(3, lastnameTextField.getText());
	     insertNewUser.setString(4, emailTextField.getText());
	     insertNewUser.setString(5, phoneTextField.getText());
	     insertNewUser.setString(6, usernameTextField.getText());
	     insertNewUser.setString(7, passwordField.getText());
	     
	     result = insertNewUser.executeUpdate();
	     
	      if (result > 0){
		   JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.PLAIN_MESSAGE);
		   String userNo = userNoTextField.getText();
		   addUserActivity("User#: "+userNo+" added", LocalDate.now());
		   userNoTextField.setText("");
		   firstnameTextField.setText("");
           lastnameTextField.setText("");
           emailTextField.setText("");
           phoneTextField.setText("");
           usernameTextField.setText("");
           passwordField.setText("");
           verifyPasswordField.setText("");
		   this.dispose();	 
	      } 
	       else{
             JOptionPane.showMessageDialog(null, "Invalid", "Invalid", JOptionPane.PLAIN_MESSAGE);	
             userNoTextField.setText("");
             firstnameTextField.setText("");
             lastnameTextField.setText("");
             emailTextField.setText("");
             phoneTextField.setText("");
             usernameTextField.setText("");
             passwordField.setText("");
             verifyPasswordField.setText("");
           }      
	     }   
	   }
	   catch (SQLException sqlException){
	     sqlException.printStackTrace();   
	   }
    }   
    private void addUserActivity(String a, LocalDate d){
	  File file = new File("inventory_activity.txt");
	  Activity userActivity = new Activity(a, d){
	    @Override
	    public String toString(){
		  return String.format("%s -- on %s%n", a, d.toString());	
		}	 
	  };   
	  
	  try(BufferedWriter out = new BufferedWriter(new FileWriter(file, true))){
	     out.write(userActivity.toString(), 0, userActivity.toString().length());
	     Object[] rowData = {userActivity};
	     ApplicationFrame.inventoryActivityTableModel.addRow(rowData);
	  }
	  catch(IOException e){
		System.err.println(e);  
	  }
    }                           
}
