package view;

import model.Icons;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * The main user profile for the player. Will contain details like character name, etc.
 */
public class UserProfile {


    private int x;
    private int y;
    private final BufferedImage img;
    private int speedKeyX, speedKeyY;



    public UserProfile(int x, int y){
        this.img = Icons.PLAYER_SPRITE;

        this.x = x;
        this.y = y;
    }

    /*
     * move the sprite based on key inputs
     */
    public void move() {

        if(this.getX() >= 0 && this.getX() < 768) {
            this.x += this.speedKeyX;
        } else if (this.getX() < 0) {
            this.x = 0;
        } else if (this.getX() > 768) {
            this.x = 1160;
        }
        if(this.getY() >= 0 && this.getY() < 768) {
            this.y += this.speedKeyY;
        } else if (this.getY() < 0) {
            this.y = 0;
        } else if (this.getY() > 768) {
            this.y = 450;
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
            speedKeyX = -10; //when move is, called change the speed
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("x position: " + this.getX());
            speedKeyX = 10;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            speedKeyY = -10; //when move is, called change the speed
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            speedKeyY = 10;
            System.out.println("y position: " + this.getY());
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
