package com.draw.drawmusic.editor;

import com.draw.drawmusic.controllers.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SplineGraphEditor implements Initializable {
    @FXML private static VBox editorTopSide;
    @FXML public TitledPane titledPane;

    public static void init(VBox _editorTopSide) {
        editorTopSide = _editorTopSide;
        FXMLController.Companion.fxmlLoad("spline-graph-editor.fxml");
        FXMLController.Companion.fxmlLoad("spline-graph-editor.fxml");
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editorTopSide.getChildren().add(titledPane);
    }

    private LineChart<Double, Double> lineChart;

    private void hideAxis() {
        lineChart.getXAxis().setOpacity(0.0);
        lineChart.getYAxis().setOpacity(0.0);
    }
}
