import java.sql.*;
import java.util.Scanner;
public class Cars extends DatabaseConnection {
    Scanner sc = new Scanner(System.in);
    Scanner in = new Scanner(System.in);

    private int chooseInput; // A variable from user which answers "1. To see your options or 2. To search for a certain brand
    private String brandInput; // A variable from user which answers this type of brand they would like
    private int car_id;

    public void luxuryCar() throws SQLException {
        carSelection("luxury_car_id");
    }

    public void familyCar() throws SQLException {
        carSelection("family_car_id");

    }

    public void sportsCar() throws SQLException {
        carSelection("sport_car_id");
    }


    //virker ikke?
    public void carType() throws SQLException {
        System.out.println("what car type do you want?");
        int answar = sc.nextInt();
        switch (answar) {
            case 1 -> luxuryCar();
            case 2 -> familyCar();
            case 3 -> sportsCar();
        }

    }

    public void carSelection(String carType) throws SQLException {
        System.out.println("Click 1.To see your options\n CLick 2. To search for a certain brand");
        chooseInput = sc.nextInt();

        switch (chooseInput) {
            case 1 ->
                    connectingSQL("SELECT car_id, car_brand, car_model, car_plate, car_first_registration, car_odometer FROM car_information WHERE " + carType + ">0",
                            new String[]{"Car ID", "Car brand:", "Car model:", "Car plate:", "Car first registration:", "Car odometer:"},
                            new String[]{"car_id", "car_brand", "car_model", "car_plate", "car_first_registration", "car_odometer"});

            case 2 -> {
                System.out.println("Type the type of brand you would like?");
                brandInput = sc.next();
                connectingSQL("Select car_id, car_brand, car_model, car_plate, car_first_registration, car_odometer FROM car_information WHERE " + carType + "> 0 AND car_brand LIKE " + "'%" + brandInput + "%'",
                        new String[]{"Car ID", "Car brand:", "Car model", "Car plate", "Car first_registration", "car_odometer"},
                        new String[]{"car_id", "car_brand", "car_model", "car_plate", "car_first_registration", "car_odometer"});
            }

        }
    }

        public void choseCar() throws SQLException { //Here the user can choose a car to reserve
            System.out.println("Write the ID of the car you wish to rent?");
            car_id = in.nextInt();
            in.nextLine(); // Scannerbug

            System.out.println("What is your renters_ID:");
            String renters_id = in.nextLine();

            Statement s = con.createStatement();


            String query = "SELECT * FROM rental_contract WHERE car_id = " + car_id + " OR renters_id = '" + renters_id + "'";
            ResultSet rs = s.executeQuery(query);

            if (rs.next()) {
                if (rs.getInt("car_id") == car_id) {
                    System.out.println("The car you wish for is currently reserved by someone else. Would you like to proceed anyways and hurry to sign the contract to secure the car(Y/N)? )");
                }
                String answer = in.nextLine();
                if (!answer.equalsIgnoreCase("Y")) {
                    return;
                }
            } else {

                query = "INSERT INTO rental_contract (car_id) VALUES (" + car_id + ", '" + renters_id + "')";
                executeDML(query, "Rental contract created with contract ID: ");
                System.out.println(query);
            }
        }
}

