/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.client.ClientProperties_Old;
import universaldistributedsystem.client.ClientProperties;
import universaldistributedsystem.common.Property;

/**
 *
 * @author Nemanja
 */
public class CllientPropertiesSave {
    public static void main(String args[]) throws IOException{
        ClientProperties cp = new ClientProperties();
        cp.host = "123.465.798";
        cp.user.username = "danijela";
        
        try {
            FileOutputStream fos = new FileOutputStream("CllientPropertiesSave.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cp);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CllientPropertiesSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex){
            Logger.getLogger(CllientPropertiesSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
