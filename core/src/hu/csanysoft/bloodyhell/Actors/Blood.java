package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Blood extends OneSpriteStaticActor {

    public Blood() {
        super(Assets.manager.get(Assets.BLOOD_TEXTURE));
        setSize(getWidth()/4, getHeight()/3.5f);
        addBaseCollisionRectangleShape();
    }

    @Override
    public void act(float delta) {
        elapsedTime += delta;
        super.act(delta);
        setSize(getWidth() + (float)Math.sin(elapsedTime*5f), getHeight() + (float)Math.sin(elapsedTime*5f));
    }
}
