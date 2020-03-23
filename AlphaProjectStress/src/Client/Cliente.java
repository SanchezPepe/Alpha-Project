/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.ConnectionData;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabia
 */
public class Cliente extends Thread {

    String name;
    int requests;
    int id;

    public Cliente(String n, int requests, int id) {
        this.name = n;
        this.requests = requests;
        this.id = id;
    }

    public void run() {
        try {
            TCPClient tcpClient = new TCPClient();
            ConnectionData cd = tcpClient.enviaRegistro(name);

            MulticastClient mc = new MulticastClient();
            Player p = new Player(name, id);
            p.setMessage("");
            for (int i = 0; i < this.requests; i++) {
                Object obj = mc.receiveUDP();
                p.setMessage("Request no: " + i + " from: " + p.getNAME());
                tcpClient.enviaJugador(p, i + 1);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
