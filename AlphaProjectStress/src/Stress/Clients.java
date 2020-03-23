/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stress;

import Client.Cliente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Jose Sanchez
 */
public class Clients {

    public static void writeNewFile(String filePath, String data) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void concatInFile(String fileName, String str){ 
        try { 
            // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)); 
            out.write(str); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        } 
    }
    
    public static void printFileContent(String ruta){
        try {
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    
    public static void main(String[] args) {
        int requests = 1;
        int numOfPlayers = 150; //1-2-3-4-5-6-7-8-9-10
        
        String ruta = "C:\\Users\\fabia\\Desktop\\SD\\proyecto-22032020\\Alpha-Project\\AlphaProjectStress\\src\\Stress\\resultados_registro2.txt";
        //Clients.concatInFile(ruta, "Para "+numOfPlayers+" jugadores\n");
        for (int i = 0; i < numOfPlayers; i++) {
            Cliente c = new Cliente("Client " + (i + 1), requests, (i+1));
            c.start();
        }

    }
}
