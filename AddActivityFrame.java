package com.ralph.inventmanagementsys;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class provides a JFrame Window object to add inventory activity to application.
 * @author Ralph Julsaint
 */
public class AddActivityFrame extends JFrame{
    private JPanel activityPanel;
    private JPanel activityComponentsPanel;
    private JPanel activityGridPanel;
    private JPanel activityButtonPanel;
    private JLabel activityLabel;
    private JTextField activityTextField;
    private JLabel activityDateLabel;
    private JTextField activityDateTextField; 
    private JButton activitySubmitButton;
    private final String url = "jdbc:derby://localhost:1527/inventory_management";
    private final ApplicationContext context = new AnnotationConfigApplicationContext(InventoryConfiguration.class);

    AddActivityFrame(){
	super("New Activity");  
	addActivityComponent();
    }	
	  
    private void addActivityComponent(){
	activityPanel = new JPanel();
	activityComponentsPanel = new JPanel();
	activityComponentsPanel.setLayout(new BoxLayout(activityComponentsPanel, BoxLayout.Y_AXIS));
	activityGridPanel = new JPanel();
	activityGridPanel.setLayout(new GridLayout(2, 2, 4, 4));
	activityButtonPanel = new JPanel();
	activityButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	activityLabel = new JLabel("Activity:");
	activityTextField = new JTextField(12);
	activityDateLabel = new JLabel("Date:");
	activityDateTextField = new JTextField(12);
	activitySubmitButton = new JButton("submit");
	    
	activityGridPanel.add(activityLabel);
	activityGridPanel.add(activityTextField);
	activityGridPanel.add(activityDateLabel);
	activityGridPanel.add(activityDateTextField);
	activityButtonPanel.add(activitySubmitButton);
	    
	activityComponentsPanel.add(activityGridPanel);
	activityComponentsPanel.add(activityButtonPanel);
	    
	activityPanel.add(activityComponentsPanel);
	    
        this.add(activityPanel);
	    
	   
	activitySubmitButton.addActionListener((ActionEvent evt) -> {
            LocalDate d = LocalDate.parse(activityDateTextField.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String dateAsString = d.format(formatter);
            d = LocalDate.parse(dateAsString);
            String text = activityTextField.getText();
            submitButtonActionPerformed(evt, text, d);
            activityTextField.setText("");
            activityDateTextField.setText("");
            AddActivityFrame.this.dispose();
        });
    }
	  
    private void submitButtonActionPerformed(ActionEvent evt, String activity, LocalDate d){
	class NewInventoryActivity extends Activity{
            LocalDate d;
	    NewInventoryActivity(String activity, LocalDate d){
		super(activity);
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
        NewInventoryActivity nia = new NewInventoryActivity(activity, d);
        DatabaseManager obj = context.getBean(DatabaseManager.class);
        String insertActivity = "INSERT INTO ACTIVITY activity VALUES (?)";
	PreparedStatement stmt = obj.createStatement(insertActivity);
        
        try{
            stmt.setString(1, nia.toString());
            int result = stmt.executeUpdate();
            if (result > 0){
                JOptionPane.showMessageDialog(null, "New activity added.", "New Activity", JOptionPane.PLAIN_MESSAGE);
                ApplicationFrame.inventoryActivityTable.setModel(new ResultSetTableModel(url, "SELECT * FROM Activity"));
            } else {
                JOptionPane.showMessageDialog(null, "No activity added.", "New Activity", JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }
	     
}
