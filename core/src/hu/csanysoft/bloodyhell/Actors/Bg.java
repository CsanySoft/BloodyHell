package hu.csanysoft.bloodyhell.Actors;

import hu.csanysoft.bloodyhell.Global.Assets;
import hu.csanysoft.bloodyhell.Global.Globals;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyCircle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Bg extends OneSpriteStaticActor {

    public Bg(boolean won) {
        super(won ? Assets.manager.get(Assets.BACKGROUND2_TEXTURE) : Assets.manager.get(Assets.BACKGROUND1_TEXTURE));
        if(!won) {


            addCollisionShape("Kerítés", new MyRectangle((Globals.WORLD_WIDTH*0.7164f-Globals.WORLD_WIDTH*0.6804f)/2,Globals.WORLD_HEIGHT,Globals.WORLD_WIDTH*0.6804f,0));

            addCollisionShape("Ház", new MyRectangle(Globals.WORLD_WIDTH*0.26f, Globals.WORLD_HEIGHT,0,0));
        } else {
                     //Második pálya collisionjai beállítva
        //addCollisionShape("Asztal", new MyRectangle((Globals.WORLD_WIDTH*0.3050f),Globals.WORLD_HEIGHT*0.2444f,Globals.WORLD_WIDTH*0.3554f,Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.60f));
        addCollisionShape("Rovarirtó", new MyRectangle((Globals.WORLD_WIDTH*0.0648f),Globals.WORLD_HEIGHT*0.1166f,Globals.WORLD_WIDTH*0.48125f,Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.55f));
        addCollisionShape("Kanapé felső", new MyRectangle((Globals.WORLD_WIDTH*0.28f),Globals.WORLD_HEIGHT*0.1388f,Globals.WORLD_WIDTH*0.0203f,Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.2083f));
        addCollisionShape("Kanapé alsó", new MyRectangle((Globals.WORLD_WIDTH*0.0953f),Globals.WORLD_HEIGHT*0.25f,Globals.WORLD_WIDTH*0.0203f,Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.459f));
        addCollisionShape("Virág nagy alsó" , new MyCircle(Globals.WORLD_WIDTH*0.078f ,  8 , 5) ); // TODO: 2018. 12. 05. ha elcsúszik akkor javítani
        addCollisionShape("Virág nagy felső" , new MyCircle(Globals.WORLD_WIDTH*0.065f ,  Globals.WORLD_WIDTH*0.6875f , Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.25f) );
        addCollisionShape("Virág nagy felső" , new MyCircle(Globals.WORLD_WIDTH*0.065f ,  Globals.WORLD_WIDTH*0.6875f , Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.25f) );
        addCollisionShape("Virág kicsi alsó" , new MyCircle(Globals.WORLD_WIDTH*0.0429f ,  Globals.WORLD_WIDTH*0.881f , Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.79f) );
        addCollisionShape("Virág kicsi felső" , new MyCircle(Globals.WORLD_WIDTH*0.0429f ,  Globals.WORLD_WIDTH*0.881f , Globals.WORLD_HEIGHT - Globals.WORLD_HEIGHT*0.355f) );
        }



    }

}
