
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Ralph Julsaint
 */
public class AppView extends JFrame {
    private DatabaseManager databaseManager;
    private List <Supplier> results;
    private List <Invoice> invoiceResults = new ArrayList<>();;
    private int numberOfEntries = 0;
    private int currentEntryIndex;
    private Supplier currentEntry;
    private String url = "jdbc:derby:inventory_management";
    private final String query = "SELECT * FROM Item";
    private final String invoice2018 = "SELECT * FROM Invoice WHERE YEAR (invoiceDate) = 2018";
    
    private JPanel addItemPanel;
    private JButton addSupplierButton;
    private JComboBox<String> graphTypeComboBox;
    private JComboBox<String> inventoryComboBox;
    private JPanel inventoryPanel;
    private JButton inventoryPrintButton;
    private JTextField inventorySearchButton;
    private JTable inventoryTable;
    private JTable invoiceTable;
    private JLabel inventory_supplierNoLabel;
    private JComboBox<String> invoiceComboBox;
    private JLabel invoiceNoLabel;
    private JTextField invoiceNoTextField;
    private JPanel invoicePanel;
    private JTextArea invoiceSelectTextArea;
    private JTextArea invoiceTextArea;
    private JLabel invoiceYearLabel;
    private JLabel itemDescLabel;
    private JTextField itemDescTextField;
    private JLabel itemNameLabel;
    private JTextField itemNameTextField;
    private JLabel itemNoLabel;
    private JTextField itemNoTextField;
    private JButton itemSubmitButton;
    private JLabel jLabel1;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel28;
    private JLabel jLabel29;
    private JLabel jLabel30;
    private JLabel jLabel33;
    private JLabel jLabel34;
    private JLabel jLabel35;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem10;
    private JMenuItem jMenuItem11;
    private JMenuItem jMenuItem12;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem7;
    private JMenuItem jMenuItem8;
    private JMenuItem jMenuItem9;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JTabbedPane jTabbedPane1;
    private JTextField partNoTextField;
    private JTextField priceTextField;
    private JButton printReportsButton;
    private JTextField quantityTextField;
    private JLabel recentActivityLabel;
    private JTextArea recentActivityTextArea;
    private JComboBox<String> reportMonthComboBox;
    private JComboBox<String> reportYearComboBox;
    private JPanel reportsPanel;
    private JTextPane reportsTextPane;
    private JPanel summaryPanel;
    private JLabel supplierAddressLabel;
    private JTextField supplierAddressTextField;
    private JButton supplierBackButton;
    private JLabel supplierCityLabel;
    private JTextField supplierCityTextField;
    private JLabel supplierEmailLabel;
    private JTextField supplierEmailTextField;
    private JLabel supplierNameLabel;
    private JTextField supplierNameTextField;
    private JButton supplierNextButton;
    private JLabel supplierNoLabel;
    private JTextField supplierNoTextField;
    private JLabel supplierPhoneLabel;
    private JTextField supplierPhoneTextField;
    private JLabel supplierPriceLabel;
    private JLabel supplierStateLabel;
    private JTextField supplierStateTextField;
    private JLabel supplierZipLabel;
    private JTextField supplierZipTextField;
    private JLabel supplier_partNoLabel;
    private JPanel suppliersPanel;
    private JTextField itemPriceTextField;
    private JTextField itemPartNoTextField;
    private JTextField itemSupplierNoTextField;
    private JScrollPane jScrollPane6;
    private JTextField jTextField15;
    private JLabel jLabel31;
    private JLabel jLabel32;
    private JButton invoicePrintButton;
    private ResultSetTableModel tableModel;
    private ResultSetTableModel tableModel2;
    private final Container container;
    private static List<RecentActivity> recentActivityList;

    /**
     * Creates new form AppView
     */
    public AppView() {
		try{
		  tableModel = new ResultSetTableModel(url, query);
	    }
	    catch (SQLException sqlException){
		  sqlException.printStackTrace();	
	    }
	    
	    try{
		  tableModel2 = new ResultSetTableModel(url, invoice2018);	
		}
		catch (SQLException sqlException){
		  sqlException.printStackTrace();	
	    }
	    container = getContentPane();
		
        initComponents();
        databaseManager = new DatabaseManager();
        displaySuppliers();
              
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTabbedPane1 = new JTabbedPane();
        summaryPanel = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        recentActivityLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        recentActivityTextArea = new JTextArea();
        jLabel4 = new JLabel();
        addItemPanel = new JPanel();
        jLabel5 = new JLabel();
        itemNoLabel = new JLabel();
        itemNoTextField = new JTextField();
        itemNameLabel = new JLabel();
        itemNameTextField = new JTextField();
        itemDescLabel = new JLabel();
        itemDescTextField = new JTextField();
        inventory_supplierNoLabel = new JLabel();
        itemSupplierNoTextField = new JTextField();
        supplierPriceLabel = new JLabel();
        itemNoTextField = new JTextField();
        itemSubmitButton = new JButton();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        itemPriceTextField = new JTextField();
        supplier_partNoLabel = new JLabel();
        itemPartNoTextField = new JTextField();
        inventoryPanel = new JPanel();
        jLabel14 = new JLabel();
        jScrollPane2 = new JScrollPane();
        inventoryTable = new JTable(tableModel);
        invoiceTable = new JTable(tableModel2);
        inventorySearchButton = new JTextField();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        inventoryPrintButton = new JButton();
        inventoryComboBox = new JComboBox<>();
        suppliersPanel = new JPanel();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        jLabel19 = new JLabel();
        supplierNoLabel = new JLabel();
        supplierNoTextField = new JTextField();
        supplierNameLabel = new JLabel();
        supplierNameTextField = new JTextField();
        supplierAddressLabel = new JLabel();
        supplierCityLabel = new JLabel();
        supplierCityTextField = new JTextField();
        supplierStateLabel = new JLabel();
        supplierStateTextField = new JTextField();
        supplierZipLabel = new JLabel();
        supplierZipTextField = new JTextField();
        supplierEmailLabel = new JLabel();
        supplierEmailTextField = new JTextField();
        supplierPhoneLabel = new JLabel();
        supplierPhoneTextField = new JTextField();
        supplierBackButton = new JButton();
        supplierNextButton = new JButton();
        supplierAddressTextField = new JTextField();
        addSupplierButton = new JButton();
        invoicePanel = new JPanel();
        jLabel28 = new JLabel();
        jLabel29 = new JLabel();
        jLabel30 = new JLabel();
        invoiceNoLabel = new JLabel();
        jScrollPane3 = new JScrollPane();
        invoiceTextArea = new JTextArea();
        invoiceNoTextField = new JTextField();
        jScrollPane4 = new JScrollPane();
        invoiceSelectTextArea = new JTextArea();
        invoiceYearLabel = new JLabel();
        invoiceComboBox = new JComboBox<>();
        reportsPanel = new JPanel();
        jLabel33 = new JLabel();
        jLabel34 = new JLabel();
        jLabel35 = new JLabel();
        jScrollPane5 = new JScrollPane();
        reportsTextPane = new JTextPane();
        reportYearComboBox = new JComboBox<>();
        printReportsButton = new JButton();
        reportMonthComboBox = new JComboBox<>();
        graphTypeComboBox = new JComboBox<>();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        jMenu2 = new JMenu();
        jMenuItem4 = new JMenuItem();
        jMenuItem5 = new JMenuItem();
        jMenuItem6 = new JMenuItem();
        jMenuItem7 = new JMenuItem();
        jMenuItem8 = new JMenuItem();
        jMenuItem9 = new JMenuItem();
        jMenuItem10 = new JMenuItem();
        jMenu3 = new JMenu();
        jMenuItem11 = new JMenuItem();
        jMenuItem12 = new JMenuItem();
        jScrollPane6 = new JScrollPane();
        jTextField15 = new JTextField();
        jLabel31 = new JLabel();
        jLabel32 = new JLabel();
        invoicePrintButton = new JButton();
        recentActivityList = new ArrayList<>();
        
        


        jLabel1.setText("Welcome, Ralph Julsaint");

        jLabel2.setText("Acct# 1234567");

        recentActivityLabel.setText("Recent Activity:");

        recentActivityTextArea.setColumns(20);
        recentActivityTextArea.setRows(5);
        recentActivityTextArea.setEditable(false);
        jScrollPane1.setViewportView(recentActivityTextArea);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Inventory Management System");

   

        jTabbedPane1.addTab("Summary", summaryPanel);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Inventory Management System");

        itemNoLabel.setText("Item #:");


        itemNameLabel.setText("Item Name:");


        itemDescLabel.setText("Item Desc:");

        inventory_supplierNoLabel.setText("Supplier #:");

        supplierPriceLabel.setText("Price:");

        itemSubmitButton.setText("Submit");
       
        itemSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSubmitButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("Welcome, Ralph Julsaint");

        jLabel13.setText("Acct# 1234567");


        supplier_partNoLabel.setText("Part #:");

      

        jTabbedPane1.addTab("Add Item", addItemPanel);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Inventory Management System");
        
        jScrollPane2.setViewportView(inventoryTable);

        inventorySearchButton.setText("Search");
        

        jLabel15.setText("Welcome, Ralph Julsaint");

        jLabel16.setText("Acct# 1234567");

        inventoryPrintButton.setText("Print");

        inventoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTabbedPane1.addTab("Inventory", inventoryPanel);

      

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Inventory Management System");

        jLabel18.setText("Welcome, Ralph Julsaint");

        jLabel19.setText("Acct# 1234567");

        supplierNoLabel.setText("Supplier #:");

        supplierNameLabel.setText("Supplier Name:");

        supplierAddressLabel.setText("Address:");

        supplierCityLabel.setText("City:");

        supplierStateLabel.setText("State:");

        supplierZipLabel.setText("Zip:");

        supplierEmailLabel.setText("Email:");

        supplierPhoneLabel.setText("Phone:");

        supplierBackButton.setText("<");

        supplierNextButton.setText(">");

       

        addSupplierButton.setText("Add");

        
        addSupplierButton.addActionListener(
          new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent evt){
			  addSupplierButtonActionPerformed(evt);	
		    }  
		  }
        );

        jTabbedPane1.addTab("Suppliers", suppliersPanel);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("Inventory Management System");

        jLabel29.setText("Welcome, Ralph Julsaint");

        jLabel30.setText("Acct# 1234567");

        invoiceNoLabel.setText("Invoice #:");

        jLabel31.setText("Invoice #");

        invoiceYearLabel.setText("Year:");

        invoicePrintButton.setText("Print");

        invoiceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2017", "2016", "2015" }));

        jScrollPane6.setViewportView(invoiceTable);


        jTabbedPane1.addTab("Invoices", invoicePanel);
        

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("Inventory Management System");

        jLabel34.setText("Welcome, Ralph Julsaint");

        jLabel35.setText("Acct. No.");

        jScrollPane5.setViewportView(reportsTextPane);

        reportYearComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "2018", "2017", "2016", "2018" }));

        printReportsButton.setText("Print");

        reportMonthComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));

        graphTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));


        jTabbedPane1.addTab("Reports", reportsPanel);
       

        jMenu1.setText("File");

        jMenuItem1.setText("Save...");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Print...");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem4.setText("Undo");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Redo");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Cut");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Copy");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Paste");
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Select All");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Find");
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem11.setText("Documentation...");
        jMenu3.add(jMenuItem11);

        jMenuItem12.setText("About...");
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);
        
        supplierBackButton.addActionListener(
          new ActionListener(){
			public void actionPerformed(ActionEvent evt){
			  browseBackButtonAction(evt);	
		    }  
		  }
        );
        
        supplierNextButton.addActionListener(
          new ActionListener(){
			public void actionPerformed(ActionEvent evt){
			  browserNextButtonAction(evt);	
		    }  
		  }
        );

    }

    
    

    private void displaySuppliers() {
        // TODO add your handling code here:
        results = databaseManager.getAllSuppliers();
        numberOfEntries = results.size();
        if (numberOfEntries != 0){
          currentEntryIndex = 0;
          currentEntry = results.get(currentEntryIndex);
          supplierNoTextField.setText(currentEntry.getSuppNo());
          supplierNameTextField.setText(currentEntry.getSuppName());
          supplierAddressTextField.setText(currentEntry.getSuppAddress());
          supplierCityTextField.setText(currentEntry.getSuppCity());
          supplierStateTextField.setText(currentEntry.getSuppState());
          supplierZipTextField.setText(currentEntry.getSuppZip());
          supplierEmailTextField.setText(currentEntry.getSuppEmail());
          supplierPhoneTextField.setText(currentEntry.getSuppPhone());
          supplierBackButton.setEnabled(true);
          supplierNextButton.setEnabled(true);
        }
    }
    
   
    
    private void browseBackButtonAction(java.awt.event.ActionEvent evt){
	  currentEntryIndex--;
	  if (currentEntryIndex < 0)
	    currentEntryIndex = numberOfEntries - 1;
	    
	  supplierTextFieldActionPerformed(evt);
	}
	
	private void browserNextButtonAction(java.awt.event.ActionEvent evt){
	  currentEntryIndex++;
	  if (currentEntryIndex >= numberOfEntries)
		currentEntryIndex = 0; 
	  
	  supplierTextFieldActionPerformed(evt);
	}
	
	private void supplierTextFieldActionPerformed(ActionEvent evt){
    
      if (numberOfEntries != 0 && currentEntryIndex < numberOfEntries){
		currentEntry = results.get(currentEntryIndex);
		supplierNoTextField.setText(currentEntry.getSuppNo());
		supplierNameTextField.setText(currentEntry.getSuppName());
		supplierAddressTextField.setText(currentEntry.getSuppAddress());
		supplierCityTextField.setText(currentEntry.getSuppCity());
		supplierStateTextField.setText(currentEntry.getSuppState());
		supplierZipTextField.setText(currentEntry.getSuppZip());
		supplierEmailTextField.setText(currentEntry.getSuppEmail());
		supplierPhoneTextField.setText(currentEntry.getSuppPhone());
	  }
    }
    
	/*
	private String displayTotalInventoryCount(){
	  Integer result = databaseManager.getItemCount();	
	  String totalCount = result.toString();
	  
	  return totalCount;
    }
    
    private String getExpiredItemCount(){
	  Integer result = databaseManager.getExpiredItemCount();
	  String expiredItem = result.toString();
	  
	  return expiredItem;	
	}
	
	private String getFlaggedItemList(){
	  List<String> result = databaseManager.getAllFlaggedItems();
	  String[] itemArray = new String[result.size()];
	  for (int i = 0; i < result.size(); i++){
		  itemArray[i] = result.get(i);
	  }
	  
	  return itemArray.toString();
	}
	
	private String getTotalSpending(){
	  Double totalSpending = databaseManager.getAnnualSpending();
	  String spending = totalSpending.toString();
	  
	  return spending;	
	}*/
    
    private void addSupplierButtonActionPerformed(ActionEvent evt){
	  AddSupplierView addSupplierView = new AddSupplierView();	
      this.dispose();
	}
	


    private void itemSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSubmitButtonActionPerformed
        // TODO add your handling code here:
        int result = databaseManager.addItem(Integer.parseInt(itemNoTextField.getText()), itemNameTextField.getText(),
            itemDescTextField.getText(), Double.parseDouble(itemPriceTextField.getText()), Integer.parseInt(itemSupplierNoTextField.getText()));
        if (result == 1){
            JOptionPane.showMessageDialog(this, "Item added!", "Item added", JOptionPane.PLAIN_MESSAGE);
            recentActivityList.add(new AddItemActivity("Item #: " + itemNoTextField.getText() + " Item: " + itemNameTextField.getText() + "  Item Price: " + itemPriceTextField.getText()));
            recentActivityTextArea.setText(recentActivityList.toString());
            container.validate();
            itemNoTextField.setText("");
            itemNameTextField.setText("");
            itemDescTextField.setText("");
            itemPriceTextField.setText("");
            itemPartNoTextField.setText("");
            itemSupplierNoTextField.setText("");
        }
        else {
            JOptionPane.showMessageDialog(this, "Item not added!", "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
