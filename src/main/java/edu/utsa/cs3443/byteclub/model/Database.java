package edu.utsa.cs3443.byteclub.model;

import edu.utsa.cs3443.byteclub.model.Person.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {
    public static int checkLogin(String email, String password) {
        try (Scanner scnr = new Scanner(new File("data/database.csv"));) {
            scnr.nextLine();
            while (scnr.hasNext()) {
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                int id = line.nextInt();
                if (line.next().equalsIgnoreCase(email)) {
                    if (line.next().equals(password)) {
                        return id;
                    }
                }
            }
        } catch (IOException e) {}

        return -1;
    }

    public static void registerItem(int itemId, String itemName, double price) {
        try (FileWriter fw = new FileWriter("data/appData/items.csv", true);) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%d,%s,%.2f\n",itemId, itemName, price));
            bw.close();
        } catch (IOException ignored) {}
    }

    public static void registerEvent(int eventID, int hostID, String location, String title, String desc, String date, int numPeople, int numSeats) {
        try (FileWriter fw = new FileWriter("data/appData/events.csv", true);) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%d,%d,%s,%s,%s,%s,%d,%d\n",eventID,hostID,location,title,desc,date,numPeople,numSeats));
            bw.close();
        } catch (IOException ignored) {}
    }

    public static void registerUser(String email, String password, String first, String last) {
        try (Scanner scnr = new Scanner(new File("data/database.csv"));) {
            scnr.useDelimiter(",");
            scnr.nextLine();
            int id = 100;
            while (scnr.hasNext()) {
                id = scnr.nextInt();
                scnr.nextLine();
            }
            registerUser(id+1, email, password, first, last);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void registerUser(int id, String email, String password, String first, String last) {
        try (FileWriter fw = new FileWriter("data/database.csv", true);) {
            // appends to database.csv
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%d,%s,%s,%s,%s\n",id,email,password,first,last));
            bw.close();
            // create userData folder and default data (biography.txt, events.txt, interests.txt, shoppingCart.txt) in userData/<id> files
            File p = new File(String.format("data/userData/%d/",id));
            p.mkdirs();

            FileWriter bio = new FileWriter(p + "/biography.txt");
            bio.write("");
            bio.close();

            FileWriter events = new FileWriter(p + "/events.txt");
            events.write("eventID\n");
            events.close();

            FileWriter interests = new FileWriter(p + "/interests.txt");
            interests.write("");
            interests.close();

            FileWriter cart = new FileWriter(p + "/shoppingCart.txt");
            cart.write("itemID\n");
            cart.close();

        } catch (IOException ignored) {}
    }
}
