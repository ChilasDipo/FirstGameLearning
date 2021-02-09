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
    int deathcounterint = 0;
    int scorecounter = 0;
    int plusscore= 0;
    Cat cat = new Cat();
    Lazers lazers = new Lazers();
    Score score = new Score();
    private ImageView coinImageView = new ImageView();
    @FXML
    ImageView[] imageview1to8 = new ImageView[9];
    ImageView[] imageview10to18 = new ImageView[9];
    ImageView[] imageview20to28 = new ImageView[9];
    ImageView[] imageview30to38 = new ImageView[9];
    @FXML
    GridPane grid;
    @FXML
    AnchorPane pane = new AnchorPane();
    @FXML
    Label left,right,up,down,deathcounter,scoreboard;
    @FXML
    void createbutton(){
        Button btn = new Button("ok");
        xxpictures();
        ypictures();
        nextxtonow();
        nextxtonowy();
        newimage();
        score.addScoreObserver(new Score.ScoreObserver() {
            //Anonymous inner class for handling updates from Score
            @Override
            public void update() {
                newimage();
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
            runmetods();
                });
        pane.getChildren().add(btn);
    }
@FXML
    void runmetods(){
        checkdeathandcoins();
        cat.checkCoordinates();
        updategrid(); }
@FXML
void updategrid(){
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
                grid.add(imageview1to8[i],lazers.getLazerxnow(),i);
            }
            //ypictures
            for (int i = 0; i <9 ; i++) {
                if (i != lazers.getLazerxnow() ){
                    grid.add(imageview20to28[i],i,lazers.getLazerynow());
                }
            }
            //nextxtonow
            for (int i = 0; i <9 ; i++) {
                grid.add(imageview10to18[i], lazers.getLazerxnext(),i);
            }
            for (int i = 0; i <9 ; i++) {
                if (i != lazers.getLazerxnext() ){
                    grid.add(imageview30to38[i], i,lazers.getLazerynext());
                }
            }
        }
     });
    lazers.lazerxandynowtonext();
    //addcoins
    plusscore = score.getPlusscore() ;
    grid.add(coinImageView, score.getX(), score.getY());
}
@FXML
void checkdeathandcoins(){
    if (cat.deadornot(lazers.getLazerxnow(),lazers.getLazerynow())){
        deathcounterint++;
        deathcounter.setText("Times died " + deathcounterint);
        if(deathcounterint % 3 == 0){
            scorecounter = 0;
            scoreboard.setText("Your score is  " + scorecounter);
        }
    }
    if (score.pickedornor(cat.x,cat.y)){
        scorecounter = scorecounter + plusscore;
        score.newrandomcoordinates();
        //newimage(); //kaldes via Observer i stedet for
        scoreboard.setText("Your score is  " + scorecounter);
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

    void newimage(){
        switch (plusscore) {
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
        for (int i = 0; i < imageview1to8.length; i++) {
            imageview1to8[i] = new ImageView();
            imageview1to8[i].setImage(new Image("assets/Lazer.png", 100, 100, false, false));
        }
    }

    public void ypictures() {
        for (int i = 0; i < imageview20to28.length; i++) {
            imageview20to28[i] = new ImageView();
            imageview20to28[i].setImage(new Image("assets/Lazeryværdi.png", 100, 100, false, false));
        }
    }

    public void nextxtonow() {
        for (int i = 0; i < imageview10to18.length; i++) {
            imageview10to18[i] = new ImageView();
            imageview10to18[i].setImage(new Image("assets/Lazernext.png", 100, 100, false, false));
        }
    }

    public void nextxtonowy() {
        for (int i = 0; i < imageview30to38.length; i++) {
            imageview30to38[i] = new ImageView();
            imageview30to38[i].setImage(new Image("assets/Lazeryværdinext.png", 100, 100, false, false));
        }
    }
}