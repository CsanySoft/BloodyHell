package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

import static com.badlogic.gdx.math.MathUtils.random;

public class Hollo extends OneSpriteAnimatedActor {

    float gotox, gotoy, x, y, xspeed, yspeed;
    Szunyog szunyog;

    public Hollo(Szunyog szunyog) {
        super(Assets.manager.get(Assets.HOLLO_TEXTURE));
        this.szunyog = szunyog;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        x = getX()+getWidth()/2;
        y = getY()+getHeight()/2;
        if(szunyog.getX() < Globals.WORLD_WIDTH*0.225f) {
            gotox = szunyog.getX();
            gotoy = szunyog.getY();
        }else {
            if(Math.abs(gotox-x) < 1 && Math.abs(gotoy-y) < 1){
                gotox = random(0, (int)Globals.WORLD_WIDTH*0.225f);
                gotoy = random(0, Globals.WORLD_HEIGHT);
            }
        }
        float xcomp = gotox - x;
        float ycomp = gotoy - y;
        xspeed = xcomp/30 > 0 ? xcomp/30 > 2 ? 2 : xcomp/30 : xcomp/30 < -2 ? -2 : xcomp/30;
        yspeed = ycomp/30 > 0 ? ycomp/30 > 2 ? 2 : ycomp/30 : ycomp/30 < -2 ? -2 : ycomp/30;
        setX(getX()+xspeed);
        setY(getY()+yspeed);
    }
}
