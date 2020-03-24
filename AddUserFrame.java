import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;

    /**
     * This class creates new form AddUserAccountView
     */

public class AddUserFrame extends JFrame {
	private JButton submitButton;
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
    private JTextField passwordTextField;
    private JTextField verifyPasswordTextField;
    private Connection connection;
    private final String url = "jdbc:derby:inventory_management;create=false";
    private PreparedStatement insertNewUser;
    private JPanel userPanel;
    private JPanel userComponentsPanel;
    private JPanel buttonPanel;

    public AddUserFrame() {
		super("New User");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
                             
    private void initComponents() {
        userNoLabel = new JLabel("User #:");
        firstnameLabel = new JLabel("First Name:");
        lastnameLabel = new JLabel("Last Name:");
        emailLabel = new JLabel("Email:");
        phoneLabel = new JLabel("Phone:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        verifyPasswordLabel = new JLabel("Verify Password:");
        
        userNoTextField = new JTextField(15);
        firstnameTextField = new JTextField(15);
        lastnameTextField = new JTextField(15);
        emailTextField = new JTextField(15);
        phoneTextField = new JTextField(15);
        usernameTextField = new JTextField(15);
        passwordTextField = new JTextField(15);
        verifyPasswordTextField = new JTextField(15);
        submitButton = new JButton("Create");
        userPanel = new JPanel(); 
        userComponentsPanel = new JPanel();
        userComponentsPanel.setLayout(new GridLayout(8, 2));
        buttonPanel = new JPanel();
        
        userComponentsPanel.add(userNoLabel);
        userComponentsPanel.add(userNoTextField);
        userComponentsPanel.add(firstnameLabel);
        userComponentsPanel.add(firstnameTextField);
        userComponentsPanel.add(lastnameLabel);
        userComponentsPanel.add(lastnameTextField);
        userComponentsPanel.add(emailLabel);
        userComponentsPanel.add(emailTextField);
        userComponentsPanel.add(phoneLabel);
        userComponentsPanel.add(phoneTextField);
        userComponentsPanel.add(usernameLabel);
        userComponentsPanel.add(usernameTextField);
        userComponentsPanel.add(passwordLabel);
        userComponentsPanel.add(passwordTextField);
        userComponentsPanel.add(verifyPasswordLabel);
        userComponentsPanel.add(verifyPasswordTextField);
        buttonPanel.add(submitButton);
        
        userPanel.add(userComponentsPanel);
        userPanel.add(buttonPanel);
        add(userPanel);
       
        submitButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  submitButtonActionPerformed(evt);	
		    }  
		  }
        );
    }
            
    private void submitButtonActionPerformed(ActionEvent evt){
		int result = 0;
	   try{
	     connection = DriverManager.getConnection(url);
	     insertNewUser = connection.prepareStatement("INSERT INTO User (userNo, userFirstName, userLastName, userEmail, userPhone, userName, userPassword) VALUES (?, ?, ?, ?, ?, ?, ?)");
	     
	     if (!verifyPasswordTextField.getText().equals(passwordTextField.getText())){
		   JOptionPane.showMessageDialog(null, "Password confirmation did not match", "Invalid", JOptionPane.PLAIN_MESSAGE);
		   userNoTextField.setText("");
		   firstnameTextField.setText("");
           lastnameTextField.setText("");
           emailTextField.setText("");
           phoneTextField.setText("");
           usernameTextField.setText("");
           passwordTextField.setText("");
           verifyPasswordTextField.setText("");
		 } else {
	     insertNewUser.setInt(1, Integer.parseInt(userNoTextField.getText()));
	     insertNewUser.setString(2, firstnameTextField.getText());
	     insertNewUser.setString(3, lastnameTextField.getText());
	     insertNewUser.setString(4, emailTextField.getText());
	     insertNewUser.setString(5, phoneTextField.getText());
	     insertNewUser.setString(6, usernameTextField.getText());
	     insertNewUser.setString(7, passwordTextField.getText());
	     
	     result = insertNewUser.executeUpdate();
	     
	      if (result > 0){
		   JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.PLAIN_MESSAGE);
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
             passwordTextField.setText("");
             verifyPasswordTextField.setText("");
           }      
	     }   
	   }
	   catch (SQLException sqlException){
	     sqlException.printStackTrace();   
	   }
    }                              
}
