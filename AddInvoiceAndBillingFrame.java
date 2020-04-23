package com.ralph.inventmanagementsys;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Ralph Julsaint
 */
public class AddInvoiceAndBillingFrame extends JFrame{
    private final String url = "jdbc:derby://localhost:1527/inventory_management";
    private final JLabel invoiceNoLabel;
    private JTextField invoiceNoTextField;
    private final JLabel customerNoLabel;
    private final JLabel invoiceDateLabel;
    private JTextField invoiceDateTextField;
    private final JButton cancelButton;
    private final JButton submitButton;
    private final JPanel invoiceAndBillingPanel;
    private final JPanel invoiceAndBillingComponentsPanel;
    private final JPanel invoiceAndBillingInputPanel;
    private final JPanel invoiceAndBillingButtonPanel;
    private JComboBox<Customer> customerList;
    private DefaultComboBoxModel<Customer> comboboxModel;
    private final ApplicationContext context = new AnnotationConfigApplicationContext(InventoryConfiguration.class);
	  
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
	    String query = "SELECT * FROM Customer";
	    ResultSet rs = stmt.executeQuery(query);
		  
	    while (rs.next()){
		comboboxModel.addElement(
		new Customer(rs.getInt("CUSTOMERNO"), rs.getString("CUSTOMERNAME"), rs.getString("CUSTOMERADDRESS"), rs.getString("CUSTOMERCITY"), 
		rs.getString("CUSTOMERSTATE"), rs.getString("CUSTOMERZIP"), rs.getString("CUSTOMEREMAIL"), rs.getString("CUSTOMERPHONE")));  
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
		
	cancelButton.addActionListener((ActionEvent evt) -> {
            AddInvoiceAndBillingFrame.this.dispose();
        });
		
	submitButton.addActionListener((ActionEvent evt) -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0){
                String invoiceNo = invoiceNoTextField.getText();
                String invoiceDate = invoiceDateTextField.getText();
                if (!invoiceNo.equals("") || !invoiceDate.equals("")){
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    java.util.Date date;
                    long dateAsLong = 0;
                    try{
                        date = (java.util.Date) formatter.parse(invoiceDate);
                        dateAsLong = date.getTime();
                    }
                    catch(ParseException e){
                        System.err.println(e);
                    }
                    
                    Date d = new Date(dateAsLong);
                    Object obj = customerList.getSelectedItem();
                    int customerNo = getCustomerNoFromName(obj);
                    
                    try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
                        String addInvoiceQuery = "INSERT INTO Invoice (invoiceNo, customerNo, invoiceDate) VALUES ("+invoiceNo+","+customerNo+","+"\'"+d+"\')";
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
        });
    }	  
    private int getCustomerNoFromName(Object customerName){
	String name = customerName.toString();
	int customerNo = 0;
	try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
	    String query = "SELECT customerNo FROM Customer WHERE customerName='"+name+"'";
	    ResultSet rs = stmt.executeQuery(query);  
	    if (rs.next()){
		customerNo = rs.getInt("customerNo");
	    }
	}
	catch(SQLException e){
	    System.err.println(e);	
	}  
	return customerNo;
    }
	  
    private void addActivity(String a, LocalDate d){
	class AddInvoiceActivity extends Activity{
            LocalDate d;
	    AddInvoiceActivity(String a, LocalDate d){
		super(a);  
                this.d = d;
	    }
            LocalDate getDate(){
                return d;
            }
            @Override
            public String toString(){
                return String.format(getActivity()+" on "+ getDate());
            }
	}  
	AddInvoiceActivity aia = new AddInvoiceActivity(a, d);
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        String addInvoiceStatement = "INSERT INTO Activity activity VALUES (?)";
        PreparedStatement stmt = obj.createStatement(addInvoiceStatement);
        try{
            stmt.setString(1, aia.toString());
            stmt.executeUpdate();
            ApplicationFrame.inventoryActivityTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Activity"));
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }    
}
