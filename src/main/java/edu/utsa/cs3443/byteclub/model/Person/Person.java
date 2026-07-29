package edu.utsa.cs3443.byteclub.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Person queryPerson(int id) {
        try (Scanner scnr = new Scanner(new File("data/database.csv"))) {
            scnr.nextLine();
            while (scnr.hasNextLine()) {
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                if (line.nextInt() == id) {
                    // we found our user
                    return new Person(id, line.next(), line.next());
                }
            }
        } catch (IOException e) { }
        return null;
    }
}
