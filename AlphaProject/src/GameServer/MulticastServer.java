/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author LPENAF
 */
public class MulticastServer {
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    
    public void sendUDP(Object obj){
        MulticastSocket socket = null;
        
        try {
            InetAddress group = InetAddress.getByName(this.IP); // destination multicast group 
            socket = new MulticastSocket(this.PORT);
            socket.joinGroup(group);
            //s.setTimeToLive(10);
            
            System.out.println("Messages' TTL (Time-To-Live): " + socket.getTimeToLive());
            
            byte [] m = Utils.Serializing.serialize(obj);
            
            DatagramPacket messageOut = 
                    new DatagramPacket(m, m.length, group, this.PORT);
            socket.send(messageOut);
            
            socket.leaveGroup(group);
        } catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
            System.out.println(e.toString());
        } catch (IOException e){
            System.out.println("IO: " + e.getMessage());
            System.out.println(e.toString());
        } finally {
            if(socket != null){
                socket.close();
            }
        }
    }
    
    public static void main(String[] args) {
        MulticastServer ms = new MulticastServer();
        ms.sendUDP("Hola!");
    }
}