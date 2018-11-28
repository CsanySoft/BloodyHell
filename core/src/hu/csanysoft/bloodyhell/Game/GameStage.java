package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.Random;

import hu.csanysoft.bloodyhell.Actors.Ember;
import hu.csanysoft.bloodyhell.Actors.Explosion;
import hu.csanysoft.bloodyhell.Actors.Szunyog;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.bloodyhell.MyGdxGame;

public class GameStage extends MyStage {

    float elapsedtime = 0;
    float gotox= 0, gotoy=0;
    float speed = 5;
    boolean flying = false;
    float fps=24;
    float rotation=0;
    Random rand;
    ArrayList<Ember> emberek = new ArrayList();


    Ember ember;
    boolean vanRobbanas = false;

    Szunyog szunyog;

    public GameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(this);
        for (int i = 0; i < 3; i++) {
            Ember ember = new Ember(rand.nextFloat()+rand.nextInt(30), new float[]{rand.nextFloat()+rand.nextInt(1000)+100,rand.nextFloat()+rand.nextInt(400)+100});
            ember.setPosition(rand.nextFloat()+rand.nextInt(1000)+100,rand.nextFloat()+rand.nextInt(400)+100);
            addActor(ember);
            emberek.add(ember);
        }

    }

    @Override
    public void init() {
        rand = new Random();
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
        setDebugAll(Globals.DEBUG_ALL);
        elapsedtime += delta;
        float x = szunyog.getX()+szunyog.getWidth()/2;
        float y = szunyog.getY()+szunyog.getHeight()/2;
        /*if(flying){
            if(szunyog.getX() < gotox) szunyog.setX(x+(gotox-x<speed ? gotox-x : speed)-szunyog.getWidth()/2);
            else if(szunyog.getX() > gotox) szunyog.setX(x-(x-gotox<speed ? x-gotox : speed)-szunyog.getWidth()/2);
            if(szunyog.getY() < gotoy) szunyog.setY(y+(gotoy-y<speed ? gotoy-y : speed)-szunyog.getHeight()/2);
            else if(szunyog.getY() > gotoy) szunyog.setY(y-(y-gotoy<speed ? y-gotoy : speed)-szunyog.getHeight()/2);
            if(x == gotox && y == gotoy) flying = false;
        }*/
        rotation = (float) ((Math.atan2 (gotox-x, -(gotoy-y))*180.0d/Math.PI)+180);
        if(flying){
            float xcomp = gotox - x;
            float ycomp = gotoy - y;
            rotation = (float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180);
            fps = 10 + Math.round( 6 * (Math.abs(xcomp/30)+Math.abs(ycomp/30))/5);
            float size = (Math.abs(xcomp/30)+Math.abs(ycomp/30))*5 > 20 ? 20 : (Math.abs(xcomp/30)+Math.abs(ycomp/30))*5;
            szunyog.setSize(80+size, 80+size);
            szunyog.setFps(fps == 10 ? 0 : fps);
            if(szunyog.getRotation()+360 - rotation < rotation - szunyog.getRotation()) szunyog.setRotation(szunyog.getRotation()+360);
            if(szunyog.getRotation()-360 - rotation > rotation - szunyog.getRotation()) szunyog.setRotation(szunyog.getRotation()-360);

            if(rotation < szunyog.getRotation()-15) {
                szunyog.setRotation(szunyog.getRotation()-10);
            }
            else if (rotation > szunyog.getRotation()+15){
                szunyog.setRotation(szunyog.getRotation()+10);
            } else szunyog.setRotation(rotation);
            float xspeed = xcomp/30 > 0 ? xcomp/30 > 8 ? 8 : xcomp/30 : xcomp/30 < -8 ? -8 : xcomp/30;
            float yspeed = ycomp/30 > 0 ? ycomp/30 > 8 ? 8 : ycomp/30 : ycomp/30 < -8 ? -8 : ycomp/30;
            szunyog.setX(szunyog.getX() + xspeed);
            szunyog.setY(szunyog.getY() + yspeed);
            if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) flying = false;

        }

        for(Ember ember : emberek){
            if (ember.overlaps(szunyog)) System.out.println("Há dika szunyogg");
        }

        for (Actor actor : getActors()) {
            if(actor instanceof Ember) {
                if(szunyog.overlaps((Ember)actor)) {
                    szunyog.setX(actor.getX());
                    szunyog.setY(actor.getY());

                    if(((Ember) actor).isStop()) {
                        if(elapsedtime - ((Ember) actor).getStoppedTime() > 3) {
                            ((Ember) actor).setStop(false);
                            if(elapsedtime - ((Ember) actor).getStoppedTime() < 6) ((Ember) actor).setStoppable(false);
                            else ((Ember) actor).setStoppable(true);
                        }
                    } else {

                        if(((Ember) actor).isStoppable()) ((Ember) actor).setStop(true);
                        ((Ember) actor).setStoppedTime(elapsedtime);
                    }








                    if(!vanRobbanas) {
                        Explosion explosion = new Explosion();

                        explosion.setPosition(szunyog.getX()-szunyog.getWidth()/2, szunyog.getY()+szunyog.getHeight()/2);
                        addActor(explosion);
                        vanRobbanas=true;
                    } else {
                        vanRobbanas = false;
                        for (int j = 0; j < getActors().size; j++){
                            if(getActors().toArray()[j] instanceof Explosion) {
                                vanRobbanas = true;
                            }
                        }
                    }







                }
            }
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
