/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.client;

import java.io.Serializable;
import universaldistributedsystem.common.Property;

/**
 * Pokušao sam da generalizujem problem, ali je to nepotrebna komplikacija.
 * 
 * @author Nemanja
 */
public class ClientProperties_Old implements Serializable{
    public Property host;
    public Property port;
    public Property user;

    public ClientProperties_Old(){
        host = new Property("Host address", "localhost");
        port = new Property("Server port", 1001);
        Property[] userProperty = new Property[3];
        userProperty[0] = new Property("Username", "cacafaca");
        userProperty[1] = new Property("Password", "cacafaca");
        userProperty[2] = new Property("Email", "cacafaca@yahoo.com");
        user = new Property("User", userProperty);
    }

    @Override
    public String toString(){
        return super.toString() + String.format("(host:%s;port:%d)", host, port);
        //return String.format("(host:%s;port:%d", host, port);
    }
}
