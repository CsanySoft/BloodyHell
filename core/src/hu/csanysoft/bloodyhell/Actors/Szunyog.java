package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Szunyog extends OneSpriteAnimatedActor {
    public Szunyog(float x, float y) {
        super(Assets.manager.get(Assets.MOSQUITO_TEXTURE));
        setPosition(x,y);
        if(fps < 1) stop();
        else start();
        setFps(fps);
        setSize(100,100);

    }

    public float fps = 25;

    @Override
    public void act(float delta) {
        super.act(delta);
        //setFps(fps);
    }
}
