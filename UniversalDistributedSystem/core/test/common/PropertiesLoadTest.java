/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import universaldistributedsystem.common.Properties;

/**
 *
 * @author Nemanja
 */
public class PropertiesLoadTest {
    public static void main(String args[]){
        Properties p = new Properties();
        String fileName = "c:/temp/properties.obj";
        p.read(fileName);
        System.out.println(p.toString());
    }
}
