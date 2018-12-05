package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Car extends OneSpriteStaticActor {

    boolean szembe;
    float speed;

    public Car(boolean szembe, float speed) {
        super(Assets.manager.get(Assets.CAR1_TEXTURE));
        setSize(getWidth()/4, getHeight()/4);
        if(szembe){
            setY(Globals.WORLD_HEIGHT);
            setRotation(180);
        } else {
            setY(0-getHeight());
        }
        this.szembe = szembe;
        this.speed = speed;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!szembe) {
            setPosition(Globals.WORLD_WIDTH*0.8f+getWidth()+30, getY()+speed);
            if(getY() > Globals.WORLD_HEIGHT){
                getStage().getActors().removeValue(this, true);
                getStage().addActor(new Car(false, Globals.rand.nextInt(3)+3+Globals.rand.nextFloat()));
            }
        }
        else{
            setPosition(Globals.WORLD_WIDTH*0.8f, getY()-speed);
            if(getY() < 0-getHeight()){
                getStage().getActors().removeValue(this, true);
                getStage().addActor(new Car(true, Globals.rand.nextInt(3)+3+Globals.rand.nextFloat()));
            }
        }

    }
}
