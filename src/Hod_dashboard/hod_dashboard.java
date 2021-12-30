
package Hod_dashboard;
import Login_Page.Login_Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hod_dashboard extends  JFrame implements ActionListener{
    
    private  JButton home_button,add_teacher_button,add_course_button,logout_button;
    private  JLabel logo,heading;
    private  JPanel left_panel,center,left_top_panel,left_center_panel,center_top_panel;
    Color left_side_color,right_side_color,font_color;
    hod_home_panel home_panel;
    teacher_registration_panel registration_panel;
    Add_courses_panel section_panel;
    int hod_id;
    public hod_dashboard(int hod_id) {
        this.hod_id=hod_id;
        initComponents();
        super.setSize(1000, 550);
    }
                        
    private void initComponents() {

        left_panel = new  JPanel();
        left_top_panel = new  JPanel();
        logo = new  JLabel();
        left_center_panel = new  JPanel();
        home_button = new  JButton();
        add_teacher_button = new  JButton();
        add_course_button = new  JButton();
        logout_button = new  JButton("Logout");
        home_panel = new hod_home_panel(this.hod_id);
        registration_panel = new teacher_registration_panel(this.hod_id);
        center = new  JPanel();
        center_top_panel = new  JPanel();
        heading = new  JLabel("");
        right_side_color = new Color(124, 193, 235);
        left_side_color = new Color(17, 12, 50);
        font_color = new Color(220, 221, 232);
        section_panel = new Add_courses_panel(this.hod_id);
        
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        setTitle("HOD");
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
        add_teacher_button.setBackground(left_side_color);
        add_course_button.setBackground(left_side_color);
        
        home_button.setForeground(font_color);
        add_teacher_button.setForeground(font_color);
        add_course_button.setForeground(font_color);
        heading.setForeground(font_color);
    }
    void buttons(){
        Font font =new Font("Calisto MT", Font.BOLD, 14);
        home_button.setText("Home");
        setHeadingText("Home");
        home_button.setBorder(null);
        other_panels(home_panel);
        home_button.setCursor(new  Cursor( Cursor.HAND_CURSOR));
        home_button.setOpaque(true);
        left_center_panel.add(home_button);
        home_button.doClick();
        home_button.setFont(font);
        add_teacher_button.setFont(font);
        add_course_button.setFont(font);
        
        home_button.addActionListener(this);
        
        add_teacher_button.setText("Add New Teacher");
        add_teacher_button.setCursor(new  Cursor( Cursor.HAND_CURSOR));
        add_teacher_button.setOpaque(true);
        add_teacher_button.setBorder(null);
        left_center_panel.add(add_teacher_button);
        add_teacher_button.addActionListener(this);
        
        add_course_button.setText("Add Courses");
        add_course_button.setCursor(new  Cursor( Cursor.HAND_CURSOR));
        add_course_button.setOpaque(true);
        add_course_button.setBorder(null);
        add_course_button.addActionListener(this);
        left_center_panel.add(add_course_button);

        
    }
    public static void main(String args[]) {
       
                new hod_dashboard(2).setVisible(true);
    }

    void other_panels(JPanel temp){
        home_panel.setVisible(false);
        registration_panel.setVisible(false);
        section_panel.setVisible(false);
        
        temp.setVisible(true);
        center.add(temp,BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Home"){
            home_button.setBackground(right_side_color);
            add_teacher_button.setBackground(left_side_color);
            add_course_button.setBackground(left_side_color);
            
            
            setHeadingText("Home");
            other_panels(home_panel);
            
        }else if(e.getActionCommand()=="Add New Teacher"){
            home_button.setBackground(left_side_color);
            add_teacher_button.setBackground(right_side_color);
            add_course_button.setBackground(left_side_color);
            setHeadingText("Add New Teacher");
            other_panels(registration_panel);
            
        }else if(e.getActionCommand()=="Add Courses"){
            home_button.setBackground(left_side_color);
            add_teacher_button.setBackground(left_side_color);
            add_course_button.setBackground(right_side_color);
            setHeadingText("Add Courses");
            other_panels(section_panel);
            
        }else if(e.getActionCommand()=="Allocate Courses"){
            home_button.setBackground(left_side_color);
            add_teacher_button.setBackground(left_side_color);
            add_course_button.setBackground(left_side_color);
            setHeadingText("Allocate Courses");
        }else if(e.getActionCommand() == "Logout"){
            this.dispose();
            new Login_Page();
        }
    }
           
}
