/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




/**
 *
 * @author Lucas
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbnovarenata";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL,USER,PASS);
        }
        catch (ClassNotFoundException | SQLException ex) {
        
           throw  new RuntimeException("erro na conexao: ",ex);   
        }
    }
    
    public static void closedConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        }
        catch(SQLException ex){
            throw  new RuntimeException("erro no fechamento: ",ex);   
           
        }
    }
    
    public static void closedConnection(Connection con, PreparedStatement stmt){
        
        closedConnection(con);
        
        try{
            if(stmt != null){
                stmt.close();
            }
        }
        catch(SQLException ex){
            throw  new RuntimeException("erro no fechamento com stmt: ",ex);   
           
        }
    }
    
        public static void closedConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closedConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            throw  new RuntimeException("erro no fechamento com rs: ",ex);   
        }
    }
}    
