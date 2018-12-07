package hu.csanysoft.bloodyhell.Actors;

import com.badlogic.gdx.audio.Music;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyCircle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Bg extends OneSpriteStaticActor {

    private Music streetSound = Assets.manager.get(Assets.STREET_SOUND);
    private Music gardenSound = Assets.manager.get(Assets.GARDEN_SOUND);

    public Bg(boolean won) {
        super(Assets.manager.get(Assets.BACKGROUND1_TEXTURE));
            streetSound.play();
            gardenSound.play();
            streetSound.setLooping(true);
            gardenSound.setLooping(true);
            addCollisionShape("Ház", new MyRectangle(Globals.WORLD_WIDTH*0.225f, Globals.WORLD_HEIGHT,0,0));
    }

}
