package duckordie.lebasoft.duck_or_die.state;

import android.view.MotionEvent;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;


public abstract class State {

    public void setCurrentState (State newState) {
        MainActivity.game.setCurrentState(newState);
    }
    public abstract void init();
    public abstract void update(float delta);
    public abstract void render(Painter g);
    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public void onResume() {}
    public void onPause() {}
}
