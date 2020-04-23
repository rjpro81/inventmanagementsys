package com.ralph.inventmanagementsys;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;


/**
 * This is the application's main class.
 * @author Ralph Julsaint
 */
public class InventoryMain {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException, IOException {
        ApplicationFrame login = new ApplicationFrame();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        login.setSize((int)dimension.getWidth(), (int)dimension.getHeight() - 40);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   
}
