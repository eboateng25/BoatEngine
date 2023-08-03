package Textures;

import Shaders.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by Emanuel Boateng on 24/11/2016.
 */
public class Image {

    float X;
    float Y;
    float ALPHA;
    float SCALEX;
    float SCALEY;
    Texture texture;
    float FAKE_WIDTH;
    float FAKE_HEIGHT;
    ImageShaderProgram shader = new ImageShaderProgram();

    public Image(float X,float Y,String PNGIMAGE){
        this.X = X;
        this.Y = Y;
        this.ALPHA = 1;
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(PNGIMAGE));
        }catch(IOException e){
            System.err.println("IMAGE COULD NOT BE LOADED. IS THE LOCATION CORRECT?");
            System.exit(1);
        }
        shader.loadShaders("src/Textures/ImageShader.vert","src/Textures/ImageShader.frag");
        SCALEX = 1;
        SCALEY = 1;
        if(texture.getImageWidth() > texture.getImageHeight()){
            FAKE_WIDTH = largestPowerOf2(texture.getTextureWidth());
            FAKE_HEIGHT = largestPowerOf2(texture.getTextureWidth());
        }
        if(texture.getImageHeight() > texture.getImageWidth()){
            FAKE_WIDTH = largestPowerOf2(texture.getImageHeight());
            FAKE_HEIGHT = largestPowerOf2(texture.getImageHeight());
        }
        if(texture.getImageHeight() == texture.getImageWidth()){
            FAKE_WIDTH = largestPowerOf2(texture.getImageWidth());
            FAKE_HEIGHT = largestPowerOf2(texture.getImageHeight());
        }
        System.out.println(FAKE_WIDTH);
        System.out.println(FAKE_HEIGHT);
    }

    public void render(){
        shader.useShaders();
        shader.setAlpha(ALPHA);
        texture.bind();
        GL11.glBegin(GL11.GL_QUADS);
        /*
            glTexCoord2f(0, 1); // bottom left
            GL11.glVertex2f(X * SCALEX,Y * SCALEY);
            glTexCoord2f(1, 1); // bottom right
            GL11.glVertex2f((X+texture.getTextureWidth())*SCALEX,Y * SCALEY);
            glTexCoord2f(1, 0); // top right
            GL11.glVertex2f((X+texture.getTextureWidth()) * SCALEX,(Y+texture.getTextureHeight()) * SCALEY);
            glTexCoord2f(0, 0); // top left
            GL11.glVertex2f((X) * SCALEX,(Y+texture.getTextureHeight()) * SCALEY);
        */
            glTexCoord2f(0, (float)texture.getImageWidth()/texture.getTextureWidth()); // bottom left
            GL11.glVertex2f(X * SCALEX,Y * SCALEY);
            glTexCoord2f((float)texture.getImageHeight()/texture.getTextureHeight(), (float)texture.getImageWidth()/texture.getTextureWidth()); // bottom right
            GL11.glVertex2f((X+texture.getImageWidth())*SCALEX,Y * SCALEY);
            glTexCoord2f((float)texture.getImageHeight()/texture.getTextureHeight(), 0); // top right
            GL11.glVertex2f((X+texture.getImageWidth()) * SCALEX,(Y+texture.getImageHeight()) * SCALEY);
            glTexCoord2f(0, 0); // top left
            GL11.glVertex2f((X) * SCALEX,(Y+texture.getImageHeight()) * SCALEY);
        GL11.glEnd();
        glUseProgram(0);
    }

    public int largestPowerOf2 (int n)
    {
        int res = 2;
        while (res < n) {
            res *= 2;
        }
        return res;
    }

    public void scale(float SCALE){SCALEX = SCALE;SCALEY = SCALE;}

    public void velX(float velX){
        this.X += velX;
    }
    public void velY(float velY){
        this.Y += velY;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getAlpha() {
        return ALPHA;
    }

    public void setAlpha(float ALPHA) {
        this.ALPHA = ALPHA;
    }

    public float getWidth(){
        return texture.getImageWidth();
    }

    public float getHeight(){
        return texture.getImageHeight();
    }

    public void scaleX(float SCALEX) {
        this.SCALEX = SCALEX;
    }

    public void scaleY(float SCALEY) {
        this.SCALEY = SCALEY;
    }
}

