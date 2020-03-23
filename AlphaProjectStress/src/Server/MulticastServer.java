/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.TCPClient;
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
        TCPClient tcpClient = new TCPClient();
        Solicitud sol;
        while(true){
            try {
                wm.changeBoard();
                //aqui mandará los jugadores
                //mr = new MulticastResponse(wm,(new Date()).toString(),"5");
                wm = (WhacMole) tcpClient.solicitudMulticast();
                //System.out.println("Multicast recivido desde el TCP -> "+mr.toString());
                if(wm != null && wm.juegoTerminado()){
                    ms.sendUDP(wm);
                    // Espera 5 segundos para reiniciar el juego
                    Thread.sleep(5000);
                    sol = (Solicitud) tcpClient.solicitudReinicio();
                    ms.sendUDP(sol);
                }else{
                    ms.sendUDP(wm);
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
