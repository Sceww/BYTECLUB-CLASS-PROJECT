package edu.utsa.cs3443.byteclub.model.Person;

import edu.utsa.cs3443.byteclub.Event;
import edu.utsa.cs3443.byteclub.Item;

import java.util.ArrayList;

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

    private void readBiography() {
        // TODO
        return;
    }

    private ArrayList<String> readInterests() {
        //TODO
        // reads from data/userData/this.id/interests.txt
        return null;
    }
    private ArrayList<Event> readEvents() {
        //TODO
        return null;
    }

    private void saveDatatoUserDatabase() {
        //TODO
    }


    //TODO: figure out what about the user is allowed to change
}
