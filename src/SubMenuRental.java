import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubMenuRental {

    //Attributes
    private int userInput;

    Scanner sc = new Scanner(System.in); //Scanner

    //New instancer of the classes as objects, to call methods from there
    CostumersReg costumersReg = new CostumersReg();
    Cars cars = new Cars();
    Login login = new Login();

    public void printMenu(String leadText) {
        System.out.println(leadText);

    }
    public void rentalReaderChoice(){
        try {
            System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- -");
            System.out.println( "  R E N T A L ");
            System.out.print(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- -");
            printMenu("\n  Choose 1. (Sign up) \n " + " Choose 2. (Add to basket)\n  Choose 3  (End session)\n" );
            System.out.print("  Enter:");
            userInput = sc.nextInt();
            switch (userInput){

                case 1 -> {
                    costumersReg.createRenter();
                }
                case 2  -> {
                    login.userLogin();
                    cars.choseCar();
                }
                case 3  -> {
                    costumersReg.rentalContract();
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

