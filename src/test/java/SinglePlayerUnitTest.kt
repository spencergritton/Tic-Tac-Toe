import edu.uiowa.Board.Piece
import edu.uiowa.Board.GeneralBoard
import edu.uiowa.Player.User
import org.junit.Assert
import org.junit.Test
import edu.uiowa.Board.State
import edu.uiowa.Player.Computer

class SinglePlayerUnitTest{

    @Test
    fun testNoWinner() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[1][1] = Piece.O
        board.board[2][2] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.Running, board.state)
    }

    @Test
    fun testRowWinner() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[0][1] = Piece.X
        board.board[0][2] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testColumnWinner() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)

        board.board[0][2] = Piece.X
        board.board[1][2] = Piece.X
        board.board[2][2] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testFrontDiagonal() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)

        board.board[0][2] = Piece.X
        board.board[1][1] = Piece.X
        board.board[2][0] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testBackDiagonal() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[1][1] = Piece.X
        board.board[2][2] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testNoWinnerXL() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(5, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[1][1] = Piece.O
        board.board[2][2] = Piece.X
        board.board[3][3] = Piece.O
        board.board[4][4] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.Running, board.state)
    }

    @Test
    fun testXLRowWinner() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(5, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[0][1] = Piece.X
        board.board[0][2] = Piece.X
        board.board[0][3] = Piece.X
        board.board[0][4] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testXLColumnWinner() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(5, Player1, Player2)

        board.board[0][4] = Piece.X
        board.board[1][4] = Piece.X
        board.board[2][4] = Piece.X
        board.board[3][4] = Piece.X
        board.board[4][4] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testXLBoardFrontDiagonal() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(5, Player1, Player2)

        board.board[0][4] = Piece.X
        board.board[1][3] = Piece.X
        board.board[2][2] = Piece.X
        board.board[3][1] = Piece.X
        board.board[4][0] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testXLBoardBackDiagonal() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(5, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[1][1] = Piece.X
        board.board[2][2] = Piece.X
        board.board[3][3] = Piece.X
        board.board[4][4] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun testFinal() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = User("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)

        board.board[0][0] = Piece.X
        board.board[1][0] = Piece.O
        board.board[1][2] = Piece.X
        board.board[1][1] = Piece.O
        board.board[2][0] = Piece.X
        board.board[2][1] = Piece.O
        board.board[0][1] = Piece.X
        board.board[2][2] = Piece.O
        board.board[0][2] = Piece.X
        board.checkIfWinner()
        Assert.assertEquals(State.XWon, board.state)
    }

    @Test
    fun mediumMovesComputerCross() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = Computer("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)
        board.turn = Player2
        board.board[0][0] = Piece.X
        board.board[1][0] = Piece.X
        Computer.mediumMove(board)
        Assert.assertEquals(Piece.X, board.board[2][0])
    }

    @Test
    fun mediumMovesComputerDown() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = Computer("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)
        board.turn = Player2
        board.board[0][0] = Piece.X
        board.board[0][1] = Piece.X
        Computer.mediumMove(board)
        Assert.assertEquals(Piece.X, board.board[0][2])
    }

    @Test
    fun mediumMovesComputerDiagMiddle() {
        var Player1 = User("Tom", Piece.O)
        var Player2 = Computer("George", Piece.X)
        val board = GeneralBoard(3, Player1, Player2)
        board.turn = Player2
        board.board[0][0] = Piece.X
        board.board[2][2] = Piece.X
        println(board)
        Computer.mediumMove(board)
        println(board)
        Assert.assertEquals(Piece.X, board.board[1][1])
    }

}