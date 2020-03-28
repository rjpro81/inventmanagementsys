import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import javax.swing.table.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import javax.swing.text.*;
import java.util.List;
import java.util.ArrayList;

/**
 * This class provides a login screen for the application.
 * @author Ralph Julsaint
 */

public class ApplicationFrame extends JFrame {
	int customerIndex = 0;
	//menu components
	
	//login components
	private JButton loginSubmitButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton registrationButton;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private DatabaseManager databaseManager;
    private Connection connection;
    private final String url = "jdbc:derby:inventory_management";
    private PreparedStatement authenticateLogin;
    private ResultSet resultSet; 
    private JPanel loginPanel;
    private JPanel loginComponentsPanel;
    private JPanel orderFulfillmentPanel;
    private JTextArea orderFulfillmentTextArea;
    //customer components
    private JLabel customerNoLabel;
    private JTextField customerNoTextField;
    private JLabel customerNameLabel;
    private JTextField customerNameTextField;
    private JLabel customerAddressLabel;
    private JTextField customerAddressTextField;
    private JLabel customerCityLabel;
    private JTextField customerCityTextField;
    private JLabel customerStateLabel;
    private JTextField customerStateTextField;
    private JLabel customerZipLabel;
    private JTextField customerZipTextField;
    private JLabel customerEmailLabel;
    private JTextField customerEmailTextField;
    private JLabel customerPhoneLabel;
    private JTextField customerPhoneTextField;
    private JPanel customerPanel;
    private JPanel navigatePanel;
    private JPanel customerScrollPanel;
    private JButton customerScrollPrevButton;
    private JButton customerScrollNextButton;
    private JTextField customerIndexTextField;
    private JTextField customerMaxTextField;
    private JLabel customerOfLabel;
    //search components
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    private JPanel searchComponentsPanel;
    private JPanel itemAdjustmentPanel;
    private JLabel itemNoLabel;
    private JTextField itemNoTextField;
    private JLabel itemNameLabel;
    private JTextField itemNameTextField;
    private JLabel itemPriceLabel;
    private JTextField itemPriceTextField;
    private JLabel itemCustomerLabel;
    private JTextField itemCustomerTextField;
    private JPanel itemButtonPanel;
    private JButton newItemButton;
    private JButton submitItemButton;
    //inventory activity components
    private JPanel inventoryActivityPanel;
    private JPanel inventoryActivityTablePanel;
    private JTextArea inventoryActivityArea;
    private JButton resetInventoryActivityButton;
    private JButton printInventoryActivityButton;
    private JPanel inventoryActivityButtonPanel;
    //inventory detail component
    private JPanel inventoryDetailPanel;
    private JPanel inventoryDetailTablePanel;
    private JTable inventoryTable;
    private JLabel inventoryDetailLabel;
    private JPanel inventoryDetailButtonPanel;
    private JLabel inventoryDetailSearchLabel;
    private JTextField searchInventoryTextField;
    private JButton goInventorySearchButton;
    private JButton printInventoryDetailButton;
    //invoice and billing components
    private JPanel invoiceAndBillingPanel;
    private JPanel invoiceAndBillingTablePanel;
    private JPanel invoiceAndBillingButtonPanel;
    private JTable invoiceTable;
    private JButton addInvoiceButton;
    private JButton printInvoiceButton;

    /**
     * This class provides the main application window a JFrame object called ApplicationFrame
     * Ralph Julsaint
     */
    public ApplicationFrame() throws SQLException, IOException{
        super("Inventory Manager");
        setLayout(new GridLayout(2, 3));
        menuBarComponent();
        loginComponent();
        customerComponent();
        searchComponent();
        inventoryActivityComponent();
        inventoryDetailComponent();
        invoiceAndBillingComponent();
    }
    
    /**
     * This method provides a menu bar component to the applicatoin
     */
    private void menuBarComponent(){
		
	  Menu[] menus = {new Menu("File"), new Menu("Edit"), new Menu("Insert"), new Menu("Format"), new Menu("Tools"), new Menu("Table"), new Menu("Window"), new Menu("Help")};
	  MenuBar menuBar = new MenuBar();
      for(Menu m : menus)
        menuBar.add(m);
	  
	  setMenuBar(menuBar);
	}

    /**
     * This method contains the login GUI components for this JFrame window object.
     */                       
    private void loginComponent() {
		Font f = new Font("Dialog", Font.PLAIN, 10);
        usernameLabel = new JLabel("username: ");
        usernameLabel.setFont(f);
        passwordLabel = new JLabel("password: ");
        passwordLabel.setFont(f);
        usernameTextField = new JTextField(8);
        passwordTextField = new JTextField(8);
        loginSubmitButton = new JButton("Login");
        loginSubmitButton.setFont(f);
        registrationButton = new JButton("New User");
        registrationButton.setFont(f);
        loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loginComponentsPanel = new JPanel();
        loginComponentsPanel.setLayout(new BoxLayout(loginComponentsPanel, BoxLayout.X_AXIS));
        orderFulfillmentPanel = new JPanel();
        orderFulfillmentTextArea = new JTextArea(12, 35);
        orderFulfillmentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderFulfillmentTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Order Fulfillment"));
        orderFulfillmentPanel.add(scrollPane);
        
        class OrderFulfillment{
		  private int orderNo;
		  private Date orderDate;
		  private int customerNo;
		  
		  OrderFulfillment(int o_n, Date o_d, int c_n){
			orderNo = o_n;
			orderDate = o_d;
			customerNo = c_n;  
		  }	
		  
		  public int getOrderNo(){
			return orderNo;  
		  }
		  
		  public Date getOrderDate(){
			return orderDate;  
		  }
		  
		  public int getCustomerNo(){
			return customerNo;  
		  }
		  
		  @Override
		  public String toString(){
			return String.format("Date: %s%nCustomer#: %d%nOrder#: %d%n%n", getOrderDate().toString(), getCustomerNo(), getOrderNo());  
		  }
		}
		
		List<OrderFulfillment> list = new ArrayList<>();
        
        String orderFulFillmentTxt = "";
        try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
		  ResultSet rs = stmt.executeQuery("SELECT * FROM Order_Table");
		  
		  while (rs.next()){list.add(new OrderFulfillment(
			rs.getInt(1),
			rs.getDate(2),
			rs.getInt(3)
		  ));}
		}
		catch(SQLException e){
		  System.err.println(e);	
		}
		
		for (OrderFulfillment of : list)
		  orderFulFillmentTxt += of;
		  
		orderFulfillmentTextArea.setText(orderFulFillmentTxt);
        
        loginSubmitButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  loginSubmitButtonActionPerformed(evt);	
		    }  
		  }
        );
        
        registrationButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
			  registrationButtonActionPerformed(event);	
		    }  
		  }
        );
        
        loginComponentsPanel.add(usernameLabel);
        loginComponentsPanel.add(Box.createHorizontalStrut(2));
        loginComponentsPanel.add(usernameTextField);
        loginComponentsPanel.add(Box.createHorizontalStrut(2));
        loginComponentsPanel.add(passwordLabel);
        loginComponentsPanel.add(Box.createHorizontalStrut(2));
        loginComponentsPanel.add(passwordTextField);
        loginComponentsPanel.add(Box.createHorizontalStrut(3));
        loginComponentsPanel.add(registrationButton);
        loginComponentsPanel.add(Box.createHorizontalStrut(3));
        loginComponentsPanel.add(loginSubmitButton);
        loginPanel.add(loginComponentsPanel);
        loginPanel.add(orderFulfillmentPanel);
        add(loginPanel);
        
    }   
     
    /**
     * This method contains the customer GUI components for this application.
     */
    private void customerComponent() throws SQLException{
        customerNoLabel = new JLabel("Customer #:");
        customerNoTextField = new JTextField(12);
        customerNoTextField.setEditable(false);
        customerNameLabel = new JLabel("Customer Name:");
        customerNameTextField = new JTextField(12);
        customerNameTextField.setEditable(false);
        customerAddressLabel = new JLabel("Customer Address:");
        customerAddressTextField = new JTextField(12);
        customerAddressTextField.setEditable(false);
        customerCityLabel = new JLabel("Customer City:");
        customerCityTextField = new JTextField(12);
        customerCityTextField.setEditable(false);
        customerStateLabel = new JLabel("Customer State:");
        customerStateTextField = new JTextField(12);
        customerStateTextField.setEditable(false);
        customerZipLabel = new JLabel("Customer Zip:");
        customerZipTextField = new JTextField(12);
        customerZipTextField.setEditable(false);
        customerEmailLabel = new JLabel("Customer Email:");
        customerEmailTextField = new JTextField(12);
        customerEmailTextField.setEditable(false);
        customerPhoneLabel = new JLabel("Customer Phone:");
        customerPhoneTextField = new JTextField(12);
        customerPhoneTextField.setEditable(false);
        customerScrollPrevButton = new JButton("prev");
        customerScrollNextButton = new JButton("next");
        customerIndexTextField = new JTextField(2);
        customerMaxTextField = new JTextField(2);
        customerOfLabel = new JLabel("of");
        customerPanel = new JPanel();
        navigatePanel = new JPanel(new GridLayout(8, 2, 4, 4));
        customerScrollPanel = new JPanel();
        
        customerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        customerScrollPanel.setLayout(new BoxLayout(customerScrollPanel, BoxLayout.X_AXIS));
        
        
        
        navigatePanel.add(customerNoLabel);
        navigatePanel.add(customerNoTextField);
        navigatePanel.add(customerNameLabel);
        navigatePanel.add(customerNameTextField);
        navigatePanel.add(customerAddressLabel);
        navigatePanel.add(customerAddressTextField);
        navigatePanel.add(customerCityLabel);
        navigatePanel.add(customerCityTextField);
        navigatePanel.add(customerStateLabel);
        navigatePanel.add(customerStateTextField);
        navigatePanel.add(customerZipLabel);
        navigatePanel.add(customerZipTextField);
        navigatePanel.add(customerEmailLabel);
        navigatePanel.add(customerEmailTextField);
        navigatePanel.add(customerPhoneLabel);
        navigatePanel.add(customerPhoneTextField);
        
        int customerNo = 0;
        String customerName = null;
        String customerAddress = null;
        String customerCity = null;
        String customerState = null;
        int customerZip = 0;
        String customerEmail = null;
        int customerPhone = 0;
        
        List<Customer> list = new ArrayList<>();
        
        //a try with resources statement that add a new Customer object from the database to output data to the customer form
        try(Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
	      ResultSet resultSet = stmt.executeQuery("SELECT * FROM Supplier");
	      while(resultSet.next()){list.add(new Customer(
		    customerNo = resultSet.getInt(1),
			customerName = resultSet.getString(2),
			customerAddress = resultSet.getString(3),
			customerCity = resultSet.getString(4),
			customerState = resultSet.getString(5),
			customerZip = resultSet.getInt(6),
			customerEmail = resultSet.getString(7),
			customerPhone = resultSet.getInt(8) 
		  ));}
		}
		
		customerMaxTextField.setHorizontalAlignment(SwingConstants.CENTER);
		customerMaxTextField.setText(""+list.size());
		customerMaxTextField.setEditable(false);
        customerIndexTextField.setHorizontalAlignment(SwingConstants.CENTER);
        customerIndexTextField.setText(""+ (customerIndex + 1));
        customerIndexTextField.setEditable(false);
		
		String custNo = ""+list.get(customerIndex).getCustomerNo();
		String custPhone = ""+list.get(customerIndex).getCustomerPhone();
		String custZip = ""+list.get(customerIndex).getCustomerZip();
        customerNoTextField.setText(custNo);
        customerNameTextField.setText(list.get(customerIndex).getCustomerName());
        customerAddressTextField.setText(list.get(customerIndex).getCustomerAddress());
        customerCityTextField.setText(list.get(customerIndex).getCustomerCity());
        customerStateTextField.setText(list.get(customerIndex).getCustomerState());
        customerZipTextField.setText(custZip);
        customerEmailTextField.setText(list.get(customerIndex).getCustomerEmail());
        customerPhoneTextField.setText(custPhone);
        
        customerScrollPanel.add(customerScrollPrevButton);
        customerScrollPanel.add(Box.createHorizontalStrut(10));
        customerScrollPanel.add(customerIndexTextField);
        customerScrollPanel.add(Box.createHorizontalStrut(10));
        customerScrollPanel.add(customerOfLabel);
        customerScrollPanel.add(Box.createHorizontalStrut(10));
        customerScrollPanel.add(customerMaxTextField);
        customerScrollPanel.add(Box.createHorizontalStrut(10));
        customerScrollPanel.add(customerScrollNextButton);
        
        customerPanel.add(navigatePanel);
        customerPanel.add(customerScrollPanel);
       
        add(customerPanel);
        
        
        /**
         * This anonymous inner class provides an event handler to scroll "up" the index of customers
         */
        customerScrollNextButton.addActionListener(
          new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent evt){
			  customerIndex++;
			  if (customerIndex == list.size())
			    customerIndex = 0;
			  
			  customerIndexTextField.setText(""+ (customerIndex + 1)); 
			  String custNo = ""+list.get(customerIndex).getCustomerNo();
		      String custPhone = ""+list.get(customerIndex).getCustomerPhone();
		      String custZip = ""+list.get(customerIndex).getCustomerZip();
              customerNoTextField.setText(custNo);
              customerNameTextField.setText(list.get(customerIndex).getCustomerName());
              customerAddressTextField.setText(list.get(customerIndex).getCustomerAddress());
              customerCityTextField.setText(list.get(customerIndex).getCustomerCity());
              customerStateTextField.setText(list.get(customerIndex).getCustomerState());
              customerZipTextField.setText(custZip);
              customerEmailTextField.setText(list.get(customerIndex).getCustomerEmail());
              customerPhoneTextField.setText(custPhone); 	
		    }
		  }
        );
        
        
        /**
         * This anonymous inner class provides an event handler to scroll "down" the list of customers.
         */
        customerScrollPrevButton.addActionListener(
          new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
			  customerIndex--;
			  if (customerIndex < 0)
			    customerIndex = list.size() - 1;
			  
			  customerIndexTextField.setText(""+ (customerIndex + 1)); 
			  String custNo = ""+list.get(customerIndex).getCustomerNo();
		      String custPhone = ""+list.get(customerIndex).getCustomerPhone();
		      String custZip = ""+list.get(customerIndex).getCustomerZip();
              customerNoTextField.setText(custNo);
              customerNameTextField.setText(list.get(customerIndex).getCustomerName());
              customerAddressTextField.setText(list.get(customerIndex).getCustomerAddress());
              customerCityTextField.setText(list.get(customerIndex).getCustomerCity());
              customerStateTextField.setText(list.get(customerIndex).getCustomerState());
              customerZipTextField.setText(custZip);
              customerEmailTextField.setText(list.get(customerIndex).getCustomerEmail());
              customerPhoneTextField.setText(custPhone);	
		    }  
		  }
        );
        	
    }
    
    
    /**
     * This method add search functionality to the application.
     */
    private void searchComponent(){
		Font f = new Font("Dialog", Font.PLAIN, 10);
		searchLabel = new JLabel("search:");
		searchLabel.setFont(f);
		searchField = new JTextField(15);
		searchField.setFont(f);
		searchButton= new JButton("go");
		searchButton.setFont(f);
		newItemButton = new JButton("New");
		submitItemButton = new JButton("Submit");
		searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
		searchComponentsPanel = new JPanel();
		itemButtonPanel = new JPanel();
		itemNoLabel = new JLabel("Item #:");
		itemNoTextField = new JTextField(12);
		itemNameLabel = new JLabel("Name:");
		itemNameTextField = new JTextField(12);
		itemPriceLabel = new JLabel("Price:");
		itemPriceTextField = new JTextField(12);
		itemCustomerLabel = new JLabel("Customer:");
		itemCustomerTextField = new JTextField(12);
		itemButtonPanel.setLayout(new BoxLayout(itemButtonPanel, BoxLayout.X_AXIS));
		itemAdjustmentPanel = new JPanel(new GridLayout(4, 2, 4, 4));
		itemAdjustmentPanel.setBorder(BorderFactory.createTitledBorder("Adjustments"));
		itemAdjustmentPanel.add(itemNoLabel);
		itemAdjustmentPanel.add(itemNoTextField);
		itemAdjustmentPanel.add(itemNameLabel);
		itemAdjustmentPanel.add(itemNameTextField);
		itemAdjustmentPanel.add(itemPriceLabel);
		itemAdjustmentPanel.add(itemPriceTextField);
		itemAdjustmentPanel.add(itemCustomerLabel);
		itemAdjustmentPanel.add(itemCustomerTextField);
		itemButtonPanel.add(newItemButton);
		itemButtonPanel.add(Box.createHorizontalStrut(4));
		itemButtonPanel.add(submitItemButton);
		searchComponentsPanel.setLayout(new BoxLayout(searchComponentsPanel, BoxLayout.X_AXIS));
		searchComponentsPanel.add(searchLabel);
		searchComponentsPanel.add(Box.createHorizontalStrut(2));
		searchComponentsPanel.add(searchField);
		searchComponentsPanel.add(Box.createHorizontalStrut(2));
		searchComponentsPanel.add(searchButton);
		searchPanel.add(searchComponentsPanel);
		searchPanel.add(itemAdjustmentPanel);
		searchPanel.add(itemButtonPanel);
		add(searchPanel);
	}
	
	/**
	 * This method provides an inventory activity table that provides a list of recent inventory activity
	 */
	private void inventoryActivityComponent(){
	  File file = new File("inventory_activity.txt");
      String s = "";
      String[] columnNames = {"Date", "Activity"};
	  LocalDate date = LocalDate.of(2020, 03, 20);
	  DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
	  String d = formatter.format(date);
      Object[][] tableData = new Object[10][10];
      int row = 0;
      int column = 1;
      try(BufferedReader in = new BufferedReader(new FileReader(file))){
        for(;(s = in.readLine()) != null; row++){
	      tableData[row][0] = d;
	      tableData[row][column] = s;
	    }
      }
      catch(IOException e){
		System.err.println(e);  
	  }
	  
      JTable inventoryActivityTable = new JTable(tableData, columnNames);
	  inventoryActivityPanel = new JPanel();
	  inventoryActivityPanel.setLayout(new BoxLayout(inventoryActivityPanel, BoxLayout.Y_AXIS));
      inventoryActivityTablePanel = new JPanel();
      inventoryActivityButtonPanel = new JPanel();
      inventoryActivityButtonPanel.setLayout(new BoxLayout(inventoryActivityButtonPanel, BoxLayout.X_AXIS));
      inventoryActivityButtonPanel.add(new JButton("clear"));
      inventoryActivityButtonPanel.add(Box.createHorizontalStrut(5));
      inventoryActivityButtonPanel.add(new JButton("print"));
      inventoryActivityTablePanel.setLayout(new BorderLayout());
      JScrollPane scrollPane = new JScrollPane(inventoryActivityTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setBorder(BorderFactory.createTitledBorder("Inventory Activity"));
      inventoryActivityTablePanel.add(scrollPane, BorderLayout.CENTER);
      inventoryActivityPanel.add(inventoryActivityTablePanel);
      inventoryActivityPanel.add(inventoryActivityButtonPanel);
      add(inventoryActivityPanel);
	}
	
	/**
	 * This method provides information on items and current inventory status.
	 */
	private void inventoryDetailComponent() throws SQLException{
      TableModel tableModel = null;
      inventoryDetailButtonPanel = new JPanel();
      inventoryDetailButtonPanel.setLayout(new BoxLayout(inventoryDetailButtonPanel, BoxLayout.X_AXIS));
      inventoryDetailSearchLabel = new JLabel("search:");
      searchInventoryTextField = new JTextField(10);
      goInventorySearchButton = new JButton("go");
      printInventoryDetailButton = new JButton("print");
	  inventoryDetailPanel = new JPanel();
	  inventoryDetailPanel.setLayout(new BoxLayout(inventoryDetailPanel, BoxLayout.Y_AXIS));
	  inventoryDetailTablePanel = new JPanel();
	  inventoryDetailTablePanel.setLayout(new BorderLayout());
	  tableModel = new ResultSetTableModel(url, "SELECT * FROM Item");
	  inventoryTable = new JTable(tableModel);
	  JScrollPane scrollPane = new JScrollPane(inventoryTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	  scrollPane.setBorder(BorderFactory.createTitledBorder("Inventory Details"));
	  inventoryDetailTablePanel.add(scrollPane);
	  inventoryDetailButtonPanel.add(inventoryDetailSearchLabel);
	  inventoryDetailButtonPanel.add(searchInventoryTextField);
	  inventoryDetailButtonPanel.add(goInventorySearchButton);
	  inventoryDetailButtonPanel.add(printInventoryDetailButton);
	  inventoryDetailPanel.add(inventoryDetailTablePanel);
	  inventoryDetailPanel.add(inventoryDetailButtonPanel);
	  add(inventoryDetailPanel); 
	}
	
	private void invoiceAndBillingComponent() throws SQLException{
	  TableModel tableModel = null;
	  tableModel = new ResultSetTableModel(url, "SELECT * FROM Invoice");
	  invoiceTable = new JTable(tableModel);
	  JScrollPane scrollPane = new JScrollPane(invoiceTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	  scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice & Billing"));
	  invoiceAndBillingPanel = new JPanel();
	  invoiceAndBillingPanel.setLayout(new BoxLayout(invoiceAndBillingPanel, BoxLayout.Y_AXIS));
      invoiceAndBillingButtonPanel = new JPanel();
      invoiceAndBillingButtonPanel.setLayout(new BoxLayout(invoiceAndBillingButtonPanel, BoxLayout.X_AXIS));
      addInvoiceButton = new JButton("add");
      printInvoiceButton = new JButton("print");
      invoiceAndBillingButtonPanel.add(addInvoiceButton);
      invoiceAndBillingButtonPanel.add(Box.createHorizontalStrut(5));
      invoiceAndBillingButtonPanel.add(printInvoiceButton);
      invoiceAndBillingTablePanel = new JPanel();
      invoiceAndBillingTablePanel.setLayout(new BorderLayout());
      invoiceAndBillingTablePanel.add(scrollPane, BorderLayout.CENTER);
      invoiceAndBillingPanel.add(invoiceAndBillingTablePanel);
      invoiceAndBillingPanel.add(invoiceAndBillingButtonPanel);
      add(invoiceAndBillingPanel);	
	}
              
    private void loginSubmitButtonActionPerformed(ActionEvent evt){
	  try{
	    connection = DriverManager.getConnection(url);
	    String inputUserName = usernameTextField.getText();
	    String inputUserPassword = passwordTextField.getText();
	    authenticateLogin = connection.prepareStatement(
	    "SELECT * FROM User WHERE userName=? AND userPassword=?");
	    authenticateLogin.setString(1, inputUserName);
	    authenticateLogin.setString(2, inputUserPassword);
	    
	    resultSet = authenticateLogin.executeQuery();
	    
	    if (resultSet.next()){
		  JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.PLAIN_MESSAGE);
		  this.dispose();	
		  /*
		  AppFrame appFrame = new AppFrame();
		  appFrame.setVisible(true);
		  appFrame.setResizable(false);
		  appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  */
		}
		else
		  JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Invalid", JOptionPane.PLAIN_MESSAGE);
	      usernameTextField.setText("");
	      passwordTextField.setText("");
	  }
	  catch (SQLException sqlException){
		sqlException.printStackTrace();  
	  }
	  finally {
		try {
		  resultSet.close();	
	    }  
	    catch (SQLException sqlException){
		  sqlException.printStackTrace();	
	    }
	  }
	        
    }
    
    private void registrationButtonActionPerformed(ActionEvent event){
	  	AddUserFrame addUserFrame = new AddUserFrame();
	  	addUserFrame.setLocationRelativeTo(null);
	  	addUserFrame.setSize(400, 250);
	  	addUserFrame.setVisible(true);
	  	addUserFrame.setResizable(false);
	  	addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }    
    
    private void addCustomerButtonActionPerformed(ActionEvent event){
	    AddCustomerFrame addCustomerFrame = new AddCustomerFrame();
	    addCustomerFrame.setLocationRelativeTo(null);
	    addCustomerFrame.setSize(300, 500);
	    addCustomerFrame.setVisible(true);
        addCustomerFrame.setResizable(false);
        addCustomerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}
              
}

