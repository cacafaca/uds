/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import universaldistributedsystem.client.ClientGUI;
import universaldistributedsystem.server.ServerGUI;

/**
 * Klasa za testiranje. Startuje klijenta i server.
 * @author Nemanja
 */
public class StartServerAndClient {
    public static void main (String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
                new ClientGUI().setVisible(true);
            }
        });
        
    }
}
