package GameServer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JGUTIERRGARC
 */

import Utils.Connection;
import java.net.*;
import java.io.*;

/**
 *
 * @author LPENAF
 */
public class TCPServer {
    public static final int PORT = 7896;
    
    public static void main (String args[]) {
	try{
            int serverPort = PORT; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                System.out.println("Waiting for messages..."); 

                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                Connection c = new Connection(clientSocket);
                c.start();
            }
	} catch(IOException e) {
            System.out.println("Listen :"+ e.getMessage());
        }
    }
}


