/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import java.util.Objects;

/**
 *
 * @author LPENAF
 */
public class Player {
    private String name;
    private int id;
    private int points;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.points = 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", id=" + id + ", points=" + points + '}';
    }
    
}
