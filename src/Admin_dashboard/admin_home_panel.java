
package Admin_dashboard;


import java.awt.Color;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import sql_connector.sql_connector;


public class admin_home_panel extends JPanel{
    Color background_color;
    int admin_id;
    JLabel id;
    JLabel admin_name;
    JLabel admin_email;
    JLabel admin_department;
    sql_connector sql;
    admin_home_panel(int admin_id){
        this.admin_id = admin_id;
        id = new JLabel();
        admin_name = new JLabel();
        admin_email = new JLabel();
        admin_department = new JLabel();
        sql = new sql_connector();
        background_color = new Color(124, 193, 235);
        
        
        
        setLayout(new BorderLayout());
        setBackground(background_color);
        get_Admin_detail();
        
        set_North();
        barchart();
        piechart();
        
        
        try {
            sql.finalize();
        } catch (SQLException ex) {
            Logger.getLogger(admin_home_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void set_North(){
        JPanel north = new JPanel();
        JPanel north_p1 = new JPanel();
        JPanel north_p2 = new JPanel();
        JPanel north_p3 = new JPanel();
        JPanel north_p4 = new JPanel();
        JLabel l1 = new JLabel("ID: ");
        JLabel l2 = new JLabel("Name: ");
        JLabel l3 = new JLabel("Email: ");
        JLabel l4 = new JLabel("Department: ");
        north.setLayout(new GridLayout(2,2));
        
        north_p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        north.setBackground(background_color);
        north_p1.setBackground(null);
        north_p2.setBackground(null);
        north_p3.setBackground(null);
        north_p4.setBackground(null);
        
        north_p1.add(l1);
        north_p1.add(id);
        
        north_p2.add(l2);
        north_p2.add(admin_name);
        
        north_p3.add(l3);
        north_p3.add(admin_email);
        
        
        north_p4.add(l4);
        north_p4.add(admin_department);
        
        north.add(north_p1);
        north.add(north_p2);
        north.add(north_p3);
        north.add(north_p4);
        this.add(north,BorderLayout.NORTH);
    }
    void piechart(){
       	DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Non-Enroll Student",99);
        dataset.setValue("Enroll Student", 77);

        //third adaption
        JFreeChart someChart = ChartFactory.createPieChart(
            "Total Student Enroll", dataset,
            true, true, false);
        ChartPanel CP = new ChartPanel(someChart);
        this.add(CP);
    }
    void barchart(){
        DefaultCategoryDataset data=new DefaultCategoryDataset();
        data.addValue(80, "Marks", "Mohsan");
        data.addValue(90, "Marks", "Nouman");
        data.addValue(79, "Marks", "Hamza");
        data.addValue(17, "Marks", "Mubeen");
        data.addValue(62, "Marks", "Mufti");
        data.addValue(60, "Marks", "Haroon");
        
        JFreeChart chart = ChartFactory.createBarChart("Marks", "Student", "Student Marks", data, PlotOrientation.HORIZONTAL, true, true, true);
        
        ChartPanel CP = new ChartPanel(chart);
        this.add(CP);
    }
     void get_Admin_detail(){
            String query="select * from admin_basic_info("+admin_id+")";
        try{
            var rs = sql.execute_Query_for_get(query);
            while(rs.next()){
                id.setText(Integer.toString(rs.getInt("admin_id")));
                admin_name.setText(rs.getString("admin_name"));
                admin_email.setText(rs.getString("admin_user_name"));
                admin_department.setText(rs.getString("department_name"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
