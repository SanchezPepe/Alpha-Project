/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author LPENAF
 */
public class Player implements Serializable {
    private final String NAME;
    private final int ID;
    private int points;
    private boolean active;
    public static final Comparator<Player> POINTS_COMPARATOR = new Comparator<Player>() {         
        @Override         
        public int compare(Player player1, Player player2) {             
          return (player2.getPoints() < player1.getPoints() ? -1 :
                  (player2.getPoints() == player1.getPoints() ? 0 : 1));         
        }        
    }; 

    public Player(String name, int id) {
        this.NAME = name;
        this.ID = id;
        this.points = 0;
        this.active = true;
    }

    public String getNAME() {
        return NAME;
    }

    public int getID() {
        return ID;
    }   

    public void setPoints() {
        this.points += 1;
    }
    
    public void newPoints(){
        this.points=0;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setStatus(boolean status){
        this.active = status;
    }
    
    public boolean getStatus(){
        return this.active;
    }
    
    @Override
    public String toString() {
        return "Player{" + "\n\tname=" + NAME + "\n\tid=" + ID + "\n\tpoints=" + points +"\n\tStatus="+active+"\n\t}";
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.NAME);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        return true;
    }
    
}