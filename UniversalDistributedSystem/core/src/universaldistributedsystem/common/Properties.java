/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nemanja
 */
public class Properties implements IProperties, Serializable{

    private List<Property> propList;

    public Properties(){
        propList = new ArrayList<Property>();
    }

    public void add(Property property) {
        propList.add(property);
    }

    public Property get(String propertyName) {
        Property foundProp = null;
        for(Property pr : propList)
            if(pr.name.equals(propertyName)){
                foundProp = pr;
                break;
            }
        return foundProp;
    }

    public void delete(String propertyName){
        for(Property pr : propList)
            if(pr.name.equals(propertyName)){
                propList.remove(pr);
                break;
            }
    }

    public int count() {
        return propList.size();
    }

    public List<Property> getList(){
        return propList;
    }

    public Property get(int index){
        Property foundProp = null;
        if(index >= 0 && index < propList.size()){
            int i = 0;
            for(Property pr : propList){
                if(index == i){
                    foundProp = pr;
                    break;
                }
                i++;
            }
        }
        return foundProp;
    }

    @Override
    public String toString(){
        String properties = "";
        for(Property pr : propList)
            properties = properties + pr.toString() + "\n";
        return properties;
    }

    public void write(String fileName){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            oos.writeObject(this);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read(String fileName){
        try{
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            Properties p = (Properties) ois.readObject();
            ois.close();
            this.propList = p.propList;            
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
