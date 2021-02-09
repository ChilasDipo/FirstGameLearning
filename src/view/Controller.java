package view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.Cat;
import logic.Lazers;
import logic.Score;

import java.io.IOException;
public class Controller {
    Cat cat = new Cat();
    Lazers lazers = new Lazers();
    Score score = new Score();
    private ImageView coinImageView = new ImageView();

    @FXML
    ImageView catImageView = new ImageView(new Image("assets/cat.png"));
    @FXML
    ImageView[] lazerXNowGrid = new ImageView[9];
    ImageView[] lazerXNextGrid = new ImageView[9];
    ImageView[] lazerYNowGrid = new ImageView[9];
    ImageView[] lazerYNextGrid = new ImageView[9];
    @FXML
    GridPane grid;
    @FXML
    AnchorPane pane = new AnchorPane();
    @FXML
    Label left, right, up, down, deathcounter, scoreboard;

    @FXML
    void createButton() {
        Button btn = new Button("Ok");
        addPictures();
        newScoreImage();
        score.addScoreObserver(new Score.ScoreObserver() {
            //Anonymous inner class for handling updates from Score
            @Override
            public void update() {
                newScoreImage();
            }
        });
        btn.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    up.setOpacity(1);
                    cat.minusY();
                    break;
                case S:
                    down.setOpacity(1);
                    cat.plusY();
                    break;
                case A:
                    left.setOpacity(1);
                    cat.minusX();
                    break;
                case D:
                    right.setOpacity(1);
                    cat.plusX();
                    break;
            }
        });
        btn.setOnKeyReleased(event -> {
            right.setOpacity(0.25);
            up.setOpacity(0.25);
            down.setOpacity(0.25);
            left.setOpacity(0.25);
            mainGameLoop();
        });
        pane.getChildren().add(btn);
    }

    @FXML
    void mainGameLoop() {
        checkDeathAndCoins();
        cat.checkCoordinates();
        updateGrid();
    }

    @FXML
    void updateGrid() {
        //lazerpick
        lazers.newRandomXAndY();
        lazers.addLazerObserver(new Lazers.LazerObserver() {
            //Anonymous inner class for handling updates from Lazers
            @Override
            public void update() {
                grid.getChildren().clear();
                grid.add(catImageView, cat.x, cat.y);
                //xxpictures
                for (int i = 0; i < 9; i++) {
                    grid.add(lazerXNowGrid[i], lazers.getCurrentXPosition(), i);
                }
                //ypictures
                for (int i = 0; i < 9; i++) {
                    if (i != lazers.getCurrentXPosition()) {
                        grid.add(lazerYNowGrid[i], i, lazers.getCurrentYPosition());
                    }
                }
                //nextxtonow
                for (int i = 0; i < 9; i++) {
                    grid.add(lazerXNextGrid[i], lazers.getNextXPosition(), i);
                }
                for (int i = 0; i < 9; i++) {
                    if (i != lazers.getNextXPosition()) {
                        grid.add(lazerYNextGrid[i], i, lazers.getNextYPosition());
                    }
                }
            }
        });
        lazers.lazerXAndYNowToNext();
        //addcoins
        grid.add(coinImageView, score.getX(), score.getY());
    }

    @FXML
    void checkDeathAndCoins() {
        if (cat.deadOrNot(lazers.getCurrentXPosition(), lazers.getCurrentYPosition())) {
            score.death();
            deathcounter.setText("Times died " + score.getDeathCounterInt());
            scoreboard.setText("Your score is  " + score.getScoreCoutner());
        }

    if(score.coinPickedOrNot(cat.x,cat.y)){
        score.newRandomCoordinates();
        //newimage(); //kaldes via Observer i stedet for
        scoreboard.setText("Your score is  " + score.getScoreCoutner());
    }
    }

@FXML
void resetGame(javafx.event.ActionEvent event)throws IOException {
        //not correct way to reset game, but it works for now
        Parent root = FXMLLoader.load(getClass().getResource("MainGameView.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    void newScoreImage(){
        switch (score.getPlusScore()) {
            case 1:
                coinImageView.setImage(new Image("assets/bronze.png"));
                break;
            case 2:
                coinImageView.setImage(new Image("assets/silver.png"));
                break;
            case 3:
                coinImageView.setImage(new Image("assets/gold.png"));
                break;
        }
    }
    public void addPictures() {
        for (int i = 0; i < lazerXNowGrid.length; i++) {
            lazerXNowGrid[i] = new ImageView();
            lazerXNowGrid[i].setImage(new Image("assets/Lazer.png", 100, 100, false, false));
        }
        for (int i = 0; i < lazerYNowGrid.length; i++) {
            lazerYNowGrid[i] = new ImageView();
            lazerYNowGrid[i].setImage(new Image("assets/Lazeryværdi.png", 100, 100, false, false));
        }
        for (int i = 0; i < lazerXNextGrid.length; i++) {
            lazerXNextGrid[i] = new ImageView();
            lazerXNextGrid[i].setImage(new Image("assets/Lazernext.png", 100, 100, false, false));
        }
        for (int i = 0; i < lazerYNextGrid.length; i++) {
            lazerYNextGrid[i] = new ImageView();
            lazerYNextGrid[i].setImage(new Image("assets/Lazeryværdinext.png", 100, 100, false, false));
        }
    }
}
