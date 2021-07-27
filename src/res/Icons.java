package res;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class to contain all image assets for use in GUI.
 * @author Dustin Ray
 */
public class Icons {

    public BufferedImage FLOOR_1 = ImageIO.read(new File("src/res/assets/floor_gray.png"));
    public BufferedImage RED_ZONE = ImageIO.read(new File("src/res/assets/red_zone.png"));

    public Icons() throws IOException {}

}
