
package Admin_dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql_connector.sql_connector;


public class allocate_courses_panel extends JPanel implements ActionListener {
    Color background_color;
    JComboBox semester, teacher,section,course;
    JButton button;
    sql_connector sql;
    int admin_id;
    int department_id;
    allocate_courses_panel(int admin_id){
        this.admin_id = admin_id;
        semester = new JComboBox();
        teacher = new JComboBox();
        section = new JComboBox();
        course = new JComboBox();
        button  = new JButton("Save");
        sql = new sql_connector();
        department_id=0;
        background_color = new Color(124, 193, 235);
        this.setLayout(new GridLayout(7,1));
        setBackground(background_color);
       get_no_of_semester();
        components();
       get_Teachers();
    }
    void components(){
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        
        JLabel l1 = new JLabel("Teacher Name: ");
        JLabel l2 = new JLabel("Semester No: ");
        JLabel l3 = new JLabel("Course: ");
        JLabel l4 = new JLabel("Section: ");
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        
        p1.setBackground(null);
        p2.setBackground(null);
        p3.setBackground(null);
        p4.setBackground(null);
        p5.setBackground(null);
        
        
        Dimension d = new Dimension(300,35);
        this.teacher.setPreferredSize(d);
        this.semester.setPreferredSize(d);
        this.course.setPreferredSize(d);
        this.section.setPreferredSize(d);
        
        semester.addActionListener(this);
        course.addActionListener(this);
        semester.setSelectedIndex(0);
        course.setSelectedIndex(0);
        button.addActionListener(this);
        p1.add(l1);
        p1.add(this.teacher);
        p2.add(l2);
        p2.add(this.semester);
        p3.add(l3);
        p3.add(this.course);
        p4.add(l4);
        p4.add(this.section);
        p5.add(button);
        
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
    }
     void get_no_of_semester(){
        String query="select D.no_of_semesters,Department.department_id from Degree_Offers as D\n" +
        "inner join Department on Department.degree_id=D.degree_id\n" +
        "where Department.admin_id="+Integer.toString(admin_id)+";";
        ResultSet rs = sql.execute_Query_for_get(query);
        int sem_no=1;
        department_id = 0;
        try {
            while(rs.next()){
                sem_no= rs.getInt("no_of_semesters");
                department_id = rs.getInt("department_id");
            }
        } catch (SQLException ex) {}
        for(int i=1;i<=sem_no;i++){
            semester.addItem(i);
        }
    }
    void get_Teachers(){
        try {
            teacher.removeAllItems();
            String query = "select T.first_name+' '+T.last_name as 'teacher_name', D.department_id from Teacher as T\n" +
                    "inner join Department as D on T.department_id = D.department_id\n" +
                    "where D.admin_id="+admin_id+";";
            ResultSet rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                teacher.addItem(rs.getString("teacher_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(allocate_courses_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void get_courses(){
        try {
            course.removeAllItems();
            String query = "select course_name from Course\n" +
                    "where semester_no ="+semester.getSelectedItem()+" and department_id = "+Integer.toString(department_id)+"";
            ResultSet rs = sql.execute_Query_for_get(query);
            String []str= new String[0];
            while(rs.next()){
                str = this.Regrow_string(str,rs.getString("course_name"));
            }
            for(var st:str){
                course.addItem(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(allocate_courses_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void get_section(){
        try {
            section.removeAllItems();
            String query="select section_name from get_sections("+semester.getSelectedItem()+",'"+course.getSelectedItem()+"',"+admin_id+")";
            ResultSet rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                section.addItem(rs.getString("section_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(allocate_courses_panel.class.getName()).log(Level.SEVERE, null, ex);
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
        if(e.getSource()==semester){
            this.get_courses();
        }
        if(e.getSource()==course){
            this.get_section();
        }
        if(e.getSource()==button){
            if(this.teacher.getItemCount()!=0 && this.semester.getItemCount()!=0 && this.course.getItemCount()!=0 && this.section.getItemCount()!=0 ){
                String query="exec store_course_allocation '"+course.getSelectedItem()+"', '"+section.getSelectedItem()+"', '"+teacher.getSelectedItem()+"'";
                int a= sql.execute_Query_for_set(query);
                if(a==1){
                    JFrame f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Successfully Allocate","Alert",JOptionPane.INFORMATION_MESSAGE);
    
                }
            }else{
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Invalid Information","Alert",JOptionPane.ERROR_MESSAGE);
    
            }
        }
    }
}
