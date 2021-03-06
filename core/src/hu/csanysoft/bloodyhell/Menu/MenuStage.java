package hu.csanysoft.bloodyhell.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.bloodyhell.Game.GameScreen;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.MyTextButton;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.bloodyhell.BloodyHell;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class MenuStage extends MyStage {
    public MenuStage(Viewport viewport, Batch batch, BloodyHell game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        setDebugAll(Globals.DEBUG_ALL);
    }

    public void init() {
        OneSpriteStaticActor spiral = new OneSpriteStaticActor(Assets.manager.get(Assets.SPIRAL)){
            @Override
            public void act(float delta) {
                super.act(delta);
                rotateBy(delta*100);
            }
        };

        OneSpriteStaticActor logo = new OneSpriteStaticActor(Assets.manager.get(Assets.LOGO)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation((float) (Math.sin(elapsedTime)*10));
            }
        };

        OneSpriteStaticActor start = new OneSpriteStaticActor(Assets.manager.get(Assets.START)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setTexture(Assets.manager.get(Assets.START_DOWN));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        game.setScreen(new GameScreen(game));
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        OneSpriteStaticActor exit = new OneSpriteStaticActor(Assets.manager.get(Assets.EXIT)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setTexture(Assets.manager.get(Assets.EXIT_DOWN));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        System.exit(0);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        OneSpriteStaticActor tutorial = new OneSpriteStaticActor(Assets.manager.get(Assets.TUTORIAL)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setTexture(Assets.manager.get(Assets.TUTORIAL_DOWN));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        game.setScreen(new TutorialScreen(game));
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        addActor(logo);
        addActor(spiral);
        addActor(start);
        addActor(tutorial);
        addActor(exit);

        start.magnify(2);
        tutorial.magnify(2);
        exit.magnify(2);
        spiral.magnify(2);

        start.setPositionCenterOfActorToCenterOfViewport();
        exit.setPositionCenterOfActorToCenterOfViewport();
        tutorial.setPositionCenterOfActorToCenterOfViewport();
        logo.setPositionCenterOfActorToCenterOfViewport();

        tutorial.changePosition(+400, +200);
        exit.changePosition(-400, -200);
        logo.changePosition(-200, +200);

        spiral.setZIndex(10);
        spiral.setOrigintoCenter();
        spiral.setPositionCenterOfActorToCenterOfViewport();
        spiral.setZIndex(0);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }


}
