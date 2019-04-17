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

// JavaFx class to display the controls page
class Controls: Stage {
    constructor() : super() {
        this.title = "GameType"
        val root = StackPane()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene

        val title = Label()
        title.text = "Controls"
        title.id = "title"
        title.textAlignment = TextAlignment.CENTER

        val controlsLabel = Label()
        controlsLabel.text = "Single Mouse Click: Select a square to place your piece\n\n" +
                "Esc: Pause menu (Only available during a game)\n\n" +
                "Mute: Mutes in-game music, available via the pause menu or\nmain menu"
        controlsLabel.textAlignment = TextAlignment.LEFT
        controlsLabel.id = "rulesOrControls"
        controlsLabel.style = "-fx-font-size: 16px; -fx-text-fill: black;"

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
        root.children.add(controlsLabel)
        root.children.add(backButton)

        this.show()
    }
}