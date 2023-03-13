import java.util.InputMismatchException;
import java.util.Scanner;

public class SubMenuOrderOverview {

    //Attributs
    private int userInput;

    Scanner sc = new Scanner(System.in); //Scanner

    RentalInformation rentalInformation = new RentalInformation();

    public void printMenu(String leadText) {
        System.out.println(leadText);
    }

    public void orderReaderChoice(){
        try {
            System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- -");
            System.out.println( "  O R D E R  O V E R V I E W");
            System.out.print(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- -");
            printMenu("\n  Choose 1. (Cancel rental) \n " + " Choose 2. (Prolog rental)\n  Choose 3  (End session)\n" );
            System.out.print("  Enter:");
            userInput = sc.nextInt();
            switch (userInput){
                case 1 -> {
                    System.out.println("Cancel rental");
                    rentalInformation.EndRental();
                }

                case 2  -> {
                    System.out.println(" Prolog rental");
                    rentalInformation.prolongRental();
                }

                case 3-> System.out.println("  Exit");
                default->
                        System.out.println(" You typed something the system could not understand");
            }

        }catch (InputMismatchException e){
            System.out.println("Waring you wrote something our system dose not contain ");
        }

    }
}
