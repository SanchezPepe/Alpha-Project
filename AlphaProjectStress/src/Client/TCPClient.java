package Client;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Server.ConnectionData;
import Server.Solicitud;
import Server.WhacMole;
import java.net.*;
import java.io.*;

/**
 *
 * @author sdist
 */
public class TCPClient {

    public Player enviaJugador(Player jugadorEnvio, int req) throws InterruptedException, ClassNotFoundException {
        Player jugadorRespuesta = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            out.writeObject(jugadorEnvio);

            Object obj = in.readObject();
            if (obj instanceof Player) {
                jugadorRespuesta = (Player) obj;
            }

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return jugadorRespuesta;
    }

    //regresa un valor de tipo conexion data
    public ConnectionData enviaRegistro(String nombre) throws ClassNotFoundException {
        ConnectionData respuesta = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            out.writeObject(new Solicitud(2, nombre));
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if (obj instanceof ConnectionData) {
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
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return respuesta;
    }

    public Player solicitaRegistroJugador(String nombre) throws ClassNotFoundException {
        Player p = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            out.writeObject(new Solicitud(1, nombre));
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if (obj instanceof Player) {
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
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return p;
    }

    public WhacMole solicitudMulticast() throws ClassNotFoundException {
        WhacMole resp = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            out.writeObject(new Solicitud(4, null));
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            if (obj instanceof WhacMole) {
                resp = (WhacMole) obj;
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
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return resp;
    }

    public Solicitud solicitudReinicio() throws ClassNotFoundException {
        Solicitud resp = null;
        Socket s = null;
        try {
            int serverPort = 1025;
            s = new Socket("localhost", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            out.writeObject(new Solicitud(3, null));
            //p = (Player) 
            //System.out.println("En espera de la respuesta");
            Object obj = in.readObject();
            Solicitud sol = (Solicitud) obj;
            if (sol.getTipo() == 3) {
                resp = sol;
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
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
        return resp;
    }
}
