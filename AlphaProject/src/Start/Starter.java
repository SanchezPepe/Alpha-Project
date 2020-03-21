package Start;

import Client.Cliente;
import Server.MulticastServer;
import Server.TCPServer;

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
    
    public static void main(String[] args){
        TCPServer server = new TCPServer();
        server.start();
        
        MulticastServer ms = new MulticastServer();
        ms.start();
        
        int numOfPlayers = 4;
        for(int i = 0; i < numOfPlayers; i++){
            Cliente c = new Cliente();
            c.start();
        }
        
    }
    
}
