package Student_dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql_connector.sql_connector;

public class Chat extends JPanel implements ActionListener{
   JComboBox teacher;
    JTextArea area;
    JTextField t1;
    JButton button;
    int student_id;
    sql_connector sql;
    Color background = new Color(124, 193, 235);
    
    Chat(int student_id){
        this.student_id = student_id;
        area = new JTextArea();
        t1 = new JTextField();
        button = new JButton("SEND");
        teacher = new JComboBox();
        sql = new sql_connector();
        this.setLayout(new BorderLayout(10,10));
        get_teacher();
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
        
        JScrollPane scroll = new JScrollPane (area, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        p1.setLayout(new GridLayout(1,1));
        p1.add(scroll);
        
        t1.setPreferredSize(new Dimension(700,50));
        t1.setFont(new Font("NewRoman",Font.BOLD,14));
        area.setFont(new Font("Courier",Font.PLAIN,12));
        button.setFont(new Font("NewRoman",Font.BOLD,14));
        teacher.setPreferredSize(new Dimension(230,30));
        button.setPreferredSize(new Dimension(120,50));
        
        area.setEditable(false);
        
        p2.setLayout(new BorderLayout(5,5));
        p2.add(t1,BorderLayout.CENTER);
        p2.add(button,BorderLayout.EAST);
        button.addActionListener(this);
        teacher.addActionListener(this);
        teacher.setSelectedIndex(0);
        
        JLabel l1 =new JLabel("Select Teacher");
        p3.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        p3.add(l1);
        p3.add(teacher);
        
        this.add(p1,BorderLayout.CENTER);
        this.add(p2,BorderLayout.PAGE_END);
        this.add(p3,BorderLayout.PAGE_START);
    }
    
    void get_teacher() {
        try {
            String query="select first_name+' '+last_name as 'name' from Teacher";
            ResultSet rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                teacher.addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    void Get_MSG(){
        try {
            String q="select teacher_id from Teacher\n" +
            "where first_name+' '+last_name = '"+teacher.getSelectedItem()+"'";
            ResultSet s=sql.execute_Query_for_get(q);
            int teacher_id=0;
            while(s.next()){
                teacher_id = s.getInt("teacher_id");
            }
            
            String query="select * from Get_Chat("+teacher_id+","+student_id+")";
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
                    area.append("\n"+name[i]+":  "+msg[i]+"\n\n");
                }if(sender[i].equals("S")){
                    area.append("You:  "+msg[i]+"\n");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    void Send_Msg(){
       try {
           String ab=String.valueOf(teacher.getSelectedItem());
           String q="select teacher_id from Teacher\n" +
                   "where first_name+' '+last_name = '"+ab+"'";
           ResultSet s=sql.execute_Query_for_get(q);
           int teacher_id=0;
           while(s.next()){
               teacher_id = s.getInt("teacher_id");
           }
           String query="exec Add_Chat "+teacher_id+","+this.student_id+",'"+t1.getText()+"','S'";
           int execute_Query_for_set = sql.execute_Query_for_set(query);
       } catch (SQLException ex) {
           Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
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
        if(e.getActionCommand().equals("SEND")){
            t1.setText(t1.getText().trim());
            if(!t1.getText().isEmpty()){
                Send_Msg();
                area.setText("");
                this.Get_MSG();
                t1.setText("");
            }
        }
        if(e.getSource()==teacher){
           area.setText("");
           this.Get_MSG();
        }
    }
}
