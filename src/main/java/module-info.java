module edu.utsa.cs3443.byteclub {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.byteclub to javafx.fxml;
    exports edu.utsa.cs3443.byteclub;
}