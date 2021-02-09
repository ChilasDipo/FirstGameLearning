package logic;
import java.util.ArrayList;
import java.util.Random;

public class Lazers {
    int lazerxnext = 0;
    int lazerxnow = 0;
    int lazerynow = 0;
    int lazerynext = 0;
    Random random = new Random();
    public Lazers() {

    }

    public int getLazerxnext() {
        return lazerxnext;
    }

    public int getLazerxnow() {
        return lazerxnow;
    }

    public int getLazerynow() {
        return lazerynow;
    }

    public int getLazerynext() {
        return lazerynext;
    }


    public void newrandomxandy() {
        //lazerpick
        lazerxnext = random.nextInt(9);
        lazerynext = random.nextInt(9);
        notifyObservers();

    }


    public void lazerxandynowtonext() {
        lazerxnow = lazerxnext;
        lazerynow = lazerynext;

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