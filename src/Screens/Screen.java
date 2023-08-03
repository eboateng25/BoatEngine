package Screens;

import Controllers.DisplayController;
import com.sun.tools.javah.Main;

/**
 * Created by Emanuel Boateng on 25/11/2016.
 */
public interface Screen {

    public void onStart();
    public void render();
    public void destroy();

}
