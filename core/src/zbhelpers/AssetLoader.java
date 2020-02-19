package zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture birdImage,backImage,grassImage,dirtImage,pipeImage;
    public static Texture taptostartImage,gameoverImage,backtomenuImage,tryagainImage,highscoreImage;

    public static TextureRegion logo, zbLogo,grass,dirt , bg, bird, bar, playButtonUp, playButtonDown;
    public static TextureRegion taptostart,gameover,tryagain,back,highscore;
    public static Sound dead,  coin;
    public static BitmapFont font, shadow;
    private static Preferences prefs;

    public static void load() {


        birdImage = new Texture(Gdx.files.internal("data/birdImage.png"));
        backImage = new Texture(Gdx.files.internal("data/backImage.png"));
        grassImage = new Texture(Gdx.files.internal("data/grassImage.png"));
        dirtImage = new Texture(Gdx.files.internal("data/dirtImage.png"));
        pipeImage = new Texture(Gdx.files.internal("data/pipeImage.png"));
        
        taptostartImage = new Texture(Gdx.files.internal("data/taptostart.png"));
        gameoverImage = new Texture(Gdx.files.internal("data/gameover.png"));
        backtomenuImage = new Texture(Gdx.files.internal("data/back.png"));
        tryagainImage = new Texture(Gdx.files.internal("data/tryagain.png"));
        highscoreImage= new Texture(Gdx.files.internal("data/highscore.png"));
         

        bg = new TextureRegion(backImage, 0, 0, 136, 180);
        bg.flip(false, true);
        
        grass = new TextureRegion(grassImage, 0, 0, 136, 11);
        grass.flip(false, true);
        
        dirt = new TextureRegion(dirtImage, 0, 0, 136, 11);
        dirt.flip(false, true);

        bird = new TextureRegion(birdImage, 0, 0, 120, 120);
        bird.flip(false, true);


        bar = new TextureRegion(pipeImage, 0, 0, 100, 20);
        bar.flip(false, true);
        
        taptostart = new TextureRegion(taptostartImage, 0, 0, 320, 69);
        taptostart.flip(false, true);
        gameover = new TextureRegion(gameoverImage, 0, 0, 286, 70);
        gameover.flip(false, true);
        back = new TextureRegion(backtomenuImage, 0, 0, 143, 68);
        back.flip(false, true);
        tryagain = new TextureRegion(tryagainImage, 0, 0, 290, 69);
        tryagain.flip(false, true);
        highscore = new TextureRegion(highscoreImage, 0, 0, 302, 70);
        highscore.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZombieBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.

        // Dispose sounds
        dead.dispose();
        coin.dispose();

        font.dispose();
        shadow.dispose();
    }

}