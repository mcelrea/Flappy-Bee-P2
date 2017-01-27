package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Flower {

    private static final float COLLISION_RECT_WIDTH = 13f;
    private static final float COLLISION_RECT_HEIGHT = 447f;
    private static final float COLLISION_CIRCLE_RADIUS = 33f;
    private final Circle floorCollisionCircle;
    private final Rectangle floorCollisionRectangle;
    private final Circle ceilingCollisionCircle;
    private final Rectangle ceilingCollisionRectangle;
    private static final float GAP = 225f;
    private static final float MAX_SPEED_PER_SECOND = 100f;
    private static final float HEIGHT_OFFSET = -400f;

    private float x = 0;
    private float y = 0;

    public Flower() {
        y = MathUtils.random(HEIGHT_OFFSET); //0 and -400
        floorCollisionRectangle = new Rectangle(x,y,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        floorCollisionCircle = new Circle(x+COLLISION_RECT_WIDTH/2,
                y+COLLISION_RECT_HEIGHT,
                COLLISION_CIRCLE_RADIUS);

        ceilingCollisionRectangle = new Rectangle(x,
                floorCollisionCircle.y + GAP,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        ceilingCollisionCircle = new Circle(x+COLLISION_RECT_WIDTH/2,
                ceilingCollisionRectangle.y,
                COLLISION_CIRCLE_RADIUS);
    }

    public void update(float delta) {
        setPosition(x - MAX_SPEED_PER_SECOND*delta);
    }

    public void updateCollisionRectangle() {
        floorCollisionRectangle.setX(x);
        ceilingCollisionRectangle.setX(x);
    }

    public void updateCollisionCircle() {
        floorCollisionCircle.setX(x+COLLISION_RECT_WIDTH/2);
        ceilingCollisionCircle.setX(x+COLLISION_RECT_WIDTH/2);
    }

    public void setPosition(float x) {
        this.x = x;
        updateCollisionRectangle();
        updateCollisionCircle();
    }

    public boolean isBeeColliding(Bee bee) {
        Circle beeCircle = bee.getCollisionCircle();
        return Intersector.overlaps(beeCircle,floorCollisionRectangle) ||
                Intersector.overlaps(beeCircle,floorCollisionCircle) ||
                Intersector.overlaps(beeCircle,ceilingCollisionRectangle) ||
                Intersector.overlaps(beeCircle,ceilingCollisionCircle);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(floorCollisionRectangle.x, floorCollisionRectangle.y,
                floorCollisionRectangle.width, floorCollisionRectangle.height);
        shapeRenderer.circle(floorCollisionCircle.x, floorCollisionCircle.y,
                floorCollisionCircle.radius);

        shapeRenderer.rect(ceilingCollisionRectangle.x, ceilingCollisionRectangle.y,
                ceilingCollisionRectangle.width, ceilingCollisionRectangle.height);
        shapeRenderer.circle(ceilingCollisionCircle.x, ceilingCollisionCircle.y,
                ceilingCollisionCircle.radius);
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
