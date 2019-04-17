package edu.uiowa.JavaFX

import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.stage.Stage

// Empty shell screen for the start of the app so all other screens won't inherit Application
class Shell: Application() {
    override fun start(primaryStage: Stage) {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA)
        primaryStage.title = "Tic Tac Title"
        val root = StackPane()
        val display = Canvas(450.0, 450.0)
        root.children.add(display)
        primaryStage.show()
        primaryStage.hide()
        TitlePage()
    }

}