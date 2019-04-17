package edu.uiowa.JavaFX

import edu.uiowa.Board.Board
import edu.uiowa.Board.Piece
import edu.uiowa.Board.GeneralBoard
import edu.uiowa.Board.State
import edu.uiowa.JavaFX.BoardBuilder.Companion.paintLines
import edu.uiowa.Player.Computer
import edu.uiowa.Player.Computer.Companion.easyMove
import edu.uiowa.Player.Computer.Companion.hardMove
import edu.uiowa.Player.Computer.Companion.mediumMove
import edu.uiowa.Player.Player
import edu.uiowa.Player.User
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage

// JavaFX class for displaying a Player vs Computer game
class PlayerVsComputerGame: Stage {

    var board: Board = GeneralBoard(3, User("1", Piece.X), Computer("2", Piece.O))
    val cellSize = 150.0
    var size = 3
    var W: Double = size * cellSize
    var H: Double = size * cellSize

    val lineWidth = 4.0
    val padding = 10.0

    // Constructs general layout of the game by calling BoardBuilder methods
    constructor(size: Int,
                p1Name: String,
                p1Color: String,
                CName: String,
                CColor: String,
                difficulty: Difficulty) : super() {

        this.size = size
        W = size * cellSize
        H = size * cellSize

        val p1NameFixed = if (p1Name.length < 1) "Player 1" else p1Name
        val CNameFixed = if (CName.length < 1) "Computer" else CName

        if (size == 4) {
            board = GeneralBoard(4, User(p1NameFixed, Piece.X), Computer(CNameFixed, Piece.O))
        }
        else if (size == 5) {
            board = GeneralBoard(5, User(p1NameFixed, Piece.X), Computer(CNameFixed, Piece.O))
        }
        board.Player1.name = p1NameFixed
        board.Player2.name = CNameFixed
        board.Player1.color = Player.getColor(p1Color)
        board.Player2.color = Player.getColor(CColor)

        this.title = "Game"
        val root = Group()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene
        val display = Canvas(W, H)
        val g: GraphicsContext = display.graphicsContext2D
        paintLines(g, cellSize, size, lineWidth, H, W)

        computerMove(g, difficulty)

        // Event handler for mouse clicks filling in spots on the board
        display.addEventHandler(MouseEvent.MOUSE_PRESSED) {event ->
            if (event.button == MouseButton.PRIMARY && board.turn == board.Player1 && board.state == State.Running) {
                val col = (event.x/cellSize).toInt()
                val row = (event.y/cellSize).toInt()

                if (board.updateCell(col, row)) {
                    BoardBuilder.placePiece(g, col, row, board, lineWidth, cellSize, padding)
                    board.checkIfWinner()
                    board.flipPlayer()
                }
                if (board.state != State.Running) {
                    if (board.state == State.Draw) {
                        BoardBuilder.endMenu(root, this, board, TypeOfGame.Computer, difficulty, p1Color, CColor)
                    }
                    else {
                        BoardBuilder.endMenu(root, this, board, TypeOfGame.Computer, difficulty, p1Color, CColor)
                    }
                }
            }

            if (board.state == State.Running && board.turn == board.Player2) {
                computerMove(g, difficulty)
                if (board.state != State.Running) {
                    BoardBuilder.endMenu(root, this, board, TypeOfGame.Computer, difficulty, p1Color, CColor)
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

    // Computer move, attempts to play a move given the difficulty
    fun computerMove(g: GraphicsContext, d: Difficulty) {
        if (board.turn == board.Player2) {
            var move = Pair(0,0)
            when (d) {
                Difficulty.Hard -> move = hardMove(board)
                Difficulty.Medium -> move = mediumMove(board)
                else -> move = easyMove(board)
            }
            BoardBuilder.placePiece(g, move.second, move.first, board, lineWidth, cellSize, padding)
            board.checkIfWinner()
            board.flipPlayer()
        }
    }

}