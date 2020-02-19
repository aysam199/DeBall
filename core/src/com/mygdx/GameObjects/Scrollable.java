package com.mygdx.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, -scrollSpeed);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        if (position.y + height > 250){
            isScrolledLeft = true;
        }
    }

    public void reset(float newX) {
    	//wen to spawn after 3 pipes
        position.y = newX;
        isScrolledLeft = false;
    }
    
    public void stop() {
        velocity.x = 0;
    }

    // Getters for instance variables
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }
    
    public float getTailY() {
        return position.y + height;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}