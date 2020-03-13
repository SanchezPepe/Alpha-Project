/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameClient;

import java.io.Serializable;


/**
 *
 * @author JGUTIERRGARC
 */
public class Person implements Serializable{
    private String name;
    private String ciudadOrigen;
    private int añoNac;

    public Person(String name, String ciudadOrigen, int añoNac) {
        this.name = name;
        this.ciudadOrigen = ciudadOrigen;
        this.añoNac = añoNac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public int getAñoNac() {
        return añoNac;
    }

    public void setAñoNac(int añoNac) {
        this.añoNac = añoNac;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", ciudadOrigen=" + ciudadOrigen + ", a\u00f1oNac=" + añoNac + '}';
    }

}