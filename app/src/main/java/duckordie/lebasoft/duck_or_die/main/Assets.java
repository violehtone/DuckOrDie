package duckordie.lebasoft.duck_or_die.main;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;
import java.io.InputStream;

import duckordie.lebasoft.duck_or_die.animation.Animation;
import duckordie.lebasoft.duck_or_die.animation.Frame;


public class Assets {

    private static SoundPool soundPool;
    //Backgrounds
    public static Bitmap playstate, storystate, menustate, selectLevelState, selectWeaponState, seastate, winterstate, highscoreState,
    howtoplaystate1, howtoplaystate2, howtoplaystate3, howtoplaystate4, howtoplaystate5, howtoplaystate6, howtoplaystateBG;
    //Buttons
    public static Bitmap newgamebutton, continuebutton, howtoplaybutton, highscorebutton, shootButton, loadButton,
            level1state, level2state, level3state, rifleImage, bazookaImage, pistolImage, buttonSpace,
            shootButtonPressed, loadButtonPressed, level1state_pressed, level2state_pressed, level3state_pressed,
            bazookaImage_pressed, pistolImage_pressed, rifleImage_pressed, pauseButton_pressed, pauseButtonSpace, pause_button;
    //Models
    public static Bitmap duckLeft1, duckLeft2, duckRight1, duckLeftDie, duckRightDie, duckRight2,
                        aim, gun, gun_shot, bazooka, bazooka_shot, bazookaAim,
                        pistol, pistolAim, pistol_shot;
    public static int themeMusic, gunshot, loadgun, bazookaSound;
    private static MediaPlayer mediaPlayer;
    public static Animation duckAnim, duckAnimR, howtoplayAnim;

    public static void load() {
        //Backgrounds
        playstate = loadBitmap("playstate.png", false);
        seastate = loadBitmap("seastate.png" ,false);
        winterstate = loadBitmap("winterstate.png", false);

        storystate = loadBitmap("storystate.png", false);
        selectLevelState = loadBitmap("SelectLevelState.png", false);
        menustate = loadBitmap("menuscreen.png", false);
        selectWeaponState = loadBitmap("SelectWeaponState.png", false);
        highscoreState = loadBitmap("highscoreState.png", false);

        howtoplaystate1 = loadBitmap("howtoplay1.png", false);
        howtoplaystate2 = loadBitmap("howtoplay2.png", false);
        howtoplaystate3 = loadBitmap("howtoplay3.png", false);
        howtoplaystate4 = loadBitmap("howtoplay4.png", false);
        howtoplaystate5 = loadBitmap("howtoplay5.png", false);
        howtoplaystate6 = loadBitmap("howtoplay6.png", false);
        howtoplaystateBG = loadBitmap("howtoplaystate_background.png", false);


        //Buttons
        newgamebutton = loadBitmap("newgamebutton.png", false);
        highscorebutton = loadBitmap("highscorebutton.png", false);
        howtoplaybutton = loadBitmap("howtoplaybutton.png", false);
        continuebutton = loadBitmap("continuebutton.png", false);
        level1state = loadBitmap("Level1_image.png", false);
        level1state_pressed = loadBitmap("Level1_image_pressed.png", false);
        level2state = loadBitmap("Level2_image.png", false);
        level2state_pressed = loadBitmap("Level2_image_pressed.png", false);
        level3state = loadBitmap("level3_image.png", false);
        level3state_pressed = loadBitmap("level3_image_pressed.png", false);
        rifleImage = loadBitmap("rifle_image.png", false);
        pistolImage = loadBitmap("pistol_image.png", false);
        bazookaImage = loadBitmap("bazooka_image.png", false);
        shootButton = loadBitmap("shootButton.png", false);
        loadButton = loadBitmap("loadButton.png", false);
        buttonSpace = loadBitmap("buttonspace.png", false);
        shootButtonPressed = loadBitmap("shootButton_pressed.png", false);
        loadButtonPressed = loadBitmap("loadButton_pressed.png", false);
        pistolImage_pressed = loadBitmap("pistol_image_pressed.png", false);
        rifleImage_pressed = loadBitmap("rifle_image_pressed.png", false);
        bazookaImage_pressed = loadBitmap("bazooka_image_pressed.png", false);
        pauseButton_pressed = loadBitmap("pauseButton_pressed.png", false);
        pauseButtonSpace = loadBitmap("pauseButtonSpace.png", false);
        pause_button = loadBitmap("pause_Button.png", false);

        //Models
        duckLeft1 = loadBitmap("duck_left1.png", true);
        duckLeft2 = loadBitmap("duck_left2.png", true);
        duckLeftDie = loadBitmap("duck_left_die.png", true);
        duckRight1 = loadBitmap("duck_right1.png", true);
        duckRight2 = loadBitmap("duck_right2.png", true);
        duckRightDie = loadBitmap("duck_right_die.png", true);

        aim = loadBitmap("aim.png", false);
        gun = loadBitmap("gun.png", false);
        gun_shot = loadBitmap("gun_shot.png", false);

        bazooka = loadBitmap("bazooka.png", false);
        bazooka_shot = loadBitmap("bazooka_shot.png" ,false);
        bazookaAim = loadBitmap("aimBazooka.png", false);

        pistol = loadBitmap("pistol.png", false);
        pistol_shot = loadBitmap("pistol_shot.png", false);
        pistolAim = loadBitmap("aimPistol.png", false);

        //Music
        themeMusic = loadSound("theme.mp3");
        gunshot = loadSound("gunshot.wav");
        loadgun = loadSound("loadgun.wav");
        bazookaSound = loadSound("bazookaSound.wav");

        //frames & Animations
        Frame f1 = new Frame(duckLeft1, .1f);
        Frame f2 = new Frame(duckLeft2, .1f);
        duckAnim = new Animation(f1, f2);

        Frame f11 = new Frame(duckRight1, .1f);
        Frame f12 = new Frame(duckRight2, .1f);
        duckAnimR = new Animation(f11, f12);

        Frame htp1 = new Frame(howtoplaystate1, 1f);
        Frame htp2 = new Frame(howtoplaystate2, 3f);
        Frame htp3 = new Frame(howtoplaystate3, 5f);
        Frame htp4 = new Frame(howtoplaystate4, 5f);
        Frame htp5 = new Frame(howtoplaystate5, 8f);
        Frame htp6 = new Frame(howtoplaystate6, 8f);
        howtoplayAnim = new Animation(htp1, htp2, htp3, htp4, htp5, htp6);

    }

    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;

        try {
            inputStream = MainActivity.assets.open(filename);
        }catch(IOException e) {
            e.printStackTrace();
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        if(transparency) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }else{
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new BitmapFactory.Options());
        return bitmap;
    }

    private static int loadSound (String filename) {
        int soundID = 0;

        if(soundPool == null) {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC,0);
        }

        try {
            soundID = soundPool.load(MainActivity.assets.openFd(filename),1);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return soundID;
    }

    public static void playSound(int soundID) {
        if (soundPool != null) {
            soundPool.play(soundID, 1, 1, 1, 0, 1);
        }
    }

    public static void onResume() {
        playMusic("theme.mp3", true);
    }

    public static void onPause() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void playMusic (String filename, boolean looping) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        try {
            AssetFileDescriptor afd = MainActivity.assets.openFd(filename);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(looping);
            mediaPlayer.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}