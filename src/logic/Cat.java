package logic;
public class Cat {
    private int x ;
    private int y ;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cat() {
         x = 2;
         y = 2;
    }

    public void plusX(){
        x++;checkCoordinates();
    }

    public void plusY(){
        y++;checkCoordinates();
    }

    public void minusX(){
        x--;checkCoordinates();
    }

    public void minusY(){
        y--;
        checkCoordinates();
    }

    public Boolean deadOrNot(int lx , int yl){
        if (x == lx | y == yl ){
            System.out.println("you died");
            return true;
        }
        return false;
    }

    private void checkCoordinates(){
        if (x < 0){ x = 1; }
        if (y < 0){ y = 1; }
        if (x > 8){ x = 7; }
        if (y > 8){ y = 7; }
    }
}