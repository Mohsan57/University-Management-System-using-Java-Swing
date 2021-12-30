/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hod_dashboard;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sql_connector.sql_connector;


public class hod_home_panel extends JPanel{
    Color background_color;
    int hod_id;
    sql_connector sql;
    JLabel id;
    JLabel admin_name;
    JLabel admin_email;
    JLabel admin_department;
    
    hod_home_panel(int hod_id){
       this.hod_id = hod_id;
       sql = new sql_connector();
       id = new JLabel();
       admin_name = new JLabel();
       admin_email = new JLabel();
       admin_department = new JLabel();
       background_color = new Color(124, 193, 235);
       
       
       
        setLayout(new BorderLayout());
        setBackground(background_color);
        get_hod_detail();
        
        set_North();
        
        
        
        try {
            sql.finalize();
        } catch (SQLException ex) {
            Logger.getLogger(hod_home_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
     void get_hod_detail(){
            String query="select * from hod_basic_info("+hod_id+")";
        try{
            var rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                id.setText(Integer.toString(rs.getInt("hod_id")));
                admin_name.setText(rs.getString("hod_name"));
                admin_email.setText(rs.getString("hod_user_name"));
                admin_department.setText(rs.getString("department_name"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    void set_North(){
        JPanel north = new JPanel();
        JPanel north_p1 = new JPanel();
        JPanel north_p2 = new JPanel();
        JPanel north_p3 = new JPanel();
        JPanel north_p4 = new JPanel();
        JLabel l1 = new JLabel("ID: ");
        JLabel l2 = new JLabel("Name: ");
        JLabel l3 = new JLabel("Email: ");
        JLabel l4 = new JLabel("Department: ");
        north.setLayout(new GridLayout(2,2));
        
        north_p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        north.setBackground(background_color);
        north_p1.setBackground(null);
        north_p2.setBackground(null);
        north_p3.setBackground(null);
        north_p4.setBackground(null);
        
        north_p1.add(l1);
        north_p1.add(id);
        
        north_p2.add(l2);
        north_p2.add(admin_name);
        
        north_p3.add(l3);
        north_p3.add(admin_email);
        
        
        north_p4.add(l4);
        north_p4.add(admin_department);
        
        north.add(north_p1);
        north.add(north_p2);
        north.add(north_p3);
        north.add(north_p4);
        this.add(north,BorderLayout.NORTH);
    }
}
