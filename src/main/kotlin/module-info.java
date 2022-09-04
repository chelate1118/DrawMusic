module com.draw.drawmusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires gov.nist.math.jama;
    requires annotations;
    requires org.testng;
    requires org.slf4j.simple;
    requires org.slf4j;

    opens com.draw.drawmusic.controllers to javafx.fxml;
    exports com.draw.drawmusic;
    exports com.draw.drawmusic.note;
    exports com.draw.drawmusic.controllers;
    exports com.draw.drawmusic.track;
    exports com.draw.drawmusic.track_properties;
    exports com.draw.drawmusic.tools;
    exports com.draw.drawmusic.note_properties;
    exports com.draw.drawmusic.editor;
    exports com.draw.drawmusic.history;
}