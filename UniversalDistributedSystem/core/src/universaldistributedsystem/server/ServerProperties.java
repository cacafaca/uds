/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.server;

import universaldistributedsystem.common.Properties;
import universaldistributedsystem.common.Property;

/**
 * Server propertis class. Used to store server porperties for
 * saving in XML with serialization.
 *
 * @author Nemanja
 */
public class ServerProperties extends Properties{

    public ServerProperties(){
        add(new Property("clients_port", 4000, "Listening port for client connections."));
        add(new Property("workstatinons_port", 4001, "Listening port for workstation connections."));
    }

    public void assign(Properties properties){
        getList().clear();
        for(Property p : properties.getList()){
            add(p);
        }
    }
}
