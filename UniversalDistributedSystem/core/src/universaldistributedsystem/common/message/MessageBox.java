/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

/**
 *
 * @author Nemanja
 */
public interface MessageBox {
    /**
     * Stavlja poruku u sanduce kao poslednju. Ako je sanduce puno onda
     * se blokira.
     * @param message Poruka.
     */
    public void put(Message message);
    /**
     * Dobavlja sledecu poruku iz sancuceta i brise je. Ako je sanduce prazno
     * blokira se.
     * @return Vraca poruku iz sanduceta.
     */
    public Message take();
    /**
     * Broj poruka u sanducetu.
     * @return
     */
    public int size();
}
