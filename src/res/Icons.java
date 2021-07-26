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
    public BufferedImage DOOR_CLOSED = ImageIO.read(new File("src/res/assets/black_square.png"));
    public BufferedImage DOOR_PLACEHOLDER = ImageIO.read(new File("src/res/assets/door_north_open.png"));
    public BufferedImage WHITE_BOARD = ImageIO.read(new File("src/res/assets/top_wall_whiteboard.png"));
    public BufferedImage WARHOL = ImageIO.read(new File("src/res/assets/top_wall_warhol.png"));
    public BufferedImage VENDING_MACHINE = ImageIO.read(new File("src/res/assets/top_wall_vending_machine.png"));
    public BufferedImage RED_ZONE = ImageIO.read(new File("src/res/assets/red_zone.png"));
    public BufferedImage TOP_WALL = ImageIO.read(new File("src/res/assets/top_wall.png"));
    public BufferedImage DESK_1 = ImageIO.read(new File("src/res/assets/desk_1.png"));
    public BufferedImage DESK_2 = ImageIO.read(new File("src/res/assets/desk_2.png"));
    public BufferedImage DESK_3 = ImageIO.read(new File("src/res/assets/desk_3.png"));
    public BufferedImage DEAD_CHAIR = ImageIO.read(new File("src/res/assets/chair/dead_chair.png"));
    public BufferedImage PLANT_CHAIR = ImageIO.read(new File("src/res/assets/plant_chair.png"));


    public Icons() throws IOException {

    }


}
