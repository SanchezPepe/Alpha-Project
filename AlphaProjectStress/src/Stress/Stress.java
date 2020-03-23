/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stress;

import Client.Cliente;
import Server.MulticastServer;
import Server.TCPServer;

/**
 *
 * @author Jose Sanchez
 */
public class Stress {

    public static void main(String[] args) {
        int requests = 2;
        int numOfPlayers = 5;

        TCPServer server = new TCPServer();
        server.start();

        MulticastServer ms = new MulticastServer();
        ms.start();

        for (int i = 0; i < numOfPlayers; i++) {
            Cliente c = new Cliente("Cliente núm: " + i, requests);
            c.start();
        }
    }

}
