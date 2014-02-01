/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

/**
 *
 * @author Nemanja
 */
public interface IProperty {
    public void setName(String name);
    public String getName();

    public void setValue(Object obj);
    public Object getValue();
}
