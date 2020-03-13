/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import GameServer.ConnectionData;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LPENAF
 */
public class TCPClient {
    public final int PORT = 7896;
    
    public Object receiveTCP(String msj){
        Socket s = null;
        Object received = null;
        try {
            int serverPort = PORT;

            s = new Socket("localhost", serverPort);    
         //   s = new Socket("127.0.0.1", serverPort);
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            
            out.writeObject(msj);
            received = in.readObject();    
        } catch (UnknownHostException e) {
            System.out.println("Sock:"+e.getMessage()); 
        } catch (EOFException e) {
            System.out.println("EOF:"+e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:"+e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(s != null){
                try {
                    s.close();
                } catch (IOException e){
                    System.out.println("close:"+e.getMessage());
                }
            }
        }
        return received;
    }
    
    public static void main (String args[]) {
        TCPClient tcp = new TCPClient();
        Object obj = tcp.receiveTCP("REGISTER");
        
        if(obj instanceof ConnectionData){
            ConnectionData ct = (ConnectionData) obj;
            System.out.println(ct.toString());
        }
    }
}
