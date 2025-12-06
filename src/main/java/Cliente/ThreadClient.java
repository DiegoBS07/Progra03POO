/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Comands.Command;
import Servidor.Server;
import Warriors.Warrior;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;

/**
 * 
 * @author diego
 */
public class ThreadClient extends Thread{
    private Client client;
    
    private boolean isRunning = true;

    public ThreadClient(Client client) {
        this.client = client;

    }
    
    public void run (){
        
        Command comandoRecibido;
        Warrior receivedWarrior;
        while (isRunning){
            try {
                Object recibido = client.objectListener.readObject(); //Recibe el objeto del server, este siendo de cualquier clase.
            
                if(recibido instanceof Command){
                comandoRecibido = (Command) recibido;
                comandoRecibido.processInClient(client);
                }
                else if(recibido instanceof Warrior){
                    receivedWarrior = (Warrior)recibido;
                    this.client.getWarriors().registerWarrior(receivedWarrior);
                    //TODO: Colocar Warrior en la interfaz Grafica
                }
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        
    }
    

    
    

    
}
