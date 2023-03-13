import java.sql.SQLException;
import java.util.Scanner;
public class CustomerRegistration {

    Scanner sc = new Scanner(System.in); //Scanner for all String variables
    Scanner in = new Scanner(System.in); //Scanner for all integer variables


    DatabaseConnection dc;

    public CustomerRegistration(DatabaseConnection dc) {
        this.dc = dc;
    }

    public void createRenter() throws SQLException {

        //This code creates a new renter account by taking user inputs for personal information, inserting the information
        //into a renters table in a SQL database and displaying a message with the new renters_id.

        System.out.println("Enter you name: ");
        String renters_name = in.nextLine();

        System.out.println("Enter your address: ");
        String renters_address = in.nextLine();

        System.out.println("Enter your  zip?");
        int renters_zip = sc.nextInt();

        System.out.println("Enter your phone number?");
        int renters_phone = sc.nextInt();

        System.out.println("Enter your licensnumber");
        int renters_license_number = sc.nextInt();

        System.out.println("Driven licens since date?");
        String renters_driver_since_date = in.nextLine();

        System.out.println("Select a username of your choice: ");
        String username = in.nextLine();

        System.out.println("Select a password: ");
        String password = in.nextLine();

        String query = "INSERT INTO renters (renters_id, renters_name, renters_address," +
                " renters_zip, renters_phone, renters_license_number, renters_username," +
                " renters_code, renters_driver_since_date)\n" +
                "SELECT COALESCE(MAX(renters_id), 0) + 1, '" +
                renters_name + "', '" +
                renters_address + "', " +
                renters_zip + ", '" +
                renters_phone + "', '" +
                renters_license_number + "', '" +
                username + "', '" +
                password + "', '" +
                renters_driver_since_date + "' FROM renters";

        DatabaseConnection.executeDML(query, "Thank you for signing up! Your account has been created.");
        DatabaseConnection.connectingSQL("SELECT renters_id \n" +
                "FROM renters \n" +
                "ORDER BY renters_id DESC \n" +
                "LIMIT 1;\n", new String[]{"Your renter id is: "}, new String[]{"renters_id"});
    }

    public void rentalContract() throws SQLException {
        //This code creates a rental contract by taking user inputs for the renter and car IDs,
        //rental dates, and maximum distance, inserting them into a rental_contract table in a SQL database
        System.out.println("Enter your renter ID?");
        int renters_id = sc.nextInt();

        System.out.println("Enter your car_id");
        int car_id = sc.nextInt();

        System.out.println("What date is the rental starting from? ");
        String rental_start_date = in.next();

        System.out.println("What day is the rental ending?");
        String rental_end_date = in.next();

        System.out.println("The current Km?");
        int rental_start_km = sc.nextInt();


        System.out.println("The max km for the renter to use?");
        int rental_maximum_km = sc.nextInt();

        String insertQuery = "INSERT INTO rental_contract (car_id, renters_id, rental_start_date," +
                " rental_end_date, rental_maximum_km, rental_start_km) VALUES (" +
                car_id + ", " + renters_id + ", '" + rental_start_date + "', '" + rental_end_date +
                "', " + rental_start_km  + ", " + rental_maximum_km + ")";


        dc.executeDML(insertQuery, "Rental contract created with contract ID: ");
        System.out.println(insertQuery);
        dc.connectingSQL("SELECT contract_id FROM rental_contract WHERE renters_id =" + renters_id, new String[]{"contract id"}, new String[]{"contract_id"});
        dc.con.close();
    }
}


