package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Bee {
    private static final float COLLISION_RADIUS = 24f;
    private final Circle collisionCircle;
    private float x;
    private float y;

    public Bee() {
        x = 100;
        y = GameplayScreen.WORLD_HEIGHT/2;
        collisionCircle = new Circle(x,y,COLLISION_RADIUS);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
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
}
