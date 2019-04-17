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

// Settings menu to choose game type a.k.a. Player vs Player or Player vs Computer
class GameType: Stage {
    constructor() : super() {
        this.title = "GameType"
        val root = StackPane()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene

        val playerVsPlayerButton = SoundButton()
        playerVsPlayerButton.text = "Player Vs Player"
        playerVsPlayerButton.id = "SinglePlayerButton"
        playerVsPlayerButton.textAlignment = TextAlignment.CENTER
        playerVsPlayerButton.onAction = EventHandler {
            this.hide()
            LocalSettings(TypeOfGame.Player)
        }

        val playerVsComputerButton = SoundButton()
        playerVsComputerButton.text = "Player Vs Computer"
        playerVsComputerButton.textAlignment = TextAlignment.CENTER
        playerVsComputerButton.translateY = 50.0
        playerVsComputerButton.onAction = EventHandler {
            this.hide()
            LocalSettings(TypeOfGame.Computer)
        }

        val title = Label()
        title.text = "Tic Tac Toe"
        title.id = "title"
        title.textAlignment = TextAlignment.CENTER

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
        root.children.add(display)
        root.children.add(playerVsPlayerButton)
        root.children.add(playerVsComputerButton)
        root.children.add(title)
        root.children.add(backButton)

        this.show()
    }
}