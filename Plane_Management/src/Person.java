import java.util.Scanner;

public class Person {
    //Declaring attributes of the person
    static String name;
    static String surname;
    static String email;
    //Scanner object for user input
    static Scanner scanner = new Scanner(System.in);
    //constructor to intialize person attributes
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person() {
        this("", "", "");
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    //print person information
    public void personalInformation() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }

}

