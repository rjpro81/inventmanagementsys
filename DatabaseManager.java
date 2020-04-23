package com.ralph.inventmanagementsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class provides a database connectivity management using JDBC.
 * @author Ralph Julsaint
 */
class DatabaseManager {
    private PreparedStatement pstmt;
    private Connection conn;
    
    DatabaseManager(String url){
        try{
            conn = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }
    
    PreparedStatement createStatement(String query){
        try{
            pstmt = conn.prepareCall(query);
        }
        catch(SQLException e){
            System.err.println(e);
        }
        
        return pstmt;
    }
}
