package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.bloodyhell.Bodies.Ize;
import hu.csanysoft.bloodyhell.MyBaseClasses.MyStage;
import hu.csanysoft.bloodyhell.MyBaseClasses.WorldBodyEditorLoader;
import hu.csanysoft.bloodyhell.MyGdxGame;

public class GameStage extends MyStage {

    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    WorldBodyEditorLoader loader;
    float elapsedtime = 0;

    public GameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void init() {
        world = new World(new Vector2(0,-10), false); //gravitáció megadása vector2-ben
        loader = new WorldBodyEditorLoader(Gdx.files.internal("bodies.json"));
        addActor(new Ize(world, loader, 100, 300));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.step(delta, 10, 10);
        elapsedtime += delta;
    }

    @Override
    public void draw() {
        super.draw();
        //box2DDebugRenderer.render(world);
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }
}
