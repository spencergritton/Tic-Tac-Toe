package edu.uiowa.Board

import edu.uiowa.Player.Player

// General board interface. Contains all necessary methods for setting up board and manipulating it
interface Board{
    var size: Int
    var Player1: Player
    var Player2: Player
    var turn: Player
    var state: State

    // Stored by rows [column][row]
    var board: Array<Array<Piece>>

    // Returns a Player object given a Piece enum
    fun getPlayerByPiece(piece: Piece): Player? {
        if (piece.equals(Player1.piece)) {return Player1}
        if (piece.equals(Player2.piece)) {return Player2}
        return null
    }

    // Returns a given column by index
    fun getColumn(getColumn: Int): Array<Piece> {
        val column = Array(size, {Piece.E})
        for (row in 0..(size-1)) {
            column.set(row, board[row][getColumn])
        }
        return column
    }

    // Updates board at column, row, to player piece of whoevers turn it is
    // Returns true if updated successfully
    fun updateCell(col: Int, row: Int): Boolean {
        if (col in 0 until size && row in 0 until size && state == State.Running) {
            if (board[col][row] == Piece.E) {
                board[col][row] = turn.piece
                return true
            }
        }
        return false
    }

    // changes who's turn it is
    fun flipPlayer() {
        if (turn.equals(Player1)) { turn = Player2 }
        else { turn = Player1 }
    }

    // Creates an empty board with all Piece.E populated
    fun createEmptyBoard(): Array<Array<Piece>> {
        val EmptyBoard: Array<Array<Piece>> = Array(size, { Array(size, {Piece.E}) })
        return EmptyBoard
    }

    // Chooses a random player for the start of the game
    fun randomPlayer(): Player {
        val random = Math.random()
        if (random > 0.499) { return Player1 }
        return Player2
    }

    // Checks if someone has won
    // If someone has won set board.state = the current state of the game
    fun checkIfWinner() {
        for (item in 0 until board.size) {

            // Check each row
            for (rowItem in 0 until board[item].size) {
                if (rowItem > 0 && rowItem < board.size - 1) {
                    if (board[item][rowItem - 1] == Piece.X && board[item][rowItem] == Piece.X && board[item][rowItem + 1] == Piece.X) {
                        state = State.XWon
                    } else if (board[item][rowItem - 1] == Piece.O && board[item][rowItem] == Piece.O && board[item][rowItem + 1] == Piece.O) {
                        state = State.OWon
                    }
                }
            }

            // Check each column
            val column = getColumn(item)
            for (rowItem in 0 until column.size) {
                if (rowItem > 0 && rowItem < column.size - 1) {
                    if (column[rowItem - 1] == Piece.X && column[rowItem] == Piece.X && column[rowItem + 1] == Piece.X) {
                        state = State.XWon
                    } else if (column[rowItem - 1] == Piece.O && column[rowItem] == Piece.O && column[rowItem + 1] == Piece.O) {
                        state = State.OWon
                    }
                }
            }
        }

        // Check angles
        // size 3
        if (size == 3) {
            // check forward angle
            val forwardAngle = mutableListOf<Piece>()
            var curr = 0
            for (i in (size-1) downTo 0){
                forwardAngle.add(board[i][curr])
                curr ++
            }
            var distinct = forwardAngle.distinct()
            if (distinct.size == 1 && distinct.get(0).equals(Piece.X)) { state = State.XWon }
            if (distinct.size == 1 && distinct.get(0).equals(Piece.O)) { state = State.OWon }

            // check backward angle
            val backwardAngle = mutableListOf<Piece>()
            curr = 0
            for (i in 0..(size-1)){
                backwardAngle.add(board[i][curr])
                curr ++
            }
            distinct = backwardAngle.distinct()
            if (distinct.size == 1 && distinct.get(0).equals(Piece.X)) { state = State.XWon }
            if (distinct.size == 1 && distinct.get(0).equals(Piece.O)) { state = State.OWon }
        }

        // size 4
        else if (size == 4) {
            // forward angle
            if (board[3][0] == Piece.X && board[2][1] == Piece.X && board[1][2] == Piece.X ) { state = State.XWon }
            if (board[3][0] == Piece.O && board[2][1] == Piece.O && board[1][2] == Piece.O ) { state = State.OWon }
            if (board[2][1] == Piece.X && board[1][2] == Piece.X && board[0][3] == Piece.X) { state = State.XWon }
            if (board[2][1] == Piece.O && board[1][2] == Piece.O && board[0][3] == Piece.O ) { state = State.OWon }
            // backward angle
            if (board[0][0] == Piece.X && board[1][1] == Piece.X && board[2][2] == Piece.X ) { state = State.XWon }
            if (board[0][0] == Piece.O && board[1][1] == Piece.O && board[2][2] == Piece.O ) { state = State.OWon }
            if (board[1][1] == Piece.X && board[2][2] == Piece.X && board[3][3] == Piece.X) { state = State.XWon }
            if (board[1][1] == Piece.O && board[2][2] == Piece.O && board[3][3] == Piece.O ) { state = State.OWon }
            // half angles
            if (board[0][1] == Piece.X && board[1][2] == Piece.X && board[2][3] == Piece.X ) { state = State.XWon }
            if (board[0][1] == Piece.O && board[1][2] == Piece.O && board[2][3] == Piece.O ) { state = State.OWon }
            if (board[1][0] == Piece.X && board[2][1] == Piece.X && board[3][2] == Piece.X) { state = State.XWon }
            if (board[1][0] == Piece.O && board[2][1] == Piece.O && board[3][3] == Piece.O ) { state = State.OWon } // end top L
            if (board[2][0] == Piece.X && board[1][1] == Piece.X && board[0][2] == Piece.X ) { state = State.XWon }
            if (board[2][0] == Piece.O && board[1][1] == Piece.O && board[0][2] == Piece.O ) { state = State.OWon }
            if (board[3][1] == Piece.X && board[2][2] == Piece.X && board[1][3] == Piece.X) { state = State.XWon }
            if (board[3][1] == Piece.O && board[2][2] == Piece.O && board[1][3] == Piece.O ) { state = State.OWon } // end bottom L
        }
        // size 5
        else {
            // 10
            if (board[0][2] == Piece.X && board[1][3] == Piece.X && board[2][4] == Piece.X ) { state = State.XWon }
            if (board[0][2] == Piece.O && board[1][3] == Piece.O && board[2][4] == Piece.O ) { state = State.OWon }
            // 9
            if (board[0][1] == Piece.X && board[1][2] == Piece.X && board[2][3] == Piece.X ) { state = State.XWon }
            if (board[0][1] == Piece.O && board[1][2] == Piece.O && board[2][3] == Piece.O ) { state = State.OWon }
            if (board[1][2] == Piece.X && board[2][3] == Piece.X && board[3][4] == Piece.X ) { state = State.XWon }
            if (board[1][2] == Piece.O && board[2][3] == Piece.O && board[3][4] == Piece.O ) { state = State.OWon }
            // 8
            if (board[0][0] == Piece.X && board[1][1] == Piece.X && board[2][2] == Piece.X ) { state = State.XWon }
            if (board[0][0] == Piece.O && board[1][1] == Piece.O && board[2][2] == Piece.O ) { state = State.OWon }
            if (board[1][1] == Piece.X && board[2][2] == Piece.X && board[3][3] == Piece.X) { state = State.XWon }
            if (board[1][1] == Piece.O && board[2][2] == Piece.O && board[3][3] == Piece.O ) { state = State.OWon }
            if (board[2][2] == Piece.X && board[3][3] == Piece.X && board[4][4] == Piece.X) { state = State.XWon }
            if (board[2][2] == Piece.O && board[3][3] == Piece.O && board[4][4] == Piece.O ) { state = State.OWon }
            // 7
            if (board[1][0] == Piece.X && board[2][1] == Piece.X && board[3][2] == Piece.X ) { state = State.XWon }
            if (board[1][0] == Piece.O && board[2][1] == Piece.O && board[3][2] == Piece.O ) { state = State.OWon }
            if (board[2][1] == Piece.X && board[3][2] == Piece.X && board[4][3] == Piece.X ) { state = State.XWon }
            if (board[2][1] == Piece.O && board[3][2] == Piece.O && board[4][3] == Piece.O ) { state = State.OWon }
            // 6
            if (board[2][0] == Piece.X && board[3][1] == Piece.X && board[4][2] == Piece.X ) { state = State.XWon }
            if (board[2][0] == Piece.O && board[3][1] == Piece.O && board[4][2] == Piece.O ) { state = State.OWon }
            // 5
            if (board[2][0] == Piece.X && board[1][1] == Piece.X && board[0][2] == Piece.X ) { state = State.XWon }
            if (board[2][0] == Piece.O && board[1][1] == Piece.O && board[0][2] == Piece.O ) { state = State.OWon }
            // 4
            if (board[3][0] == Piece.X && board[2][1] == Piece.X && board[1][2] == Piece.X ) { state = State.XWon }
            if (board[3][0] == Piece.O && board[2][1] == Piece.O && board[1][2] == Piece.O ) { state = State.OWon }
            if (board[2][1] == Piece.X && board[1][2] == Piece.X && board[0][3] == Piece.X ) { state = State.XWon }
            if (board[2][1] == Piece.O && board[1][2] == Piece.O && board[0][3] == Piece.O ) { state = State.OWon }
            // 3
            if (board[4][0] == Piece.X && board[3][1] == Piece.X && board[2][2] == Piece.X ) { state = State.XWon }
            if (board[4][0] == Piece.O && board[3][1] == Piece.O && board[2][2] == Piece.O ) { state = State.OWon }
            if (board[3][1] == Piece.X && board[2][2] == Piece.X && board[1][3] == Piece.X) { state = State.XWon }
            if (board[3][1] == Piece.O && board[2][2] == Piece.O && board[1][3] == Piece.O ) { state = State.OWon }
            if (board[2][2] == Piece.X && board[1][3] == Piece.X && board[0][4] == Piece.X) { state = State.XWon }
            if (board[2][2] == Piece.O && board[1][3] == Piece.O && board[0][4] == Piece.O ) { state = State.OWon }
            // 2
            if (board[4][1] == Piece.X && board[3][2] == Piece.X && board[2][3] == Piece.X ) { state = State.XWon }
            if (board[4][1] == Piece.O && board[3][2] == Piece.O && board[2][3] == Piece.O ) { state = State.OWon }
            if (board[3][2] == Piece.X && board[2][3] == Piece.X && board[1][4] == Piece.X ) { state = State.XWon }
            if (board[3][2] == Piece.O && board[2][3] == Piece.O && board[1][4] == Piece.O ) { state = State.OWon }
            // 5
            if (board[4][2] == Piece.X && board[3][1] == Piece.X && board[2][2] == Piece.X ) { state = State.XWon }
            if (board[4][2] == Piece.O && board[3][1] == Piece.O && board[2][2] == Piece.O ) { state = State.OWon }
        }

        // check if draw
        if (board.count { it.contains(Piece.E) } == 0 && state == State.Running) {
            state = State.Draw
        }
    }
}