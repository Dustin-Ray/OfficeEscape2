package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class to contain all image assets for use in GUI.
 * @author Dustin Ray
 */
public class Icons {

    public BufferedImage FLOOR_1 = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_2.png"));
    public BufferedImage FLOOR_2 = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_47.png"));
    public BufferedImage BOTTOM_WALL = ImageIO.read(new File("src/res/assets/bottom_wall.png"));
    public BufferedImage TOP_WALL = ImageIO.read(new File("src/res/assets/top_wall.png"));
    public BufferedImage TOP_LEFT_CORNER = ImageIO.read(new File("src/res/assets/top_left_corner.png"));
    public BufferedImage TOP_RIGHT_CORNER = ImageIO.read(new File("src/res/assets/top_right_corner.png"));
    public BufferedImage BOTTOM_LEFT_CORNER = ImageIO.read(new File("src/res/assets/bottom_left_corner.png"));
    public BufferedImage BOTTOM_RIGHT_CORNER = ImageIO.read(new File("src/res/assets/bottom_right_corner.png"));
    public BufferedImage LEFT_WALL = ImageIO.read(new File("src/res/assets/left_wall.png"));
    public BufferedImage RIGHT_WALL = ImageIO.read(new File("src/res/assets/right_wall.png"));
    public BufferedImage DESK_FACING_RIGHT = ImageIO.read(new File("src/res/assets/desk_facing_right.png"));






    public Icons() throws IOException {

    }


}
