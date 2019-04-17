package edu.uiowa.Player

import edu.uiowa.Board.Board
import edu.uiowa.Board.Piece
import javafx.scene.paint.Color

// Class implementing Player for a computer user
class Computer: Player{

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

    companion object {
        // Method that makes an easy difficulty move for the computer
        fun easyMove(board: Board): Pair<Int, Int> {
            var row = 0
            var column = 0
            var chosen = false
            val percentChance = 0.9 // 15%

            while(row < board.size) {
                while (column < board.size) {
                    // Randomly choose spot
                    val random = Math.random()
                    if (random > percentChance) {
                        if (board.updateCell(column, row)) {
                            return Pair(row, column)
                        }
                    }
                    column += 1
                }
                column = 0
                row += 1
                if (row == board.size && !chosen) {
                    row = 0
                    column = 0
                }
            }
            return Pair(row, column)
        }

        // Method that makes an medium difficulty move for the computer
        fun mediumMove(board: Board): Pair<Int, Int> {
            // Will always connect 3 in a row
            var i = 0
            var j = 0
            while (i < board.size-1) {
                // for row
                while (j < board.size - 1) {

                    if (j-1 >= 0 && j+1 < board.size) {
                        if (board.board[i][j-1] == board.Player2.piece &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i][j+1] == Piece.E) { // X, X, E
                            if (board.updateCell(i, j+1)) {
                                return Pair(j+1, i)
                            }
                        }
                        else if (board.board[i][j-1] == board.Player2.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i][j+1] == board.Player2.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i][j-1] == Piece.E &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i][j+1] == board.Player2.piece) { // E, X, X
                            if (board.updateCell(i, j-1)) {
                                return Pair(j-1, i)
                            }
                        }
                    }

                    if (i-1 >= 0 && i+1 < board.size) {
                        if (board.board[i-1][j] == board.Player2.piece &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i+1][j] == Piece.E) { // X, X, E
                            if (board.updateCell(i+1, j)) {
                                return Pair(j, i+1)
                            }
                        }
                        else if (board.board[i-1][j] == board.Player2.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i+1][j] == board.Player2.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i-1][j] == Piece.E &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i+1][j] == board.Player2.piece) { // E, X, X
                            if (board.updateCell(i-1, j)) {
                                return Pair(j, i-1)
                            }
                        }
                    }

                    // check diag down
                    if (i-1 >= 0 && j-1 >= 0 && i+1 < board.size && j+1 < board.size) {
                        if (board.board[i-1][j-1] == board.Player2.piece &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i+1][j+1] == Piece.E) { // X, X, E
                            if (board.updateCell(i+1, j+1)) {
                                return Pair(j+1, i+1)
                            }
                        }
                        else if (board.board[i-1][j-1] == board.Player2.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i+1][j+1] == board.Player2.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i-1][j-1] == Piece.E &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i+1][j+1] == board.Player2.piece) { // E, X, X
                            if (board.updateCell(i-1, j-1)) {
                                return Pair(j-1, i-1)
                            }
                        }
                    }

                    // check diag up
                    if (i-1 >= 0 && j+1 < board.size && i+1 < board.size && j-1 >= 0) {
                        if (board.board[i-1][j+1] == board.Player2.piece &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i+1][j-1] == Piece.E) { // X, X, E
                            if (board.updateCell(i+1, j-1)) {
                                return Pair(j-1, i+1)
                            }
                        }
                        else if (board.board[i-1][j+1] == board.Player2.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i+1][j-1] == board.Player2.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i-1][j+1] == Piece.E &&
                                board.board[i][j] == board.Player2.piece &&
                                board.board[i+1][j-1] == board.Player2.piece) { // E, X, X
                            if (board.updateCell(i-1, j+1)) {
                                return Pair(j+1, i-1)
                            }
                        }
                    }
                    j++
                }
                j = 0
                i++
            }
            return easyMove(board)
        }

        // Method that makes a hard difficulty move for the computer
        fun hardMove(board: Board): Pair<Int, Int> {
            // Will always block 3 in a row
            // for column
            var i = 0
            var j = 0
            while (i < board.size) {
                // for row
                while (j < board.size) {

                    if (j-1 >= 0 && j+1 < board.size) {
                        if (board.board[i][j-1] == board.Player1.piece &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i][j+1] == Piece.E) { // X, X, E
                            if (board.updateCell(i, j+1)) {
                                return Pair(j+1, i)
                            }
                        }
                        else if (board.board[i][j-1] == board.Player1.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i][j+1] == board.Player1.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i][j-1] == Piece.E &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i][j+1] == board.Player1.piece) { // E, X, X
                            if (board.updateCell(i, j-1)) {
                                return Pair(j-1, i)
                            }
                        }
                    }

                    if (i-1 >= 0 && i+1 < board.size) {
                        if (board.board[i-1][j] == board.Player1.piece &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i+1][j] == Piece.E) { // X, X, E
                            if (board.updateCell(i+1, j)) {
                                return Pair(j, i+1)
                            }
                        }
                        else if (board.board[i-1][j] == board.Player1.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i+1][j] == board.Player1.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i-1][j] == Piece.E &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i+1][j] == board.Player1.piece) { // E, X, X
                            if (board.updateCell(i-1, j)) {
                                return Pair(j, i-1)
                            }
                        }
                    }

                    // check diag down
                    if (i-1 >= 0 && j-1 >= 0 && i+1 < board.size && j+1 < board.size) {
                        if (board.board[i-1][j-1] == board.Player1.piece &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i+1][j+1] == Piece.E) { // X, X, E
                            if (board.updateCell(i+1, j+1)) {
                                return Pair(j+1, i+1)
                            }
                        }
                        else if (board.board[i-1][j-1] == board.Player1.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i+1][j+1] == board.Player1.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i-1][j-1] == Piece.E &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i+1][j+1] == board.Player1.piece) { // E, X, X
                            if (board.updateCell(i-1, j-1)) {
                                return Pair(j-1, i-1)
                            }
                        }
                    }

                    // check diag up
                    if (i-1 >= 0 && j+1 < board.size && i+1 < board.size && j-1 >= 0) {
                        if (board.board[i-1][j+1] == board.Player1.piece &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i+1][j-1] == Piece.E) { // X, X, E
                            if (board.updateCell(i+1, j-1)) {
                                return Pair(j-1, i+1)
                            }
                        }
                        else if (board.board[i-1][j+1] == board.Player1.piece &&
                                board.board[i][j] == Piece.E &&
                                board.board[i+1][j-1] == board.Player1.piece) { // X, E, X
                            if (board.updateCell(i, j)) {
                                return Pair(j, i)
                            }
                        }
                        else if (board.board[i-1][j+1] == Piece.E &&
                                board.board[i][j] == board.Player1.piece &&
                                board.board[i+1][j-1] == board.Player1.piece) { // E, X, X
                            if (board.updateCell(i-1, j+1)) {
                                return Pair(j+1, i-1)
                            }
                        }
                    }
                    j++
                }
                j = 0
                i++
            }
            return mediumMove(board)
        }
    }

}