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
public class CommandGiveup  extends Command{
    private String senderName;
    public CommandGiveup(String[] args) {
        super(CommandType.PRIVATE_MESSAGE, args);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.senderName = threadServidor.name;
        this.setIsBroadcast(true);
        threadServidor.isActive = false;
    }
    
    @Override
    public void processInClient(Client client) {
        //Message "string"
        client.getRefFrame().writeMessage(this.senderName + " escribió: GIVEUP, me rindo, dejo la partida");
    }
}
