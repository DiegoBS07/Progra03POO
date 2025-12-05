/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comands;

import Cliente.Client;
import Servidor.ThreadServidor;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author diego
 */
public abstract class Command implements Serializable{
    private CommandType type;
    private String[] parameters;
    private boolean isBroadcast;
    private boolean ManagedFromServer = false;

    public Command(CommandType type, String[] parameters) {
        this.type = type;
        this.parameters =  parameters; // Enemigo 4 5 6 7 9 10
    }
    
    public abstract void processForServer(ThreadServidor threadServidor);
    public void processInClient(Client client){
        client.getRefFrame().writeMessage(this.toString());
    }

    public CommandType getType() {
        return type;
    }

    public String[] getParameters() {
        return parameters;
    }
    
    public String toString(){
        return type.toString() + "->" + Arrays.toString(parameters);
    }

    public boolean isIsBroadcast() {
        return isBroadcast;
    }

    public void setIsBroadcast(boolean isBroadcast) {
        this.isBroadcast = isBroadcast;
    }

    public boolean isManagedFromServer() {
        return ManagedFromServer;
    }

    public void setManagedFromServer(boolean ManagedFromServer) {
        this.ManagedFromServer = ManagedFromServer;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }
}
