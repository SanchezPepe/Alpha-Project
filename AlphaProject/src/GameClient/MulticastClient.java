/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameClient;

import GUI.Board;
import GameServer.WhacMole;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LPENAF
 */
public class MulticastClient {
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    
    public Object receiveUDP(){
        MulticastSocket socket =null;
        Object received = null;
        
        try {
            InetAddress group = InetAddress.getByName(IP); // destination multicast group 
            socket = new MulticastSocket(PORT);
            socket.joinGroup(group); 

            byte[] buffer = new byte[1000];
            System.out.println("Waiting for messages");
            DatagramPacket messageIn = 
                    new DatagramPacket(buffer, buffer.length);
            socket.receive(messageIn);
            received = Utils.Serializing.deserialize(messageIn.getData());
            System.out.println("Message from: " + messageIn.getAddress());
            
            socket.leaveGroup(group);
        } catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(socket != null)
                socket.close();
        }
        return received;
    }
    
    public static void main(String args[]) throws ClassNotFoundException, IOException{ 
        MulticastClient mc = new MulticastClient();
        Board b = new Board();
        b.setVisible(true);
        while(true){
            Object obj = mc.receiveUDP();
            if(obj instanceof WhacMole){
                WhacMole wm = (WhacMole) obj;
                System.out.println(wm.toString());
                b.updateBoard(wm.getBoard());
            }
        }
        
    }
}