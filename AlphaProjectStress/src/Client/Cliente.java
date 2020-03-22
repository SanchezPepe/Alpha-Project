/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.ConnectionData;
import Server.Solicitud;
import Server.WhacMole;
import Client.TCPClient;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabia
 */
public class Cliente extends Thread {

    String name;
    int requests;

    public Cliente(String n, int requests){
        this.name = n;
        this.requests = requests;
    }

    public void run() {
        try {
            TCPClient tcpClient = new TCPClient();
            ConnectionData cd = tcpClient.enviaRegistro(name);

            System.out.println(cd.toString());

            if (cd != null) { 
                Player yo = tcpClient.solicitaRegistroJugador(name);
                System.out.println(yo.toString());

                MulticastClient mc = new MulticastClient();
                tcpClient.enviaJugador(yo);
                
                System.out.println(this.name + " sends " + requests);
                for(int i = 0; i < this.requests; i++){

                }

                while (true) {
                    Object obj = mc.receiveUDP();
                    System.out.println("Objeto recibido: " + obj.toString());
                    if (obj instanceof WhacMole) {
                        WhacMole wm = (WhacMole) obj;
                        System.out.println("Recibí tablero del server: " + wm.getBoard());
                    } else {
                        if (obj instanceof String) {
                            System.out.println("Recibiendo en el cliente");
                        } else if (obj instanceof Solicitud) {
                            int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
                        }
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
