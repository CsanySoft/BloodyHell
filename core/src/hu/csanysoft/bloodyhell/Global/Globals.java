package hu.csanysoft.bloodyhell.Global;

import java.util.Random;

public class Globals {

    public static final Random rand = new Random();

    public static final int WORLD_WIDTH = 1280;
    public static final int WORLD_HEIGHT = 720;

    public static final boolean DEBUG_ALL = true;

    public static int random(int a, int b){return (int)(Math.random()*(b-a+1)+a);}
}
