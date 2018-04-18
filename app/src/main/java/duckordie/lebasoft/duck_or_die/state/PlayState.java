package duckordie.lebasoft.duck_or_die.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.model.DuckL;
import duckordie.lebasoft.duck_or_die.model.DuckR;
import duckordie.lebasoft.duck_or_die.util.Painter;

public class PlayState extends State {

    //Ducks
    private DuckL duckL; //182x113 px.
    private DuckR duckR;

    //Points
    int points;
    int level;
    int levelCounter;

    @Override
    public void init() {
        duckL = new DuckL(MainActivity.GAME_WIDTH, 200, 182, 113);
        duckR = new DuckR(0, 400, 182, 113);
        points = 0;
        int level = 1;
        int levelCounter = 0;
    }

    @Override
    public void update(float delta) {
        Assets.duckAnim.update(delta);
        duckL.update(delta);
        Assets.duckAnimR.update(delta);
        duckR.update(delta);
        levelCounter++;

        //Check if game-over
        if(duckL.getX() <= -182 || duckR.getX() > MainActivity.GAME_WIDTH) {
            setCurrentState(new GameOverState(points, level));
        }

        //Levels
        if (levelCounter < 300) {
            level = 1;
        }
        if (levelCounter == 300) {
            level = 2;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 600) {
            level = 3;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 900) {
            level = 4;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 1200) {
            level = 5;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 1500) {
            level = 6;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 1800) {
            level = 7;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 2100) {
            level = 8;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 2400) {
            level = 9;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        } else if (levelCounter == 2700) {
            level = 10;
            duckL.increaseSpeed(3);
            duckR.increaseSpeed(3);
        }
    }

    @Override
    public void render(Painter g) {

        //Render background fill-color
        g.setColor(Color.rgb(195, 250, 255));
        g.fillRect(0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);

        //Render ducks
        if(duckL.getAlive()) {
            Assets.duckAnim.render(g, (int) duckL.getX(), (int) duckL.getY(), duckL.getWidth(), duckL.getHeight());
        }else{
            g.drawImage(Assets.duckLeftDie, (int) duckL.getX(), (int) duckL.getY(), duckL.getHeight(), duckL.getWidth());
        }

        if(duckR.getAlive()) {
            Assets.duckAnimR.render(g, (int) duckR.getX(), (int) duckR.getY(), duckR.getWidth(), duckR.getHeight());
        }else{
            g.drawImage(Assets.duckRightDie, (int) duckR.getX(), (int) duckR.getY(), duckR.getHeight(), duckR.getWidth());
        }

        //Render background image
        g.drawImage(Assets.playstate, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);

        //Render text
        g.setColor(Color.DKGRAY);
        g.setFont(Typeface.DEFAULT_BOLD, 45);
        g.drawString("Ducks shot: " + points, 80, 80);
        g.drawString("Level: " + level, 80, 130);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            Assets.playSound(Assets.gunshot);

            if (duckL.getRect().contains(scaledX, scaledY)) {
                duckL.die();
                points++;
            } else if (duckR.getRect().contains(scaledX, scaledY)) {
                duckR.die();
                points++;
            }
        }
            return true;
    }

}
