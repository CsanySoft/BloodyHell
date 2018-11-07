package hu.csanysoft.bloodyhell.Bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanysoft.bloodyhell.MyBaseClasses.OneSpriteStaticActor;
import hu.csanysoft.bloodyhell.MyBaseClasses.ShapeType;
import hu.csanysoft.bloodyhell.MyBaseClasses.WorldActorGroup;
import hu.csanysoft.bloodyhell.MyBaseClasses.WorldBodyEditorLoader;

public class Ize extends WorldActorGroup {

    OneSpriteStaticActor actor;

    public Ize(World world, WorldBodyEditorLoader loader, int x, int y) {
        super(world, loader, "Name", BodyDef.BodyType.DynamicBody, 0, 0.2f, 5, false);
        addActor(actor = new OneSpriteStaticActor("badlogic.jpg"));
        actor.setSize(100,100);
        addActor(actor);
        setSize(100,100);
        addToWorld();
        setPosition(x,y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
