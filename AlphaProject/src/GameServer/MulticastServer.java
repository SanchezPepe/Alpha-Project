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
import java.util.Date;

/**
 *
 * @author LPENAF
 */
public class MulticastServer {
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    
    public void sendUDP(Object obj){
        System.out.println("Objeto a mandar "+obj.toString());
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
    
    public static void main(String[] args) throws InterruptedException {
        MulticastServer ms = new MulticastServer();
        WhacMole wm = new WhacMole();
        String score = (new Date()).toString();
        while(true){
            wm.changeBoard();
            ms.sendUDP(wm);
            Thread.sleep(200);
            ms.sendUDP(score);
            Thread.sleep(300);
            score = (new Date()).toString();
        }
    }
}
