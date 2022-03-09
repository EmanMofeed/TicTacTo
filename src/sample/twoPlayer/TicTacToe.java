package sample.twoPlayer;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class TicTacToe {
     Random random = new Random();
     public Button[] buttons = new Button[9];
     boolean flag = false;
     boolean player1_turn;

    public TicTacToe(){
        for (int i = 0; i < 9 ; i++) {
            buttons[i] = new Button();
            buttons[i].setMinWidth(120);
            buttons[i].setMinHeight(120);
        }

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void firstTurn(){
        if(random.nextInt(2)==0) {
            player1_turn = true;
        }else {
            player1_turn = false;
        }

    }

    public void check() {
        //check if x win conditions
        if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
        }
        //check O win conditions
        if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
            oWins(2, 4, 6);
        }
    }

    public void xWins(int col1,int col2, int col3){
        buttons[col1].setStyle("-fx-background-color: #1aff1a; ");
        buttons[col2].setStyle("-fx-background-color: #1aff1a; ");
        buttons[col3].setStyle("-fx-background-color: #1aff1a; ");
        for (int i = 0; i < 9; i++) {
            buttons[i].setDisable(false);
        }
        flag =true;
    }

    public void oWins(int col1,int col2, int col3){
        buttons[col1].setStyle("-fx-background-color: #1aff1a; ");
        buttons[col2].setStyle("-fx-background-color: #1aff1a; ");
        buttons[col3].setStyle("-fx-background-color: #1aff1a; ");
        for (int i = 0; i < 9; i++) {
            buttons[i].setDisable(false);
        }
        flag =true;

    }
    public void actionPerformed() {
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            buttons[i].setOnAction(e -> {
                if (e.getSource() == buttons[finalI]) {
                    if (player1_turn) {
                        if(flag==false) {
                            if (buttons[finalI].getText() == "") {
                                buttons[finalI].setText("X");
                                buttons[finalI].setTextFill(Color.RED);
                                buttons[finalI].setFont(Font.font(20));
                                player1_turn = false;
                                check();
                            }
                        }
                    } else if (buttons[finalI].getText() == "") {
                        if (flag == false) {
                            buttons[finalI].setText("O");
                            buttons[finalI].setTextFill(Color.BLUE);
                            buttons[finalI].setFont(Font.font(20));
                            player1_turn = true;
                            check();
                        }
                    }
                }
            });

        }
    }
}
