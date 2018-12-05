package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

import static com.badlogic.gdx.math.MathUtils.random;

public class Hollo extends OneSpriteAnimatedActor {

    private float gotox;
    private float gotoy;
    private final Szunyog szunyog;

    public Hollo(Szunyog szunyog) {
        super(Assets.manager.get(Assets.HOLLO_TEXTURE));
        this.szunyog = szunyog;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        float x = getX() + getWidth() / 2;
        float y = getY() + getHeight() / 2;
        if(szunyog.getX()+szunyog.getWidth()/2 < Globals.WORLD_WIDTH*0.225f) {
            gotox = szunyog.getX()+szunyog.getWidth()/2;
            gotoy = szunyog.getY()+szunyog.getHeight()/2;
        }else {
            if(Math.abs(gotox- x) < 1 && Math.abs(gotoy- y) < 1){
                gotox = random(0, Globals.WORLD_WIDTH *0.225f);
                gotoy = random(0, Globals.WORLD_HEIGHT);
            }
        }
        float xcomp = gotox - x;
        float ycomp = gotoy - y;
        float xspeed = xcomp / 30 > 0 ? xcomp / 30 > 2 ? 2 : xcomp / 30 : xcomp / 30 < -2 ? -2 : xcomp / 30;
        float yspeed = ycomp / 30 > 0 ? ycomp / 30 > 2 ? 2 : ycomp / 30 : ycomp / 30 < -2 ? -2 : ycomp / 30;
        setX(getX()+ xspeed);
        setY(getY()+ yspeed);
    }
}
