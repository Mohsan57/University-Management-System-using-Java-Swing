
package Splash_Screen;

import  java.awt.*;
import  javax.swing.*;
import static java.lang.Thread.sleep;

public class Splash_Screen extends  JFrame implements Runnable {
    private  JLabel label,loading_logo,logo;
    private  JPanel main_panel,center,North,Left_North,jPanel14,jPanel13,jPanel12;
    private  JProgressBar bar;
    
    public Splash_Screen(){
        Components();
    }
    void Components() {
        
        main_panel = new  JPanel();
        Left_North = new  JPanel();
        logo = new  JLabel();
        North = new  JPanel();
        label = new  JLabel();
        center = new  JPanel();
        jPanel12 = new  JPanel();
        jPanel13 = new  JPanel();
        loading_logo = new  JLabel();
        jPanel14 = new  JPanel();
        bar = new  JProgressBar();

        setAlwaysOnTop(true);
        super.getContentPane().setBackground(new  Color(17, 12, 50));
        setLocation(new  Point(200, 100));
        setMaximizedBounds(new  Rectangle(150, 100, 900, 500));
        setUndecorated(false);
        setPreferredSize(new  Dimension(900, 500));

        main_panel.setLayout(new  BorderLayout());

        setLogo();
        logo.setPreferredSize(new  Dimension(100, 75));
        Left_North.add(logo);

        main_panel.add(Left_North,  BorderLayout.WEST);

        label.setFont(new  Font("Calisto MT", Font.BOLD, 36));
        label.setForeground(Color.white);
        setLabel("<html><p style=\"text-align: center;\">UNIVERSITY MANAGEMENT <br> SYSTEM</p></html>");
        North.add(label);

        main_panel.add(North,  BorderLayout.CENTER);

        getContentPane().add(main_panel,  BorderLayout.PAGE_START);

        center.setBackground(new  Color(17, 12, 50));
        center.setLayout(new  GridLayout(3, 1));

        GroupLayout jPanel12Layout = new  GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        
        center.add(jPanel12);

        setLoading_logo();
        jPanel13.add(loading_logo);

        center.add(jPanel13);

        bar.setValue(50);
        bar.setPreferredSize(new  Dimension(420, 70));
        bar.setStringPainted(true);
        jPanel14.add(bar);

        center.add(jPanel14);

        super.setUndecorated(true);
        super.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        getContentPane().add(center,  BorderLayout.CENTER);
        this.setBackground();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
         setVisible(true);
         
     }

    public String getLabel() {
        return label.getText();
    }

    public void setLabel(String text) {
        this.label.setText(text);
    }

    public String getLoading_logo() {
        return loading_logo.getText();
    }

    public void setLoading_logo() {
        loading_logo.setIcon(new  ImageIcon(getClass().getResource("/Images/loading 2_2.gif")));
       
    }

    public String getLogo() {
        return logo.getText();
    }

    public void setLogo() {
        this.logo.setIcon(new  ImageIcon(getClass().getResource("/Images/logo2_1.png")));
        
    }

    public int getbar() {
        return bar.getValue();
    }

    public void setbar(int value) {
        this.bar.setValue(value);
    }
    
     void setBackground(){
         Color bkcolor = new Color(17, 12, 50);
         bar.setBackground(Color.white);
         bar.setBorderPainted(true);
        this.Left_North.setBackground(bkcolor);
        this.North.setBackground(bkcolor);
        this.center.setBackground(bkcolor);
        this.jPanel12.setBackground(bkcolor);
        this.jPanel13.setBackground(bkcolor);
        this.jPanel14.setBackground(bkcolor);
        this.main_panel.setBackground(bkcolor);
        
    }
    
     @Override
    public void run() {
       try {
            loading();
        } catch (InterruptedException ex) {}
    }
    void loading() throws InterruptedException{
        for(int i=0;i<=100;i++){
            sleep(20);
            setbar(i);
        }
    }             
}
