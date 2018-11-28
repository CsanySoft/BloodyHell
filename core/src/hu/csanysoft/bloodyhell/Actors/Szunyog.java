package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyCircle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Szunyog extends OneSpriteAnimatedActor {
    public Szunyog(float x, float y) {
        super(Assets.manager.get(Assets.MOSQUITO_TEXTURE));
        setPosition(x,y);
        if(fps < 1) stop();
        else start();
        setFps(fps);
        setSize(100,100);
        setZIndex(100);
        addCollisionShape("Fej", new MyCircle(10, 40,70));
        addCollisionShape("Tor", new MyRectangle(20, 70,40, 0));
        addCollisionShape("Balszarny", new MyRectangle(20, 10,20, 67));
        addCollisionShape("Jobbszarny", new MyRectangle(20, 10,60, 67));
    }

    public float fps = 25;

    @Override
    public void act(float delta) {
        super.act(delta);
        //setFps(fps);
    }
}
