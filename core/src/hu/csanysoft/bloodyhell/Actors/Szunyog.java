package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.OneSpriteStaticActor;

public class Szunyog extends OneSpriteStaticActor {
    public Szunyog(float x, float y) {
        super(Assets.manager.get(Assets.MOSQUITO));
        setPosition(x,y);
        setSize(100,100);
    }

}
