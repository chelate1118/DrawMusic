module com.draw.drawmusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.draw.drawmusic to javafx.fxml;
    exports com.draw.drawmusic;
}