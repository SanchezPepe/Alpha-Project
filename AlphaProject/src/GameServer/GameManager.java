/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameServer;

import GameClient.Player;
import java.util.ArrayList;

/**
 *
 * @author LPENAF
 */
public class GameManager {
    private WhacMole game;
    //direcciones para el multicast
    private final String IP = "228.5.6.7";
    private final int PORT = 6789;
    private final ConnectionData connect;

    
    public GameManager() {
        this.connect = new ConnectionData(this.IP, this.PORT);
        this.game = new WhacMole();
    }
    
    public Player getPlayerByName(String name){
        
        ArrayList<Player> jugadores = game.getPlayers();
        System.out.println("Jugadores registrados --> "+jugadores.toString());
        for (int i = 0; i < jugadores.size(); i++) {
            Player jugador = jugadores.get(i);
            if(jugador.getNAME().equals(name)){
                return jugador;
            }
        }
        return null;
    }
    
    public void reiniciar(){
        this.game.restart();
    }
    
    public ConnectionData registerPlayer(String name){
        Player newPlayer = new Player(name, this.game.getPlayers().size() + 1);
        
        if(this.game.addPlayer(newPlayer)){
            return this.connect;
        } else {
            int pos =getPlayerPosByName(name);
            if(pos>=0){
                Player p = this.game.getPlayers().get(pos);
                p.setStatus(true);
                this.game.updatePlayer(p);
                return this.connect;
            }else{
                return new ConnectionData("-1", -1);
            }
            
            
        }
    }
    
    public int getPlayerPosByName(String name){
        int res = -1;
        ArrayList<Player> jugadores = game.getPlayers();
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).getNAME().equals(name)){
                return i;
            }
        }
        return res;
    }

    public WhacMole getGame() {
        return game;
    }

    public void setGame(WhacMole game) {
        this.game = game;
    }
    
    public void changeBoard(){
        this.game.changeBoard();
    }
    
    public void updatePlayer(Player p){
        this.game.updatePlayer(p);
    }
    
}
