package hu.csanysoft.bloodyhell.Actors;

import java.util.Random;

import hu.csanysoft.bloodyhell.Game.GameStage;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Ember extends OneSpriteAnimatedActor {
    GameStage gameStage;
    float speed, gotox, gotoy, xspeed, yspeed, x, y;
    boolean szembe;
    float[] dest;
    Random rand;

    public Ember(float speed, float[] dest) {
        super(Assets.manager.get(Assets.WALK_TEXTURE));
        this.speed = speed;
        this.dest = dest;
        setFps(11);
        rand = new Random();
    }



    @Override

    public void act(float delta) {
        super.act(delta);
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
    }

}