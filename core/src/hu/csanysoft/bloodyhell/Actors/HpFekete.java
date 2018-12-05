package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class HpFekete extends OneSpriteStaticActor {

    Ember ember;
    float y;

    public HpFekete(Ember ember, float y, float width) {
        super(Assets.manager.get(Assets.HP_FEKETE_TEXTURE));
        this.ember = ember;
        this.y = y;
        setWidth(width/3);
        setHeight(getHeight()/3);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(ember.getX()+ember.getWidth()/2-getWidth()/2, ember.getY()+ember.getHeight()+y);
        if(!ember.isVisible()) getStage().getActors().removeValue(this, true);
    }
}
