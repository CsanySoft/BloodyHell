package hu.csanysoft.bloodyhell.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
        MyTextButton btn = new MyTextButton("Start"){


            public void init() {
                setPosition(Globals.WORLD_WIDTH/2-this.getWidth()/2, Globals.WORLD_HEIGHT/2-this.getWidth()/2);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new GameScreen(game));
                    }
                });
            }
        };

        addActor(btn);
        btn.setZIndex(10);

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

        addActor(logo);
        addActor(spiral);

        logo.setPositionCenterOfActorToCenterOfViewport();
        logo.chamgePosition(-200, +200);
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
