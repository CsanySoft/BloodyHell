package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.audio.Music;

import hu.csanysoft.bloodyhell.Game.GameScreen;
import hu.csanysoft.bloodyhell.Game.GameStage;
import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Car extends OneSpriteStaticActor {

    private final boolean szembe;
    private final float speed;
    GameStage gameStage;

    private static Music streetSound = Assets.manager.get(Assets.STREET_SOUND);

    public Car(boolean szembe, float speed) {
        super(Assets.manager.get(Assets.CAR1_TEXTURE));
        if(!streetSound.isPlaying()) {
            streetSound.play();
            streetSound.setLooping(true);
        }
        setSize(getWidth()/4, getHeight()/4);
        if(szembe){
            setY(Globals.WORLD_HEIGHT);
            setRotation(180);
        } else {
            setY(0-getHeight());
        }
        this.szembe = szembe;
        this.speed = speed;
        addBaseCollisionRectangleShape();
        gameStage = (GameStage) getStage();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!szembe) {
            setPosition(Globals.WORLD_WIDTH*0.8f+getWidth()+30, getY()+speed);
            if(getY() > Globals.WORLD_HEIGHT){
                gameStage = (GameStage) getStage();
                getStage().getActors().removeValue(this, true);
                remove();
                Car car = new Car(false, Globals.rand.nextInt(3)+3+Globals.rand.nextFloat());
                System.out.println(gameStage.autok.size());
                gameStage.autok.set(0,car);
                getStage().addActor(car);

            }
        }
        else{
            setPosition(Globals.WORLD_WIDTH*0.8f, getY()-speed);
            if(getY() < 0-getHeight()){
                gameStage = (GameStage) getStage();
                getStage().getActors().removeValue(this, true);
                remove();
                Car car = new Car(true, Globals.rand.nextInt(3)+3+Globals.rand.nextFloat());
                gameStage.autok.set(1,car);
                getStage().addActor(car);
            }
        }

    }

}
