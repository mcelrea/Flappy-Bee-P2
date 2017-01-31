package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Bee {
    private static final float COLLISION_RADIUS = 20f;
    private final Circle collisionCircle;
    private float x;
    private float y;
    private static final float DIVE_ACCEL = 0.3f;
    private float ySpeed = 0;
    private static final float FLY_ACCEL = 5f;
    private Texture image;

    public Bee() {
        x = 100;
        y = GameplayScreen.WORLD_HEIGHT/2;
        collisionCircle = new Circle(x,y,COLLISION_RADIUS);
        image = new Texture("Bee_normal.png");
    }

    public void resetBeeSpeed() { //234324
        ySpeed = 0;
    }

    public void update() {
        ySpeed -= DIVE_ACCEL;
        setPosition(x, y+ySpeed);
    }

    public void flyUp() {
        ySpeed = FLY_ACCEL;
        setPosition(x,y+ySpeed);
    }


    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(image,x-image.getWidth()/2,y-image.getHeight()/2);
    }

    public void updateCollisionCircle() {
        collisionCircle.setX(x);
        collisionCircle.setY(y);
    }

    public void setPosition(float x, float y) {
        //update graphics position
        this.x = x;
        this.y = y;

        //update collision circle position
        updateCollisionCircle();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Circle getCollisionCircle() {
        return collisionCircle;
    }
}
