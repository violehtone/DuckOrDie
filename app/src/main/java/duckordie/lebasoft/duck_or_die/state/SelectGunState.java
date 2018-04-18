package duckordie.lebasoft.duck_or_die.state;


import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;

public class SelectGunState extends State {

    private UIbutton rifleButton, pistolButton, bazookaButton;
    private LEVEL selectedLevel;

    public SelectGunState(LEVEL level) {
        this.selectedLevel = level;
    }

    @Override
    public void init() {
        rifleButton = new UIbutton(215, 300, 465, 550, Assets.rifleImage, Assets.rifleImage_pressed);
        pistolButton = new UIbutton(515, 300, 765, 550, Assets.pistolImage, Assets.pistolImage_pressed);
        bazookaButton = new UIbutton(815, 300, 1065, 550, Assets.bazookaImage, Assets.bazookaImage_pressed);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.selectWeaponState, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        rifleButton.render(g);
        pistolButton.render(g);
        bazookaButton.render(g);

        g.setColor(Color.RED);
        g.setFont(Typeface.DEFAULT_BOLD, 35);
        g.drawString("Good ol' rifle", 255, 280);
        g.drawString("Pistol", 590, 280);
        g.drawString("Bazooka", 880, 280);

        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT_BOLD, 20);

        g.drawString("Magazine size: 5", 260, 590);
        g.drawString("Sight size: medium", 250, 630);

        g.drawString("Magazine size: 10", 550, 590);
        g.drawString("Sight size: small", 555, 630);

        g.drawString("Magazine size: 2", 860, 590);
        g.drawString("Sight size: large", 860, 630);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            rifleButton.onTouchDown(scaledX, scaledY);
            bazookaButton.onTouchDown(scaledX, scaledY);
            pistolButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (rifleButton.isPressed(scaledX, scaledY)) {
                pistolButton.cancel();
                bazookaButton.cancel();
                setCurrentState(new Level1State(WEAPON.RIFLE, selectedLevel));

            }else if (bazookaButton.isPressed(scaledX, scaledY)) {
                rifleButton.cancel();
                pistolButton.cancel();
                setCurrentState(new Level1State(WEAPON.BAZOOKA, selectedLevel));

            }else if(pistolButton.isPressed(scaledX, scaledY)) {
                bazookaButton.cancel();
                rifleButton.cancel();
                setCurrentState(new Level1State(WEAPON.PISTOL, selectedLevel));
            }

            rifleButton.cancel();
            bazookaButton.cancel();
            pistolButton.cancel();
        }
        return true;
    }
}

