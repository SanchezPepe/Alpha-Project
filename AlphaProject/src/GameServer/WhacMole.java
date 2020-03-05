/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameServer;

import java.io.Serializable;
import java.util.Arrays;
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

    public WhacMole(){
        this.board = new int[9];
        this.changeBoard();
    }
    
    public void changeBoard(){
        Random r = new Random();
        
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = 0;
        }
        this.monsterPosition = r.nextInt(9);
        this.board[this.monsterPosition] = 1;
    }

    @Override
    public String toString() {
        return "WhacMole{" + "board=" + Arrays.toString(board) + ", monsterPosition=" + monsterPosition + '}';
    }    
    
}
