/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Koddy Rae Madriaga
 */
public class DBoperations {
    
    private Connection getConnection() {
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost:3306/products";
            String username = "root";
            String password = "password";
            
            conn = DriverManager.getConnection(connectionString, username, password);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
    
        public String getProducts() {
        String table = "";
        String sql = "select * from products;";
        
                try {
            Connection conn = getConnection();
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                table = table + rs.getString(2) + "," + rs.getString(3) + ",";
            }
            
            rs.close();
            st.close();
            conn.close(); 
  
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return table;
    }
    
    public boolean addProduct(String productName, String productCost) {
        boolean result = false;
        
        String sql = "insert into products (productName, productUnitCost) values(?,?);";
        
                try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setString(1, productName);
            st.setString(2, productCost);
            
            int rowsAffected = st.executeUpdate();
            
            result = (rowsAffected > 0);
            
            st.close();
            conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean deleteProduct(String id) {
        boolean result = false;
        
        String sql = "delete from products where productID=?;";
        
        try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            
            st.setString(1, id);
            
            int rowsAffected = st.executeUpdate();
            
            result = (rowsAffected > 0);
            
            st.close();
            conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
