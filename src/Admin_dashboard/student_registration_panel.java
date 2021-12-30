/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin_dashboard;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import sql_connector.sql_connector;

public class student_registration_panel extends JPanel implements KeyListener,ActionListener{
    Color background_color;
    JLabel labels[],photo_label;
    JTextField f_name,l_name,cnic,phone_no,father_occ,mother_occ,country,province,city,postal_code,home_address;
    JTextField qualification,college,board_name,roll_no,total_marks,obtain_marks;
    JComboBox grade;
    JPanel basic_info_panel,location_panel,inter_panel,photo_panel;
    JTextField photo_address;
    JPanel bottom,center;
    JButton save_button,clear_button;
    JRadioButton b1,b2;
    JDateChooser date;
    JFileChooser file;
    int admin_id;
    sql_connector sql;
    student_registration_panel(int admin_id){
        this.admin_id = admin_id;
        
        background_color = new Color(124, 193, 235);
        
        setBackground(background_color);
        
        labels = new JLabel[21];
        bottom = new JPanel();
        center = new JPanel();
        basic_info_panel = new JPanel();
        location_panel = new JPanel();
        inter_panel = new JPanel();
        photo_panel = new JPanel();
        save_button = new JButton("Save");
        f_name = new JTextField();
        l_name = new JTextField();
        cnic = new JTextField();
        phone_no = new JTextField();
        father_occ = new JTextField();
        mother_occ = new JTextField();
        country = new JTextField();
        city = new JTextField();
        province = new JTextField();
        postal_code = new JTextField();
        home_address = new JTextField();
        qualification = new JTextField();
        college = new JTextField();
        board_name = new JTextField();
        roll_no = new JTextField();
        total_marks = new JTextField();
        obtain_marks = new JTextField();
        grade = new JComboBox();
        b1 = new JRadioButton();
        b2 = new JRadioButton();
        date= new JDateChooser();
        photo_address = new JTextField();
        photo_label= new JLabel();
        
        sql = new sql_connector();
        
        bottom.setLayout(new FlowLayout());
        bottom.add(save_button);
        save_button.addActionListener(this);
        center.setLayout(new GridLayout(2,2));
        center.add(basic_info_panel);
        center.add(location_panel);
        center.add(inter_panel);
        center.add(photo_panel);
        
        basic_info_panel.setBorder(new TitledBorder(null, "Personal Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
        location_panel.setBorder(new TitledBorder(null, "Location Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
        inter_panel.setBorder(new TitledBorder(null, "Intermediate Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
        photo_panel.setBorder(new TitledBorder(null, "Photo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
        
        basic_info_panel.setBackground(background_color);
        location_panel.setBackground(background_color);
        inter_panel.setBackground(background_color);
        photo_panel.setBackground(background_color);
        bottom.setBackground(background_color);
        
        
        setLayout(new BorderLayout());
        add(bottom,BorderLayout.PAGE_END);
        add(center,BorderLayout.CENTER);
         set_st_labels();
        basic_info();
        location_info();
        Intermediate_info();
        photo();
        this.setVisible(true);
        this.revalidate();
    }
    void set_st_labels(){
        for(int i =0 ;i< 21;i++){
           labels[i]=new JLabel();
        }
        
        labels[0].setText("First Name");
        labels[1].setText("Last Name");
        labels[2].setText("Gender");
        labels[3].setText("Date of Birth");
        labels[4].setText("CNIC Number");
        labels[5].setText("Phone Number");
        labels[6].setText("Father Occupation (optional)");
        labels[7].setText("Mother Occupation (optional)");
        labels[8].setText("Qualification");
        labels[9].setText("College Name");
        labels[10].setText("Board Name");
        labels[11].setText("Roll No");
        labels[12].setText("Total Marks");
        labels[13].setText("Obtain Marks");
        labels[14].setText("Grade");
        labels[15].setText("Country");
        labels[16].setText("province");
        labels[17].setText("City");
        labels[18].setText("Postal Code");
        labels[19].setText("Home Address");
        labels[20].setText("Photo");
        
        
    }
    void basic_info(){
        basic_info_panel.setLayout(new GridLayout(4,2));
        JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
        panel1= new JPanel();
        panel2= new JPanel();
        panel3= new JPanel();
        panel4= new JPanel();
        panel5= new JPanel();
        panel6= new JPanel();
        panel7= new JPanel();
        panel8= new JPanel();
        
        basic_info_panel.add(panel1);
        basic_info_panel.add(panel2);
        basic_info_panel.add(panel3);
        basic_info_panel.add(panel4);
        basic_info_panel.add(panel5);
        basic_info_panel.add(panel6);
        basic_info_panel.add(panel7);
        basic_info_panel.add(panel8);
        
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel8.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        panel1.add(this.labels[0]);
        panel2.add(this.labels[1]);
        panel3.add(this.labels[2]);
        panel4.add(this.labels[3]);
        panel5.add(this.labels[4]);
        panel6.add(this.labels[5]);
        panel7.add(this.labels[6]);
        panel8.add(this.labels[7]);
        
        f_name.setPreferredSize(new Dimension(170,30));
        l_name.setPreferredSize(new Dimension(170,30));
        phone_no.setPreferredSize(new Dimension(170,30));
        cnic.setPreferredSize(new Dimension(170,30));
        father_occ.setPreferredSize(new Dimension(190,30));
        mother_occ.setPreferredSize(new Dimension(190,30));
        
        phone_no.addKeyListener(this);
        cnic.addKeyListener(this);
        
        Date todaydate=new Date();
        
        date.setLocale(Locale.US);
        
        date.setMaxSelectableDate(new Date(todaydate.getYear(),todaydate.getMonth(),todaydate.getDay()));
        
        b1.setText("Male");
        b2.setText("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        b1.setBackground(background_color);
        b2.setBackground(background_color);
        
        panel1.add(this.f_name);
        panel2.add(this.l_name);
        panel3.add(this.b1);
        panel3.add(this.b2);
        panel4.add(this.date);
        panel5.add(this.cnic);
        panel6.add(this.phone_no);
        panel7.add(this.father_occ);
        panel8.add(this.mother_occ);
        
        panel1.setBackground(background_color);
        panel2.setBackground(background_color);
        panel3.setBackground(background_color);
        panel4.setBackground(background_color);
        panel5.setBackground(background_color);
        panel6.setBackground(background_color);
        panel7.setBackground(background_color);
        panel8.setBackground(background_color);
    }
    
    
    void location_info(){
        location_panel.setLayout(new GridLayout(3,2));
        JPanel panel1,panel2,panel3,panel4,panel5;
        panel1= new JPanel();
        panel2= new JPanel();
        panel3= new JPanel();
        panel4= new JPanel();
        panel5= new JPanel();
        
        location_panel.add(panel1);
        location_panel.add(panel2);
        location_panel.add(panel3);
        location_panel.add(panel4);
        location_panel.add(panel5);
        
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        panel1.add(this.labels[15]);
        panel2.add(this.labels[16]);
        panel3.add(this.labels[17]);
        panel4.add(this.labels[18]);
        panel5.add(this.labels[19]);
        
        
        panel1.add(this.country);
        panel2.add(this.province);
        panel3.add(this.city);
        panel4.add(this.postal_code);
        panel5.add(this.home_address);
        
        country.setPreferredSize(new Dimension(170,30));
        province.setPreferredSize(new Dimension(170,30));
        city.setPreferredSize(new Dimension(170,30));
        postal_code.setPreferredSize(new Dimension(170,30));
        home_address.setPreferredSize(new Dimension(270,30));
        
        postal_code.addKeyListener(this);
        date.setPreferredSize(new Dimension(140,30));
        
        
        panel1.setBackground(background_color);
        panel2.setBackground(background_color);
        panel3.setBackground(background_color);
        panel4.setBackground(background_color);
        panel5.setBackground(background_color);
    }
    
    void Intermediate_info(){
        inter_panel.setLayout(new GridLayout(4,2));
        JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
        panel1= new JPanel();
        panel2= new JPanel();
        panel3= new JPanel();
        panel4= new JPanel();
        panel5= new JPanel();
        panel6= new JPanel();
        panel7= new JPanel();
        panel8= new JPanel();
        
        inter_panel.add(panel1);
        inter_panel.add(panel2);
        inter_panel.add(panel3);
        inter_panel.add(panel4);
        inter_panel.add(panel5);
        inter_panel.add(panel6);
        inter_panel.add(panel7);
        inter_panel.add(panel8);
        
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel8.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        panel1.add(this.labels[8]);
        panel2.add(this.labels[9]);
        panel3.add(this.labels[10]);
        panel4.add(this.labels[11]);
        panel5.add(this.labels[12]);
        panel6.add(this.labels[13]);
        panel7.add(this.labels[14]);
        
        panel1.add(this.qualification);
        panel2.add(this.college);
        panel3.add(this.board_name);
        panel4.add(this.roll_no);
        panel5.add(this.total_marks);
        panel6.add(this.obtain_marks);
        panel7.add(this.grade);
        
        roll_no.addKeyListener(this);
        total_marks.addKeyListener(this);
        obtain_marks.addKeyListener(this);
        qualification.setPreferredSize(new Dimension(170,30));
        college.setPreferredSize(new Dimension(170,30));
        board_name.setPreferredSize(new Dimension(170,30));
        roll_no.setPreferredSize(new Dimension(170,30));
        total_marks.setPreferredSize(new Dimension(170,30));
        obtain_marks.setPreferredSize(new Dimension(170,30));
        grade.setPreferredSize(new Dimension(170,30));
        
        grade.addItem("A+");
        grade.addItem("A");
        grade.addItem("B+");
        grade.addItem("B");
        grade.addItem("C+");
        grade.addItem("C");
        grade.addItem("D+");
        grade.addItem("D");
        grade.addItem("E");
        grade.addItem("F");
        
        
        
        panel1.setBackground(background_color);
        panel2.setBackground(background_color);
        panel3.setBackground(background_color);
        panel4.setBackground(background_color);
        panel5.setBackground(background_color);
        panel6.setBackground(background_color);
        panel7.setBackground(background_color);
        panel8.setBackground(background_color);
    }
    
    void photo(){
        photo_panel.setLayout(new BorderLayout());
        JPanel panel1= new JPanel();
        JPanel panel2= new JPanel();
        JPanel panel3= new JPanel();
        JPanel panel4= new JPanel();
        JPanel panel5= new JPanel();
        JButton upload_button = new JButton("Upload");
        photo_panel.add(panel1,BorderLayout.CENTER);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panel3.setBackground(new Color(200,248,255));
        panel3.setPreferredSize(new Dimension(170,170));
        panel1.add(panel3);
        panel3.setLayout(new GridLayout(1,1));
        panel3.add(photo_label);
        
        photo_panel.add(panel2,BorderLayout.PAGE_END);
        photo_address.setEditable(false);
        photo_address.setPreferredSize(new Dimension(200,30));
        panel2.setLayout(new GridLayout(2,1));
        panel2.add(panel4);
        panel2.add(panel5);
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        panel4.add(photo_address);
        panel5.add(upload_button);
        upload_button.addActionListener(this);
       
        
        photo_address.setBackground(background_color);
        panel1.setBackground(background_color);
        panel2.setBackground(background_color);
        panel4.setBackground(background_color);
        panel5.setBackground(background_color);
        
        
        
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Upload")){
            file=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            file.setDialogTitle("Select an image");
            file.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, and JPEG images", "png", "jpg", "jpeg");
            file.addChoosableFileFilter(filter);
            int returnValue = file.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = file.getSelectedFile();
			photo_address.setText(selectedFile.getName());
                        ImageIcon icon=new ImageIcon(selectedFile.getPath());
                        Image image = icon.getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT);
                        ImageIcon newimage = new ImageIcon(image);
                        photo_label.setIcon(newimage);
		}
        }
        else if(e.getActionCommand().equalsIgnoreCase("Save")){
             if(!f_name.getText().isEmpty() && !l_name.getText().isEmpty() && !cnic.getText().isEmpty() && !phone_no.getText().isEmpty() &&
                !country.getText().isEmpty() && !province.getText().isEmpty() && !city.getText().isEmpty() && !postal_code.getText().isEmpty() && !home_address.getText().isEmpty() &&
                !qualification.getText().isEmpty() && !college.getText().isEmpty() && !board_name.getText().isEmpty() && !roll_no.getText().isEmpty() && !total_marks.getText().isEmpty() &&
                     !obtain_marks.getText().isEmpty()){
                 
                 if(!photo_address.getText().isEmpty() && (b1.isSelected() || b2.isSelected()) && date.getDate()!=null){
                        System.out.println("True");
                        JPasswordField password_field = new JPasswordField();
                        JPasswordField con_password_field = new JPasswordField();
                        JPanel pane = new JPanel(new GridBagLayout());
                        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 
                        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
                        new Insets(2, 2, 2, 2), 0, 0);
                        pane.add(new JLabel("Password:"), gbc);

                        gbc.gridy = 1;
                        pane.add(new JLabel("Confirm Password:"), gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 0;
                        gbc.anchor = GridBagConstraints.EAST;
                        pane.add(password_field, gbc);
                        password_field.setPreferredSize(new Dimension(70,30));
                        con_password_field.setPreferredSize(new Dimension(70,30));
                        gbc.gridy = 1;
                        pane.add(con_password_field, gbc);
                        int reply = JOptionPane.showConfirmDialog(null, pane, "Set Password",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (reply == JOptionPane.OK_OPTION) {
                            String password = new String(password_field.getPassword());
                            String con_password = new String(con_password_field.getPassword());
                            if(password.equals(con_password)){
                                try {
                                    store_basic_detail(con_password);
                                } catch (SQLException ex) {
                                    Logger.getLogger(student_registration_panel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    String query2="select max(student_email) as 'student_email' from Students_Accounts";
                                    ResultSet rs = sql.execute_Query_for_get(query2);
                                    String email= new String();
                                    while(rs.next()){
                                        email=rs.getString("student_email");
                                    }
                                    JFrame f=new JFrame();
                                    JOptionPane.showMessageDialog(f,"Email: "+email+" Successfully Registerd","Alert",JOptionPane.WARNING_MESSAGE);
                                    {
                                     qualification.setText("");
                                     college.setText("");
                                     board_name.setText("");
                                     roll_no.setText("");
                                     total_marks.setText("");
                                     obtain_marks.setText("");
                                     f_name.setText("");
                                     l_name.setText("");
                                     cnic.setText("");
                                     phone_no.setText("");
                                     father_occ.setText("");
                                     mother_occ.setText("");
                                     country.setText("");
                                     province.setText("");
                                     city.setText("");
                                     postal_code.setText("");
                                     home_address.setText("");
                                     photo_address.setText("");
                                     photo_label.setIcon(null);
                                     b1.setSelected(false);
                                     b2.setSelected(false);
                                     date.setDate(null);
                                     file.setSelectedFile(null);
                                    }
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(student_registration_panel.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }else{
                                  JFrame f=new JFrame();
                                  JOptionPane.showMessageDialog(f,"Password Not Match","Alert",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                 }
             }
             else{
                  JFrame f=new JFrame();
                  JOptionPane.showMessageDialog(f,"Please Fill Complete Form ","Alert",JOptionPane.ERROR_MESSAGE);
                          
             }
        }
    }
    void store_location(){
        String query="exec store_location '"+this.country.getText()+"',\'"+this.province.getText()+"\',\'"+this.city.getText()+"\',"+this.postal_code.getText()+",\'"+this.home_address.getText()+"\'";
        int execute_Query_for_set = sql.execute_Query_for_set(query);
    }
    void store_education_detail(){
        String query="exec store_inter_education '"+this.qualification.getText()+"','"+this.college.getText()+"','"+this.board_name.getText()+"',"+this.roll_no.getText()+","+this.total_marks.getText()+","+this.obtain_marks.getText()+",'"+this.grade.getSelectedItem().toString()+"'";
        int execute_Query_for_set = sql.execute_Query_for_set(query);
    }
    void store_image() throws FileNotFoundException, IOException{
            String ext = file.getSelectedFile().getName().substring(file.getSelectedFile().getName().lastIndexOf('.')+1);
            String query="INSERT INTO Profile_Pic(photo_name,photo_extension,photo) values" +
                "('"+file.getSelectedFile().getName()+"','"+ext+"',(SELECT * FROM   OPENROWSET(BULK N'"+file.getSelectedFile().getPath()+"', SINGLE_BLOB ) as IMG_DATA));";
        int execute_Query_for_set = sql.execute_Query_for_set(query);
    }
    void store_basic_detail(String pass) throws SQLException{
        store_location();
        store_education_detail();
        try {
            store_image();
        } catch (IOException ex) {
            Logger.getLogger(student_registration_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         java.util.Date today=new java.util.Date();
         java.sql.Date sqlDate2=new java.sql.Date(today.getTime());
         
         java.sql.Date sqlDate = new java.sql.Date( date.getDate().getTime());
         
        ResultSet rs = sql.execute_Query_for_get("select department_id from Department\n" +
            "where admin_id="+Integer.toString(admin_id)+";");
        int department_id=0;
        while(rs.next()){
            department_id  = rs.getInt("department_id");
        }
        rs=null;
         String str = new String();
         if(b1.isSelected()){
             str = "M";
         }else if(b2.isSelected()){
             str ="F";
         }
         if(this.father_occ.getText()==""){
             this.father_occ.setText("NULL");
         }
         if(this.mother_occ.getText()==""){
             this.mother_occ.setText("NULL");
         }
         String query="exec store_student_info '"+this.f_name.getText()+"','"+this.l_name.getText()+"','"+str+"','"+sqlDate+"','"+this.cnic.getText()+"',"
                 + "'"+this.phone_no.getText()+"','"+this.father_occ.getText()+"','"+this.mother_occ.getText()+"',"+Integer.toString(department_id)+",'"+sqlDate2+"','"+pass+"'";
         int a = sql.execute_Query_for_set(query);
    }
}