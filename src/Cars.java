import java.sql.*;
import java.util.Scanner;
public class Cars {
    Scanner sc = new Scanner(System.in);
    Scanner in = new Scanner(System.in);

    private int chooseInput; // A variable from user which answers "1. To see your options or 2. To search for a certain brand
    private String brandInput; // A variable from user which answers this type of brand they would like

    DatabaseConnection dc;

    public Cars (DatabaseConnection dc) {
        this.dc = dc;
    }


    public void carType() throws SQLException {
        System.out.println("what car type do you want?  \n 1. For luxuryCar \n 2. For familyCar \n 3. For sportsCar" );
        int answar = sc.nextInt();
        switch (answar) {
            case 1 -> luxuryCar();
            case 2 -> familyCar();
            case 3 -> sportsCar();
        }

    }
    public void luxuryCar() throws SQLException {
        carSelection("luxury_car_id");
    }

    public void familyCar() throws SQLException {
        carSelection("family_car_id");

    }

    public void sportsCar() throws SQLException {
        carSelection("sport_car_id");
    }

    public void carSelection(String carType) {

        System.out.println("Click 1.To see your options\n CLick 2. To search for a certain brand");
        chooseInput = sc.nextInt();

        switch (chooseInput) {
            case 1 ->
                    DatabaseConnection.connectingSQL("SELECT car_id, car_brand, car_model, car_plate, car_first_registration, car_odometer FROM car_information WHERE " + carType + ">0",
                            new String[]{"Car ID: ", "Car brand:" , "Car model: ", "Car plate: ", "Car first registration: ", "Car odometer: "},
                            new String[]{"car_id", "car_brand", "car_model", "car_plate", "car_first_registration", "car_odometer"});

            case 2 -> {
                System.out.println("Type the type of brand you would like?");
                brandInput = sc.next();
                DatabaseConnection.connectingSQL("Select car_id, car_brand, car_model, car_plate, car_first_registration, car_odometer FROM car_information WHERE " + carType + "> 0 AND car_brand LIKE " + "'%" + brandInput + "%'",
                        new String[]{"Car ID", "Car brand:", "Car model", "Car plate", "Car first_registration", "car_odometer"},
                        new String[]{"car_id", "car_brand", "car_model", "car_plate", "car_first_registration", "car_odometer"});

             }
        }
    }

        public void choseCar() throws SQLException { //Here the user can choose a car to reserve
            System.out.println("Please enter the ID of the car you wish to rent:");
            int carId = in.nextInt();
            in.nextLine(); // consume the newline character left by nextInt()

            System.out.println("Please enter your renter's ID:");
            String rentersId = in.nextLine();

            String query = "SELECT * FROM rental_contract WHERE car_id = " + carId;

            Statement statement = dc.con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) { // car is currently reserved by someone else
                System.out.println("The car you wish to rent is currently reserved by someone else. " +
                        "you can proceed anyway and hurry to sign the contract to secure the car");
              }
            query = "INSERT INTO rental_contract (car_id, renters_id) VALUES (" + carId + ", '" + rentersId + "')";
            dc.executeDML(query, "Rental contract created with contract ID: ");

        }
            }





