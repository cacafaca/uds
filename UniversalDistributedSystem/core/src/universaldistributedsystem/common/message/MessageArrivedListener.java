/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

import java.util.EventListener;

/**
 * The listener interface for receiving message events.
 *
 * @author Nemanja
 */
public interface MessageArrivedListener extends EventListener{
    public void messageArrived(MessageArrivedEvent event);
}
