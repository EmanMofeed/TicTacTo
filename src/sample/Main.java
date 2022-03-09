package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.easyAI.Game;
import sample.hardAI.MinMax;
import sample.twoPlayer.TicTacToe;

public class Main extends Application {
    BackgroundFill fill = new BackgroundFill(Color.rgb(194, 194, 214), CornerRadii.EMPTY, Insets.EMPTY);
    Background background = new Background(fill);
    Game game = new Game();
    TicTacToe ticTacToe = new TicTacToe();


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        Group group = new Group();

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(130, 130, 130, 130));
        pane.setHgap(10.5);
        pane.setVgap(10.5);

        Button easy = new Button("AI Easy Level");
        easy.setFont(Font.font(14));
        easy.setBackground(background);
        easy.setTextFill(Color.rgb(40, 40, 62));
        easy.setPrefSize(150, 50);
        pane.add(easy, 0, 0);

        Button unbeatable = new Button("AI unbeatable");
        unbeatable.setFont(Font.font(14));
        unbeatable.setBackground(background);
        unbeatable.setTextFill(Color.rgb(40, 40, 62));
        unbeatable.setPrefSize(150, 50);
        pane.add(unbeatable, 1, 0);

        Button two = new Button("Two players");
        two.setFont(Font.font(14));
        two.setBackground(background);
        two.setTextFill(Color.rgb(40, 40, 62));
        two.setPrefSize(150, 50);
        pane.add(two, 2, 0);
        two.setOnAction(e -> {
                    Group group1 = new Group();
                    GridPane grid = new GridPane();
                    Stage stage = new Stage();
                    Text t = new Text("Tic-Tac-Toe");
                    t.setId("fancytext");
                    t.setX(130);
                    t.setY(100);
                    int r = 0;
                    int c = 0;
                    for (int i = 0; i < 9; i++) {
                        if (r < 3) {
                            ticTacToe.buttons[i].setStyle("-fx-background-color: GRAY");
                            grid.add(ticTacToe.buttons[i], r++, c);
                            continue;
                        } else {
                            r = 0;
                            ++c;
                            ticTacToe.buttons[i].setStyle("-fx-background-color: GRAY");
                            grid.add(ticTacToe.buttons[i], r++, c);
                        }
                    }
                    grid.setLayoutX(120);
                    grid.setLayoutY(160);
                    Button resat = new Button();
                    text("Rest      ",resat);
                    Button exit = new Button();
                    text("exit",exit);
                    resat.setOnAction(k->{
                        for (int i = 0; i <9 ; i++) {
                            ticTacToe.buttons[i].setText("");
                            ticTacToe.buttons[i].setStyle("-fx-background-color: GRAY");
                            ticTacToe.setFlag(false);
                        }
                        ticTacToe.firstTurn();
                        ticTacToe.actionPerformed();
                    });
                    exit.setOnAction(l->{
                        stage.close();
                    });
                    grid.add(resat,3,0);
                    grid.add(exit,4,0);

                    grid.setVgap(9);
                    grid.setHgap(9);

                    group1.getChildren().addAll(t, grid);

                    Scene scene = new Scene(group1, 850, 600);
                    scene.getStylesheets().add("sample/twoPlayer/css.css");
                    stage.setScene(scene);
                    stage.setTitle("Tic-Tac-Toe");
                    stage.show();

                      ticTacToe.firstTurn();
                      ticTacToe.actionPerformed();
                });
        easy.setOnAction(k->{
                    Group group1 = new Group();
                    GridPane grid = new GridPane();
                    Stage stage = new Stage();
                    Text t = new Text("Tic-Tac-Toe");
                    t.setId("fancytext");
                    t.setX(130);
                    t.setY(100);
                    int r = 0;
                    int c = 0;
                    for (int i = 0; i < 9; i++) {
                        if (r < 3) {
                            grid.add(game.buttons[i], r++, c);
                            game.buttons[i].setStyle("-fx-background-color: GRAY");

                            continue;
                        } else {
                            r = 0;
                            ++c;
                            grid.add(game.buttons[i], r++, c);
                            game.buttons[i].setStyle("-fx-background-color: GRAY");

                        }
                    }

                    grid.setLayoutX(120);
                    grid.setLayoutY(160);


                    Button labelPlayer2 = game.getLabelPlayer2();
                    text("Computer",labelPlayer2);
                    Button resat = new Button();
                    text("Rest      ",resat);
                    Button exit = new Button();
                    text("Exit         ",exit);
                    TextField turn = game.getTextField();
                    turn.setId("fancytext1");
                    turn.setMaxWidth(250);
                    turn.setMaxHeight(100);
                    grid.add(turn,4,1);
                    turn.setDisable(true);
                      resat.setOnAction(l->{
                        for (int i = 0; i <9 ; i++) {
                            game.buttons[i].setText("");
                            game.buttons[i].setStyle("-fx-background-color: GRAY");
                            game.setFlag(false);
                        }
                        game.actionPerformed();
                    });

                      exit.setOnAction(m->{
                          stage.close();
                      });
                    grid.add(labelPlayer2,3,0);
                    grid.add(resat,4,0);
                    grid.add(exit,3,1);



                    grid.setVgap(9);
                    grid.setHgap(9);

                    group1.getChildren().addAll(t, grid);

                    Scene scene = new Scene(group1, 900, 600);
                    scene.getStylesheets().add("sample/twoPlayer/css.css");
                    stage.setScene(scene);
                    stage.setTitle("Tic-Tac-Toe");
                    stage.show();

                    game.actionPerformed();
        });

        unbeatable.setOnAction(d->{
            sample.ai.TicTacToe ticTacToe = new sample.ai.TicTacToe();
            Stage stage = new Stage();
            ticTacToe.starts(stage);

        });
        group.getChildren().addAll(pane);
        Scene scene = new Scene(group);
        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#c1c1d7")),     //colors
                new Stop(1, Color.web("#3c3c5d")))
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Button text(String string, Button button){
        button.setFont(Font.font(null, FontWeight.BOLD, 32));
        button.setText(string);
        button.setMinWidth(120);
        button.setMinHeight(120);
        return button;
    }

}
