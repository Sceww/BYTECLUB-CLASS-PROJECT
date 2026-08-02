package edu.utsa.cs3443.byteclub.view;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EventCard extends HBox {
    private boolean userView;

    private Event event;
    private Label date;
    private Label host;
    private Label title;
    private Label location;
    private Label desc;
    private Label numPeople;
    private Button signUp;

    private void onRemoveClick() {
        ByteClubApplication.getCurrentUser().getClassList().remove(event);
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
        // remove self
        ((VBox) this.getParent()).getChildren().remove(this);
    }

    private void onSignupClick() {
        // add event to user
        ByteClubApplication.getCurrentUser().joinEvent(event);
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
    }

    public EventCard(Event e, boolean userView) {
        this.event = e;
        this.userView = userView;

        this.setAlignment(Pos.CENTER);
        date = new Label();
        host = new Label();
        title = new Label();
        location = new Label();
        desc = new Label();
        numPeople = new Label();
        signUp = new Button();

        // fill in labels with data
        if (e != null) {
            date.setText(e.getDateAndTime());
            host.setText(e.getInstructorName());
            title.setText(e.getCourseName());
            location.setText(e.getLocation());
            desc.setText(e.getCourseName());
            numPeople.setText(String.format("%d/%d", e.getNumAttendees(), e.getNumSeats()));
            if (userView) {
                signUp.setOnAction(event -> onRemoveClick());
                signUp.setText("remove");
            } else {
                signUp.setOnAction(event -> onSignupClick());
                signUp.setText(e.canJoin() ? "Sign Up" : "Full");
                if (!e.canJoin()) {
                    signUp.setDisable(true);
                }
            }
        }
        // add children
        this.getChildren().addAll(date, host);
        // create VBox
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(title, location, desc, numPeople);
        this.getChildren().addAll(v, signUp);
    }
    
    public EventCard(int id) {
        this(Event.queryEvent(id), false);
    }
}