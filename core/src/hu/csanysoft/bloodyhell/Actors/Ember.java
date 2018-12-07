package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.audio.Music;

import java.util.Random;

import hu.csanysoft.bloodyhell.Game.GameStage;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

@SuppressWarnings("UnusedAssignment")
public class Ember extends OneSpriteAnimatedActor {
    GameStage gameStage;
    public float[] dest;
    private final Random rand;
    private boolean stop = false;
    private boolean stoppable = true;
    private float stoppedTime = -5;
    public float szunyoggal = 0;
    private float blood = 100;
    private final float initialBlood;
    private final float toughness;
    final float kill;
    public float szamlalo;
    public static int db = 0;
    public int id = 0;
    private Music walking = Assets.manager.get(Assets.WALKING_SOUND);

    public Ember() {
        super(Assets.manager.get(Assets.WALK_TEXTURE));
        setFps(11);
        rand = new Random();
        //addBaseCollisionRectangleShape();
        toughness = rand.nextFloat()+rand.nextInt(3)+2;
        blood *= toughness;
        initialBlood = blood;
        kill = toughness *105f;
        setSize(128,128);
        setZIndex(0);
        addCollisionShape("Tor", new MyRectangle(128, 48,0, 40));
        id = db++;
        this.gameStage = (GameStage) getStage();
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public float getStoppedTime() {
        return stoppedTime;
    }

    public void setStoppedTime(float stoppedTime) {
        this.stoppedTime = stoppedTime;
    }

    public boolean isStoppable() {
        return stoppable;
    }

    public void setStoppable(boolean stoppable) {
        this.stoppable = stoppable;
    }

    public void decreaseBlood(float value) {blood-=value;}

    public float getBlood() {
        return blood;
    }

    public float getKill() {
        return kill;
    }

    public float getInitialBlood() {

        return initialBlood;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!stop) {

            if(szamlalo>0) szamlalo-=0.005;
            else szamlalo = 0;
            walking.play();
            walking.setLooping(true);
            float gotox = dest[0];
            float gotoy = dest[1];
            float x = getX() + getWidth() / 2;
            float y = getY() + getHeight() / 2;
            float xcomp = gotox - x;
            float ycomp = gotoy - y;
            fps = 4 + Math.round( 4 * (Math.abs(xcomp/30)+Math.abs(ycomp/30))/5);
            setFps(fps > 11 ? 11 : fps == 4 ? 0 : fps);
            float xspeed = xcomp / 30 > 0 ? xcomp / 30 > 2 ? 2 : xcomp / 30 : xcomp / 30 < -2 ? -2 : xcomp / 30;
            float yspeed = ycomp / 30 > 0 ? ycomp / 30 > 2 ? 2 : ycomp / 30 : ycomp / 30 < -2 ? -2 : ycomp / 30;
            setX(getX()+ xspeed);
            setY(getY()+ yspeed);
            if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) {
                gameStage = (GameStage) getStage();
                gameStage.newDestForEmber(this);
            }
            setRotation((float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180));
        } else {
            if(szamlalo < szunyoggal)
            szamlalo = szunyoggal;
            walking.dispose();
            setFps(0);
            stop();
            if(blood <= 0) {
                setVisible(false);
                stoppable = false;
                getStage().getActors().removeValue(this, true);
            }
        }


    }



}