/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.client.ClientProperties;

/**
 *
 * @author Nemanja
 */
public class CllientPropertiesLoad {

    public static void main(String args[]) throws IOException {
        ClientProperties cp;
        try {
            FileInputStream fis = new FileInputStream("CllientPropertiesSave.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                cp = (ClientProperties) ois.readObject();
                System.out.println(cp.toString());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CllientPropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
            ois.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CllientPropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CllientPropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
