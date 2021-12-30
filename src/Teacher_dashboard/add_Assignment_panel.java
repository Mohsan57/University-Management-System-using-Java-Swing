package Teacher_dashboard;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import sql_connector.sql_connector;

public class add_Assignment_panel extends JPanel implements ActionListener{
    
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JFileChooser j1;
    JDateChooser Submission_date = new JDateChooser();
    Color background_color;
    JButton button,button2;
    JComboBox course_name;
    JTextField assignment_title;
    JTextField assignment_number;
    JTextField assignment_marks;
    JTextArea description;
    sql_connector sql;
    JFileChooser file;
    int teacher_id;
    add_Assignment_panel(int teacher_id){
        this.teacher_id = teacher_id;
        l1= new JLabel("Assignment Title");
        l2= new JLabel("Assignment Number");
        l3= new JLabel("Submission File docx/pdf only");
        l4= new JLabel("Description ");
        l5= new JLabel("Submission Date");
        l6= new JLabel("Total Marks ");
        l7= new JLabel("Course Name");
        l8= new JLabel("Total Marks");
        l9= new JLabel("");
        sql = new sql_connector();
        button = new JButton("Save");
        button2 = new JButton("Submit File");
        assignment_number = new JTextField();
        assignment_title = new JTextField();
        course_name = new JComboBox();
        description = new JTextArea();
        assignment_marks = new JTextField();
        j1= new JFileChooser();
       file = new JFileChooser();
        
        
        background_color = new Color(124, 193, 235);
        setBackground(background_color);

        button2.addActionListener(this);
        button.addActionListener(this);
        
        course_name();
        // Layout
        this.setLayout(new GridLayout(6,2));
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
       JPanel p10 = new JPanel();
       
       
       p1.setBackground(null);
       p2.setBackground(null);
       p3.setBackground(null);
       p4.setBackground(null);
       p5.setBackground(null);
       p6.setBackground(null);
       p7.setBackground(null);
       p8.setBackground(null);
       p9.setBackground(null);
       p10.setBackground(null);
       
       p7.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p7.add(l7);
       p7.add(course_name);
       course_name.setPreferredSize(new Dimension(300,30));
       
       p1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p1.add(l4);
       p1.add(description);
       description.setPreferredSize(new Dimension(250,60));
      
       
       p2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p2.add(l1);
       p2.add(assignment_title);
       assignment_title.setPreferredSize(new Dimension(200,30));
       
       p3.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p3.add(l2);
       p3.add(assignment_number);
       assignment_number.setPreferredSize(new Dimension(200,30));
       
       p4.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p4.add(l5);
       p4.add(Submission_date);
       Submission_date.setPreferredSize(new Dimension(200,30));
       
       p5.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p5.add(l3);
       p5.add(button2);
       p5.add(l9);
       button2.setPreferredSize(new Dimension(100,30));
       
       
       p6.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p6.add(button);
       button.setPreferredSize(new Dimension(100,30));
       
      
       
       p8.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
       
       p9.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       p9.add(l8);
       p9.add(assignment_marks);
       
       assignment_marks.setPreferredSize(new Dimension(200,30));
       
       p10.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
       
       
       this.add(p2);
       
       
       this.add(p3);
       
       this.add(p1);
       
       this.add(p4);
       this.add(p5);
       this.add(p7);
       this.add(p9);
       
          add(p8);
       this.add(p10);
       
       this.add(p6);
       
       
    }
    void course_name(){
        try {
            String query="select * from teacher_allocate_courses_and_Sections("+Integer.toString(teacher_id)+")";
            ResultSet rs=sql.execute_Query_for_get(query);
            while(rs.next()){
                course_name.addItem(rs.getString("teacher_allocate_courses"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(add_Assignment_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void add_assignment(){
        try {
            String ab=Integer.toString(teacher_id);
            String ac=String.valueOf( course_name.getSelectedItem());
            String q="select * from get_instractor_id("+ab+",'"+ac+"')";
            ResultSet rs= sql.execute_Query_for_get(q);
            int instractor_id=0;
            while(rs.next()){
                instractor_id = rs.getInt("instractor_id");
            }
            String ext = file.getSelectedFile().getName().substring(file.getSelectedFile().getName().lastIndexOf('.')+1);
            java.util.Date today=new java.util.Date();
            java.sql.Date sqlDate2=new java.sql.Date(today.getTime());
            java.sql.Date sqlDate = new java.sql.Date( this.Submission_date.getDate().getTime());
            String query="insert into Assignments(assignment_name,assignment_no,file_extention,assignemnt_file,description,upload_date,submission_date,total_marks,instractor_id)\n" +
                        "values ('"+this.assignment_title.getText()+"',"+this.assignment_number.getText()+",'"+ext+"',(SELECT * FROM   OPENROWSET(BULK N'"+file.getSelectedFile().getPath()+"', SINGLE_BLOB ) as FILE_DATA),'"+this.description.getText()+"','"+sqlDate2+"','"+sqlDate+"',"+this.assignment_marks.getText()+","+instractor_id+")";
            int a=sql.execute_Query_for_set(query);
            if(a==1){
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Successfully Added","Alert",JOptionPane.INFORMATION_MESSAGE);
                
                assignment_title.setText("");
                assignment_number.setText("");
                assignment_marks.setText("");
                description.setText("");
                
            }
            else{
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Not Added","Alert",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(add_Assignment_panel.class.getName()).log(Level.SEVERE, null, ex);
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
        if(e.getActionCommand().equals("Submit File")){
            file=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            file.setDialogTitle("Select an File");
            file.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
            file.addChoosableFileFilter(filter);
            int returnValue = file.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
			l9.setText(file.getSelectedFile().getName());
                        l9.setForeground(Color.red);
		}
        }
        if(e.getActionCommand().equals("Save")){
            if(course_name.getItemCount()!=0 && !assignment_title.getText().isEmpty() && !assignment_number.getText().isEmpty() && !assignment_marks.getText().isEmpty()){
                add_assignment();
            }else{
                 JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Not Added","Alert",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
}
