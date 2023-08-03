package Shapes;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by Emanuel Boateng on 21/11/2016.
 */
public class Square {

    ShapeShaderProgram shader = new ShapeShaderProgram();

    float X, Y, WIDTH, HEIGHT;
    float R,G,B,A;

    public Square(float X, float Y, float WIDTH, float HEIGHT){
        this.X = X;
        this.Y = Y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        R = 0;
        G = 0;
        B = 0;
        A = 1;
        shader.loadShaders("src/Shapes/ShapeShader.vert","src/Shapes/ShapeShader.frag");
    }

    public void setColor(float R,float G,float B){
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public void setColor(float R,float G,float B, float A){
        this.R = R;
        this.G = G;
        this.B = B;
        this.A = A;
    }

    public void setAlpha(float A){
        this.A = A;
    }

    public void render(){
        shader.setColor(R,G,B,A);
        shader.useShaders();
        GL11.glColor4f(R,G,B,A);

        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(X,Y);
            GL11.glVertex2f(X+WIDTH,Y);
            GL11.glVertex2f(X+WIDTH,Y+HEIGHT);
            GL11.glVertex2f(X,Y+HEIGHT);
        GL11.glEnd();
        glUseProgram(0);
    }

    public void velX(float velX){
        this.X += velX;
    }
    public void velY(float velY){
        this.Y += velY;
    }

    public void setY(float y) {
        Y = y;
    }

    public void setX(float x) {
        X = x;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public float getAlpha() {
        return A;
    }
}
