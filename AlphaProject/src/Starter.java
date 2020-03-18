
import Cliente.Cliente;
import GameServer.MulticastServer;
import TCP.TCPServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jose Sanchez
 */
public class Starter {
    /**
     * 
    server
            servidor multicast
                    cliente
         */
    public static void main(String[] args){
        TCPServer server = new TCPServer();
        server.start();
        
        MulticastServer ms = new MulticastServer();
        ms.start();
        
        int numOfPlayers = 3;
        for(int i = 0; i < numOfPlayers; i++){
            Cliente c = new Cliente();
            c.start();
        }
        
    }
    
}
