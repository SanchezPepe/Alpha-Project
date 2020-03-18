/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import GUI.Board;
import GameClient.MulticastClient;
import GameClient.Player;
import GameServer.ConnectionData;
import GameServer.MulticastResponse;
import GameServer.SolicitudReinicio;
import GameServer.WhacMole;
import TCP.TCPClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fabia
 */
public class Cliente extends Thread {

    public void run() {
        try {
            //este se debe cambiar por un servicio tcp
            //GameManager gm = new GameManager();
            //hace el registro tcp --> Cambiar por la peticion TCP
            String nombre;
            nombre = JOptionPane.showInputDialog(null, "Por favor, escriba su nombre");
            //ConnectionData cd; = gm.registerPlayer(nombre);
            TCPClient tcpClient = new TCPClient();
            ConnectionData cd = tcpClient.enviaRegistro(nombre);

            System.out.println(cd.toString());
            //Thread.sleep(5000);
            if (cd != null) {
                //conseguimos nuestro jugador
                Player yo = tcpClient.solicitaRegistroJugador(nombre);
                System.out.println(yo.toString());
                //quiere decir que ya se le dio el lugar para hacer la conexion
                //aqui se levanta el multicast, el y el tablero
                MulticastClient mc = new MulticastClient();
                //mandamos el tablero con nuestro jugador
                Board b = new Board(yo);
                b.setVisible(true);
                //se la pasa escuchando siempre al multicast
                WhacMole wm;
                MulticastResponse mr;
                while (true) {
                    Object obj = mc.receiveUDP();
                    System.out.println("Objeto recibido: " + obj.toString());
                    if (obj instanceof WhacMole) {
                        wm = (WhacMole) obj;
                        //System.out.println(wm.toString());
                        b.updateBoard(wm.getBoard());
                    } else {
                        if (obj instanceof String) {
                            System.out.println("Recibiendo en el cliente");
                            b.updateScore((String) obj);
                        } else {
                            if (obj instanceof MulticastResponse) {
                                //aqui se recibe el whacmole, el puntaje, y los puntos maximos para terminar el juego
                                mr = (MulticastResponse) obj;
                                b.updateBoard(mr.getBoard().getBoard());
                                b.updateTerminado(mr.juegoTerminado());
                                b.updateScore(mr.getScore());

                                //Thread.sleep(3000);
                            } else {
                                if (obj instanceof SolicitudReinicio) {
                                    int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
                                    b.updateBoard(board);
                                    b.updateScore("¡Juego nuevo!");
                                    b.updateTerminado(false);
                                    b.resetPoints();
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
