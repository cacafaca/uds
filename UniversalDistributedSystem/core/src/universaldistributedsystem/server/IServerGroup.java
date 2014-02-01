/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.server;

/**
 *
 * @author Nemanja
 */
public interface IServerGroup {
    public void start() throws Exception;
    public void stop();
    public boolean isRunning();
}
