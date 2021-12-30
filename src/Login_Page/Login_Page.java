
package Login_Page;

import Admin_dashboard.admin_dashboard;
import Hod_dashboard.hod_dashboard;
import Student_dashboard.Student_dashboard;
import Teacher_dashboard.Teacher_dashboard;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import sql_connector.sql_connector;
 
public class Login_Page extends JFrame implements ActionListener, FocusListener {
    
                
    private JButton button;
    private JTextField email;
    private JPasswordField password_field;
    private Color text_before_color,left_side_color,right_side_color;
    private JLabel label, logo,error,temp;
    private JPanel left_side;
    private JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11,panel12;
    private JPanel right_side;
    private JPanel right_center; 
    private sql_connector sql;
    private String name,user_name,password;
    public Login_Page(){
        init_Components();
    }
    private void init_Components() {

        left_side = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        logo = new JLabel();
        panel4 = new JPanel();
        right_side = new JPanel();
        panel5 = new JPanel();
        label = new JLabel();
        right_center = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel10 = new JPanel();
        email = new JTextField();
        panel1 = new JPanel();
        password_field = new JPasswordField();
        panel8 = new JPanel();
        button = new JButton();
        panel9 = new JPanel();
        panel11 = new JPanel();
        panel12 = new JPanel();
        error = new JLabel();
        temp = new JLabel("");
        text_before_color = new Color(70,70,70);
        left_side_color = new Color(19, 141, 216);
        right_side_color = new Color(17, 12, 50);
        sql = new sql_connector();
        setTitle("Sign in");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1, 2));
        getContentPane().add(left_side);
        getContentPane().add(right_side);
        
        panel2.setBackground(null);
        panel3.setBackground(null);
        panel4.setBackground(null);
        panel5.setBackground(null);
        panel6.setBackground(null);
        panel7.setBackground(null);
        
        panel7.setLayout(new GridLayout(2, 1));
        panel10.setBackground(null);
        panel7.add(panel10);
        panel1.setBackground(null);
        panel7.add(panel1);
        panel8.setBackground(null);
        panel9.setBackground(null);
        panel11.setBackground(null);
        panel12.setBackground(null);
        panel8.setLayout(new GridLayout(3,1));
        panel8.add(panel11);
        panel8.add(panel12);
        panel8.add(temp);
        panel11.setLayout(new FlowLayout());
        panel12.setLayout(new FlowLayout());
        panel11.add(error);
        left_side();
        right_side();
        right_center_side();
        labels();
        TextFields();
        button();
        
        setSize(900,600);
        setLocation(200,30);
        setVisible(true);
    }
    void left_side(){
         left_side.setBackground(left_side_color);
        left_side.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        left_side.setLayout(new GridLayout(3, 1));
        left_side.add(panel2);        
        left_side.add(panel3);
        left_side.add(panel4);
    }
    void right_side(){
         right_side.setBackground(right_side_color);
        right_side.setLayout(new BorderLayout());
        
        right_side.add(panel5, BorderLayout.PAGE_START);
        
        right_side.add(right_center, BorderLayout.CENTER);
    }
    void right_center_side(){
        

        right_center.setBackground(null);
        right_center.setLayout(new GridLayout(4, 1));


        right_center.add(panel6);
        
        right_center.add(panel7);

        right_center.add(panel8);


        right_center.add(panel9);


    }
    void labels(){
        
        logo.setIcon(new  ImageIcon(getClass().getResource("/Images/logo 3.png"))); // NOI18N
        panel3.add(logo);
        
        label.setFont(new Font("Calisto MT", 1, 48)); // NOI18N
        label.setForeground(new Color(255, 255, 255));
        label.setText("SIGN IN");
        label.setToolTipText("");
        panel5.add(label);
        
        error.setText("* Invalid Email or Password");
        error.setForeground(Color.red);
        error.setVisible(false);
    }
    void TextFields(){
        email.setText("Email");
        email.setPreferredSize(new Dimension(300, 50));
        panel10.add(email);
        email.addFocusListener(this);
        email.setForeground(text_before_color);
        email.setSelectedTextColor(Color.red);
        
        
        password_field.setText("Password");
        password_field.setPreferredSize(new Dimension(300, 50));
        String pass=new String(password_field.getPassword());
        if(pass.equals("Password")){
            password_field.setEchoChar((char)0);
        }
        password_field.addFocusListener(this);
        password_field.setForeground(text_before_color);
        panel1.add(password_field);
        
    }
    void button(){
        button.setText("sign in");
        button.setPreferredSize(new Dimension(130,30));
        button.setBackground(left_side_color);
        button.setBorder(null);
        button.setForeground(Color.white);
        button.addActionListener(this);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel12.add(button);
        button.setMnemonic(KeyEvent.VK_ENTER);
    }
    
    int admin_authenticator(String mail,String pass) throws SQLException{
        int id=0;
        ResultSet result = sql.execute_Query_for_get("select * from Admin "
                + "where admin_user_name = \'"+mail+"\' and password = \'"+pass+"\'");
        if(result!=null){
            while(result.next()){
                name = result.getString("admin_name");
                user_name = result.getString("admin_user_name");
                password = result.getString("password");
                id = result.getInt("admin_id");
            }
        }else{
            id=0;
        }
        return id;
    }
    int hod_authenticator(String mail,String pass) throws SQLException{
        int id=0;
        ResultSet result = sql.execute_Query_for_get("select * from HOD "
                + "where hod_user_name = \'"+mail+"\' and password = \'"+pass+"\'");
        if(result!=null){
            while(result.next()){
                name = result.getString("hod_name");
                user_name = result.getString("hod_user_name");
                password = result.getString("password");
                id = result.getInt("hod_id");
            }
        }else{
            id=0;
        }
        return id;
    }
    int teacher_authenticator(String mail,String pass) throws SQLException{
        int id=0;
        ResultSet result = sql.execute_Query_for_get("select * from Teachers_Accounts "
                + "where teacher_email = \'"+mail+"\' and password = \'"+pass+"\'");
        if(result!=null){
            while(result.next()){
                user_name = result.getString("teacher_email");
                password = result.getString("password");
                id = result.getInt("teacher_id");
            }
        }else{
            id=0;
        }
        return id;
    }
    int student_authenticator(String mail,String pass) throws SQLException{
        int id=0;
        ResultSet result = sql.execute_Query_for_get("select * from Students_Accounts "
                + "where student_email = \'"+mail+"\' and password = \'"+pass+"\'");
        if(result!=null){
            while(result.next()){
                user_name = result.getString("student_email");
                password = result.getString("password");
                id = result.getInt("student_id");
            }
        }else{
            id=0;
        }
        return id;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if("sign in".equals(e.getActionCommand())){
            String mail = email.getText();
            String pass=new String(password_field.getPassword());
            if(mail.matches("^[0-9]+(@student\\.uol\\.edu\\.pk)$")){
               try {
                    int id = student_authenticator(mail,pass);
                    if(id!=0){
                        this.dispose();
                        error.setVisible(false);
                        sql.finalize();
                        new Student_dashboard(id).setVisible(true);
                    }else{
                        error.setVisible(true);
                    }
                } catch (SQLException ex) {
                    try {
                        sql.finalize();
                    } catch (SQLException ex1) {
                        Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if( mail.matches("^[0-9]+(@teacher\\.uol\\.edu\\.pk)$")){
                try {
                    int id = teacher_authenticator(mail,pass);
                    if(id!=0){
                        this.dispose();
                        error.setVisible(false);
                        sql.finalize();
                        new Teacher_dashboard(id).setVisible(true);
                    }else{
                        error.setVisible(true);
                    }
                } catch (SQLException ex) {
                    try {
                        sql.finalize();
                    } catch (SQLException ex1) {
                        Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(mail.matches("^[a-zA-Z\\_]+(@admin\\.uol\\.edu\\.pk)$")){
                try {
                    int id = admin_authenticator(mail,pass);
                    if(id!=0){
                        this.dispose();
                        error.setVisible(false);
                        sql.finalize();
                        new admin_dashboard(id).setVisible(true);
                    }else{
                        error.setVisible(true);
                    }
                } catch (SQLException ex) {
                    try {
                        sql.finalize();
                    } catch (SQLException ex1) {
                        Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(mail.matches("^[a-zA-Z\\_]+(@hod\\.uol\\.edu\\.pk)$")){
                try {
                    int id = hod_authenticator(mail,pass);
                    if(id!=0){
                        this.dispose();
                        error.setVisible(false);
                        sql.finalize();
                        new hod_dashboard(id).setVisible(true);
                    }else{
                        error.setVisible(true);
                    }
                } catch (SQLException ex) {
                    try {
                        sql.finalize();
                    } catch (SQLException ex1) {
                        Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                error.setVisible(true);
            }
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        
        if(e.getSource()==email){
            if(email.getText().equals("Email")){
                email.setForeground(Color.white);
                email.setBackground(right_side_color);
                Border border = BorderFactory.createTitledBorder(null,"Email",TitledBorder.LEFT,TitledBorder.TOP,null,Color.white);
                email.setBorder(border);
                email.setText(null);
                email.setCaretColor(Color.white);
            }
        }
         if(e.getSource()==password_field){
             String pass=new String(password_field.getPassword());
            if(pass.equals("Password")){
                password_field.setForeground(Color.white);
                password_field.setBackground(right_side_color);
                Border border = BorderFactory.createTitledBorder(null,"Password",TitledBorder.LEFT,TitledBorder.TOP,null,Color.white);
                password_field.setBorder(border);
                password_field.setEchoChar('*');
                password_field.setText(null);
                password_field.setCaretColor(Color.white);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    if(e.getSource()==email){
            if(email.getText().equals("")){
                email.setForeground(text_before_color);
                email.setBorder(null);
                email.setText("Email");
                email.setBackground(Color.white);
            }
        }
    if(e.getSource()==password_field){
         String pass=new String(password_field.getPassword());
            if(pass.equals("")){
                password_field.setForeground(text_before_color);
                password_field.setBorder(null);
                password_field.setText("Password");
                password_field.setBackground(Color.white);
                password_field.setEchoChar((char)0);
            }
        }
    
    }
    
}       
