/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameServer;

import GameClient.Player;

/**
 *
 * @author LPENAF
 */
public class GameManager {
    private WhacMole game;
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    private final ConnectionData connect;

    public GameManager() {
        this.game = new WhacMole();
        this.connect = new ConnectionData(this.IP, this.PORT);
    }
    
    public ConnectionData registerPlayer(String name){
        Player newPlayer = new Player(name, this.game.getPlayers().size() + 1);
        
        if(this.game.addPlayer(newPlayer)){
            return this.connect;
        } else {
            return new ConnectionData("-1", -1);
        }
    }
}
