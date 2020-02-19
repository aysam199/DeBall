package com.mygdx.deball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import screens.GameScreen;
import screens.MenuScreen;
import zbhelpers.AssetLoader;

public class deball extends Game {
	
	public BitmapFont font;
	public SpriteBatch batch;
	Texture Welcome_background,Welcome_ball,loadingImage,DeBallImage;
	OrthographicCamera camera;
	public static Preferences prefs;
	long startTime;
	
    @Override
    public void create() {
    	font = new BitmapFont();
    	font.getData().setScale(2f);
    	batch = new SpriteBatch();
    	startTime = TimeUtils.millis();
		Welcome_background = new Texture(Gdx.files.internal("data/Welcome_background.png"));
		Welcome_ball = new Texture(Gdx.files.internal("data/birdImage.png"));
		loadingImage = new Texture(Gdx.files.internal("data/loadingImage.png"));
		DeBallImage = new Texture(Gdx.files.internal("data/DeBallImage.png"));
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        AssetLoader.load();
        setScreen(new MenuScreen(this)); 
        
    }
    
    public void render() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(Welcome_background, 0, 0,800, 565);
        batch.draw(Welcome_ball, 300, 300,220,110);
        batch.draw(DeBallImage, 260, 230,302,70);
        batch.draw(loadingImage, 190, 0,600,50);

        batch.end();

    	if( TimeUtils.timeSinceMillis(startTime) > 2000){
    		super.render(); 
    	}
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}