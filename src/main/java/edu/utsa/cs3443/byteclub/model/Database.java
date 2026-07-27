package edu.utsa.cs3443.byteclub.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {
    public static boolean checkLogin(String email, String password) {
        try (Scanner scnr = new Scanner(new File("data/database.csv"));) {

        } catch (IOException e) {

        }

        return false;
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

    public static void registerUser(int id, String email, String password, String first, String last) {
        try (FileWriter fw = new FileWriter("data/database.csv", true);) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("%d,%s,%s,%s,%s\n",id,email,password,first,last));
            bw.close();
        } catch (IOException ignored) {}
    }
}
