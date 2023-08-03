import Controllers.Camera;
import Controllers.DisplayController;
import Screens.MainScreen;
import Screens.MainScreen;
import Screens.Screen;
import Shaders.ShaderProgram;
import Shapes.Square;
import Textures.Image;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by Emanuel Boateng on 21/11/2016.
 */
public class Main {

    public static void start(Screen screen) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //variables
        DisplayController display = new DisplayController(1280,720);
        display.create();
        Camera camera = new Camera(0,0,display.WIDTH,display.HEIGHT);

        MainScreen mainScreen = new MainScreen();

        screen.onStart();


        while(!Display.isCloseRequested()){
            //loops
            display.update();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glEnable (GL_BLEND);
            GL11.glEnable(GL_TEXTURE_2D);
            GL11.glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            GL11.glClearColor(1,1,1,1);

            screen.render();
        }

        screen.destroy();
        display.distroy();
    }
}
