import java.sql.SQLException;
import java.util.Scanner;

public class CarOverview {
    Scanner sc = new Scanner(System.in);
    private String userInput;

    DatabaseConnection dc;

    public CarOverview(DatabaseConnection dc) {
        this.dc = dc;
    }

    public void rentedCars()throws SQLException {
        String query = "SELECT rental_end_date\n" +
                "FROM car_information\n" +
                "INNER JOIN rental_contract ON car_information.car_id = rental_contract.car_id;";
        dc.connectingSQL(query, new String[]{"The end of the rental"}, new String[]{"rental_end_date"});
    }

    public void availableCars()throws SQLException {
        String query = "SELECT * FROM car_information " +
                "LEFT JOIN rental_contract ON car_information.car_id = rental_contract.car_id " +
                "WHERE rental_contract.car_id IS NULL;";
        String[] columns = {"car_information.car_id", "car_information.car_brand", "car_information.car_model",
                "car_information.car_plate", "car_information.car_first_registration"};
        dc.connectingSQL(query, new String[]{"car_information.car_id ", "car_information.car_brand ", "car_information.car_model " +
                "",
                "car_information.car_plate ", "car_information.car_first_registration "}, columns);
    }

    public void prolongRental()throws SQLException {
        System.out.println("what is you renter ID?");
        userInput = sc.nextLine();
        System.out.println("What date do you wish to change it to?");
        String newDate = sc.nextLine();
        String query = "UPDATE rental_contract\n" +
                "SET rental_end_date =" + "'" + newDate + "'" + "\n" +
                "WHERE renters_i = " + userInput;
        dc.executeDML(query, "The rental is now prolong to: " + newDate);
    }

    public void endRental() throws SQLException {

            System.out.println("what is you renter ID?");
            userInput = sc.nextLine();
            String deleteQuery = "DELETE FROM rental_contract WHERE renters_id =" + userInput;

            dc.executeDML(deleteQuery, "\"Thank you for renting with us! " +
                    "Please return the vehicle at kailua car rental with full tank\"");


    }
}
