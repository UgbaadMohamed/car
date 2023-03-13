import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in); //Scanner

    //Attributes
    private int input;
    private boolean keeplaying = true;

    DatabaseConnection dc = new DatabaseConnection();

    //New instancer of the classes as objects, to call methods from there
    SubMenuAvailableCars subMenuAvailabelCars = new SubMenuAvailableCars(dc);
    SubMenuRental subMenuRental = new SubMenuRental(dc);
    SubMenuOrderOverview subMenuOrderOverview = new SubMenuOrderOverview(dc);


    public void menuHeader() { //The method for the pretty consol based UI
        System.out.printf("  ———————————————————————————————————————————————————————————————————————————————————————————————————————————————%n");
        System.out.println( "   K A I L U A    C A R      R E N T A L   " );
        System.out.println("  ———————————————————————————————————————————————————————————————————————————————————————————————————————————————");

        System.out.println();
        System.out.printf("  %-25s   %-30s    %5s %n","R E N T A L                          ","       A V A I L A B L E   C A R S ", "        O R D E R  O V E R V I E W       ");

        System.out.printf("  %-38s  %-37s %-50s ","Sign Up","       Luxury car  ",  "         Cancel rental  ");
        System.out.println();
        System.out.printf("   %-38s  %-37s %-50s ","\n  Add to basket         ","          Sports cars ","            Prolog rental");
        System.out.println();
        System.out.printf("   %-25s  %-37s","\n  Finalize order & sign contract", "               Family cars ");
        System.out.println();
        System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println( "  I N F O R M A T I O N ");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("\n  Choose 1 (Rental)");
        System.out.println("  Choose 2 (Available cars )");
        System.out.println("  Choose 3 (Order overview )");
        System.out.print("\n  Enter:");
    }

    public void readChoiceMenu(){
        try{
            while (keeplaying) {
                menuHeader();
                input = sc.nextInt();
                switch (input) {
                    case 1:
                        subMenuRental.rentalReaderChoice();
                        break;

                    case 2:
                        subMenuAvailabelCars.carsReaderChoice();
                        break;

                    case 3:
                        subMenuOrderOverview.orderReaderChoice();
                        break;

                    default:
                        System.out.println("You typed something the system could not understand!");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Waring you wrote something our system dose not contain ");
        }
    }
}



