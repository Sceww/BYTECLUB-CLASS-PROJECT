package edu.utsa.cs3443.byteclub.util;

import edu.utsa.cs3443.byteclub.model.Database;
import edu.utsa.cs3443.byteclub.model.Person.User;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Not intended to be used by end users
 * Assists in the generation of data
 */
public class DataGenerator {

    private static final Scanner scnr = new Scanner(System.in);

    private static void p(String m) {
        System.out.println(m);
    }

    private static void createItem() {
        p("itemId");
        int itemId = scnr.nextInt();
        scnr.nextLine();
        p("itemName");
        String itemName = scnr.nextLine().strip();
        p("price");
        double price = scnr.nextDouble();
        scnr.nextLine();

        // save to database
        Database.registerItem(itemId, itemName, price);
    }

    private static void createEvent() {
        p("eventid");
        int eventId = scnr.nextInt();
        scnr.nextLine();
        p("hostid");
        int hostid = scnr.nextInt();
        scnr.nextLine();
        p("location");
        String location = scnr.nextLine().strip();
        p("title");
        String title = scnr.nextLine().strip();
        p("desc");
        String desc = scnr.nextLine().strip();
        p("date (DD/MM/YY 12:00 AM/PM)");
        String date = scnr.nextLine();
        p("numPeople");
        int numPeople = scnr.nextInt();
        scnr.nextLine();
        p("numSeats");
        int numSeats = scnr.nextInt();
        scnr.nextLine();

        // save to database
        Database.registerEvent(eventId, hostid, location, title, desc, date, numPeople, numSeats);
    }

    private static void createUser() {
        p("userid");
        int userid = scnr.nextInt();
        scnr.nextLine();
        p("email");
        String email = scnr.nextLine().strip();
        p("password");
        String password = scnr.nextLine().strip();
        p("first");
        String first = scnr.nextLine().strip();
        p("last");
        String last = scnr.nextLine().strip();
        p("biography");
        String biography = scnr.nextLine();
        p("interests");
        String interests = scnr.nextLine();

        // save to database
        Database.registerUser(userid, email, password, first, last);

        // save to user
        User u = new User(userid, first, last);
        u.setBiography(biography);
        u.setInterests(interests);
        u.saveDatatoDisk();
    }

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("1. Users");
            System.out.println("2. Events");
            System.out.println("3. Items");
            System.out.println("4. Exit");
            // poll user input
            int choice = 0;
            try {
                choice = scnr.nextInt();
                scnr.nextLine();
                switch (choice)  {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        createEvent();
                        break;
                    case 3:
                        createItem();
                        break;
                    case 4:
                        run = false;
                        break;
                    default:
                        System.out.println("Please enter a valid choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                scnr.nextLine(); // Clear the bad input from the buffer
            }
        }
    }
}
