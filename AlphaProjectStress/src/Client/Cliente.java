/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.ConnectionData;
import Stress.Clients;
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
            
            long tot = 0;
            long tf = 0;
            long ti = System.currentTimeMillis();
            ConnectionData cd = tcpClient.enviaRegistro(name);
            tf = System.currentTimeMillis();
            tot = tf-ti;
            //System.out.println();
            String ruta = "C:\\Users\\fabia\\Desktop\\SD\\proyecto-22032020\\Alpha-Project\\AlphaProjectStress\\src\\Stress\\resultados_registro2.txt";
            Clients.concatInFile(ruta, "J"+this.id+":"+tot+"\n");
            /*
            MulticastClient mc = new MulticastClient();
            Player p = new Player(name, id);
            p.setMessage("");
            for (int i = 0; i < this.requests; i++) {
                Object obj = mc.receiveUDP();
                p.setMessage("Request no: " + i + " from: " + p.getNAME());
                tcpClient.enviaJugador(p, i + 1);
            }*/
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO Auto-generated catch block
        
    }
}
