package logic;
import java.util.ArrayList;
import java.util.Random;

public class Lazers {
    int nextXPosition = 0;
    int currentXPosition = 0;
    int currentYPosition = 0;
    int nextYPosition = 0;

    Random random = new Random();

    public int getNextXPosition() {
        return nextXPosition;
    }

    public int getCurrentXPosition() {
        return currentXPosition;
    }

    public int getCurrentYPosition() {
        return currentYPosition;
    }

    public int getNextYPosition() {
        return nextYPosition;
    }


    public void newRandomXAndY() {
        //lazerpick
        nextXPosition = random.nextInt(9);
        nextYPosition = random.nextInt(9);
        notifyObservers();

    }


    public void lazerXAndYNowToNext() {
        currentXPosition = nextXPosition;
        currentYPosition = nextYPosition;

    }
    private ArrayList<LazerObserver> observers = new ArrayList<LazerObserver>();

    private void notifyObservers() {
        for (LazerObserver observer : observers) {
            observer.update();
        }
    }
    public void addLazerObserver(LazerObserver observer) {
        if (!observers.contains(observers))
            observers.add(observer);
    }
    public void removeLazerObserver(LazerObserver observer) {
        observers.remove(observer);
    }
    public interface LazerObserver {
        public void update();
    }
}