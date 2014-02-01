/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.miscellaneous;

import java.io.File;

/**
 *
 * @author Nemanja
 */
public class MakeDir {
    public static void main(String args[]){
        File f = new File("c:\\temp\\nemanja\\daca\\a.txt");
        System.out.println(f.getPath());
        f.mkdirs();
    }
}
