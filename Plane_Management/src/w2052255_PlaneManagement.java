//using for reading text
import java.io.BufferedReader;
//represents a file
import java.io.File;
//use for resding char files
import java.io.FileReader;
//singals that an I/o exception
import java.io.IOException;
//input provided does not match the excepted data type
import java.util.InputMismatchException;
//use for  reading user input from console
import java.util.Scanner;

//Main method to program
public class w2052255_PlaneManagement {
    public static int seatNumber;
    public static String rowLetter;
    //array to represent seating rows A,B,C,D
    static int[][] seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Row A
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,}, // Row B
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,}, // Row C
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // Row D
    };
   //Array to store ticket object
    static Ticket[] tickets =new Ticket[52];

    static int ticketcount=0;

    //Main method to start the program
    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        //Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        Ticket ticket = new Ticket();
        ticket.removeTickets();

        //Menu option
        int option;
        do {
            //Display the menu options
            System.out.println('\n');
            System.out.println("**********************************************");
            System.out.println("*           Plane Management Menu            *");
            System.out.println("**********************************************");
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find first available seat");
            System.out.println("4. Show seating plan");
            System.out.println("5. Print tickets information and total sales");
            System.out.println("6. Search ticket");
            System.out.println("0. Exit");
            System.out.println("**********************************************");
            System.out.println('\n');

            //print option user select
            System.out.print("Enter your option: ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        //booking a seat
                        buy_Seat();
                        break;
                    case 2:
                        //cancelling a seat pre-booked
                        cancel_Seat();
                        break;
                    case 3:
                        //search available first seat location
                        find_First_Availble();
                        break;
                    case 4:
                        //display seat plan
                        show_Seating_Plan();
                        break;
                    case 5:
                        //print ticket information and person information
                        print_Ticket_Information();
                        //call total price
                        Ticket.totalPrice();
                        break;
                    case 6:
                        //search you want buy seat available or not
                        search_ticket();

                        break;
                    case 0:
                        //exiting the program
                        System.out.println("Exiting program.");
                        break;
                    default:
                        //handling invalid menu option
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                //handling invalid input
                System.out.println("Invalid option. Please enter your option again.");
                scanner.next();
                option = -1;
            }
        }    while (option != 0);

        scanner.close();
    }
    // method to validation name and surname
    private static String getValidName(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.next().trim();
            if (!isValidName(input)) {
                System.out.println("Invalid input. Please enter a valid name or surname.");
            }
        } while (!isValidName(input));
        return input;
    }

    // Method to validation email
    private static String getValidEmail(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.next().trim();
            if (!isValidEmail(input)) {
                System.out.println("Invalid input. Please enter a valid email address.");
            }
        } while (!isValidEmail(input));
        return input;
    }

    // Validation method for name and surname
    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
    // validation method for email
    private static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    //Method for buying a seat
    public static void buy_Seat() {
        Scanner scanner = new Scanner(System.in);
        //ask user want seat row
        System.out.print("Enter row letter (A,B,C,D):  ");
        //char variable store row letter
        char rowLetter;
        //handling invalid input
        try {
            rowLetter = scanner.next().toUpperCase().charAt(0);
        } catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid row letter.");
            return;
        }
        //verify row letter correct or not
        int row = rowLetter - 'A';
        if (row < 0 || row >= seats.length) {
            System.out.println("Invalid row your entered. Try again.");
            return;

        }
        //get user want seat number
        System.out.print("Enter the seat number book you wanted: ");
        // create integer variable
        int seatNumber;
        //handling invalid input
        try {
            seatNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Seat number you entered. Please enter a valid seat number.");
            scanner.next();
            return;
        }
        //verify seat number correct or not
        if (seatNumber < 1 || seatNumber > seats[row].length) {
            System.out.println("Invalid seat number. Try again.");
            return;
        }
        //seat is booked early
        if (seats[row][seatNumber - 1] == 1) {
            System.out.println("Seat is already booked.");
            return;
        }else {
            //get passenger name,surname and email
            String name = getValidName(scanner,"Enter passenger name: ");
            String surname = getValidName(scanner, "Enter passenger surname:");
            String email = getValidEmail(scanner, "Enter your email:");

            //create a new person object
            Person person1 = new Person(name, surname, email);
            //save ticket information
            String row1 = Character.toString(rowLetter);
            double price = Ticket.ticketPrice(seatNumber);
            if(row1.equals("A")){
                tickets[seatNumber-1] = new Ticket( row1, seatNumber,person1);
                tickets[seatNumber-1].filesave(price);
            } else if (row1.equals("B")) {
                tickets[14+seatNumber-1] = new Ticket(row1, seatNumber,person1);
                tickets[14+seatNumber-1].filesave(price);

            } else if (row1.equals("C")) {
                tickets[26+seatNumber-1] = new Ticket(row1, seatNumber,person1);
                tickets[26+seatNumber-1].filesave(price);

            } else if (row1.equals("D")) {
                tickets[38+seatNumber-1] = new Ticket(row1, seatNumber,person1);
                tickets[38+seatNumber-1].filesave(price);
            }
            //confirm seat book message
            seats[row][seatNumber - 1] = 1;
            System.out.println("Your Seat booked successfully.");
        }
    }
    //Method for cancelling a seat pre-booked
    public static void cancel_Seat() {
        Scanner scanner = new Scanner(System.in);
        //ask cancelling seat user prebooked
        System.out.print("Enter row letter (A,B,C,D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        //ask cancelling seat number user prebooked
        System.out.print("Enter the seat number you booked: ");
        int seatNumber;
        //handling invalid input
        try{
            seatNumber = scanner.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Invalid Seat number you entered. Please enter a valid seat number.");
            scanner.next();
            return;
        }
        //check correct row and seat number given by user
        int row = rowLetter- 'A';
        if (row < 0|| row>= seats.length || seatNumber < 1 || seatNumber> seats[row].length){
            System.out.println("Invalid row or seat number.");
            return;
        }
        //print message seat not booked early
        if (seats[row][seatNumber-1]==0){
            System.out.println("This seat is already available.");
            //confirm seat cancelling successful
        } else {
            seats[row][seatNumber-1]=0;
            System.out.println("Your seat canceled successfully.");
        }
    }
    //Method to find and display first available seat
    public static void find_First_Availble() {
        for (int i=0; i< seats.length; i++){
            for (int j=0; j< seats[i].length; j++) {
                if (seats[i][j]==0){
                    //print message available seat
                    System.out.println("The First available seat is : Row "+(char)('A'+i)+",seat"+(j+1));
                    return;
                }
            }

        }
        // all seat booking print this one
        System.out.println("No available seats.");
    }
    //Method to display seating plan
    public static void show_Seating_Plan() {
        // print seating plan
        System.out.println("Seating Plan:");
        for (int i = 0; i < seats.length; i++){
            System.out.print((char)('A'+i)+" ");
            for (int j = 0;j<seats[i].length; j++) {
                // if seat is available print this
                if (seats[i][j]==0){
                    System.out.print("0 ");
                    // if booked seat print this
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
    //Method to print ticket and person information and total prices
    public static void print_Ticket_Information() {
        String projectDirectory = System.getProperty("user.dir");
        //create ticket folder
        String relativePath = "Tickets";
        String directoryPath = projectDirectory + File.separator + relativePath;
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // Loop through all files in the directory
        assert files != null;
        for (File file : files) {
            // Check if the file is a text file is correct
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    System.out.println("Ticket " + file.getName() + ":");
                    System.out.println("\n");
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("\n");
                    //can't read file print this message
                } catch (IOException e) {
                    System.out.println("Error reading file: " + file.getName());
                }
            }
        }
    }
    //Method to search ticket information
    public static void search_ticket() {
        Scanner scanner = new Scanner(System.in);
        //ask user want search row
        System.out.print("Enter row letter (A,B,C,D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        //ask user want search seat number
        System.out.print("Enter the seat number want to search: ");
        int seatNumber;
        //handling invalid input
        try{
            seatNumber = scanner.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Invalid Seat number you entered. Please enter a valid seat number.");
            scanner.next();
            return;
        }
        scanner.nextLine();
        //wrong row or seat number print this
        int row = rowLetter - 'A';
        if (row < 0 || row >= seats.length || seatNumber < 1 || seatNumber > seats[row].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }
        String ticketIdentifier = rowLetter + String.valueOf(seatNumber);
        //not booking seat print available message
        if (seats[row][seatNumber-1]==0){
            System.out.println("This seat is available.");
        }else {
            //seat booked print this and passenger information
            System.out.println("This seat is already booked.");
            // Directory path to save the ticket
            String directoryPath = "./Tickets";
            String filename = directoryPath + "/" + ticketIdentifier + ".txt";

            // Check if the file exists
            File file = new File(filename);
            if (file.exists()) {
                try {
                    FileReader fileReader = new FileReader(filename);
                    Scanner fileScanner = new Scanner(fileReader);

                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        System.out.println(line);
                    }

                    fileScanner.close();
                    fileReader.close();

                } catch (IOException e) {
                    System.out.println("An error occurred while reading the ticket information.");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
