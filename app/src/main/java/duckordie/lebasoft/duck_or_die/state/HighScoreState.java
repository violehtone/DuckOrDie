package duckordie.lebasoft.duck_or_die.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;

public class HighScoreState extends State {

    private String highScore;
    private UIbutton continueButton;
    private String level;

    @Override
    public void init() {
        highScore = MainActivity.getHighScore() + "";
        continueButton = new UIbutton(348, 500, 931, 608, Assets.continuebutton, Assets.continuebutton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.highscoreState, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        continueButton.render(g);
        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT_BOLD, 150);
        g.drawString(highScore, 550, 350);
        g.setFont(Typeface.DEFAULT_BOLD, 40);
        g.drawString("Ducks shot", 530, 420);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            continueButton.onTouchDown(scaledX,scaledY);
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
            if (continueButton.isPressed(scaledX, scaledY)) {
                setCurrentState(new MenuState());
            } else {
                continueButton.cancel();
            }
        }
        return true;
    }
}
