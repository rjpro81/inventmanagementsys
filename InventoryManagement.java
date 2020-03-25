import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.io.*;

/**
 * This is the main application driver class
 * @author Ralph Julsaint
 */
public class InventoryManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException{
        ApplicationFrame login = new ApplicationFrame();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        login.setSize((int)dimension.getWidth(), (int)dimension.getHeight() - 40);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
