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
 * @author JGUTIERRGARC
 */
public class MulticastSenderPeer {
    public static void main(String args[]){ 
        MulticastSocket s = null;
        WhacMole wm = new WhacMole();
        
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
            s = new MulticastSocket(6789);
            s.joinGroup(group); 
            //s.setTimeToLive(10);
            
            System.out.println("Messages' TTL (Time-To-Live): " + s.getTimeToLive());
            
            byte [] m = Utils.Serializing.serialize(wm);
            
            DatagramPacket messageOut = 
                    new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);

            s.leaveGroup(group);
        } catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } finally {
            if(s != null)
                s.close();
        }
    }
}
