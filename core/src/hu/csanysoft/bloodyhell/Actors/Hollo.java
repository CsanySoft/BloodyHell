package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

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
        gotox = szunyog.getX(); gotoy = szunyog.getY();
        x = getX()+getWidth()/2;
        y = getY()+getHeight()/2;
        float xcomp = gotox - x;
        float ycomp = gotoy - y;
        xspeed = xcomp/30 > 0 ? xcomp/30 > 2 ? 2 : xcomp/30 : xcomp/30 < -2 ? -2 : xcomp/30;
        yspeed = ycomp/30 > 0 ? ycomp/30 > 2 ? 2 : ycomp/30 : ycomp/30 < -2 ? -2 : ycomp/30;
        setX(getX()+xspeed);
        setY(getY()+yspeed);
    }
}
