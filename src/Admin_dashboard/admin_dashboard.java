
package Admin_dashboard;
import Login_Page.Login_Page;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class admin_dashboard extends  JFrame implements ActionListener{
    
    private  JButton home_button,add_student_button,create_section_button, allocate_course_button,logout_button;
    private  JLabel logo,heading;
    private  JPanel left_panel,center,left_top_panel,left_center_panel,center_top_panel;
    Color left_side_color,right_side_color,font_color;
    admin_home_panel home_panel;
    student_registration_panel registration_panel;
    create_section_panel section_panel;
    allocate_courses_panel allocation;
    int admin_id;
    String admin_name;
    String admin_email;
    String admin_department;
    public admin_dashboard(int id) throws SQLException {
        admin_id = id;
        initComponents();
        super.setSize(1000, 550);
    }
                        
    private void initComponents() {
        left_panel = new  JPanel();
        left_top_panel = new  JPanel();
        logo = new  JLabel();
        left_center_panel = new  JPanel();
        home_button = new  JButton();
        add_student_button = new  JButton();
        create_section_button = new  JButton();
        allocate_course_button = new  JButton();
        logout_button = new  JButton("Logout");
        home_panel = new admin_home_panel(admin_id);
        registration_panel = new student_registration_panel(admin_id);
        center = new  JPanel();
        center_top_panel = new  JPanel();
        heading = new  JLabel("");
        right_side_color = new Color(124, 193, 235);
        left_side_color = new Color(17, 12, 50);
        font_color = new Color(220, 221, 232);
        section_panel = new create_section_panel(this.admin_id);
        allocation = new allocate_courses_panel(admin_id);
        
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
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
        add_student_button.setBackground(left_side_color);
        create_section_button.setBackground(left_side_color);
        allocate_course_button.setBackground(left_side_color);
        
        home_button.setForeground(font_color);
        add_student_button.setForeground(font_color);
        create_section_button.setForeground(font_color);
        allocate_course_button.setForeground(font_color);
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
        add_student_button.setFont(font);
        create_section_button.setFont(font);
        allocate_course_button.setFont(font);
        
        home_button.addActionListener(this);
        
        add_student_button.setText("Add New Student");
        add_student_button.setCursor(new  Cursor( Cursor.HAND_CURSOR));
        add_student_button.setOpaque(true);
        add_student_button.setBorder(null);
        left_center_panel.add(add_student_button);
        add_student_button.addActionListener(this);
        
        create_section_button.setText("Create Section");
        create_section_button.setCursor(new  Cursor( Cursor.HAND_CURSOR));
        create_section_button.setOpaque(true);
        create_section_button.setBorder(null);
        create_section_button.addActionListener(this);
        left_center_panel.add(create_section_button);

        allocate_course_button.setText("Allocate Courses");
        allocate_course_button.setCursor(new  Cursor( Cursor.HAND_CURSOR));
        allocate_course_button.setOpaque(true);
        allocate_course_button.setBorder(null);
        allocate_course_button.addActionListener(this);
        left_center_panel.add(allocate_course_button);
        
    }
    public static void main(String args[]) throws SQLException {
       
                new admin_dashboard(1).setVisible(true);
    }

    void other_panels(JPanel temp){
        home_panel.setVisible(false);
        registration_panel.setVisible(false);
        section_panel.setVisible(false);
        allocation.setVisible(false);
        
        temp.setVisible(true);
        center.add(temp,BorderLayout.CENTER);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Home"){
            home_button.setBackground(right_side_color);
            add_student_button.setBackground(left_side_color);
            create_section_button.setBackground(left_side_color);
            allocate_course_button.setBackground(left_side_color);
            
            
            setHeadingText("Home");
            other_panels(home_panel);
            
        }else if(e.getActionCommand()=="Add New Student"){
            home_button.setBackground(left_side_color);
            add_student_button.setBackground(right_side_color);
            create_section_button.setBackground(left_side_color);
            allocate_course_button.setBackground(left_side_color);
            setHeadingText("Add New Student");
            other_panels(registration_panel);
            
        }else if(e.getActionCommand()=="Create Section"){
            home_button.setBackground(left_side_color);
            add_student_button.setBackground(left_side_color);
            create_section_button.setBackground(right_side_color);
            allocate_course_button.setBackground(left_side_color);
            setHeadingText("Create Section");
            other_panels(section_panel);
            
        }else if(e.getActionCommand()=="Allocate Courses"){
            home_button.setBackground(left_side_color);
            add_student_button.setBackground(left_side_color);
            create_section_button.setBackground(left_side_color);
            allocate_course_button.setBackground(right_side_color);
            setHeadingText("Allocate Courses");
            other_panels(allocation);
        }else if(e.getActionCommand() == "Logout"){
            this.dispose();
            new Login_Page();
        }
    }

}
