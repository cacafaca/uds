/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import java.io.Serializable;

/**
 * Class containing nessecary fields for property like, name of the property,
 * value and description
 * @author Nemanja
 */
public class Property implements Serializable{
    public String name;
    public Object value;
    public String description;

    // Three param constructor
    public Property(String name, Object value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    // Two param constructor
    public Property(String name, Object value) {
        this.name = name;
        this.value = value;
        this.description = null;
    }

    // Three param setter
    public void set(String name, Object value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    // Two param setter
    public void set(String name, Object value) {
        this.name = name;
        this.value = value;
        this.description = null;
    }

    @Override
    public String toString(){
        return String.format("name:%s;value:%s;description:%s", name, value.toString(), description);
    }
}