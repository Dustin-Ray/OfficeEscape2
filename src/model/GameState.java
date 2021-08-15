package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameState {


    public GameState() {

    }


    public void save(String savePath, Object o) {
        try {
            FileOutputStream fos = new FileOutputStream(savePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Object load(String loadPath) {
        Object o = null;
        try {
            FileInputStream fis = new FileInputStream(loadPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            o = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
