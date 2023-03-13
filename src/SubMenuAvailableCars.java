import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubMenuAvailableCars {

    private int userInput;

    Scanner sc = new Scanner(System.in); //Scanner

    DatabaseConnection dc;
    CarOverview ri = new CarOverview(dc);
    Cars cars = new Cars(dc);


    public SubMenuAvailableCars (DatabaseConnection dc) {
        this.dc = dc;
    }

    public void printMenu(String leadText) {
        System.out.println(leadText);
    }

    public void carGroupDescription() {
        System.out.println("\n\n  1️⃣ Luxury (>3000 ccm, automatic gear, air condition, cruise control, leather seats\n" +
                "  2️⃣ Family (manual gear, air condition, some with cruise control, 7 seats or more)\n" +
                "  3️⃣ Sport (manual  gear, > 200 hp)\n\n");
    }

    public void carsReaderChoice(){
        try {
            System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- -");
            System.out.println( "  A V A I L A B E L   C A R S");
            System.out.print(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- -");
            carGroupDescription();
            printMenu("\n  Choose 1. (Search for car group) \n " + "Choose 2. (View all available cars)\n  Choose 3. (Search for cars that are currently rented)\n" +
                    "  Choose 4  (Exit)\n" );
            System.out.print("  Enter:");
            userInput = sc.nextInt();
            switch (userInput){

                case 1 -> {
                    cars.carType();
                }
                case 2  -> {
                    ri.availableCars();
                }
                case 3  -> {
                    ri.rentedCars();
                }
                case 4-> System.out.println("  Exit");
                default->
                        System.out.println(" You typed something the system could not understand");
            }

        }catch (InputMismatchException e){
            System.out.println("Waring you wrote something our system dose not contain ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}