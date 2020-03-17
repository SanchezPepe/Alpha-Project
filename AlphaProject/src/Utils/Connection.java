/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import GameServer.ConnectionData;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LPENAF
 */
public class Connection extends Thread {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket clientSocket;
    
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch(IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run(){
        try {
            Object data = in.readObject();
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
            
            if(data.equals("REGISTER")){
                out.writeObject(new ConnectionData("-1'", 1));
            } else {
                out.writeObject("Otro servicio acá");
            }
        } catch(EOFException e) {
            System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
            System.out.println("IO:"+e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }
}