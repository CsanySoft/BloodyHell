package hu.csanysoft.bloodyhell.Bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanysoft.bloodyhell.MyBaseClasses.ShapeType;
import hu.csanysoft.bloodyhell.MyBaseClasses.WorldActorGroup;

public class ValamiSzenzor extends WorldActorGroup {
    public ValamiSzenzor(World world, float x, float y) {
        super(world, ShapeType.Circle, BodyDef.BodyType.StaticBody, 0, 0, 0, true);
        addToWorld();
        setSize(100,100);
        setPosition(x,y);
    }
}
