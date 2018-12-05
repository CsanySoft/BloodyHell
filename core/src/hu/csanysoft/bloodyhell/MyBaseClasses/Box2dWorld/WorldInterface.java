package hu.csanysoft.bloodyhell.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

@SuppressWarnings("SameReturnValue")
interface WorldInterface {
    void addToWorld();
    void removeFromWorld();
    void setInActive();
    void setActive();
    boolean isActive();
    Body getBody();
    void setSensor(boolean sensor);
}
