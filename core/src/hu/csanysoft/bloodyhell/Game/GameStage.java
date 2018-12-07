package hu.csanysoft.bloodyhell.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.bloodyhell.BloodyHell;

@SuppressWarnings("unchecked")
public class GameStage extends MyStage {

    private float elapsedtime = 0;
    private float gotox= 0;
    private float gotoy=0;
    private boolean flying = false;
    private Random rand;
    public ArrayList<Ember> emberek = new ArrayList();
    public ArrayList<Car> autok = new ArrayList<Car>();
    private Ember overlappedEmber = null;
    public boolean won;
    private Bg bg;
    public Car le;

    public Car setLe(Car le) {
        this.le = le;
        return le;
    }

    public Car setFel(Car fel) {
        this.fel = fel;
        return fel;
    }

    public Car fel;
    private final Blood blood;
    private KajaCsik kajaCsik;

    private boolean vanRobbanas = false;

    private Szunyog szunyog;

    public GameStage(BloodyHell game, boolean won) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(this);
        this.won = won;
        bg = new Bg(won);
        bg.setSize(getWidth(), getHeight());
        addActor(bg);
        for (int i = 0; i < 3; i++) {
            Ember ember = new Ember();
            ember.setPosition(Globals.WORLD_WIDTH/2, 0);
            addActor(ember);
            ember.setRotation(180);
            newDestForEmber(ember);
            emberek.add(ember);
            addBloodLineToEmber(ember);
            addKillLineToEmber(ember);
        }

        blood = new Blood();
        blood.setPosition(Globals.WORLD_WIDTH*0.9f,rand.nextInt((int)(Globals.WORLD_HEIGHT-blood.getHeight() + 1)));
        addActor(blood);
        addActor(szunyog = new Szunyog(400, 300));
        addActor(kajaCsik = new KajaCsik(szunyog));

        if(!won) {
            le = new Car(true, rand.nextInt(6)+3+Globals.rand.nextFloat());
            fel = new Car(false, rand.nextInt(6)+3+Globals.rand.nextFloat());
            autok.add(0,fel);
            autok.add(1,le);
            addActor(le);
            addActor(fel);
                for (int i = 0; i < 1; i++) {
                Hollo hollo = new Hollo(szunyog);
                hollo.setPosition(rand.nextFloat()+rand.nextInt((int)(Globals.WORLD_WIDTH*0.225f)+1),rand.nextFloat()+rand.nextInt(400)+100);
                addActor(hollo);
            }
        }




    }

    public void newDestForEmber(Ember ember) {
        float[] dest = new float[]{rand.nextInt(Globals.WORLD_WIDTH - 1) + rand.nextFloat(), rand.nextInt(Globals.WORLD_HEIGHT - 1) + 1};
        ember.dest = dest;
        float[] dest2 = {ember.getX()+ember.getWidth()/2, ember.getY()+ember.getHeight()};
        ember.addCollisionShape("teszt", (new MyRectangle(ember.getWidth(), Math.abs(new Vector2(dest2[0], dest2[1]).dst(new Vector2(dest[0], dest[1]))),0, ember.getHeight(),ember.getWidth()/2,ember.getHeight()/2, (float) ((Math.atan2(dest[0] - dest2[0], -(dest[1] - dest2[1])) * 180.0d / Math.PI) + 180),0)));

        for(String s : ember.getMyOverlappedShapeKeys(bg)){
            if(s.equals("teszt"))newDestForEmber(ember);
        }
        /*if((new MyRectangle(2, new Vector3(dest).dst2(new Vector3(ember.dest)),ember.getX(), ember.getY(),0,0,(float)(Math.atan2(ember.getX() - dest[0], -(ember.getY() - dest[1])) * 180.0d / Math.PI),0))) {

        }*/
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
        game.setScreen(new GameScreen(game, win));
        this.dispose();
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


        if(le != null && fel != null) {
            if(le.getY() + le.getHeight() < 0){
                le = new Car(false, rand.nextInt(6)+3+Globals.rand.nextFloat());
            }
            if(fel.getY() > Globals.WORLD_HEIGHT) fel = new Car(true, rand.nextInt(6)+3+Globals.rand.nextFloat());
        }



        for(Ember ember : emberek){

            if(Gdx.input.isKeyPressed(Input.Keys.K)) newDestForEmber(ember);
            if(Gdx.input.isKeyPressed(Input.Keys.A)) bg.removeCollisionShape("teszt"+ember.id);

            if(elapsedtime - ember.getStoppedTime() > ember.getKill()/105) {
                ember.setStop(false);
                ember.setStoppable(true);
            } else if (!ember.isStop()){
                ember.setStoppable(false);
                ember.setStop(false);
            }
            for(String s : ember.getMyOverlappedShapeKeys(szunyog)) {
               // System.out.println(s);
                if(s.equals("Tor")) {
                    if (overlappedEmber == null) {
                        overlappedEmber = ember;
                        System.out.println("overlapped");
                    }
                } else if(s.equals("Tor") && ember.szunyoggal > 0 ) {
                    ember.szunyoggal -= .005f;
                    ember.szamlalo = 0;
                }
                else {
                    ember.szunyoggal = 0;
                    ember.szamlalo = 0;
                }

            }

            if(ember.isVisible()) won = false;

            for (String s : bg.getMyOverlappedShapeKeys(ember)) {
                if(s.equals("Ház")) {
                    //ember.dest = new float[]{rand.nextFloat()+rand.nextInt((int)(Globals.WORLD_WIDTH*0.6804-ember.getWidth() - 50 - Globals.WORLD_WIDTH*0.225f + 10)) + Globals.WORLD_WIDTH*0.225f+10,rand.nextFloat()+rand.nextInt(Globals.WORLD_HEIGHT-1)};
                } else if (s.equals("Felső kerítés bal")) {
                    //ember.dest = new float[]{rand.nextInt((int)Math.rint(Globals.WORLD_WIDTH*0.6804f)-1)+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+rand.nextFloat()};
                } else if (s.equals("Felső kerítés jobb")) {
                    //ember.dest = new float[]{rand.nextInt((int)(Globals.WORLD_WIDTH-Globals.WORLD_WIDTH*0.7164f-1))+Globals.WORLD_WIDTH*0.7164f+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+rand.nextFloat()};
                } else if (s.equals("Alsó kerítés bal")) {
                    //ember.dest = new float[]{rand.nextInt((int)Math.rint(Globals.WORLD_WIDTH*0.6804f)-1)+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+Globals.WORLD_HEIGHT/2+rand.nextFloat()};
                } else if (s.equals("Alsó kertés jobb"))  {
                    //ember.dest = new float[]{rand.nextInt((int)(Globals.WORLD_WIDTH-Globals.WORLD_WIDTH*0.7164f-1))+Globals.WORLD_WIDTH*0.7164f+rand.nextFloat(),rand.nextInt(Globals.WORLD_HEIGHT/2)+Globals.WORLD_HEIGHT/2+rand.nextFloat()};
                }
            }
        }

        if(won){
            newGame(true);
        }
        else won = true;

        if(overlappedEmber != null) {
            System.out.println("Van");
            if(overlappedEmber.isVisible()) {
                for(String s : overlappedEmber.getMyOverlappedShapeKeys(szunyog)) {
                    if(!s.equals("Tor")) {
                        System.out.println("Töröl");
                        overlappedEmber = null;
                    }
                }
            } else overlappedEmber = null;


        } else System.out.println("Nincs");

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
                    overlappedEmber.decreaseBlood(10.25f);
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
            //System.out.println(car.getY());
            if(szunyog.overlaps(car)) {
                //System.out.println("ZUTTY");
                getActors().removeValue(szunyog, true);
                //szunyog.setVisible(false);
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
