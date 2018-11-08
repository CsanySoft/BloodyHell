package hu.csanysoft.bloodyhell.Bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanysoft.bloodyhell.MyBaseClasses.OneSpriteStaticActor;
import hu.csanysoft.bloodyhell.MyBaseClasses.ShapeType;
import hu.csanysoft.bloodyhell.MyBaseClasses.WorldActorGroup;

public class Doboz extends WorldActorGroup {

    OneSpriteStaticActor actor;

    public Doboz(World world, float x, float y, float rotation) {
        super(world, ShapeType.Rectangle, BodyDef.BodyType.StaticBody, 10, 1f, 8, false);
        addActor(actor = new OneSpriteStaticActor("badlogic.jpg"));
        actor.setSize(400, 100);
        setSize(400, 100);
        addToWorld();
        setRotation(rotation);
        setPosition(x,y);
    }

}
