/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import java.io.Serializable;

/**
 *
 * @author fabia
 */
public class MulticastResponse implements Serializable{
    private WhacMole board;
    private String score;
    private String maxPoints;

    public MulticastResponse(WhacMole board, String score, String maxPoints) {
        this.board = board;
        this.score = score;
        this.maxPoints = maxPoints;
    }
    
    public boolean juegoTerminado(){
        return this.board.getGameMaxPoints()==Integer.parseInt(maxPoints);
    }

    public WhacMole getBoard() {
        return board;
    }

    public void setBoard(WhacMole board) {
        this.board = board;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(String maxPoints) {
        this.maxPoints = maxPoints;
    }  

    @Override
    public String toString() {
        return "MulticastResponse{" + "board=" + board + ", score=" + score + ", maxPoints=" + maxPoints + '}';
    }
    
    
}
