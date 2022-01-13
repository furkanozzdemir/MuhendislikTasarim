package kütüphane.sistemii;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Bağlantı {
    
    public static Connection bağlan;
    
    public static void  bağlantıAç() throws ClassNotFoundException{
        
        
        try {
            
        Class.forName("com.mysql.jdbc.Driver");
        String con="jdbc:mysql://localhost:3306/librarymenagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
        String kullanıcıadi="root";
        String sifre="global_wars";
            bağlan=DriverManager.getConnection(con, kullanıcıadi, sifre);
             
                    } catch (SQLException e) {
                        
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Bağlantı başarısız oldu");
        }
     
    }
    
     public static Connection bağlantıKapat(){
        try {
            bağlan.close();            
        } catch (SQLException e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "bağlantı kapatılamadı");
        }
        return null;
           

     }
    
    
}
