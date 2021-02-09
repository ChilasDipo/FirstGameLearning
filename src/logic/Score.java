package logic;

import java.util.ArrayList;
import java.util.Random;
public class Score {
   private int x;
    private  int y;
    private Random random = new Random();
    private int plusscore;
    public int getPlusscore() { return plusscore+1; }
    public Score() { newrandomcoordinates(); }
    public void newrandomcoordinates(){
        x =  random.nextInt(9);
        y =  random.nextInt(9);
        plusscore = random.nextInt(3);
        notifyObservers();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Boolean pickedornor(int cx, int cy){
        if (x == cx & y == cy ){
            System.out.println("plus one in score");
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
}