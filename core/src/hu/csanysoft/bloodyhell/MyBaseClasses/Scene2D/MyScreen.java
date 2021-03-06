package hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hu.csanysoft.bloodyhell.MyBaseClasses.Game.InitableInterface;
import hu.csanysoft.bloodyhell.BloodyHell;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyScreen implements Screen, InitableInterface {

    protected static final SpriteBatch spriteBatch = new SpriteBatch();

    private float r=0;
    private float g=0;
    private float b=0;

    protected final BloodyHell game;

    public static SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    protected MyScreen(BloodyHell game) {
        this.game = game;
        init();
    }

    @Override
    public void dispose() {
        //spriteBatch.dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        //setCameraReset(viewport, width, height);
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    public Game getGame() {
        return game;
    }

    protected void setBackGroundColor(float r, float g, float b)
    {
        this.r=r;
        this.g = g;
        this.b = b;
    }


}
