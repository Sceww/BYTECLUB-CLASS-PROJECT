package edu.utsa.cs3443.byteclub.model;

import edu.utsa.cs3443.byteclub.model.Person.Person;
import edu.utsa.cs3443.byteclub.model.Person.User;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Event {

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName;}

    public String getDateAndTime() { return dateAndTime; }
    public void setDateAndTime(String dateAndTime) { this.dateAndTime = dateAndTime; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Person getInstructor() { return instructor; }
    public void setInstructor(User instructor) { this.instructor = instructor; }

    public String getInstructorName() { return instructor.getFirstName() + " " + instructor.getLastName(); }

    public int getNumSeats() { return numSeats; }
    public void setNumSeats(int numSeats) { this.numSeats = numSeats; }

    public int getNumAttendees() { return numAttendees; }
    public void setNumAttendees(int numAttendees) { this.numAttendees = numAttendees; }

    private final int eventID;
    private String courseName;
    private String dateAndTime; //use Java time class
    private String location;
    private Person instructor;
    private int numSeats;
    private int numAttendees;

    public Event(int eventID, String courseName, String dateAndTime, String location, Person instructor, int numSeats, int numAttendees) {
        this.eventID = eventID;
        this.courseName = courseName;
        this.dateAndTime = dateAndTime;
        this.location = location;
        this.instructor = instructor; //actually I think we can have this just be the name from instructor/person class     instructor.getName
        this.numSeats = numSeats;
        this.numAttendees = numAttendees;
    }

    public static Event queryEvent(int eventID) {
        // scan event csv
        try (Scanner scnr = new Scanner(new File("data/appData/events.csv"));) {
            scnr.nextLine();
            while (scnr.hasNextLine()) {
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                if (line.nextInt() == eventID) {
                    // we found our event;
                    Person host = Person.queryPerson(line.nextInt());
                    String loc = line.next();
                    String title = line.next();
                    String desc = line.next();
                    String date = line.next();
                    int numPeople = line.nextInt();
                    int numSeats = line.nextInt();
                    return new Event(eventID, title, date, loc, host, numSeats, numPeople);
                }
            }
        } catch (IOException e) {}
        return null;
    }

    public int getEventID() {
        return eventID;
    }

    public boolean canJoin() {
        return numAttendees < numSeats;
    }
}
