package Controllers;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Created by Emanuel Boateng on 21/11/2016.
 */
public class DisplayController {
    public final int WIDTH;
    public final int HEIGHT;

    public DisplayController(int WIDTH,int HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void create() {
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.sync(30);
            Display.create();

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

        } catch (Exception e) {
            //if fails
        }
    }

    public void update(){
        Display.update();

    }

    public void distroy(){
        Display.destroy();
    }
}
