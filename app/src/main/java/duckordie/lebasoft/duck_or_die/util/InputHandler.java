package duckordie.lebasoft.duck_or_die.util;

import android.view.MotionEvent;
import android.view.View;

import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.state.State;

public class InputHandler implements View.OnTouchListener {

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState=currentState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int) ((event.getX() / v.getWidth()) * MainActivity.GAME_WIDTH);
        int scaledY = (int) ((event.getY() / v.getHeight()) * MainActivity.GAME_HEIGHT);

        return currentState.onTouch(event, scaledX, scaledY);
    }
}