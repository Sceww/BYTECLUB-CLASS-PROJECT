package edu.utsa.cs3443.byteclub.model;

import edu.utsa.cs3443.byteclub.model.Person.User;

public class Event {

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName;}

    public String getDateAndTime() { return dateAndTime; }
    public void setDateAndTime(String dateAndTime) { this.dateAndTime = dateAndTime; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public User getInstructor() { return instructor; }
    public void setInstructor(User instructor) { this.instructor = instructor; }

    public String getInstructorName() { return instructor.getFirstName() + " " + instructor.getLastName(); }

    public int getNumSeats() { return numSeats; }
    public void setNumSeats(int numSeats) { this.numSeats = numSeats; }

    public int getNumAttendees() { return numAttendees; }
    public void setNumAttendees(int numAttendees) { this.numAttendees = numAttendees; }

    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }

    private String courseName;
    private String dateAndTime; //use Java time class
    private String location;
    private User instructor;
    private int numSeats;
    private int numAttendees;
    private boolean isOpen;

    public Event(String courseName, String dateAndTime, String location, User instructor, int numSeats, int numAttendees, boolean isOpen) {
        this.courseName = courseName;
        this.dateAndTime = dateAndTime;
        this.location = location;
        this.instructor = instructor; //actually I think we can have this just be the name from instructor/person class     instructor.getName
        this.numSeats = numSeats;
        this.numAttendees = numAttendees;
        this.isOpen = isOpen;

    }


    public boolean canJoin() {
        return isOpen && numAttendees < numSeats;
    }



}
