package duckordie.lebasoft.duck_or_die.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.renderscript.BaseObj;
import android.view.MotionEvent;

import duckordie.lebasoft.duck_or_die.main.Assets;
import duckordie.lebasoft.duck_or_die.main.MainActivity;
import duckordie.lebasoft.duck_or_die.model.Aim;
import duckordie.lebasoft.duck_or_die.model.Bazooka;
import duckordie.lebasoft.duck_or_die.model.BazookaAim;
import duckordie.lebasoft.duck_or_die.model.DuckL;
import duckordie.lebasoft.duck_or_die.model.DuckR;
import duckordie.lebasoft.duck_or_die.model.Gun;
import duckordie.lebasoft.duck_or_die.model.Pistol;
import duckordie.lebasoft.duck_or_die.model.PistolAim;
import duckordie.lebasoft.duck_or_die.util.Painter;
import duckordie.lebasoft.duck_or_die.util.UIbutton;

public class Level1State extends State {

    private boolean gamePaused = false;
    private String pausedString = "Game Paused. Tap the pause button again to continue";

    //Things
    private DuckL duckL; //182x113 px.
    private DuckR duckR;
    private Aim aim;
    private BazookaAim bazookaAim;
    private Gun gun;
    private Bazooka bazooka;
    private Pistol pistol;
    private PistolAim pistolAim;
    private UIbutton shootButton, loadButton, pauseButton; //200 x 200

    //Points
    int points;
    int bullets;
    int level;
    int levelCounter;

    //Weapon & Level
    WEAPON weapon;
    LEVEL selectedLevel;

    public Level1State(WEAPON wpn, LEVEL lvl) {
        this.weapon = wpn;
        this.selectedLevel = lvl;
    }

    @Override
    public void init() {
        duckL = new DuckL(MainActivity.GAME_WIDTH, 200, 182, 113);
        duckR = new DuckR(0, 400, 182, 113);
        points = 0;
        level = 1;
        levelCounter = 0;

        //rifle
        if (weapon == WEAPON.RIFLE) {
            aim = new Aim(400, 300, 200, 200);
            gun = new Gun(515, 495, 250, 250);
            bullets = 5;


        //Bazooka
        } else if (weapon == WEAPON.BAZOOKA) {
            bazookaAim = new BazookaAim(400, 300, 300, 300);
            bazooka = new Bazooka(515, 495, 250, 250);
            bullets = 2;

        //pistol
        }
        else if (weapon == WEAPON.PISTOL) {
            pistolAim = new PistolAim(400, 300, 100, 100);
            pistol = new Pistol(515, 495, 250, 250);
            bullets = 10;
        }

        //Buttons (120 x 120 px)
        shootButton = new UIbutton(50, 590, 170, 710, Assets.shootButton, Assets.shootButtonPressed);
        loadButton = new UIbutton(180, 590, 300, 710, Assets.loadButton, Assets.loadButtonPressed);
        pauseButton = new UIbutton(1045, 590, 1165, 710, Assets.pause_button, Assets.pauseButton_pressed);
    }

    @Override public void onPause() {
        gamePaused = false;
    }

    @Override
    public void update(float delta) {
        if(gamePaused) {
            return;
        }

        Assets.duckAnim.update(delta);
        duckL.update(delta);
        Assets.duckAnimR.update(delta);
        duckR.update(delta);

        //UPDATE WEAPON & AIM
        if (weapon == WEAPON.RIFLE) {
            aim.update(delta);
            gun.update(delta);
        } else if (weapon == WEAPON.BAZOOKA) {
            bazookaAim.update(delta);
            bazooka.update(delta);
        }else if(weapon == WEAPON.PISTOL) {
            pistolAim.update(delta);
            pistol.update(delta);
        }

        levelCounter++;

        //CHECK IF GAME OVER
        if (duckL.getX() <= -182 || duckR.getX() > MainActivity.GAME_WIDTH) {
            setCurrentState(new GameOverState(points, level));
        }

        if (levelCounter % 300 == 0) {
            level ++;
            duckL.increaseSpeed(2);
            duckR.increaseSpeed(2);
        }

        /*
        //LEVELS 1-10
        if (levelCounter < 300) {
            level = 1;
        }
        if (levelCounter == 300) {
            level = 2;
            duckL.increaseSpeed(2);
            duckR.increaseSpeed(2);
        } else if (levelCounter == 600) {
            level = 3;
            duckL.increaseSpeed(1);
            duckR.increaseSpeed(1);
        } else if (levelCounter == 900) {
            level = 4;
            duckL.increaseSpeed(2);
            duckR.increaseSpeed(2);
        } else if (levelCounter == 1200) {
            level = 5;
            duckL.increaseSpeed(1);
            duckR.increaseSpeed(1);
        } else if (levelCounter == 1500) {
            level = 6;
            duckL.increaseSpeed(2);
            duckR.increaseSpeed(2);
        } else if (levelCounter == 1800) {
            level = 7;
            duckL.increaseSpeed(1);
            duckR.increaseSpeed(1);
        } else if (levelCounter == 2100) {
            level = 8;
            duckL.increaseSpeed(2);
            duckR.increaseSpeed(2);
        } else if (levelCounter == 2400) {
            level = 9;
            duckL.increaseSpeed(1);
            duckR.increaseSpeed(1);
        } else if (levelCounter == 2700) {
            level = 10;
            duckL.increaseSpeed(1);
            duckR.increaseSpeed(1);
        }*/
    }

    @Override
    public void render(Painter g) {

        //Render background fill-color
        if(selectedLevel == LEVEL.SUMMER) {
            g.setColor(Color.rgb(195, 250, 255));
            g.fillRect(0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        }else if (selectedLevel == LEVEL.WINTER) {
            g.setColor(Color.rgb(1, 0, 128));
            g.fillRect(0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        }else if (selectedLevel == LEVEL.SEA) {
            g.setColor(Color.rgb(134, 204, 255));
            g.fillRect(0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        }

        //Render ducks
        if (duckL.getAlive()) {
            Assets.duckAnim.render(g, (int) duckL.getX(), (int) duckL.getY(), duckL.getWidth(), duckL.getHeight());
        } else {
            g.drawImage(Assets.duckLeftDie, (int) duckL.getX(), (int) duckL.getY(), duckL.getHeight(), duckL.getWidth());
        }

        if (duckR.getAlive()) {
            Assets.duckAnimR.render(g, (int) duckR.getX(), (int) duckR.getY(), duckR.getWidth(), duckR.getHeight());
        } else {
            g.drawImage(Assets.duckRightDie, (int) duckR.getX(), (int) duckR.getY(), duckR.getHeight(), duckR.getWidth());
        }

        //Render background image
        if(selectedLevel == LEVEL.SUMMER) {
            g.drawImage(Assets.playstate, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        }else if (selectedLevel == LEVEL.WINTER) {
            g.drawImage(Assets.winterstate, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        }else if (selectedLevel == LEVEL.SEA) {
            g.drawImage(Assets.seastate, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        }

        //Render text
        if(selectedLevel == LEVEL.WINTER) {
            g.setColor(Color.WHITE);
        }else {
            g.setColor(Color.DKGRAY);
        }

        g.setFont(Typeface.DEFAULT_BOLD, 35);
        g.drawString("Ducks shot: " + points, 40, 50);
        g.drawString("Level: " + level, 40, 90);
        g.drawString("Bullets: " + bullets, 40, 130);

        //Render aim & gun
        //RIFLE
        if (weapon == WEAPON.RIFLE) {
            g.drawImage(Assets.aim, aim.getX(), aim.getY(), aim.getWidth(), aim.getHeight());
            if (gun.getShot()) {
                g.drawImage(Assets.gun_shot, gun.getX(), gun.getY(), gun.getWidth(), gun.getHeight());
            } else {
                g.drawImage(Assets.gun, gun.getX(), gun.getY(), gun.getWidth(), gun.getHeight());
            }
            gun.setShot(false);

        //BAZOOKA
        } else if (weapon == WEAPON.BAZOOKA) {
            g.drawImage(Assets.bazookaAim, bazookaAim.getX(), bazookaAim.getY(), bazookaAim.getWidth(), bazookaAim.getHeight());
            if (bazooka.getShot()) {
                g.drawImage(Assets.bazooka_shot, bazooka.getX(), bazooka.getY(), bazooka.getWidth(), bazooka.getHeight());
            } else {
                g.drawImage(Assets.bazooka, bazooka.getX(), bazooka.getY(), bazooka.getWidth(), bazooka.getHeight());
            }
            bazooka.setShot(false);

        //PISTOL
        } else if (weapon == WEAPON.PISTOL) {
        g.drawImage(Assets.pistolAim, pistolAim.getX(), pistolAim.getY(), pistolAim.getWidth(), pistolAim.getHeight());
        if (pistol.getShot()) {
            g.drawImage(Assets.pistol_shot, pistol.getX(), pistol.getY(), pistol.getWidth(), pistol.getHeight());
        } else {
            g.drawImage(Assets.pistol, pistol.getX(), pistol.getY(), pistol.getWidth(), pistol.getHeight());
        }
            pistol.setShot(false);
        }

        //render buttons
        g.drawImage(Assets.buttonSpace, 40, 542, 270, 178); //270 x 178
        shootButton.render(g);
        loadButton.render(g);
        g.drawImage(Assets.pauseButtonSpace, 970, 542, 270, 178); //270 x 178
        pauseButton.render(g);

        if(gamePaused) {
            g.setColor(Color.argb(153, 0, 0, 0));
            g.fillRect(0,0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
            g.setFont(Typeface.DEFAULT_BOLD, 45);
            g.drawString(pausedString, 90, 300);
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            shootButton.onTouchDown(scaledX, scaledY);
            pauseButton.onTouchDown(scaledX, scaledY);

            if (bullets <= 0) {
                loadButton.onTouchDown(scaledX, scaledY);
            }

            //RIFLE
            if (weapon == WEAPON.RIFLE) {
                if (shootButton.getButtonRect().contains(scaledX, scaledY) && bullets > 0) {
                    if (gamePaused == false) {
                        gun.shot();
                        Assets.playSound(Assets.gunshot);
                        bullets--;
                        loadButton.cancel();
                        pauseButton.cancel();

                        if (aim.getRect().contains((int) duckL.getX() + (duckL.getWidth() / 2), (int) duckL.getY() + (duckL.getHeight() / 2))) {
                            duckL.die();
                            points++;
                        } else if (aim.getRect().contains((int) duckR.getX() + (duckR.getWidth() / 2), (int) duckR.getY() + (duckR.getHeight() / 2))) {
                            duckR.die();
                            points++;
                        }
                    }
                } else if (loadButton.getButtonRect().contains(scaledX, scaledY) && bullets <= 0) {
                    if (gamePaused == false) {
                        Assets.playSound(Assets.loadgun);
                        bullets += 5;
                    }
                } else if (pauseButton.getButtonRect().contains(scaledX, scaledY)) {
                    if (gamePaused == false) {
                        gamePaused = true;
                    } else {
                        gamePaused = false;
                    }
                }

                    //BAZOOKA
                } else if (weapon == WEAPON.BAZOOKA) {
                    if (shootButton.getButtonRect().contains(scaledX, scaledY) && bullets > 0) {
                        if (gamePaused == false) {
                            bazooka.shot();
                            Assets.playSound(Assets.bazookaSound);
                            bullets--;
                            loadButton.cancel();
                            pauseButton.cancel();

                            if (bazookaAim.getRect().contains((int) duckL.getX() + (duckL.getWidth() / 2), (int) duckL.getY() + (duckL.getHeight() / 2))) {
                                duckL.die();
                                points++;
                            } else if (bazookaAim.getRect().contains((int) duckR.getX() + (duckR.getWidth() / 2), (int) duckR.getY() + (duckR.getHeight() / 2))) {
                                duckR.die();
                                points++;
                            }
                        }
                    } else if (loadButton.getButtonRect().contains(scaledX, scaledY) && bullets <= 0) {
                        if (gamePaused == false) {
                            Assets.playSound(Assets.loadgun);
                            bullets += 2;
                        }

                    } else if (pauseButton.getButtonRect().contains(scaledX, scaledY)) {
                        if (gamePaused == false) {
                            gamePaused = true;
                        } else {
                            gamePaused = false;
                        }
                    }



                        //PISTOL
                    } else if (weapon == WEAPON.PISTOL) {
                        if (shootButton.getButtonRect().contains(scaledX, scaledY) && bullets > 0) {
                            if (gamePaused == false) {
                                pistol.shot();
                                Assets.playSound(Assets.gunshot);
                                bullets--;
                                loadButton.cancel();
                                pauseButton.cancel();

                                if (pistolAim.getRect().contains((int) duckL.getX() + (duckL.getWidth() / 2), (int) duckL.getY() + (duckL.getHeight() / 2))) {
                                    duckL.die();
                                    points++;
                                } else if (pistolAim.getRect().contains((int) duckR.getX() + (duckR.getWidth() / 2), (int) duckR.getY() + (duckR.getHeight() / 2))) {
                                    duckR.die();
                                    points++;
                                }
                            }
                        } else if (loadButton.getButtonRect().contains(scaledX, scaledY) && bullets <= 0) {
                            if (gamePaused == false) {
                                Assets.playSound(Assets.loadgun);
                                bullets += 10;
                                shootButton.cancel();
                                pauseButton.cancel();
                            }
                        } else if (pauseButton.getButtonRect().contains(scaledX, scaledY)) {
                            if (gamePaused == false) {
                                gamePaused = true;
                            } else {
                                gamePaused = false;
                            }
                        }

                    }
                }

        if (e.getAction() == MotionEvent.ACTION_MOVE && !shootButton.getButtonRect().contains(scaledX, scaledY) &&
                !loadButton.getButtonRect().contains(scaledX, scaledY) && !pauseButton.getButtonRect().contains(scaledX, scaledY)) {

            if (gamePaused) {

            } else {

                if (weapon == WEAPON.RIFLE) {
                    aim.setCoordinates(scaledX - (aim.getWidth() / 2), scaledY - (aim.getHeight() / 2));
                } else if (weapon == WEAPON.BAZOOKA) {
                    bazookaAim.setCoordinates(scaledX - (bazookaAim.getWidth() / 2), scaledY - (bazookaAim.getHeight() / 2));
                } else if (weapon == WEAPON.PISTOL) {
                    pistolAim.setCoordinates(scaledX - (pistolAim.getWidth() / 2), scaledY - (pistolAim.getHeight() / 2));
                }
            }
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
/*
            if(gamePaused) {
                gamePaused = false;
                return true;
            }*/

            shootButton.cancel();
            loadButton.cancel();
        }
        return true;
    }
}
