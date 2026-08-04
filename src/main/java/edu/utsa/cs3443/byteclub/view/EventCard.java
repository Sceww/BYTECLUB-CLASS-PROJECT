package edu.utsa.cs3443.byteclub.view;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class EventCard extends HBox {
    private static final double INFO_SPACING = 2;
    private static final double SIDE_WIDTH = 92;

    private boolean userView;

    private Event event;
    private Label title;
    private Label subline;
    private Label host;
    private Label numPeople;
    private Button signUp;

    private void onRemoveClick() {
        ByteClubApplication.getCurrentUser().getClassList().remove(event);
        event.decrementAttendees();
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
        // remove self
        ((VBox) this.getParent()).getChildren().remove(this);
    }

    private void onSignupClick() {
        // add event to user, but only if they haven't already joined
        boolean joined = ByteClubApplication.getCurrentUser().joinEvent(event);
        if (!joined) {
            // already signed up, don't double count attendance
            signUp.setText("Already Signed Up");
            signUp.setDisable(true);
            return;
        }
        event.incrementAttendees();
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
        numPeople.setText(String.format("%d/%d seats", event.getNumAttendees(), event.getNumSeats()));
        if (!event.canJoin()) {
            signUp.setText("Full");
            signUp.setDisable(true);
        }
    }

    public EventCard(Event e, boolean userView) {
        this.event = e;
        this.userView = userView;

        // row-level layout: a fixed height "card" so every row lines up the same way
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(12);
        this.setPadding(new Insets(10, 14, 10, 14));
        this.setStyle("-fx-border-color: #dddddd; -fx-border-width: 0 0 1 0;");
        this.setPrefWidth(384);
        this.setMinHeight(64);

        // left side: title + a details subline + instructor, always left-aligned
        title = new Label();
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        title.setWrapText(true);

        subline = new Label();
        subline.setStyle("-fx-font-size: 11; -fx-text-fill: #666666;");
        subline.setWrapText(true);

        host = new Label();
        host.setStyle("-fx-font-size: 11; -fx-text-fill: #666666;");
        host.setWrapText(true);

        VBox info = new VBox(INFO_SPACING);
        info.setAlignment(Pos.CENTER_LEFT);
        info.getChildren().addAll(title, subline, host);
        HBox.setHgrow(info, Priority.ALWAYS);

        // right side: fixed-width column so the seat count and button always land
        // in the same place no matter how long the course title/date/location are
        numPeople = new Label();
        numPeople.setPrefWidth(SIDE_WIDTH);
        numPeople.setAlignment(Pos.CENTER);
        numPeople.setStyle("-fx-font-size: 11;");

        signUp = new Button();
        signUp.setPrefWidth(SIDE_WIDTH);

        VBox side = new VBox(6);
        side.setAlignment(Pos.CENTER);
        side.setPrefWidth(SIDE_WIDTH);
        side.getChildren().addAll(numPeople, signUp);

        // fill in labels with data
        if (e != null) {
            title.setText(e.getCourseName());
            subline.setText(String.format("%s  •  %s", e.getDateAndTime(), e.getLocation()));
            host.setText("Instructor: " + e.getInstructorName());
            numPeople.setText(String.format("%d/%d seats", e.getNumAttendees(), e.getNumSeats()));

            if (userView) {
                signUp.setOnAction(event -> onRemoveClick());
                signUp.setText("Remove");
            } else {
                signUp.setOnAction(event -> onSignupClick());
                boolean alreadyJoined = ByteClubApplication.getCurrentUser() != null
                        && ByteClubApplication.getCurrentUser().hasJoined(e);
                if (alreadyJoined) {
                    signUp.setText("Already Signed Up");
                    signUp.setDisable(true);
                } else {
                    signUp.setText(e.canJoin() ? "Sign Up" : "Full");
                    if (!e.canJoin()) {
                        signUp.setDisable(true);
                    }
                }
            }
        }

        this.getChildren().addAll(info, side);
    }

    public EventCard(int id) {
        this(Event.queryEvent(id), false);
    }
}
