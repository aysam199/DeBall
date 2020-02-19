package GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import zbhelpers.AssetLoader;

import com.mygdx.GameObjects.Bird;
import com.mygdx.GameObjects.Pipe;
import com.mygdx.GameObjects.ScrollHandler;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	// Game Objects
	private Bird bird;
	private ScrollHandler scroller;
	private Pipe pipe1, pipe2, pipe3;

	// Game Assets
	private TextureRegion bg,grass,dirt;
	private TextureRegion birdMid;
	private TextureRegion  bar;


	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		bird = myWorld.getBird();
		scroller = myWorld.getScroller();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass=AssetLoader.grass;
		dirt=AssetLoader.dirt;
		birdMid = AssetLoader.bird;
		bar = AssetLoader.bar;
	}

	



	private void drawPipes() {
		batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
				pipe1.getHeight());
		batcher.draw(bar, pipe1.getX() + pipe1.getWidth() + 45, pipe1.getY(),
				midPointY + 66 - (pipe1.getWidth() + 28), pipe1.getHeight());

		batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
				pipe2.getHeight());
		batcher.draw(bar, pipe2.getX() + pipe2.getWidth() + 45, pipe2.getY(),
				midPointY + 66 - (pipe2.getWidth() + 28), pipe2.getHeight());

		batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
				pipe3.getHeight());
		batcher.draw(bar, pipe3.getX() + pipe3.getWidth() + 45, pipe3.getY(),
				midPointY + 66 - (pipe3.getWidth() + 28), pipe3.getHeight());
	}
	

	public void render(float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(0 / 255.0f, 1 / 0.0f, 0 / 0.0f, 1);


		
	/*	shapeRenderer.rect(pipe1.getBarUp().x,pipe1.getBarUp().y-1,
				pipe1.getBarUp().width,pipe1.getBarUp().height+2);
		shapeRenderer.rect(pipe1.getBarDown().x,pipe1.getBarDown().y-1,
				pipe1.getBarDown().width+1,pipe1.getBarDown().height+2);

		shapeRenderer.circle(bird.getBoundingCircle().x,bird.getBoundingCircle().y,
				bird.getBoundingCircle().radius);
				*/
		

		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();
		
		batcher.draw(bg, 0, 0, 136, 180);
		batcher.draw(grass, 0, 180, 136, 11);
		batcher.draw(dirt, 0, 191, 136,50);

		drawPipes();
		batcher.enableBlending();

		batcher.draw(birdMid, bird.getX(), bird.getY(), bird.getWidth() / 2.0f,
				bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1,
				1, bird.getRotation());

		if (myWorld.isReady()) {
			batcher.draw(AssetLoader.taptostart, 25, 70,90,20);
			


		} else {

			if (myWorld.isGameOver()) {
					batcher.draw(AssetLoader.gameover, 25, 46,90,20);
					
					batcher.draw(AssetLoader.highscore, 20, 98,100,30);
					String highScore = AssetLoader.getHighScore() + "";

					// Draw shadow first
					AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
							- (3 * highScore.length()), 128);
					// Draw text
					AssetLoader.font.draw(batcher, highScore, (136 / 2)
							- (3 * highScore.length() - 1), 127);

				batcher.draw(AssetLoader.tryagain, 5, 66,75,20);
				batcher.draw(AssetLoader.back, 90, 66,40,20);
				// Convert integer into String
				String score = myWorld.getScore() + "";

				// Draw shadow first
				AssetLoader.shadow.draw(batcher, score,
						(136 / 2) - (3 * score.length()), 12);
				// Draw text
				AssetLoader.font.draw(batcher, score,
						(136 / 2) - (3 * score.length() - 1), 11);

			}

			// Convert integer into String
			String score = myWorld.getScore() + "";

			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
					- (3 * score.length()), 12);
			// Draw text
			AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
					- (3 * score.length() - 1), 11);

		}
		batcher.end();

	}

}