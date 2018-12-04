package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class KajaCsik extends OneSpriteStaticActor {

    Szunyog szunyog;

    public KajaCsik(Szunyog szunyog) {
        super(Assets.manager.get(Assets.FOODBAR_TEXTURE));
        setSize(Globals.WORLD_WIDTH, 50);
        setPosition(0,0);
        this.szunyog = szunyog;
    }

 /*   public void decreaseFood(float amount) {
        foodleft -= amount;
        if(foodleft < 0) foodleft = 0;
        setWidth(Globals.WORLD_WIDTH * (foodleft/100));
        //System.out.println("Food amount: "+foodleft);
    }
    public void increaseFood(float amount) {
        foodleft += amount;
        if(foodleft > 100 ) foodleft = 100;
        setWidth(Globals.WORLD_WIDTH * (foodleft/100));
        //System.out.println("Food amount: "+foodleft);
    }

    public float getAmount(){
        return foodleft;
    } */

    @Override
    public void act(float delta) {
        super.act(delta);
        setWidth(Globals.WORLD_WIDTH * (szunyog.getBlood()/100));
    }
}
