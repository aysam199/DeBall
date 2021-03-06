package com.mygdx.GameObjects;

import com.mygdx.GameObjects.Pipe;

import GameWorld.GameWorld;
import zbhelpers.AssetLoader;

public class ScrollHandler {

    private Pipe pipe1, pipe2, pipe3;
    public static final int SCROLL_SPEED = -68;
    public static final int PIPE_GAP = 100;
    
    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;

        
        pipe1 = new Pipe(0, 40, 80, 10, SCROLL_SPEED, yPos);
        pipe2 = new Pipe(0, pipe1.getTailY() - PIPE_GAP,  80, 10, SCROLL_SPEED,
                yPos);
        pipe3 = new Pipe(0, pipe2.getTailY() - PIPE_GAP,  80, 10, SCROLL_SPEED,
                yPos);
    }

    public void update(float delta) {

        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailY() - PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailY() - PIPE_GAP);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailY() - PIPE_GAP);
        }

    }

    public void stop() {

        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    public boolean collides(Bird bird) {

        if (!pipe1.isScored()
                && pipe1.getY() + (pipe1.getHeight() / 2) > bird.getY()
                        + bird.getHeight()) {
            addScore(1);
            pipe1.setScored(true);
            AssetLoader.coin.play();
        } else if (!pipe2.isScored()
                && pipe2.getY() + (pipe2.getHeight() / 2) > bird.getY()
                        + bird.getHeight()) {
            addScore(1);
            pipe2.setScored(true);
            AssetLoader.coin.play();

        } else if (!pipe3.isScored()
                && pipe3.getY() + (pipe3.getHeight() / 2) > bird.getY()
                        + bird.getHeight()) {
            addScore(1);
            pipe3.setScored(true);
            AssetLoader.coin.play();

        }

        return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3
                .collides(bird));
    }

    public void onRestart() {

    	pipe1.onRestart(40, SCROLL_SPEED);
        pipe2.onRestart(pipe1.getTailY() - PIPE_GAP, SCROLL_SPEED);
        pipe3.onRestart(pipe2.getTailY() - PIPE_GAP, SCROLL_SPEED);
        
     
    }
    
    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }



    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

}