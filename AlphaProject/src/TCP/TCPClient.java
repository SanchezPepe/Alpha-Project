package TCP;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import GameClient.Player;
import GameServer.ConnectionData;
import GameServer.MulticastResponse;
import GameServer.SolicitudJugador;
import GameServer.SolicitudMulticast;
import GameServer.SolicitudRegistro;
import GameServer.SolicitudReinicio;
import java.net.*;
import java.io.*;


/**
 *
 * @author sdist
 */
public class TCPClient {
    
    public Player enviaJugador(Player jugadorEnvio) throws InterruptedException, ClassNotFoundException{
        Player jugadorRespuesta = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());   
            out.writeObject(jugadorEnvio);
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if(obj instanceof Player){
                jugadorRespuesta = (Player) obj;
            }
            //System.out.println("Despues de la respuesta");
            //System.out.println("Received: " + obj.toString());
            //Thread.sleep(15000);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
        return jugadorRespuesta;
    }
    
    //regresa un valor de tipo conexion data
    public ConnectionData enviaRegistro(String nombre) throws ClassNotFoundException{
        ConnectionData respuesta = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());   
            out.writeObject(new SolicitudRegistro(nombre));
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if(obj instanceof ConnectionData){
                respuesta = (ConnectionData) obj;
            }
            //System.out.println("Despues de la respuesta");
            //System.out.println("Received: " + obj.toString());
            //Thread.sleep(15000);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
        return respuesta;
    }
    
    public Player solicitaRegistroJugador(String nombre) throws ClassNotFoundException{
        Player p = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());   
            out.writeObject(new SolicitudJugador(nombre));
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if(obj instanceof Player){
                p = (Player) obj;
            }
            //System.out.println("Despues de la respuesta");
            //System.out.println("Received: " + obj.toString());
            //Thread.sleep(15000);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
        return p;
    }
    
    public MulticastResponse solicitudMulticast() throws ClassNotFoundException{
        MulticastResponse resp = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());   
            out.writeObject(new SolicitudMulticast());
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if(obj instanceof MulticastResponse){
                resp = (MulticastResponse) obj;
            }
            //System.out.println("Despues de la respuesta");
            //System.out.println("Received: " + obj.toString());
            //Thread.sleep(15000);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
        return resp;
    }
    
    public SolicitudReinicio solicitudReinicio()throws ClassNotFoundException{
        SolicitudReinicio resp = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());   
            out.writeObject(new SolicitudReinicio());
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if(obj instanceof SolicitudReinicio){
                resp = (SolicitudReinicio) obj;
            }
            //System.out.println("Despues de la respuesta");
            //System.out.println("Received: " + obj.toString());
            //Thread.sleep(15000);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
        return resp;
    }
    
//    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
//        Player jugadorEnvio = new Player("Fabian",1000);
//        TCPClient tcpC = new TCPClient();
//        Player p2 = tcpC.enviaJugador(jugadorEnvio);
//        System.out.println(p2.toString());
//    }
}
