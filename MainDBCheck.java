
package mini;
import java.sql.Connection;
import java.sql.DriverManager;
public class MainDBCheck {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/vinayak";
        String user = "root";
        String password = "Ruben07!";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connected to MySQL successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}