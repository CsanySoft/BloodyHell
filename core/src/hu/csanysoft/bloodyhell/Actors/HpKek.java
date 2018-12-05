package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class HpKek extends OneSpriteStaticActor {

    private final Ember ember;
    private final float y;

    public HpKek(Ember ember, float y) {
        super(Assets.manager.get(Assets.HP_KEK_TEXTURE));
        this.ember = ember;
        this.y = y;
        setHeight(getHeight()/3);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(ember.getX()+ember.getWidth()/2-getWidth()/2, ember.getY()+ember.getHeight()+y);
        if(getWidth() <= ember.kill) setWidth(ember.szunyoggal*105/3);
        if(getWidth() > ember.szunyoggal*105) setWidth(ember.szunyoggal*105/3);
        if(!ember.isVisible()) getStage().getActors().removeValue(this, true);
    }
}
