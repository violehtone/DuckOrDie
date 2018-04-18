package duckordie.lebasoft.duck_or_die.model;

import android.graphics.Rect;

public class Gun {

    public Rect gunRect;
    public int x,y,width,height;
    public boolean shot;

    public Gun(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        gunRect = new Rect();
        shot = false;
    }

    public void update (float delta) {
        gunRect.set(x, y, x+width, y+height);
    }

    public void setCoordinates(int coorX, int coorY) {
        this.x = coorX;
        this.y = coorY;
    }

    public void shot() {
        shot = true;
    }

    public void setX(int newX) {this.x=newX;}
    public void setY(int newY) {this.y=newY;}
    public void setShot(boolean value) {this.shot=value;}

    public Rect getRect() {return gunRect;}
    public boolean getShot() {return shot;}
    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}

}

