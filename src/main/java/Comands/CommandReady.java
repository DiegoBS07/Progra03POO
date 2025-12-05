/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Cliente.Client;
import Servidor.ThreadServidor;

/**
 *
 * @author Andrés
 */
public class CommandReady extends Command{
    
    public CommandReady(String[] parameters) {
        super(CommandType.READY, parameters);
        this.setManagedFromServer(true);
    }
    

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        //TODO: Nueva condicion de inicio
//        if (threadServidor.getFighters().getFighters().size() != 3){
//            threadServidor.throwError(new CommandGeneralError("Error, no todos los luchadores han sido creados"));
//            return;
//        }
        threadServidor.setReady(true);
        threadServidor.respondAction(this);
    }
    
    @Override
    public void processInClient(Client client){
        client.setIsReady(true);

    }
    
    
}
