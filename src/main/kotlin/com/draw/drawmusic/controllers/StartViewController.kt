package com.draw.drawmusic.controllers

import com.draw.drawmusic.MainApplicationManager
import com.draw.drawmusic.WindowSize
import javafx.fxml.FXML

class StartViewController {
    @FXML
    fun buttonClicked() {
        MainApplicationManager.changeStage("main-view.fxml", "dark_mode.css", "Draw Music!",
                                    "icon.png", WindowSize.IS_MAXIMIZED)
    }
}