package hu.csanysoft.bloodyhell.Game;

import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyScreen;
import hu.csanysoft.bloodyhell.BloodyHell;
import hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D.MyStage;

public class GameScreen extends MyScreen {

    private final MyStage stage;

    public GameScreen(BloodyHell game, boolean won) {
        super(game);
        //System.out.println("wonS = " + won);
        stage = new GameStage(game, won);

    }

    public GameScreen(BloodyHell game) {
        super(game);
        stage = new GameStage(game, false);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }


    @Override
    public void init() {
        setBackGroundColor(1,1,1);
    }

    @Override
    public void resume() {
        super.resume();
    }

}
