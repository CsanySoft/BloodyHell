package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Explosion extends OneSpriteAnimatedActor {

    public Explosion() {
        super(Assets.manager.get(Assets.EXPLOSION_TEXTURE));
        setFps(20);
    }

    @Override
    protected void repeated() {
        if(getStage()!=null) {
            getStage().getActors().removeValue(this, true);
        }
        super.repeated();
    }
}
