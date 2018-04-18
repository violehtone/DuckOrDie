package duckordie.lebasoft.duck_or_die.state;

import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;


public class MenuState extends State {

    private UIbutton newgamebutton, highscorebutton, howtoplaybutton;

    @Override
    public void init() {
        //button size 583 x 108 px
        newgamebutton = new UIbutton(617, 270, 1200, 378, Assets.newgamebutton, Assets.newgamebutton);
        highscorebutton = new UIbutton(617, 390, 1200, 498, Assets.highscorebutton, Assets.highscorebutton);
        howtoplaybutton = new UIbutton(617, 510, 1200, 618, Assets.howtoplaybutton, Assets.howtoplaybutton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.menustate, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        newgamebutton.render(g);
        highscorebutton.render(g);
        howtoplaybutton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            newgamebutton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (newgamebutton.isPressed(scaledX, scaledY)) {
                setCurrentState(new SelectLevelState());
            }
        }
        return true;
    }
}
