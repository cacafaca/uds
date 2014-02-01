/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.client;

import java.io.Serializable;

/**
 *
 * @author Nemanja
 */
public class ClientProperties implements Serializable{
    public String host;
    public int port;

    public class User implements Serializable{
        public String username;
        public String password;
        public String email;
        public User(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }
    }
    public User user;

    public ClientProperties(){
        host = "localhost";
        port = 1001;
        user = new User("cacafaca", "pass", "cacafaca@zahoo.com");
    }

    @Override
    public String toString(){
        return super.toString() + String.format("(host:%s;port:%d)", host, port);
        //return String.format("(host:%s;port:%d", host, port);
    }
}
