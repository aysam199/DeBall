package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.deball.deball;

public class InstScreen implements Screen {
	final deball game;
	public SpriteBatch batch;
	Texture bg;
	OrthographicCamera camera;
	Music music;
	BitmapFont font;
	long startTime;

	// This is the constructor, not the class declaration
	public InstScreen(final deball game) {
		this.game = game;
		startTime = TimeUtils.millis();
		bg = new Texture(Gdx.files.internal("data/menudimBack.png"));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		font = new BitmapFont();
		font.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().setScale(2.5f);

		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		music.setLooping(true);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font.draw(batch,"Press on the right half of the", 50, 190);
		font.draw(batch,"screen to roll right and left half", 50, 155);
		font.draw(batch,"of the screen to roll left", 50, 120);
		font.draw(batch,"roll to dodge the bars! ", 50, 85);
		
		batch.end();
		if (Gdx.input.isTouched()) {
			if( TimeUtils.timeSinceMillis(startTime) > 500){  
			game.setScreen(new MenuScreen(game));
			}
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

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
