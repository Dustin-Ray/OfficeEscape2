package view;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserProfile {


    private int x;
    private int y;
    private BufferedImage img;
    private int speedKeyX, speedKeyY;
    private int myXDim;
    private int myYDim;

    public UserProfile(){ }

    public UserProfile(String fileLoc, int x, int y, int theXDim, int theYDim){
        try{
            this.img = ImageIO.read(new File(fileLoc));
        } catch (IOException e){
            System.out.println("Can't load file!");
        }

        myXDim = theXDim;
        myYDim = theYDim;
        this.x = x;
        this.y = y;
    }

    /*
     * move the sprite based on key inputs
     */
    public void move() {
        this.x += this.speedKeyX;
        this.y += this.speedKeyY;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
            speedKeyX = -10; //when move is, called change the speed
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && this.getX() < 1200) {
            System.out.println(this.getX());
            speedKeyX = 10;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            speedKeyY = -10; //when move is, called change the speed
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            speedKeyY = 10;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            speedKeyX = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            speedKeyX = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            speedKeyY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            speedKeyY = 0;
        }




    }

    //GETTERS
    public BufferedImage getImage(){ return this.img; }
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    //SETTERS
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }



}
