/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Cliente.Client;
import Servidor.ThreadServidor;

/**
 *
 * @author diego
 */
public class CommandPrivateMessage extends Command{
    private String senderName;

    public CommandPrivateMessage(String[] args) {
        super(CommandType.PRIVATE_MESSAGE, args);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.senderName = threadServidor.name;
        this.setIsBroadcast(false);
    }
    
    @Override
    public void processInClient(Client client) {
        //private_message Andres "Hola mundo"
        client.getRefFrame().writeMessage( this.senderName + " escribió: " + this.getParameters()[2]);
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
