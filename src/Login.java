import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    DatabaseConnection dc;

    //Attributes
    String username;
    String password;

    Scanner sc = new Scanner(System.in); //Scanner

    public Login (DatabaseConnection dc) {
        this.dc = dc;
    } //Constructor


    public void userLogin() throws SQLException {

        //This code prompts the user to enter their username and password, queries the database to check if the
        // credentials are correct, and then outputs a success or error message based on the result.
        System.out.println("Enter your Username: ");
        username = sc.nextLine();

        System.out.println("Enter your password: ");
        password= sc.nextLine();
        
        String query = "SELECT * FROM renters WHERE renters_username ='" + username + "' AND renters_code ='" + password + "'";
        dc.connectingSQL(query ,new String [] {},new String [] {});

        Statement s = dc.con.createStatement();

        ResultSet rs = s.executeQuery(query);

           if (rs.next()) {
                if (rs.getString("renters_code").equals(password) && rs.getString("renters_username").equals(username)) {
                    System.out.println("You have successfully logged in.");
                }} else {
                    System.out.println("Invalid username or password.");
                }

    }
}







