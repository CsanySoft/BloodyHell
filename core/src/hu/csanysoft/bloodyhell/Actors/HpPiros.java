package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class HpPiros extends OneSpriteStaticActor {

    Ember ember;
    float y;

    public HpPiros(Ember ember, float y) {
        super(Assets.manager.get(Assets.HP_PIROS_TEXTURE));
        this.ember = ember;
        this.y = y;
        setHeight(getHeight()/3);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(ember.getX()+ember.getWidth()/2-getWidth()/2, ember.getY()+ember.getHeight()+y);
        setWidth(ember.getBlood()/3);
        if(!ember.isVisible()) getStage().getActors().removeValue(this, true);
    }
}
