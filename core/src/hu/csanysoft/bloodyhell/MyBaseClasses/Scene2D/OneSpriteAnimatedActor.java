package hu.csanysoft.bloodyhell.MyBaseClasses.Scene2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OneSpriteAnimatedActor extends OneSpriteActor {

    private final TextureAtlas textureAtlas;
    protected float fps = 30;
    private boolean running = true;
    private boolean looping = true;
    private float animationTime = 0;

    private int prevFrame = 0;


    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    @SuppressWarnings("SameReturnValue")
    public int getActualFrame() {
        return 0;
    }

    public OneSpriteAnimatedActor(String file) {
        super(null);
        textureAtlas = new TextureAtlas(Gdx.files.internal(file));
        sprite = new Sprite(textureAtlas.getRegions().get(0).getTexture());
        init();
    }

    protected OneSpriteAnimatedActor(TextureAtlas textureAtlas) {
        super(null);
        this.textureAtlas = textureAtlas;
        sprite = new Sprite(textureAtlas.getRegions().get(0).getTexture());
        init();
    }

    @Override
    public void init() {
        super.init();
        setSize(textureAtlas.getRegions().get(0).getRegionWidth(), textureAtlas.getRegions().get(0).getRegionHeight());
    }

    public float getFps() {
        return fps;
    }

    public void setFps(float fps) {
        this.fps = fps;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (running) {
            animationTime+=delta;
            int actualFrame=((int) (animationTime * fps)) % textureAtlas.getRegions().size;
            if (actualFrame<prevFrame){
                repeated();
                if (!looping) {
                    stop();
                    return;
                }
            }
            setFrame(actualFrame);
            prevFrame = actualFrame;
        }
    }

    protected void repeated(){
    }

    private void setFrame(int frame)
    {
        sprite.setRegion(textureAtlas.getRegions().get(frame % textureAtlas.getRegions().size));
    }

    public void setFramePercent(float percent) {
        setFrame((int)(getFrameCount()*percent));
    }

    private int getFrameCount()
    {
        return textureAtlas.getRegions().size;
    }

    protected void start()
    {
        running = true;
    }

    protected void stop()
    {
        running = false;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        setFrame(((int) (elapsedTime * fps)));
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        setFrame(((int) (elapsedTime * fps)));
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        setFrame(((int) (elapsedTime * fps)));
    }
}
