
package sample.hardAI;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Main;

public class MinMax {
    public Button buttons[][] = new Button[3][3];
    TextField textField = new TextField();
    boolean player1_turn = true;
    Button labelPlayer2 = new Button();
    boolean flag = false;


        static String player = "x", opponent = "o";


        // This function returns true if there are moves remaining on the board. It returns false if there are no moves left to play.
         Boolean isMovesLeft(Button board[][]) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j].getText() == "")
                        return true;
            return false;
        }

        // This is the evaluation function as discussed in the previous article ( http://goo.gl/sJgv68 )
         int evaluate(Button b[][]) {
            // Checking for Rows for X or O victory.
            for (int row = 0; row < 3; row++) {
                if (b[row][0] == b[row][1] &&
                        b[row][1] == b[row][2]) {
                    if (b[row][0].getText() == player)
                        return +10;
                    else if (b[row][0].getText() == opponent)
                        return -10;
                }
            }

            // Checking for Columns for X or O victory.
            for (int col = 0; col < 3; col++) {
                if (b[0][col] == b[1][col] &&
                        b[1][col] == b[2][col]) {
                    if (b[0][col].getText() == player)
                        return +10;

                    else if (b[0][col].getText() == opponent)
                        return -10;
                }
            }

            // Checking for Diagonals for X or O victory.
            if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
                if (b[0][0].getText() == player)
                    return +10;
                else if (b[0][0].getText() == opponent)
                    return -10;
            }

            if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
                if (b[0][2].getText() == player)
                    return +10;
                else if (b[0][2].getText() == opponent)
                    return -10;
            }

            // Else if none of them have won then return 0
            return 0;
        }

        // This is the minimax function. It considers all the possible ways the game can go and returns the value of the board
         int minimax(Button board[][], int depth, Boolean isMax) {
            int score = evaluate(board);
            // If Maximizer has won the game return his/her evaluated score
            if (score == 10)
                return score;
            // If Minimizer has won the game return his/her evaluated score
            if (score == -10)
                return score;

            // If there are no more moves and no winner then it is a tie
            if (isMovesLeft(board) == false)
                return 0;

            // If this maximizer's move
            if (isMax) {
                int best = -1000;

                // Traverse all cells
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // Check if cell is empty
                        if (board[i][j].getText() == "") {
                            // Make the move
                            board[i][j].setText(player);

                            // Call minimax recursively and choose the maximum value
                            best = Math.max(best, minimax(board,
                                    depth + 1, !isMax));

                            // Undo the move
                            board[i][j].setText("");
                        }
                    }
                }
                return best;
            }

            // If this minimizer's move
            else {
                int best = 1000;

                // Traverse all cells
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // Check if cell is empty
                        if (board[i][j].getText() == "") {
                            // Make the move
                            board[i][j].setText(opponent);

                            // Call minimax recursively and choose the minimum value
                            best = Math.min(best, minimax(board,
                                    depth + 1, !isMax));

                            // Undo the move
                            board[i][j].setText("");
                        }
                    }
                }
                return best;
            }
        }

        // This will return the best possible move for the player
        public Move findBestMove(Button board[][]) {
            int bestVal = -1000;
            Move bestMove = new Move();
            bestMove.row = -1;
            bestMove.col = -1;

            // Traverse all cells, evaluate minimax function for all empty cells. And return the cell with optimal value.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j].getText() == "") {
                        // Make the move
                        board[i][j].setText(player);

                        // compute evaluation function for this move.
                        int moveVal = minimax(board, 0, false);

                        // Undo the move
                        board[i][j].setText("");

                        // If the value of the current move is more than the best value, then update best/
                        if (moveVal > bestVal) {
                            bestMove.row = i;
                            bestMove.col = j;
                            bestVal = moveVal;
                        }
                    }
                }
            }
            return bestMove;
        }


    public void actionPerformed() {
        for(int i = 0;i< 3;i++){
            for (int j = 0; j <3 ; j++) {
            int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction(e -> {
                if (e.getSource() == buttons[finalI][finalJ]) {
                    if (player1_turn) {
                        if (buttons[finalI][finalJ].getText() == "") {
                            if(flag==false) {
                                buttons[finalI][finalJ].setText("X");
                                buttons[finalI][finalJ].setTextFill(Color.RED);
                                buttons[finalI][finalJ].setFont(Font.font(20));
                                player1_turn = false;
                                textField.setText("Computer");
                            }

                        }
                    } }});

        }
        labelPlayer2.setOnAction(k->{
            if (!player1_turn) {
                if (flag == false) {
                    player1_turn = true;
                    textField.setText("Player");
                    Move x =findBestMove(buttons);
                    buttons[x.col][x.row].setText("X");
                    buttons[x.col][x.row].setTextFill(Color.RED);
                    buttons[x.col][x.row].setFont(Font.font(20));
                }
            }

        });

    }

}
}