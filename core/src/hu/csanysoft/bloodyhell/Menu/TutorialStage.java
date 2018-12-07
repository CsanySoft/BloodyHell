package hu.csanysoft.bloodyhell.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.bloodyhell.BloodyHell;
import hu.csanysoft.bloodyhell.Game.GameScreen;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.MyTextField;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class TutorialStage extends MyStage{

        public TutorialStage(Viewport viewport, Batch batch, BloodyHell game) {
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

            Label text = new Label("A játék célja, hogy a pályán mozgó emberektől elszívjuk a vért." +
                    " A fölöttük elhelyezett két sáv (kék, piros) az adott ember idegességét, valamint vérmennyiségét" +
                    "mutatja. Hogyha a szúnyog nem szív elég vért, akkor vége lesz a játéknak." +
                    "Ennek elkerülésének érdekében cikázva az autók között vehet fel, valamint fogyaszthat frissen is táplálékot." +
                    "A jóllakottságot az alsó csík mutatja.", style);

            addActor(spiral);
            addActor(exit);
            addActor(text);

            exit.setPositionCenterOfActorToCenterOfViewport();
            text.setPosition(exit.getX()-500, exit.getY()+100);
            text.setWrap(true);
            text.setWidth(1000);
            text.setAlignment(1);
            exit.changePosition(+350, -200);
            exit.magnify(2);
            spiral.setZIndex(10);
            spiral.setOrigintoCenter();
            spiral.setPositionCenterOfActorToCenterOfViewport();
            spiral.setZIndex(0);
        }
}
