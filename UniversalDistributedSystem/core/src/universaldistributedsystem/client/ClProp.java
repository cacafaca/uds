/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.client;

import universaldistributedsystem.common.IProperty;

/**
 * oj
 * @author Nemanja
 */
public class ClProp implements IProperty{

    private String name;
    private Object obj;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(Object obj) {
        this.obj = obj;
    }

    public Object getValue() {
        return obj;
    }

}
