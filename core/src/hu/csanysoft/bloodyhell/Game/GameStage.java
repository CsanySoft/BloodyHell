package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.Random;

import hu.csanysoft.bloodyhell.Actors.Bg;
import hu.csanysoft.bloodyhell.Actors.Blood;
import hu.csanysoft.bloodyhell.Actors.Car;
import hu.csanysoft.bloodyhell.Actors.Ember;
import hu.csanysoft.bloodyhell.Actors.Explosion;
import hu.csanysoft.bloodyhell.Actors.Hollo;
import hu.csanysoft.bloodyhell.Actors.HpFekete;
import hu.csanysoft.bloodyhell.Actors.HpKek;
import hu.csanysoft.bloodyhell.Actors.HpPiros;
import hu.csanysoft.bloodyhell.Actors.KajaCsik;
import hu.csanysoft.bloodyhell.Actors.Szunyog;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.bloodyhell.BloodyHell;

@SuppressWarnings("unchecked")
public class GameStage extends MyStage {

    private float elapsedtime = 0;
    private float gotox= 0;
    private float gotoy=0;
    private boolean flying = false;
    private Random rand;
    private ArrayList<Ember> emberek = new ArrayList();
    private ArrayList<Car> autok = new ArrayList<Car>();
    private Ember overlappedEmber = null;
    private boolean won = true;
    private Bg bg;
    private Car le;
    private Car fel;
    private final Blood blood;

    private boolean vanRobbanas = false;

    private Szunyog szunyog;

    public GameStage(BloodyHell game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(this);
        for (int i = 0; i < 3; i++) {
            Ember ember = new Ember(new float[]{rand.nextFloat()+rand.nextInt((int)(Globals.WORLD_WIDTH*0.6804-100 - 50 - Globals.WORLD_WIDTH*0.225f + 10)) + Globals.WORLD_WIDTH*0.225f+10,rand.nextFloat()+rand.nextInt(Globals.WORLD_HEIGHT-1)});
            ember.setPosition(rand.nextFloat()+rand.nextInt((int)(Globals.WORLD_WIDTH*0.6804-ember.getWidth() - 50 - Globals.WORLD_WIDTH*0.225f + 10)) + Globals.WORLD_WIDTH*0.225f+10,rand.nextFloat()+rand.nextInt(Globals.WORLD_HEIGHT-1));
            addActor(ember);
            emberek.add(ember);
            addBloodLineToEmber(ember);
            addKillLineToEmber(ember);
            KajaCsik kajaCsik;
            addActor(kajaCsik = new KajaCsik(szunyog));
            Hollo hollo = new Hollo(szunyog);
            hollo.setPosition(rand.nextFloat()+rand.nextInt((int)(Globals.WORLD_WIDTH*0.225f)+1),rand.nextFloat()+rand.nextInt(400)+100);
            addActor(hollo);
        }

        le = new Car(true, rand.nextInt(6)+3+Globals.rand.nextFloat());
        fel = new Car(false, rand.nextInt(6)+3+Globals.rand.nextFloat());
        autok.add(fel);
        autok.add(le);
        addActor(le);
        addActor(fel);

        blood = new Blood();
        blood.setPosition(Globals.WORLD_WIDTH*0.9f,rand.nextInt((int)(Globals.WORLD_HEIGHT-blood.getHeight() + 1)));
        addActor(blood);
    }

    private void addBloodLineToEmber(Ember ember) {
        addActor(new HpFekete(ember, 30, ember.getInitialBlood()));
        addActor(new HpPiros(ember, 30));
    }


    private void addKillLineToEmber(Ember ember) {
        addActor(new HpFekete(ember, 40, ember.getKill()));
        addActor(new HpKek(ember, 40));
    }

    private void newGame(boolean win) {
        game.setScreen(new GameScreen(game));
        this.dispose();
    }

    @Override
    public void init() {
        rand = new Random();
        bg = new Bg();
        bg.setSize(getWidth(), getHeight());
        addActor(bg);
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
        szunyog.decreaseBlood(.1f);
        elapsedtime += delta;
        setDebugAll(Globals.DEBUG_ALL);
        float x = szunyog.getX()+szunyog.getWidth()/2;
        float y = szunyog.getY()+szunyog.getHeight()/2;
        /*if(flying){
            if(szunyog.getX() < gotox) szunyog.setX(x+(gotox-x<speed ? gotox-x : speed)-szunyog.getWidth()/2);
            else if(szunyog.getX() > gotox) szunyog.setX(x-(x-gotox<speed ? x-gotox : speed)-szunyog.getWidth()/2);
            if(szunyog.getY() < gotoy) szunyog.setY(y+(gotoy-y<speed ? gotoy-y : speed)-szunyog.getHeight()/2);
            else if(szunyog.getY() > gotoy) szunyog.setY(y-(y-gotoy<speed ? y-gotoy : speed)-szunyog.getHeight()/2);
            if(x == gotox && y == gotoy) flying = false;
        }*/
        float rotation = (float) ((Math.atan2(gotox - x, -(gotoy - y)) * 180.0d / Math.PI) + 180);
        float xspeed = 0, yspeed = 0;
        if(flying){
            float xcomp = gotox - x;
            float ycomp = gotoy - y;
            rotation = (float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180);
            float fps = 10 + Math.round(6 * (Math.abs(xcomp / 30) + Math.abs(ycomp / 30)) / 5);
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
            xspeed = xcomp/30 > 0 ? xcomp/30 > 8 ? 8 : xcomp/30 : xcomp/30 < -8 ? -8 : xcomp/30;
            yspeed = ycomp/30 > 0 ? ycomp/30 > 8 ? 8 : ycomp/30 : ycomp/30 < -8 ? -8 : ycomp/30;
            szunyog.setX(szunyog.getX() + xspeed);
            szunyog.setY(szunyog.getY() + yspeed);
            if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) flying = false;

        }


        if(le.getY() + le.getHeight() < 0) le = new Car(true, rand.nextInt(6)+3+Globals.rand.nextFloat());
        if(fel.getY() > Globals.WORLD_HEIGHT) fel = new Car(true, rand.nextInt(6)+3+Globals.rand.nextFloat());

        for(Ember ember : emberek){
            if(elapsedtime - ember.getStoppedTime() > ember.getKill()/105) {
                ember.setStop(false);
                ember.setStoppable(true);
            } else if (!ember.isStop()){
                ember.setStoppable(false);
                ember.setStop(false);
            }
            if (ember.overlaps(szunyog)) {
                if (overlappedEmber == null) overlappedEmber = ember;
            } else if(ember.szunyoggal > 0 ) {
                ember.szunyoggal -= .005f;
                ember.szamlalo = 0;
            }
            else {
                ember.szunyoggal = 0;
                ember.szamlalo = 0;
            }

            if(ember.isVisible()) won = false;

            for (String s : bg.getMyOverlappedShapeKeys(ember)) {
                if(s.equals("Ház")) {
                    ember.dest = new float[]{rand.nextFloat()+rand.nextInt((int)(Globals.WORLD_WIDTH*0.6804-ember.getWidth() - 50 - Globals.WORLD_WIDTH*0.225f + 10)) + Globals.WORLD_WIDTH*0.225f+10,rand.nextFloat()+rand.nextInt(Globals.WORLD_HEIGHT-1)};
                } else if (s.equals("Felső kerítés bal")) {
                    ember.dest = new float[]{rand.nextInt((int)Math.rint(Globals.WORLD_WIDTH*0.6804f)-1)+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+rand.nextFloat()};
                } else if (s.equals("Felső kerítés jobb")) {
                    ember.dest = new float[]{rand.nextInt((int)(Globals.WORLD_WIDTH-Globals.WORLD_WIDTH*0.7164f-1))+Globals.WORLD_WIDTH*0.7164f+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+rand.nextFloat()};
                } else if (s.equals("Alsó kerítés bal")) {
                    ember.dest = new float[]{rand.nextInt((int)Math.rint(Globals.WORLD_WIDTH*0.6804f)-1)+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+Globals.WORLD_HEIGHT/2+rand.nextFloat()};
                } else if (s.equals("Alsó kertés jobb"))  {
                    ember.dest = new float[]{rand.nextInt((int)(Globals.WORLD_WIDTH-Globals.WORLD_WIDTH*0.7164f-1))+Globals.WORLD_WIDTH*0.7164f+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+Globals.WORLD_HEIGHT/2+rand.nextFloat()};
                }
            }
        }

        if(won){
            newGame(true);
        }
        else won = true;

        if(overlappedEmber != null) {
            if(!overlappedEmber.overlaps(szunyog)) {
                overlappedEmber = null;
            }
        }

        if(overlappedEmber != null && szunyog.isVisible()) {
            if(overlappedEmber.isStoppable() && overlappedEmber.isVisible()){
                if(overlappedEmber.szamlalo < 1.5) {
                    szunyog.setX(overlappedEmber.getX());
                    szunyog.setY(overlappedEmber.getY());
                    flying=false;
                    xspeed = 0; yspeed = 0;
                }
                if(Math.abs(xspeed) + Math.abs(yspeed) < 1) {
                    overlappedEmber.setStop(true);
                    overlappedEmber.szunyoggal+=delta;
                    overlappedEmber.szamlalo+=delta;
                    overlappedEmber.decreaseBlood(1.25f);
                    szunyog.increaseBlood(.2f);
                }


                if(overlappedEmber.szunyoggal > overlappedEmber.getKill()/105) {
                    overlappedEmber.setStoppedTime(elapsedtime);
                    if(!vanRobbanas) {
                        overlappedEmber.szunyoggal = 0;
                        overlappedEmber = null;
                        Explosion explosion = new Explosion();
                        explosion.setPosition(szunyog.getX()-szunyog.getWidth()/2, szunyog.getY()+szunyog.getHeight()/2);
                        getActors().removeValue(szunyog, true);
                        szunyog.setVisible(false);
                        addActor(explosion);
                        vanRobbanas=true;
                        newGame(false);
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


   /*     for (Actor actor : getActors()) {
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
        } */

        for (Car car : autok) {
            if(car.overlaps(szunyog)) {
                System.out.println("ZUTTY");
                getActors().removeValue(szunyog, true);
                szunyog.setVisible(false);
                newGame(false);
            }
        }

        if(szunyog.overlaps(blood) && blood.isVisible()) {
            szunyog.increaseBlood(50);
            getActors().removeValue(blood, true);
            blood.setVisible(false);
        }
    }
    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
        bg.setSize(screenWidth, screenHeight);
    }



    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }
}
