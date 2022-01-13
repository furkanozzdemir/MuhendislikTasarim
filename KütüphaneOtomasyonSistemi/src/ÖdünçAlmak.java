package kütüphane.sistemii;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static kütüphane.sistemii.Bağlantı.bağlan;

public class ÖdünçAlmak {

    private int id;
    private int member_id;
    private int book_id;
    private boolean delivered;
    private LocalDate purchase_date;
    private LocalDate return_date;

    public ÖdünçAlmak() {
    }

    public ÖdünçAlmak(int member_id, int book_id) {
        this.member_id = member_id;
        this.book_id = book_id;
        this.delivered = false;
        this.purchase_date = LocalDate.now();
        this.return_date = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public LocalDate getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public static boolean returnBook(String memberTC) {
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Üyeİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs;
        int barrowId = -1;
        int memberId = -1;
        try {
            ResultSet rs2;
            Statement stm = (Statement) bağlan.createStatement();
            String sql = "select member_id from librarymenagement.member where national_id = '" + memberTC + "';";
            rs2 = stm.executeQuery(sql);
            rs2.next();
            memberId = rs2.getInt("member_id");

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt yapılırken hata meydana geldi");
        }
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Üyeİşlemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (memberId == -1) {
                return false;
            } else {
                Statement stm = (Statement) bağlan.createStatement();
                String sql = "select id from librarymenagement.barrow where member_id = " + memberId + " and was_delivered = false";
                rs = stm.executeQuery(sql);
                rs.next();
                barrowId = rs.getInt("id");
            }

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt yapılırken hata meydana geldi");
        }
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ÖdünçAlmak.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (barrowId == -1) {
                return false;
            } else {
                Statement stm = (Statement) bağlan.createStatement();
                String sql = "UPDATE `librarymenagement`.`barrow` SET `was_delivered` = true, `return_date` = '" + LocalDate.now() + "' WHERE `id` =" + barrowId;
                stm.executeUpdate(sql);
            }

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt yapılırkene hata meydana geldi");
        }
        return true;
    }

    public static boolean ekle(String memberTC, int bookId) {
        ResultSet rs;
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ÖdünçAlmak.class.getName()).log(Level.SEVERE, null, ex);
        }
        int memberId = -1;
        try {
            ResultSet rs2;
            Statement stm = (Statement) bağlan.createStatement();
            String sql = "select member_id from librarymenagement.member where national_id = '" + memberTC + "';";
            rs2 = stm.executeQuery(sql);
            rs2.next();
            memberId = rs2.getInt("member_id");

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt yapılırken hata meydana geldi");
        }
        try {
            Bağlantı.bağlantıAç();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ÖdünçAlmak.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement stm = (Statement) bağlan.createStatement();
            String sql = "select id from `librarymenagement`.`barrow` where member_id= " + memberId + " and `was_delivered` = false;";
            rs = stm.executeQuery(sql);

            if (rs.next() || memberId == -1) {
                System.out.println("zaten kitap aldınız");
                return false;
            } else {
                try {
                    Bağlantı.bağlantıAç();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ÖdünçAlmak.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ÖdünçAlmak ödünç = new ÖdünçAlmak(memberId, bookId);
                    Statement stm2 = (Statement) bağlan.createStatement();
                    String sql2 = "INSERT INTO `librarymenagement`.`barrow` (`member_id`, `book_id`, `was_delivered`, `purchase_date`,`return_date`) VALUES ('" + ödünç.getMember_id() + "', '" + ödünç.getBook_id() + "'," + ödünç.isDelivered() + ",'" + ödünç.getPurchase_date() + "',null);";
                    stm2.executeUpdate(sql2);

                    Bağlantı.bağlantıKapat();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("kayıt yapılırkene hata meydana geldi");
                }
            }

            Bağlantı.bağlantıKapat();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("kayıt yapılırkene hata meydana geldi");
        }
        return true;
    }

    /* public static void main(String[] args){
        ekle("15185302566",11);
       // returnBook("15185302566");
    }*/
}
