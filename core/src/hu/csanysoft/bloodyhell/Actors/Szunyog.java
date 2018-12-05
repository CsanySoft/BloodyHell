package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyCircle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Szunyog extends OneSpriteAnimatedActor {
    private float blood = 100;
    
    public Szunyog(float x, float y) {
        super(Assets.manager.get(Assets.MOSQUITO_TEXTURE));
        setPosition(x,y);
        float fps = 25;
        if(fps < 1) stop();
        else start();
        setFps(fps);
        setSize(100,100);
        addBaseCollisionRectangleShape();
        setZIndex(1);
        addCollisionShape("Fej", new MyCircle(10, 40,70));
        addCollisionShape("Tor", new MyRectangle(20, 70,40, 0));
        addCollisionShape("Balszarny", new MyRectangle(20, 10,20, 67));
        addCollisionShape("Jobbszarny", new MyRectangle(20, 10,60, 67));
    }

    public void decreaseBlood(float amount) {
        blood -= amount;
        if(blood < 0) blood = 0;
        //System.out.println("Food amount: "+foodleft);
    }
    public void increaseBlood(float amount) {
        blood += amount;
        if(blood > 100 ) blood = 100;
        //System.out.println("Food amount: "+foodleft);
    }

    public float getBlood() {
        return blood;
    }

}
