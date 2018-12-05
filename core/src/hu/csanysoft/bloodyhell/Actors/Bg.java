package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Bg extends OneSpriteStaticActor {

    public Bg() {
        super(Assets.manager.get(Assets.BACKGROUND1_TEXTURE));
        addCollisionShape("Felső kerítés bal", new MyRectangle((Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,Globals.WORLD_HEIGHT*0.3f,Globals.WORLD_WIDTH*0.6804f,Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.3f));
        addCollisionShape("Felső kerítés jobb", new MyRectangle((Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,Globals.WORLD_HEIGHT*0.3f,Globals.WORLD_WIDTH*0.6804f + (Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.3f));
        addCollisionShape("Alsó kerítés bal", new MyRectangle((Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,Globals.WORLD_HEIGHT*0.45f,Globals.WORLD_WIDTH*0.6804f,0));
        addCollisionShape("Alsó kerítés jobb", new MyRectangle((Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,Globals.WORLD_HEIGHT*0.45f,Globals.WORLD_WIDTH*0.6804f + (Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,0));
        addCollisionShape("Ház", new MyRectangle(Globals.WORLD_WIDTH*0.225f, Globals.WORLD_HEIGHT,0,0));
    }

}
