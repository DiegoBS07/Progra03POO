/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Comands.*;
import Comands.CommandGeneralError;
import Comands.CommandSkipTurn;
import Comands.DisponibilityCheckerOfCommand;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Server {
    private final int PORT = 35500;
    private final int maxConections = 4;
    private ServerSocket serverSocket;
    private ArrayList<ThreadServidor> connectedClients; // arreglo de hilos por cada cliente conectado
    //referencia a la pantalla
    FrameServer refFrame;
    private ThreadConnections connectionsThread;
    private TurnManager turnManager;

    public Server(FrameServer refFrame) {
        connectedClients = new ArrayList<ThreadServidor>();
        this.turnManager = new TurnManager(connectedClients);
        ThreadGameStarter threadGameStarter = new ThreadGameStarter(turnManager);
        threadGameStarter.start();
        this.refFrame = refFrame;
        this.init();
        connectionsThread = new ThreadConnections(this);
        connectionsThread.start();
    }
    
    //método que inicializa el server
    private void init(){
        try {
            serverSocket = new ServerSocket(PORT);
            refFrame.writeMessage("Server running!!!");
        } catch (IOException ex) {
            refFrame.writeMessage("Error: " + ex.getMessage());
        }
    }
    
    void executeCommand(Command comando, ThreadServidor threadServidor) {
        if (DisponibilityCheckerOfCommand.checkTurnCommandDisponibility(comando)) {
            if (comando.isIsBroadcast()) {
                broadcast(comando);
            } else {
                sendPrivate(comando);
            }
            return;
        }


        if (threadServidor.isTurn()) {
//            if (comando instanceof CommandHealing || comando instanceof CommandPower || comando instanceof CommandResistance){
//                broadcast(comando);
//                return;
//            }

            if (comando instanceof CommandSkipTurn){
                turnManager.processCommand(comando);    //Saltar turno
                return;
            }

            if (comando.isIsBroadcast()) {
                broadcast(comando);
                turnManager.processCommand(comando);
                return;
            } else {
                sendPrivate(comando);
                turnManager.processCommand(comando);
                return;
            }
        }
        threadServidor.throwError(new CommandGeneralError("No es tu turno"));


    }

    
    public void broadcast(Command comando){
        for (ThreadServidor client : connectedClients) {
            try {
                client.objectSender.writeObject(comando);
                client.objectSender.flush();
            } catch (IOException ex) {
                
            }
        }

    }
    
    public void sendPrivate(Command comando){
        //asumo que el nombre del cliente viene en la posición 1 .  private_message Andres "Hola"
        if (comando.getParameters().length <= 1)
            return;
        
        String searchName =  comando.getParameters()[1];
        
        for (ThreadServidor client : connectedClients) {
            if (client.name.equals(searchName)){
                try {
                //simulo enviar solo al primero, pero debe buscarse por nombre
                    client.objectSender.writeObject(comando);
                    break;
                } catch (IOException ex) {
                
                }
            }
        }
    }
    
    /*El método verifica si el nombre del cliente seleccionado ya existe en el servidor*/
    public boolean isExistentName(String name){
        if (connectedClients.isEmpty()){
            return false;
        }
        for (ThreadServidor connectedClient : connectedClients){
            if (connectedClient.name == null){  
            //Si no posee nombre asignado es por que es el nuevo cliente conectado
                continue;
            }
            if (connectedClient.name.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public void showAllNames(){
        this.refFrame.writeMessage("Usuarios conectados");
        for (ThreadServidor client : connectedClients) {
            this.refFrame.writeMessage(client.name);
        }
    }

    public ObjectInputStream obtainListener (String name){
        for (ThreadServidor client : connectedClients) {
            if (client.name.equals(name)){
                return client.objectListener;
            }
        }
        return null;
    }

    public int getMaxConections() {
        return maxConections;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ArrayList<ThreadServidor> getConnectedClients() {
        return connectedClients;
    }

    public FrameServer getRefFrame() {
        return refFrame;
    }


    
    
    
    
    

    
    
}
