package Hod_dashboard;
import Admin_dashboard.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql_connector.sql_connector;

public class Add_courses_panel extends JPanel implements ActionListener,KeyListener{
    
    JLabel l1,l2,l3,l4;
    Color background_color;
    JButton button;
    JComboBox semester_no,pre_req_courses;
    JTextField course_name,creadit_hour;
    String courses_name[];
    int sem_no,hod_id;
    sql_connector  sql;
    
    Add_courses_panel(int hod_id){
        this.hod_id =hod_id;
        l1= new JLabel("Course Name");
        l2= new JLabel("Creadit Hour");
        l3= new JLabel("Pre Required Courses");
        l4= new JLabel("Semester No");
        button = new JButton("Save");
        creadit_hour = new JTextField();
        semester_no = new JComboBox();
        pre_req_courses = new JComboBox();
        course_name = new JTextField();
        sql =new sql_connector();
        courses_name  = new String[0];
        background_color = new Color(124, 193, 235);
        setBackground(background_color);

        
        get_no_of_semester();
        get_course_name_id();
        this.setLayout(new GridLayout(6,1));
        components();
    }
    void components(){
       JPanel p1 = new JPanel();
       JPanel p2 = new JPanel();
       JPanel p3 = new JPanel();
       JPanel p4 = new JPanel();
       JPanel p5 = new JPanel();
       JPanel p6 = new JPanel();
       
       p1.setBackground(null);
       p2.setBackground(null);
       p3.setBackground(null);
       p4.setBackground(null);
       p5.setBackground(null);
       p6.setBackground(null);
       
       
       
       p1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p1.add(l4);
       p1.add(semester_no);
       semester_no.setPreferredSize(new Dimension(200,30));
      
       
       p2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p2.add(l1);
       p2.add(course_name);
       course_name.setPreferredSize(new Dimension(200,30));
       
       p3.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p3.add(l2);
       p3.add(creadit_hour);
       creadit_hour.setPreferredSize(new Dimension(200,30));
       creadit_hour.addKeyListener(this);
       
       p4.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       
       p5.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p5.add(l3);
       p5.add(pre_req_courses);
       pre_req_courses.setPreferredSize(new Dimension(300,30));
       
       p6.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
       p6.add(button);
       button.setPreferredSize(new Dimension(100,30));
       
       
       this.add(p1);
       this.add(p2);
       this.add(p3);
       
       this.add(p5);
       this.add(p4);
       this.add(p6);
       button.addActionListener(this);
       
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
    int[] Regrow_int(int str[],int value){
        int size=str.length;
        int st[]=new int[size+1];
        for(int i=0;i<size;i++){
            st[i]=str[i];
        }
        st[size]=value;
        return st;
    }
    void get_no_of_semester(){
        String query="select D.no_of_semesters from Degree_Offers as D\n" +
        "inner join Department on Department.degree_id=D.degree_id\n" +
        "where Department.hod_id="+Integer.toString(hod_id)+";";
        ResultSet rs = sql.execute_Query_for_get(query);
        try {
            while(rs.next()){
                sem_no= rs.getInt("no_of_semesters");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_courses_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=1;i<=sem_no;i++){
            semester_no.addItem(i);
        }
    }
    void get_course_name_id(){
        String query2="select C.course_name from Course as C\n" +
        "inner join Department as D on C.department_id=D.department_id\n" +
        "where D.hod_id="+Integer.toString(hod_id)+";";
        ResultSet rs = sql.execute_Query_for_get(query2);
        try {
            while(rs.next()){
                courses_name=this.Regrow_string(courses_name, rs.getString("course_name"));
            }
            pre_req_courses.addItem("NULL");
            for(int i=0;i<courses_name.length;i++){
                this.pre_req_courses.addItem(courses_name[i]);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Add_courses_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    void store_course(String pre_course){
        ResultSet rs = sql.execute_Query_for_get("select department_id from Department\n" +
            "where hod_id="+Integer.toString(hod_id)+";");
        int department_id=0;
        try {
            while(rs.next()){
                department_id = rs.getInt("department_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacher_registration_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query="exec add_courses '"+this.course_name.getText()+"', "+this.creadit_hour.getText()+", '"+pre_course+"',"+this.semester_no.getSelectedItem()+","+Integer.toString(department_id)+";";
        
        int execute_Query_for_set = sql.execute_Query_for_set(query);
        this.pre_req_courses.addItem(this.course_name.getText());
        this.course_name.setText("");
        this.creadit_hour.setText("");
        this.pre_req_courses.setSelectedIndex(0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Save")){
            if(!course_name.getText().isEmpty() && !creadit_hour.getText().isEmpty()){
                String str=new String();
                if(this.pre_req_courses.getSelectedIndex()==0){
                    str = ("NULL");
                }else{
                    str = (courses_name[this.pre_req_courses.getSelectedIndex()-1]);
                }
                this.store_course(str);
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Register Successfully","Alert",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Please Fill Complete Form ","Alert",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
         char c = e.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        e.consume();
      }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    
}
