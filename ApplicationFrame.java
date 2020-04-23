package com.ralph.inventmanagementsys;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Font;
import java.util.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


/**
 * This class creates the main application window.
 * @author Ralph Julsaint
 */
public class ApplicationFrame extends JFrame{
    private final ApplicationContext context = new AnnotationConfigApplicationContext(InventoryConfiguration.class);
    int customerIndex = 0;
    private final String url = "jdbc:derby://localhost:1527/inventory_management";
    //menu components
	
    //login components
    private JButton loginSubmitButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton registrationButton;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
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
    private JPanel searchFieldPanel;
    private JPanel itemAdjustmentPanel;
    private JLabel itemNoLabel;
    private JTextField itemNoTextField;
    private JLabel itemNameLabel;
    private JTextField itemNameTextField;
    private JLabel itemPriceLabel;
    private JTextField itemPriceTextField;
    private JLabel itemQtyLabel;
    private JTextField itemQtyTextField;
    private JLabel itemExpDateLabel;
    private JTextField itemExpDateTextField;
    private JLabel itemCustomerLabel;
    private JPanel itemButtonPanel;
    private JButton newItemButton;
    private JButton updateItemButton;
    private JComboBox<Customer> customerList;
    private DefaultComboBoxModel<Customer> customerComboBoxModel;
    //inventory activity components
    private JPanel inventoryActivityPanel;
    private JPanel inventoryActivityTablePanel;
    static JTable inventoryActivityTable;
    static DefaultTableModel inventoryActivityTableModel;
    private JButton inventoryActivityAddButton;
    private JButton inventoryActivityClearButton;
    private JPanel inventoryActivityButtonPanel;
    //inventory detail component
    private JPanel inventoryDetailPanel;
    private JPanel inventoryDetailTablePanel;
    private AbstractTableModel inventoryDetailTableModel;
    static JTable inventoryDetailTable;
    private JPanel inventoryDetailButtonPanel;
    private JLabel inventoryDetailSearchLabel;
    private JTextField searchInventoryTextField;
    private JButton goInventorySearchButton;
    private JButton printInventoryDetailButton;
    //invoice and billing components
    private JPanel invoiceAndBillingPanel;
    private JPanel invoiceAndBillingTablePanel;
    private JPanel invoiceAndBillingButtonPanel;
    private TableModel invoiceAndBillingTableModel;
    static JTable invoiceTable;
    private JButton addInvoiceButton;
    private JButton clearInvoiceButton;

    /**
     * This class provides the main application window a JFrame object called ApplicationFrame
     * Ralph Julsaint
     * @throws java.sql.SQLException
     * @throws java.io.IOException
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
     * This method provides a menu bar component to the application
     */
    private void menuBarComponent(){
        Menu[] menus = {new Menu("File"), new Menu("Edit"), new Menu("Insert"), new Menu("Format"), new Menu("Tools"), new Menu("Table"),       new Menu("Window"), new Menu("Help")};
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
        passwordTextField = new JPasswordField(8);
        loginSubmitButton = new JButton("login");
        loginSubmitButton.setFont(f);
        registrationButton = new JButton("new user");
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
            private final int orderNo;
            private final Date orderDate;
            private final int customerNo;
		  
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
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        PreparedStatement stmt = obj.createStatement("SELECT * FROM Order_Table");
        try{
          ResultSet rs = stmt.executeQuery();
		  
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
        
        loginSubmitButton.addActionListener((ActionEvent evt) -> {
            loginSubmitButtonActionPerformed(evt);
        });
        
        registrationButton.addActionListener((ActionEvent event) -> {
            registrationButtonActionPerformed(event);
        });
        
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

        List<Customer> list = new ArrayList<>();
        Customer customerObj;
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        PreparedStatement stmt = obj.createStatement("SELECT * FROM Customer");
        //a try with resources statement that add a new Customer object from the database to output data to the customer form
        try{
            ResultSet rs = stmt.executeQuery();
	    while(rs.next()){
                customerObj = context.getBean(Customer.class);
                customerObj.setCustomerno(rs.getInt("CUSTOMERNO"));
                customerObj.setCustomername(rs.getString("CUSTOMERNAME"));
                customerObj.setCustomeraddress(rs.getString("CUSTOMERADDRESS"));
                customerObj.setCustomercity(rs.getString("CUSTOMERCITY"));
                customerObj.setCustomerstate(rs.getString("CUSTOMERSTATE"));
                customerObj.setCustomerzip(rs.getString("CUSTOMERZIP"));
                customerObj.setCustomeremail(rs.getString("CUSTOMEREMAIL"));
                customerObj.setCustomerphone(rs.getString("CUSTOMERPHONE"));
                list.add(new Customer(customerObj.getCustomerno(), customerObj.getCustomername(), customerObj.getCustomeraddress(),
                customerObj.getCustomercity(), customerObj.getCustomerstate(), customerObj.getCustomerzip(),
                customerObj.getCustomeremail(), customerObj.getCustomerphone()));
	    }
        }
        catch(SQLException e){
            System.err.println(e);
        }
        customerMaxTextField.setHorizontalAlignment(SwingConstants.CENTER);
        customerMaxTextField.setText(""+list.size());
        customerMaxTextField.setEditable(false);
        customerIndexTextField.setHorizontalAlignment(SwingConstants.CENTER);
        customerIndexTextField.setText(""+ (customerIndex + 1));
        customerIndexTextField.setEditable(false);
        String custNo = ""+list.get(customerIndex).getCustomerno();
        String custPhone = ""+list.get(customerIndex).getCustomerphone();
        String custZip = ""+list.get(customerIndex).getCustomerzip();
        customerNoTextField.setText(custNo);
        customerNameTextField.setText(list.get(customerIndex).getCustomername());
        customerAddressTextField.setText(list.get(customerIndex).getCustomeraddress());
        customerCityTextField.setText(list.get(customerIndex).getCustomercity());
        customerStateTextField.setText(list.get(customerIndex).getCustomerstate());
        customerZipTextField.setText(custZip);
        customerEmailTextField.setText(list.get(customerIndex).getCustomeremail());
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
        customerScrollNextButton.addActionListener((ActionEvent evt) -> {
            customerIndex++;
            if (customerIndex == list.size())
                customerIndex = 0;
            customerIndexTextField.setText(""+ (customerIndex + 1));
            String custNo1 = ""+list.get(customerIndex).getCustomerno();
            String custPhone1 = ""+list.get(customerIndex).getCustomerphone();
            String custZip1 = ""+list.get(customerIndex).getCustomerzip();
            customerNoTextField.setText(custNo1);
            customerNameTextField.setText(list.get(customerIndex).getCustomername());
            customerAddressTextField.setText(list.get(customerIndex).getCustomeraddress());
            customerCityTextField.setText(list.get(customerIndex).getCustomercity());
            customerStateTextField.setText(list.get(customerIndex).getCustomerstate());
            customerZipTextField.setText(custZip1);
            customerEmailTextField.setText(list.get(customerIndex).getCustomeremail());
            customerPhoneTextField.setText(custPhone1);
        });
    
        /**
         * This anonymous inner class provides an event handler to scroll "down" the list of customers.
         */
        customerScrollPrevButton.addActionListener((ActionEvent evt) -> {
            customerIndex--;
            if (customerIndex < 0)
                customerIndex = list.size() - 1;
            customerIndexTextField.setText(""+ (customerIndex + 1));
            String custNo1 = ""+list.get(customerIndex).getCustomerno();
            String custPhone1 = ""+list.get(customerIndex).getCustomerphone();
            String custZip1 = ""+list.get(customerIndex).getCustomerzip();
            customerNoTextField.setText(custNo1);
            customerNameTextField.setText(list.get(customerIndex).getCustomername());
            customerAddressTextField.setText(list.get(customerIndex).getCustomeraddress());
            customerCityTextField.setText(list.get(customerIndex).getCustomercity());
            customerStateTextField.setText(list.get(customerIndex).getCustomerstate());
            customerZipTextField.setText(custZip1);
            customerEmailTextField.setText(list.get(customerIndex).getCustomeremail());
            customerPhoneTextField.setText(custPhone1);
        });
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
        newItemButton = new JButton("new");
        updateItemButton = new JButton("update");
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchComponentsPanel = new JPanel();
        searchComponentsPanel.setLayout(new BoxLayout(searchComponentsPanel, BoxLayout.Y_AXIS));
        searchFieldPanel = new JPanel();
        itemButtonPanel = new JPanel();
        itemNoLabel = new JLabel("Item #:");
        itemNoTextField = new JTextField(12);
        itemNoTextField.setEditable(false);
        itemNameLabel = new JLabel("Name:");
        itemNameTextField = new JTextField(12);
        itemNameTextField.setEditable(false);
        itemPriceLabel = new JLabel("Price:");
        itemPriceTextField = new JTextField(12);
        itemQtyLabel = new JLabel("QTY:");
        itemQtyTextField = new JTextField(12);
        itemExpDateLabel = new JLabel("Expiration:");
        itemExpDateTextField = new JTextField("mm/dd/yyyy", 12);
        itemCustomerLabel = new JLabel("Customer:");
        itemButtonPanel.setLayout(new BoxLayout(itemButtonPanel, BoxLayout.X_AXIS));
        itemAdjustmentPanel = new JPanel(new GridLayout(6, 2, 4, 4));
        itemAdjustmentPanel.setBorder(BorderFactory.createTitledBorder("Adjustments"));
        itemAdjustmentPanel.add(itemNoLabel);
        itemAdjustmentPanel.add(itemNoTextField);
        itemAdjustmentPanel.add(itemNameLabel);
        itemAdjustmentPanel.add(itemNameTextField);
        itemAdjustmentPanel.add(itemPriceLabel);
        itemAdjustmentPanel.add(itemPriceTextField);
        itemAdjustmentPanel.add(itemQtyLabel);
        itemAdjustmentPanel.add(itemQtyTextField);
        itemAdjustmentPanel.add(itemExpDateLabel);
        itemAdjustmentPanel.add(itemExpDateTextField);
        itemAdjustmentPanel.add(itemCustomerLabel);
        itemAdjustmentPanel.add(getCustomerList());
        itemButtonPanel.add(newItemButton);
        itemButtonPanel.add(Box.createHorizontalStrut(4));
        itemButtonPanel.add(updateItemButton);
        searchFieldPanel.setLayout(new BoxLayout(searchFieldPanel, BoxLayout.X_AXIS));
        searchFieldPanel.add(searchLabel);
        searchFieldPanel.add(Box.createHorizontalStrut(2));
        searchFieldPanel.add(searchField);
        searchFieldPanel.add(searchButton);
        searchComponentsPanel.add(searchFieldPanel);
        searchComponentsPanel.add(itemAdjustmentPanel);
        searchComponentsPanel.add(itemButtonPanel);
        searchPanel.add(searchComponentsPanel);
        add(searchPanel);
		
        searchField.addKeyListener(
            new KeyAdapter(){
	        @Override
	        public void keyPressed(KeyEvent evt){
	            if (evt.getKeyCode() == KeyEvent.VK_ENTER){
	                String itemEntered = searchField.getText();
	                if(!itemEntered.equals("")){
                            DatabaseManager obj = context.getBean(DatabaseManager.class);
                            String query = "SELECT itemNo, itemName, itemPrice, itemQOH, itemExpDate, customerNo FROM Item WHERE itemName=?";
                            PreparedStatement stmt = obj.createStatement(query);
	                    try{
                                stmt.setString(1, itemEntered);
	                        ResultSet rs = stmt.executeQuery();
                                Item itemObj;
		                if (rs.next()){
                                    itemObj = context.getBean(Item.class);
		                    itemObj.setItemno(rs.getInt("ITEMNO"));
		                    itemObj.setItemname(rs.getString("ITEMNAME"));
		                    itemObj.setItemprice(rs.getBigDecimal("ITEMPRICE"));
		                    itemObj.setItemqoh(rs.getInt("ITEMQOH"));
		                    itemObj.setItemexpdate(rs.getDate("ITEMEXPDATE"));
                                    
                                    itemNoTextField.setText(""+itemObj.getItemno());
		                    itemNameTextField.setText(itemObj.getItemname());
		                    itemPriceTextField.setText(""+itemObj.getItemprice());
		                    itemQtyTextField.setText(""+itemObj.getItemqoh());
                                    if (itemObj.getItemexpdate() == null){
                                        itemExpDateTextField.setText("");
                                    } else {
		                        LocalDate d = LocalDate.parse(itemObj.getItemexpdate().toString());
		                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		                        String dateAsString = d.format(formatter);
		                        itemExpDateTextField.setText(dateAsString);
                                    }
		                } else {
		                    searchField.setText("");
		                    itemNoTextField.setText("");
		                    itemNameTextField.setText("");
		                    itemPriceTextField.setText("");
                                    itemQtyTextField.setText("");
		                    itemExpDateTextField.setText("mm/dd/yyyy");
		                    JOptionPane.showMessageDialog(null, "No record exists for item entered");	  
		                } 
		                    searchField.setText("");
		            }
		            catch(SQLException e){
		                System.err.println(e);  
		            }	
                        } else {
	                    JOptionPane.showMessageDialog(null, "You entered an invalid item");	
	                }
	            }	
	        }  
	    }
        );
		
        searchButton.addActionListener((ActionEvent evt) -> {
            String itemEntered = searchField.getText();
            if (!itemEntered.equals("")){
                DatabaseManager obj = context.getBean(DatabaseManager.class);
                String query = "SELECT itemNo, itemName, itemPrice, itemQOH, itemExpDate, customerNo FROM Item WHERE itemName=?";
                PreparedStatement stmt = obj.createStatement(query);
                try{
                    stmt.setString(1, itemEntered);
                    ResultSet rs = stmt.executeQuery();
                    Item itemObj;
                    if(rs.next()){
                        itemObj = context.getBean(Item.class);
                        itemObj.setItemno(rs.getInt("ITEMNO"));
                        itemObj.setItemname(rs.getString("ITEMNAME"));
                        itemObj.setItemprice(rs.getBigDecimal("ITEMPRICE"));
                        itemObj.setItemqoh(rs.getInt("ITEMQOH"));
                        itemObj.setItemexpdate(rs.getDate("ITEMEXPDATE"));
                        
                        itemNoTextField.setText(""+itemObj.getItemno());
                        itemNameTextField.setText(itemObj.getItemname());
                        itemPriceTextField.setText(""+itemObj.getItemprice());
                        itemQtyTextField.setText(""+itemObj.getItemqoh());
                        
                        if (itemObj.getItemexpdate() == null){
                            itemExpDateTextField.setText("");
                        } else {
                            LocalDate d = LocalDate.parse(itemObj.getItemexpdate().toString());
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                            String dateAsString = d.format(formatter);
                            itemExpDateTextField.setText(dateAsString);
                        }
                    } else {
                        searchField.setText("");
                        itemNoTextField.setText("");
                        itemNameTextField.setText("");
                        itemPriceTextField.setText("");
                        itemQtyTextField.setText("");
                        itemExpDateTextField.setText("mm/dd/yyyy");
                        JOptionPane.showMessageDialog(null, "No record exists for item entered");
                    }
                    searchField.setText("");
                    
                }
                catch(SQLException e){
                    System.err.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You entered an invalid item");  
            }
        });
	
        newItemButton.addActionListener((ActionEvent evt) -> {
            AddItemFrame frame = new AddItemFrame();
            frame.setLocationRelativeTo(null);
            frame.setSize(370, 250);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
		
        updateItemButton.addActionListener((ActionEvent evt) -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0) {
                int itemNo = Integer.parseInt(itemNoTextField.getText());
                double itemPrice = Double.parseDouble(itemPriceTextField.getText());
                int itemQOH = Integer.parseInt(itemQtyTextField.getText());
                SimpleDateFormat f1 = new SimpleDateFormat("MM/dd/yyyy");
                java.util.Date d;
                long dateAsLong = 0;
                try {
                    d = (java.util.Date) f1.parse(itemExpDateTextField.getText());
                    dateAsLong = d.getTime();
                }catch(ParseException e){
                    System.err.println(e);
                }
                java.sql.Date expDate = new java.sql.Date(dateAsLong);
                int customerNo = getCustomerNoFromName(customerList.getSelectedItem());
                DatabaseManager obj = context.getBean(DatabaseManager.class);
                String updateStatement = "UPDATE Item SET itemPrice =?, itemQOH =?, itemExpDate=?, customerNo =? WHERE itemNo =?";
                PreparedStatement stmt = obj.createStatement(updateStatement);
                try{
                    stmt.setDouble(1, itemPrice);
                    stmt.setInt(2, itemQOH);
                    stmt.setDate(3, expDate);
                    stmt.setInt(4, customerNo);
                    stmt.setInt(5, itemNo);
                    int result = stmt.executeUpdate();
                    itemNoTextField.setText("");
                    itemNameTextField.setText("");
                    itemPriceTextField.setText("");
                    itemQtyTextField.setText("");
                    itemExpDateTextField.setText("");
                    
                    if (result > 0){
                        JOptionPane.showMessageDialog(null, "Record is updated");
                        inventoryDetailTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Item"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Record was not updated");
                    }
                }
                catch(SQLException e){
                    System.err.println(e);
                }
            }
        });
    }
	
    /**
     * This method provides an inventory activity table that provides a list of recent inventory activity
     */
    private void inventoryActivityComponent(){
        Font f = new Font("Dialog", Font.PLAIN, 10);
        try{
            inventoryActivityTable = new JTable(new ResultSetTableModel(url, "SELECT * FROM Activity"));
        }
        catch(SQLException e){
            System.err.println(e);
        }
        inventoryActivityPanel = new JPanel();
        inventoryActivityPanel.setLayout(new BoxLayout(inventoryActivityPanel, BoxLayout.Y_AXIS));
        inventoryActivityTablePanel = new JPanel();
        inventoryActivityButtonPanel = new JPanel();
        inventoryActivityButtonPanel.setLayout(new BoxLayout(inventoryActivityButtonPanel, BoxLayout.X_AXIS));
        inventoryActivityAddButton = new JButton("add");
        inventoryActivityAddButton.setFont(f);
        inventoryActivityClearButton = new JButton("clear");
        inventoryActivityClearButton.setFont(f);
        inventoryActivityButtonPanel.add(inventoryActivityAddButton);
        inventoryActivityButtonPanel.add(Box.createHorizontalStrut(5));
        inventoryActivityButtonPanel.add(inventoryActivityClearButton);
        inventoryActivityTablePanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(inventoryActivityTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inventory Activity"));
        inventoryActivityTablePanel.add(scrollPane, BorderLayout.CENTER);
        inventoryActivityPanel.add(inventoryActivityTablePanel);
        inventoryActivityPanel.add(inventoryActivityButtonPanel);
        add(inventoryActivityPanel);
      
        inventoryActivityAddButton.addActionListener((ActionEvent evt) -> {
            AddActivityFrame frame = new AddActivityFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(300, 150);
        });
      
        inventoryActivityClearButton.addActionListener((ActionEvent evt) -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0) {
                String statement = "DELETE FROM Activity";
                DatabaseManager obj = context.getBean(DatabaseManager.class);
                PreparedStatement stmt = obj.createStatement(statement);
                int result;
                try {
                    result = stmt.executeUpdate();
                    if (result > 0){
                        JOptionPane.showMessageDialog(null, "All records deleted.", "Activity", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No records deleted.", "Activity", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });
    }
	
    /**
     * This method provides information on items and current inventory status.
     */
    private void inventoryDetailComponent() throws SQLException{
        Font f = new Font("Dialog", Font.PLAIN, 10);
        inventoryDetailButtonPanel = new JPanel();
        inventoryDetailButtonPanel.setLayout(new BoxLayout(inventoryDetailButtonPanel, BoxLayout.X_AXIS));
        inventoryDetailSearchLabel = new JLabel("search:");
        inventoryDetailSearchLabel.setFont(f);
        searchInventoryTextField = new JTextField(6);
        searchInventoryTextField.setFont(f);
        goInventorySearchButton = new JButton("go");
        goInventorySearchButton.setFont(f);
        printInventoryDetailButton = new JButton("print");
        printInventoryDetailButton.setFont(f);
        inventoryDetailPanel = new JPanel();
        inventoryDetailPanel.setLayout(new BoxLayout(inventoryDetailPanel, BoxLayout.Y_AXIS)); 
        inventoryDetailTablePanel = new JPanel();
        inventoryDetailTablePanel.setLayout(new BorderLayout());
        inventoryDetailTableModel = new ResultSetTableModel(url, "SELECT * FROM Item");
        inventoryDetailTable = new JTable(inventoryDetailTableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryDetailTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inventory Details"));
        inventoryDetailTablePanel.add(scrollPane);
        inventoryDetailButtonPanel.add(inventoryDetailSearchLabel);
        inventoryDetailButtonPanel.add(searchInventoryTextField);
        inventoryDetailButtonPanel.add(goInventorySearchButton);
        inventoryDetailButtonPanel.add(Box.createHorizontalStrut(5));
        inventoryDetailButtonPanel.add(printInventoryDetailButton);
        inventoryDetailPanel.add(inventoryDetailTablePanel);
        inventoryDetailPanel.add(inventoryDetailButtonPanel);
        add(inventoryDetailPanel); 
	  
        searchInventoryTextField.addKeyListener(
            new KeyAdapter(){
	        @Override
	        public void keyPressed(KeyEvent evt){
	            if (evt.getKeyCode() == KeyEvent.VK_ENTER)  
	                if (!(searchInventoryTextField.getText().equals(""))){	
                            try{
		                String itemName = searchInventoryTextField.getText();
		                final String query = "SELECT * FROM Item Where itemName='"+itemName+"'";
		                inventoryDetailTableModel = new ResultSetTableModel(url, query);
	                        inventoryDetailTable.setModel(inventoryDetailTableModel);
	                        searchInventoryTextField.setText("");
		            }
		            catch(SQLException e){
		                System.err.println(e);  
		            }
                        } else {
                            try{
	                        inventoryDetailTableModel = new ResultSetTableModel(url, "SELECT * FROM Item");
	                        inventoryDetailTable.setModel(inventoryDetailTableModel);
	                    }
	                    catch(SQLException e){
		                System.err.println(e);  
		            }
                        }
	        }	
            }
        );

        printInventoryDetailButton.addActionListener((ActionEvent evt) -> {
            try{
                inventoryDetailTable.print();
            }
            catch(PrinterException e){
                System.err.println(e);
            }
        });
	  
        goInventorySearchButton.addActionListener((ActionEvent evt) -> {
            if (!(searchInventoryTextField.getText().equals(""))){
                try{
                    String itemName = searchInventoryTextField.getText();
                    final String query = "SELECT * FROM Item Where itemName='"+itemName+"'";
                    inventoryDetailTableModel = new ResultSetTableModel(url, query);
                    inventoryDetailTable.setModel(inventoryDetailTableModel);
                    searchInventoryTextField.setText("");
                }
                catch(SQLException e){
                    System.err.println(e);
                }
            } else {
                try{
                    inventoryDetailTableModel = new ResultSetTableModel(url, "SELECT * FROM Item");
                    inventoryDetailTable.setModel(inventoryDetailTableModel);
                }
                catch(SQLException e){
                    System.err.println(e);
                }
            }
        });
    }
	
    private void invoiceAndBillingComponent() throws SQLException{
        Font f = new Font("Dialog", Font.PLAIN, 10);
        invoiceAndBillingTableModel = new ResultSetTableModel(url, "SELECT * FROM Invoice");
        invoiceTable = new JTable(invoiceAndBillingTableModel);
        JScrollPane scrollPane = new JScrollPane(invoiceTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice & Billing"));
        invoiceAndBillingPanel = new JPanel();
        invoiceAndBillingPanel.setLayout(new BoxLayout(invoiceAndBillingPanel, BoxLayout.Y_AXIS));
        invoiceAndBillingButtonPanel = new JPanel();
        invoiceAndBillingButtonPanel.setLayout(new BoxLayout(invoiceAndBillingButtonPanel, BoxLayout.X_AXIS));
        addInvoiceButton = new JButton("add");
        addInvoiceButton.setFont(f);
        clearInvoiceButton = new JButton("clear");
        clearInvoiceButton.setFont(f);
        invoiceAndBillingButtonPanel.add(addInvoiceButton);
        invoiceAndBillingButtonPanel.add(Box.createHorizontalStrut(5));
        invoiceAndBillingButtonPanel.add(clearInvoiceButton);
        invoiceAndBillingTablePanel = new JPanel();
        invoiceAndBillingTablePanel.setLayout(new BorderLayout());
        invoiceAndBillingTablePanel.add(scrollPane, BorderLayout.CENTER);
        invoiceAndBillingPanel.add(invoiceAndBillingTablePanel);
        invoiceAndBillingPanel.add(invoiceAndBillingButtonPanel);
        add(invoiceAndBillingPanel);	
      
        addInvoiceButton.addActionListener((ActionEvent evt) -> {
            AddInvoiceAndBillingFrame frame = new AddInvoiceAndBillingFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setSize(350, 150);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
      
        clearInvoiceButton.addActionListener((ActionEvent evt) -> {
            int option = JOptionPane.showConfirmDialog(null, "Warning: this will delete all invoices", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0){
                DatabaseManager obj = context.getBean(DatabaseManager.class);
                String deleteStatement = "DELETE FROM Invoice";
                PreparedStatement stmt = obj.createStatement(deleteStatement);
                try{
                    int result = stmt.executeUpdate();
                    if (result > 0){
                        JOptionPane.showMessageDialog(null, "All records deleted");
                        invoiceTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Invoice"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No records deleted");
                    }
                }
                catch(SQLException e){
                    System.err.println(e);
                }
            }
        });
    }
              
    private void loginSubmitButtonActionPerformed(ActionEvent evt){
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        PreparedStatement authenticateLogin = obj.createStatement("SELECT * FROM App_User WHERE userName=? AND userPassword=?");
        try{
	    String inputUserName = usernameTextField.getText();
	    char[] inputUserPassword = passwordTextField.getPassword();
	    String userPasswordStr = "";
	    for (char c : inputUserPassword)
	        userPasswordStr += c;

	    
	    authenticateLogin.setString(1, inputUserName);
	    authenticateLogin.setString(2, userPasswordStr);
	    resultSet = authenticateLogin.executeQuery();
	    if (resultSet.next()){
	        JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.PLAIN_MESSAGE);
            //after successful login all GUI data will be made visible here
	    }
	    else
	        JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Invalid", JOptionPane.PLAIN_MESSAGE);

	    usernameTextField.setText("");
	    passwordTextField.setText("");
        }
        catch (SQLException e){
            System.err.println(e);   
        }
        finally {
            try {
	        resultSet.close();	
	    }
	    catch (SQLException e){
	        System.err.println(e);	
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

    private JComboBox<Customer> getCustomerList(){
        customerList = null;
        customerComboBoxModel = new DefaultComboBoxModel<>();
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        String query = "SELECT * FROM Customer";
        PreparedStatement stmt = obj.createStatement(query);
        try{
            ResultSet rs = stmt.executeQuery();  
            while(rs.next()){
                customerComboBoxModel.addElement(new Customer(rs.getInt("CUSTOMERNO"), rs.getString("CUSTOMERNAME"), rs.getString("CUSTOMERADDRESS"), rs.getString("CUSTOMERCITY"), rs.getString("CUSTOMERSTATE"), rs.getString("CUSTOMERZIP"), rs.getString("CUSTOMEREMAIL"), rs.getString("CUSTOMERPHONE")));	
            }
        }
        catch(SQLException e){
            System.err.println(e);  
        }
        customerList = new JComboBox<>(customerComboBoxModel);
        customerList.setMaximumRowCount(5);
        return customerList;
    }  
	
    private int getCustomerNoFromName(Object customerName){
        int customerNo = 0;
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        String query = "SELECT customerNo FROM Customer WHERE customerName=?";
        PreparedStatement stmt = obj.createStatement(query);
        try{
            stmt.setString(1, ""+customerName);
	    ResultSet rs = stmt.executeQuery();
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
