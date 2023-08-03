package Controllers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * Created by Emanuel Boateng on 06/10/2017.
 */
public class Camera {
    float X, Y, WIDTH, HEIGHT;

    public Camera(float X, float Y, float WIDTH, float HEIGHT){
        this.X = X;
        this.Y = Y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        updateCamera();
    }

    public void updateCamera() {
        GL11.glOrtho(X, WIDTH, Y, HEIGHT, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    public void setX(float X){
        this.X = X;
        WIDTH = WIDTH + X;
        updateCamera();
    }
    public void setY(float Y){
        this.Y = Y;
        HEIGHT = HEIGHT + Y;
        updateCamera();
    }


}
