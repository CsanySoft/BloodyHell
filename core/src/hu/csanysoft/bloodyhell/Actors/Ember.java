package hu.csanysoft.bloodyhell.Actors;

import java.util.Random;

import hu.csanysoft.bloodyhell.Game.GameStage;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Ember extends OneSpriteAnimatedActor {
    GameStage gameStage;
    float gotox, gotoy, xspeed, yspeed, x, y;
    boolean szembe;
    float[] dest;
    Random rand;
    boolean stop = false, stoppable = true;
    float stoppedTime = -5;
    public float szunyoggal = 0;
    float blood = 100, initialBlood;
    float toughness;
    float kill;

    public Ember(float speed, float[] dest) {
        super(Assets.manager.get(Assets.WALK_TEXTURE));
        this.dest = dest;
        setFps(11);
        rand = new Random();
        setSize(128,128);
        addCollisionShape("Tor", new MyRectangle(128, 48,0, 40));
        addBaseCollisionRectangleShape();
        toughness = rand.nextFloat()+rand.nextInt(3)+2;
        blood *= toughness;
        initialBlood = blood;
        kill = toughness *105f;
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
            setFps(11);
            if(blood<initialBlood)blood+=0.3f*toughness;
            gotox = dest[0]; gotoy = dest[1];
            x = getX()+getWidth()/2;
            y = getY()+getHeight()/2;
            float xcomp = gotox - x;
            float ycomp = gotoy - y;
            xspeed = xcomp/30 > 0 ? xcomp/30 > 2 ? 2 : xcomp/30 : xcomp/30 < -2 ? -2 : xcomp/30;
            yspeed = ycomp/30 > 0 ? ycomp/30 > 2 ? 2 : ycomp/30 : ycomp/30 < -2 ? -2 : ycomp/30;
            setX(getX()+xspeed);
            setY(getY()+yspeed);
            if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) {
                dest = new float[]{rand.nextFloat()+rand.nextInt(1000)+100,rand.nextFloat()+rand.nextInt(400)+100};
            }
            setRotation((float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180));
        } else {
            setFps(0);
            if(blood <= 0) {
                setVisible(false);
                stoppable = false;
                getStage().getActors().removeValue(this, true);
            }
        }
    }

}