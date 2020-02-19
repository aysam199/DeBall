package com.mygdx.GameObjects;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

	private Random r;

	private Rectangle  barUp, barDown;
	public static final int VERTICAL_GAP = 45;
	private float groundY;
	private boolean isScored = false;
	

	public Pipe(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);

		r = new Random();
		barUp = new Rectangle();
		barDown = new Rectangle();
		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {

		super.update(delta);

		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x + width + 44, position.y,
				groundY +66 - (position.x + width + VERTICAL_GAP),height);
	}

	@Override
	public void reset(float newX) {
		super.reset(newX);
		width = r.nextInt(118) ;
		isScored = false;
	}

	public boolean collides(Bird bird) {
		if (position.y + height + 30 > bird.getY() + bird.getHeight()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), barDown));
		}
		return false;
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.y = -scrollSpeed;
		reset(x);
	}



	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}

}