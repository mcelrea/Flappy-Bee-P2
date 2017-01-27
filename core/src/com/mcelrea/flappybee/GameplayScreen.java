package com.mcelrea.flappybee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameplayScreen implements Screen {

    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 640;
    private SpriteBatch batch; //draw graphics
    private ShapeRenderer shapeRenderer; //draw shapes
    private Camera camera; //the players view of the world
    private Viewport viewport; //control the view of the world
    private Bee bee;
    private Array<Flower> flowers = new Array<Flower>();
    private float GAP_BETWEEN_FLOWERS = 200f;

    public GameplayScreen(MyGdxGame myGdxGame) {
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(); //2D camera
        camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        bee = new Bee();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        update(delta);

        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        batch.begin();
        bee.draw(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        //all shape drawing goes here
        shapeRenderer.begin();
        bee.drawDebug(shapeRenderer);
        for(int i=0; i < flowers.size; i++) {
            flowers.get(i).drawDebug(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public void createNewFlower() {
        Flower flower = new Flower();
        flower.setPosition(WORLD_WIDTH + 33);
        flowers.add(flower);
    }

    public void checkIfNewFlowerIsNeeded() {
        //no flowers in the game
        if(flowers.size == 0) {
            createNewFlower();
        }
        else { //if a flower is needed
            Flower flower = flowers.get(flowers.size-1);
            if(flower.getX() < WORLD_WIDTH - GAP_BETWEEN_FLOWERS) {
                createNewFlower();
            }
        }
    }

    public void removeFlowersIfPassed() {
        if(flowers.size > 0) {
            Flower firstFlower = flowers.first();
            if(firstFlower.getX() < -33) {
                flowers.removeValue(firstFlower,true);
            }
        }
    }

    public void keepBeeOnScreen() {
        bee.setPosition(bee.getX(),
                MathUtils.clamp(bee.getY(),0,WORLD_HEIGHT));
    }

    private void update(float delta) {
        //bee stuff
        bee.update();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bee.flyUp();
        }
        keepBeeOnScreen();

        //flower stuff
        for(int i=0; i < flowers.size; i++) {
            flowers.get(i).update(delta);
        }
        checkIfNewFlowerIsNeeded();
        removeFlowersIfPassed();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
