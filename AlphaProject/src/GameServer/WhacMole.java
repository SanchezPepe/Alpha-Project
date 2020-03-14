/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import GameClient.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author LPENAF
 */
public class WhacMole implements Serializable{
    /*
    if cell has monster then board[i] = 1 otherwise board[i] = 0
    */
    private int board[];
    private int monsterPosition;
    private ArrayList<Player> players;
    
    public WhacMole(){
        this.board = new int[9];
        this.changeBoard();
        this.players = new ArrayList<>();
    }
    
    public void changeBoard(){
        Random r = new Random();
        
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = 0;
        }
        this.monsterPosition = r.nextInt(9);
        this.board[this.monsterPosition] = 1;
    }

    public int[] getBoard() {
        return board;
    }

    public int getMonsterPosition() {
        return monsterPosition;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public boolean addPlayer(Player player){
        if(!this.players.contains(player)){
            return this.players.add(player);
        }
        return false;
    }
    
    public void setLeaderboard(){
        Collections.sort(this.players, Player.POINTS_COMPARATOR);
    }
    
    @Override
    public String toString() {
        return "WhacMole{" + "board=" + Arrays.toString(board) + ", monsterPosition=" + monsterPosition + '}';
    }    
    
}
