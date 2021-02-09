package logic;
public class Cat {
    public int x ;
    public int y ;
    public Cat() {
         x = 2;
         y = 2;
    }

    public void plusX(){
        x++;
    }

    public void plusY(){
        y++;
    }

    public void minusX(){
        x--;
    }

    public void minusY(){
        y--;
    }

    public Boolean deadOrNot(int lx , int yl){
        if (x == lx | y == yl ){
            System.out.println("you died");
            return true;
        }
        return false;
    }

    public void checkCoordinates(){
        if (x < 0){ x = 0; }
        if (y < 0){ y = 0; }
        if (x > 8){ x = 8; }
        if (y > 8){ y = 8; }
    }
}