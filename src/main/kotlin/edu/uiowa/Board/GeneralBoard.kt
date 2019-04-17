package edu.uiowa.Board

import edu.uiowa.Player.Player

// A implementation of the board class for both player vs player and player vs computer
class GeneralBoard: Board{

    constructor(size: Int, player1: Player, player2: Player){
        this.size = size
        board = createEmptyBoard()
        Player1 = player1
        Player2 = player2
        turn = randomPlayer()
    }

    override var size: Int = 3
    override var Player1: Player
    override var Player2: Player
    override var board: Array<Array<Piece>>
    override var turn: Player = randomPlayer()
    override var state = State.Running

    // Shows the board in string format
    override fun toString(): String {
        var returnString = ""
        for (i in 0..(size-1)) {
            for (c in 0..(size-1)) {
                returnString += board[c][i]
                returnString += ", "
            }
            returnString += "\n"
        }
        return returnString
    }

}