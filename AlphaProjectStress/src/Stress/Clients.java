/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stress;

import Client.Cliente;
/**
 *
 * @author Jose Sanchez
 */
public class Clients {

    public static void main(String[] args) {
        int requests = 10;
        int numOfPlayers = 3;

        for (int i = 0; i < numOfPlayers; i++) {
            Cliente c = new Cliente("Client " + (i + 1), requests, (i+1));
            c.start();
        }
    }
}
