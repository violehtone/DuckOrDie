package duckordie.lebasoft.duck_or_die.main;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity {

    public static final int GAME_WIDTH = 1280, GAME_HEIGHT = 720;
    public static GameView game;
    public static AssetManager assets;
    private static int highScore;
    private static SharedPreferences prefs;
    private static final String highScoreKey = "highScoreKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getPreferences(Activity.MODE_PRIVATE);
        highScore = retrieveHighScore();

        assets = getAssets();
        game = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static void setHighScore(int highScore) {
        MainActivity.highScore = highScore;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(highScoreKey, highScore);
        editor.commit();
    }

    private int retrieveHighScore() {
        return prefs.getInt(highScoreKey,0);
    }

    public static int getHighScore() {
        return highScore;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Assets.onResume();
        game.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Assets.onPause();
        game.onPause();
    }

}