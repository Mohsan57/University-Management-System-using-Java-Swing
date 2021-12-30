/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teacher_dashboard;
import Login_Page.Login_Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Teacher_dashboard extends  JFrame implements ActionListener{
    
    private  JButton home_button,final_marks,Setting,logout_button,chat_button;
    private  JLabel logo,heading;
    private  JPanel left_panel,center,left_top_panel,left_center_panel,center_top_panel;
    Color left_side_color,right_side_color,font_color;
    Home_panel home_panel;
    add_Assignment_panel add_assig_panel;
    Setting_panel Setting_Panel;
    Chat chat;
    int teacher_id;
    public Teacher_dashboard(int teacher_id) {
        this.teacher_id = teacher_id;
        Components();
        super.setSize(1000, 550);
    }
                        
    private void Components() {

        left_panel = new  JPanel();
        left_top_panel = new  JPanel();
        logo = new  JLabel();
        left_center_panel = new  JPanel();
        home_button = new  JButton();
        final_marks = new  JButton();
        Setting = new  JButton();
        logout_button = new  JButton("Logout");
        chat_button = new  JButton("Chat");
        home_panel = new Home_panel(this.teacher_id);
        center = new  JPanel();
        center_top_panel = new  JPanel();
        heading = new  JLabel("");
        right_side_color = new Color(124, 193, 235);
        left_side_color = new Color(17, 12, 50);
        font_color = new Color(220, 221, 232);
        add_assig_panel = new add_Assignment_panel(teacher_id);
        Setting_Panel = new Setting_panel(teacher_id);
        chat = new Chat(teacher_id);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        setTitle("Teacher");
        setLocation(new  Point(200, 100));

        
        left_panel.setLayout(new  BorderLayout());

        logo.setHorizontalAlignment( SwingConstants.CENTER);
        logo.setIcon(new  ImageIcon(getClass().getResource("/Images/logo2_1.png"))); // NOI18N
        logo.setPreferredSize(new  Dimension(100, 70));
        left_top_panel.add(logo);

        left_panel.add(left_top_panel,  BorderLayout.PAGE_START);

        left_center_panel.setLayout(new  GridLayout(7, 1, 8, 8));

        left_panel.add(left_center_panel,  BorderLayout.CENTER);
        left_panel.add(logout_button,BorderLayout.PAGE_END);
        logout_button.setBackground(left_side_color);
        logout_button.setForeground(font_color);
        logout_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout_button.addActionListener(this);
        
        getContentPane().add(left_panel,  BorderLayout.LINE_START);

        center.setLayout(new  BorderLayout());

        center_top_panel.setOpaque(false);

        heading.setFont(new  Font("Calisto MT", Font.BOLD, 27)); // NOI18N
        heading.setHorizontalAlignment( SwingConstants.CENTER);
        
        center_top_panel.add(heading);
        
        center.add(center_top_panel,  BorderLayout.PAGE_START);

        getContentPane().add(center,  BorderLayout.CENTER);
        buttons();
        background_colors();
    }
    public void setHeadingText(String text){
        heading.setText(text);
    }
    void background_colors(){
        left_panel.setBackground(left_side_color);
        center.setBackground(left_side_color);
        left_top_panel.setBackground(left_side_color);
        left_center_panel.setBackground(left_side_color);
        center_top_panel.setBackground(left_side_color);
        home_button.setBackground(right_side_color);
        final_marks.setBackground(left_side_color);
        Setting.setBackground(left_side_color);
        chat_button.setBackground(left_side_color);
        
        home_button.setForeground(font_color);
        final_marks.setForeground(font_color);
        Setting.setForeground(font_color);
        heading.setForeground(font_color);
        chat_button.setForeground(font_color);
    }
    void buttons(){
        Font font =new Font("Calisto MT", Font.BOLD, 14);
        home_button.setText("Home");
        setHeadingText("Home");
        home_button.setBorder(null);
        
        other_panels(home_panel);
        Cursor cursor=new  Cursor( Cursor.HAND_CURSOR);
        home_button.setCursor(cursor);
        home_button.setOpaque(true);
        left_center_panel.add(home_button);
        home_button.doClick();
        home_button.setFont(font);
        final_marks.setFont(font);
        Setting.setFont(font);
        chat_button.setFont(font);
        
        home_button.addActionListener(this);
        
        
        final_marks.setText("Add Assignment");
        final_marks.setCursor(cursor);
        final_marks.setOpaque(true);
        final_marks.setBorder(null);
        final_marks.addActionListener(this);
        left_center_panel.add(final_marks);
        
        
        Setting.setText("Setting");
        Setting.setCursor(cursor);
        Setting.setOpaque(true);
        Setting.setBorder(null);
        Setting.addActionListener(this);
        left_center_panel.add(Setting);
        
        chat_button.setCursor(cursor);
        chat_button.setOpaque(true);
        chat_button.setBorder(null);
        chat_button.addActionListener(this);
        left_center_panel.add(chat_button);
        
    }
    public static void main(String args[]) {
       
                new Teacher_dashboard(2).setVisible(true);
    }

    void other_panels(JPanel temp){
        home_panel.setVisible(false);
        add_assig_panel.setVisible(false);
        Setting_Panel.setVisible(false);
        chat.setVisible(false);
        
        temp.setVisible(true);
        center.add(temp,BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Home"){
            home_button.setBackground(right_side_color);
            final_marks.setBackground(left_side_color);
            Setting.setBackground(left_side_color);
            chat_button.setBackground(left_side_color);
            
            
            setHeadingText("Home");
            other_panels(home_panel);
            
        }else if(e.getActionCommand()=="Assignment Marks"){
            home_button.setBackground(left_side_color);
            final_marks.setBackground(left_side_color);
            Setting.setBackground(left_side_color);
            chat_button.setBackground(left_side_color);
            setHeadingText("Assignment Marks");
            
        }else if(e.getActionCommand()=="Add Assignment"){
            home_button.setBackground(left_side_color);
            final_marks.setBackground(right_side_color);
            Setting.setBackground(left_side_color);
            
            chat_button.setBackground(left_side_color);
            setHeadingText("Add Assignment");
            other_panels(add_assig_panel);
            
        }else if(e.getActionCommand()=="Setting"){
            home_button.setBackground(left_side_color);
            final_marks.setBackground(left_side_color);
            Setting.setBackground(right_side_color);
            chat_button.setBackground(left_side_color);
            
            setHeadingText("Setting");
            other_panels(Setting_Panel);
        
        }else if(e.getActionCommand() == "Chat"){
             home_button.setBackground(left_side_color);
            final_marks.setBackground(left_side_color);
            Setting.setBackground(left_side_color);
            chat_button.setBackground(right_side_color);
            
             setHeadingText("Chat");
            other_panels(chat);
        }
        else if(e.getActionCommand() == "Logout"){
            this.dispose();
            new Login_Page();
        }
    }

}
