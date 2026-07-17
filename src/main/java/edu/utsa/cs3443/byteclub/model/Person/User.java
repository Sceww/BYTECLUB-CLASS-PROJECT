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
    private boolean isInstructor; // TODO: deliberate on whether instructor mode should be a thing to be implemented
    private ArrayList<Event> classList;

    public User(String firstName, String lastName) {
        super(firstName, lastName);
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

    private ArrayList<String> readInterests(String line) {
        //TODO
        return null;
    }
    private ArrayList<Event> readEvents(String line) {
        //TODO
        return null;
    }
    private ArrayList<Item> readShoppingCart(String line) {
        //TODO: IMPLEMENT ME!
        return null;
    }

    private void saveDatatoUserDatabase() {
        //TODO
    }


    //TODO: figure out what about the user is allowed to change
}
