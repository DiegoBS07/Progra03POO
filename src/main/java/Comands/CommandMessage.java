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
public class CommandMessage extends Command{
    private String senderName;
    public CommandMessage(String[] args) {
        super(CommandType.MESSAGE, args);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.senderName = threadServidor.name;
        this.setIsBroadcast(true);
        
    }
    
    @Override
    public void processInClient(Client client) {
        //Message "string"
        client.getRefFrame().writeMessage(this.senderName + " escribió: " + this.getParameters()[1]);
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
