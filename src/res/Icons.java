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
    public BufferedImage DOOR_CLOSED = ImageIO.read(new File("src/res/assets/door_north_closed.png"));
    public BufferedImage DOOR_OPEN = ImageIO.read(new File("src/res/assets/door_north_open.png"));
    public BufferedImage WHITE_BOARD = ImageIO.read(new File("src/res/assets/top_wall_whiteboard.png"));
    public BufferedImage WARHOL = ImageIO.read(new File("src/res/assets/top_wall_warhol.png"));
    public BufferedImage VENDING_MACHINE = ImageIO.read(new File("src/res/assets/top_wall_vending_machine.png"));
    public BufferedImage FLOOR_2 = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_47.png"));
    public BufferedImage BOTTOM_WALL = ImageIO.read(new File("src/res/assets/bottom_wall.png"));
    public BufferedImage TOP_WALL = ImageIO.read(new File("src/res/assets/top_wall.png"));
    public BufferedImage TOP_LEFT_CORNER = ImageIO.read(new File("src/res/assets/top_left_corner.png"));
    public BufferedImage TOP_RIGHT_CORNER = ImageIO.read(new File("src/res/assets/top_right_corner.png"));
    public BufferedImage BOTTOM_LEFT_CORNER = ImageIO.read(new File("src/res/assets/bottom_left_corner.png"));
    public BufferedImage BOTTOM_RIGHT_CORNER = ImageIO.read(new File("src/res/assets/bottom_right_corner.png"));
    public BufferedImage LEFT_WALL = ImageIO.read(new File("src/res/assets/left_wall.png"));
    public BufferedImage RIGHT_WALL = ImageIO.read(new File("src/res/assets/right_wall.png"));
    public BufferedImage DESK_1 = ImageIO.read(new File("src/res/assets/desk_1.png"));
    public BufferedImage DESK_2 = ImageIO.read(new File("src/res/assets/desk_2.png"));
    public BufferedImage DESK_3 = ImageIO.read(new File("src/res/assets/desk_3.png"));
    public BufferedImage DEAD_CHAIR = ImageIO.read(new File("src/res/assets/dead_chair.png"));
    public BufferedImage PLANT_CHAIR = ImageIO.read(new File("src/res/assets/plant_chair.png"));






    public Icons() throws IOException {

    }


}
