package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.bloodyhell.BloodyHell;
import hu.csanysoft.bloodyhell.Game.GameScreen;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.MyTextField;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class EndStage extends MyStage{

    public EndStage(BloodyHell game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
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
                        game.setScreenBackByStackPop();
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };
        Label.LabelStyle style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
        style.fontColor = Color.BLACK;

        OneSpriteStaticActor text = new OneSpriteStaticActor(Assets.manager.get(Assets.GAME_OVER));

        addActor(spiral);
        addActor(exit);
        addActor(text);

        exit.setPositionCenterOfActorToCenterOfViewport();
        exit.changePosition(+350, -200);
        exit.magnify(2);
        spiral.setZIndex(10);
        spiral.setOrigintoCenter();
        spiral.setPositionCenterOfActorToCenterOfViewport();
        spiral.setZIndex(0);
    }
}
