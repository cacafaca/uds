/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

/**
 *
 * @author Nemanja
 */
public interface IProperties {
    public void add(Property property);
    public Property get(String propertyName);
    public Property get(int index);
    public void delete(String propertyName);
    public int count();
}
