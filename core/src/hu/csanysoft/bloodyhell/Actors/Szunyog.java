package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.OneSpriteAnimatedActor;
import hu.csanysoft.bloodyhell.MyBaseClasses.OneSpriteStaticActor;

public class Szunyog extends OneSpriteAnimatedActor {
    public Szunyog(float x, float y) {
        super(Assets.manager.get(Assets.MOSQUITO_TEXTURE));
        setPosition(x,y);
        setFps(25);
        setSize(100,100);
    }

}
