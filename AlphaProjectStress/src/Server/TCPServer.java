package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Client.Player;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class TCPServer extends Thread {

    public void run() {
        try {
            int serverPort = 1025;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            GameManager gm = new GameManager();
            int maxPoints = (int) (3 + Math.random() * 3);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, gm, maxPoints);
                c.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Connection extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    GameManager gm;
    int maxPoints;

    public Connection(Socket aClientSocket, GameManager gm, int maxPoints) {
        try {
            clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.gm = gm;
            this.maxPoints = maxPoints;
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            Object obj = in.readObject();
            if (obj instanceof Player) {
                Player p = (Player) obj;
                System.out.println(p.getMessage());
                this.gm.updatePlayer(p);
                out.writeObject(obj);
                //System.out.println("Fin de respuesta");
            } else {
                if (obj instanceof String) {
                    System.out.println("Se recibió un string");
                    out.writeObject(obj);
                } else {
                    // El objeto es una solicitud
                    Solicitud s = (Solicitud) obj;
                    int tipoSolicitud = s.getTipo();
                    switch (tipoSolicitud) {
                        // Solicitud Jugador
                        case 1:
                            Player cd = gm.getPlayerByName(s.getNombre());
                            out.writeObject(cd);
                            break;
                        // Solicitud Registro
                        case 2:
                            ConnectionData con = gm.registerPlayer(s.getNombre());
                            System.out.println("Soliciud de registro que regresa --> " + con.toString());
                            System.out.println("Los jugadores registrados son: " + gm.getGame().getPlayers().toString());
                            out.writeObject(con);
                            break;
                        // Solicitud Reinicio
                        case 3:
                            this.gm.reiniciar();
                            out.writeObject(obj);
                            break;
                        // Solicitud Multicast
                        case 4:
                            this.gm.getGame().changeBoard();
                            int maxActual = this.gm.getGame().getGameMaxPoints();
                            String mensaje = gm.getGame().getScores();
                            if (maxActual >= maxPoints) {
                                mensaje = "Juego terminado, ganador: " + gm.getGame().getTopPlayers().get(0).getNAME();
                            }
                            
                            WhacMole wm = gm.getGame();
                            wm.setStatus(mensaje);
                            wm.setPointsToWin(maxPoints);

                            out.writeObject(wm);
                            break;
                    }
                }
            }
            //
            //p.setID(p.getID() + 1);
            //System.out.println("Received from player: " + p.getID() + " " + p.getNAME());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
