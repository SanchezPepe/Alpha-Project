/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import java.io.Serializable;

/**
 *
 * @author LPENAF
 */
public class ConnectionData implements Serializable{
    private String ip;
    private int port;

    public ConnectionData(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
    
    @Override
    public String toString() {
        return "ConnectionData{" + "ip=" + ip + ", port=" + port + '}';
    }
}
