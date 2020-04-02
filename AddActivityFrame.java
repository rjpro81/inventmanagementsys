/**
 * This class provides a JFrame Window object to add inventory activity to application.
 */
 
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.time.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;


class AddActivityFrame extends JFrame{
private JPanel activityPanel;
private JPanel activityComponentsPanel;
private JPanel activityGridPanel;
private JPanel activityButtonPanel;
private JLabel activityLabel;
private JTextField activityTextField;
private JLabel activityDateLabel;
private JTextField activityDateTextField;
private JButton activitySubmitButton;

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
    activityLabel = new JLabel("activity:");
    activityTextField = new JTextField(12);
    activityDateLabel = new JLabel("date:");
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
    
    add(activityPanel);
    
    
    activitySubmitButton.addActionListener(
      new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent evt){
		  String dateAsString = activityDateTextField.getText();
		  LocalDate d = LocalDate.of(Integer.parseInt(dateAsString.substring(6, 10)), Integer.parseInt(dateAsString.substring(0, 2)), Integer.parseInt(dateAsString.substring(3, 5)));
		  String text = activityTextField.getText();
		  submitButtonActionPerformed(evt, text, d);
		}  
	  }
    );
  }
  
  private void submitButtonActionPerformed(ActionEvent evt, String activity, LocalDate date){
	File file = new File("inventory_activity.txt");
	
	class NewInventoryActivity extends Activity{
	  NewInventoryActivity(String a, LocalDate d){
		super(a, d);
	  }	
	}
	try(BufferedWriter out = new BufferedWriter(new FileWriter(file, true))){
	  NewInventoryActivity nia = new NewInventoryActivity(activity, date);
	  int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
	  if (option == 0){
	    out.write(nia.toString(), 0, nia.toString().length());
	    activityTextField.setText("");
	    activityDateTextField.setText("");
	    Object[] rowData = {nia};
	    updateActivityTable(rowData);
	    this.dispose();
	  }
	} 
	catch(IOException e){
	  System.err.println(e);	
	} 
  }
  
  private void updateActivityTable(Object[] rowData){
    ApplicationFrame.inventoryActivityTableModel.addRow(rowData);
  }
}
