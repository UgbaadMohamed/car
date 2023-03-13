import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login  {
    DatabaseConnection dc = (DatabaseConnection) DatabaseConnection.con;
    String username;
    String password;
    Scanner sc = new Scanner(System.in);
    public void userLogin() throws SQLException {

        try {

            System.out.println("Enter your Username: ");
            username = sc.nextLine();

            System.out.println("Enter your password: ");
            password= sc.nextLine();

            Statement s = dc.con.createStatement();

            String query = "SELECT * FROM renters WHERE renters_username ='" + username + "' AND renters_code ='" + password + "'";
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                if (rs.getString("renters_code").equals(password) && rs.getString("renters_username").equals(username)) {
                    System.out.println("You have successfully logged in.");
                } else {
                    System.out.println("Invalid username or password.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}





