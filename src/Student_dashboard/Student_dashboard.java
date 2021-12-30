
package Student_dashboard;

import Login_Page.Login_Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_dashboard extends  JFrame implements ActionListener{
    
    private  JButton home_button,Enrollment,submit_assignment,Setting,logout_button,chat_button;
    private  JLabel logo,heading;
    private  JPanel left_panel,center,left_top_panel,left_center_panel,center_top_panel;
    Color left_side_color,right_side_color,font_color;
    Home_panel Home_panel;
    Submit_Assignment Submit_Assignment_Panel;
    Enrollment_panel Enrollment_panel;
    Setting_panel Setting_Panel;
    Chat chat;
    int student_id;
    public Student_dashboard(int student_id) {
        this.student_id = student_id;
        Components();
        super.setSize(1000, 550);
        
    }
                        
    private void Components() {

        left_panel = new  JPanel();
        left_top_panel = new  JPanel();
        logo = new  JLabel();
        left_center_panel = new  JPanel();
        home_button = new  JButton();
        Enrollment = new  JButton();
        Setting = new  JButton();
        logout_button = new  JButton("Logout");
        submit_assignment  = new JButton();
        chat_button = new  JButton("Chat");
        Home_panel = new Home_panel(this.student_id);
        Submit_Assignment_Panel = new Submit_Assignment(this.student_id);
        center = new  JPanel();
        center_top_panel = new  JPanel();
        heading = new  JLabel("");
        right_side_color = new Color(124, 193, 235);
        left_side_color = new Color(17, 12, 50);
        font_color = new Color(220, 221, 232);
        Enrollment_panel = new Enrollment_panel(student_id);
        Setting_Panel = new Setting_panel(student_id);
        chat = new Chat(student_id);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student");
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
        submit_assignment.setBackground(left_side_color);
        Enrollment.setBackground(left_side_color);
        Setting.setBackground(left_side_color);
        chat_button.setBackground(left_side_color);
        
        home_button.setForeground(font_color);
        submit_assignment.setForeground(font_color);
        Enrollment.setForeground(font_color);
        Setting.setForeground(font_color);
        heading.setForeground(font_color);
        chat_button.setForeground(font_color);
    }
    void buttons(){
        Font font =new Font("Calisto MT", Font.BOLD, 14);
        home_button.setText("Home");
        setHeadingText("Home");
        home_button.setBorder(null);
        
        other_panels(Home_panel);
        Cursor cursor=new  Cursor( Cursor.HAND_CURSOR);
        home_button.setCursor(cursor);
        home_button.setOpaque(true);
        left_center_panel.add(home_button);
        home_button.doClick();
        home_button.setFont(font);
        submit_assignment.setFont(font);
        Enrollment.setFont(font);
        Setting.setFont(font);
        chat_button.setFont(font);
        
        home_button.addActionListener(this);
        
        
        Enrollment.setText("Enrollment");
        Enrollment.setCursor(cursor);
        Enrollment.setOpaque(true);
        Enrollment.setBorder(null);
        Enrollment.addActionListener(this);
        left_center_panel.add(Enrollment);
        
        
        submit_assignment.setText("Submit Assignment");
        submit_assignment.setCursor(cursor);
        submit_assignment.setOpaque(true);
        submit_assignment.setBorder(null);
        left_center_panel.add(submit_assignment);
        submit_assignment.addActionListener(this);
        
        
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
        new Student_dashboard(70100).setVisible(true);
    }

    void other_panels(JPanel temp){
        Home_panel.setVisible(false);
        Submit_Assignment_Panel.setVisible(false);
        Enrollment_panel.setVisible(false);
        Setting_Panel.setVisible(false);
        chat.setVisible(false);
        
        temp.setVisible(true);
        center.add(temp,BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Home"){
            home_button.setBackground(right_side_color);
            submit_assignment.setBackground(left_side_color);
            Enrollment.setBackground(left_side_color);
            Setting.setBackground(left_side_color);
            chat_button.setBackground(left_side_color);
            
            
            setHeadingText("Home");
            other_panels(Home_panel);
            
        }else if(e.getActionCommand()=="Submit Assignment"){
            home_button.setBackground(left_side_color);
            submit_assignment.setBackground(right_side_color);
            Enrollment.setBackground(left_side_color);
            Setting.setBackground(left_side_color);
            chat_button.setBackground(left_side_color);
            setHeadingText("Submit Assignment");
            other_panels(Submit_Assignment_Panel);
            
        }else if(e.getActionCommand()=="Enrollment"){
            home_button.setBackground(left_side_color);
            submit_assignment.setBackground(left_side_color);
            Enrollment.setBackground(right_side_color);
            Setting.setBackground(left_side_color);
            chat_button.setBackground(left_side_color);
            
            setHeadingText("Enrollment");
            other_panels(Enrollment_panel);
            
        }else if(e.getActionCommand()=="Setting"){
            home_button.setBackground(left_side_color);
            submit_assignment.setBackground(left_side_color);
            Enrollment.setBackground(left_side_color);
            Setting.setBackground(right_side_color);
            chat_button.setBackground(left_side_color);
            
            setHeadingText("Setting");
            other_panels(Setting_Panel);
        
        }else if(e.getActionCommand() == "Chat"){
            home_button.setBackground(left_side_color);
            submit_assignment.setBackground(left_side_color);
            Enrollment.setBackground(left_side_color);
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
