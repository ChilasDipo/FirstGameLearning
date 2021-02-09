package logic;
import java.util.ArrayList;
import java.util.Random;

public class Lazers {
    private int nextXPosition = 0;
    private int currentXPosition = 0;
    private int currentYPosition = 0;
    private int nextYPosition = 0;

    private  Random random = new Random();

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
        notifyObservers(1);

    }


    public void lazerXAndYNowToNext() {
        currentXPosition = nextXPosition;
        currentYPosition = nextYPosition;
        notifyObservers(2);

    }
    private ArrayList<LazerObserver> observers = new ArrayList<LazerObserver>();

    private void notifyObservers(int i) {
        for (LazerObserver observer : observers) {
            observer.update(i);
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
        public void update(int i);
    }
}