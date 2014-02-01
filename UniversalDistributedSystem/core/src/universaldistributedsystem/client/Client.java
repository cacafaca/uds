/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.client;

import universaldistributedsystem.common.SystemClient;
import universaldistributedsystem.common.logging.Log;

/**
 * Basic class for client.
 * @author Nemanja
 */
public class Client extends SystemClient{

    public  Client(Log log, ClientType clientType){
        super(log, clientType);
    }

}
