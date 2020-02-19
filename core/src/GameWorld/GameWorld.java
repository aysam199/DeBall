package GameWorld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import zbhelpers.AssetLoader;

import com.mygdx.GameObjects.Bird;
import com.mygdx.GameObjects.Pipe;
import com.mygdx.GameObjects.ScrollHandler;

public class GameWorld {

    private Bird bird;
    private ScrollHandler scroller;
    private int score = 0;
    private int midPointY;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        bird = new Bird(59, midPointY +95, 17, 17);
        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this, midPointY + 66);


    }

    public void update(float delta) {

        switch (currentState) {
        case READY:
            updateReady(delta);
            break;

        case RUNNING:
            updateRunning(delta);
            break;
        default:
            break;
        }

    }

    private void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
        	scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
            }
            AssetLoader.dead.play();
        }

      
    }

    public Bird getBird() {
        return bird;

    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(0);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    

}