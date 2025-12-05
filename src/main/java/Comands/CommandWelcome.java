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
public class CommandWelcome extends Command {

    public CommandWelcome() {
        String[] welcomeMsg = {"WELCOME"};
        super(CommandType.WELCOME, welcomeMsg);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        threadServidor.writeInFrame("Se dio la bienvenida a un cliente");
    }
    
    @Override
    public void processInClient(Client client){
        client.getRefFrame().writeMessage("Bienvenido/a a la batalla!");
    }
    
    
    
}
