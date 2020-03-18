/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import TCP.TCPClient;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LPENAF
 */
public class MulticastServer extends Thread{
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    
    public void sendUDP(Object obj){
        //System.out.println("Objeto a mandar "+obj.toString());
        MulticastSocket socket = null;
        
        try {
            InetAddress group = InetAddress.getByName(this.IP); // destination multicast group 
            socket = new MulticastSocket(this.PORT);
            socket.joinGroup(group);
            //s.setTimeToLive(10);
            
            //System.out.println("Messages' TTL (Time-To-Live): " + socket.getTimeToLive());
            
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
    
    public void run(){
        MulticastServer ms = new MulticastServer();
        WhacMole wm = new WhacMole();
        //String score = (new Date()).toString();
        MulticastResponse mr;
        TCPClient tcpClient = new TCPClient();
        SolicitudReinicio solicitudReinicio;
        while(true){
            try {
                wm.changeBoard();
                //aqui mandará los jugadores
                //mr = new MulticastResponse(wm,(new Date()).toString(),"5");
                mr = (MulticastResponse) tcpClient.solicitudMulticast();
                //System.out.println("Multicast recivido desde el TCP -> "+mr.toString());
                if(mr.juegoTerminado()){
                    //mr = tcpClient.solicitudReinicio();
                    //mr.setScore("El juego ha terminado");
                    ms.sendUDP(mr);
                    //Thread.sleep(5000);
                    System.out.println("Antes de los 7");
                    Thread.sleep(7000);
                    System.out.println("Despues de los 7");
                    solicitudReinicio = (SolicitudReinicio) tcpClient.solicitudReinicio();
                    ms.sendUDP(solicitudReinicio);
                    //mr = (MulticastResponse) tcpClient.solicitudReinicio();
                }else{
                    ms.sendUDP(mr);
                }
                Thread.sleep(1000);
                //score = (new Date()).toString();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
