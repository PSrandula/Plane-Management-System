import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Ticket {
    static String rowLetter ; //row(A,B,C,D)
    static int seatNumber; //seat number
    static double price;  //price of ticket
    static Person person1;  //person details



    public Ticket(){

    }
    //constructor to intialize ticket information
    public Ticket(String rowLetter,int seatNumber, Person person) {
        this.rowLetter = rowLetter;
        this.seatNumber = seatNumber;
        this.person1 = person1;
        this.price = price;

    }

    // Getters and Setters
    public String getRow(){
        return rowLetter;
    }
    public static int getSeat(){
        return seatNumber;
    }
    public static double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person1;
    }
    // save text file
    public static void filesave(double price){
        // Directory path to save the ticket
        String directoryPath = "./Tickets";
        //creating file name using row and seat number
        String filename = directoryPath + "/" + rowLetter + seatNumber + ".txt";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            //create a filewriter object to write to file
            FileWriter file_save = new FileWriter(filename);
            //writing ticket informations to file
            file_save.write("--------Ticket Information--------\n");
            file_save.write("\n");
            file_save.write("Row: " + rowLetter + "\n");
            file_save.write("Seat: " + seatNumber + "\n");
            file_save.write("Price: $" + price  );
            file_save.write("\n");
            //writing person information to file
            file_save.write("--------Person Information--------\n");
            file_save.write("\n");
            file_save.write("Name: " + person1.name + "\n");
            file_save.write("Surname: " + person1.surname + "\n");
            file_save.write("Email: " + person1.email + "\n");
            //closing the filewritter object
            file_save.close();
            System.out.println("Ticket information saved to this file: " + filename);
        } catch (IOException e) {
            //handling exceptions
            System.out.println("An error occurred while saving ticket information.");
            throw new RuntimeException(e);
        }

    }
    // save text file removing method
    public  void removeTickets(){
        // remove the all in Tickets file when program run again
        System.out.println("Removing the tickets now");
        // Change this to your directory path
        String directoryPath = "./Tickets";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // Loop through all files
        assert files != null;
        for (File file : files) {
            // Check if the file is a text file
            if (file.isFile() && file.getName().endsWith(".txt")) {
                if (file.delete()) {
                    System.out.println("Deleted file: " + file.getName());
                } else {
                    System.out.println("Failed to delete file: " + file.getName());
                }
            }
        }


    }
    //Method ticket price range creating
    public static double ticketPrice(int seatNumber){
        double Price = 0;
        System.out.println(seatNumber);

        if (seatNumber >= 1 && seatNumber <= 5) {
            Price = 200;
            System.out.println(Price);
        } else if (seatNumber >= 6 && seatNumber <= 9) {
            Price = 150;
            System.out.println(Price);
        } else if (seatNumber >= 10 && seatNumber <= 14) {
            Price = 180;
            System.out.println(Price);
        } else {
            System.out.println("Invalid seat number.");
        }

        return Price;


    }
    // Method total price calculating
    public static void totalPrice() {
        // Initialize total price
        double totalPrice = 0.0;

        // Directory contain the text files
        String directoryPath = "./Tickets";

        // Get list of files
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // Loop through each file in the directory
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try {
                        // Read file
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.startsWith("Price:")) {
                                String priceStr = line.split(":")[1].trim().replace("$", "");
                                double price = Double.parseDouble(priceStr);
                                totalPrice += price;
                            }
                        }

                        bufferedReader.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Print total price
        System.out.printf("Total Price: $%.2f%n", totalPrice);
    }




}
