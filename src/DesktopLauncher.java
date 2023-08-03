import Screens.MainScreen;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Emanuel Boateng on 15/02/2017.
 */
public class DesktopLauncher {

    public static void main(String args[]) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        Main.start(new MainScreen());
    }
}
