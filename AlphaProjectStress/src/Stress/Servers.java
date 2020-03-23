/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stress;

import Server.MulticastServer;
import Server.TCPServer;

/**
 *
 * @author Jose Sanchez
 */
public class Servers {

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.start();

        MulticastServer ms = new MulticastServer();
        ms.start();
    }

}
