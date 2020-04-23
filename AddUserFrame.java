package com.ralph.inventmanagementsys;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class creates a window to add users to the database for application access.
 * @author Ralph Julsaint
 */
public class AddUserFrame extends JFrame{
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
    private final String url = "jdbc:derby://localhost:1527/inventory_management";
    private PreparedStatement insertNewUser;
    private JPanel userPanel;
    private JPanel userComponentsPanel;
    private JPanel userInputPanel;
    private JPanel buttonPanel;
    private final ApplicationContext context = new AnnotationConfigApplicationContext(InventoryConfiguration.class);

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
       
        submitButton.addActionListener((ActionEvent evt) -> {
            submitButtonActionPerformed(evt);
        });
        
        cancelButton.addActionListener((ActionEvent evt) -> {
            AddUserFrame.this.dispose();
        });
    }
            
    private void submitButtonActionPerformed(ActionEvent evt){
	int result;
	String pass = "";
	String verifyPass = "";
	char[] passArray = passwordField.getPassword();
	char[] verifyPassArray = verifyPasswordField.getPassword();
	   
	for (char p : passArray)
	    pass += p;
	   
	for (char vp : verifyPassArray)
	    verifyPass += vp;
        
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        PreparedStatement stmt = obj.createStatement("INSERT INTO App_User (userNo, userFirstName, userLastName, userEmail, userPhone, userName, userPassword) VALUES (?, ?, ?, ?, ?, ?, ?)");
	try{ 
	    if (!verifyPass.equals(pass)){
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
	        stmt.setInt(1, Integer.parseInt(userNoTextField.getText()));
	        stmt.setString(2, firstnameTextField.getText());
	        stmt.setString(3, lastnameTextField.getText());
	        stmt.setString(4, emailTextField.getText());
	        stmt.setString(5, phoneTextField.getText());
	        stmt.setString(6, usernameTextField.getText());
	        stmt.setString(7, pass);
	     
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
	        } else {
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
	catch (SQLException e){
	    System.err.println(e);   
	}
    }   
    private void addUserActivity(String a, LocalDate d){
	class AddUserActivity extends Activity{
            LocalDate d;
            AddUserActivity(String a, LocalDate d){
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
	  
	AddUserActivity aua = new AddUserActivity(a, d);
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        String addUserStatement = "INSERT INTO Activity activity VALUES (?)";
        PreparedStatement stmt = obj.createStatement(addUserStatement);
        try{
            stmt.setString(1, aua.toString());
            stmt.executeUpdate();
            ApplicationFrame.inventoryActivityTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Activity"));
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }    
}
