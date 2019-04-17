package edu.uiowa.JavaFX

import edu.uiowa.Board.Board
import edu.uiowa.Board.Piece
import edu.uiowa.Board.State
import javafx.animation.TranslateTransition
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage
import javafx.util.Duration
import java.io.File

// This is an important class containing methods that are used by both
// PlayerVsPlayerGame and PlayerVsComputerGame
class BoardBuilder{
    companion object {
        // Places piece onto the JavaFx board for player to see
        fun placePiece(g: GraphicsContext,
                       col: Int,
                       row: Int,
                       board: Board,
                       lineWidth: Double,
                       cellSize: Double,
                       padding: Double) {

            val sound = Media(File("./src/main/resources/piece.mp3").toURI().toString())
            val mediaPlayer = MediaPlayer(sound)
            mediaPlayer.play()

            when(board.turn.piece) {
                Piece.X -> {
                    g.stroke = board.getPlayerByPiece(Piece.X)?.color
                    g.lineWidth = lineWidth
                    g.strokeLine(
                            col * cellSize + padding,
                            row * cellSize + padding,
                            (col + 1) * cellSize - padding,
                            (row + 1) * cellSize - padding
                    )
                    g.strokeLine(
                            (col + 1) * cellSize - padding,
                            row * cellSize + padding,
                            col * cellSize + padding,
                            (row + 1) * cellSize - padding
                    )
                }
                Piece.O -> {
                    g.stroke = board.getPlayerByPiece(Piece.O)?.color
                    g.lineWidth = lineWidth
                    g.strokeOval(
                            col * cellSize + padding,
                            row * cellSize + padding,
                            cellSize - 2 * padding,
                            cellSize - 2 * padding
                    )
                }
            }
        }

        // Paints tic tac toe lines onto JavaFx board
        fun paintLines(g: GraphicsContext,
                       cellSize: Double,
                       size: Int,
                       lineWidth: Double,
                       H: Double,
                       W: Double) {

            var lines = 0
            var x = cellSize
            while (lines < size-1) {
                g.stroke = Color.GRAY
                g.lineWidth = lineWidth
                g.strokeLine(x,0.0, x, H)
                g.strokeLine(0.0, x, W, x)
                x += cellSize
                lines ++
            }
        }

        // Releases confetti when the player wins!
        fun confetti(root: Group): MediaPlayer {
            val musicFile = "./src/main/resources/tada.mp3"
            val sound = Media(File(musicFile).toURI().toString())
            val mediaPlayer = MediaPlayer(sound)
            mediaPlayer.play()

            val confettiList = ArrayList<Circle>()
            var x = 15.0
            for (i in 1..70) {
                val circle = Circle()
                circle.fill = randomColor()
                circle.radius = 5.0
                circle.centerY = Math.random() * 60 - 60
                circle.centerX = x
                x += 5
                confettiList.add(circle)
            }
            for (item in confettiList) {
                root.children.add(item)
                val translation = TranslateTransition()
                translation.duration = Duration.seconds(8.0)
                translation.toY = 1000.0
                if (Math.random() > 0.5) { translation.toX = item.centerX + Math.random()*10 }
                else { translation.toX = item.centerX - Math.random()*10 }
                translation.node = item
                translation.play()
            }
            return mediaPlayer
        }

        // Picks random color
        fun randomColor(): Color {
            val random = Math.random()
            when {
                random < 0.1 -> return Color.ALICEBLUE
                random < 0.2 -> return Color.RED
                random < 0.3 -> return Color.GREEN
                random < 0.4 -> return Color.YELLOW
                random < 0.5 -> return Color.ORANGE
                random < 0.6 -> return Color.TURQUOISE
                random < 0.7 -> return Color.PURPLE
                random < 0.8 -> return Color.CRIMSON
                random < 0.9 -> return Color.LIME
                else -> return Color.AZURE
            }
        }

        // Plays the sound that happens when the player loses
        fun loseDrawSound(): MediaPlayer {
            val musicFile = "./src/main/resources/aww.mp3"
            val sound = Media(File(musicFile).toURI().toString())
            val mediaPlayer = MediaPlayer(sound)
            mediaPlayer.play()
            return mediaPlayer
        }

        // Displays the JavaFx menu at the end of each game
        // Important so user can choose to exit or play again
        fun endMenu(root: Group, caller: Stage, board: Board, type: TypeOfGame, difficulty: Difficulty, p1Color: String, p2Color: String) {
            val playAgain = Label()
            playAgain.text = "Play Again"
            playAgain.id = "title"
            playAgain.style = "-fx-text-fill: sandybrown;"
            playAgain.onMouseClicked = EventHandler {
                val musicFile = "./src/main/resources/button.mp3"
                val sound = Media(File(musicFile).toURI().toString())
                val mediaPlayer = MediaPlayer(sound)
                mediaPlayer.play()
                caller.hide()
                if (type == TypeOfGame.Player) {
                    PlayerVsPlayerGame(board.size, board.Player1.name, p1Color, board.Player2.name, p2Color)
                } else {
                    PlayerVsComputerGame(board.size, board.Player1.name, p1Color, board.Player2.name, p2Color, difficulty)
                }
            }
            playAgain.onMouseEntered = EventHandler {
                playAgain.style = "-fx-text-fill: wheat;"
            }
            playAgain.onMouseExited = EventHandler {
                playAgain.style = "-fx-text-fill: sandybrown;"
            }

            val menus = Label()
            menus.text = "Menus"
            menus.id = "title"
            menus.style = "-fx-text-fill: sandybrown;"
            menus.onMouseClicked = EventHandler {
                val musicFile = "./src/main/resources/button.mp3"
                val sound = Media(File(musicFile).toURI().toString())
                val mediaPlayer = MediaPlayer(sound)
                mediaPlayer.play()
                caller.hide()
                TitlePage()
            }
            menus.onMouseEntered = EventHandler {
                menus.style = "-fx-text-fill: wheat;"
            }
            menus.onMouseExited = EventHandler {
                menus.style = "-fx-text-fill: sandybrown;"
            }

            val title = Label()
            title.text = ""
            title.id = "title"
            title.style = "-fx-text-fill: orange;"

            if (board.state == State.Draw) {
                loseDrawSound()
                title.text = "Draw!"
            } else if (board.state == State.XWon) {
                if (type == TypeOfGame.Player) {
                    confetti(root)
                    title.text = "${board.getPlayerByPiece(Piece.X)?.name} Won!"
                } else {
                    if (board.getPlayerByPiece(Piece.X) == board.Player2) {
                        loseDrawSound()
                        title.text = "You Lose!"
                    } else {
                        confetti(root)
                        title.text = "You Win!"
                    }
                }
            } else {
                if (type == TypeOfGame.Player) {
                    confetti(root)
                    title.text = "${board.getPlayerByPiece(Piece.O)?.name} Won!"
                } else {
                    if (board.getPlayerByPiece(Piece.O) == board.Player2) {
                        loseDrawSound()
                        title.text = "You Lose!"
                    } else {
                    confetti(root)
                    title.text = "You Win!"
                    }
                }
            }
            val vbox = VBox()
            menus.alignment = Pos.CENTER_LEFT

            vbox.children.add(title)
            vbox.children.add(menus)
            vbox.children.add(playAgain)

            root.children.add(vbox)
        }
    }
}