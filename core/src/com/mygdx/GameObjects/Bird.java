package com.mygdx.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import zbhelpers.AssetLoader;

public class Bird {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;
    
    private boolean isAlive;

    private Circle boundingCircle;

    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
    }

    public void update(float delta) {

        if (velocity.x > 200) {
            velocity.x = 200;
        }

        if (position.x < 0) {
            position.x = 0;
            velocity.x = 0;
        }
        if (position.x > 118) {
            position.x = 118;
            velocity.x = 0;
        }


        position.add(velocity.cpy().scl(delta));

        boundingCircle.set(position.x + 9, position.y + 9, 8f);

        if (velocity.x < 0) {
            rotation -= 50 * delta;

            if (rotation < -50) {
                rotation = -50;
            }
        }

        if (velocity.x > 0) {
            rotation += 50 * delta;
            if (rotation > 50) {
                rotation = 50;
            }

        }

    }


    public void onClick() {
        if (isAlive) {
        	if (Gdx.input.isTouched()) {
    			if (Gdx.input.getX() < (Gdx.graphics.getWidth()/2)){
    				velocity.x = -83;
    			}
    			if (Gdx.input.getX() > (Gdx.graphics.getWidth()/2)){
    				velocity.x = 83;
    			}
    			}
            
        }
        
        
    }
    
    public void die() {
        isAlive = false;
        velocity.x = 0;
    }
    
    public void decelerate() {
        acceleration.x = 0;
    }
    
    public void onRestart(int y) {
        rotation = 0;
        position.x = 59;
        position.y =163;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 460;
        acceleration.y = 0;
        isAlive = true;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isAlive() {
        return isAlive;
    }
}