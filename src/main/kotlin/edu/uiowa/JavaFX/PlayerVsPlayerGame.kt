package edu.uiowa.JavaFX

import edu.uiowa.Board.Board
import edu.uiowa.Board.Piece
import edu.uiowa.Board.GeneralBoard
import edu.uiowa.Board.State
import edu.uiowa.JavaFX.BoardBuilder.Companion.endMenu
import edu.uiowa.JavaFX.BoardBuilder.Companion.paintLines
import edu.uiowa.JavaFX.BoardBuilder.Companion.placePiece
import edu.uiowa.Player.Player
import edu.uiowa.Player.User
import javafx.animation.TranslateTransition
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Label
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import javafx.scene.input.KeyCode
import javafx.util.Duration

// JavaFx class for PlayerVsPlayer game
class PlayerVsPlayerGame: Stage {

    var board: Board = GeneralBoard(3, User("1", Piece.X), User("2", Piece.O))
    val cellSize = 150.0
    var size = 3
    var W: Double = size * cellSize
    var H: Double = size * cellSize

    val lineWidth = 4.0
    val padding = 10.0

    // Constructs general layout of the game by calling BoardBuilder methods
    constructor(size: Int, p1Name: String, p1Color: String, p2Name: String, p2Color: String) : super() {
        this.size = size
        W = size * cellSize
        H = size * cellSize

        val p1NameFixed = if (p1Name.length < 1) "Player 1" else p1Name
        val p2NameFixed = if (p2Name.length < 1) "Player 2" else p2Name

        if (size == 4) {
            board = GeneralBoard(4, User(p1NameFixed, Piece.X), User(p2NameFixed, Piece.O))
        }
        else if (size == 5) {
            board = GeneralBoard(5, User(p1NameFixed, Piece.X), User(p2NameFixed, Piece.O))
        }
        board.Player1.name = p1NameFixed
        board.Player2.name = p2NameFixed
        board.Player1.color = Player.getColor(p1Color)
        board.Player2.color = Player.getColor(p2Color)

        this.title = "Game"
        val root = Group()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene
        val display = Canvas(W, H)
        val g: GraphicsContext = display.graphicsContext2D
        paintLines(g, cellSize, size, lineWidth, H, W)
        displayFirst(root)

        display.addEventHandler(MouseEvent.MOUSE_PRESSED) {event ->
            if (event.button == MouseButton.PRIMARY && board.state == State.Running) {
                val col = (event.x/cellSize).toInt()
                val row = (event.y/cellSize).toInt()

                if (board.updateCell(col, row)) {
                    placePiece(g, col, row, board, lineWidth, cellSize, padding)
                    board.checkIfWinner()
                    board.flipPlayer()
                    if (board.state != State.Running) {
                        if (board.state == State.Draw) {
                            endMenu(root, this, board, TypeOfGame.Player, Difficulty.None, p1Color, p2Color)
                        }
                        else {
                            endMenu(root, this, board, TypeOfGame.Player, Difficulty.None, p1Color, p2Color)
                        }
                    }
                }
            }
        }

        scene.addEventHandler(KeyEvent.ANY) { key ->
            if (key.code === KeyCode.ESCAPE) {
                PauseMenu(this)
            }
        }

        root.children.add(display)

        this.show()
    }

    // Displays the first player to move with scrolling text.
    // Not necessary for computer game because it can be inferred.
    fun displayFirst(root: Group) {
        val label = Label()
        label.text = "${board.turn.name} First"
        label.id = "title"
        label.style = "-fx-text-fill: orange;"
        label.translateX = -400.0
        val translation = TranslateTransition()
        translation.duration = Duration.seconds(6.0)
        translation.toX = cellSize * size + 300
        translation.node = label
        translation.play()
        root.children.add(label)
    }

}