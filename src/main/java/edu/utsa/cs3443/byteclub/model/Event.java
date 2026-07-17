package edu.utsa.cs3443.byteclub;

public class Event {

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public int getNumAttendees() {
        return numAttendees;
    }

    public void setNumAttendees(int numAttendees) {
        this.numAttendees = numAttendees;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    private String courseName;
    private String dateAndTime; //use Java time class
    private String location;
    private String instructor;
    private int numSeats;
    private int numAttendees;
    private boolean isOpen;

    Event(String courseName, String dateAndTime, String location, String instructor, int numSeats, int numAttendees, boolean isOpen) {
        this.courseName = courseName;
        this.dateAndTime = dateAndTime;
        this.location = location;
        this.instructor = instructor; //actually I think we can have this just be the name from instructor/person class     instructor.getName
        this.numSeats = numSeats;
        this.numAttendees = numAttendees;
        this.isOpen = isOpen;

    }



    //method that if numAttendees >= numSeats, display a message saying cannot join class


}
