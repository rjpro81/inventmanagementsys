import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;

class AddInvoiceAndBillingFrame extends JFrame{
  private final String url = "jdbc:derby:inventory_management";
  private JLabel invoiceNoLabel;
  private JTextField invoiceNoTextField;
  private JLabel customerNoLabel;
  private JTextField customerNoTextField;
  private JLabel invoiceDateLabel;
  private JTextField invoiceDateTextField;
  private JButton cancelButton;
  private JButton submitButton;
  private JPanel invoiceAndBillingPanel;
  private JPanel invoiceAndBillingComponentsPanel;
  private JPanel invoiceAndBillingInputPanel;
  private JPanel invoiceAndBillingButtonPanel;
  
  
  AddInvoiceAndBillingFrame(){
	super("Add Invoice");
	
	invoiceNoLabel = new JLabel("invoice#:");
	invoiceNoTextField = new JTextField(10);
	customerNoLabel = new JLabel("customer#:");
	customerNoTextField = new JTextField(10);
	invoiceDateLabel = new JLabel("date:");
	invoiceDateTextField = new JTextField(10);  
	cancelButton = new JButton("cancel");
	submitButton = new JButton("submit");
	
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
	invoiceAndBillingInputPanel.add(customerNoTextField);
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
		    String customerNo = customerNoTextField.getText();
		    String invoiceDate = invoiceDateTextField.getText();	
		  
		    if (!invoiceNo.equals("") || !customerNo.equals("") || !invoiceDate.equals("")){
			  int year = Integer.parseInt(invoiceDate.substring(6, 10));
			  int month = Integer.parseInt(invoiceDate.substring(0, 2));
			  int day = Integer.parseInt(invoiceDate.substring(3, 5));
		      Date date = new Date(year, month, day);
		    
		      try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
			    String addInvoiceQuery = "INSERT INTO Invoice (invoiceNo, suppNo, invoiceDate) VALUES ("+invoiceNo+","+customerNo+","+"\'"+date+"\')"; 	
			    int result = stmt.executeUpdate(addInvoiceQuery);
			    
			    invoiceNoTextField.setText("");
			    customerNoTextField.setText("");
			    invoiceDateTextField.setText("");
			  
			    if (result > 0){
				  JOptionPane.showMessageDialog(null, "New invoice added"); 
				  ApplicationFrame.invoiceTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Invoice"));
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
}
