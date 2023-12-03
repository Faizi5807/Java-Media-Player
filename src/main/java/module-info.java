module com.example.mediaplayer2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.media;
    requires org.jetbrains.annotations;

    opens com.example.mediaplayer2 to javafx.fxml;
    exports com.example.mediaplayer2;
}