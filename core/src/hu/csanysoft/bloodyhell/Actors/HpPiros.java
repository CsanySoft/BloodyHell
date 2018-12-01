package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class HpPiros extends OneSpriteStaticActor {

    Ember ember;

    public HpPiros(Ember ember) {
        super(Assets.manager.get(Assets.HP_PIROS_TEXTURE));
        this.ember = ember;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(ember.getX()+ember.getWidth()/2-getWidth()/2, ember.getY()+ember.getHeight()+30);
        setWidth(ember.getBlood());
        if(!ember.isVisible()) getStage().getActors().removeValue(this, true);
    }
}
