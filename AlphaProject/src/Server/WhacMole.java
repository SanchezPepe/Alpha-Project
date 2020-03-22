/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Player;
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
    private String status;
    private int pointsToWin;
    
    public WhacMole(){
        this.board = new int[9];
        this.changeBoard();
        this.players = new ArrayList<>();
    }
    
    public void changeBoard(){
        Random r = new Random();
        this.board = new int[]{ 0,0,0,0,0,0,0,0,0 }; 
        this.monsterPosition = r.nextInt(9);
        this.board[this.monsterPosition] = 1;
    }

    public int[] getBoard() {
        return board;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPointsToWin(int pointsToWin) {
        this.pointsToWin = pointsToWin;
    }

    public boolean juegoTerminado(){
        return getGameMaxPoints() == this.pointsToWin;
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
    
    public void updatePlayer(Player p){
        for (int i = 0; i < players.size(); i++) {
            if(p.equals(players.get(i))){
                players.set(i, p);
            }
        }
    }
    
    public ArrayList<Player> getTopPlayers(){
        ArrayList<Player> jugad = new ArrayList<Player>();
        int maxPoints = getGameMaxPoints();
        for (int i = 0; i < this.players.size(); i++) {
            if(players.get(i).getPoints()==maxPoints){
                jugad.add(players.get(i));
            }
        }
        return jugad;
    }
    
    public int getGameMaxPoints(){
        int res = 0;
        int puntaje = 0;
        for (int i = 0; i < this.players.size(); i++) {
            puntaje = this.players.get(i).getPoints();
            if(puntaje>res){
                res = puntaje;
            }
        }
        return res;
    }
    
    public void setLeaderboard(){
        Collections.sort(this.players, Player.POINTS_COMPARATOR);
    }
    
    public String getScores(){
        setLeaderboard();
        String res = "Puntos\tJugador\n";
        Player p;
        System.out.println("Jugadores --> "+ players.toString());
        for (int i = 0; i < players.size(); i++) {
            p = players.get(i);
            if(p.getStatus()){
                res += p.getPoints() +" -- "+ p.getNAME()+"\n";
            }else{
                //res += p.getPoints() +" -- "+ p.getNAME()+"(Dado de baja)\n";
            }
            
        }
        return res;
    }
    
    public void restart(){
        Player p;
        for (int i = 0; i < players.size(); i++) {
            p = players.get(i);
            p.newPoints();
            this.players.set(i, p);
        }
    }
    
    @Override
    public String toString() {
        return "WhacMole{" + "board=" + Arrays.toString(board) + ", monsterPosition=" + monsterPosition + '}';
    }    
    
}
