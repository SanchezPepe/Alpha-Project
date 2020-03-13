/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameServer;

import GameClient.Player;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author JGUTIERRGARC
 */
public class MulticastSenderPeer {
    private WhacMole game;
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    private final ConnectionData connect;

    public MulticastSenderPeer() {
        this.game = new WhacMole();
        this.connect = new ConnectionData(this.IP, this.PORT);
    }
    
    public void registerPlayer(String name){
        Player newPlayer = new Player(name, this.game.getPlayers().size() + 1);
        
        if(this.game.addPlayer(newPlayer)){
            this.sendUDP(this.connect);
        } else {
            ConnectionData cd = new ConnectionData("-1", -1);
            this.sendUDP(cd);
        }
    }
    
    public void sendUDP(Object o){
        MulticastSocket socket = null;
        
        try {
            InetAddress group = InetAddress.getByName(this.IP); // destination multicast group 
            socket = new MulticastSocket(this.PORT);
            socket.joinGroup(group); 
            //s.setTimeToLive(10);
            
            System.out.println("Messages' TTL (Time-To-Live): " + socket.getTimeToLive());
            
            byte [] m = Utils.Serializing.serialize(o);
            
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
    
    public static void main(String args[]){ 
        MulticastSenderPeer msp = new MulticastSenderPeer();
        msp.registerPlayer("lpenaf");
        msp.registerPlayer("jjsa");
        msp.registerPlayer("fof");
        msp.registerPlayer("lpenaf");
        msp.registerPlayer("aadh");
    }
}
