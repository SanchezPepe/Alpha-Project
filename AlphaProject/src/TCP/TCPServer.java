package TCP;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import GameClient.Player;
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
		while (true) {
			try {
				System.out.println("Waiting for players response...");
				/**
				 * Listens for a connection to be made to this socket and accepts it. The method
				 * blocks until a connection is made.
				 */
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
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

	public Connection(Socket aClientSocket) {
		try {
                    clientSocket = aClientSocket;
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			Player p = (Player) in.readObject();
			//p.setID(p.getID() + 1);
			System.out.println("Received from player: " + p.getID() + " " + p.getNAME());
			out.writeObject(p);
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
