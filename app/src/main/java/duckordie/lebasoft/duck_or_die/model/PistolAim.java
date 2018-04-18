package duckordie.lebasoft.duck_or_die.model;

import android.graphics.Rect;

public class PistolAim {


    public Rect aimRect;
    public int x,y,width,height;

    public PistolAim(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        aimRect = new Rect();
    }

    public void update (float delta) {
        aimRect.set(x, y, x+width, y+height);
    }

    public void setCoordinates(int coorX, int coorY) {
        this.x = coorX;
        this.y = coorY;
    }

    public void setX(int newX) {this.x=newX;}
    public void setY(int newY) {this.y=newY;}

    public Rect getRect() {return aimRect;}
    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}

}


