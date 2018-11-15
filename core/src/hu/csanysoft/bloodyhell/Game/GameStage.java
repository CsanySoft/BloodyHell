package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanysoft.bloodyhell.Actors.Szunyog;
import hu.csanysoft.bloodyhell.MyBaseClasses.MyStage;
import hu.csanysoft.bloodyhell.MyGdxGame;
import jdk.nashorn.internal.objects.Global;

public class GameStage extends MyStage {

    float elapsedtime = 0;
    float gotox= 0, gotoy=0;
    float speed = 5;
    boolean flying = false;
    float fps=24;
    float rotation=0;

    Szunyog szunyog;

    public GameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void init() {
        addListener(new DragListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                flying = true;
                gotox = x;
                gotoy = y;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                flying = true;
                gotox = x;
                gotoy = y;

            }
        });

        addActor(szunyog = new Szunyog(400, 300));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedtime += delta;
        float x = szunyog.getX()+szunyog.getWidth()/2;
        float y = szunyog.getY()+szunyog.getHeight()/2;
        float gx = gotox-szunyog.getWidth()/2;
        float gy = gotoy-szunyog.getHeight()/2;
        /*if(flying){
            if(szunyog.getX() < gotox) szunyog.setX(x+(gotox-x<speed ? gotox-x : speed)-szunyog.getWidth()/2);
            else if(szunyog.getX() > gotox) szunyog.setX(x-(x-gotox<speed ? x-gotox : speed)-szunyog.getWidth()/2);
            if(szunyog.getY() < gotoy) szunyog.setY(y+(gotoy-y<speed ? gotoy-y : speed)-szunyog.getHeight()/2);
            else if(szunyog.getY() > gotoy) szunyog.setY(y-(y-gotoy<speed ? y-gotoy : speed)-szunyog.getHeight()/2);
            if(x == gotox && y == gotoy) flying = false;
        }*/
        if(flying){
            float xcomp = gotox - x;
            float ycomp = gotoy - y;
            rotation = (float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180);
            fps = 24 + (float)Math.ceil((Math.abs(xcomp/30)+Math.abs(ycomp/30))/5);
            szunyog.fps = fps;
            if(rotation < szunyog.getRotation()-15) {
                System.out.println("rotation = " + rotation);
                System.out.println("szunyog.getRotation() = " + szunyog.getRotation());
                szunyog.setRotation(szunyog.getRotation()-10);
            }
            else if (rotation > szunyog.getRotation()+15){
                System.out.println("rotation = " + rotation);
                System.out.println("szunyog.getRotation() = " + szunyog.getRotation());
                szunyog.setRotation(szunyog.getRotation()+10);
            } else szunyog.setRotation(rotation);
            //TODO: Megcsinálni, hogy ne csináljon teljes fordulatot ha gyorsabb lenne másik irányba fordulni
            szunyog.setX(szunyog.getX() + xcomp/30);
            szunyog.setY(szunyog.getY() + ycomp/30);
            if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) flying = false;
        }
    }

    @Override
    public void draw() {
        super.draw();
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
