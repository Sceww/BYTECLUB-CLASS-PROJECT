package edu.utsa.cs3443.byteclub.model.Person;

import edu.utsa.cs3443.byteclub.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User is the class the user will be interfacing with most directly
 */
public class User extends Person {
    // Calendar?
    private ArrayList<String> interestList;
    private String biography;
    private ArrayList<Item> shoppingCart;
    private String email;
    private ArrayList<Event> classList;

    public User(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        biography = "";
        classList = new ArrayList<>();
        shoppingCart = new ArrayList<>();
        interestList = new ArrayList<>();
    }

    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setInterests(String m) {
        interestList.clear();
        Scanner scnr = new Scanner(m);
        scnr.useDelimiter(",");
        while (scnr.hasNext()) {
            interestList.add(scnr.next().strip());
        }
    }

    public void readCart() {
        try (Scanner scnr = new Scanner(new File(String.format("data/userData/%d/shoppingCart.txt", getId())))) {
            while (scnr.hasNextInt()) {
                Item i = Item.queryItem(scnr.nextInt());
                if (i != null) {
                    shoppingCart.add(i);
                }
            }
        } catch (IOException e) {

        }
    }

    public void readEvents() {
        try (Scanner scnr = new Scanner(new File(String.format("data/userData/%d/events.txt", getId())))) {
            while (scnr.hasNextInt()) {
                Event e = Event.queryEvent(scnr.nextInt());
                if (e != null) {
                    classList.add(e);
                }
            }
        } catch (IOException e) {

        }
    }
    public void readInterests() {
        try (Scanner scnr = new Scanner(new File(String.format("data/userData/%d/interests.txt", getId())))) {
            setInterests(scnr.nextLine());
        } catch (IOException e) {

        }
    }
    public void readBio() {
        try (Scanner scnr = new Scanner(new File(String.format("data/userData/%d/biography.txt", getId())))) {
            biography = scnr.nextLine();
        } catch (IOException e) {

        }
    }

    public ArrayList<String> getInterestList() {
        return interestList;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public ArrayList<Event> getClassList() {
        return classList;
    }

    public void saveDatatoDisk() {
        // user data is saved under data/userData/<id>/
        String path = String.format("data/userData/%d/",getId());
        File p = new File(path);
        if (!p.exists()) {
            p.mkdirs();
        }
        
        try (FileWriter bio = new FileWriter(path + "biography.txt")) {
            bio.write(biography);
        } catch (IOException e) {
            throw new RuntimeException("DISASTROUS ERROR OCCURRED! " + e.getMessage());
        }
        try (FileWriter events = new FileWriter(path + "events.txt")) {
            events.write("eventID\n");
            for (Event event : classList) {
                events.write(String.format("%d\n", event.getEventID()));
            }
        } catch (IOException e) {
            throw new RuntimeException("DISASTROUS ERROR OCCURRED! " + e.getMessage());
        }
        try (FileWriter interests = new FileWriter(path + "interests.txt")) {
            for (int i = 0; i < interestList.size(); i++) {
                interests.write(interestList.get(i));
                if ( (i != (interestList.size() - 1)) ) {
                    interests.write(",");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("DISASTROUS ERROR OCCURRED! " + e.getMessage());
        }
        try (FileWriter cart = new FileWriter(path + "shoppingCart.txt")) {
            cart.write("itemID\n");
            for (Item item : shoppingCart) {
                cart.write(String.format("%d\n", item.getId()));
            }
        } catch (IOException e) {
            throw new RuntimeException("DISASTROUS ERROR OCCURRED! " + e.getMessage());
        }
    }

    public void addToCart(Item item) {
        shoppingCart.add(item);
    }

    public void joinEvent(Event event) {
        classList.add(event);
    }

    public static User queryUser(int id) {
        // first, scan database for user id
        try (Scanner scnr = new Scanner(new File("data/database.csv"))) {
            scnr.nextLine();
            while (scnr.hasNextLine()) {
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                if (line.nextInt() == id) {
                    // we found our user
                    User u = new User(id, line.next(), line.next());
                    u.readBio();
                    u.readCart();
                    u.readEvents();
                    u.readInterests();
                    return u;
                }
            }
        } catch (IOException e) { }
        return null;
    }
}
