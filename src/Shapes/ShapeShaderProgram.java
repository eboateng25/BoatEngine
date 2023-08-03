package Shapes;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Emanuel Boateng on 22/11/2016.
 */
public class ShapeShaderProgram {

    int shaderProgram = glCreateProgram();
    int vertexShader = glCreateShader(GL_VERTEX_SHADER);
    int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

    float RED,GREEN,BLUE,ALPHA;

    public void loadShaders(String VERTEX,String FRAGMENT){
        glShaderSource(vertexShader,loadVertexShader(VERTEX));
        glCompileShader(vertexShader);
        glShaderSource(fragmentShader,loadFragmentShader(FRAGMENT));
        glCompileShader(fragmentShader);
        if(glGetShader(vertexShader,GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Vertex shader not compiled\n\n"+glGetShaderInfoLog(vertexShader,GL_INFO_LOG_LENGTH));
            System.exit(0);
        }
        if(glGetShader(fragmentShader,GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Fragment shader not compiled\n\n"+glGetShaderInfoLog(fragmentShader,GL_INFO_LOG_LENGTH));
            System.exit(0);
        }
        glAttachShader(shaderProgram,vertexShader);
        glAttachShader(shaderProgram,fragmentShader);
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);
        setUniform(shaderProgram);
    }

    public void setUniform(int programID){
        //link pID with variable
        int uniform_color = GL20.glGetUniformLocation(programID,"color");

        //assisn value
        GL20.glUniform4f(uniform_color,RED,GREEN,BLUE,ALPHA);
    }

    public void useShaders(){
        glUseProgram(shaderProgram);
        glAttachShader(shaderProgram,vertexShader);
        glAttachShader(shaderProgram,fragmentShader);
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);
        setUniform(shaderProgram);
    }

    public void destroy(){
        glDeleteProgram(shaderProgram);
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

    }

    public StringBuilder loadVertexShader(String filename){
        StringBuilder vertexShaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                vertexShaderSource.append(line).append("\n");
            }
        }catch (Exception e){
            System.err.println("VERTEX FAILED\n"+e);
            Display.destroy();
            System.exit(1);
        }

        return vertexShaderSource;
    }
    public StringBuilder loadFragmentShader(String filename){
        StringBuilder fragmentShaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                fragmentShaderSource.append(line).append("\n");
            }
        }catch (Exception e){
            System.err.println("FRAGMENT FAILED");
            Display.destroy();
            System.exit(1);
        }

        return fragmentShaderSource;
    }

    public void setColor(float R, float G, float B, float A){
        this.RED = R;
        this.GREEN = G;
        this.BLUE = B;
        this.ALPHA = A;
    }

}
