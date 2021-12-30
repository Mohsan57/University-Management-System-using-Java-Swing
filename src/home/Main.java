package home;

import Login_Page.Login_Page;
import Splash_Screen.Splash_Screen;
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        
        Splash_Screen splash_screen=new Splash_Screen();
        Thread t=new Thread(splash_screen);
        t.start();
        t.join();
        if(splash_screen.getbar()==100){
            sleep(100);
            splash_screen.dispose();
            Login_Page login_page = new Login_Page();
        }
    }
    
}