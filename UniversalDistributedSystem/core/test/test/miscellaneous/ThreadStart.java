/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.miscellaneous;

/**
 *
 * @author Nemanja
 */
public class ThreadStart implements Runnable{

    private Thread runner;

    public ThreadStart(){
        if(runner == null){
            runner = new Thread();
            runner.start();
        }
    }

    public void run(){
        int max = Integer.MAX_VALUE;
        int i = 0;
        while(i < max){
            System.out.print(i+",");
            i++;
        }
    }

    public static void main(String args[]){
        Thread t = new Thread(null, new ThreadStart(), "nemanjin thread");
        t.start();
    }
}
