/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

/**
 *
 * @author LPENAF
 */
public class Player {
    private String name;
    private String id;
    private String port;
    private String ip;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", id=" + id + ", port=" + port + ", ip=" + ip + '}';
    }
    
}
