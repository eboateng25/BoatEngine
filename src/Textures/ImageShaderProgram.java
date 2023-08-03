package Textures;

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
public class ImageShaderProgram {

    int shaderProgram = glCreateProgram();
    int vertexShader = glCreateShader(GL_VERTEX_SHADER);
    int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

    float alpha;

    public void loadShaders(String VERTEX,String FRAGMENT){
        glShaderSource(vertexShader,loadVertexShader(VERTEX));
        glCompileShader(vertexShader);
        glShaderSource(fragmentShader,loadFragmentShader(FRAGMENT));
        glCompileShader(fragmentShader);
        if(glGetShader(vertexShader,GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Vertex shader not compiled\n\n"+glGetShaderInfoLog(vertexShader,GL_INFO_LOG_LENGTH));
            System.exit(0);
        }
        if(glGetShader(fragmentShader,GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment shader not compiled\n\n" + glGetShaderInfoLog(fragmentShader, GL_INFO_LOG_LENGTH));
            System.exit(0);
        }
        glAttachShader(shaderProgram,vertexShader);
        glAttachShader(shaderProgram,fragmentShader);
        glLinkProgram(shaderProgram);
        setUniforms(shaderProgram);
        glValidateProgram(shaderProgram);



        GL13.glActiveTexture(GL13.GL_TEXTURE0);
    }

    public void setUniforms(int programID){
        //link pID with variable
        int uniform_texture1 = GL20.glGetUniformLocation(programID,"texture1");
        int uniform_alpha = GL20.glGetUniformLocation(programID,"in_alpha");

        //assisn value
        GL20.glUniform1i(uniform_texture1, 0);
        GL20.glUniform1f(uniform_alpha, alpha);

    }

    public void useShaders(){
        glUseProgram(shaderProgram);
        glAttachShader(shaderProgram,vertexShader);
        glAttachShader(shaderProgram,fragmentShader);
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);

        setUniforms(shaderProgram);
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

    public void setAlpha(float alpha){
        this.alpha = alpha;
    }

}
