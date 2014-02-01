/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.Properties;
import universaldistributedsystem.common.Property;

/**
 *
 * @author Nemanja
 */
public class PropertiesSaveTest {
    public static void main(String args[]){
        Properties p = new Properties();
        p.add(new Property("Proeprty int", 22, "This property is integer"));
        p.add(new Property("Proeprty string", "bla bla", "This property is string"));
        p.add(new Property("Proeprty double", 3.14, "This property is double"));

        String fileName = "c:/temp/properties.obj";
        p.write(fileName);       
    }
}
