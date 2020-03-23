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
<<<<<<< HEAD
            /*
=======
            
            
>>>>>>> b615a37261e610cf2fc785b5b7751600cabf83f0
            MulticastClient mc = new MulticastClient();
            Player p = new Player(name, id);
            p.setMessage("");
            
            // Estresamiento con requests
            long sum = 0;
            long sum2 = 0;
            long t1, t2;
            for (int i = 0; i < this.requests; i++) {
                Object obj = mc.receiveUDP();
                p.setMessage("Request no: " + i + " from: " + p.getNAME());
                t1 = System.currentTimeMillis();
                tcpClient.enviaJugador(p, i + 1);
<<<<<<< HEAD
            }*/
            
        }catch (ClassNotFoundException ex) {
=======
                t2 = System.currentTimeMillis();
                sum += t2;
                sum2 += t2 * t2;
            }
            
            double avg = ((double) sum) / requests;
            double std = Math.sqrt(((double) sum2)/ (requests - avg * avg));
            System.out.println(Integer.toString(requests) + '\t' + Double.toString(avg) + '\t' + Double.toString(std));
            
        } catch (ClassNotFoundException ex) {
>>>>>>> b615a37261e610cf2fc785b5b7751600cabf83f0
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO Auto-generated catch block
        
    }
}
