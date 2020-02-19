package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.deball.deball;

public class MenuScreen implements Screen {
	public static deball game;
	public SpriteBatch batch;
	Texture bg;
	OrthographicCamera camera;
	Music music;
	BitmapFont font;
	long startTime;

	// This is the constructor, not the class declaration
	public MenuScreen(final deball game) {
		this.game = game;
		startTime = TimeUtils.millis();
		font = new BitmapFont();
		bg = new Texture(Gdx.files.internal("data/menuBack.png"));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		music.setLooping(true);
	}
	
	

	@Override
	public void render(float delta) {
		
		int x_pos = Gdx.input.getX();
		int y_pos = Gdx.input.getY();
		// System.out.println("x" + x_pos );
		// System.out.println("y" + y_pos);
		// int wid=Gdx.graphics.getWidth();
		// int hey=Gdx.graphics.getHeight();
		// System.out.println("width" + wid);
		// System.out.println("heigh" + hey);

		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		
		if (Gdx.input.isTouched()) {
			if( TimeUtils.timeSinceMillis(startTime) > 500){  
			if (Gdx.input.getY() > (Gdx.graphics.getHeight() / 17.72)
					&& Gdx.input.getY() < (Gdx.graphics.getHeight() / 4.43)
					&& Gdx.input.getX() > (Gdx.graphics.getWidth() / 10.8)
					&& Gdx.input.getX() < (Gdx.graphics.getWidth() / 1.6363)) {
				game.setScreen(new GameScreen());
			}
			if (Gdx.input.getY() > (Gdx.graphics.getHeight() / 3.544)
					&& Gdx.input.getY() < (Gdx.graphics.getHeight() / 2.3315)
					&& Gdx.input.getX() > (Gdx.graphics.getWidth() / 8.307)
					&& Gdx.input.getX() < (Gdx.graphics.getWidth() / 1.1489)) {
				game.setScreen(new InstScreen(game));
			}
			if (Gdx.input.getY() > 0
					&& Gdx.input.getY() < (Gdx.graphics.getHeight() / 11.075)
					&& Gdx.input.getX() > (Gdx.graphics.getWidth() / 1.4594)
					&& Gdx.input.getX() < (Gdx.graphics.getWidth())) {
				game.setScreen(new CreditsScreen(game));
			}
			if (Gdx.input.getY() > (Gdx.graphics.getHeight() / 2.109)
					&& Gdx.input.getY() < (Gdx.graphics.getHeight() / 1.772)
					&& Gdx.input.getX() > (Gdx.graphics.getWidth() / 7.7142)
					&& Gdx.input.getX() < (Gdx.graphics.getWidth() / 1.1739)) {
				game.setScreen(new HiscoreScreen(game));
			}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
			music.play();
	}

	@Override
	public void hide() {
		music.stop();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
