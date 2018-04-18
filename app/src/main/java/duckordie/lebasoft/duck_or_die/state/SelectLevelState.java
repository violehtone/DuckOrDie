package duckordie.lebasoft.duck_or_die.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;

public class SelectLevelState extends State {

    private UIbutton level1button, level2button, level3button;

    @Override
    public void init() {
        level1button = new UIbutton(215, 300, 465, 550, Assets.level1state, Assets.level1state_pressed);
        level2button = new UIbutton(515, 300, 765, 550, Assets.level2state, Assets.level2state_pressed);
        level3button = new UIbutton(815, 300, 1065, 550, Assets.level3state, Assets.level3state_pressed);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.selectLevelState, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        level1button.render(g);
        level2button.render(g);
        level3button.render(g);

        g.setColor(Color.CYAN);
        g.setFont(Typeface.DEFAULT_BOLD, 30);
        g.drawString("Summer", 275, 280);
        g.drawString("Winter", 585, 280);
        g.drawString("Sea", 900, 280);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            level1button.onTouchDown(scaledX, scaledY);
            level2button.onTouchDown(scaledX, scaledY);
            level3button.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {

            if (level1button.isPressed(scaledX, scaledY)) {
                level2button.cancel();
                level3button.cancel();
                setCurrentState(new SelectGunState(LEVEL.SUMMER));
            }else if(level2button.isPressed(scaledX, scaledY)) {
                level1button.cancel();
                level3button.cancel();
                setCurrentState(new SelectGunState(LEVEL.WINTER));
            }else if(level3button.isPressed(scaledX, scaledY)) {
                level1button.cancel();
                level2button.cancel();
                setCurrentState(new SelectGunState(LEVEL.SEA));
            }

            level2button.cancel();
            level1button.cancel();
            level3button.cancel();
        }
        return true;
    }
}
