package duckordie.lebasoft.duck_or_die.model;

import android.graphics.Rect;

import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.RandomNumberGenerator;

public class DuckL {

    public float x, y;
    public int width, height, duckSpeed, fallSpeed;
    public Rect duckRect;
    public boolean isAlive;

    public DuckL(float x, float y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        duckRect = new Rect();
        duckSpeed = 5;
        isAlive = true;
        fallSpeed = 0;
    }

    public void update(float delta) {
        if(isAlive) {
            x -= duckSpeed;
        }
        y += fallSpeed;
        duckRect.set((int) x, (int) y, (int) x+width, (int) y+height);

        if (y >= MainActivity.GAME_HEIGHT + 100) {
            relocate();
        }

    }

    public void relocate() {
        isAlive=true;
        fallSpeed = 0;
        setX(MainActivity.GAME_WIDTH + RandomNumberGenerator.getRandomIntBetween(500,1000));
        setY(RandomNumberGenerator.getRandomIntBetween(50, MainActivity.GAME_HEIGHT - 280));
    }

    public void die() {
        isAlive = false;
        fallSpeed = 20;
    }

    public void increaseSpeed(float inc) {
        duckSpeed += inc;
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Rect getRect() {return duckRect;}
    public boolean getAlive() {return isAlive;}

    public void setX(float newX) {x = newX;}
    public void setY(float newY) {y = newY;}
    public void setAlive(boolean value) {isAlive = value;}






}
