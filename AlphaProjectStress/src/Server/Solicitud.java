/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.Serializable;

/**
 *
 * @author Jose Sanchez
 */
public class Solicitud implements Serializable {
    
    /**
     * Tipos de solicitud
     * 1. Jugador
     * 2. Registro
     * 3. Reinicio
     * 4. Multicast
     */
    private int tipo;
    // Nombre de solicitud o jugador
    private String nombre;
    
    public Solicitud(int tipo, String nom){
        this.tipo = tipo;
        this.nombre = nom;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    
    
}
