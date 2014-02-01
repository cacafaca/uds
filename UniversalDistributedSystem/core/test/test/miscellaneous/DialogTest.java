/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.miscellaneous;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nemanja
 */
public class DialogTest extends JFrame{
    public static void main(String args[]){
        DialogTest d = new DialogTest();
        int n = JOptionPane.showConfirmDialog(
            d,
            "Would you like green eggs and ham?",
            "An Inane Question",
            JOptionPane.YES_NO_OPTION);
    }
}
