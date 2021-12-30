package Admin_dashboard;
import Hod_dashboard.Add_courses_panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import sql_connector.sql_connector;


public class create_section_panel extends JPanel implements ActionListener{
    JTextField t1;
    JComboBox sections_box;
    JComboBox semester_no;
    JComboBox semester_no2;
    JLabel label1, label2;
    JComboBox courses;
    JButton add,update,search;
    Color background_color;
    sql_connector sql;
    JPanel p2;
    int admin_id;
    create_section_panel(int admin_id){
        this.admin_id=admin_id;
        t1 = new JTextField();
        sections_box = new JComboBox();
        semester_no = new JComboBox();
        semester_no2 = new JComboBox();
        add = new JButton("Add");
        update = new JButton("Update");
        search = new JButton("Search");
        label1 = new JLabel("Create Section");
        label2 = new JLabel("Create Section");
        background_color = new Color(124, 193, 235);
        courses =new JComboBox();
        sql = new sql_connector();
        p2=new JPanel();
        add.addActionListener(this);
        update.addActionListener(this);
        search.addActionListener(this);
        semester_no2.addActionListener(this);
        setBackground(background_color);
        setLayout(new BorderLayout());
        get_no_of_semester();
        create_section_panel();
        set_courses_panel();
    }
    void create_section_panel(){
        JPanel panel =new JPanel();
        JPanel panel2 =new JPanel();
        JPanel panel3 =new JPanel();
        JPanel panel4 =new JPanel();
        JLabel l1=new JLabel("Select Semester");
        JLabel l2=new JLabel("Choose Section Name");
        
        panel.setLayout(new GridLayout(8,1,10,10));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        t1.setPreferredSize(new Dimension(200,40));
        add.setPreferredSize(new Dimension(100,40));
        
        panel2.add(l2);
        panel2.add(t1);
        panel3.add(add);
        panel4.add(l1);
        panel4.add(semester_no);
        semester_no.setPreferredSize(new Dimension(200,30));
        t1.setFont(new Font("TimeRoman",Font.BOLD,14));
        l1.setFont(new Font("TimeRoman",Font.BOLD,14));
        l2.setFont(new Font("TimeRoman",Font.BOLD,14));
        
        panel.setBorder(new TitledBorder(null, "Create Section", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.add(panel4);
        panel.add(panel2);
        panel.add(panel3);
        
        this.add(panel,BorderLayout.WEST);
        panel.setBackground(null);
        panel2.setBackground(null);
        panel3.setBackground(null);
        panel4.setBackground(null);
    }
    void set_courses_panel(){
        JPanel panel =new JPanel();
        JPanel panel1 =new JPanel();
        JPanel panel2 =new JPanel();
        JPanel panel3 =new JPanel();
        JPanel panel4 =new JPanel();
        JPanel panel5 =new JPanel();
        JLabel l1=new JLabel("Select Semester");
        JLabel l2=new JLabel("Select Section");
        
        sections_box.setPreferredSize(new Dimension(250,30));
        semester_no2.setPreferredSize(new Dimension(250,30));
        
        panel1.setLayout(new GridLayout(3,1));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel5.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        
        panel.setBackground(null);
        panel1.setBackground(null);
        panel2.setBackground(null);
        panel3.setBackground(null);
        panel4.setBackground(null);
        panel5.setBackground(null);
           
        panel2.add(l1);
        panel2.add(semester_no2);
        
        panel3.add(l2);
        panel3.add(sections_box);
        
        panel5.add(search);
        panel1.add(panel2);
        panel1.add(panel3);
        panel1.add(panel5);
        
        panel.setLayout(new BorderLayout());
        panel.setBorder(new TitledBorder(null, "Add Courses to Sections", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
       
        this.add(panel,BorderLayout.CENTER);
        
        panel.setLayout(new BorderLayout());
        panel.add(panel1,BorderLayout.NORTH);
        panel.add(panel4,BorderLayout.CENTER);
        
        JLabel l3 = new JLabel("Select Courses");
        JPanel p1=new JPanel();
        JPanel p3=new JPanel();
        p1.add(l3);
        
        panel4.setLayout(new BorderLayout());
        panel4.add(p1,BorderLayout.NORTH);
        panel4.add(p2,BorderLayout.CENTER);
        panel4.add(p3,BorderLayout.SOUTH);
        
        
        p2.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        p1.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        courses.setPreferredSize(new Dimension(300,40));
        p2.add(courses);
        p3.setLayout(new FlowLayout());
        p3.add(update);
        
        
        panel4.setBackground(null);
        p1.setBackground(null);
        p2.setBackground(null);
        p3.setBackground(null);
    }
    void get_no_of_semester(){
        String query="select D.no_of_semesters from Degree_Offers as D\n" +
        "inner join Department on Department.degree_id=D.degree_id\n" +
        "where Department.admin_id="+Integer.toString(admin_id)+";";
        ResultSet rs = sql.execute_Query_for_get(query);
        int sem_no=1;
        try {
            while(rs.next()){
                sem_no= rs.getInt("no_of_semesters");
            }
        } catch (SQLException ex) {}
        for(int i=1;i<=sem_no;i++){
            semester_no.addItem(i);
            semester_no2.addItem(i);
        }
    }
    void create_section(){
        int department_id=0;
        String ad=Integer.toString(admin_id);
        String q="select Department.department_id from Department where admin_id="+ad+"";
        ResultSet department = sql.execute_Query_for_get(q);
        try {
            while(department.next()){
                department_id = department.getInt("department_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(create_section_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query="insert into Section(semester, section_name,Department_id) values ("+semester_no.getSelectedItem()+" , '"+t1.getText()+"',"+department_id+");";
        sql.execute_Query_for_set(query);
        
        t1.setText("");
        semester_no.setSelectedIndex(0);
    }
    void get_section(){
        sections_box.removeAllItems();
        try {
            String ad = Integer.toString(admin_id);
            String sem = String.valueOf(semester_no2.getSelectedItem());
            String query = "select S.section_name from Section as S\n" +
            "inner join Department as D on S.Department_id = D.department_id\n" +
            "inner join Admin as A on A.admin_id = D.admin_id\n" +
            "where A.admin_id = "+ad+" and S.semester = "+sem+"";
            ResultSet rs = sql.execute_Query_for_get(query);
            String st[]=new String[0];
            while(rs.next()){
                st = Regrow_string(st,rs.getString("section_name"));
            }
            for (String st1 : st) {
                sections_box.addItem(st1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(create_section_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void get_courses(){
        try {
            String ad = Integer.toString(admin_id);
            String sem = String.valueOf(semester_no2.getSelectedItem());
            courses.removeAllItems();
            String query="select C.course_name from Course as C\n" +
            "inner join Department as D on D.department_id=C.department_id\n" +
            "inner join Admin as A on A.admin_id=D.department_id\n" +
            "where A.admin_id="+ad+" and C.semester_no="+sem+"";
            ResultSet rs=sql.execute_Query_for_get(query);
            while(rs.next()){
                courses.addItem(rs.getString("course_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(create_section_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void set_courses_to_sections(){
        int department_id=0;
        String ad= Integer.toString(admin_id);
        String q="select Department.department_id from Department where admin_id="+ad+"";
        ResultSet department = sql.execute_Query_for_get(q);
        try {
            while(department.next()){
                department_id = department.getInt("department_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(create_section_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = sql.execute_Query_for_get("select Section.section_id from Section\n" +
                    "where section_name='"+sections_box.getSelectedItem()+"' and semester="+semester_no2.getSelectedItem()+"and Department_id="+department_id+";");
            int sec_id=0;
            while(rs.next()){
                sec_id  = rs.getInt("section_id");
            }
            int course_id=0;
            rs =null;
            rs = sql.execute_Query_for_get("select course_id from Course\n" +
                            "where course_name = '"+courses.getSelectedItem()+"'");
            while(rs.next()){
                course_id = rs.getInt("course_id");
            }
            
            int a =sql.execute_Query_for_set("insert into Course_Section(course_id, section_id) values ("+Integer.toString(course_id)+", "+Integer.toString(sec_id)+");");
            if(a==1){
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Successfully Allocate","Alert",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    String[] Regrow_string(String str[],String value){
        int size=str.length;
        String st[]=new String[size+1];
        for(int i=0;i<size;i++){
            st[i]=str[i];
        }
        st[size]=value;
        return st;
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add")){
            if(!t1.getText().isEmpty()){
                this.create_section();
            }
        }
        if(e.getActionCommand().equals("Search")){
            get_courses();
        }
        if(e.getSource()==semester_no2){
            get_section();
        }if(e.getActionCommand().equals("Update")){
            set_courses_to_sections();
        }
    }
}
