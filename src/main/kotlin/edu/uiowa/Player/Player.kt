package edu.uiowa.Player

import edu.uiowa.Board.Piece
import javafx.scene.paint.Color

// Interface of Player which is the general object placed in all boards to represent User or Computer
interface Player{
    // Name of player
    var name: String
    // Piece that player is using.. i.e. X, O
    var piece: Piece
    var color: Color

    companion object {
        // Gets a color string and converts it to a Color object
        fun getColor(color: String): Color {
            when {
                color.equals("RED") -> return Color.RED
                color.equals("BLUE") -> return Color.BLUE
                color.equals("GREEN") -> return Color.GREEN
                color.equals("YELLOW") -> return Color.YELLOW
                color.equals("ORANGE") -> return Color.ORANGE
                else -> return Color.BLACK
            }
        }
    }
}