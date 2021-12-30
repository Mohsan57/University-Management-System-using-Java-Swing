package Student_dashboard;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sql_connector.sql_connector;

public class Enrollment_panel extends JPanel implements ActionListener{
    JComboBox semester_no,course_name,section;
    JButton button;
    int student_id;
    sql_connector sql;
    Color  background_color = new Color(124, 193, 235);
    Enrollment_panel(int student_id){
        this.student_id =student_id;
        semester_no= new JComboBox();
        course_name = new JComboBox();
        section = new JComboBox();
        button = new JButton("Enroll");
        sql = new sql_connector();
        
        this.setBackground(background_color);
        this.setLayout(new GridLayout(7,1,5,5));
        
        get_semester_no();
        components();
    }
    void components(){
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JLabel l1 = new JLabel("Semester No");
        JLabel l2 = new JLabel("Course Name");
        JLabel l3 = new JLabel("Section");
        JLabel l4 = new JLabel();
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.setLayout(new FlowLayout(FlowLayout.CENTER));
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        p6.setLayout(new FlowLayout(FlowLayout.LEFT));
        p7.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        p1.setBackground(null);
        p2.setBackground(null);
        p3.setBackground(null);
        p4.setBackground(null);
        p5.setBackground(null);
        p6.setBackground(null);
        p7.setBackground(null);
        
        Dimension d = new Dimension(300,30);
        semester_no.setPreferredSize(d);
        course_name.setPreferredSize(d);
        section.setPreferredSize(d);
        
        semester_no.addActionListener(this);
        course_name.addActionListener(this);
        button.addActionListener(this);
        p1.add(l1);
        p1.add(semester_no);
        
        
        p2.add(l2);
        p2.add(course_name);
        
        p3.add(l3);
        p3.add(section);
        
        p4.add(button);
        
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
    }
    
    void get_semester_no(){
        try {
            String query="select DO.no_of_semesters from Student as S\n" +
                    "inner join Department as D on S.department_id=D.department_id\n" +
                    "inner join Degree_Offers as DO on DO.degree_id = D.degree_id\n" +
                    "where S.student_id = "+student_id+"";
            
            ResultSet rs = sql.execute_Query_for_get(query);
            int semester=0;
            while(rs.next()){
                semester=rs.getInt("no_of_semesters");
            }
            for(int i=1;i<=semester;i++){
                semester_no.addItem(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enrollment_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void get_Courses(){
        course_name.removeAllItems();
        try {
            String query="select C.course_name from Student as S\n" +
                    "inner join Department as D on S.department_id=D.department_id\n" +
                    "inner join Degree_Offers as DO on DO.degree_id = D.degree_id\n" +
                    "inner join Course as C on C.department_id=D.department_id\n" +
                    "where S.student_id = "+this.student_id+" and C.semester_no = "+this.semester_no.getSelectedItem()+"";
            
            ResultSet rs = sql.execute_Query_for_get(query);
            String c[]=new String[0]; 
            while(rs.next()){
                c=Regrow_string(c,rs.getString("course_name"));
            }
            for(int i=0;i<c.length;i++){
                course_name.addItem(c[i]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enrollment_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void get_section(){
        try {
            section.removeAllItems();
            String query="select SE.section_name from Student as S\n" +
                    "inner join Department as D on S.department_id=D.department_id\n" +
                    "inner join Degree_Offers as DO on DO.degree_id = D.degree_id\n" +
                    "inner join Course as C on C.department_id=D.department_id\n" +
                    "inner join Course_Section as CS on C.course_id = CS.course_id\n" +
                    "inner join Section as SE on SE.section_id=CS.section_id\n" +
                    "where S.student_id = "+student_id+" and C.semester_no = "+semester_no.getSelectedItem()+" and C.course_name='"+course_name.getSelectedItem()+"'";
            
            ResultSet rs =sql.execute_Query_for_get(query);
            String sec[]=new String[0];
            while(rs.next()){
                sec=Regrow_string(sec,rs.getString("section_name"));
            }
            for(int i=0;i<sec.length;i++){
                section.addItem(sec[i]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enrollment_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Enrollment(){
        String query="exec set_Enrollment "+student_id+",'"+course_name.getSelectedItem()+"','"+section.getSelectedItem()+"'";
        int execute_Query_for_set = sql.execute_Query_for_set(query);
        if(execute_Query_for_set==1){
            JFrame f=new JFrame();
            JOptionPane.showMessageDialog(f,"Successfully Enroll","Alert",JOptionPane.INFORMATION_MESSAGE);
            
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
        if(e.getSource()==semester_no){
            get_Courses();
        }
        if(e.getSource() == course_name){
            get_section();
        }
        if(e.getSource()==button){
            Enrollment();
        }
    }
    
}
