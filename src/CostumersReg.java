import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
public class CostumersReg extends DatabaseConnection {

    Scanner sc = new Scanner(System.in); //Scanner for all String variables
    Scanner in = new Scanner(System.in); //Scanner for all integer variables

    public void createRenter() throws SQLException {
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

        String query = "INSERT INTO renters (renters_id, renters_name, renters_address, renters_zip, renters_phone, renters_license_number, renters_username, renters_code, renters_driver_since_date)\n" +
                "SELECT COALESCE(MAX(renters_id), 0) + 1, '" +
                renters_name + "', '" +
                renters_address + "', " +
                renters_zip + ", '" +
                renters_phone + "', '" +
                renters_license_number + "', '" +
                username + "', '" +
                password + "', '" +
                renters_driver_since_date + "' FROM renters";

        executeDML(query, "Thank you for signing up! Your account has been created.");
        connectingSQL("SELECT renters_id \n" +
                "FROM renters \n" +
                "ORDER BY renters_id DESC \n" +
                "LIMIT 1;\n",new String[]{"Your renter id is: "},new String[]{"renters_id"});
    }

       public void rentalContract () throws SQLException {
           System.out.println("Enter your renter ID?");
           int renters_id = sc.nextInt();

           System.out.println("Enter your car_id");
           int car_id= sc.nextInt();

           System.out.println("What date is the rental starting from? ");
           String rental_start_date = in.next();

           System.out.println("What day is the rental ending?");
           String rental_end_date = in.next();

           System.out.println("The max km for the renter to use?");
           int rental_maximum_km = sc.nextInt();

           String insertQuery = "INSERT INTO rental_contract (car_id, renters_id, rental_start_date, rental_end_date, rental_maximum_km, rental_start_km) VALUES (" +
                   car_id + ", " + renters_id + ", '" + rental_start_date + "', '" + rental_end_date + "', " + rental_maximum_km + ", (SELECT rental_start_date FROM car_information WHERE car_id =" + car_id + "))";

           executeDML(insertQuery, "Rental contract created with contract ID: ");
           System.out.println(insertQuery);
           connectingSQL("SELECT contract_id FROM rental_contract WHERE renters_id =" + renters_id, new String[]{"contract id"}, new String[]{"contract_id"});
           con.close();
       }

   }
