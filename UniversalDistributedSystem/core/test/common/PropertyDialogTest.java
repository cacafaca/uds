/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import universaldistributedsystem.common.Properties;
import universaldistributedsystem.common.Property;
import universaldistributedsystem.common.PropertyDialog;

/**
 *
 * @author Nemanja
 */
public class PropertyDialogTest {
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PropertyDialog dialog = new PropertyDialog(new javax.swing.JFrame(), true);

                Properties p = new Properties();
                p.add(new Property("Proeprty int", 22, "This property is integer"));
                p.add(new Property("Proeprty string", "bla bla", "This property is string"));
                p.add(new Property("Proeprty double", 3.14, "This property is double"));

                Properties p1 = new Properties();
                p1.add(new Property("Property int", 1, "This property is int"));
                p1.add(new Property("Property string", "abc", "This property is string"));
                p.add(new Property("Property object", p1, "This property is object"));
                
                //dialog.setProperties(p);
                Properties pout = dialog.loadAndShow(p);

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

                System.out.println("Old properties:\n" + p.toString());
                if(dialog.isChanged())
                    System.out.println("New properties:\n" + pout.toString());
                System.exit(0);
            }
        });
    }
}
