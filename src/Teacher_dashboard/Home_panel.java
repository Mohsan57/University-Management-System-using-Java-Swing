/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teacher_dashboard;
import java.awt.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql_connector.sql_connector;


public class Home_panel extends JPanel{
    Color background_color;
    JLabel photo_label;
    int teacher_id;
    sql_connector sql;
    String name,gender,date_of_birth,phone_no,department,city,email;
    
    Home_panel(int teacher_id){
        this.teacher_id = teacher_id;
        
        photo_label = new JLabel();
        sql = new sql_connector();
        background_color = new Color(124, 193, 235);
        
        
        setBackground(background_color);
        this.setLayout(new BorderLayout());
        get_teacher_detail();
        components();
        try {
            sql.finalize();
        } catch (SQLException ex) {
            Logger.getLogger(Home_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void components(){
        JPanel p1 =new JPanel();
        JPanel p2 =new JPanel();
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        photo_label.setPreferredSize(new Dimension(200,200));
        p1.add(photo_label);
        p1.setBackground(null);
        p2.setBackground(null);
        
        p2.setLayout(new GridLayout(8,3,0,0));
        
        JPanel p3=new JPanel();
        JPanel p4=new JPanel();
        JPanel p5=new JPanel();
        JPanel p6=new JPanel();
        JPanel p7=new JPanel();
        JPanel p8=new JPanel();
        JPanel p9=new JPanel();
        JPanel p10=new JPanel();
        
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        p6.setLayout(new FlowLayout(FlowLayout.LEFT));
        p7.setLayout(new FlowLayout(FlowLayout.LEFT));
        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
        p9.setLayout(new FlowLayout(FlowLayout.LEFT));
        p10.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel l1 = new JLabel("Name: "+name);
        JLabel l2 = new JLabel("Gender: "+gender);
        JLabel l3 = new JLabel("DOB: "+date_of_birth);
        JLabel l4 = new JLabel("Email: "+email);
        JLabel l5 = new JLabel("Department: "+department);
        JLabel l6 = new JLabel("City: "+city);
        JLabel l7 = new JLabel("Phone No: "+phone_no);
        
        
        p3.setBackground(null);
        p4.setBackground(null);
        p5.setBackground(null);
        p6.setBackground(null);
        p7.setBackground(null);
        p8.setBackground(null);
        p9.setBackground(null);
        p10.setBackground(null);
        
        p3.add(l1);
        p4.add(l2);
        p5.add(l3);
        p6.add(l4);
        p7.add(l5);
        p8.add(l5);
        p9.add(l6);
        p10.add(l7);
        
        p2.add(p3);
        p2.add(p4);
        p2.add(p5);
        p2.add(p6);
        p2.add(p7);
        p2.add(p8);
        p2.add(p9);
        p2.add(p10);
        JPanel p[] =new JPanel[10];
       for(int i=0;i<10;i++){
           p[i] =new JPanel();
           p2.add(p[i]);
           p[i].setBackground(null);
       }
        
        this.add(p2,BorderLayout.CENTER);
        this.add(p1,BorderLayout.WEST);
    }
    void get_teacher_detail(){
        try {
            String query="select * from teacher_detail("+this.teacher_id+")";
            ResultSet rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                name =rs.getString("name");
                gender = rs.getString("gender");
                date_of_birth = rs.getDate("date_of_birth").toString();
                phone_no =rs.getString("phone_no");
                department =rs.getString("department_name");
                city = rs.getString("city");
                email = rs.getString("teacher_email");
                byte[] photo_source = rs.getBytes("photo");
                if(photo_source!=null){
                    ImageIcon icon= new ImageIcon(photo_source);
                    Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
                    ImageIcon newimage = new ImageIcon(image);
                    photo_label.setIcon(newimage);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
