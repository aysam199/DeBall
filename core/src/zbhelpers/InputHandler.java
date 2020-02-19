package zbhelpers;

import screens.GameScreen;
import screens.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import GameWorld.GameWorld;

import com.mygdx.GameObjects.Bird;
import com.mygdx.deball.deball;

public class InputHandler implements InputProcessor {
	private Bird myBird;
	private GameWorld myWorld;
	final deball game;

	// Ask for a reference to the Bird when InputHandler is created.
	public InputHandler(GameWorld myWorld) {
		game=MenuScreen.game;
		// myBird now represents the gameWorld's bird.
		this.myWorld = myWorld;
		myBird = myWorld.getBird();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (myWorld.isReady()) {
			myWorld.start();
		}
		myBird.onClick();

		if (myWorld.isGameOver()) {
			if (Gdx.input.isTouched()) {
    			if (Gdx.input.getY() > (Gdx.graphics.getHeight() / 3.2814)
    					&& Gdx.input.getY() < (Gdx.graphics.getHeight() / 2.5314)
    					&& Gdx.input.getX() > (Gdx.graphics.getWidth() / 9)
    					&& Gdx.input.getX() < (Gdx.graphics.getWidth() / 1.7419)) {
    				myWorld.restart();
    			}
    			if (Gdx.input.getY() > (Gdx.graphics.getHeight() / 3.2814)
    					&& Gdx.input.getY() < (Gdx.graphics.getHeight() / 2.5314)
    					&& Gdx.input.getX() > (Gdx.graphics.getWidth() / 1.421)
    					&& Gdx.input.getX() < (Gdx.graphics.getWidth())) {
    				game.setScreen(new MenuScreen(game)); 
    			}
        	}

			
		}

		return true;
	}
		

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}