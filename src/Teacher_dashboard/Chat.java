package Teacher_dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sql_connector.sql_connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Chat extends JPanel implements ActionListener{
    JComboBox student;
    JTextArea area;
    JTextField t1;
    JButton button;
    int teacher_id;
    sql_connector sql;
     Color background = new Color(124, 193, 235);
     
    Chat(int teacher_id){
        this.teacher_id = teacher_id;
        area = new JTextArea();
        t1 = new JTextField();
        button = new JButton("SEND");
        student = new JComboBox();
        sql = new sql_connector();
        this.setLayout(new BorderLayout(10,10));
        get_student();
        Components();
    }
    void Components(){
        JPanel p1 =new JPanel();
        JPanel p2 =new JPanel();
        JPanel p3 =new JPanel();
        
        this.setBackground(background);
        p1.setBackground(null);
        p2.setBackground(null);
        p3.setBackground(null);
        
        
        JScrollPane scroll = new JScrollPane (area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        p1.setLayout(new GridLayout(1,1));
        p1.add(scroll);
        
        t1.setPreferredSize(new Dimension(700,50));
        t1.setFont(new Font("NewRoman",Font.BOLD,14));
        area.setFont(new Font("Courier",Font.PLAIN,12));
        button.setFont(new Font("NewRoman",Font.BOLD,14));
        student.setPreferredSize(new Dimension(230,30));
        button.setPreferredSize(new Dimension(120,50));
        
        area.setEditable(false);
        
        p2.setLayout(new BorderLayout(5,5));
        p2.add(t1,BorderLayout.CENTER);
        p2.add(button,BorderLayout.EAST);
        button.addActionListener(this);
        student.addActionListener(this);
        student.setSelectedIndex(0);
        
        JLabel l1 =new JLabel("Select Student");
        p3.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        p3.add(l1);
        p3.add(student);
        
        this.add(p1,BorderLayout.CENTER);
        this.add(p2,BorderLayout.PAGE_END);
        this.add(p3,BorderLayout.PAGE_START);
    }
    
    void get_student() {
        try {
            String query="select student_id from Student;";
            ResultSet rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                student.addItem(rs.getInt("student_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Get_MSG(){
        try {
            String query="select * from Get_Chat("+teacher_id+","+student.getSelectedItem()+")";
            ResultSet rs =sql.execute_Query_for_get(query);
            
            String name[]=new String[0];
            String msg[]=new String[0];
            String sender[]=new String[0];
            String student_id[]=new String[0];
            while(rs.next()){
                name = Regrow_string(name,rs.getString("name"));
                msg = Regrow_string(msg,rs.getString("MSG"));
                student_id = Regrow_string(student_id,rs.getString("student_id"));
                sender = Regrow_string(sender,rs.getString("sender"));
            }
            for(int i=0;i<name.length;i++){
                if(sender[i].equals("T")){
                    area.append("You:  "+msg[i]+"\n");
                }if(sender[i].equals("S")){
                    area.append("\n"+student_id[i]+":  "+msg[i]+"\n\n");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Send_Msg(){
        String query="exec Add_Chat "+this.teacher_id+","+student.getSelectedItem()+",'"+t1.getText()+"','T'";
        int execute_Query_for_set = sql.execute_Query_for_set(query);
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
        if(e.getActionCommand().equals("SEND")){
            t1.setText(t1.getText().trim());
            if(!t1.getText().isEmpty()){
                Send_Msg();
                area.setText("");
                this.Get_MSG();
                t1.setText("");
            }
        }
        if(e.getSource()==student){
           area.setText("");
           this.Get_MSG();
        }
    }
    
}
