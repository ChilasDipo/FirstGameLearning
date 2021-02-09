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
public class Controller  {
    int deathCounterInt = 0;
    int scoreCounter = 0;
    int plusScore = 0;
    Cat cat = new Cat();
    Lazers lazers = new Lazers();
    Score score = new Score();
    private ImageView coinImageView = new ImageView();
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
    Label left,right,up,down,deathcounter,scoreboard;
    @FXML
    void createbutton(){
        Button btn = new Button("Ok");
        xxpictures();
        ypictures();
        nextxtonow();
        nextxtonowy();
        newScoreImage();
        score.addScoreObserver(new Score.ScoreObserver() {
            //Anonymous inner class for handling updates from Score
            @Override
            public void update() {
                newScoreImage();
            }
        });
        btn.setOnKeyPressed( event ->{
            switch (event.getCode()){
                case W:
                    up.setOpacity(1);
                    cat.minusy();
                    break;
                case S:
                    down.setOpacity(1);
                    cat.plusy();
                    break;
                case A:
                    left.setOpacity(1);
                    cat.minusx();
                    break;
                case D:
                    right.setOpacity(1);
                    cat.plusx();
                    break;
                }
        });
        btn.setOnKeyReleased( event ->{
                    right.setOpacity(0.25);
                    up.setOpacity(0.25);
                    down.setOpacity(0.25);
                    left.setOpacity(0.25);
            mainGameLoop();
                });
        pane.getChildren().add(btn);
    }
@FXML
    void mainGameLoop(){
        checkDeathAndCoins();
        cat.checkCoordinates();
        updateGrid(); }
@FXML
void updateGrid(){
    //lazerpick
     lazers.newrandomxandy();
     lazers.addLazerObserver(new Lazers.LazerObserver() {
        //Anonymous inner class for handling updates from Lazers
        @Override
        public void update() {
            grid.getChildren().clear();
            grid.add(cat.getImageView() ,cat.x,cat.y);
            //xxpictures
            for (int i = 0; i <9 ; i++) {
                grid.add(lazerXNowGrid[i],lazers.getLazerxnow(),i);
            }
            //ypictures
            for (int i = 0; i <9 ; i++) {
                if (i != lazers.getLazerxnow() ){
                    grid.add(lazerYNowGrid[i],i,lazers.getLazerynow());
                }
            }
            //nextxtonow
            for (int i = 0; i <9 ; i++) {
                grid.add(lazerXNextGrid[i], lazers.getLazerxnext(),i);
            }
            for (int i = 0; i <9 ; i++) {
                if (i != lazers.getLazerxnext() ){
                    grid.add(lazerYNextGrid[i], i,lazers.getLazerynext());
                }
            }
        }
     });
    lazers.lazerxandynowtonext();
    //addcoins
    plusScore = score.getPlusscore() ;
    grid.add(coinImageView, score.getX(), score.getY());
}
@FXML
void checkDeathAndCoins(){
    if (cat.deadornot(lazers.getLazerxnow(),lazers.getLazerynow())){
        deathCounterInt++;
        deathcounter.setText("Times died " + deathCounterInt);
        if(deathCounterInt % 3 == 0){
            scoreCounter = 0;
            scoreboard.setText("Your score is  " + scoreCounter);
        }
    }
    if (score.pickedornor(cat.x,cat.y)){
        scoreCounter = scoreCounter + plusScore;
        score.newrandomcoordinates();
        //newimage(); //kaldes via Observer i stedet for
        scoreboard.setText("Your score is  " + scoreCounter);
    }
}
@FXML
void resetgame(javafx.event.ActionEvent event)throws IOException {
        //not correct way to reset game, but it works for now
        Parent blah = FXMLLoader.load(getClass().getResource("../sample.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    void newScoreImage(){
        switch (plusScore) {
            case 0:
                coinImageView.setImage(new Image("assets/bronze.png"));
                break;
            case 1:
                coinImageView.setImage(new Image("assets/silver.png"));
                break;
            case 2:
                coinImageView.setImage(new Image("assets/gold.png"));
                break;
        }
    }
    public void xxpictures() {
        for (int i = 0; i < lazerXNowGrid.length; i++) {
            lazerXNowGrid[i] = new ImageView();
            lazerXNowGrid[i].setImage(new Image("assets/Lazer.png", 100, 100, false, false));
        }
    }

    public void ypictures() {
        for (int i = 0; i < lazerYNowGrid.length; i++) {
            lazerYNowGrid[i] = new ImageView();
            lazerYNowGrid[i].setImage(new Image("assets/Lazeryværdi.png", 100, 100, false, false));
        }
    }

    public void nextxtonow() {
        for (int i = 0; i < lazerXNextGrid.length; i++) {
            lazerXNextGrid[i] = new ImageView();
            lazerXNextGrid[i].setImage(new Image("assets/Lazernext.png", 100, 100, false, false));
        }
    }

    public void nextxtonowy() {
        for (int i = 0; i < lazerYNextGrid.length; i++) {
            lazerYNextGrid[i] = new ImageView();
            lazerYNextGrid[i].setImage(new Image("assets/Lazeryværdinext.png", 100, 100, false, false));
        }
    }
}