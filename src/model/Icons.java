package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Icons {

    public BufferedImage FLOOR_1 = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_2.png"));
    public BufferedImage FLOOR_2 = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_47.png"));
    public BufferedImage BOTTOM_WALL = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59.png"));
    public BufferedImage TOP_WALL = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59topwall.png"));
    public BufferedImage BOTTOM_LEFT_CORNER = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59.png"));
    public BufferedImage TOP_LEFT_CORNER = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59topwall.png"));
    public BufferedImage BOTTOM_RIGHT_CORNER = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59.png"));
    public BufferedImage TOP_RIGHT_CORNER = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59topwall.png"));
    public BufferedImage LEFT_WALL = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59leftwall.png"));
    public BufferedImage RIGHT_WALL = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_59rightwall.png"));
    public BufferedImage COMPUTER_DESK = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_289.png"));
    public BufferedImage COMPUTER_DESK_LEGS = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_289.png"));
    public BufferedImage PLANT = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_99.png"));
    public BufferedImage PRINTER = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/32x32/Modern_Office_Singles_32x32_178.png"));

    public static BufferedImage PLAYER_SPRITE;

    static {
        try {
            PLAYER_SPRITE = ImageIO.read(new File("src/res/icons/4_Modern_Office_singles/48x48/Modern_Office_Singles_48x48_111.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Icons() throws IOException {

    }


}
