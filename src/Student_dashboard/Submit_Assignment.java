package Student_dashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql_connector.sql_connector;
public class Submit_Assignment extends JPanel implements ActionListener,WindowListener{
    JLabel assignment_name,assignment_no,submission_date,total_marks;
    JLabel description;
    JComboBox course_name, ass_no;
    JButton button;
    int student_id;
    int assignment_id=0;
    sql_connector sql;
    Color background_color = new Color(124, 193, 235);
    Submit_Assignment(int student_id){
        this.student_id =student_id;
        course_name = new JComboBox();
        button = new JButton("Download File");
        assignment_name =new JLabel();
        assignment_no =new JLabel();
        submission_date =new JLabel();
        total_marks = new JLabel();
        description = new JLabel();
        ass_no = new JComboBox();
        sql = new sql_connector();
        
        this.setBackground(background_color);
        
        course_name.addActionListener(this);
        ass_no.addActionListener(this);
        
        this.setLayout(new GridLayout(10,1,5,5));
        get_Courses();
        if(course_name.getItemCount()!=0){
            course_name.setSelectedIndex(0);
        }
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
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();
        
        JLabel l1 = new JLabel("Course Name");
        JLabel l2 = new JLabel("Assignment No");
        JLabel l3 = new JLabel("Submission Date: ");
        JLabel l4 = new JLabel("Total Marks");
        JLabel l5 = new JLabel("Assignment File");
        JLabel l6 = new JLabel("Assignment File");
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        p6.setLayout(new FlowLayout(FlowLayout.LEFT));
        p7.setLayout(new FlowLayout(FlowLayout.LEFT));
        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
        p9.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        p1.setBackground(null);
        p2.setBackground(null);
        p3.setBackground(null);
        p4.setBackground(null);
        p5.setBackground(null);
        p6.setBackground(null);
        p7.setBackground(null);
        p8.setBackground(null);
        p9.setBackground(null);
        
        
        Dimension d = new Dimension(300,30);
        
        course_name.setPreferredSize(d);
        ass_no.setPreferredSize(d);
        
        p1.add(l1);
        p1.add(course_name);
        
        p2.add(l2);
        p2.add(ass_no);
       
        p3.add(assignment_name);
        assignment_name.setFont(new Font("TimesRoman",Font.BOLD,20));
        
        
        p4.add(description);
        
        p5.add(l3);
        p5.add(submission_date);
        
        p6.add(l4);
        p6.add(total_marks);
        
        p7.add(l5);
        p7.add(button);
        button.setBackground(background_color);
        button.setBorder(null);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
    }
    
    void get_Courses(){
        try {
            String st=Integer.toString(student_id);
            String query="Select C.course_name from Enrollment as E\n" +
                    "inner join Course_Section as CS on E.course_section_id=CS.course_section_id\n" +
                    "inner join Section as S on CS.section_id = S.section_id\n" +
                    "inner join Course as C on CS.course_id=C.course_id\n" +
                    "where E.student_id="+st+"";
            ResultSet rs = sql.execute_Query_for_get(query);
            String a[]=new String[0];
            while(rs.next()){
                a=Regrow_string(a,rs.getString("course_name"));
            }
            for(String b:a){
                course_name.addItem(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Submit_Assignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void get_assignment_no(){
        try {
            String st=Integer.toString(student_id);
            String course= String.valueOf(course_name.getSelectedItem());
            ass_no.removeAllItems();
            
            String query="select A.assignment_no from Assignments as A\n" +
                    "inner join Course_Allocation as CA on A.instractor_id = CA.instractor_id\n" +
                    "inner join Course_Section as CS on CA.course_section_id = CS.course_section_id\n" +
                    "inner join Section as S on S.section_id = CS.section_id\n" +
                    "inner join Course as C on CS.course_id = C.course_id\n" +
                    "inner join Enrollment as E on E.course_section_id = CS.course_section_id\n" +
                    "where C.course_name='"+course+"' and E.student_id="+st+"";
            ResultSet rs = sql.execute_Query_for_get(query);
            String a[]=new String[0];
            while(rs.next()){
                a=Regrow_string(a,rs.getString("assignment_no"));
            }
            for(String b:a){
                ass_no.addItem(b);
            }
        } catch (SQLException ex) {
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
    void get_assignment_detail(){
        try {
            String st=Integer.toString(student_id);
            String course= String.valueOf(course_name.getSelectedItem());
            String assno= String.valueOf(ass_no.getSelectedItem());
            String query="select assignment_name,assignment_id,submission_date,total_marks,description from get_assignment_details('"+course+"', "+st+", "+assno+")";
            ResultSet rs = sql.execute_Query_for_get(query);
            String name = null,des = null,marks = null, date=null;
            
            while(rs.next()){
                name = (rs.getString("assignment_name"));
                assignment_id = rs.getInt("assignment_id");
                des = (rs.getString("description"));
                marks = (rs.getString("total_marks"));
                date = rs.getDate("submission_date").toString();
            }
            assignment_name.setText(name);
            description.setText(des);
            total_marks.setText(marks);
            submission_date.setText(date);
        } catch (SQLException ex) {
            Logger.getLogger(Submit_Assignment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    void get_file() throws IOException{
        try {
            String st=Integer.toString(student_id);
            String course= String.valueOf(course_name.getSelectedItem());
            String assno= String.valueOf(ass_no.getSelectedItem());
            String query="select assignemnt_file from get_assignment_details('"+course+"', "+st+", "+assno+")";
            ResultSet rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                byte[] bytes = rs.getBytes("assignemnt_file");
                try (FileOutputStream fos = new FileOutputStream("D:\\temp_pics\\"+assignment_name.getText()+".pdf")) {
                    fos.write(bytes);
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(Submit_Assignment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Submit_Assignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==course_name){
            get_assignment_no();
            if(ass_no.getItemCount()!=0){
                ass_no.setSelectedIndex(0);
            }
        }
        if(e.getSource()==ass_no){
            get_assignment_detail();
        }
        if(e.getSource()==button){
            if(course_name.getItemCount()!=0 && ass_no.getItemCount()!=0){
                try {
                    get_file();
                } catch (IOException ex) {
                    Logger.getLogger(Submit_Assignment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
