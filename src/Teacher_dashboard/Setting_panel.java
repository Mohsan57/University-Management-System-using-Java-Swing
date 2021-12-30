/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Teacher_dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql_connector.sql_connector;


public class Setting_panel extends JPanel implements ActionListener{
        JLabel l1,l2,l3,l4,l5;
        JPasswordField t1,t2,t3;
        JButton b1;
        Color background_color;
        int teacher_id;
        String pass;
        sql_connector sql;
        Setting_panel(int teacher_id){
            this.teacher_id = teacher_id;
            l1= new JLabel("Old Password");
        l2= new JLabel("New Password");
        l3= new JLabel("Confirm Password");
        l4= new JLabel("Email : ");
        l5= new JLabel("");
        sql=new sql_connector();
        background_color = new Color(124, 193, 235);
        
        setBackground(background_color);
        
        
        t1= new JPasswordField("");
        t2= new JPasswordField("");
        t3= new JPasswordField("");
        
        b1 = new JButton("Update");
        
        this.setLayout(new GridLayout(7,1));
        get_email();
        components();
        
        }
    void components(){
       JPanel p1 = new JPanel();
       JPanel p2 = new JPanel();
       JPanel p3 = new JPanel();
       JPanel p4 = new JPanel();
       JPanel p5 = new JPanel();
       
       
        p1.setBackground(background_color);
        p2.setBackground(background_color);
        p3.setBackground(background_color);
        p4.setBackground(background_color);
        p5.setBackground(background_color);
       
       
       
       p5.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p5.add(l4);
       p5.add(l5);
       
       p1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p1.add(l1);
       p1.add(t1);
       t1.setPreferredSize(new Dimension(200,30));
       
       p2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p2.add(l2);
       p2.add(t2);
       t2.setPreferredSize(new Dimension(200,30));
       
       p3.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p3.add(l3);
       p3.add(t3);
       t3.setPreferredSize(new Dimension(200,30));
       
       p4.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
       p4.add(b1);
       b1.setPreferredSize(new Dimension(100,30));
       b1.addActionListener(this);
       this.add(p5);
       this.add(p1);
       this.add(p2);
       this.add(p3);
       this.add(p4);  
    }
    void get_email(){
            try {
                String q="select * from Teachers_Accounts where teacher_id = "+Integer.toString(teacher_id)+"";
                ResultSet rs = sql.execute_Query_for_get(q);
                while(rs.next()){
                    l5.setText(rs.getString("teacher_email"));
                    pass = rs.getString("password");
                }   
            } catch (SQLException ex) {
                Logger.getLogger(Setting_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    void pass_update(){
        String con_password = new String(t3.getPassword());
        String q="update Teachers_Accounts\n" +
            "set password='"+con_password+"' \n" +
            "where teacher_email='"+l5.getText()+"' ";
        int a = sql.execute_Query_for_set(q);
        if(a==1){
            JFrame f=new JFrame();
            JOptionPane.showMessageDialog(f,"Password update","Alert",JOptionPane.INFORMATION_MESSAGE);
            pass = new String(con_password);
            t1.setText("");
            t2.setText("");
            t3.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String old = new String(t1.getPassword());
            String p1 = new String(t2.getPassword());
            String p2 = new String(t3.getPassword());
            
            if(p1.equals(p2)){
                if(old.equals(pass)){
                    pass_update();
                }else{
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Incorrect Password","Alert",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Password and Confirm Password not Match","Alert",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
