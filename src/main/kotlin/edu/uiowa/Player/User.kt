package edu.uiowa.Player

import edu.uiowa.Board.Piece
import javafx.scene.paint.Color

// User class implementation of the Player interface
class User: Player{

    constructor(name: String, piece: Piece){
        this.name = name
        this.piece = piece
        this.color = Color.BLACK
    }

    override var name: String
    override var piece: Piece
    override var color: Color

    override fun toString(): String {
        return "$name: $piece"
    }

}