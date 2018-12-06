package hu.csanysoft.bloodyhell.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {
    public static AssetManager manager;

    private static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";

    private static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();

    static {

        fontParameter.fontFileName = "arial.ttf";

        fontParameter.fontParameters.size = 30;

        fontParameter.fontParameters.characters = CHARS;

        fontParameter.fontParameters.color = Color.WHITE;

    }

    public static final AssetDescriptor<BitmapFont> ARIAL_30_FONT
            = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static final AssetDescriptor<Texture> MOSQUITO = new AssetDescriptor<Texture>("textures/mosquito.png", Texture.class);

    public static final AssetDescriptor<Texture>  BACKGROUND1_TEXTURE
            = new AssetDescriptor<Texture>("textures/background_1.png", Texture.class);

    private static final AssetDescriptor<Texture>  BACKGROUND2_TEXTURE
            = new AssetDescriptor<Texture>("textures/background_2.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> MOSQUITO_TEXTURE
            = new AssetDescriptor<TextureAtlas>("mosquito/mosquito.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> HOLLO_TEXTURE
            = new AssetDescriptor<TextureAtlas>("hollo.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> WALK_TEXTURE
            = new AssetDescriptor<TextureAtlas>("walk.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Texture> FOODBAR_TEXTURE
            = new AssetDescriptor<Texture>("textures/foodbar.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> EXPLOSION_TEXTURE
            = new AssetDescriptor<TextureAtlas>("explosion.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Texture> HP_FEKETE_TEXTURE
            = new AssetDescriptor<Texture>("hp fekete.png", Texture.class);

    public static final AssetDescriptor<Texture> HP_PIROS_TEXTURE
            = new AssetDescriptor<Texture>("hp piros.png", Texture.class);

    public static final AssetDescriptor<Texture> HP_KEK_TEXTURE
            = new AssetDescriptor<Texture>("hp kek.png", Texture.class);

    private static final AssetDescriptor<Texture> START
            = new AssetDescriptor<Texture>("ui/start.png", Texture.class);

    private static final AssetDescriptor<Texture> START_DOWN
            = new AssetDescriptor<Texture>("ui/start_down.png", Texture.class);

    private static final AssetDescriptor<Texture> EXIT
            = new AssetDescriptor<Texture>("ui/exit.png", Texture.class);

    private static final AssetDescriptor<Texture> EXIT_DOWN
            = new AssetDescriptor<Texture>("ui/exit_down.png", Texture.class);

    private static final AssetDescriptor<Texture> TUTORIAL
            = new AssetDescriptor<Texture>("ui/tutorial.png", Texture.class);

    private static final AssetDescriptor<Texture> TUTORIAL_DOWN
            = new AssetDescriptor<Texture>("ui/tutorial_down.png", Texture.class);

    public static final AssetDescriptor<Texture> SPIRAL
            = new AssetDescriptor<Texture>("ui/spiral.png", Texture.class);

    public static final AssetDescriptor<Texture> CAR1_TEXTURE
            = new AssetDescriptor<Texture>("car1.png", Texture.class);

    public static final AssetDescriptor<Texture> BLOOD_TEXTURE
            = new AssetDescriptor<Texture>("blood.png", Texture.class);

    public static final AssetDescriptor<Texture> LOGO
            = new AssetDescriptor<Texture>("ui/bloodyhell.png", Texture.class);

    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }


    public static void load() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

        manager.load(ARIAL_30_FONT);
        manager.load(MOSQUITO_TEXTURE);
        manager.load(WALK_TEXTURE);
        manager.load(FOODBAR_TEXTURE);
        manager.load(HOLLO_TEXTURE);
        manager.load(EXPLOSION_TEXTURE);
        manager.load(BACKGROUND1_TEXTURE);
        manager.load(BACKGROUND2_TEXTURE);
        manager.load(HP_FEKETE_TEXTURE);
        manager.load(HP_PIROS_TEXTURE);
        manager.load(HP_KEK_TEXTURE);
        manager.load(START);
        manager.load(START_DOWN);
        manager.load(EXIT);
        manager.load(EXIT_DOWN);
        manager.load(TUTORIAL);
        manager.load(TUTORIAL_DOWN);
        manager.load(SPIRAL);
        manager.load(CAR1_TEXTURE);
        manager.load(BLOOD_TEXTURE);
        manager.load(LOGO);
    }

    @SuppressWarnings("EmptyMethod")
    public static void afterLoaded() {
        //Assets.manager.get(Assets.MAIN_MUSIC).setLooping(true);
        //Assets.manager.get(Assets.MAIN_MUSIC).play();
    }
    public static void unload() {
        manager.dispose();
    }
}
