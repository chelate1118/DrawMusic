module com.draw.drawmusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens com.draw.drawmusic.controllers to javafx.fxml;
    exports com.draw.drawmusic;
    exports com.draw.drawmusic.controllers;
    exports com.draw.drawmusic.track;
    exports com.draw.drawmusic.properties;
    exports com.draw.drawmusic.tools;
}