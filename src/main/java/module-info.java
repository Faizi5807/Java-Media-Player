module com.example.mediaplayer2 {
    requires javafx.controls;
    requires javafx.fxml;
requires javafx.media;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.mediaplayer2 to javafx.fxml;
    exports com.example.mediaplayer2;
}