/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.logging;

import java.util.EventListener;

/**
 *
 * @author Nemanja
 */
public interface LogListener extends EventListener{
    public void Logged(LogEvent event);
    public void InfoLogged(LogEvent event);
    public void WarnningLogged(LogEvent event);
    public void ErrorLogged(LogEvent event);
}
