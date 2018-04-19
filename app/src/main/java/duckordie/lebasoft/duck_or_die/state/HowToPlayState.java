package duckordie.lebasoft.duck_or_die.state;

import android.graphics.Color;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;

public class HowToPlayState extends State{

    private UIbutton continueButton;


    @Override
    public void init() {
        continueButton = new UIbutton(348, 600, 931, 708, Assets.continuebutton, Assets.continuebutton);
    }

    @Override
    public void update(float delta) {
        Assets.howtoplayAnim.update(delta);
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.howtoplaystateBG, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        Assets.howtoplayAnim.render(g, 208, 147, 864, 427);
        continueButton.render(g);
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
