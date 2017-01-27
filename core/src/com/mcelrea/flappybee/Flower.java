package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Flower {

    private static final float COLLISION_RECT_WIDTH = 13f;
    private static final float COLLISION_RECT_HEIGHT = 447f;
    private static final float COLLISION_CIRCLE_RADIUS = 33f;
    private final Circle collisionCircle;
    private final Rectangle collisionRectangle;
    private static final float MAX_SPEED_PER_SECOND = 100f;

    private float x = 0;
    private float y = 0;

    public Flower() {
        collisionRectangle = new Rectangle(x,y,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        collisionCircle = new Circle(x+COLLISION_RECT_WIDTH/2,
                y+COLLISION_RECT_HEIGHT,
                COLLISION_CIRCLE_RADIUS);
    }

    public void update(float delta) {
        setPosition(x - MAX_SPEED_PER_SECOND*delta);
    }

    public void updateCollisionRectangle() {
        collisionRectangle.setX(x);
    }

    public void updateCollisionCircle() {
        collisionCircle.setX(x+COLLISION_RECT_WIDTH/2);
    }

    public void setPosition(float x) {
        this.x = x;
        updateCollisionRectangle();
        updateCollisionCircle();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collisionRectangle.x,collisionRectangle.y,
                collisionRectangle.width,collisionRectangle.height);
        shapeRenderer.circle(collisionCircle.x,collisionCircle.y,
                collisionCircle.radius);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
