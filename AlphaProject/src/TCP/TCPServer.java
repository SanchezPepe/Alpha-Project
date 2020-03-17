package TCP;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import GameClient.Player;
import GameServer.ConnectionData;
import GameServer.GameManager;
import GameServer.MulticastResponse;
import GameServer.SolicitudJugador;
import GameServer.SolicitudMulticast;
import GameServer.SolicitudRegistro;
import GameServer.SolicitudReinicio;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class TCPServer {

	public static void main(String args[]) throws IOException {
		int serverPort = 1025;
		ServerSocket listenSocket = new ServerSocket(serverPort);
                GameManager gm = new GameManager();
                int maxPoints = (int)(3+Math.random()*3);
		while (true) {
			try {
				System.out.println("Waiting for players response...");
				/**
				 * Listens for a connection to be made to this socket and accepts it. The method
				 * blocks until a connection is made.
				 */
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket, gm, maxPoints);
				c.start();
			} catch (IOException e) {
				System.out.println("Listen :" + e.getMessage());
			} finally {
				//listenSocket.close();
			}
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
                    if(obj instanceof Player){
                        System.out.println("Se recibió un jugador");
                        Player p = (Player) obj;
                        System.out.println(p.toString());
                        this.gm.updatePlayer(p);
                        //System.out.println("Escribiendo respuesta desde el server");
                        out.writeObject(obj);
                        //System.out.println("Fin de respuesta");
                    }else{
                        if(obj instanceof String){
                            System.out.println("Se recibió un string");
                            //aqui se asume que se registran
                            //se tiene que regresar la conexion
                            //out.writeObject(obj);
                            //se tiene que regresar el identificador
                            out.writeObject(obj);
                            //System.out.println(((String) obj).toString());
                            
                        }else{
                            if(obj instanceof SolicitudRegistro){                  
                                SolicitudRegistro sr = (SolicitudRegistro) obj;
                                ConnectionData cd = gm.registerPlayer(sr.getNombre());
                                System.out.println("Soliciud de registro que regresa --> "+cd.toString());
                                System.out.println("Los jugadores registrados son: "+gm.getGame().getPlayers().toString());
                                out.writeObject(cd);
                            }else{
                                if(obj instanceof SolicitudJugador){
                                    SolicitudJugador sr = (SolicitudJugador) obj;
                                    Player cd = gm.getPlayerByName(sr.getNombre());
                                    out.writeObject(cd);
                                }else{
                                    if(obj instanceof SolicitudMulticast){
                                        //aqui se responde a la solicitud del multicast
                                        this.gm.getGame().changeBoard();
                                        int maxActual = this.gm.getGame().getGameMaxPoints();
                                        String mensaje = gm.getGame().getScores();
                                        if(maxActual>=maxPoints){
                                            mensaje = "El juego ha terminado\n "+
                                                    "\t el ganador es: "+gm.getGame().getTopPlayers().get(0).getNAME();
                                        }
                                        
                                        MulticastResponse mr = new MulticastResponse(gm.getGame(),mensaje,""+maxPoints);
                                        //System.out.println("Solicitud pedida por parte del multicast");
                                        out.writeObject(mr);
                                    }else{
                                        if(obj instanceof SolicitudReinicio){
                                            this.gm.reiniciar();
                                            //MulticastResponse mr = new MulticastResponse(gm.getGame(),gm.getGame().getScores(),""+maxPoints);
                                            out.writeObject(obj);
                                        }
                                    }
                                }
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
