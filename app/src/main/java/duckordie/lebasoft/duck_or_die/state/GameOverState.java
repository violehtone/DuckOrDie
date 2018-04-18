package duckordie.lebasoft.duck_or_die.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;

public class GameOverState extends State {

    private UIbutton continuebutton;
    private int highScore;
    private int levelReached;

    public GameOverState(int points, int level) {
        this.highScore = points;
        this.levelReached = level;
    }

    @Override
    public void init() {
        //button size 583 x 108 px
        continuebutton = new UIbutton(348, 500, 931, 608, Assets.continuebutton, Assets.continuebutton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.DKGRAY);
        g.fillRect(0,0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT_BOLD, 150);
        g.drawString("GAME OVER", 220, 270);

        g.setFont(Typeface.DEFAULT_BOLD, 60);
        g.setColor(Color.GREEN);
        g.drawString("Ducks killed: " + highScore, 430, 370);
        g.setColor(Color.GREEN);
        g.setFont(Typeface.DEFAULT_BOLD, 40);
        g.drawString("You reached level " + levelReached, 415, 440);
        continuebutton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            continuebutton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (continuebutton.isPressed(scaledX, scaledY)) {
                setCurrentState(new MenuState());
            }
        }
        return true;
    }
}
