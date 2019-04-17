package edu.uiowa.JavaFX

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.scene.text.TextAlignment
import javafx.stage.Stage

// JavaFx class for rules page
class Rules: Stage {
    constructor() : super() {
        this.title = "GameType"
        val root = StackPane()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene

        val title = Label()
        title.text = "Rules"
        title.id = "title"
        title.textAlignment = TextAlignment.CENTER

        val rulesLabel = Label()
        rulesLabel.text = "1. All boards are: 3x3, 4x4, or 5x5\n\n" +
                "2. The player who moves first is randomized each game\n\n" +
                "3. The players will be given the chance to play each\nother again after each game\n\n" +
                "4. No matter the game board the user must connect\nthree in a row to win\n\n" +
                "5. Have fun!"
        rulesLabel.textAlignment = TextAlignment.LEFT
        rulesLabel.id = "rulesOrControls"
        rulesLabel.style = "-fx-font-size: 16px; -fx-text-fill: black;"

        val backButton = SoundButton()
        backButton.text = "Back"
        backButton.textAlignment = TextAlignment.CENTER
        backButton.onAction = EventHandler {
            this.hide()
            TitlePage()
        }

        StackPane.setAlignment(title, Pos.TOP_CENTER)
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT)

        val display = Canvas(450.0, 450.0)
        root.children.add(title)
        root.children.add(display)
        root.children.add(rulesLabel)
        root.children.add(backButton)

        this.show()
    }
}