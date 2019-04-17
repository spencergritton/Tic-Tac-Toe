package edu.uiowa.JavaFX

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import javafx.scene.control.*
import javafx.scene.paint.Color

// JavaFx menu to pick settings of the game
// Player names, piece colors, game size, difficulty.
class LocalSettings: Stage {
    constructor(gType: TypeOfGame) : super() {
        this.title = "GameType"
        val root = StackPane()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene

        val title = Label()
        title.text = "Tic Tac Toe"
        title.id = "title"
        title.textAlignment = TextAlignment.CENTER

        val settingsHBox = HBox()
        settingsHBox.alignment = Pos.CENTER
        val p1VBox = VBox()
        p1VBox.alignment = Pos.CENTER
        p1VBox.style = "-fx-padding: 10px;"
        val p2VBox = VBox()
        p2VBox.alignment = Pos.CENTER
        p2VBox.style = "-fx-padding: 10px;"

        //P1
        val p1NameLabel = Label()
        p1NameLabel.text = "Player 1 Name"
        p1NameLabel.translateY = -20.0
        val p1Name = TextField()
        p1Name.translateY = -20.0
        val p1ColorLabel = Label()
        p1ColorLabel.text = "Color"
        val p1Color = ComboBox<String>()
        p1Color.items.addAll("BLACK", "BLUE", "RED", "GREEN", "YELLOW", "ORANGE")
        p1Color.value = "BLACK"
        p1VBox.children.add(p1NameLabel)
        p1VBox.children.add(p1Name)
        p1VBox.children.add(p1ColorLabel)
        p1VBox.children.add(p1Color)
        p1Color.setCellFactory{ _ ->
            object : ListCell<String>() {

                // Method to fill in drop down menus with colors
                override fun updateItem(item: String?, empty: Boolean) {
                    super.updateItem(item, empty)

                    when {
                        item.equals("RED") -> {
                            textFill = Color.RED
                            text = "RED"
                        }
                        item.equals("BLUE") -> {
                            textFill = Color.BLUE
                            text = "BLUE"
                        }
                        item.equals("GREEN") -> {
                            textFill = Color.GREEN
                            text = "GREEN"
                        }
                        item.equals("YELLOW") -> {
                            textFill = Color.YELLOW
                            text = "YELLOW"
                        }
                        item.equals("ORANGE") -> {
                            textFill = Color.ORANGE
                            text = "ORANGE"
                        }
                        else -> {
                            textFill = Color.BLACK
                            text = "BLACK"
                        }
                    }
                }

            }
        }
        p1Color.setButtonCell(p1Color.getCellFactory().call(null))

        //P2
        val p2NameLabel = Label()
        p2NameLabel.text = "Player 2 Name"
        if (gType.equals(TypeOfGame.Computer)) { p2NameLabel.text = "Computer Name" }
        p2NameLabel.translateY = -20.0
        val p2Name = TextField()
        p2Name.translateY = -20.0
        val p2ColorLabel = Label()
        p2ColorLabel.text = "Color"
        val p2Color = ComboBox<String>()
        p2Color.items.addAll("BLACK", "BLUE", "RED", "GREEN", "YELLOW", "ORANGE")
        p2Color.value = "BLACK"
        p2VBox.children.add(p2NameLabel)
        p2VBox.children.add(p2Name)
        p2VBox.children.add(p2ColorLabel)
        p2VBox.children.add(p2Color)
        p2Color.setCellFactory{ _ ->
            object : ListCell<String>() {

                // Method to fill in drop down menus with colors
                override fun updateItem(item: String?, empty: Boolean) {
                    super.updateItem(item, empty)

                    when {
                        item.equals("RED") -> {
                            textFill = Color.RED
                            text = "RED"
                        }
                        item.equals("BLUE") -> {
                            textFill = Color.BLUE
                            text = "BLUE"
                        }
                        item.equals("GREEN") -> {
                            textFill = Color.GREEN
                            text = "GREEN"
                        }
                        item.equals("YELLOW") -> {
                            textFill = Color.YELLOW
                            text = "YELLOW"
                        }
                        item.equals("ORANGE") -> {
                            textFill = Color.ORANGE
                            text = "ORANGE"
                        }
                        else -> {
                            textFill = Color.BLACK
                            text = "BLACK"
                        }
                    }
                }

            }
        }
        p2Color.setButtonCell(p2Color.getCellFactory().call(null))

        val boardSizeLabel = Label()
        boardSizeLabel.text = "Board Size"
        boardSizeLabel.translateY = 62.0
        val boardSize = ComboBox<Int>()
        boardSize.items.addAll(3, 4, 5)
        boardSize.value = 3
        boardSize.translateY = 85.0

        val difficultyLabel = Label()
        difficultyLabel.text = "Difficulty"
        difficultyLabel.translateY = 115.0
        val difficulty = ComboBox<String>()
        difficulty.items.addAll("Easy", "Medium", "Hard")
        difficulty.value = "Medium"
        difficulty.translateY = 138.0

        val play = SoundButton()
        play.text = "Play"
        play.id = "Play"
        play.textAlignment = TextAlignment.CENTER
        play.translateY = if (gType == TypeOfGame.Player) -50.0 else 0.0
        play.onAction = EventHandler {
            this.hide()
            if (gType.equals(TypeOfGame.Computer)) {
                PlayerVsComputerGame(boardSize.value,
                        p1Name.text,
                        p1Color.value,
                        p2Name.text,
                        p2Color.value,
                        Difficulty.valueOf(difficulty.value))
            } else {
                PlayerVsPlayerGame(boardSize.value, p1Name.text, p1Color.value, p2Name.text, p2Color.value)
            }
        }

        settingsHBox.children.add(p1VBox)
        settingsHBox.children.add(p2VBox)

        val backButton = SoundButton()
        backButton.text = "Back"
        backButton.textAlignment = TextAlignment.CENTER
        backButton.onAction = EventHandler {
            this.hide()
            GameType()
        }

        StackPane.setAlignment(title, Pos.TOP_CENTER)
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER)
        StackPane.setAlignment(settingsHBox, Pos.CENTER)
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT)

        val display = Canvas(450.0, 450.0)
        root.children.add(display)
        root.children.add(title)
        root.children.add(settingsHBox)
        root.children.add(boardSizeLabel)
        root.children.add(boardSize)
        root.children.add(play)
        root.children.add(backButton)
        if (gType == TypeOfGame.Computer) {
            root.children.add(difficultyLabel)
            root.children.add(difficulty)
        }

        this.show()
    }
}