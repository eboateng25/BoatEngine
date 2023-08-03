package Screens;

import Controllers.DisplayController;
import Shaders.ShaderProgram;
import Shapes.Square;
import Textures.Image;
import Textures.ImageShaderProgram;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by Emanuel Boateng on 25/11/2016.
 */
public class MainScreen implements Screen {

    Image logo;

    ImageShaderProgram shader;

    Square square;

    @Override
    public void onStart() {
        shader = new ImageShaderProgram();
        shader.loadShaders("src/Screens/Grayscale.vert","src/Screens/Grayscale.frag");
        logo = new Image(500,0,"logo.png");
        square = new Square(250,250,500,500);
    }

    @Override
    public void render() {
        GL11.glClearColor(0,0,0,1);
        square.setColor(0.5f,0.5f,0.5f);
        square.render();
        logo.scale(1f);
        logo.render();
    }

    @Override
    public void destroy() {

    }
}
