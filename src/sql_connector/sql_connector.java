/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class sql_connector {
    Connection connection;
    Statement statement;
   public sql_connector() {
        String url= "jdbc:sqlserver://DESKTOP-543HFVM\\SQLEXPRESS;databaseName=UML;";  
        String username="sa";
        String password="123";
        try{
            connection = DriverManager.getConnection(url,username,password);
             statement=connection.createStatement();
            System.out.println("Connected to Microsoft SQL Server");
        }catch(SQLException e){
            System.out.println("OOP's Error");
            e.printStackTrace();
        }
    }
   
   public ResultSet execute_Query_for_get(String query){
       
       try{
           ResultSet rs=statement.executeQuery(query);
           return rs;
            }catch(SQLException e){
                System.out.println("Error in Execute Query");
                System.out.println(e.getMessage());
                return null;
            }
       
   } 
   
   public int execute_Query_for_set(String query){
       
       try{
           statement.executeUpdate(query);
           return 1;
            }catch(SQLException e){
                if(e.getErrorCode() == 2627){
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Already Allocate this Course to this Section","Alert",JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Error in Execute Query");
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                return 0;
            }
       
   }
   
    public static void main(String[] args) throws SQLException {
        sql_connector sql = new sql_connector();
        var rs=sql.execute_Query_for_get("select * from Admin where admin_id = "+1);
        if(rs==null){
            System.out.println("rs is null");
        }else{
           while(rs.next()){
                   System.out.println(rs.getString("admin_name")+"\t"+rs.getString("admin_user_name")+"\t"+rs.getString("password"));
                }
        }
    }
   
   @Override
    public void finalize() throws SQLException{
        connection.close();
        System.out.println("Disconnect to sql");
    }
   
}
