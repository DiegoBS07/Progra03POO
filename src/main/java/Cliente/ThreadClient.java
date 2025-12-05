/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Comands.Command;
import Servidor.Server;
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
        while (isRunning){
            try {
                Object recibido = client.objectListener.readObject(); //Recibe el objeto del server, este siendo de cualquier clase.
            
                if(recibido instanceof Command){
                comandoRecibido = (Command) recibido;
                comandoRecibido.processInClient(client);
                }
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        
    }
    

    
    

    
}
