package logic;
import java.util.ArrayList;
import java.util.Random;
public class Score {
    private int deathCounterInt;
    private int scoreCoutner;

    public int getScoreCoutner() {
        return scoreCoutner;
    }

    public int getDeathCounterInt() {
        return deathCounterInt;
    }

    private int x;
    private  int y;
    private Random random = new Random();
    private int plusScore;
    public int getPlusScore() { return plusScore; }
    public Score() { newRandomCoordinates(); }
    public void newRandomCoordinates(){
        x =  random.nextInt(9);
        y =  random.nextInt(9);
        plusScore = random.nextInt(3)+1;
        notifyObservers();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Boolean coinPickedOrNot(int cx, int cy){
        if (x == cx & y == cy ){
            System.out.println("plus one in score");
            scoreCoutner = scoreCoutner + plusScore;
            return true;
        }
        return false;
    }

    private ArrayList<ScoreObserver> observers = new ArrayList<ScoreObserver>();

    private void notifyObservers() {
        for (ScoreObserver observer : observers) {
            observer.update();
        }
    }

    public void addScoreObserver(ScoreObserver observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }

    public void removeScoreObserver(ScoreObserver observer) {
        observers.remove(observer);
    }

    public interface ScoreObserver {
        public void update();
    }
    public void death() {
        deathCounterInt++;
        if(deathCounterInt % 3 == 0) {
            scoreCoutner = 0;
    }
}

}