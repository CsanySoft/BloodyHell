package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.bloodyhell.Bodies.Doboz;
import hu.csanysoft.bloodyhell.Bodies.Ize;
import hu.csanysoft.bloodyhell.Bodies.ValamiSzenzor;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.MyStage;
import hu.csanysoft.bloodyhell.MyBaseClasses.WorldBodyEditorLoader;
import hu.csanysoft.bloodyhell.MyGdxGame;
import jdk.nashorn.internal.objects.Global;

public class GameStage extends MyStage {

    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    Matrix4 debugMatrix;
    WorldBodyEditorLoader loader;
    float elapsedtime = 0;

    ValamiSzenzor szenzor;

    public GameStage(MyGdxGame game) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(this);
        debugMatrix = new Matrix4(getCamera().combined);
        box2DDebugRenderer = new Box2DDebugRenderer();
        setDebugAll(Globals.DEBUG_ALL);
    }

    @Override
    public void init() {
        world = new World(new Vector2(0,-10), false); //gravitáció megadása vector2-ben
        loader = new WorldBodyEditorLoader(Gdx.files.internal("bodies.json"));
        addActor(new Ize(world, loader, 500, 600));
        addActor(new Doboz(world, 500, 100, -25));
        addActor(szenzor = new ValamiSzenzor(world, 600, 300));




        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().getUserData() instanceof ValamiSzenzor && contact.getFixtureB().getUserData() instanceof Ize ||
                        contact.getFixtureA().getUserData() instanceof Ize && contact.getFixtureB().getUserData() instanceof ValamiSzenzor) {

                    System.out.println("Ütközés kezdete");

                }

            }

            @Override
            public void endContact(Contact contact) {
                if(contact.getFixtureA().getUserData() instanceof ValamiSzenzor && contact.getFixtureB().getUserData() instanceof Ize ||
                        contact.getFixtureA().getUserData() instanceof Ize && contact.getFixtureB().getUserData() instanceof ValamiSzenzor){

                    System.out.println("Ütközés vége");
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.step(delta, 60, 60);
        elapsedtime += delta;
    }

    @Override
    public void draw() {
        super.draw();
        if(Globals.DEBUG_ALL) box2DDebugRenderer.render(world, debugMatrix);
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
