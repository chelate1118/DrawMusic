package com.draw.drawmusic.editor;

import com.draw.drawmusic.controllers.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SplineGraphEditor implements Initializable {
    @FXML private static VBox editorTopSide;
    @FXML public TitledPane titledPane;
    @FXML public ScrollPane scrollPane;
    @FXML public Group group;
    @FXML public LineChart<Double, Double> lineChart;

    private static class GraphProperty {
        String textInTitledPane;

        GraphProperty(String textInTitledPane) {
            this.textInTitledPane = textInTitledPane;
        }
    }

    private static final ArrayList<GraphProperty> roleOfGraph = new ArrayList<>(Arrays.asList(
            new GraphProperty("Tempo"),
            new GraphProperty("Dynamic")
    ));

    public static void init(VBox _editorTopSide) {
        editorTopSide = _editorTopSide;

        while(!roleOfGraph.isEmpty()) {
            FXMLController.Companion.fxmlLoad("spline-graph-editor.fxml");
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editorTopSide.getChildren().add(titledPane);

        fitScrollPaneSize();
        setProperties();
    }

    private void fitScrollPaneSize() {
        scrollPane.setFitToWidth(true);
    }

    private void setProperties() {
        GraphProperty graphProperty = roleOfGraph.remove(0);

        titledPane.setText(graphProperty.textInTitledPane);
    }

    private void hideAxis() {
        lineChart.getXAxis().setOpacity(0.0);
        lineChart.getYAxis().setOpacity(0.0);
    }
}
