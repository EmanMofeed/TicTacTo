package sample.ai;
import static sample.ai.Mark.BLANK;
import static sample.ai.Mark.X;
import static sample.ai.Mark.O;


public class Board {

    private Mark[][] board;
    private Mark winningMark;
    private int BOARD_WIDTH = 3;
    private boolean crossTurn, gameOver;
    private int availableMoves = BOARD_WIDTH * BOARD_WIDTH;

    public Board() {
        board = new Mark[BOARD_WIDTH][BOARD_WIDTH];
        crossTurn = true;
        gameOver = false;
        winningMark = BLANK;
        initialiseBoard();
    }

    private void initialiseBoard() {
        for (int row = 0; row < BOARD_WIDTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                board[row][col] = BLANK;
            }
        }
    }

    public boolean placeMark(int row, int col) {
        if (row < 0 || row >= BOARD_WIDTH || col < 0 || col >= BOARD_WIDTH
                || isTileMarked(row, col) || gameOver) {
            return false;
        }
        availableMoves--;
        board[row][col] = crossTurn ? X : O;
        togglePlayer();
        checkWin(row, col);
        return true;
    }

    /**
     * Check row and column provided and diagonals for win.
     *row Row to check
     * col Column to check
     */
    private void checkWin(int row, int col) {
        int rowSum = 0;
        // Check row for winner.
        for (int c = 0; c < BOARD_WIDTH; c++) {
            rowSum += getMarkAt(row, c).getMark();
        }
        if (calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " wins on row " + row);
            return;
        }

        // Check column for winner.
        rowSum = 0;
        for (int r = 0; r < BOARD_WIDTH; r++) {
            rowSum += getMarkAt(r, col).getMark();
        }
        if (calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " wins on column " + col);
            return;
        }

        // Top-left to bottom-right diagonal.
        rowSum = 0;
        for (int i = 0; i < BOARD_WIDTH; i++) {
            rowSum += getMarkAt(i, i).getMark();
        }
        if (calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " wins on the top-left to "
                    + "bottom-right diagonal");
            return;
        }

        // Top-right to bottom-left diagonal.
        rowSum = 0;
        int indexMax = BOARD_WIDTH - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += getMarkAt(i, indexMax - i).getMark();
        }
        if (calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " wins on the top-right to "
                    + "bottom-left diagonal.");
            return;
        }

        if (!anyMovesAvailable()) {
            gameOver = true;
            System.out.println("Tie!");
        }
    }

    /**
     * Calculates if provided ASCII sum equates to a win for X or O.
     *
     * rowSum Sum of characters' ASCII values in a row
     *Mark indicating which player won or a space character if neither
     */
    private Mark calcWinner(int rowSum) {
        int Xwin = X.getMark() * BOARD_WIDTH;
        int Owin = O.getMark() * BOARD_WIDTH;
        if (rowSum == Xwin) {
            gameOver = true;
            winningMark = X;
            return X;
        } else if (rowSum == Owin) {
            gameOver = true;
            winningMark = O;
            return O;
        }
        return BLANK;
    }

    private void togglePlayer() {
        crossTurn = !crossTurn;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean anyMovesAvailable() {
        return availableMoves > 0;
    }

    public Mark getMarkAt(int row, int column) {
        return board[row][column];
    }

    public boolean isTileMarked(int row, int column) {
        return board[row][column].isMarked();
    }

    public void setMarkAt(int row, int column, Mark newMark) {
        board[row][column] = newMark;
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        for (Mark[] row : board) {
            for (Mark tile : row) {
                strBldr.append(tile).append(' ');
            }
            strBldr.append("\n");
        }
        return strBldr.toString();
    }

    public boolean isCrossTurn() {
        return crossTurn;
    }

    public int getWidth() {
        return BOARD_WIDTH;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Mark getWinningMark() {
        return winningMark;
    }
}
