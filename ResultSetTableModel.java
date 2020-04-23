package com.ralph.inventmanagementsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

/**
 * This class creates an AbstractTableModel object for constructing tables in the application.
 * @author Ralph Julsaint
 */
public class ResultSetTableModel extends AbstractTableModel{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean connectedToDatabase = false;
  
    ResultSetTableModel(String url, String query) throws SQLException {
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
        connectedToDatabase = true;
        setQuery(query);
    }
	   
    /**
     *
     * @param column
     * @return
     * @throws IllegalStateException
     */
    @Override
    public Class<?> getColumnClass(int column) throws IllegalStateException{
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
		   
        try{
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);   
        } 
        catch (ClassNotFoundException | SQLException e){
            System.err.println(e);   
        }  
        return Object.class;
    }
	   
    @Override
    public int getColumnCount() throws IllegalStateException{
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
		   
        try{
            return metaData.getColumnCount();	 
        }   
        catch (SQLException e){
            System.err.println(e);	 
        }
        return 0;
    }	 
	   
    /**
     *
     * @param column
     * @return
     * @throws IllegalStateException
     */
    @Override
    public String getColumnName(int column) throws IllegalStateException{
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
		   
        try{
            return metaData.getColumnName(column + 1);	 
        }   
        catch (SQLException e){
            System.err.println(e);	 
        }
        return "";
    }
	   
    /**
     *
     * @return
     * @throws IllegalStateException
     */
    @Override
    public int getRowCount() throws IllegalStateException{
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
	       
         return numberOfRows;	   
    }
	   
    /**
     *
     * @param row
     * @param column
     * @return
     * @throws IllegalStateException
     */
    @Override
    public Object getValueAt(int row, int column) throws IllegalStateException{
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
		   
        try{
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);	 
        }   
        catch (SQLException e){
            System.err.println(e);	 
        }
        return "";
    }
	   
    private void setQuery(String query) throws SQLException, IllegalStateException{
        if (!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
	       
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void close(){
        if (connectedToDatabase){
            try{
                resultSet.close();
                statement.close();
                connection.close();   
            }	 
            catch (SQLException e){
                System.err.println(e);   
            }
            finally{
                connectedToDatabase = false;   
            }
        }   
    }    
}
